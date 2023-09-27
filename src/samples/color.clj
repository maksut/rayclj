(ns samples.color
  (:require [ffi :as f]))

(f/load-lib! "color")

(def color
  [[:r :c-byte]
   [:g :c-byte]
   [:b :c-byte]
   [:a :c-byte]])

(def test-invoke
  (f/native-fn {:name "test" :args [color]}))

(defn test-fn [color]
  (test-invoke color))

(test-fn {:r 245 :g 245 :b 245 :a 255})
