(ns examples.core.input-keys
  (:require [raylib.core :as rcore]
            [raylib.text :as rtext]
            [raylib.shapes :as rshapes]))

(let [screen-width 800
      screen-height 450
      vec-add (fn [v1 v2] (map + v1 v2))]

  (rcore/init-window! screen-width screen-height "raylib [core] example - keyboard input")
  (rcore/set-target-fps! 60) ;; Set our game to run at 60 frames-per-second

  (loop [ball-position [(/ screen-width 2) (/ screen-height 2)]]
    (when-not (rcore/window-should-close?) ;; Detect window close button or ESC key
      ;; Update
      (let [new-position
            (cond-> ball-position
              (rcore/is-key-down? :right) (vec-add [2 0])  ;; x += 2
              (rcore/is-key-down? :left) (vec-add [-2 0])  ;; x -= 2
              (rcore/is-key-down? :up) (vec-add [0 -2])    ;; y -= 2
              (rcore/is-key-down? :down) (vec-add [0 2]))] ;; y += 2

        ;; Draw
        (rcore/with-drawing!
          (rcore/clear-background! :white)
          (rtext/draw-text! "move the ball with arrow keys" 10 10 20 :darkgray)
          (rshapes/draw-circle-v! new-position 50 :maroon))

        ;; Loop
        (recur new-position))))

  (rcore/close-window!)) ;; De-Initialization
