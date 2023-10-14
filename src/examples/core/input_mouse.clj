(ns examples.core.input-mouse
  (:require [raylib.core :as rcore]
            [raylib.text :as rtext]
            [raylib.shapes :as rshapes]))

(let [screen-width 800
      screen-height 450]

  (rcore/init-window! screen-width screen-height "raylib [core] example - mouse input")
  (rcore/set-target-fps! 60) ;; Set our game to run at 60 frames-per-second

  (loop [ball-color :darkblue]
    (when-not (rcore/window-should-close?) ;; Detect window close button or ESC key
      ;; Update
      (let [ball-position (rcore/get-mouse-position!)
            ball-color
            (cond
              (rcore/mouse-button-pressed? :left) :maroon
              (rcore/mouse-button-pressed? :middle) :lime
              (rcore/mouse-button-pressed? :right) :darkblue
              (rcore/mouse-button-pressed? :side) :purple
              (rcore/mouse-button-pressed? :extra) :yellow
              (rcore/mouse-button-pressed? :forward) :orange
              (rcore/mouse-button-pressed? :back) :beige
              :else ball-color)]

        ;; Draw
        (rcore/with-drawing!
          (rcore/clear-background! :white)
          (rshapes/draw-circle-v! ball-position 40 ball-color)
          (rtext/draw-text! "move ball with mouse and click mouse button to change color" 10 10 20 :darkgray))

        ;; Loop
        (recur ball-color))))

  (rcore/close-window!)) ;; De-Initialization
