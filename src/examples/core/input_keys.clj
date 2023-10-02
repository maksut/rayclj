(ns examples.core.input-keys
  (:require [raylib :as r]))

(let [screen-width 800
      screen-height 450
      vec-add (fn [v1 v2] (map + v1 v2))]
  (r/init-window! screen-width screen-height "raylib [core] example - keyboard input")

  (r/set-target-fps! 60) ; Set our game to run at 60 frames-per-second

  (loop [ball-position [(/ screen-width 2) (/ screen-height 2)]]
    ; Update
    (let [new-position
          (cond-> ball-position
            (r/is-key-down? ::r/right) (vec-add [2 0])  ;; x += 2
            (r/is-key-down? ::r/left) (vec-add [-2 0])  ;; x -= 2
            (r/is-key-down? ::r/up) (vec-add [0 -2])    ;; y -= 2
            (r/is-key-down? ::r/down) (vec-add [0 2]))] ;; y += 2

      ; Draw
      (r/begin-drawing!)
      (r/clear-background! ::r/white)
      (r/draw-text! "move the ball with arrow keys" 10 10 20 ::r/darkgray)
      (r/draw-circle-v! new-position 50 ::r/maroon)
      (r/end-drawing!)

      ; Loop
      (if (r/window-should-close?)  ; Detect window close button or ESC key
        (r/close-window!)           ; De-Initialization
        (recur new-position)))))    ; Otherwise loop
