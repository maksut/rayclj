(ns examples.core.input-mouse
  (:require [raylib.core :as rc]
            [raylib.enums :as enums]
            [raylib.shapes :as rs]))

(let [screen-width 800
      screen-height 450]

  (rc/init-window! screen-width screen-height "raylib [core] example - mouse input")
  (rc/set-target-fps! 60) ;; Set our game to run at 60 frames-per-second

  (loop [ball-color ::enums/darkblue]
    (when-not (rc/window-should-close?) ;; Detect window close button or ESC key
      ;; Update
      (let [ball-position (rc/get-mouse-position!)
            ball-color
            (cond
              (rc/mouse-button-pressed? ::enums/left) ::enums/maroon
              (rc/mouse-button-pressed? ::enums/middle) ::enums/lime
              (rc/mouse-button-pressed? ::enums/right) ::enums/darkblue
              (rc/mouse-button-pressed? ::enums/side) ::enums/purple
              (rc/mouse-button-pressed? ::enums/extra) ::enums/yellow
              (rc/mouse-button-pressed? ::enums/forward) ::enums/orange
              (rc/mouse-button-pressed? ::enums/back) ::enums/beige
              :else ball-color)]

        ;; Draw
        (rc/with-drawing!
          (rc/clear-background! ::enums/white)
          (rs/draw-circle-v! ball-position 40 ball-color)
          (rc/draw-text! "move ball with mouse and click mouse button to change color" 10 10 20 ::enums/darkgray))

        ;; Loop
        (recur ball-color))))

  (rc/close-window!)) ;; De-Initialization
