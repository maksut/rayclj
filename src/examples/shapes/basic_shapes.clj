(ns examples.shapes.basic-shapes
  (:require [raylib.core :as rc]
            [raylib.text :as rt]
            [raylib.enums :as enums]
            [raylib.shapes :as rs]))

(let [screen-width 800
      screen-height 450]

  (rc/init-window! screen-width screen-height "raylib [shapes] example - basic shapes drawing")

  (rc/set-target-fps! 60) ; Set our game to run at 60 frames-per-second

  (loop [rotation 0.0]
    (when-not (rc/window-should-close?) ; Detect window close button or ESC key
      (rc/with-drawing!
        ; Draw
        (rc/clear-background! ::enums/white)

        (rt/draw-text! "some basic shapes available on raylib" 20 20 20 ::enums/darkgray)

        (rt/draw-fps! 50 50)

        ; Circle shapes and lines
        (rs/draw-circle! (/ screen-width 5) 120 35 ::enums/darkblue)
        (rs/draw-circle-gradient! (/ screen-width 5) 220 60 ::enums/green ::enums/skyblue)
        (rs/draw-circle-lines! (/ screen-width 5) 340 80 ::enums/darkblue)

        ; Rectangle shapes and lines
        (rs/draw-rectangle! (- (* 2 (/ screen-width 4)) 60) 100 120 60 ::enums/red)
        (rs/draw-rectangle-gradient-h! (- (* 2 (/ screen-width 4)) 90) 170 180 130 ::enums/maroon ::enums/gold)
        (rs/draw-rectangle-lines! (- (* 2 (/ screen-width 4)) 40) 320 80 60 ::enums/orange) ; NOTE: Uses QUADS internally, not lines

        ; Triangle shapes and lines
        (rs/draw-triangle!
         [(* 3 (/ screen-width 4)) 80]
         [(- (* 3 (/ screen-width 4)) 60) 150]
         [(+ (* 3 (/ screen-width 4)) 60) 150]
         ::enums/violet)

        (rs/draw-triangle-lines!
         [(* 3 (/ screen-width 4)) 160]
         [(- (* 3 (/ screen-width 4)) 20) 230]
         [(+ (* 3 (/ screen-width 4)) 20) 230]
         ::enums/darkblue)

        ; Polygon shapes and lines
        (let [poly-center [(* 3 (/ screen-width 4)) 330]]
          (rs/draw-poly! poly-center 6 80 rotation ::enums/brown)
          (rs/draw-poly-lines! poly-center 6 90 rotation ::enums/brown)
          (rs/draw-poly-lines-ex! poly-center 6 85 rotation 6 ::enums/beige))

       ; NOTE: We draw all LINES based shapes together to optimize internal drawing,
       ; this way, all LINES are rendered in a single draw pass
        (rs/draw-line! 18 42 (- screen-width 18) 42 ::enums/black))

      ; Loop
      (recur (+ rotation 0.2))))

  (rc/close-window!)) ; De-Initialization

(comment
  (import java.util.WeakHashMap)

  (def compute-if-not-exists
    (let [weak-map (WeakHashMap.)]
      (fn [f arg]
        (let [exists (.get weak-map arg)]
          (if (nil? exists)
            (let [value (f arg)]
              (.put weak-map arg value)
              value)
            exists))))))
