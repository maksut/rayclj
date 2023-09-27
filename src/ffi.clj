(ns ffi
  (:import [java.lang.foreign Arena MemorySegment Linker Linker$Option FunctionDescriptor ValueLayout ValueLayout$OfBoolean ValueLayout$OfInt MemoryLayout MemoryLayout$PathElement SymbolLookup StructLayout]
           [java.lang.invoke MethodHandle MethodHandles]
           [java.nio.file Path]
           [java.nio ByteOrder]))

(set! *warn-on-reflection* true)

;; System/loadLibrary is associated with a class loader
;; SymbolLookup/loaderLookup needs to happen together
;; So whenever we load a library we create a lookup instance and reset an atom.
;; Sigh...
(defonce ^:private loader-lookup (atom (SymbolLookup/loaderLookup)))

(defn load-lib! [name]
  (System/loadLibrary name)
  (reset! loader-lookup (SymbolLookup/loaderLookup)))

(defn- lookup [name]
  (let [loader-lookup ^SymbolLookup @loader-lookup
        optional (.find loader-lookup name)]
    (.orElse optional nil)))

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
(defn- memory-segment-set [^MemorySegment seg c-type ^long offset val]
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
(defn- memory-segment-get [^MemorySegment seg c-type ^long offset]
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

(defn- struct-layout [spec]
  (let [fields (->> spec (map field-layout) (assoc-offset))
        layouts (into-array MemoryLayout (map :mem-layout fields))
        ^MemoryLayout layout (MemoryLayout/structLayout layouts)]
    {:fields fields
     :mem-layout layout
     :byte-size (.byteSize layout)}))

(defn- set-field-seg! [m ^MemorySegment seg field]
  (let [c-type (second (:spec field))
        offset (:offset field)
        val ((:ident field) m)]
    (memory-segment-set seg c-type offset val)))

(defn- map->struct-seg [struct-layout m]
  (let [seg (auto-alloc (:mem-layout struct-layout))
        fields (:fields struct-layout)
        set-field (partial set-field-seg! m seg)]
    (doall (map set-field fields))
    seg))

(defn- field-seg->tuple [field-layout ^MemorySegment seg]
  (let [offset (:offset field-layout)
        c-type (second (:spec field-layout))
        val (memory-segment-get seg c-type offset)
        ident (:ident field-layout)]
    [ident val]))

(defn struct-seg->map [struct-layout ^MemorySegment seg]
  (->> (:fields struct-layout)
       (map #(field-seg->tuple % seg))
       (into {})))

(defn- arg-layout [arg-spec]
  (if (keyword? arg-spec)
    (primitives arg-spec)
    (-> arg-spec struct-layout :mem-layout)))

(defn- func-descr [{:keys [args return] :or {args [] return nil}}]
  (let [arg-layouts (map arg-layout args)
        arg-layouts (into-array MemoryLayout arg-layouts)]
    (if (nil? return)
      (FunctionDescriptor/ofVoid arg-layouts)
      (FunctionDescriptor/of (arg-layout return) arg-layouts))))

(def ^:private object-class (Class/forName "[Ljava.lang.Object;"))

(defn- arg->seg-fn [arg-spec]
  (case arg-spec
    :c-bool boolean
    :c-byte unchecked-byte
    :c-short unchecked-short
    :c-int unchecked-int
    :c-long unchecked-long
    :c-float unchecked-float
    :c-double unchecked-double
    :c-pointer auto-str       ;; TODO: fix this assumption; all pointers are strings!
    (fn [m] (map->struct-seg (struct-layout arg-spec) m)))) ;; default assumes structs 

(defn native-fn [{name :name :as spec}]
  (let [linker (Linker/nativeLinker)
        addr (lookup name)
        descr (func-descr spec)
        handle (.downcallHandle linker addr descr (into-array Linker$Option []))
        arg-specs (:args spec)
        handle (.asSpreader handle object-class (count arg-specs)) ; so we can call .invoke
        converters (map arg->seg-fn arg-specs) ;; fns that coerce primitives and convert map args into struct memory segments
        apply (fn [f x] (f x))]
    (fn [& args]
      (let [converted-args (map apply converters args)]
        (.invoke handle (object-array converted-args))))))
