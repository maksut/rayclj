(ns examples.textures.bunnymark-mutable
  (:require [rayclj.raylib.functions :as rl]
            [rayclj.raylib.structs :as rstructs]
            [rayclj.rlgl.defines :refer [default-batch-buffer-elements]]))

(set! *unchecked-math* :warn-on-boxed)

(definterface IBunny
  (update_x ^:void [^double min-x ^double max-x])
  (update_y ^:void [^double min-y ^double max-y])
  (draw ^:void [texture]))

(deftype Bunny [^:unsynchronized-mutable ^double pos-x
                ^:unsynchronized-mutable ^double pos-y
                ^:unsynchronized-mutable ^double speed-x
                ^:unsynchronized-mutable ^double speed-y
                ^:unsynchronized-mutable color]
  IBunny
  (update_x
    [_ min-x max-x]
    (set! pos-x (unchecked-add pos-x speed-x))

    (when (or (> pos-x max-x) (< pos-x min-x))
      (set! speed-x (unchecked-negate speed-x))))

  (update_y
    [_ min-y max-y]
    (set! pos-y (unchecked-add pos-y speed-y))

    (when (or (> pos-y max-y) (< pos-y min-y))
      (set! speed-y (unchecked-negate speed-y))))

  (draw [_ texture]
    (rl/draw-texture texture pos-x pos-y color)))

(defn new-bunny []
  (let [[pos-x pos-y] (rl/get-mouse-position)
        speed-x (/ ^long (rl/get-random-value -250 250) 60.0)
        speed-y (/ ^long (rl/get-random-value -250 250) 60.0)
        color (rstructs/color
               {:r (rl/get-random-value 50 240)
                :g (rl/get-random-value 80 240)
                :b (rl/get-random-value 100 240)
                :a 255})]
    (Bunny. pos-x pos-y speed-x speed-y color)))

(defn -main []
  (let [screen-width 800 screen-height 450]
    (rl/init-window screen-width screen-height "raylib [textures] example - bunnymark")
    (rl/set-target-fps 60)

    (let [{:keys [^long width ^long height] :as tex-bunny} (rl/load-texture "examples/examples/textures/resources/wabbit_alpha.png")
          tex-bunny-seg (rstructs/texture tex-bunny)
          min-x (* -0.5 width)
          max-x (+ screen-width min-x)
          min-y (* -0.5 height)
          max-y (+ screen-height min-y)]

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
                (.update_x bunny min-x max-x)
                (.update_y bunny min-y max-y)
                (.draw bunny tex-bunny-seg))

              (rl/draw-rectangle 0 0 screen-width 40 :black)
              (rl/draw-text (format "bunnies: %d" bunnies-count) 120 10 20 :green)
              (rl/draw-text (format "batched draw calls: %d" (inc (unchecked-divide-int bunnies-count default-batch-buffer-elements))) 320 10 20 :maroon)
              (rl/draw-fps 10 10))

            (recur bunnies))))

      (rl/unload-texture tex-bunny-seg)) ;; Unload bunny texture
    (rl/close-window)))                  ;; Close window and OpenGL context
