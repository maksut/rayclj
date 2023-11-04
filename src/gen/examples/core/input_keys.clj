(ns gen.examples.core.input-keys
  (:require [gen.functions :as rl]))

(let [screen-width 800
      screen-height 450
      vec-add (fn [v1 v2] (map + v1 v2))]

  (rl/init-window screen-width screen-height "raylib [core] example - keyboard input")
  (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

  (loop [ball-position [(/ screen-width 2) (/ screen-height 2)]]
    (when-not (rl/window-should-close?) ;; Detect window close button or ESC key
      ;; Update
      (let [new-position
            (cond-> ball-position
              (rl/key-down? :right) (vec-add [2 0])  ;; x += 2
              (rl/key-down? :left) (vec-add [-2 0])  ;; x -= 2
              (rl/key-down? :up) (vec-add [0 -2])    ;; y -= 2
              (rl/key-down? :down) (vec-add [0 2]))] ;; y += 2

        ;; Draw
        (rl/with-drawing
          (rl/clear-background :white)
          (rl/draw-text "move the ball with arrow keys" 10 10 20 :darkgray)
          (rl/draw-circle-v new-position 50 :maroon))

        ;; Loop
        (recur new-position))))

  (rl/close-window)) ;; De-Initialization
