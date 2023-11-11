(ns examples.textures.bunnymark-parallel
  (:require [raylib.functions :as rl]
            [raylib.structs :as rstructs]
            [clj-async-profiler.core :as prof]))

(def BUNNY_PARTITION_SIZE 8192)

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

(def BATCH_SIZE 5000)

(comment
  (prof/serve-ui 8080))

;; Initialization
(prof/profile
 (let [screen-width 800 screen-height 450]
   (rl/init-window screen-width screen-height "raylib [textures] example - bunnymark")

   (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

   (let [tex-bunny (rl/load-texture "src/examples/textures/resources/wabbit_alpha.png")
         tex-bunny-seg (rstructs/texture tex-bunny)
         min-x (* -1 (/ (:width tex-bunny) 2))
         max-x (+ (rl/get-screen-width) min-x)
         min-y (* -1 (/ (:height tex-bunny) 2))
         max-y (+ (rl/get-screen-height) min-y)
         draw-bunny (fn [{[x y] :position color :color}] (rl/draw-texture tex-bunny-seg x y color))]
     (loop [batches [] bunnies-count 0]
       (when-not (rl/window-should-close?) ;; Detect window close button or ESC key
         (let [[batches bunnies-count]
               (if (rl/mouse-button-down? :left)
                 (let [new-bunnies (take 100 (repeatedly new-bunny))
                       all-bunnies (concat (flatten batches) new-bunnies)
                       bunnies-count (count all-bunnies)]
                   [(partition-all BATCH_SIZE all-bunnies) bunnies-count])
                 [batches bunnies-count])

               update-bunny (update-bunny min-x max-x min-y max-y)
               batches (pmap #(map update-bunny %) batches)]

           (rl/with-drawing
             (rl/clear-background :raywhite)

             (loop [b (first batches) bs (rest batches)]
               (when (seq? b)
                 (dorun (map draw-bunny b))
                 (recur (first bs) (rest bs))))

               ; (map (fn [batch] (dorun (map draw-bunny batch))) batches)))

             (rl/draw-rectangle 0 0 screen-width 40 :black)
             (rl/draw-text (format "bunnies: %d" bunnies-count) 120 10 20 :green)
             (rl/draw-text (format "batched draw calls: %d" (int (inc (/ bunnies-count BUNNY_PARTITION_SIZE)))) 320 10 20 :maroon)
             (rl/draw-fps 10 10))

           (recur batches bunnies-count))))

     (rl/unload-texture tex-bunny) ;; Unload bunny texture
     (rl/close-window))))           ;; Close window and OpenGL context
