(ns examples.textures.srcrec_dstrec
  (:require [raylib.core :as rcore]
            [raylib.text :as rtext]
            [raylib.textures :as rtextures]
            [raylib.shapes :as rshapes]))

(let [screen-width 800 screen-height 450]
  (rcore/init-window! screen-width screen-height "raylib [textures] examples - texture source and destination rectangles")

  (rcore/set-target-fps! 60)

  ; NOTE: Textures MUST be loaded after Window initialization (OpenGL context is required)
  (let [scarfy (rtextures/load-texture! "src/examples/textures/resources/scarfy.png") ;; Texture loading
        frame-width (/ (:width scarfy) 6)
        frame-height (:height scarfy)

        ; Source rectangle (part of the texture to use for drawing)
        source-rec {:x 0.0 :y 0.0 :width frame-width :height frame-height}

        ; Destination rectangle (screen rectangle where drawing part of texture)
        dest-rec {:x (/ screen-width 2.0)
                  :y (/ screen-height 2.0)
                  :width (* frame-width 2.0)
                  :height (* frame-height 2.0)}

        ; Origin of the texture (rotation/scale point), it's relative to destination rectangle size
        origin [frame-width frame-height]]

    (loop [rotation 0.0]
      ;; Update
      (let [new-rotation (inc rotation)]

        ;; Draw
        (rcore/with-drawing!
          (rcore/clear-background! :raywhite)

          ;; NOTE: Using DrawTexturePro() we can easily rotate and scale the part of the texture we draw
          ;; sourceRec defines the part of the texture we use for drawing
          ;; destRec defines the rectangle where our texture part will fit (scaling it to fit)
          ;; origin defines the point of the texture used as reference for rotation and scaling
          ;; rotation defines the texture rotation (using origin as rotation point)
          (rtextures/draw-texture-pro! scarfy source-rec dest-rec origin rotation :white)

          (rshapes/draw-line! (:x dest-rec) 0.0 (:x dest-rec) screen-height :gray)
          (rshapes/draw-line! 0 (:y dest-rec) screen-width (:y dest-rec) :gray)
          (rtext/draw-text! "(c) Scarfy sprite by Eiden Marsal" (- screen-width 200) (- screen-height 20) 10 :gray))

        (when-not (rcore/window-should-close?) ;; Detect window close button or ESC key
          (recur new-rotation)))) ; loop

    ;; De-Initialization
    (rtextures/unload-texture! scarfy) ;; Texture unloading
    (rcore/close-window!))) ;; Close window and OpenGL context
