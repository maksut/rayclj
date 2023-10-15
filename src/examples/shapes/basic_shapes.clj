(ns examples.shapes.basic-shapes
  (:require [raylib.core :as rcore]
            [raylib.text :as rtext]
            [raylib.shapes :as rshapes]))

(let [screen-width 800
      screen-height 450]

  (rcore/init-window! screen-width screen-height "raylib [shapes] example - basic shapes drawing")

  (rcore/set-target-fps! 60) ; Set our game to run at 60 frames-per-second

  (loop [rotation 0.0]
    (when-not (rcore/window-should-close?) ; Detect window close button or ESC key
      (rcore/with-drawing!
        ; Draw
        (rcore/clear-background! :white)

        (rtext/draw-text! "some basic shapes available on raylib" 20 20 20 :darkgray)

        (rtext/draw-fps! 50 50)

        ; Circle shapes and lines
        (rshapes/draw-circle! (/ screen-width 5) 120 35 :darkblue)
        (rshapes/draw-circle-gradient! (/ screen-width 5) 220 60 :green :skyblue)
        (rshapes/draw-circle-lines! (/ screen-width 5) 340 80 :darkblue)

        ; Rectangle shapes and lines
        (rshapes/draw-rectangle! (- (* 2 (/ screen-width 4)) 60) 100 120 60 :red)
        (rshapes/draw-rectangle-gradient-h! (- (* 2 (/ screen-width 4)) 90) 170 180 130 :maroon :gold)
        (rshapes/draw-rectangle-lines! (- (* 2 (/ screen-width 4)) 40) 320 80 60 :orange) ; NOTE: Uses QUADS internally, not lines

        ; Triangle shapes and lines
        (rshapes/draw-triangle!
         [(* 3 (/ screen-width 4)) 80]
         [(- (* 3 (/ screen-width 4)) 60) 150]
         [(+ (* 3 (/ screen-width 4)) 60) 150]
         :violet)

        (rshapes/draw-triangle-lines!
         [(* 3 (/ screen-width 4)) 160]
         [(- (* 3 (/ screen-width 4)) 20) 230]
         [(+ (* 3 (/ screen-width 4)) 20) 230]
         :darkblue)

        ; Polygon shapes and lines
        (let [poly-center [(* 3 (/ screen-width 4)) 330]]
          (rshapes/draw-poly! poly-center 6 80 rotation :brown)
          (rshapes/draw-poly-lines! poly-center 6 90 rotation :brown)
          (rshapes/draw-poly-lines-ex! poly-center 6 85 rotation 6 :beige))

       ; NOTE: We draw all LINES based shapes together to optimize internal drawing,
       ; this way, all LINES are rendered in a single draw pass
        (rshapes/draw-line! 18 42 (- screen-width 18) 42 :black))

      ; Loop
      (recur (+ rotation 0.2))))

  (rcore/close-window!)) ; De-Initialization
