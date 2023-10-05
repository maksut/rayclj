(ns examples.core.basic-window
  (:require [raylib :as r]))

(let [message (r/string "Hello, World!")]
  (r/init-window! 800 450 (r/string "raylib [core] example - basic window"))
  (while (not (r/window-should-close?))
    (r/begin-drawing!)
    (r/clear-background! (r/color ::r/white))
    (r/draw-text! message 190 200 20 (r/color ::r/lightgray))
    (r/end-drawing!))
  (r/close-window!))
