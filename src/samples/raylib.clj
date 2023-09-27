(ns samples.raylib
  (:require [ffi :as f]))

(f/load-lib! "raylib")

(def ^:private init-window-invoke
  (f/invoke-fn {:name "InitWindow" :args [:c-int :c-int :c-pointer]}))

(defn init-window [width height title]
  (init-window-invoke (int width) (int height) (f/auto-str title)))

(def window-should-close?
  (f/invoke-fn {:name "WindowShouldClose" :args [] :return :c-bool}))

(def close-window
  (f/invoke-fn {:name "CloseWindow" :args []}))

(def begin-drawing
  (f/invoke-fn {:name "BeginDrawing" :args []}))

(def end-drawing
  (f/invoke-fn {:name "EndDrawing" :args []}))

(def color-spec
  [[:r :c-byte]
   [:g :c-byte]
   [:b :c-byte]
   [:a :c-byte]])

(def color-layout (f/struct-layout color-spec "Color"))

(def clear-backbround-invoke
  (f/invoke-fn {:name "ClearBackground" :args [color-spec]}))

(defn clear-backbround [color]
  (clear-backbround-invoke (f/map->struct-seg color-layout color)))

(def draw-text-invoke
  (f/invoke-fn {:name "DrawText" :args [:c-pointer :c-int :c-int :c-int color-spec]}))

(defn draw-text [text pos-x pos-y font-size color]
  (draw-text-invoke
   (f/auto-str text)
   (int pos-x)
   (int pos-y)
   (int font-size)
   (f/map->struct-seg color-layout color)))

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
