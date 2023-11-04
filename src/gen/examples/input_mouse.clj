(ns gen.examples.input-mouse
  (:require [gen.functions :as rl]))

(let [screen-width 800
      screen-height 450]

  (rl/init-window screen-width screen-height "raylib [core] example - mouse input")
  (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

  (loop [ball-color :darkblue]
    (when-not (rl/window-should-close?) ;; Detect window close button or ESC key
      ;; Update
      (let [ball-position (rl/get-mouse-position)
            ball-color
            (cond
              (rl/mouse-button-pressed? :left) :maroon
              (rl/mouse-button-pressed? :middle) :lime
              (rl/mouse-button-pressed? :right) :darkblue
              (rl/mouse-button-pressed? :side) :purple
              (rl/mouse-button-pressed? :extra) :yellow
              (rl/mouse-button-pressed? :forward) :orange
              (rl/mouse-button-pressed? :back) :beige
              :else ball-color)]

        ;; Draw
        (rl/begin-drawing)
        (rl/clear-background {:r 0 :g 0 :b 0 :a 255})
        (rl/clear-background :white)
        (rl/draw-circle-v ball-position 40 ball-color)
        (rl/draw-text "move ball with mouse and click mouse button to change color" 10 10 20 :darkgray)
        (rl/end-drawing)

        ;; Loop
        (recur ball-color))))

  (rl/close-window)) ;; De-Initialization
