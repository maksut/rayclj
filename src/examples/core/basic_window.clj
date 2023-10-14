(ns examples.core.basic-window
  (:require [raylib.core :as rcore]
            [raylib.text :as rtext]))

(let [screen-width 800 screen-height 450]
  (rcore/init-window! screen-width screen-height "raylib [core] example - basic window")
  (rcore/set-target-fps! 60) ;; Set our game to run at 60 frames-per-second

  ;; Main game loop
  (while (not (rcore/window-should-close?)) ;; Detect window close button or ESC key
    ;; Update
    ;;----------------------------------------------------------------------------------
    ;; TODO: Update your game state here
    ;;----------------------------------------------------------------------------------

    ;; Draw
    (rcore/with-drawing!
      (rcore/clear-background! :white)
      (rtext/draw-text! "Hello, World!" 190 200 20 :lightgray)))

  ;; De-Initialization
  (rcore/close-window!)) ;; Close window and OpenGL context
