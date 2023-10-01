(ns examples.core.basic-window
  (:require [raylib :as r]))

(do
  (r/init-window! 800 450 "raylib [core] example - basic window")
  (while (not (r/window-should-close?))
    (r/begin-drawing!)
    (r/clear-background!  ::r/white)
    (r/draw-text! "Hello, World!" 190 200 20 ::r/lightgray)
    (r/end-drawing!))
  (r/close-window!))
