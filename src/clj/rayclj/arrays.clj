(ns rayclj.arrays
  "Utilities for arrays and array like structures"
  (:require [rayclj.arena :as rarena])
  (:import
   [java.lang.foreign Arena MemorySegment ValueLayout MemoryLayout]))

(defn get-string [^MemorySegment seg]
  (.getUtf8String seg 0))

(defn set-string [^MemorySegment seg val max-len]
  (when (> (count val) max-len)
    (throw (ex-info "String too long" {:val val :max-len max-len})))
  (.setUtf8String seg 0 val))

(defn string
  ([str] (.allocateUtf8String rarena/*current-arena* str))
  ([^Arena arena str] (.allocateUtf8String arena str)))

(defn get-float-array [^MemorySegment seg size]
  (let [layout (ValueLayout/JAVA_FLOAT)]
    (mapv (fn [i] (.getAtIndex seg layout (long i))) (range size))))

(defn set-float-array
  [^MemorySegment seg elems max-size]
  (when (> (count elems) max-size)
    (throw (ex-info "Float array too long" {:elems elems :max-size max-size})))
  (let [layout (ValueLayout/JAVA_FLOAT)]
    (dorun
     (map-indexed (fn [i elem] (.setAtIndex seg layout (long i) (float elem))) elems)))
  seg)

(defn get-char-array [^MemorySegment seg size]
  (let [layout (ValueLayout/JAVA_BYTE)]
    (apply
     str
     (mapv (fn [i] (char (.getAtIndex seg layout (long i)))) (range size)))))

(defn set-char-array
  [^MemorySegment seg elems max-size]
  (when (> (count elems) max-size)
    (throw (ex-info "Byte array too long" {:elems elems :max-size max-size})))
  (let [layout (ValueLayout/JAVA_BYTE)]
    (dorun
     (map-indexed
      (fn [i elem]
        (.setAtIndex seg layout (long i) (-> elem int unchecked-byte))) elems)))
  seg)

(defn get-int-array [^MemorySegment seg size]
  (let [layout (ValueLayout/JAVA_INT)]
    (mapv (fn [i] (.getAtIndex seg layout (long i))) (range size))))

(defn set-int-array
  [^MemorySegment seg elems max-size]
  (when (> (count elems) max-size)
    (throw (ex-info "Int array too long" {:elems elems :max-size max-size})))
  (let [layout (ValueLayout/JAVA_INT)]
    (dorun
     (map-indexed (fn [i elem] (.setAtIndex seg layout (long i) (int elem))) elems)))
  seg)

(def get-unsigned-int-array get-int-array)

(def set-unsigned-int-array set-int-array)

;;
;; Utilities for struct function definitions
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
      ([^Arena arena elems]
       (let [array-layout (MemoryLayout/sequenceLayout (count elems) elem-layout)
             seg (.allocate arena array-layout)]
         (set-array seg elems)))
      ([elems]
       (if (instance? MemorySegment elems)
         elems
         (to-array rarena/*current-arena* elems))))))

