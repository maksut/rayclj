(ns examples.textures.logo-raylib
  (:require [raylib.functions :as rl]))

(let [screen-width 800 screen-height 450]
  (rl/init-window screen-width screen-height "raylib [textures] example - texture loading and drawing")

  (let [texture (rl/load-texture "resources/examples/textures/raylib_logo.png") ;; Texture loading
        texture-x (- (/ screen-width 2) (/ (:width texture) 2))
        texture-y (- (/ screen-height 2) (/ (:height texture) 2))]

    ;; Main game loop
    (while (not (rl/window-should-close?)) ;; Detect window close button or ESC key
      ;; Update
      ;;----------------------------------------------------------------------------------
      ;; TODO: Update your game state here
      ;;----------------------------------------------------------------------------------

      ;; Draw
      (rl/with-drawing
        (rl/clear-background :raywhite)
        (rl/draw-texture texture texture-x texture-y :white)
        (rl/draw-text "this IS a texture" 360 370 10 :gray)))

    ;; De-Initialization
    (rl/unload-texture texture) ;; Texture unloading
    (rl/close-window))) ;; Close window and OpenGL context
