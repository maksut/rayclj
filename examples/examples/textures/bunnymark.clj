(ns examples.textures.bunnymark
  (:require [rayclj.raylib.functions :as rl]
            [rayclj.raylib.structs :as rstructs]
            [rayclj.rlgl.defines :refer [default-batch-buffer-elements]]))

(set! *unchecked-math* :warn-on-boxed)

(defn new-bunny []
  {:position (rl/get-mouse-position)
   :speed [(/ ^long (rl/get-random-value -250 250) 60.0)
           (/ ^long (rl/get-random-value -250 250) 60.0)]
   :color (rstructs/color
           {:r (rl/get-random-value 50 240)
            :g (rl/get-random-value 80 240)
            :b (rl/get-random-value 100 240)
            :a 255})})

(defn update-bunny [^double min-x ^double max-x ^double min-y ^double max-y]
  (fn [{[^double pos-x ^double pos-y] :position [^double speed-x ^double speed-y] :speed :as bunny}]
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

(defn -main []
  ;; Initialization
  (let [screen-width 800 screen-height 450]
    (rl/init-window screen-width screen-height "raylib [textures] example - bunnymark")

    (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

    (let [{:keys [^long width ^long height] :as tex-bunny} (rl/load-texture "examples/examples/textures/resources/wabbit_alpha.png")
          tex-bunny-seg (rstructs/texture tex-bunny)
          min-x (* -0.5 width)
          max-x (+ screen-width min-x)
          min-y (* -0.5 height)
          max-y (+ screen-height min-y)]
      (loop [bunnies [] bunnies-count 0]
        (when-not (rl/window-should-close?) ;; Detect window close button or ESC key
          (let [bunnies (if (rl/mouse-button-down? :left)
                          (concat bunnies (take 100 (repeatedly new-bunny)))
                          bunnies)

                bunnies (into [] (map (update-bunny min-x max-x min-y max-y)) bunnies)]

            (rl/with-drawing
              (rl/clear-background :raywhite)

              (doseq [{[x y] :position color :color} bunnies]
                (rl/draw-texture tex-bunny-seg x y color))

              (rl/draw-rectangle 0 0 screen-width 40 :black)
              (rl/draw-text (format "bunnies: %d" bunnies-count) 120 10 20 :green)
              (rl/draw-text (format "batched draw calls: %d" (unchecked-inc (unchecked-divide-int bunnies-count default-batch-buffer-elements))) 320 10 20 :maroon)
              (rl/draw-fps 10 10))

            (recur bunnies (count bunnies)))))

      (rl/unload-texture tex-bunny) ;; Unload bunny texture
      (rl/close-window))))          ;; Close window and OpenGL context
