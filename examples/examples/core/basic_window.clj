(ns examples.core.basic-window
  (:require [rayclj.raylib.functions :as rl]))

(defn -main []
  (let [screen-width 800 screen-height 450]
    (rl/init-window screen-width screen-height "raylib [core] example - basic window")
    (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

    ;; Main game loop
    (while (not (rl/window-should-close?)) ;; Detect window close button or ESC key
      ;; Update
      ;;----------------------------------------------------------------------------------
      ;; TODO: Update your game state here
      ;;----------------------------------------------------------------------------------

      ;; Draw
      (rl/with-drawing
        (rl/clear-background :white)
        (rl/draw-text "Hello, World!" 190 200 20 :lightgray)))

    ;; De-Initialization
    (rl/close-window))) ;; Close window and OpenGL context
