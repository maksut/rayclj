(ns ffi
  (:import [java.lang.foreign Arena MemorySegment Linker Linker$Option FunctionDescriptor ValueLayout ValueLayout$OfBoolean ValueLayout$OfInt MemoryLayout MemoryLayout$PathElement SymbolLookup StructLayout]
           [java.lang.invoke MethodHandle MethodHandles]
           [java.nio.file Path]
           [java.nio ByteOrder]))

(set! *warn-on-reflection* true)

(defonce loader-lookup (atom (SymbolLookup/loaderLookup)))

;; System/loadLibrary is associated with a class loader
;; SymbolLookup/loaderLookup needs to happen together
;; So whenever we load a library we create a lookup instance and reset an atom.
;; Sigh...
(defn load-lib! [name]
  (System/loadLibrary name)
  (reset! loader-lookup (SymbolLookup/loaderLookup)))

(defn lookup [name]
  (let [loader-lookup ^SymbolLookup @loader-lookup]
    (->> name
         (.find loader-lookup)
         (.get))))

(def ^:private primitives
  {:c-bool ValueLayout/JAVA_BOOLEAN
   :c-byte ValueLayout/JAVA_BYTE
   :c-short ValueLayout/JAVA_SHORT
   :c-int ValueLayout/JAVA_INT
   :c-long ValueLayout/JAVA_LONG
   :c-float ValueLayout/JAVA_FLOAT
   :c-double ValueLayout/JAVA_DOUBLE
   :c-pointer ValueLayout/ADDRESS})

; to avoid reflection
(defn memory-segment-set [^MemorySegment seg c-type ^long offset val]
  (case c-type
    :c-bool (.set seg ValueLayout/JAVA_BOOLEAN offset ^boolean val)
    :c-byte (.set seg ValueLayout/JAVA_BYTE offset (unchecked-byte val))
    :c-short (.set seg ValueLayout/JAVA_SHORT offset ^short val)
    :c-int (.set seg ValueLayout/JAVA_INT offset ^int val)
    :c-long (.set seg ValueLayout/JAVA_LONG offset ^long val)
    :c-float (.set seg ValueLayout/JAVA_FLOAT offset ^float val)
    :c-double (.set seg ValueLayout/JAVA_DOUBLE offset ^double val)
    :c-pointer (.set seg ValueLayout/ADDRESS offset ^MemorySegment val)))

; to avoid reflection
(defn memory-segment-get [^MemorySegment seg c-type ^long offset]
  (case c-type
    :c-bool (.get seg ValueLayout/JAVA_BOOLEAN offset)
    :c-byte (.get seg ValueLayout/JAVA_BYTE offset)
    :c-short (.get seg ValueLayout/JAVA_SHORT offset)
    :c-int (.get seg ValueLayout/JAVA_INT offset)
    :c-long (.get seg ValueLayout/JAVA_LONG offset)
    :c-float (.get seg ValueLayout/JAVA_FLOAT offset)
    :c-double (.get seg ValueLayout/JAVA_DOUBLE offset)
    :c-pointer (.get seg ValueLayout/ADDRESS offset)))

(defn- auto-str ^MemorySegment [str]
  (.allocateUtf8String (Arena/ofAuto) str))

(defn- auto-alloc ^MemoryLayout [^MemoryLayout layout]
  (.allocate (Arena/ofAuto) layout))

(defn- field-layout [field-spec]
  (let [ident (first field-spec)
        c-type (second field-spec)
        ^MemoryLayout layout (primitives c-type)
        layout (.withName layout (name ident))]
    {:spec field-spec
     :ident ident
     :mem-layout layout
     :byte-size (.byteSize layout)}))

(defn- assoc-offset [field-specs]
  (loop [previous-offset 0
         [x & xs] field-specs
         result []]
    (if (nil? x)
      result
      (let [offset (+ previous-offset (long (:byte-size x)))]
        (recur offset xs (conj result (assoc x :offset previous-offset)))))))

(defn- struct-layout
  ([spec name]
   (let [layout (struct-layout spec)
         ^MemoryLayout mem-layout (:mem-layout layout)
         mem-layout (.withName mem-layout name)]
     (assoc layout :mem-layout mem-layout)))
  ([spec]
   (let [fields (->> spec (map field-layout) (assoc-offset))
         layouts (into-array MemoryLayout (map :mem-layout fields))
         ^MemoryLayout layout (MemoryLayout/structLayout layouts)]
     {:fields fields
      :mem-layout layout
      :byte-size (.byteSize layout)})))

(defn map->struct [struct-layout m]
  (loop [seg (auto-alloc (:mem-layout struct-layout))
         [f & fs] (:fields struct-layout)]
    (if (nil? f)
      seg
      (let [c-type (second (:spec f))
            offset (:offset f)
            val ((:ident f) m)]
        (memory-segment-set seg c-type offset val)
        (recur seg fs)))))

(defn- field->tuple [field-layout ^MemorySegment seg]
  (let [offset (:offset field-layout)
        c-type (second (:spec field-layout))
        val (memory-segment-get seg c-type offset)
        ident (:ident field-layout)]
    [ident val]))

(defn struct->map [struct-layout ^MemorySegment seg]
  (->> (:fields struct-layout)
       (map #(field->tuple % seg))
       (into {})))

(defn layout [prim-or-struct-spec]
  (if (keyword? prim-or-struct-spec)
    (primitives prim-or-struct-spec)
    (-> prim-or-struct-spec struct-layout :mem-layout)))

(defn- descr [{:keys [args return] :or {args [] return nil}}]
  (let [arg-layouts (map layout args)
        arg-layouts (into-array MemoryLayout arg-layouts)]
    (if (nil? return)
      (FunctionDescriptor/ofVoid arg-layouts)
      (FunctionDescriptor/of (layout return) arg-layouts))))

(def ^:private object-class (Class/forName "[Ljava.lang.Object;"))

(defn- invoke-fn [{name :name :as spec}]
  (let [linker (Linker/nativeLinker)
        addr (lookup name)
        descr (descr spec)
        handle (.downcallHandle linker addr descr (into-array Linker$Option []))
        arg-size (count (:args spec))
        handle (.asSpreader handle object-class arg-size)]
    (fn [& args] (.invoke handle (object-array args)))))

;; usage
(load-lib! "raylib")

(def ^:private init-window-invoke
  (invoke-fn {:name "InitWindow" :args [:c-int :c-int :c-pointer]}))

(defn init-window [width height title]
  (init-window-invoke (int width) (int height) (auto-str title)))

(def window-should-close?
  (invoke-fn {:name "WindowShouldClose" :args [] :return :c-bool}))

(def close-window
  (invoke-fn {:name "CloseWindow" :args []}))

(def begin-drawing
  (invoke-fn {:name "BeginDrawing" :args []}))

(def end-drawing
  (invoke-fn {:name "EndDrawing" :args []}))

(def color-spec
  [[:r :c-byte]
   [:g :c-byte]
   [:b :c-byte]
   [:a :c-byte]])

(def color-layout (struct-layout color-spec "Color"))

(def clear-backbround-invoke
  (invoke-fn {:name "ClearBackground" :args [color-spec]}))

(descr {:name "ClearBackground" :args [color-spec]})

(defn clear-backbround [color]
  (clear-backbround-invoke (map->struct color-layout color)))

(def draw-text-invoke
  (invoke-fn {:name "DrawText" :args [:c-pointer :c-int :c-int :c-int color-spec]}))

(defn draw-text [text pos-x pos-y font-size color]
  (draw-text-invoke
   (auto-str text)
   (int pos-x)
   (int pos-y)
   (int font-size)
   (map->struct color-layout color)))

(->> color-spec
     (map field-layout)
     (assoc-offset))

(load-lib! "color")

; (def test-invoke
;   (invoke-fn {:name "test" :args [color-spec]}))
;
; (defn test [color]
;   (test-invoke (map->struct color-layout color)))
;
; (test {:r 245 :g 245 :b 245 :a 255})
;
; (defn print-struct [struct]
;   (let [buffer (.asByteBuffer struct)
;         to-print [(.get buffer) (.get buffer) (.get buffer) (.get buffer)]]
;     (println to-print)
;     to-print))

; (let [white (map->struct color-layout {:r 245 :g 245 :b 245 :a 255})
;       lightgray (map->struct color-layout {:r 200 :g 200 :b 200 :a 255})]
;   {:white white
;    :bytes (print-struct white)
;    :layout (struct->map color-layout white)})

; (let [white {:r 245 :g 245 :b 245 :a 255}
;       lightgray {:r 200 :g 200 :b 200 :a 255}]
;   (init-window 800 450 "raylib [core] example - basic window")
;   (while (not (window-should-close?))
;     (begin-drawing)
;     (clear-backbround white)
;     (draw-text "Hello, World!" 190 200 20 lightgray)
;     (end-drawing))
;   (close-window))
;
