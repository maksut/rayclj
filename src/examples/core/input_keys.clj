(ns examples.core.input-keys
  (:require [raylib.core :as rc]
            [raylib.text :as rt]
            [raylib.enums :as enums]
            [raylib.shapes :as rs]))

(let [screen-width 800
      screen-height 450
      vec-add (fn [v1 v2] (map + v1 v2))]

  (rc/init-window! screen-width screen-height "raylib [core] example - keyboard input")
  (rc/set-target-fps! 60) ;; Set our game to run at 60 frames-per-second

  (loop [ball-position [(/ screen-width 2) (/ screen-height 2)]]
    (when-not (rc/window-should-close?) ;; Detect window close button or ESC key
      ;; Update
      (let [new-position
            (cond-> ball-position
              (rc/is-key-down? ::enums/right) (vec-add [2 0])  ;; x += 2
              (rc/is-key-down? ::enums/left) (vec-add [-2 0])  ;; x -= 2
              (rc/is-key-down? ::enums/up) (vec-add [0 -2])    ;; y -= 2
              (rc/is-key-down? ::enums/down) (vec-add [0 2]))] ;; y += 2

        ;; Draw
        (rc/with-drawing!
          (rc/clear-background! ::enums/white)
          (rt/draw-text! "move the ball with arrow keys" 10 10 20 ::enums/darkgray)
          (rs/draw-circle-v! new-position 50 ::enums/maroon))

        ;; Loop
        (recur new-position))))

  (rc/close-window!)) ;; De-Initialization
