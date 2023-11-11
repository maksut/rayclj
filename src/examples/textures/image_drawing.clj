(ns examples.textures.image-drawing
  (:require [raylib.functions :as rl]))

;; Initialization
(let [screen-width 800 screen-height 450]
  (rl/init-window screen-width screen-height "raylib [textures] examples - image drawing")

  ;; NOTE: Textures MUST be loaded after Window initialization (OpenGL context is required)
  (let [cat (-> (rl/load-image "src/examples/textures/resources/cat.png") ;; Load image in CPU memory (RAM)
                (rl/image-crop {:x 100 :y 10 :width 280 :height 380})         ;; Crop an image piece
                (rl/image-flip-horizontal)                                    ;; Flip cropped image horizontally
                (rl/image-resize 150 200))                                    ;; Resize flipped-cropped image

        parrots (rl/load-image "src/examples/textures/resources/parrots.png") ;; Load image in CPU memory (RAM)
        {parrots-width :width parrots-height :height} parrots
        src-rec {:x 0 :y 0 :width (:width cat) :height (:height cat)}
        dst-rec {:x 30 :y 40 :width (* (:width cat) 1.5) :height (* (:height cat) 1.5)}
        parrots (-> parrots
                     ;; Draw one image over the other with a scaling of 1.5f
                    (rl/image-draw cat src-rec dst-rec :white)
                     ;; Crop resulting image
                    (rl/image-crop {:x 0 :y 50 :width parrots-width :height (- parrots-height 100)})
                     ;; Draw on the image with a few image draw methods
                    (rl/image-draw-pixel 10 10 :white)
                    (rl/image-draw-circle-lines 10 10 5 :white)
                    (rl/image-draw-rectangle 5 20 10 10 :white))

        ;; Load custom font for frawing on image
        font (rl/load-font "src/examples/textures/resources/custom_jupiter_crash.png")

        ;; Draw over image using custom font
        parrots (rl/image-draw-text-ex parrots font "PARROTS & CAT" [300 230] (:base-size font) -2 :white)]

    (rl/unload-image cat) ;; Unload image from RAM
    (rl/unload-font font) ;; Unload custom font (already drawn used on image)

    (let [texture (rl/load-texture-from-image parrots)
          texture-width (:width texture)
          texture-height (:height texture)]
      (rl/unload-image parrots) ;; Once image has been converted to texture and uploaded to VRAM, it can be unloaded from RAM

      (rl/set-target-fps 60)

      (while (not (rl/window-should-close?)) ;; Detect window close button or ESC key
        ;; Update
        ;;----------------------------------------------------------------------------------
        ;; TODO: Update your variables here
        ;;----------------------------------------------------------------------------------

        ;; Draw
        (rl/with-drawing
          (rl/clear-background :raywhite)

          (rl/draw-texture
           texture
           (- (/ screen-width 2) (/ texture-width 2))
           (- (/ screen-height 2) (/ texture-height 2) 40)
           :white)

          (rl/draw-rectangle-lines
           (- (/ screen-width 2) (/ texture-width 2))
           (- (/ screen-height 2) (/ texture-height 2) 40)
           texture-width
           texture-height
           :darkgray)

          (rl/draw-text "We are drawing only one texture from various images composed" 240 350 10 :darkgray)
          (rl/draw-text "Source images have been cropped, scaled, flipped and copied one over the other." 190 370 10 :darkgray)))

      ;; De-Initialization
      (rl/unload-texture texture) ;; Texture unloading
      (rl/close-window))))        ;; Close window and OpenGL context


