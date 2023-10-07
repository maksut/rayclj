(ns examples.experiment.hexagon
  (:require [raylib.core :as rc]
            [raylib.text :as rt]
            [raylib.shapes :as rs]
            [raylib.enums :as enums]))

(let [screen-width 800
      screen-height 450
      message "move ball with mouse and click mouse button to change color"]

  (rc/init-window! screen-width screen-height "raylib [core] example - mouse input")

  (rc/set-target-fps! 60) ; Set our game to run at 60 frames-per-second

  (loop [hex-color ::enums/darkblue]
    (when-not (rc/window-should-close?) ; Detect window close button or ESC key
      ; Update
      (let [hex-position (rc/get-mouse-position!)
            hex-color
            (cond
              (rc/mouse-button-pressed? ::enums/left) ::enums/maroon
              (rc/mouse-button-pressed? ::enums/middle) ::enums/lime
              (rc/mouse-button-pressed? ::enums/right) ::enums/darkblue
              (rc/mouse-button-pressed? ::enums/side) ::enums/purple
              (rc/mouse-button-pressed? ::enums/extra) ::enums/yellow
              (rc/mouse-button-pressed? ::enums/forward) ::enums/orange
              (rc/mouse-button-pressed? ::enums/back) ::enums/beige
              :else hex-color)]

        ; Draw
        (rc/begin-drawing!)
        (rc/clear-background! ::enums/white)
        (rs/draw-poly! hex-position 6 40 45 hex-color)
        (rt/draw-text! message 10 10 20 ::enums/darkgray)
        (rc/end-drawing!)

        ; Loop
        (recur hex-color))))

  (rc/close-window!)) ; De-Initialization
