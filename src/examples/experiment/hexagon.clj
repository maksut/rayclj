(ns examples.experiment.hexagon
  (:require [raylib :as r]))

(let [screen-width 800
      screen-height 450
      message (r/string "move ball with mouse and click mouse button to change color")]

  (r/init-window! screen-width screen-height (r/string "raylib [core] example - mouse input"))

  (r/set-target-fps! 60) ; Set our game to run at 60 frames-per-second

  (loop [hex-color ::r/darkblue]
    (when-not (r/window-should-close?) ; Detect window close button or ESC key
      ; Update
      (let [hex-position (r/get-mouse-position!)
            hex-color
            (cond
              (r/mouse-button-pressed? ::r/left) ::r/maroon
              (r/mouse-button-pressed? ::r/middle) ::r/lime
              (r/mouse-button-pressed? ::r/right) ::r/darkblue
              (r/mouse-button-pressed? ::r/side) ::r/purple
              (r/mouse-button-pressed? ::r/extra) ::r/yellow
              (r/mouse-button-pressed? ::r/forward) ::r/orange
              (r/mouse-button-pressed? ::r/back) ::r/beige
              :else hex-color)]

        ; Draw
        (r/begin-drawing!)
        (r/clear-background! (r/color ::r/white))
        (r/draw-poly! hex-position 6 40 45 (r/color hex-color))
        (r/draw-text! message 10 10 20 (r/color ::r/darkgray));
        (r/end-drawing!)

        ; Loop
        (recur hex-color))))

  (r/close-window!)) ; De-Initialization
