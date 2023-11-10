(ns gen.examples.textures.bunnymark
  (:require [gen.functions :as rl]
            [gen.structs :as rstructs]))

(def MAX_BATCH_ELEMENTS 8192)

(defn new-bunny []
  {:position (rl/get-mouse-position)
   :speed [(/ (rl/get-random-value -250 250) 60.0)
           (/ (rl/get-random-value -250 250) 60.0)]
   :color (rstructs/color
           {:r (rl/get-random-value 50 240)
            :g (rl/get-random-value 80 240)
            :b (rl/get-random-value 100 240)
            :a 255})})

(defn update-bunny [min-x max-x min-y max-y]
  (fn [{[pos-x pos-y] :position [speed-x speed-y] :speed :as bunny}]
    (let [; update position first
          pos-x (unchecked-add pos-x speed-x)
          pos-y (unchecked-add pos-y speed-y)
          bunny (assoc bunny :position [pos-x pos-y])

          ; then update speed if needed
          pos-x-out? (or (> pos-x max-x) (< pos-x min-x))
          pos-y-out? (or (> pos-y max-y) (< pos-y min-y))]
      (if (or pos-x-out? pos-y-out?)
        (assoc bunny :speed [(if pos-x-out? (unchecked-negate speed-x) speed-x)
                             (if pos-y-out? (unchecked-negate speed-y) speed-y)])
        bunny))))

;; Initialization
(let [screen-width 800 screen-height 450]
  (rl/init-window screen-width screen-height "raylib [textures] example - bunnymark")

  (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

  (let [tex-bunny (rl/load-texture "src/gen/examples/textures/resources/wabbit_alpha.png")
        tex-bunny-seg (rstructs/texture tex-bunny)
        min-x (* -1 (/ (:width tex-bunny) 2))
        max-x (+ (rl/get-screen-width) min-x)
        min-y (* -1 (/ (:height tex-bunny) 2))
        max-y (+ (rl/get-screen-height) min-y)]
    (loop [bunnies [] bunnies-count 0]
      (when-not (rl/window-should-close?) ;; Detect window close button or ESC key
        (let [bunnies (if (rl/mouse-button-down? :left)
                        (concat bunnies (take 100 (repeatedly new-bunny)))
                        bunnies)

              bunnies (into [] (map (update-bunny min-x max-x min-y max-y)) bunnies)]

          (rl/with-drawing
            (rl/clear-background :raywhite)

            (dorun
             (map
              (fn [{[x y] :position color :color}] (rl/draw-texture tex-bunny-seg x y color))
              bunnies))

            (rl/draw-rectangle 0 0 screen-width 40 :black)
            (rl/draw-text (format "bunnies: %d" bunnies-count) 120 10 20 :green)
            (rl/draw-text (format "batched draw calls: %d" (int (inc (/ bunnies-count MAX_BATCH_ELEMENTS)))) 320 10 20 :maroon)
            (rl/draw-fps 10 10))

          (recur bunnies (count bunnies)))))

    (rl/unload-texture tex-bunny) ;; Unload bunny texture
    (rl/close-window)))           ;; Close window and OpenGL context
