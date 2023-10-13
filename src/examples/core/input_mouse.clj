(ns examples.core.input-mouse
  (:require [raylib.core :as rc]
            [raylib.text :as rt]
            [raylib.shapes :as rs]))

(let [screen-width 800
      screen-height 450]

  (rc/init-window! screen-width screen-height "raylib [core] example - mouse input")
  (rc/set-target-fps! 60) ;; Set our game to run at 60 frames-per-second

  (loop [ball-color :darkblue]
    (when-not (rc/window-should-close?) ;; Detect window close button or ESC key
      ;; Update
      (let [ball-position (rc/get-mouse-position!)
            ball-color
            (cond
              (rc/mouse-button-pressed? :left) :maroon
              (rc/mouse-button-pressed? :middle) :lime
              (rc/mouse-button-pressed? :right) :darkblue
              (rc/mouse-button-pressed? :side) :purple
              (rc/mouse-button-pressed? :extra) :yellow
              (rc/mouse-button-pressed? :forward) :orange
              (rc/mouse-button-pressed? :back) :beige
              :else ball-color)]

        ;; Draw
        (rc/with-drawing!
          (rc/clear-background! :white)
          (rs/draw-circle-v! ball-position 40 ball-color)
          (rt/draw-text! "move ball with mouse and click mouse button to change color" 10 10 20 :darkgray))

        ;; Loop
        (recur ball-color))))

  (rc/close-window!)) ;; De-Initialization
