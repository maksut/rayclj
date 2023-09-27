(ns samples.raylib
  (:require [ffi :as f]))

(f/load-lib! "raylib")

(def init-window
  (f/native-fn {:name "InitWindow" :args [:c-int :c-int :c-pointer]}))

(def window-should-close?
  (f/native-fn {:name "WindowShouldClose" :args [] :return :c-bool}))

(def close-window
  (f/native-fn {:name "CloseWindow" :args []}))

(def begin-drawing
  (f/native-fn {:name "BeginDrawing" :args []}))

(def end-drawing
  (f/native-fn {:name "EndDrawing" :args []}))

(def color
  [[:r :c-byte]
   [:g :c-byte]
   [:b :c-byte]
   [:a :c-byte]])

(def clear-backbround
  (f/native-fn {:name "ClearBackground" :args [color]}))

(def draw-text
  (f/native-fn {:name "DrawText" :args [:c-pointer :c-int :c-int :c-int color]}))

;; raylib example1
(let [white {:r 245 :g 245 :b 245 :a 255}
      lightgray {:r 200 :g 200 :b 200 :a 255}]
  (init-window 800 450 "raylib [core] example - basic window")
  (while (not (window-should-close?))
    (begin-drawing)
    (clear-backbround white)
    (draw-text "Hello, World!" 190 200 20 lightgray)
    (end-drawing))
  (close-window))
