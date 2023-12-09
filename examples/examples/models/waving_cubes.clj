(ns examples.models.waving-cubes
  (:require [rayclj.raylib.functions :as rl]
            [clojure.math :as math]
            [rayclj.memory :as memory]))

(def initial-camera
  {:position [30.0 20.0 30.0] ;; Camera position
   :target [0.0 0.0 0.0]      ;; Camera looking at point
   :up [0.0 1.0 0.0]          ;; Camera up vector (rotation towards target)
   :fovy 70.0                 ;; Camera field-of-view Y
   :projection :perspective}) ;; Camera mode

(defn with-cubes
  "Generates num-blocs^3 cubes in the state at time.
  And calls f with each cube. f is a three arity function that takes
  cube position, size and color."
  [num-blocks time f]
  (let [;; Calculate time scale for cube position and size
        scale (* (+ 2.0 (math/sin time)) 0.7)]
    (doseq [x (range num-blocks)
            y (range num-blocks)
            z (range num-blocks)]
      (let [;; Scale of the blocks depends on x/y/z positions
            block-scale (/ (+ x y z) 30.0)

            ;; Scatter makes the waving effect by adding block-scale over time
            scatter (math/sin (+ (* block-scale 20.0) (* time 4.0)))

            ;; Calculate the cube position
            half-num-blocks (/ num-blocks 2)
            cube-pos [(+ (* (- x half-num-blocks) scale 3.0) scatter)
                      (+ (* (- y half-num-blocks) scale 2.0) scatter)
                      (+ (* (- z half-num-blocks) scale 3.0) scatter)]

            ;; Pick a color with a hue depending on cube position for the rainbow color effect
            cube-color (rl/color-from-hsv (mod (* (+ x y z) 18) 360) 0.75 0.9)

            ;; Calculate cube size
            cube-size  (* (- 2.4 scale) block-scale)]

        ;; And finally, call f with cube data
        (f cube-pos cube-size cube-color)))))

(defn -main []
  ;; Initialization
  (let [screen-width 800
        screen-height 450
        num-blocks 15] ;; Specify the amount of blocks in each direction

    (rl/init-window screen-width screen-height "raylib [models] example - waving cubes")
    (rl/set-target-fps 60)

    (loop [camera initial-camera]
      (when-not (rl/window-should-close?)
        ;; Update
        (let [time (rl/get-time)
              ;; Calculate time scale for cube position and size
              camera-time (double (* time 0.3))
              camera (-> camera
                         (assoc-in [:position 0] (* (math/cos camera-time) 30.0))
                         (assoc-in [:position 2] (* (math/sin camera-time) 40.0)))]

          ;; Draw
          (memory/with-confined-arena
            (rl/with-drawing
              (rl/clear-background :white)

              (rl/with-mode3d camera
                (rl/draw-grid 10 5.0)

                (with-cubes num-blocks time
                  (fn [cube-pos cube-size cube-color]
                    (rl/draw-cube cube-pos cube-size cube-size cube-size cube-color))))))
          (rl/draw-fps 10 10)
          (recur camera))))

    ;; De-Initialization
    (rl/close-window))) ;; Close window and OpenGL context
