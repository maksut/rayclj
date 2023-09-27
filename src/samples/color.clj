(ns samples.color
  (:require [ffi :as f]))

(f/load-lib! "color")

(def color-spec
  [[:r :c-byte]
   [:g :c-byte]
   [:b :c-byte]
   [:a :c-byte]])

(def color-layout (f/struct-layout color-spec "Color"))

(def test-invoke
  (f/invoke-fn {:name "test" :args [color-spec]}))

(defn test-fn [color]
  (test-invoke (f/map->struct-seg color-layout color)))

(test-fn {:r 245 :g 245 :b 245 :a 255})
