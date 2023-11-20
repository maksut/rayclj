(ns gen.core
  (:require
   [clojure.string :as string]
   [clojure.java.io :as io]
   [jsonista.core :as json]
   [zprint.core :as zp]
   [gen.overrides :as overrides]))

(defn c-name->clj-name [s]
  (when s
    (-> s
        (string/replace #"([a-z])([A-Z])" "$1-$2")
        (string/replace #"_" "-")
        (string/replace #" " "-")
        (string/lower-case)
        (string/replace #"^rl-" "")))) ;; drop `rl-` prefix from rlgl definitions

(def memory-segment-symbol (symbol "^MemorySegment"))

(defn array? [name]
  (re-matches #"(.+)\[([0-9]+)\]" name))

(defn pointer? [name]
  (re-find #"([a-zA-Z0-9]*)\s*\*" name))

(defn c-string? [name]
  (= name "const char *"))

(defn field-getter [header-name struct-name all-struct-names {:keys [name type]}]
  (let [resolved-type (get all-struct-names type)
        [_ array array-size] (array? type)]
    (cond
      resolved-type
      (let [getter (symbol (str "rayclj." header-name "." struct-name "/" name "$slice"))
            struct-getter (symbol (str "get-" (c-name->clj-name resolved-type)))]
        `(~struct-getter (~getter ~'seg)))

      array
      (let [getter (symbol (str "rayclj." header-name "." struct-name "/" name "$slice"))
            struct-getter (symbol (str "get-" (c-name->clj-name array) "-array"))]
        `(~struct-getter (~getter ~'seg) ~(Integer/parseInt array-size)))

      :else
      (let [getter (symbol (str "rayclj." header-name "." struct-name "/" name "$get"))]
        `(~getter ~'seg)))))

(defn field-setter [header-name struct-name all-struct-names {:keys [name type]}]
  (let [resolved-type (get all-struct-names type)
        [_ array array-size] (array? type)]
    (cond
      resolved-type
      (let [setter (symbol (str "rayclj." header-name "." struct-name "/" name "$slice"))
            struct-setter (symbol (str "set-" (c-name->clj-name resolved-type)))]
        `(~struct-setter (~setter ~'seg) ~(symbol name)))

      array
      (let [setter (symbol (str "rayclj." header-name "." struct-name "/" name "$slice"))
            struct-setter (symbol (str "set-" (c-name->clj-name array) "-array"))]
        `(~struct-setter (~setter ~'seg) ~(symbol name) ~(Integer/parseInt array-size)))

      :else
      (let [setter (str "rayclj." header-name "." struct-name "/" name "$set")]
        `(~(symbol setter) ~'seg ~(symbol name))))))

(defn field-getter-kv [header-name struct-name all-struct-names field]
  (list (keyword (:name field)) (field-getter header-name struct-name all-struct-names field)))

(defn doc-str [{:keys [description fields docstring]}]
  (if docstring
    docstring
    (let [field-doc (fn [{:keys [name type description]}]
                      (str ":EL  " type " " name (when description (str " // " description))))]
      (str description
           (apply str (map field-doc fields))))))

(defn struct-get-fn [header-name all-struct-names {:keys [name fields as-vector?] :as struct}]
  (let [kebab-name (symbol (str "get-" (c-name->clj-name name)))]
    `(~'defn ~kebab-name ~(doc-str struct)
             [~memory-segment-symbol ~'seg]
             ~(if as-vector?
                (into [] (map (partial field-getter header-name name all-struct-names) fields))
                (apply array-map (mapcat (partial field-getter-kv header-name name all-struct-names) fields))))))

(defn struct-set-fn [header-name all-struct-names {:keys [name fields as-vector?] :as struct}]
  (let [kebab-name (symbol (str "set-" (c-name->clj-name name)))
        args (mapv (comp symbol :name) fields)
        args (if as-vector? args (array-map :keys args))]
    `(~'defn ~kebab-name ~(doc-str struct)
             [~memory-segment-symbol ~'seg ~args]
             ~@(map (partial field-setter header-name name all-struct-names) fields)
             ~'seg)))

(defn struct-fn [header-name {:keys [name] :as struct}]
  (let [kebab-name (c-name->clj-name name)
        fn-name (symbol kebab-name)
        struct-set-fn (symbol (str "set-" kebab-name))
        layout-sym (symbol (str "rayclj." header-name "." name "/$LAYOUT"))]
    `(~'defn ~fn-name ~(doc-str struct)
             [~'v]
             (if (instance? ~'MemorySegment ~'v)
               ~'v
               (~struct-set-fn (memory/allocate (~layout-sym)) ~'v)))))

(defn array-fn [header-name {:keys [name]}]
  (let [kebab-name (c-name->clj-name name)
        fn-name (symbol (str kebab-name "-array"))
        struct-set-fn (symbol (str "set-" kebab-name))
        layout-sym (symbol (str "rayclj." header-name "." name "/$LAYOUT"))]
    `(~'def ~fn-name (~'array-fn (~layout-sym) ~struct-set-fn))))

(defn get-array-fn [header-name {:keys [name]}]
  (let [kebab-name (c-name->clj-name name)
        fn-name (symbol (str "get-" kebab-name "-array"))
        struct-set-fn (symbol (str "get-" kebab-name))
        layout-sym (symbol (str "rayclj." header-name "." name "/$LAYOUT"))]
    `(~'def ~fn-name (~'get-array-fn (~layout-sym) ~struct-set-fn))))

(defn set-array-fn [header-name {:keys [name]}]
  (let [kebab-name (c-name->clj-name name)
        fn-name (symbol (str "set-" kebab-name "-array"))
        struct-set-fn (symbol (str "set-" kebab-name))
        layout-sym (symbol (str "rayclj." header-name "." name "/$LAYOUT"))]
    `(~'def ~fn-name (~'set-array-fn (~layout-sym) ~struct-set-fn))))

(defn pprint [f]
  (-> (zp/zprint-str f)
      (string/replace #":EL" "\n")
      (string/replace #"\\n" "\n")
      (string/replace #":CARET" "^")))

(defn get-all-struct-names [api]
  (let [struct-names (map #(:name %) (:structs api))
        struct-names (apply hash-map (interleave struct-names struct-names))
        aliases (mapcat #(list (:name %) (:type %)) (:aliases api))
        aliases (apply hash-map aliases)]
    (merge aliases struct-names)))

(defn get-overrided
  [overrides definition]
  (let [name (keyword (second definition))
        override (get overrides name)]
    (if override
      override
      [definition])))

(defn get-overrided-struct-fn
  [definition]
  (get-overrided overrides/struct-functions definition))

(defn get-overrided-fn
  [definition]
  (get-overrided overrides/functions definition))

(defn pprint-struct-fns [header-name out-file all-struct-names struct]
  (let [get-fn (struct-get-fn header-name all-struct-names struct)
        set-fn (struct-set-fn header-name all-struct-names struct)
        struct-fn (struct-fn header-name struct)
        array-fn (array-fn header-name struct)
        get-array-fn (get-array-fn header-name struct)
        set-array-fn (set-array-fn header-name struct)
        str-fns [get-fn set-fn struct-fn array-fn get-array-fn set-array-fn]
        str-fns (mapcat get-overrided-struct-fn str-fns)
        str-fns (map pprint str-fns)
        str-fns (apply str (interleave str-fns (repeat "\n\n")))]
    (spit out-file str-fns :append true)))

(defn add-as-vector [{:keys [name] :as struct}]
  (let [as-vector? (or (clojure.string/starts-with? name "Vector")
                       (clojure.string/starts-with? name "Matrix"))]
    (assoc struct :as-vector? as-vector?)))

(defn add-docstring [{:keys [name] :as struct}]
  (if-let [docstring (get overrides/docstrings name)]
    (assoc struct :docstring docstring)
    struct))

(defn filter-duplicates
  ([functions] (filter-duplicates functions #{}))
  ([[f & fs] seen-names]
   (if f
     (let [name (:name f)
           new-names (conj seen-names name)]
       (if (contains? seen-names name)
         (do
           (println "Skipping " name "because of duplicates")
           (filter-duplicates fs new-names))
         (lazy-seq (cons f (filter-duplicates fs new-names)))))
     nil)))

(defn process-api [{:keys [structs functions] :as api}]
  (let [structs (map add-as-vector structs)
        structs (map add-docstring structs)

        ; not in the generated java files because of missing C defines
        blacklisted-structs #{"rlglData"}
        blacklisted-functions #{"rlEnableStatePointer" "rlDisableStatePointer"}

        structs (filter #(not (blacklisted-structs (:name %))) structs)
        functions (filter #(not (blacklisted-functions (:name %))) functions)
        functions (filter-duplicates functions)]
    (-> api
        (assoc :structs structs)
        (assoc :functions functions))))

(defn generate-structs [header-name api]
  (let [out-file (str "src/clj/rayclj/" header-name "/structs.clj")
        template-file (str "dev/gen/" header-name "/structs.clj.template")
        structs (:structs api)
        all-struct-names (get-all-struct-names api)]
    (io/make-parents out-file)
    (spit out-file (slurp template-file))
    (dorun
     (map
      (partial pprint-struct-fns header-name out-file all-struct-names)
      structs))
    api))

;; Currently only supports function arguments. Struct fields are not supported.
(def enum-args
  {["SetConfigFlags" "flags"] "renums/config-flags"
   ["SetWindowState" "flags"] "renums/config-flags"
   ["ClearWindowState" "flags"] "renums/config-flags"
   ["IsWindowState" "flag"] "renums/config-flags"
   ["TraceLog" "logLevel"] "renums/trace-log-level"
   ["SetTraceLogLevel" "logLevel"] "renums/trace-log-level"
   ["IsKeyPressed" "key"] "renums/keyboard-key"
   ["IsKeyPressedRepeat" "key"] "renums/keyboard-key"
   ["IsKeyDown" "key"] "renums/keyboard-key"
   ["IsKeyReleased" "key"] "renums/keyboard-key"
   ["IsKeyUp" "key"] "renums/keyboard-key"
   ["SetExitKey" "key"] "renums/keyboard-key"
   ["SetCameraAltControl" "keyAlt"] "renums/keyboard-key"
   ["SetCameraSmoothZoomControl" "keySmoothZoom"] "renums/keyboard-key"
   ["SetCameraMoveControls" "keyFront"] "renums/keyboard-key"
   ["SetCameraMoveControls" "keyBack"] "renums/keyboard-key"
   ["SetCameraMoveControls" "keyRight"] "renums/keyboard-key"
   ["SetCameraMoveControls" "keyLeft"] "renums/keyboard-key"
   ["SetCameraMoveControls" "keyUp"] "renums/keyboard-key"
   ["SetCameraMoveControls" "keyDown"] "renums/keyboard-key"
   ["IsMouseButtonPressed" "button"] "renums/mouse-button"
   ["IsMouseButtonDown" "button"] "renums/mouse-button"
   ["IsMouseButtonReleased" "button"] "renums/mouse-button"
   ["IsMouseButtonUp" "button"] "renums/mouse-button"
   ["SetCameraPanControl" "keyPan"] "renums/mouse-button"
   ["SetMouseCursor" "cursor"] "renums/mouse-cursor"
   ["IsGamepadButtonPressed" "button"] "renums/gamepad-button"
   ["IsGamepadButtonDown" "button"] "renums/gamepad-button"
   ["IsGamepadButtonReleased" "button"] "renums/gamepad-button"
   ["IsGamepadButtonUp" "button"] "renums/gamepad-button"
   ["GetGamepadAxisMovement" "axis"] "renums/gamepad-axis"
   ["SetShaderValue" "uniformType"] "renums/shader-uniform-data-type"
   ["SetShaderValueV" "uniformType"] "renums/shader-uniform-data-type"
   ["LoadImageRaw" "format"] "renums/pixel-format"
   ["ImageFormat" "newFormat"] "renums/pixel-format"
   ["SetTextureFilter" "filter"] "renums/texture-filter"
   ["SetTextureWrap" "wrap"] "renums/texture-wrap"
   ["LoadTextureCubemap" "layout"] "renums/cubemap-layout"
   ["LoadFontData" "type"] "renums/font-type"
   ["BeginBlendMode" "mode"] "renums/blend-mode"
   ["SetGesturesEnabled" "flags"] "renums/gesture" ;; combination of flags not supported yet
   ["IsGestureDetected" "gesture"] "renums/gesture"
   ["SetCameraMode" "mode"] "renums/camera-mode"})

(defn coerced-arg [all-struct-names function-name {:keys [name type]}]
  (let [pointer-type (second (pointer? type))
        struct-name (get all-struct-names (or pointer-type type))
        enum-arg (enum-args [function-name name])
        name (symbol (c-name->clj-name name))]
    (cond
      (c-string? type) `(~'memory/string ~name)
      struct-name (let [struct-fn (symbol (str "rstructs/" (c-name->clj-name struct-name)))]
                    `(~struct-fn ~name))
      enum-arg `(if (~'keyword? ~name) (~(symbol enum-arg) ~name) ~name)
      :else name)))

(defn clj-fn-name [name return-type]
  (let [name (if (string/starts-with? name "Is") (subs name 2) name)
        name (if (= "bool" return-type) (str name "?") name)
        name (c-name->clj-name name)]
    name))

(defn fn-doc-str [{:keys [description params returnType docstring]}]
  (if docstring
    docstring
    (let [field-doc (fn [{:keys [name type]}] (str type " " name))
          field-docs (string/join ", " (map field-doc params))]
      (str description :EL "  [" field-docs "] -> " returnType))))

(def first-arg-is-return
  #{"ImageFormat"
    "ImageToPOT"
    "ImageCrop"
    "ImageAlphaCrop"
    "ImageAlphaClear"
    "ImageAlphaMask"
    "ImageAlphaPremultiply"
    "ImageBlurGaussian"
    "ImageResize"
    "ImageResizeNN"
    "ImageResizeCanvas"
    "ImageMipmaps"
    "ImageDither"
    "ImageFlipVertical"
    "ImageFlipHorizontal"
    "ImageRotateCW"
    "ImageRotateCCW"
    "ImageColorTint"
    "ImageColorInvert"
    "ImageColorGrayscale"
    "ImageColorContrast"
    "ImageColorBrightness"
    "ImageColorReplace"
    "ImageClearBackground"
    "ImageDrawPixel"
    "ImageDrawPixelV"
    "ImageDrawLine"
    "ImageDrawLineV"
    "ImageDrawCircle"
    "ImageDrawCircleV"
    "ImageDrawCircleLines"
    "ImageDrawCircleLinesV"
    "ImageDrawRectangle"
    "ImageDrawRectangleV"
    "ImageDrawRectangleRec"
    "ImageDrawRectangleLines"
    "ImageDraw"
    "ImageDrawText"
    "ImageDrawTextEx"})

(defn get-fn [header-name all-struct-names {:keys [name params returnType] :as function}]
  (let [return-first-arg (first-arg-is-return name)
        java-fn (symbol (str header-name "_h/" name))
        clj-fn (symbol (clj-fn-name name returnType))
        args (mapv (comp symbol c-name->clj-name :name) params)
        struct-return (c-name->clj-name (get all-struct-names returnType))
        coerced-args (mapv (partial coerced-arg all-struct-names name) params)]
    (cond
      return-first-arg
      (let [type (:type (first params))
            first-type (second (pointer? type))
            struct-name (c-name->clj-name (get all-struct-names first-type))
            struct (symbol (str "rstructs/get-" struct-name))]
        `(~'defn ~clj-fn
                 ~(fn-doc-str function)
                 ~args
                 (~'let [~'first-arg ~(first coerced-args)]
                        (~java-fn ~'first-arg ~@(rest coerced-args))
                        (~struct ~'first-arg))))
      struct-return
      `(~'defn ~clj-fn
               ~(fn-doc-str function)
               ~args
               (~(symbol (str "rstructs/get-" struct-return)) (~java-fn ~'memory/*current-arena* ~@coerced-args)))
      :else
      `(~'defn ~clj-fn
               ~(fn-doc-str function)
               ~args
               (~java-fn ~@coerced-args)))))

(defn pprint-fn [header-name out-file all-struct-names function]
  (let [function (get-fn header-name all-struct-names function)
        functions (map pprint (get-overrided-fn function))
        str-fns (apply str (interleave functions (repeat "\n\n")))]
    (spit out-file str-fns :append true)))

(defn generate-functions [header-name api]
  (let [out-file (str "src/clj/rayclj/" header-name "/functions.clj")
        template-file (str "dev/gen/" header-name "/functions.clj.template")
        functions (:functions api)
        all-struct-names (get-all-struct-names api)]
    (io/make-parents out-file)
    (spit out-file (slurp template-file))
    (dorun
     (map
      (partial pprint-fn header-name out-file all-struct-names)
      functions))
    api))

(defn enum-value-prefix [enum]
  (loop [value-names (map :name (:values enum))
         prefix ""]
    (let [firsts (map first value-names)]
      (if (apply = firsts)
        (recur (map rest value-names) (str prefix (first firsts)))
        prefix))))

(defn enum-key-value [prefix {:keys [name value description]}]
  (let [name (if (string/starts-with? name prefix) (subs name (count prefix)) name)
        name (c-name->clj-name name)]
    (str
     ":" name " " ;; keyword
     value
     (when description (str " ;; " description "\n"))))) ;; comment

(defn get-enum-str [{:keys [name description values] :as enum}]
  (let [name (symbol (c-name->clj-name name))
        prefix (enum-value-prefix enum)
        all-kvs (apply str (map (partial enum-key-value prefix) values))]
    (format "(def %s \"%s\"{%s})\n\n" name description all-kvs)))

(defn pprint-enum [enum]
  (let [print-options {:parse-string? true
                       :comment {:wrap? false}
                       :map {:comma? false :justify? true}}]
    (zp/zprint-str (get-enum-str enum) print-options)))

(defn generate-enums [header-name api]
  (let [out-file (str "src/clj/rayclj/" header-name "/enums.clj")
        template-file (str "dev/gen/" header-name "/enums.clj.template")
        enums (:enums api)
        clj-enums (map pprint-enum enums)
        str-enums (apply str (interleave clj-enums (repeat "\n\n")))]
    (io/make-parents out-file)
    (spit out-file (slurp template-file))
    (spit out-file str-enums :append true)
    api))

(defn get-define [{:keys [name description value]}]
  (let [name (symbol (c-name->clj-name name))]
    (if (empty? description)
      `(~'def ~name ~value)
      `(~'def ~name ~description ~value))))

(defn generate-defines [header-name api]
  (let [out-file (str "src/clj/rayclj/" header-name "/defines.clj")
        template-file (str "dev/gen/" header-name "/defines.clj.template")
        defines (:defines api)
        export-types #{"INT" "DOUBLE" "STRING"}
        defines (filter #(export-types (:type %)) defines)
        pprint-define #(pprint (get-define %))
        clj-defines (map pprint-define defines)
        str-defines (apply str (interleave clj-defines (repeat "\n\n")))]
    (io/make-parents out-file)
    (spit out-file (slurp template-file))
    (spit out-file str-defines :append true)
    api))

(defn generate-for-header [header-name]
  (let [api (slurp (str "native/raylib_linux_amd64/api/" header-name "_api.json"))
        api (json/read-value api json/keyword-keys-object-mapper)]
    (->> api
         (process-api)
         (generate-defines header-name)
         (generate-enums header-name)
         (generate-structs header-name)
         (generate-functions header-name))
    nil))

(defn generate-all []
  (generate-for-header "raylib")
  (generate-for-header "rlgl"))

(comment
  (generate-all)
  (generate-for-header "raylib")
  (generate-for-header "rlgl")

  (def raylib-api
    (-> "native/raylib_linux_amd64/api/raylib_api.json"
        (slurp)
        (json/read-value json/keyword-keys-object-mapper)))

  (def enums (:enums raylib-api))
  (def enums-by-name
    (into {} (map (juxt (comp keyword :name) identity)) enums))

  (enum-value-prefix (:FontType enums-by-name))

  (print (zp/zprint-str (get-enum-str (enums-by-name :FontType)) {:parse-string? true}))
  (generate-enums raylib-api)

  (def rlgl-api
    (-> "native/raylib_linux_amd64/api/rlgl_api.json"
        (slurp)
        (json/read-value json/keyword-keys-object-mapper)))

  (def defines (:defines rlgl-api))

  (def define (first (filter #(= "INT" (:type %)) defines)))
  (c-name->clj-name (:name define))
  (get-define define)
  (get-define {:name "FOO" :vale 1234 :description "foo bar baz"})

  (generate-defines "rlgl" rlgl-api)

  (def functions (:functions rlgl-api))

  (def functions-by-name
    (into {} (map (juxt (comp keyword :name) identity)) functions))

  (def init-window (:InitWindow functions-by-name))
  (get-fn all-struct-names init-window)
  (mapv (partial coerced-arg all-struct-names) (:params init-window))

  (generate-functions raylib-api)

  (def get-mouse-position (:GetMousePosition functions-by-name))
  (get-fn all-struct-names get-mouse-position)

  (generate-structs raylib-api)

  (def structs (:structs (process-api raylib-api)))
  (def structs-by-name
    (into {} (map (juxt (comp keyword :name) identity)) structs))

  (:Matrix structs-by-name)

  (def all-struct-names (get-all-struct-names raylib-api))

  (def vector2 (:Vector2 structs-by-name))
  (def render-texture (:RenderTexture structs-by-name))

  (print (pprint (struct-get-fn all-struct-names (assoc vector2 :as-vector? true))))
  (print (pprint (struct-get-fn all-struct-names (assoc vector2 :as-vector? false))))
  (print (pprint (struct-set-fn all-struct-names (assoc vector2 :as-vector? true))))
  (print (pprint (struct-set-fn all-struct-names (assoc vector2 :as-vector? false))))
  (print (pprint (struct-fn vector2)))
  (print (pprint (array-fn vector2)))
  (print (pprint (get-array-fn vector2)))
  (keyword (second (get-array-fn vector2)))

  (print (pprint (struct-get-fn all-struct-names (assoc render-texture :as-vector? true))))
  (print (pprint (struct-get-fn all-struct-names (assoc render-texture :as-vector? false))))
  (print (pprint (struct-set-fn all-struct-names (assoc render-texture :as-vector? true))))
  (print (pprint (struct-set-fn all-struct-names (assoc render-texture :as-vector? false))))
  (print (pprint (struct-fn render-texture)))
  (print (pprint (array-fn render-texture)))
  (print (pprint (get-array-fn render-texture)))
  (c-name->clj-name "LoadImage")
  (c-name->clj-name "Camera3D")
  (c-name->clj-name "Vector2")
  (c-name->clj-name "rlSetMatrixViewOffsetStereo"))
