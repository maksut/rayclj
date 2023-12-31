(ns rayclj.memory
  "Utilities for memory management and arrays"
  (:refer-clojure :exclude [int float double float-array byte-array int-array char-array])
  (:import
   [java.lang.foreign Arena MemorySegment ValueLayout ValueLayout$OfInt ValueLayout$OfFloat ValueLayout$OfDouble MemoryLayout]))

(set! *warn-on-reflection* true)

(defn auto-arena ^Arena [] (Arena/ofAuto))

(defn global-arena ^Arena [] (Arena/global))

(defn confined-arena ^Arena [] (Arena/ofConfined))

(def ^Arena ^:dynamic *current-arena* (auto-arena))

(defmacro with-confined-arena [& body]
  `(with-open [arena# (confined-arena)]
     (binding [memory/*current-arena* arena#]
       ~@body)))

(def null MemorySegment/NULL)

(defn allocate
  ([^MemoryLayout layout]
   (.allocate *current-arena* layout))
  ([^Arena arena ^MemoryLayout layout]
   (.allocate arena layout)))

(defn allocate-array
  ([^MemoryLayout layout ^long size]
   (.allocateArray *current-arena* layout size))
  ([^Arena arena ^MemoryLayout layout ^long size]
   (.allocateArray arena layout size)))

(defn get-string [^MemorySegment seg]
  (.getUtf8String seg 0))

(defn set-string [^MemorySegment seg val max-len]
  (when (> (count val) max-len)
    (throw (ex-info "String too long" {:val val :max-len max-len})))
  (.setUtf8String seg 0 val))

(defn string
  [str]
  (if (instance? MemorySegment str)
    str
    (.allocateUtf8String *current-arena* str)))

(defn get-int
  [^MemorySegment seg]
  (.get seg (ValueLayout/JAVA_INT) 0))

(defn int
  [val]
  (let [^ValueLayout$OfInt layout (ValueLayout/JAVA_INT)
        ^MemorySegment seg (allocate layout)]
    (.set seg layout 0 ^int val)
    seg))

(defn get-float
  [^MemorySegment seg]
  (.get seg (ValueLayout/JAVA_FLOAT) 0))

(defn float
  [val]
  (let [^ValueLayout$OfFloat layout (ValueLayout/JAVA_FLOAT)
        ^MemorySegment seg (allocate layout)]
    (.set seg layout 0 ^float val)
    seg))

(defn get-double
  [^MemorySegment seg]
  (.get seg (ValueLayout/JAVA_DOUBLE) 0))

(defn double
  [val]
  (let [^ValueLayout$OfDouble layout (ValueLayout/JAVA_DOUBLE)
        ^MemorySegment seg (allocate layout)]
    (.set seg layout 0 ^double val)
    seg))

;;
;; Array functions for arrays of primitives
;;

(defn primitive-array-fn [^MemoryLayout layout set-array-fn]
  (fn to-array
    ([elems ^long max-size]
     (let [seg (allocate-array layout max-size)]
       (set-array-fn seg elems max-size)
       seg))
    ([elems]
     (if (instance? MemorySegment elems)
       elems
       (to-array elems (count elems))))))

(defn get-float-array [^MemorySegment seg size]
  (let [layout (ValueLayout/JAVA_FLOAT)]
    (mapv (fn [i] (.getAtIndex seg layout (long i))) (range size))))

(defn set-float-array
  [^MemorySegment seg elems max-size]
  (when (> (count elems) max-size)
    (throw (ex-info "Float array too long" {:elems elems :max-size max-size})))
  (let [layout (ValueLayout/JAVA_FLOAT)]
    (dorun
     (map-indexed (fn [i elem] (.setAtIndex seg layout (long i) ^float elem)) elems)))
  seg)

(def float-array (primitive-array-fn ValueLayout/JAVA_FLOAT set-float-array))

(defn get-byte-array [^MemorySegment seg size]
  (let [layout (ValueLayout/JAVA_BYTE)]
    (mapv (fn [i] (.getAtIndex seg layout (long i))) (range size))))

(defn set-byte-array
  [^MemorySegment seg elems max-size]
  (when (> (count elems) max-size)
    (throw (ex-info "Byte array too long" {:elems elems :max-size max-size})))
  (let [layout (ValueLayout/JAVA_BYTE)]
    (dorun
     (map-indexed
      (fn [i elem]
        (.setAtIndex seg layout (long i) (-> elem int unchecked-byte))) elems)))
  seg)

(def byte-array (primitive-array-fn ValueLayout/JAVA_BYTE set-byte-array))

(defn get-char-array [^MemorySegment seg size]
  (->> (get-byte-array seg size)
       (take-while #(not= 0 %)) ;; zero terminated string
       (map char)
       (apply str)))

(defn set-char-array [^MemorySegment seg elems max-size]
  (let [pad-size (- max-size (count elems))
        padded-elems (concat elems (take pad-size (repeat 0)))]
    (set-byte-array seg padded-elems max-size)))

(def char-array (primitive-array-fn ValueLayout/JAVA_BYTE set-char-array))

(defn get-int-array [^MemorySegment seg size]
  (let [layout (ValueLayout/JAVA_INT)]
    (mapv (fn [i] (.getAtIndex seg layout (long i))) (range size))))

(defn set-int-array
  [^MemorySegment seg elems max-size]
  (when (> (count elems) max-size)
    (throw (ex-info "Int array too long" {:elems elems :max-size max-size})))
  (let [layout (ValueLayout/JAVA_INT)]
    (dorun
     (map-indexed (fn [i elem] (.setAtIndex seg layout (long i) ^int elem)) elems)))
  seg)

(def int-array (primitive-array-fn ValueLayout/JAVA_INT set-int-array))

(def get-unsigned-int-array get-int-array)
(def set-unsigned-int-array set-int-array)
(def unsigned-int-array int-array)

;;
;; Utility functions for arrays of structs
;;

(defn as-slice
  [^MemorySegment seg index element-size]
  (.asSlice seg (* index element-size)))

(defn get-array-fn
  [^MemoryLayout elem-layout get-fn]
  (let [element-size (.byteSize elem-layout)]
    (fn [^MemorySegment seg size]
      (mapv (fn [i] (get-fn (as-slice seg i element-size))) (range size)))))

(defn set-array-fn
  [^MemoryLayout elem-layout set-fn]
  (let [element-size (.byteSize elem-layout)]
    (fn set-array
      ([^MemorySegment seg elems max-size]
       (when (> (count elems) max-size) (throw (ex-info "Array too long" {:elems elems :max-size max-size})))
       (set-array seg elems))
      ([^MemorySegment seg elems]
       (dorun
        (map-indexed (fn [i elem] (set-fn (as-slice seg i element-size) elem)) elems))
       seg))))

(defn array-fn
  [^MemoryLayout elem-layout set-fn]
  (let [set-array (set-array-fn elem-layout set-fn)]
    (fn to-array
      ([elems max-size]
       (if (instance? MemorySegment elems)
         elems
         (let [seg (allocate-array elem-layout max-size)]
           (set-array seg elems max-size))))
      ([elems]
       (if (instance? MemorySegment elems)
         elems
         (let [max-size (count elems)
               seg (allocate-array elem-layout max-size)]
           (set-array seg elems max-size)))))))
