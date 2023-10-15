(ns raylib.shapes
  "Basic Shapes Drawing Functions (Module: shapes)"
  (:require [raylib.arena :as rarena]
            [raylib.structs :as rstructs])
  (:import [raylib raylib_h]
           [java.lang.foreign Arena]))

(defn set-shapes-texture!
  "Set texture and rectangle to be used on shapes drawing
   NOTE: It can be useful when using basic shapes and one single font,
   defining a font char white rectangle would allow drawing everything in a single draw call"
  ([^Arena arena texture source] (raylib_h/SetShapesTexture (rstructs/texture arena texture) source))
  ([texture source] (raylib_h/SetShapesTexture (rstructs/texture texture) source)))

(defn draw-pixel!
  "Draw a pixel"
  ([^Arena arena pos-x pos-y color] (raylib_h/DrawPixel pos-x pos-y (rstructs/color arena color)))
  ([pos-x pos-y color] (raylib_h/DrawPixel pos-x pos-y (rstructs/color color))))

(defn draw-pixel-v!
  "Draw a pixel (Vector version)"
  ([^Arena arena position-v color]
   (raylib_h/DrawPixelV (rstructs/vector2 arena position-v) (rstructs/color arena color)))
  ([position-v color]
   (raylib_h/DrawPixelV (rstructs/vector2 position-v) (rstructs/color color))))

(defn draw-line!
  "Draw a line"
  ([^Arena arena start-pos-x start-pos-y end-pos-x end-pos-y color]
   (raylib_h/DrawLine
    start-pos-x
    start-pos-y
    end-pos-x
    end-pos-y
    (rstructs/color arena color)))
  ([start-pos-x start-pos-y end-pos-x end-pos-y color]
   (raylib_h/DrawLine
    start-pos-x
    start-pos-y
    end-pos-x
    end-pos-y
    (rstructs/color color))))

(defn draw-line-v!
  "Draw a line (Vector version)"
  ([^Arena arena start-pos end-pos color]
   (raylib_h/DrawLineV
    (rstructs/vector2 arena start-pos)
    (rstructs/vector2 arena end-pos)
    (rstructs/color arena color)))
  ([start-pos end-pos color]
   (raylib_h/DrawLineV
    (rstructs/vector2 start-pos) (rstructs/vector2 end-pos) (rstructs/color color))))

(defn draw-line-ex!
  "Draw a line defining thickness"
  ([^Arena arena start-pos end-pos thick color]
   (raylib_h/DrawLineEx
    (rstructs/vector2 arena start-pos)
    (rstructs/vector2 arena end-pos)
    thick
    (rstructs/color arena color)))
  ([start-pos end-pos thick color]
   (raylib_h/DrawLineEx
    (rstructs/vector2 start-pos) (rstructs/vector2 end-pos) thick (rstructs/color color))))

(defn draw-line-bezier!
  "Draw a line using cubic-bezier curves in-out"
  ([^Arena arena start-pos end-pos thick color]
   (raylib_h/DrawLineBezier
    (rstructs/vector2 arena start-pos)
    (rstructs/vector2 arena end-pos)
    thick (rstructs/color arena color)))
  ([start-pos end-pos thick color]
   (raylib_h/DrawLineBezier
    (rstructs/vector2 start-pos)
    (rstructs/vector2 end-pos)
    thick (rstructs/color color))))

(defn draw-line-bezier-quad!
  "Draw line using quadratic bezier curves with a control point"
  ([^Arena arena start-pos end-pos control-pos thick color]
   (raylib_h/DrawLineBezierQuad
    (rstructs/vector2 arena start-pos)
    (rstructs/vector2 arena end-pos)
    (rstructs/vector2 arena control-pos)
    thick
    (rstructs/color arena color)))
  ([start-pos end-pos control-pos thick color]
   (raylib_h/DrawLineBezierQuad
    (rstructs/vector2 start-pos)
    (rstructs/vector2 end-pos)
    (rstructs/vector2 control-pos)
    thick
    (rstructs/color color))))

(defn draw-line-bezier-cubic!
  "Draw line using cubic bezier curves with 2 control points"
  ([^Arena arena start-pos end-pos start-control-pos end-control-pos thick color]
   (raylib_h/DrawLineBezierCubic
    (rstructs/vector2 arena start-pos)
    (rstructs/vector2 arena end-pos)
    (rstructs/vector2 arena start-control-pos)
    (rstructs/vector2 arena end-control-pos)
    thick
    (rstructs/color arena color)))
  ([start-pos end-pos start-control-pos end-control-pos thick color]
   (raylib_h/DrawLineBezierCubic
    (rstructs/vector2 start-pos)
    (rstructs/vector2 end-pos)
    (rstructs/vector2 start-control-pos)
    (rstructs/vector2 end-control-pos)
    thick
    (rstructs/color color))))

(defn draw-line-strip!
  "Draw lines sequence"
  ([^Arena arena points point-count color]
   (raylib_h/DrawLineStrip
    (rstructs/vector2-array arena points)
    point-count
    (rstructs/color arena color)))
  ([points-as-vec color]
   (raylib_h/DrawLineStrip
    (rstructs/vector2-array points-as-vec)
    (count points-as-vec)
    (rstructs/color color))))

(defn draw-circle!
  "Draw a color-filled circle"
  ([^Arena arena center-x center-y radius color]
   (raylib_h/DrawCircle center-x center-y radius (rstructs/color arena color)))
  ([center-x center-y radius color]
   (raylib_h/DrawCircle center-x center-y radius (rstructs/color color))))

(defn draw-circle-sector!
  "Draw a piece of a circle"
  ([^Arena arena center-v radius start-angle end-angle segments color]
   (raylib_h/DrawCircleSector
    (rstructs/vector2 arena center-v)
    radius
    start-angle
    end-angle
    segments
    (rstructs/color arena color)))
  ([center-v radius start-angle end-angle segments color]
   (raylib_h/DrawCircleSector
    (rstructs/vector2 center-v)
    radius
    start-angle
    end-angle
    segments
    (rstructs/color color))))

(defn draw-circle-sector-lines!
  "Draw circle sector outline"
  ([^Arena arena center-v radius start-angle end-angle segments color]
   (raylib_h/DrawCircleSectorLines
    (rstructs/vector2 arena center-v)
    radius
    start-angle
    end-angle
    segments
    (rstructs/color arena color)))
  ([center-v radius start-angle end-angle segments color]
   (raylib_h/DrawCircleSectorLines
    (rstructs/vector2 center-v)
    radius
    start-angle
    end-angle
    segments
    (rstructs/color color))))

(defn draw-circle-gradient!
  "Draw a gradient-filled circle"
  ([^Arena arena center-x center-y radius color1 color2]
   (raylib_h/DrawCircleGradient
    center-x
    center-y
    radius
    (rstructs/color arena color1)
    (rstructs/color arena color2)))
  ([center-x center-y radius color1 color2]
   (raylib_h/DrawCircleGradient
    center-x
    center-y
    radius
    (rstructs/color color1)
    (rstructs/color color2))))

(defn draw-circle-v!
  "Draw a color-filled circle (Vector version)"
  ([^Arena arena center-v radius color]
   (raylib_h/DrawCircleV
    (rstructs/vector2 arena center-v) radius (rstructs/color arena color)))
  ([center-v radius color]
   (raylib_h/DrawCircleV
    (rstructs/vector2 center-v) radius (rstructs/color color))))

(defn draw-circle-lines!
  "Draw circle outline"
  ([^Arena arena center-x center-y radius color]
   (raylib_h/DrawCircleLines center-x center-y radius (rstructs/color arena color)))
  ([center-x center-y radius color]
   (raylib_h/DrawCircleLines center-x center-y radius (rstructs/color color))))

(defn draw-ellipse!
  "Draw ellipse"
  ([^Arena arena center-x center-y radius-h radius-v color]
   (raylib_h/DrawEllipse center-x center-y radius-h radius-v (rstructs/color arena color)))
  ([center-x center-y radius-h radius-v color]
   (raylib_h/DrawEllipse center-x center-y radius-h radius-v (rstructs/color color))))

(defn draw-ellipse-lines!
  "Draw ellipse outline"
  ([^Arena arena center-x center-y radius-h radius-v color]
   (raylib_h/DrawEllipseLines center-x center-y radius-h radius-v (rstructs/color arena color)))
  ([center-x center-y radius-h radius-v color]
   (raylib_h/DrawEllipseLines center-x center-y radius-h radius-v (rstructs/color color))))

(defn draw-ring!
  "Draw ring"
  ([^Arena arena center-v inner-radius outer-radius start-angle end-angle segments color]
   (raylib_h/DrawRing
    (rstructs/vector2 arena center-v)
    inner-radius
    outer-radius
    start-angle
    end-angle
    segments
    (rstructs/color arena color)))
  ([center-v inner-radius outer-radius start-angle end-angle segments color]
   (raylib_h/DrawRing
    (rstructs/vector2 center-v)
    inner-radius
    outer-radius
    start-angle
    end-angle
    segments
    (rstructs/color color))))

(defn draw-ring-lines!
  "Draw ring outline"
  ([^Arena arena center-v inner-radius outer-radius start-angle end-angle segments color]
   (raylib_h/DrawRingLines
    (rstructs/vector2 arena center-v)
    inner-radius
    outer-radius
    start-angle
    end-angle
    segments
    (rstructs/color arena color)))
  ([center-v inner-radius outer-radius start-angle end-angle segments color]
   (raylib_h/DrawRingLines
    (rstructs/vector2 center-v)
    inner-radius
    outer-radius
    start-angle
    end-angle
    segments
    (rstructs/color color))))

(defn draw-rectangle!
  "Draw a color-filled rectangle"
  ([^Arena arena pos-x pos-y width height color]
   (raylib_h/DrawRectangle pos-x pos-y width height (rstructs/color arena color)))
  ([pos-x pos-y width height color]
   (raylib_h/DrawRectangle pos-x pos-y width height (rstructs/color color))))

(defn draw-rectangle-v!
  "Draw a color-filled rectangle (Vector version)"
  ([^Arena arena position size color]
   (raylib_h/DrawRectangleV (rstructs/vector2 arena position) size (rstructs/color arena color)))
  ([position size color]
   (raylib_h/DrawRectangleV (rstructs/vector2 position) size (rstructs/color color))))

(defn draw-rectangle-rec!
  "Draw a color-filled rectangle"
  ([^Arena arena rectangle color]
   (raylib_h/DrawRectangleRec (rstructs/rectangle arena rectangle) (rstructs/color arena color)))
  ([rectangle color]
   (raylib_h/DrawRectangleRec (rstructs/rectangle rectangle) (rstructs/color color))))

(defn draw-rectangle-pro!
  "Draw a color-filled rectangle with pro parameters"
  ([^Arena arena rectangle origin rotation color]
   (raylib_h/DrawRectanglePro
    (rstructs/rectangle arena rectangle) origin rotation (rstructs/color arena color)))
  ([rectangle origin rotation color]
   (raylib_h/DrawRectanglePro
    (rstructs/rectangle rectangle) origin rotation (rstructs/color color))))

(defn draw-rectangle-gradient-v!
  "Draw a vertical-gradient-filled rectangle"
  ([^Arena arena pos-x pos-y width height color1 color2]
   (raylib_h/DrawRectangleGradientV
    pos-x pos-y width height (rstructs/color arena color1) (rstructs/color arena color2)))
  ([pos-x pos-y width height color1 color2]
   (raylib_h/DrawRectangleGradientV
    pos-x pos-y width height (rstructs/color color1) (rstructs/color color2))))

(defn draw-rectangle-gradient-h!
  "Draw a horizontal-gradient-filled rectangle"
  ([^Arena arena pos-x pos-y width height color1 color2]
   (raylib_h/DrawRectangleGradientH
    pos-x pos-y width height (rstructs/color arena color1) (rstructs/color arena color2)))
  ([pos-x pos-y width height color1 color2]
   (raylib_h/DrawRectangleGradientH
    pos-x pos-y width height (rstructs/color color1) (rstructs/color color2))))

(defn draw-rectangle-gradient-ex!
  "Draw a gradient-filled rectangle with custom vertex colors"
  ([^Arena arena rectangle color1 color2 color3 color4]
   (raylib_h/DrawRectangleGradientEx
    (rstructs/rectangle arena rectangle)
    (rstructs/color arena color1)
    (rstructs/color arena color2)
    (rstructs/color arena color3)
    (rstructs/color arena color4)))
  ([rectangle color1 color2 color3 color4]
   (raylib_h/DrawRectangleGradientEx
    (rstructs/rectangle rectangle)
    (rstructs/color color1)
    (rstructs/color color2)
    (rstructs/color color3)
    (rstructs/color color4))))

(defn draw-rectangle-lines!
  "Draw rectangle outline"
  ([^Arena arena pos-x pos-y width height color]
   (raylib_h/DrawRectangleLines pos-x pos-y width height (rstructs/color arena color)))
  ([pos-x pos-y width height color]
   (raylib_h/DrawRectangleLines pos-x pos-y width height (rstructs/color color))))

(defn draw-rectangle-lines-ex!
  "Draw rectangle outline with extended parameters"
  ([^Arena arena rectangle line-thick color]
   (raylib_h/DrawRectangleLinesEx
    (rstructs/rectangle arena rectangle) line-thick (rstructs/color arena color)))
  ([rectangle line-thick color]
   (raylib_h/DrawRectangleLinesEx
    (rstructs/rectangle rectangle) line-thick (rstructs/color color))))

(defn draw-rectangle-rounded!
  "Draw rectangle with rounded edges"
  ([^Arena arena rectangle roundness segments color]
   (raylib_h/DrawRectangleRounded
    (rstructs/rectangle arena rectangle) roundness segments (rstructs/color arena color)))
  ([rectangle roundness segments color]
   (raylib_h/DrawRectangleRounded
    (rstructs/rectangle rectangle) roundness segments (rstructs/color color))))

(defn draw-rectangle-rounded-lines!
  "Draw rectangle with rounded edges outline"
  ([^Arena arena rectangle roundness segments line-thick color]
   (raylib_h/DrawRectangleRoundedLines
    (rstructs/rectangle arena rectangle)
    roundness
    segments
    line-thick
    (rstructs/color arena color)))
  ([rectangle roundness segments line-thick color]
   (raylib_h/DrawRectangleRoundedLines
    (rstructs/rectangle rectangle)
    roundness
    segments
    line-thick
    (rstructs/color color))))

(defn draw-triangle!
  "Draw a color-filled triangle (vertex in counter-clockwise order!)"
  ([^Arena arena v1 v2 v3 color]
   (raylib_h/DrawTriangle
    (rstructs/vector2 arena v1)
    (rstructs/vector2 arena v2)
    (rstructs/vector2 arena v3)
    (rstructs/color color)))
  ([v1 v2 v3 color]
   (raylib_h/DrawTriangle
    (rstructs/vector2 v1)
    (rstructs/vector2 v2)
    (rstructs/vector2 v3)
    (rstructs/color color))))

(defn draw-triangle-lines!
  "Draw triangle outline (vertex in counter-clockwise order!)"
  ([^Arena arena v1 v2 v3 color]
   (raylib_h/DrawTriangleLines
    (rstructs/vector2 arena v1)
    (rstructs/vector2 arena v2)
    (rstructs/vector2 arena v3)
    (rstructs/color color)))
  ([v1 v2 v3 color]
   (raylib_h/DrawTriangleLines
    (rstructs/vector2 v1)
    (rstructs/vector2 v2)
    (rstructs/vector2 v3)
    (rstructs/color color))))

(defn draw-triangle-fan!
  "Draw a triangle fan defined by points (first vertex is the center)"
  ([^Arena arena points point-count color]
   (raylib_h/DrawTriangleFan
    (rstructs/vector2-array arena points)
    point-count
    (rstructs/color arena color)))
  ([points-as-vectors color]
   (raylib_h/DrawTriangleFan
    (rstructs/vector2-array points-as-vectors)
    (count points-as-vectors)
    (rstructs/color color))))

(defn draw-triangle-strip!
  "Draw a triangle strip defined by points"
  ([^Arena arena points point-count color]
   (raylib_h/DrawTriangleStrip
    (rstructs/vector2-array arena points)
    point-count
    (rstructs/color arena color)))
  ([points-as-vec color]
   (raylib_h/DrawTriangleStrip
    (rstructs/vector2-array points-as-vec)
    (count points-as-vec)
    (rstructs/color color))))

(defn draw-poly!
  "Draw a regular polygon (Vector version)"
  ([^Arena arena center-v sides radius rotation color]
   (raylib_h/DrawPoly
    (rstructs/vector2 arena center-v) sides radius rotation (rstructs/color arena color)))
  ([center-v sides radius rotation color]
   (raylib_h/DrawPoly
    (rstructs/vector2 center-v) sides radius rotation (rstructs/color color))))

(defn draw-poly-lines!
  "Draw a polygon outline of n sides"
  ([^Arena arena center-v sides radius rotation color]
   (raylib_h/DrawPolyLines
    (rstructs/vector2 arena center-v) sides radius rotation (rstructs/color arena color)))
  ([center-v sides radius rotation color]
   (raylib_h/DrawPolyLines
    (rstructs/vector2 center-v) sides radius rotation (rstructs/color color))))

(defn draw-poly-lines-ex!
  "Draw a polygon outline of n sides with extended parameters"
  ([^Arena arena center-v sides radius rotation line-thick color]
   (raylib_h/DrawPolyLinesEx
    (rstructs/vector2 arena center-v) sides radius rotation line-thick (rstructs/color arena color)))
  ([center-v sides radius rotation line-thick color]
   (raylib_h/DrawPolyLinesEx
    (rstructs/vector2 center-v) sides radius rotation line-thick (rstructs/color color))))

;; Basic shapes collision detection functions
(defn check-collision-recs?
  "Check collision between two rectangles"
  ([^Arena arena rectangle1 rectangle2]
   (raylib_h/CheckCollisionRecs (rstructs/rectangle arena rectangle1) (rstructs/rectangle arena rectangle2)))
  ([rectangle1 rectangle2]
   (raylib_h/CheckCollisionRecs (rstructs/rectangle rectangle1) (rstructs/rectangle rectangle2))))

(defn check-collision-circles?
  "Check collision between two circles"
  ([^Arena arena center1-v radius1 center2-v radius2]
   (raylib_h/CheckCollisionCircles
    (rstructs/vector2 arena center1-v)
    radius1
    (rstructs/vector2 arena center2-v)
    radius2))
  ([center1-v radius1 center2-v radius2]
   (raylib_h/CheckCollisionCircles
    (rstructs/vector2 center1-v)
    radius1
    (rstructs/vector2 center2-v)
    radius2)))

(defn check-collision-circle-rec?
  "Check collision between circle and rectangle"
  ([^Arena arena center-v radius rectangle]
   (raylib_h/CheckCollisionCircleRec
    (rstructs/vector2 arena center-v)
    radius
    (rstructs/rectangle arena rectangle)))
  ([center-v radius rectangle]
   (raylib_h/CheckCollisionCircleRec
    (rstructs/vector2 center-v)
    radius
    (rstructs/rectangle rectangle))))

(defn check-collision-point-rec?
  "Check if point is inside rectangle"
  ([^Arena arena point-v rectangle]
   (raylib_h/CheckCollisionPointRec (rstructs/vector2 arena point-v) (rstructs/rectangle arena rectangle)))
  ([point-v rectangle]
   (raylib_h/CheckCollisionPointRec (rstructs/vector2 point-v) (rstructs/rectangle rectangle))))

(defn check-collision-point-circle?
  "Check if point is inside circle"
  ([^Arena arena point-v center-v radius]
   (raylib_h/CheckCollisionPointCircle (rstructs/vector2 arena point-v) (rstructs/vector2 arena center-v) radius))
  ([point-v center-v radius]
   (raylib_h/CheckCollisionPointCircle (rstructs/vector2 point-v) (rstructs/vector2 center-v) radius)))

(defn check-collision-point-triangle?
  "Check if point is inside a triangle"
  ([^Arena arena point-v p1-v p2-v p3-v]
   (raylib_h/CheckCollisionPointTriangle
    (rstructs/vector2 arena point-v)
    (rstructs/vector2 arena p1-v)
    (rstructs/vector2 arena p2-v)
    (rstructs/vector2 arena p3-v)))
  ([point-v p1-v p2-v p3-v]
   (raylib_h/CheckCollisionPointTriangle
    (rstructs/vector2 point-v)
    (rstructs/vector2 p1-v)
    (rstructs/vector2 p2-v)
    (rstructs/vector2 p3-v))))

(defn check-collision-point-poly?
  "Check if point is within a polygon described by array of vertices"
  ([^Arena arena point-v points points-count]
   (raylib_h/CheckCollisionPointPoly
    (rstructs/vector2 arena point-v)
    (rstructs/vector2-array arena points)
    points-count))
  ([point-v points-as-vec]
   (raylib_h/CheckCollisionPointPoly
    (rstructs/vector2 point-v)
    (rstructs/vector2-array points-as-vec)
    (count points-as-vec))))

;; TODO: Test return by referense
; RLAPI bool CheckCollisionLines(Vector2 startPos1, Vector2 endPos1, Vector2 startPos2, Vector2 endPos2, Vector2 *collisionPoint); // 
(defn check-collision-lines!
  "Check the collision between two lines defined by two points each, returns collision point by reference"
  ([^Arena arena start-pos1-v end-pos1-v start-pos2-v end-pos2-v]
   (let [collision-point-seg (rstructs/vector2 arena [0 0])
         collision? (raylib_h/CheckCollisionLines
                     (rstructs/vector2 arena start-pos1-v)
                     (rstructs/vector2 arena end-pos1-v)
                     (rstructs/vector2 arena start-pos2-v)
                     (rstructs/vector2 arena end-pos2-v)
                     collision-point-seg)
         collision-point (rstructs/vector2 arena collision-point-seg)]
     [collision? collision-point])) ; returning a tuple
  ([start-pos1-v end-pos1-v start-pos2-v end-pos2-v]
   (check-collision-lines! rarena/*current-arena* start-pos1-v end-pos1-v start-pos2-v end-pos2-v)))

; RLAPI bool CheckCollisionPointLine(Vector2 point, Vector2 p1, Vector2 p2, int threshold);
(defn check-collision-line?
  "Check if point belongs to line created between two points [p1] and [p2] with defined margin in pixels [threshold]"
  ([^Arena arena point-v p1-v p2-v threshold]
   (raylib_h/CheckCollisionPointLine
    (rstructs/vector2 arena point-v)
    (rstructs/vector2 arena p1-v)
    (rstructs/vector2 arena p2-v)
    threshold))
  ([point-v p1-v p2-v threshold]
   (raylib_h/CheckCollisionPointLine
    (rstructs/vector2 point-v)
    (rstructs/vector2 p1-v)
    (rstructs/vector2 p2-v)
    threshold)))

(defn get-collision-rec!
  "Get collision rectangle for two rectangles collision"
  ([^Arena arena rectangle1 rectangle2]
   (raylib_h/GetCollisionRec
    arena
    (rstructs/rectangle arena rectangle1)
    (rstructs/rectangle arena rectangle2)))
  ([rectangle1 rectangle2]
   (raylib_h/GetCollisionRec
    rarena/*current-arena*
    (rstructs/rectangle rectangle1)
    (rstructs/rectangle rectangle2))))
