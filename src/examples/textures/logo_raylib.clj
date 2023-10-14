(ns examples.textures.logo-raylib
  (:require [raylib.core :as rcore]
            [raylib.text :as rtext]
            [raylib.textures :as rtextures]))

(let [screen-width 800 screen-height 450]
  (rcore/init-window! screen-width screen-height "raylib [textures] example - texture loading and drawing")

  (let [texture (rtextures/load-texture! "src/examples/textures/resources/raylib_logo.png") ;; Texture loading
        texture-x (- (/ screen-width 2) (/ (:width texture) 2))
        texture-y (- (/ screen-height 2) (/ (:height texture) 2))]

    ;; Main game loop
    (while (not (rcore/window-should-close?)) ;; Detect window close button or ESC key
      ;; Update
      ;;----------------------------------------------------------------------------------
      ;; TODO: Update your game state here
      ;;----------------------------------------------------------------------------------

      ;; Draw
      (rcore/with-drawing!
        (rcore/clear-background! :raywhite)
        (rtextures/draw-texture! texture texture-x texture-y :white)
        (rtext/draw-text! "this IS a texture!" 360 370 10 :gray)))

    ;; De-Initialization
    (rtextures/unload-texture! texture) ;; Texture unloading
    (rcore/close-window!))) ;; Close window and OpenGL context
