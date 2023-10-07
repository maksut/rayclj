(ns raylib.shapes
  "Basic Shapes Drawing Functions (Module: shapes)"
  (:require [raylib.structs :as structs])
  (:import [raylib raylib_h]))

(defn set-shapes-texture!
  "Set texture and rectangle to be used on shapes drawing
   NOTE: It can be useful when using basic shapes and one single font,
   defining a font char white rectangle would allow drawing everything in a single draw call"
  [textur source] (raylib_h/SetShapesTexture (structs/texture textur) source))

(defn draw-pixel!
  "Draw a pixel"
  [pos-x pos-y col] (raylib_h/DrawPixel pos-x pos-y (structs/color col)))

(defn draw-pixel-v!
  "Draw a pixel (Vector version)"
  [position col]
  (raylib_h/DrawPixelV (structs/vector2 position) (structs/color col)))

(defn draw-line!
  "Draw a line"
  [start-pos-x start-pos-y end-pos-x end-pos-y col]
  (raylib_h/DrawLine
   start-pos-x
   start-pos-y
   end-pos-x
   end-pos-y
   (structs/color col)))

(defn draw-line-v!
  "Draw a line (Vector version)"
  [start-pos end-pos col]
  (raylib_h/DrawLineV
   (structs/vector2 start-pos) (structs/vector2 end-pos) (structs/color col)))

(defn draw-line-ex!
  "Draw a line defining thickness"
  [start-pos end-pos thick col]
  (raylib_h/DrawLineEx
   (structs/vector2 start-pos) (structs/vector2 end-pos) thick (structs/color col)))

(defn draw-line-bezier!
  "Draw a line using cubic-bezier curves in-out"
  [start-pos end-pos thick col]
  (raylib_h/DrawLineBezier
   (structs/vector2 start-pos)
   (structs/vector2 end-pos)
   thick (structs/color col)))

(defn draw-line-bezier-quad!
  "Draw line using quadratic bezier curves with a control point"
  [start-pos end-pos control-pos thick col]
  (raylib_h/DrawLineBezierQuad
   (structs/vector2 start-pos)
   (structs/vector2 end-pos)
   (structs/vector2 control-pos)
   thick
   (structs/color col)))

(defn draw-line-bezier-cubic!
  "Draw line using cubic bezier curves with 2 control points"
  [start-pos end-pos start-control-pos end-control-pos thick col]
  (raylib_h/DrawLineBezierCubic
   (structs/vector2 start-pos)
   (structs/vector2 end-pos)
   (structs/vector2 start-control-pos)
   (structs/vector2 end-control-pos)
   thick
   (structs/color col)))

;;TODO: test this
; RLAPI void DrawLineStrip (Vector2 *points, int pointCount, Color color);
(defn draw-line-strip!
  "Draw lines sequence"
  [points point-count color]
  (raylib_h/DrawLineStrip points point-count color))

(defn draw-circle!
  "Draw a color-filled circle"
  [center-x center-y radius col]
  (raylib_h/DrawCircle center-x center-y radius (structs/color col)))

(defn draw-circle-sector!
  "Draw a piece of a circle"
  [center radius start-angle end-angle segments col]
  (raylib_h/DrawCircleSector
   (structs/vector2 center)
   radius
   start-angle
   end-angle
   segments
   (structs/color col)))

(defn draw-circle-sector-lines!
  "Draw circle sector outline"
  [center radius start-angle end-angle segments col]
  (raylib_h/DrawCircleSectorLines
   (structs/vector2 center)
   radius
   start-angle
   end-angle
   segments
   (structs/color col)))

(defn draw-circle-gradient!
  "Draw a gradient-filled circle"
  [center-x center-y radius color1 color2]
  (raylib_h/DrawCircleGradient
   center-x
   center-y
   radius
   (structs/color color1)
   (structs/color color2)))

(defn draw-circle-v!
  "Draw a color-filled circle (Vector version)"
  [center-v radius col]
  (raylib_h/DrawCircleV
   (structs/vector2 center-v) radius (structs/color col)))

(defn draw-circle-lines!
  "Draw circle outline"
  [center-x center-y radius col]
  (raylib_h/DrawCircleLines center-x center-y radius (structs/color col)))

(defn draw-ellipse!
  "Draw ellipse"
  [center-x center-y radius-h radius-v col]
  (raylib_h/DrawEllipse center-x center-y radius-h radius-v (structs/color col)))

(defn draw-ellipse-lines!
  "Draw ellipse outline"
  [center-x center-y radius-h radius-v col]
  (raylib_h/DrawEllipseLines center-x center-y radius-h radius-v (structs/color col)))

(defn draw-ring!
  "Draw ring"
  [center inner-radius outer-radius start-angle end-angle segments col]
  (raylib_h/DrawRing
   (structs/vector2 center)
   inner-radius
   outer-radius
   start-angle
   end-angle
   segments
   (structs/color col)))

(defn draw-ring-lines!
  "Draw ring outline"
  [center inner-radius outer-radius start-angle end-angle segments col]
  (raylib_h/DrawRingLines
   (structs/vector2 center)
   inner-radius
   outer-radius
   start-angle
   end-angle
   segments
   (structs/color col)))

(defn draw-rectangle!
  "Draw a color-filled rectangle"
  [pos-x pos-y width height col]
  (raylib_h/DrawRectangle pos-x pos-y width height (structs/color col)))

(defn draw-rectangle-v!
  "Draw a color-filled rectangle (Vector version)"
  [position size col]
  (raylib_h/DrawRectangleV (structs/vector2 position) size (structs/color col)))

(defn draw-rectangle-rec!
  "Draw a color-filled rectangle"
  [rec col]
  (raylib_h/DrawRectangleRec (structs/rectangle rec) (structs/color col)))

(defn draw-rectangle-pro!
  "Draw a color-filled rectangle with pro parameters"
  [rec origin rotation col]
  (raylib_h/DrawRectanglePro
   (structs/rectangle rec) origin rotation (structs/color col)))

(defn draw-rectangle-gradient-v!
  "Draw a vertical-gradient-filled rectangle"
  [pos-x pos-y width height color1 color2]
  (raylib_h/DrawRectangleGradientV
   pos-x pos-y width height (structs/color color1) (structs/color color2)))

(defn draw-rectangle-gradient-h!
  "Draw a horizontal-gradient-filled rectangle"
  [pos-x pos-y width height color1 color2]
  (raylib_h/DrawRectangleGradientH
   pos-x pos-y width height (structs/color color1) (structs/color color2)))

(defn draw-rectangle-gradient-ex!
  "Draw a gradient-filled rectangle with custom vertex colors"
  [rec col1 col2 col3 col4]
  (raylib_h/DrawRectangleGradientEx
   (structs/rectangle rec)
   (structs/color col1)
   (structs/color col2)
   (structs/color col3)
   (structs/color col4)))

(defn draw-rectangle-lines!
  "Draw rectangle outline"
  [pos-x pos-y width height col]
  (raylib_h/DrawRectangleLines pos-x pos-y width height (structs/color col)))

(defn draw-rectangle-lines-ex!
  "Draw rectangle outline with extended parameters"
  [rec line-thick col]
  (raylib_h/DrawRectangleLinesEx
   (structs/rectangle rec) line-thick (structs/color col)))

(defn draw-rectangle-rounded!
  "Draw rectangle with rounded edges"
  [rec roundness segments col]
  (raylib_h/DrawRectangleRounded
   (structs/rectangle rec) roundness segments (structs/color col)))

(defn draw-rectangle-rounded-lines!
  "Draw rectangle with rounded edges outline"
  [rec roundness segments line-thick col]
  (raylib_h/DrawRectangleRoundedLines
   (structs/rectangle rec)
   roundness
   segments
   line-thick
   (structs/color col)))

(defn draw-triangle!
  "Draw a color-filled triangle (vertex in counter-clockwise order!)"
  [v1 v2 v3 col]
  (raylib_h/DrawTriangle
   (structs/vector2 v1)
   (structs/vector2 v2)
   (structs/vector2 v3)
   (structs/color col)))

(defn draw-triangle-lines!
  "Draw triangle outline (vertex in counter-clockwise order!)"
  [v1 v2 v3 col]
  (raylib_h/DrawTriangleLines
   (structs/vector2 v1)
   (structs/vector2 v2)
   (structs/vector2 v3)
   (structs/color col)))

; TODO: test this
; RLAPI void DrawTriangleFan (Vector2 *points, int pointCount, Color color);
(defn draw-triangle-fan!
  "Draw a triangle fan defined by points (first vertex is the center)"
  [points point-count col]
  (raylib_h/DrawTriangleFan points point-count (structs/color col)))

; TODO: test this
; RLAPI void DrawTriangleStrip (Vector2 *points, int pointCount, Color color);
(defn draw-triangle-strip!
  "Draw a triangle strip defined by points"
  [points point-count col]
  (raylib_h/DrawTriangleStrip points point-count (structs/color col)))

(defn draw-poly!
  "Draw a regular polygon (Vector version)"
  [center sides radius rotation col]
  (raylib_h/DrawPoly
   (structs/vector2 center) sides radius rotation (structs/color col)))

(defn draw-poly-lines!
  "Draw a polygon outline of n sides"
  [center sides radius rotation col]
  (raylib_h/DrawPolyLines
   (structs/vector2 center) sides radius rotation (structs/color col)))

(defn draw-poly-lines-ex!
  "Draw a polygon outline of n sides with extended parameters"
  [center sides radius rotation line-thick col]
  (raylib_h/DrawPolyLinesEx
   (structs/vector2 center) sides radius rotation line-thick (structs/color col)))

; // Basic shapes collision detection functions
; RLAPI bool CheckCollisionRecs(Rectangle rec1, Rectangle rec2);                                           // Check collision between two rectangles
; RLAPI bool CheckCollisionCircles(Vector2 center1, float radius1, Vector2 center2, float radius2);        // Check collision between two circles
; RLAPI bool CheckCollisionCircleRec(Vector2 center, float radius, Rectangle rec);                         // Check collision between circle and rectangle
; RLAPI bool CheckCollisionPointRec(Vector2 point, Rectangle rec);                                         // Check if point is inside rectangle
; RLAPI bool CheckCollisionPointCircle(Vector2 point, Vector2 center, float radius);                       // Check if point is inside circle
; RLAPI bool CheckCollisionPointTriangle(Vector2 point, Vector2 p1, Vector2 p2, Vector2 p3);               // Check if point is inside a triangle
; RLAPI bool CheckCollisionPointPoly(Vector2 point, Vector2 *points, int pointCount);                      // Check if point is within a polygon described by array of vertices
; RLAPI bool CheckCollisionLines(Vector2 startPos1, Vector2 endPos1, Vector2 startPos2, Vector2 endPos2, Vector2 *collisionPoint); // Check the collision between two lines defined by two points each, returns collision point by reference
; RLAPI bool CheckCollisionPointLine(Vector2 point, Vector2 p1, Vector2 p2, int threshold);                // Check if point belongs to line created between two points [p1] and [p2] with defined margin in pixels [threshold]
; RLAPI Rectangle GetCollisionRec(Rectangle rec1, Rectangle rec2);                                         // Get collision rectangle for two rectangles collision
;
