(ns examples.shapes.basic-shapes
  (:require [raylib :as r]))

(let [screen-width 800
      screen-height 450
      message (r/string "some basic shapes available on raylib")]

  (r/init-window! screen-width screen-height (r/string "raylib [shapes] example - basic shapes drawing"))

  (r/set-target-fps! 60) ; Set our game to run at 60 frames-per-second

  (loop [rotation 0.0]
    (when-not (r/window-should-close?) ; Detect window close button or ESC key

       ; Draw
      (r/begin-drawing!)
      (r/clear-background! (r/color ::r/white))

      (r/draw-text! message 20 20 20 (r/color ::r/darkgray))

      ; Circle shapes and lines
      (r/draw-circle! (/ screen-width 5) 120 35 (r/color ::r/darkblue))
      (r/draw-circle-gradient! (/ screen-width 5) 220 60 (r/color ::r/green) (r/color ::r/skyblue))
      (r/draw-circle-lines! (/ screen-width 5) 340 80 (r/color ::r/darkblue))

      ; Rectangle shapes and lines
      (r/draw-rectangle! (- (* 2 (/ screen-width 4)) 60) 100 120 60 (r/color ::r/red))
      (r/draw-rectangle-gradient-h! (- (* 2 (/ screen-width 4)) 90) 170 180 130 (r/color ::r/maroon) (r/color ::r/gold))
      (r/draw-rectangle-lines! (- (* 2 (/ screen-width 4)) 40) 320 80 60 (r/color ::r/orange)) ; NOTE: Uses QUADS internally, not lines

      ; Triangle shapes and lines
      (r/draw-triangle!
       (r/vector2 [(* 3 (/ screen-width 4)) 80])
       (r/vector2 [(- (* 3 (/ screen-width 4)) 60) 150])
       (r/vector2 [(+ (* 3 (/ screen-width 4)) 60) 150])
       (r/color ::r/violet))

      (r/draw-triangle-lines!
       (r/vector2 [(* 3 (/ screen-width 4)) 160])
       (r/vector2 [(- (* 3 (/ screen-width 4)) 20) 230])
       (r/vector2 [(+ (* 3 (/ screen-width 4)) 20) 230])
       (r/color ::r/darkblue))

      ; Polygon shapes and lines
      (r/draw-poly! (r/vector2 [(* 3 (/ screen-width 4)) 330]) 6 80 rotation (r/color ::r/brown))
      (r/draw-poly-lines! (r/vector2 [(* 3 (/ screen-width 4)) 330]) 6 90 rotation (r/color ::r/brown))
      (r/draw-poly-lines-ex! (r/vector2 [(* 3 (/ screen-width 4)) 330]) 6 85 rotation 6 (r/color ::r/beige))

      ; NOTE: We draw all LINES based shapes together to optimize internal drawing,
      ; this way, all LINES are rendered in a single draw pass
      (r/draw-line! 18 42 (- screen-width 18) 42 (r/color ::r/black))

      (r/end-drawing!)

      ; Loop
      (recur (+ rotation 0.2))))

  (r/close-window!)) ; De-Initialization
