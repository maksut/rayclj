(ns raylib.shapes
  "Basic Shapes Drawing Functions (Module: shapes)"
  (:require [raylib.arena :as rarena]
            [raylib.structs :as rstructs])
  (:import [raylib raylib_h]))

(defn set-shapes-texture!
  "Set texture and rectangle to be used on shapes drawing
   NOTE: It can be useful when using basic shapes and one single font,
   defining a font char white rectangle would allow drawing everything in a single draw call"
  [textur source] (raylib_h/SetShapesTexture (rstructs/texture textur) source))

(defn draw-pixel!
  "Draw a pixel"
  [pos-x pos-y color] (raylib_h/DrawPixel pos-x pos-y (rstructs/color color)))

(defn draw-pixel-v!
  "Draw a pixel (Vector version)"
  [position color]
  (raylib_h/DrawPixelV (rstructs/vector2 position) (rstructs/color color)))

(defn draw-line!
  "Draw a line"
  [start-pos-x start-pos-y end-pos-x end-pos-y color]
  (raylib_h/DrawLine
   start-pos-x
   start-pos-y
   end-pos-x
   end-pos-y
   (rstructs/color color)))

(defn draw-line-v!
  "Draw a line (Vector version)"
  [start-pos end-pos color]
  (raylib_h/DrawLineV
   (rstructs/vector2 start-pos) (rstructs/vector2 end-pos) (rstructs/color color)))

(defn draw-line-ex!
  "Draw a line defining thickness"
  [start-pos end-pos thick color]
  (raylib_h/DrawLineEx
   (rstructs/vector2 start-pos) (rstructs/vector2 end-pos) thick (rstructs/color color)))

(defn draw-line-bezier!
  "Draw a line using cubic-bezier curves in-out"
  [start-pos end-pos thick color]
  (raylib_h/DrawLineBezier
   (rstructs/vector2 start-pos)
   (rstructs/vector2 end-pos)
   thick (rstructs/color color)))

(defn draw-line-bezier-quad!
  "Draw line using quadratic bezier curves with a control point"
  [start-pos end-pos control-pos thick color]
  (raylib_h/DrawLineBezierQuad
   (rstructs/vector2 start-pos)
   (rstructs/vector2 end-pos)
   (rstructs/vector2 control-pos)
   thick
   (rstructs/color color)))

(defn draw-line-bezier-cubic!
  "Draw line using cubic bezier curves with 2 control points"
  [start-pos end-pos start-control-pos end-control-pos thick color]
  (raylib_h/DrawLineBezierCubic
   (rstructs/vector2 start-pos)
   (rstructs/vector2 end-pos)
   (rstructs/vector2 start-control-pos)
   (rstructs/vector2 end-control-pos)
   thick
   (rstructs/color color)))

(defn draw-line-strip!
  "Draw lines sequence"
  ([points-as-seg point-count color]
   (raylib_h/DrawLineStrip points-as-seg point-count color))
  ([points-as-vec color]
   (raylib_h/DrawLineStrip
    (rstructs/vector2-array points-as-vec)
    (count points-as-vec)
    (rstructs/color color))))

(defn draw-circle!
  "Draw a color-filled circle"
  [center-x center-y radius color]
  (raylib_h/DrawCircle center-x center-y radius (rstructs/color color)))

(defn draw-circle-sector!
  "Draw a piece of a circle"
  [center radius start-angle end-angle segments color]
  (raylib_h/DrawCircleSector
   (rstructs/vector2 center)
   radius
   start-angle
   end-angle
   segments
   (rstructs/color color)))

(defn draw-circle-sector-lines!
  "Draw circle sector outline"
  [center radius start-angle end-angle segments color]
  (raylib_h/DrawCircleSectorLines
   (rstructs/vector2 center)
   radius
   start-angle
   end-angle
   segments
   (rstructs/color color)))

(defn draw-circle-gradient!
  "Draw a gradient-filled circle"
  [center-x center-y radius color1 color2]
  (raylib_h/DrawCircleGradient
   center-x
   center-y
   radius
   (rstructs/color color1)
   (rstructs/color color2)))

(defn draw-circle-v!
  "Draw a color-filled circle (Vector version)"
  [center-v radius color]
  (raylib_h/DrawCircleV
   (rstructs/vector2 center-v) radius (rstructs/color color)))

(defn draw-circle-lines!
  "Draw circle outline"
  [center-x center-y radius color]
  (raylib_h/DrawCircleLines center-x center-y radius (rstructs/color color)))

(defn draw-ellipse!
  "Draw ellipse"
  [center-x center-y radius-h radius-v color]
  (raylib_h/DrawEllipse center-x center-y radius-h radius-v (rstructs/color color)))

(defn draw-ellipse-lines!
  "Draw ellipse outline"
  [center-x center-y radius-h radius-v color]
  (raylib_h/DrawEllipseLines center-x center-y radius-h radius-v (rstructs/color color)))

(defn draw-ring!
  "Draw ring"
  [center inner-radius outer-radius start-angle end-angle segments color]
  (raylib_h/DrawRing
   (rstructs/vector2 center)
   inner-radius
   outer-radius
   start-angle
   end-angle
   segments
   (rstructs/color color)))

(defn draw-ring-lines!
  "Draw ring outline"
  [center inner-radius outer-radius start-angle end-angle segments color]
  (raylib_h/DrawRingLines
   (rstructs/vector2 center)
   inner-radius
   outer-radius
   start-angle
   end-angle
   segments
   (rstructs/color color)))

(defn draw-rectangle!
  "Draw a color-filled rectangle"
  [pos-x pos-y width height color]
  (raylib_h/DrawRectangle pos-x pos-y width height (rstructs/color color)))

(defn draw-rectangle-v!
  "Draw a color-filled rectangle (Vector version)"
  [position size color]
  (raylib_h/DrawRectangleV (rstructs/vector2 position) size (rstructs/color color)))

(defn draw-rectangle-rec!
  "Draw a color-filled rectangle"
  [rectangle color]
  (raylib_h/DrawRectangleRec (rstructs/rectangle rectangle) (rstructs/color color)))

(defn draw-rectangle-pro!
  "Draw a color-filled rectangle with pro parameters"
  [rectangle origin rotation color]
  (raylib_h/DrawRectanglePro
   (rstructs/rectangle rectangle) origin rotation (rstructs/color color)))

(defn draw-rectangle-gradient-v!
  "Draw a vertical-gradient-filled rectangle"
  [pos-x pos-y width height color1 color2]
  (raylib_h/DrawRectangleGradientV
   pos-x pos-y width height (rstructs/color color1) (rstructs/color color2)))

(defn draw-rectangle-gradient-h!
  "Draw a horizontal-gradient-filled rectangle"
  [pos-x pos-y width height color1 color2]
  (raylib_h/DrawRectangleGradientH
   pos-x pos-y width height (rstructs/color color1) (rstructs/color color2)))

(defn draw-rectangle-gradient-ex!
  "Draw a gradient-filled rectangle with custom vertex colors"
  [rectangle color1 color2 color3 color4]
  (raylib_h/DrawRectangleGradientEx
   (rstructs/rectangle rectangle)
   (rstructs/color color1)
   (rstructs/color color2)
   (rstructs/color color3)
   (rstructs/color color4)))

(defn draw-rectangle-lines!
  "Draw rectangle outline"
  [pos-x pos-y width height color]
  (raylib_h/DrawRectangleLines pos-x pos-y width height (rstructs/color color)))

(defn draw-rectangle-lines-ex!
  "Draw rectangle outline with extended parameters"
  [rectangle line-thick color]
  (raylib_h/DrawRectangleLinesEx
   (rstructs/rectangle rectangle) line-thick (rstructs/color color)))

(defn draw-rectangle-rounded!
  "Draw rectangle with rounded edges"
  [rectangle roundness segments color]
  (raylib_h/DrawRectangleRounded
   (rstructs/rectangle rectangle) roundness segments (rstructs/color color)))

(defn draw-rectangle-rounded-lines!
  "Draw rectangle with rounded edges outline"
  [rectangle roundness segments line-thick color]
  (raylib_h/DrawRectangleRoundedLines
   (rstructs/rectangle rectangle)
   roundness
   segments
   line-thick
   (rstructs/color color)))

(defn draw-triangle!
  "Draw a color-filled triangle (vertex in counter-clockwise order!)"
  [v1 v2 v3 color]
  (raylib_h/DrawTriangle
   (rstructs/vector2 v1)
   (rstructs/vector2 v2)
   (rstructs/vector2 v3)
   (rstructs/color color)))

(defn draw-triangle-lines!
  "Draw triangle outline (vertex in counter-clockwise order!)"
  [v1 v2 v3 color]
  (raylib_h/DrawTriangleLines
   (rstructs/vector2 v1)
   (rstructs/vector2 v2)
   (rstructs/vector2 v3)
   (rstructs/color color)))

(defn draw-triangle-fan!
  "Draw a triangle fan defined by points (first vertex is the center)"
  ([points-as-seg point-count color]
   (raylib_h/DrawTriangleFan points-as-seg point-count (rstructs/color color)))
  ([points-as-vectors color]
   (raylib_h/DrawTriangleFan
    (rstructs/vector2-array points-as-vectors)
    (count points-as-vectors)
    (rstructs/color color))))

(defn draw-triangle-strip!
  "Draw a triangle strip defined by points"
  ([points-as-seg point-count color]
   (raylib_h/DrawTriangleStrip points-as-seg point-count (rstructs/color color)))
  ([points-as-vec color]
   (raylib_h/DrawTriangleStrip
    (rstructs/vector2-array points-as-vec)
    (count points-as-vec)
    (rstructs/color color))))

(defn draw-poly!
  "Draw a regular polygon (Vector version)"
  [center sides radius rotation color]
  (raylib_h/DrawPoly
   (rstructs/vector2 center) sides radius rotation (rstructs/color color)))

(defn draw-poly-lines!
  "Draw a polygon outline of n sides"
  [center sides radius rotation color]
  (raylib_h/DrawPolyLines
   (rstructs/vector2 center) sides radius rotation (rstructs/color color)))

(defn draw-poly-lines-ex!
  "Draw a polygon outline of n sides with extended parameters"
  [center sides radius rotation line-thick color]
  (raylib_h/DrawPolyLinesEx
   (rstructs/vector2 center) sides radius rotation line-thick (rstructs/color color)))

;; Basic shapes collision detection functions
(defn check-collision-recs
  "Check collision between two rectangles"
  [rectangle1 rectangle2]
  (raylib_h/CheckCollisionRecs (rstructs/rectangle rectangle1) (rstructs/rectangle rectangle2)))

(defn check-collision-circles
  "Check collision between two circles"
  [center1 radius1 center2 radius2]
  (raylib_h/CheckCollisionCircles
   (rstructs/vector2 center1)
   radius1
   (rstructs/vector2 center2)
   radius2))

(defn check-collision-circle-rec
  "Check collision between circle and rectangle"
  [center radius rectangle]
  (raylib_h/CheckCollisionCircleRec
   (rstructs/vector2 center)
   radius
   (rstructs/rectangle rectangle)))

(defn check-collision-point-rec
  "Check if point is inside rectangle"
  [point rectangle]
  (raylib_h/CheckCollisionPointRec (rstructs/vector2 point) (rstructs/rectangle rectangle)))

(defn check-collision-point-circle
  "Check if point is inside circle"
  [point center radius]
  (raylib_h/CheckCollisionPointCircle (rstructs/vector2 point) (rstructs/vector2 center) radius))

(defn check-collision-point-triangle
  "Check if point is inside a triangle"
  [point p1 p2 p3]
  (raylib_h/CheckCollisionPointTriangle
   (rstructs/vector2 point)
   (rstructs/vector2 p1)
   (rstructs/vector2 p2)
   (rstructs/vector2 p3)))

(defn check-collision-point-poly
  "Check if point is within a polygon described by array of vertices"
  ([point points-as-seg point-as-seg-count]
   (raylib_h/CheckCollisionPointPoly (rstructs/vector2 point) points-as-seg point-as-seg-count))
  ([point points-as-vec]
   (raylib_h/CheckCollisionPointPoly
    (rstructs/vector2 point)
    (rstructs/vector2-array points-as-vec)
    (count points-as-vec))))

;; TODO: Test return by referense
; RLAPI bool CheckCollisionLines(Vector2 startPos1, Vector2 endPos1, Vector2 startPos2, Vector2 endPos2, Vector2 *collisionPoint); // 
(defn check-collision-lines
  "Check the collision between two lines defined by two points each, returns collision point by reference"
  ([start-pos1 end-pos1 start-pos2 end-pos2 collision-point]
   (raylib_h/CheckCollisionLines
    (rstructs/vector2 start-pos1)
    (rstructs/vector2 end-pos1)
    (rstructs/vector2 start-pos2)
    (rstructs/vector2 end-pos2)
    collision-point)))

; RLAPI bool CheckCollisionPointLine(Vector2 point, Vector2 p1, Vector2 p2, int threshold);
(defn check-collision-line
  "Check if point belongs to line created between two points [p1] and [p2] with defined margin in pixels [threshold]"
  ([point p1 p2 threshold]
   (raylib_h/CheckCollisionPointLine
    (rstructs/vector2 point)
    (rstructs/vector2 p1)
    (rstructs/vector2 p2)
    threshold)))

(defn get-collision-rec
  "Get collision rectangle for two rectangles collision"
  [rectangle1 rectangle2]
  (raylib_h/GetCollisionRec
   rarena/*current-arena*
   (rstructs/rectangle rectangle1)
   (rstructs/rectangle rectangle2)))
