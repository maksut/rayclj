(ns examples.shaders.pallette-switch
  (:require
   [rayclj.raylib.functions :as rl]
   [rayclj.memory :as memory]))

(def palettes
  [(memory/int-array
    [;; 3-BIT RGB
     0, 0, 0,
     255, 0, 0,
     0, 255, 0,
     0, 0, 255,
     0, 255, 255,
     255, 0, 255,
     255, 255, 0,
     255, 255, 255])
   (memory/int-array
    [;; AMMO-8 (GameBoy-like)
     4, 12, 6,
     17, 35, 24,
     30, 58, 41,
     48, 93, 66,
     77, 128, 97,
     137, 162, 87,
     190, 220, 127,
     238, 255, 204])
   (memory/int-array
    [;; RKBV (2-strip film)
     21, 25, 26,
     138, 76, 88,
     217, 98, 117,
     230, 184, 193,
     69, 107, 115,
     75, 151, 166,
     165, 189, 194,
     255, 245, 247])])

(def palette-text
  ["3-BIT RGB"
   "AMMO-8 (GameBoy-like)"
   "RKBV (2-strip film)"])

(def MAX_PALETTES 3)
(def COLORS_PER_PALETTE 8)

(defn -main []
  (let [screen-width 800 screen-height 450]

    ;; Initialization
    (rl/init-window screen-width screen-height "raylib [shaders] example - color palette switch")

    ;; Load shader to be used on some parts drawing
    ;; NOTE: Defining 0 (NULL) for vertex shader forces usage of internal default vertex shader
    (let [shader (rl/load-shader memory/null "examples/examples/shaders/resources/glsl330/palette_switch.fs")
          palette-loc (rl/get-shader-location shader "palette")
          line-height (int (/ screen-height COLORS_PER_PALETTE))]

      (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

      ;; Main game loop
      (loop [current-palette 0]
        ;; Update
        (let [current-palette
              (cond
                (rl/key-pressed? :right) (min (dec MAX_PALETTES) (inc current-palette))
                (rl/key-pressed? :left) (max 0 (dec current-palette))
                :else current-palette)]

          ;; Send new value to the shader to be used on drawing.
          ;; NOTE: We are sending RGB triplets w/o the alpha channel
          (rl/set-shader-value-v shader palette-loc (nth palettes current-palette) :ivec3 COLORS_PER_PALETTE)

          ;; Draw
          (rl/with-drawing
            (rl/clear-background :white)

            ;; Draw horizontal screen-wide rectangles with increasing "palette index"
            ;; The used palette index is encoded in the RGB components of the pixel
            (rl/with-shader-mode shader
              (doseq [i (range COLORS_PER_PALETTE)]
                (rl/draw-rectangle 0 (* line-height i) (rl/get-screen-width) line-height {:r i :g i :b i :a 255})))

            (rl/draw-text "< >" 10 10 30 :darkblue)
            (rl/draw-text "CURRENT PALETTE:" 60 15 20 :raywhite)
            (rl/draw-text (nth palette-text current-palette) 300 15 20 :red)
            (rl/draw-fps 700 15))

          (when-not (rl/window-should-close?) ;; Detect window close button or ESC key
            (recur (int current-palette)))))

      ;; De-Initialization
      (rl/unload-shader shader) ;; Unload shader
      (rl/close-window)))) ;; Close window and OpenGL context
