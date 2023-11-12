(ns examples.experiment.hexagon
  (:require [rayclj.raylib.functions :as rl]))

(let [screen-width 800
      screen-height 450
      message "move ball with mouse and click mouse button to change color"]

  (rl/init-window screen-width screen-height "raylib [core] example - mouse input")

  (rl/set-target-fps 60) ; Set our game to run at 60 frames-per-second

  (loop [hex-color :darkblue]
    (when-not (rl/window-should-close?) ; Detect window close button or ESC key
      ; Update
      (let [hex-position (rl/get-mouse-position)
            hex-color
            (cond
              (rl/mouse-button-pressed? :left) :maroon
              (rl/mouse-button-pressed? :middle) :lime
              (rl/mouse-button-pressed? :right) :darkblue
              (rl/mouse-button-pressed? :side) :purple
              (rl/mouse-button-pressed? :extra) :yellow
              (rl/mouse-button-pressed? :forward) :orange
              (rl/mouse-button-pressed? :back) :beige
              :else hex-color)]

        ; Draw
        (rl/begin-drawing)
        (rl/clear-background :white)
        ; (rl/draw-poly hex-position 6 40 45 hex-color) ; raylib 4.5.0
        (rl/draw-poly hex-position 6 40 0 hex-color) ; raylib git master
        (rl/draw-text message 10 10 20 :darkgray)
        (rl/end-drawing)

        ; Loop
        (recur hex-color))))

  (rl/close-window)) ; De-Initialization
