(ns examples.core.basic-window
  (:require [raylib.core :as rc]
            [raylib.text :as rt]))

(let [screen-width 800 screen-height 450]
  (rc/init-window! screen-width screen-height "raylib [core] example - basic window")
  (rc/set-target-fps! 60) ;; Set our game to run at 60 frames-per-second

  ;; Main game loop
  (while (not (rc/window-should-close?)) ;; Detect window close button or ESC key
    ;; Update
    ;;----------------------------------------------------------------------------------
    ;; TODO: Update your game state here
    ;;----------------------------------------------------------------------------------

    ;; Draw
    (rc/with-drawing!
      (rc/clear-background! :white)
      (rt/draw-text! "Hello, World!" 190 200 20 :lightgray)))

  ;; De-Initialization
  (rc/close-window!)) ;; Close window and OpenGL context
