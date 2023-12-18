(ns examples.shaders.book-of-shaders
  "For running examples of https://thebookofshaders.com"
  (:require
   [rayclj.raylib.functions :as rl]
   [rayclj.memory :as memory]))

(def screen-width 400)
(def screen-height 400)

(defn load-shader [frag-shader-file]
  (let [shader-struct (rl/load-shader memory/null frag-shader-file)]
    {:shader-struct shader-struct
     :time-loc (rl/get-shader-location shader-struct "u_time")
     :resolution-loc (rl/get-shader-location shader-struct "u_resolution")
     :mouse-loc (rl/get-shader-location shader-struct "u_mouse")}))

(defn run-shader
  [frag-shader-file]

  (rl/init-window screen-width screen-height "Shader book examples")
  (rl/set-target-fps 60)

  (let [frag-shader-file (str "examples/examples/shaders/resources/book_of_shaders/" frag-shader-file)
        shader (load-shader frag-shader-file)
        resolution (memory/float-array [screen-width screen-height])]
    (loop [{:keys [shader-struct time-loc resolution-loc mouse-loc] :as shader} shader]
      (let [time (rl/get-time)
            [x y] (rl/get-mouse-position)
            mouse (memory/float-array [x (- screen-height y)])] ;; in shaders 0,0 is bottom left

        (when (>= time-loc 0)
          (rl/set-shader-value shader-struct time-loc (memory/float time) :float))

        (when (>= resolution-loc 0)
          (rl/set-shader-value shader-struct resolution-loc resolution :vec2))

        (when (>= mouse-loc 0)
          (rl/set-shader-value shader-struct mouse-loc mouse :vec2))

        (rl/with-drawing
          (rl/clear-background :white)

          (rl/with-shader-mode shader-struct
            (rl/draw-rectangle 0 0 screen-width screen-height :red))

          (rl/draw-fps 300 15)))

      (when-not (rl/window-should-close?)
        (recur
         (if (rl/key-pressed? :r) ;; reload shader on key press 'r'
           (do
             (rl/unload-shader shader-struct)
             (load-shader frag-shader-file))
           shader))))

    (rl/unload-shader (:shader-struct shader))
    (rl/close-window)))

(comment
  ;; uniforms
  (run-shader "example01.fs")
  (run-shader "example02.fs")
  (run-shader "example03.fs")

  ;; shaping functions
  (run-shader "example04.fs")
  (run-shader "example05.fs")
  (run-shader "example06.fs")
  (run-shader "example07.fs")

  ;; colors
  (run-shader "example08.fs")
  (run-shader "example09.fs")
  (run-shader "example10.fs")
  (run-shader "example11.fs")

  ;; shapes
  (run-shader "example12.fs")
  (run-shader "example13.fs")
  (run-shader "example14.fs")
  (run-shader "example15.fs")
  (run-shader "example16.fs")
  (run-shader "example17.fs")
  (run-shader "example18.fs")

  ;; matrices
  (run-shader "example19.fs")
  (run-shader "example20.fs")
  (run-shader "example21.fs")
  (run-shader "example22.fs")

  ;; patterns
  (run-shader "example23.fs")
  (run-shader "example24.fs")
  (run-shader "example25.fs")

  ;; random
  (run-shader "example26.fs")
  (run-shader "example27.fs")
  (run-shader "example28.fs")

  ;; noise
  (run-shader "example29.fs")
  (run-shader "example30.fs")
  (run-shader "example31.fs")

  ;; cellular noise
  (run-shader "example32.fs")
  (run-shader "example33.fs")
  (run-shader "example34.fs"))
