(ns examples.textures.image-drawing
  (:require [raylib.core :as rcore]
            [raylib.text :as rtext]
            [raylib.textures :as rtextures]
            [raylib.shapes :as rshapes]))

;; Initialization
(let [screen-width 800 screen-height 450]
  (rcore/init-window! screen-width screen-height "raylib [textures] examples - image drawing")

  ;; NOTE: Textures MUST be loaded after Window initialization (OpenGL context is required)
  (let [cat (-> (rtextures/load-image! "src/examples/textures/resources/cat.png") ;; Load image in CPU memory (RAM)
                (rtextures/image-crop! {:x 100 :y 10 :width 280 :height 380})     ;; Crop an image piece
                (rtextures/image-flip-horizontal!)                                ;; Flip cropped image horizontally
                (rtextures/image-resize! 150 200))                                ;; Resize flipped-cropped image

        parrots (rtextures/load-image! "src/examples/textures/resources/parrots.png") ;; Load image in CPU memory (RAM)
        {parrots-width :width parrots-height :height} parrots
        src-rec {:x 0 :y 0 :width (:width cat) :height (:height cat)}
        dst-rec {:x 30 :y 40 :width (* (:width cat) 1.5) :height (* (:height cat) 1.5)}
        parrots (-> parrots
                    ;; Draw one image over the other with a scaling of 1.5f
                    (rtextures/image-draw! cat src-rec dst-rec :white)
                    ;; Crop resulting image
                    (rtextures/image-crop! {:x 0 :y 50 :width parrots-width :height (- parrots-height 100)})
                    ;; Draw on the image with a few image draw methods
                    (rtextures/image-draw-pixel! 10 10 :white)
                    (rtextures/image-draw-circle-lines! 10 10 5 :white)
                    (rtextures/image-draw-rectangle! 5 20 10 10 :white))

        ;; Load custom font for frawing on image
        font (rtext/load-font! "src/examples/textures/resources/custom_jupiter_crash.png")

        ;; Draw over image using custom font
        parrots (rtextures/image-draw-text-ex! parrots font "PARROTS & CAT" [300 230] (:base-size font) -2 :white)]

    (rtextures/unload-image! cat) ;; Unload image from RAM
    (rtext/unload-font! font)     ;; Unload custom font (already drawn used on image)

    (let [texture (rtextures/load-texture-from-image! parrots)
          texture-width (:width texture)
          texture-height (:height texture)]
      (rtextures/unload-image! parrots) ;; Once image has been converted to texture and uploaded to VRAM, it can be unloaded from RAM

      (rcore/set-target-fps! 60)

      (loop []
        (when-not (rcore/window-should-close?) ;; Detect window close button or ESC key
          ;; Update
          ;;----------------------------------------------------------------------------------
          ;; TODO: Update your variables here
          ;;----------------------------------------------------------------------------------

          ;; Draw
          (rcore/with-drawing!
            (rcore/clear-background! :raywhite)

            (rtextures/draw-texture!
             texture
             (- (/ screen-width 2) (/ texture-width 2))
             (- (/ screen-height 2) (/ texture-height 2) 40)
             :white)

            (rshapes/draw-rectangle-lines!
             (- (/ screen-width 2) (/ texture-width 2))
             (- (/ screen-height 2) (/ texture-height 2) 40)
             texture-width
             texture-height
             :darkgray)

            (rtext/draw-text! "We are drawing only one texture from various images composed!" 240 350 10 :darkgray)
            (rtext/draw-text! "Source images have been cropped, scaled, flipped and copied one over the other." 190 370 10 :darkgray))
          (recur)))

      ;; De-Initialization
      (rtextures/unload-texture! texture) ;; Texture unloading
      (rcore/close-window!))))            ;; Close window and OpenGL context


