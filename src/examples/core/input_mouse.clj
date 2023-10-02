(ns examples.core.input-mouse
  (:require [raylib :as r]))

(let [screen-width 800
      screen-height 450]
  (r/init-window! screen-width screen-height "raylib [core] example - mouse input")

  (r/set-target-fps! 60) ; Set our game to run at 60 frames-per-second

  (loop [ball-color ::r/darkblue]
    ; Update
    (let [ball-position (r/get-mouse-position!)
          ball-color
          (cond
            (r/mouse-button-pressed? ::r/left) ::r/maroon
            (r/mouse-button-pressed? ::r/middle) ::r/lime
            (r/mouse-button-pressed? ::r/right) ::r/darkblue
            (r/mouse-button-pressed? ::r/side) ::r/purple
            (r/mouse-button-pressed? ::r/extra) ::r/yellow
            (r/mouse-button-pressed? ::r/forward) ::r/orange
            (r/mouse-button-pressed? ::r/back) ::r/beige
            :else ball-color)]

      ; Draw
      (r/begin-drawing!)
      (r/clear-background! ::r/white)
      (r/draw-circle-v! ball-position 40 ball-color)
      (r/draw-text! "move ball with mouse and click mouse button to change color" 10 10 20 ::r/darkgray);
      (r/end-drawing!)

      ; Loop
      (if (r/window-should-close?)  ; Detect window close button or ESC key
        (r/close-window!)           ; De-Initialization
        (recur ball-color)))))      ; Otherwise loop
