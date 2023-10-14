(ns examples.experiment.hexagon
  (:require [raylib.core :as rcore]
            [raylib.text :as rtext]
            [raylib.shapes :as rshapes]))

(let [screen-width 800
      screen-height 450
      message "move ball with mouse and click mouse button to change color"]

  (rcore/init-window! screen-width screen-height "raylib [core] example - mouse input")

  (rcore/set-target-fps! 60) ; Set our game to run at 60 frames-per-second

  (loop [hex-color :darkblue]
    (when-not (rcore/window-should-close?) ; Detect window close button or ESC key
      ; Update
      (let [hex-position (rcore/get-mouse-position!)
            hex-color
            (cond
              (rcore/mouse-button-pressed? :left) :maroon
              (rcore/mouse-button-pressed? :middle) :lime
              (rcore/mouse-button-pressed? :right) :darkblue
              (rcore/mouse-button-pressed? :side) :purple
              (rcore/mouse-button-pressed? :extra) :yellow
              (rcore/mouse-button-pressed? :forward) :orange
              (rcore/mouse-button-pressed? :back) :beige
              :else hex-color)]

        ; Draw
        (rcore/begin-drawing!)
        (rcore/clear-background! :white)
        ; (rshapes/draw-poly! hex-position 6 40 45 hex-color) ; raylib 4.5.0
        (rshapes/draw-poly! hex-position 6 40 0 hex-color) ; raylib git master
        (rtext/draw-text! message 10 10 20 :darkgray)
        (rcore/end-drawing!)

        ; Loop
        (recur hex-color))))

  (rcore/close-window!)) ; De-Initialization
