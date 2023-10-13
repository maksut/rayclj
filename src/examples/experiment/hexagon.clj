(ns examples.experiment.hexagon
  (:require [raylib.core :as rc]
            [raylib.text :as rt]
            [raylib.shapes :as rs]))

(let [screen-width 800
      screen-height 450
      message "move ball with mouse and click mouse button to change color"]

  (rc/init-window! screen-width screen-height "raylib [core] example - mouse input")

  (rc/set-target-fps! 60) ; Set our game to run at 60 frames-per-second

  (loop [hex-color :darkblue]
    (when-not (rc/window-should-close?) ; Detect window close button or ESC key
      ; Update
      (let [hex-position (rc/get-mouse-position!)
            hex-color
            (cond
              (rc/mouse-button-pressed? :left) :maroon
              (rc/mouse-button-pressed? :middle) :lime
              (rc/mouse-button-pressed? :right) :darkblue
              (rc/mouse-button-pressed? :side) :purple
              (rc/mouse-button-pressed? :extra) :yellow
              (rc/mouse-button-pressed? :forward) :orange
              (rc/mouse-button-pressed? :back) :beige
              :else hex-color)]

        ; Draw
        (rc/begin-drawing!)
        (rc/clear-background! :white)
        (rs/draw-poly! hex-position 6 40 45 hex-color)
        (rt/draw-text! message 10 10 20 :darkgray)
        (rc/end-drawing!)

        ; Loop
        (recur hex-color))))

  (rc/close-window!)) ; De-Initialization
