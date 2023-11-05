(ns gen.core
  (:require
   [clojure.string :as string]
   [jsonista.core :as json]
   [zprint.core :as zp]
   [gen.overrides :as overrides]))

(defn to-kebab-case [s]
  (when s
    (-> s
        (string/replace #"([a-z])([A-Z])" "$1-$2")
        (string/replace #"_" "-")
        (string/lower-case))))

(def memory-segment-symbol (symbol "^MemorySegment"))
(def arena-symbol (symbol "^Arena"))

(defn array? [name]
  (re-find #"\[[0-9]+\]" name))

(defn pointer? [name]
  (re-find #"([a-zA-Z0-9]*)\s*\*" name))

(defn c-string? [name]
  (= name "const char *"))

(defn field-getter [struct-name all-names {:keys [name type]}]
  (if-let [resolved-type (get all-names type)]
    (let [getter (symbol (str "raylib." struct-name "/" name "$slice"))
          struct-getter (symbol (str "get-" (to-kebab-case resolved-type)))]
      `(~struct-getter (~getter ~'seg)))
    (let [getter (symbol (str "raylib." struct-name "/" name "$get"))]
      `(~getter ~'seg))))

(defn field-setter [struct-name all-names {:keys [name type]}]
  (if-let [resolved-type (get all-names type)]
    (let [setter (symbol (str "raylib." struct-name "/" name "$slice"))
          struct-setter (symbol (str "set-" (to-kebab-case resolved-type)))]
      `(~struct-setter (~setter ~'seg) ~(symbol name)))
    (let [setter (str "raylib." struct-name "/" name "$set")]
      `(~(symbol setter) ~'seg ~(symbol name)))))

(defn field-getter-kv [struct-name all-names field]
  (list (keyword (:name field)) (field-getter struct-name all-names field)))

(defn doc-str [{:keys [description fields params docstring]}]
  (if docstring
    docstring
    (let [items (or fields params)
          field-doc (fn [{:keys [name type description]}]
                      (str ":EL  " type " " name (when description (str " // " description))))]
      (str description
           (apply str (map field-doc items))))))

(defn struct-get-fn [all-names {:keys [name fields as-vector?] :as struct}]
  (let [kebab-name (symbol (str "get-" (to-kebab-case name)))]
    `(~'defn ~kebab-name ~(doc-str struct)
             [~memory-segment-symbol ~'seg]
             ~(if as-vector?
                (into [] (map (partial field-getter name all-names) fields))
                (apply array-map (mapcat (partial field-getter-kv name all-names) fields))))))

(defn struct-set-fn [all-names {:keys [name fields as-vector?] :as struct}]
  (let [kebab-name (symbol (str "set-" (to-kebab-case name)))
        args (mapv (comp symbol :name) fields)
        args (if as-vector? args (array-map :keys args))]
    `(~'defn ~kebab-name ~(doc-str struct)
             [~memory-segment-symbol ~'seg ~args]
             ~@(map (partial field-setter name all-names) fields)
             ~'seg)))

(defn struct-fn [{:keys [name] :as struct}]
  (let [kebab-name (to-kebab-case name)
        fn-name (symbol kebab-name)
        struct-set-fn (symbol (str "set-" kebab-name))
        layout-sym (symbol (str "raylib." name "/$LAYOUT"))]
    `(~'defn ~fn-name ~(doc-str struct)
             ([~arena-symbol ~'arena ~'v]
              (~struct-set-fn (.allocate ~'arena (~layout-sym)) ~'v))
             ([~'v]
              (if (instance? ~'MemorySegment ~'v)
                ~'v
                (~fn-name rarena/*current-arena* ~'v))))))

(defn array-fn [{:keys [name]}]
  (let [kebab-name (to-kebab-case name)
        fn-name (symbol (str kebab-name "-array"))
        struct-set-fn (symbol (str "set-" kebab-name))
        layout-sym (symbol (str "raylib." name "/$LAYOUT"))]
    `(~'def ~fn-name (~'array-fn (~layout-sym) ~struct-set-fn))))

(defn get-array-fn [{:keys [name]}]
  (let [kebab-name (to-kebab-case name)
        fn-name (symbol (str "get-" kebab-name "-array"))
        struct-set-fn (symbol (str "get-" kebab-name))
        layout-sym (symbol (str "raylib." name "/$LAYOUT"))]
    `(~'def ~fn-name (~'get-array-fn (~layout-sym) ~struct-set-fn))))

(defn pprint [f]
  (-> (zp/zprint-str f)
      (string/replace #":EL" "\n")
      (string/replace #"\\n" "\n")
      (string/replace #":CARET" "^")))

(defn get-all-struct-names [raylib-api]
  (let [struct-names (map #(:name %) (:structs raylib-api))
        struct-names (apply hash-map (interleave struct-names struct-names))
        aliases (mapcat #(list (:name %) (:type %)) (:aliases raylib-api))
        aliases (apply hash-map aliases)]
    (merge aliases struct-names)))

(defn get-overrided [definition]
  (let [name (keyword (second definition))
        override (get overrides/definitions name)]
    (if override
      override
      [definition])))

(defn pprint-struct-fns [out-file all-struct-names struct]
  (let [get-fn (struct-get-fn all-struct-names struct)
        set-fn (struct-set-fn all-struct-names struct)
        struct-fn (struct-fn struct)
        array-fn (array-fn struct)
        get-array-fn (get-array-fn struct)
        str-fns [get-fn set-fn struct-fn array-fn get-array-fn]
        str-fns (mapcat get-overrided str-fns)
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

(defn process-api [{:keys [structs functions] :as raylib-api}]
  (let [blacklisted-structs
        #{"Material"
          "BoneInfo"
          "VrStereoConfig"
          "VrDeviceInfo"
          "ModelAnimation"
          "AutomationEvent"}
        structs (filter #(not (blacklisted-structs (:name %))) structs)
        structs (map add-as-vector structs)
        structs (map add-docstring structs)
        blacklisted-functions #{"LoadMaterialDefault" "LoadVrStereoConfig"}
        functions (filter #(not (blacklisted-functions (:name %))) functions)]
    (-> raylib-api
        (assoc :structs structs)
        (assoc :functions functions))))

(defn generate-structs [raylib-api]
  (let [out-file "src/gen/structs.clj"
        structs (:structs raylib-api)
        all-struct-names (get-all-struct-names raylib-api)]
    (spit out-file (slurp "src/gen/structs.clj.template"))
    (dorun
     (map
      (partial pprint-struct-fns out-file all-struct-names)
      structs))
    raylib-api))

(defn find-enum-map [function-name arg-name]
  (cond
    (and (re-find #"Key" function-name) (= arg-name "key")) (symbol "renums/keyboard-key")
    (and (re-find #"MouseButton" function-name) (= arg-name "button")) (symbol "renums/mouse-button")
    (and (re-find #"GamepadButton" function-name) (= arg-name "button")) (symbol "renums/gamepad-button"))
  ;; TODO: rest of the enums
  )

(defn coerced-arg [all-struct-names function-name needs-arena? {:keys [name type]}]
  (let [pointer-type (second (pointer? type))
        struct-name (get all-struct-names (or pointer-type type))
        enum-map (find-enum-map function-name name)
        name (symbol (to-kebab-case name))]
    (cond
      (c-string? type) (if needs-arena? `(~'string ~'arena ~name) `(~'string ~name))
      struct-name (let [struct-fn (symbol (str "rstructs/" (to-kebab-case struct-name)))]
                    (if needs-arena?
                      `(~struct-fn ~'arena ~name)
                      `(~struct-fn ~name)))
      enum-map `(if (keyword? ~name) (~enum-map ~name) ~name)
      :else name)))

(defn kebabize-fn-name [name return-type]
  (let [name (if (string/starts-with? name "Is") (subs name 2) name)
        name (if (= "bool" return-type) (str name "?") name)
        name (to-kebab-case name)]
    name))

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

(defn get-fn [all-struct-names {:keys [name params returnType] :as function}]
  (let [return-first-arg (first-arg-is-return name)
        java-fn (symbol (str "raylib_h/" name))
        clj-fn (symbol (kebabize-fn-name name returnType))
        args (mapv (comp symbol to-kebab-case :name) params)
        struct-return (to-kebab-case (get all-struct-names returnType))
        needs-arena (some (comp c-string? :type) params)
        coerced-args (mapv (partial coerced-arg all-struct-names name false) params)
        coerced-args-arena (mapv (partial coerced-arg all-struct-names name true) params)]
    (cond
      return-first-arg
      (let [type (:type (first params))
            first-type (second (pointer? type))
            struct-name (to-kebab-case (get all-struct-names first-type))
            struct (symbol (str "rstructs/get-" struct-name))]
        `(~'defn ~clj-fn
                 ~(doc-str function)
                 ~args
                 (~'let [~'first-arg ~(first coerced-args)]
                        (~java-fn ~'first-arg ~@(rest coerced-args))
                        (~struct ~'first-arg))))
      struct-return
      `(~'defn ~clj-fn
               ~(doc-str function)
               ([:CARETArena ~'arena ~@args]
                (~java-fn ~'arena ~@coerced-args-arena))
               (~args
                (~(symbol (str "rstructs/get-" struct-return)) (~java-fn ~'rarena/*current-arena* ~@coerced-args))))
      needs-arena
      `(~'defn ~clj-fn
               ~(doc-str function)
               ([:CARETArena ~'arena ~@args]
                (~java-fn ~@coerced-args-arena))
               (~args
                (~java-fn ~@coerced-args)))
      :else
      `(~'defn ~clj-fn
               ~(doc-str function)
               ~args
               (~java-fn ~@coerced-args)))))

(defn pprint-fn [out-file all-struct-names function]
  (let [function (get-fn all-struct-names function)
        functions (map pprint (get-overrided function))
        str-fns (apply str (interleave functions (repeat "\n\n")))]
    (spit out-file str-fns :append true)))

(defn generate-functions [raylib-api]
  (let [out-file "src/gen/functions.clj"
        functions (:functions raylib-api)
        all-struct-names (get-all-struct-names raylib-api)]
    (spit out-file (slurp "src/gen/functions.clj.template"))
    (dorun
     (map
      (partial pprint-fn out-file all-struct-names)
      functions))
    raylib-api))

(defn enum-value-prefix [enum]
  (loop [value-names (map :name (:values enum))
         prefix ""]
    (let [firsts (map first value-names)]
      (if (apply = firsts)
        (recur (map rest value-names) (str prefix (first firsts)))
        prefix))))

(defn enum-key-value [prefix {:keys [name value description]}]
  (let [name (if (string/starts-with? name prefix) (subs name (count prefix)) name)
        name (to-kebab-case name)]
    (str
     ":" name " " ;; keyword
     value
     (when description (str " ;; " description "\n"))))) ;; comment

(defn get-enum-str [{:keys [name description values] :as enum}]
  (let [name (symbol (to-kebab-case name))
        prefix (enum-value-prefix enum)
        all-kvs (apply str (map (partial enum-key-value prefix) values))]
    (format "(def %s \"%s\"{%s})\n\n" name description all-kvs)))

(defn pprint-enum [enum]
  (let [print-options {:parse-string? true
                       :comment {:wrap? false}
                       :map {:comma? false :justify? true}}]
    (zp/zprint-str (get-enum-str enum) print-options)))

(defn generate-enums [raylib-api]
  (let [out-file "src/gen/enums.clj"
        enums (:enums raylib-api)
        clj-enums (map pprint-enum enums)
        str-enums (apply str (interleave clj-enums (repeat "\n\n")))]
    (spit out-file (slurp "src/gen/enums.clj.template"))
    (spit out-file str-enums :append true)
    raylib-api))

(defn generate-all []
  (let [raylib-api (slurp "native/raylib_linux_amd64/api/raylib_api.json")
        raylib-api (json/read-value raylib-api json/keyword-keys-object-mapper)]
    (-> raylib-api
        (process-api)
        (generate-enums)
        (generate-structs)
        (generate-functions))
    nil))

(comment
  (generate-all)

  (defonce raylib-api-json
    (slurp "https://raw.githubusercontent.com/raysan5/raylib/4.5.0/parser/output/raylib_api.json"))

  (def raylib-api (json/read-value raylib-api-json json/keyword-keys-object-mapper))

  (def enums (:enums raylib-api))
  (def enums-by-name
    (into {} (map (juxt (comp keyword :name) identity)) enums))

  (enum-value-prefix (:FontType enums-by-name))

  (print (zp/zprint-str (get-enum-str (enums-by-name :FontType)) {:parse-string? true}))
  (generate-enums raylib-api)

  (def functions (:functions raylib-api))
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
  (to-kebab-case "LoadImage")
  (to-kebab-case "Camera3D")
  (to-kebab-case "Vector2")
  (to-kebab-case "rlSetMatrixViewOffsetStereo"))
