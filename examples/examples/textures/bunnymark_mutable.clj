(ns examples.textures.bunnymark-mutable
  (:require [rayclj.raylib.functions :as rl]
            [rayclj.raylib.structs :as rstructs]
            [rayclj.rlgl.defines :refer [default-batch-buffer-elements]]))

(definterface IBunny
  (update_and_draw [texture min-x max-x min-y max-y]))

(deftype Bunny [^:unsynchronized-mutable ^:int pos-x
                ^:unsynchronized-mutable ^:int pos-y
                ^:unsynchronized-mutable ^:int speed-x
                ^:unsynchronized-mutable ^:int speed-y
                color]

  IBunny
  (update-and-draw [_ texture min-x max-x min-y max-y]
    (set! pos-x (unchecked-add pos-x speed-x))
    (set! pos-y (unchecked-add pos-y speed-y))

    (when (or (> pos-x max-x) (< pos-x min-x))
      (set! speed-x (unchecked-negate speed-x)))

    (when (or (> pos-y max-y) (< pos-y min-y))
      (set! speed-y (unchecked-negate speed-y)))

    (rl/draw-texture texture pos-x pos-y color)))

(defn new-bunny []
  (let [[pos-x pos-y] (rl/get-mouse-position)
        speed-x (/ (rl/get-random-value -250 250) 60.0)
        speed-y (/ (rl/get-random-value -250 250) 60.0)
        color (rstructs/color
               {:r (rl/get-random-value 50 240)
                :g (rl/get-random-value 80 240)
                :b (rl/get-random-value 100 240)
                :a 255})]
    (Bunny. pos-x pos-y speed-x speed-y color)))

(let [screen-width 800 screen-height 450]
  (rl/init-window screen-width screen-height "raylib [textures] example - bunnymark")
  (rl/set-target-fps 60)

  (let [{:keys [width height] :as tex-bunny} (rl/load-texture "examples/examples/textures/resources/wabbit_alpha.png")
        tex-bunny-seg (rstructs/texture tex-bunny)
        min-x (* -1 (/ width 2))
        max-x (+ (rl/get-screen-width) min-x)
        min-y (* -1 (/ height 2))
        max-y (+ (rl/get-screen-height) min-y)]

    (loop [bunnies []]
      (when-not (rl/window-should-close?)
        (let [bunnies (if (rl/mouse-button-down? :left)
                        (loop [bunnies bunnies i 0]
                          (if (< i 100)
                            (recur (conj bunnies (new-bunny)) (inc i))
                            bunnies))
                        bunnies)

              bunnies-count (count bunnies)]

          ;; update mixed in drawing!
          (rl/with-drawing
            (rl/clear-background :raywhite)

            (doseq [^IBunny bunny bunnies]
              (.update-and-draw bunny tex-bunny-seg min-x max-x min-y max-y))

            (rl/draw-rectangle 0 0 screen-width 40 :black)
            (rl/draw-text (format "bunnies: %d" bunnies-count) 120 10 20 :green)
            (rl/draw-text (format "batched draw calls: %d" (int (inc (/ bunnies-count default-batch-buffer-elements)))) 320 10 20 :maroon)
            (rl/draw-fps 10 10))

          (recur bunnies))))

    (rl/unload-texture tex-bunny-seg)) ;; Unload bunny texture
  (rl/close-window))               ;; Close window and OpenGL context
