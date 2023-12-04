(ns gen.overrides
  (:require
   [rayclj.memory :as memory]))

(def docstrings
  {"Matrix"
   (str
    "Matrix, 4x4 components, column major, OpenGL style, right-handed:EL"
    "  float m0, m4, m8,  m12 // Matrix first row (4 components):EL"
    "  float m1, m5, m9,  m13 // Matrix second row (4 components):EL"
    "  float m2, m6, m10, m14 // Matrix third row (4 components):EL"
    "  float m3, m7, m11, m15 // Matrix fourth row (4 components)")

   "Color"
   (str
    "Color, 4 components, R8G8B8A8 (32bit):EL"
    "  unsigned char r; // Color red value:EL"
    "  unsigned char g; // Color green value:EL"
    "  unsigned char b; // Color blue value:EL"
    "  unsigned char a; // Color alpha value")

   "rlVertexBuffer"
   "Dynamic vertex buffers (position + texcoords + colors + indices arrays)
  int elementCount // Number of elements in the buffer (QUADS)
  float * vertices // Vertex position (XYZ - 3 components per vertex) (shader-location = 0)
  float * texcoords // Vertex texture coordinates (UV - 2 components per vertex) (shader-location = 1)
  unsigned char * colors // Vertex colors (RGBA - 4 components per vertex) (shader-location = 3)
  unsigned int * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  unsigned int vaoId // OpenGL Vertex Array Object id
  unsigned int[4] vboId // OpenGL Vertex Buffer Objects id (4 types of vertex data)"})

(def struct-functions
  {:set-color
   ['(defn set-color
       "Color, 4 components, R8G8B8A8 (32bit)
  unsigned char r; // Color red value
  unsigned char g; // Color green value
  unsigned char b; // Color blue value
  unsigned char a; // Color alpha value"
       [:CARETMemorySegment seg {:keys [r g b a]}]
       (rayclj.raylib.Color/r$set seg (unchecked-byte r))
       (rayclj.raylib.Color/g$set seg (unchecked-byte g))
       (rayclj.raylib.Color/b$set seg (unchecked-byte b))
       (rayclj.raylib.Color/a$set seg (unchecked-byte a))
       seg)]

   :color
   ['(declare predefined-colors)
    '(defn color
       "Color, 4 components, R8G8B8A8 (32bit)
  unsigned char r; // Color red value
  unsigned char g; // Color green value
  unsigned char b; // Color blue value
  unsigned char a; // Color alpha value"
       [c]
       (cond
         (instance? MemorySegment c) c
         (keyword? c) (predefined-colors c)
         :else (set-color (memory/allocate (rayclj.raylib.Color/$LAYOUT)) c)))
    '(def predefined-colors
       (let [arena (memory/global-arena)
             layout (rayclj.raylib.Color/$LAYOUT)
             global-color (fn [c] (set-color (memory/allocate arena layout) c))]
         (into
          {}
          (map (fn [[k v]] [k (global-color v)]) renums/predefined-colors))))]

   :get-font
   ['(defn get-font
       "Font, font texture and GlyphInfo array data
  int baseSize // Base size (default chars height)
  int glyphCount // Number of glyph characters
  int glyphPadding // Padding around the glyph characters
  Texture2D texture // Texture atlas containing the glyphs
  Rectangle * recs // Rectangles in texture for the glyphs
  GlyphInfo * glyphs // Glyphs info data"
       [:CARETMemorySegment seg]
       (let [array-size (rayclj.raylib.Font/glyphCount$get seg)]
         {:base-size (rayclj.raylib.Font/baseSize$get seg)
          :glyph-count array-size
          :glyph-padding (rayclj.raylib.Font/glyphPadding$get seg)
          :texture (get-texture (rayclj.raylib.Font/texture$slice seg))
          :recs (get-rectangle-array (rayclj.raylib.Font/recs$get seg) array-size)
          :glyphs (get-glyph-info-array (rayclj.raylib.Font/glyphs$get seg) array-size)}))]

   :set-font
   ['(defn set-font
       "Font, font texture and GlyphInfo array data
  int baseSize // Base size (default chars height)
  int glyphCount // Number of glyph characters
  int glyphPadding // Padding around the glyph characters
  Texture2D texture // Texture atlas containing the glyphs
  Rectangle * recs // Rectangles in texture for the glyphs
  GlyphInfo * glyphs // Glyphs info data"
       ([:CARETMemorySegment seg {:keys [base-size glyph-count glyph-padding texture recs glyphs]}]
        (rayclj.raylib.Font/baseSize$set seg base-size)
        (rayclj.raylib.Font/glyphCount$set seg glyph-count)
        (rayclj.raylib.Font/glyphPadding$set seg glyph-padding)
        (set-texture (rayclj.raylib.Font/texture$slice seg) texture)
        (rayclj.raylib.Font/recs$set seg (rectangle-array recs glyph-count))
        (rayclj.raylib.Font/glyphs$set seg (glyph-info-array glyphs glyph-count))
        seg))]

   :font ['(defn font
             "Font, font texture and GlyphInfo array data
  int baseSize // Base size (default chars height)
  int glyphCount // Number of glyph characters
  int glyphPadding // Padding around the glyph characters
  Texture2D texture // Texture atlas containing the glyphs
  Rectangle * recs // Rectangles in texture for the glyphs
  GlyphInfo * glyphs // Glyphs info data"
             [v]
             (if (clojure.core/instance? MemorySegment v)
               v
               (set-font (memory/allocate (rayclj.raylib.Font/$LAYOUT)) v)))]

   :get-shader '[(defn get-shader
                   "Shader
  unsigned int id // Shader program id
  int * locs // Shader locations array (RL_MAX_SHADER_LOCATIONS)"
                   [:CARETMemorySegment seg]
                   {:id (rayclj.raylib.Shader/id$get seg),
                    :locs (get-int-array (rayclj.raylib.Shader/locs$get seg) gldefines/max-shader-locations)})]

   :set-shader '[(defn set-shader
                   "Shader
  unsigned int id // Shader program id
  int * locs // Shader locations array (RL_MAX_SHADER_LOCATIONS)"
                   [:CARETMemorySegment seg {:keys [id locs]}]
                   (rayclj.raylib.Shader/id$set seg id)
                   (rayclj.raylib.Shader/locs$set seg (memory/int-array locs gldefines/max-shader-locations))
                   seg)]

   ;;
   ;; rlgl overrides
   ;;

   :get-vertex-buffer
   ['(defn get-vertex-buffer
       "Dynamic vertex buffers (position + texcoords + colors + indices arrays)
  int elementCount // Number of elements in the buffer (QUADS)
  float * vertices // Vertex position (XYZ - 3 components per vertex) (shader-location = 0)
  float * texcoords // Vertex texture coordinates (UV - 2 components per vertex) (shader-location = 1)
  unsigned char * colors // Vertex colors (RGBA - 4 components per vertex) (shader-location = 3)
  unsigned int * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  unsigned int vaoId // OpenGL Vertex Array Object id
  unsigned int[4] vboId // OpenGL Vertex Buffer Objects id (4 types of vertex data)"
       [^MemorySegment seg]
       (let [element-count (rayclj.rlgl.rlVertexBuffer/elementCount$get seg)]
         {:elementCount element-count,
          :vertices (get-float-array (rayclj.rlgl.rlVertexBuffer/vertices$get seg) (* element-count 3)),
          :texcoords (get-float-array (rayclj.rlgl.rlVertexBuffer/texcoords$get seg) (* element-count 2)),
          :colors (get-byte-array (rayclj.rlgl.rlVertexBuffer/colors$get seg) (* element-count 4)),
          :indices (get-unsigned-int-array (rayclj.rlgl.rlVertexBuffer/indices$get seg) (* element-count 6)),
          :vaoId (rayclj.rlgl.rlVertexBuffer/vaoId$get seg),
          :vboId (get-unsigned-int-array (rayclj.rlgl.rlVertexBuffer/vboId$slice seg) 4)}))]

   :set-vertex-buffer
   ['(defn set-vertex-buffer
       "Dynamic vertex buffers (position + texcoords + colors + indices arrays)
  int elementCount // Number of elements in the buffer (QUADS)
  float * vertices // Vertex position (XYZ - 3 components per vertex) (shader-location = 0)
  float * texcoords // Vertex texture coordinates (UV - 2 components per vertex) (shader-location = 1)
  unsigned char * colors // Vertex colors (RGBA - 4 components per vertex) (shader-location = 3)
  unsigned int * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  unsigned int vaoId // OpenGL Vertex Array Object id
  unsigned int[4] vboId // OpenGL Vertex Buffer Objects id (4 types of vertex data)"
       [:CARETMemorySegment seg
        {:keys [elementCount vertices texcoords colors indices vaoId vboId]}]
       (rayclj.rlgl.rlVertexBuffer/elementCount$set seg elementCount)
       ; (set-float-array (rayclj.rlgl.rlVertexBuffer/vertices$get seg) vertices (* elementCount 3))
       (rayclj.rlgl.rlVertexBuffer/vertices$set seg (memory/float-array vertices (* elementCount 3)))
       ; (set-float-array (rayclj.rlgl.rlVertexBuffer/texcoords$get seg) texcoords (* elementCount 2))
       (rayclj.rlgl.rlVertexBuffer/texcoords$set seg (memory/float-array texcoords (* elementCount 2)))
       ; (set-byte-array (rayclj.rlgl.rlVertexBuffer/colors$get seg) colors (* elementCount 4))
       (rayclj.rlgl.rlVertexBuffer/colors$set seg (memory/byte-array colors (* elementCount 4)))
       ; (set-byte-array (rayclj.rlgl.rlVertexBuffer/indices$get seg) indices (* elementCount 6))
       (rayclj.rlgl.rlVertexBuffer/indices$set seg (memory/byte-array indices (* elementCount 6)))
       (rayclj.rlgl.rlVertexBuffer/vaoId$set seg vaoId)
       (set-unsigned-int-array (rayclj.rlgl.rlVertexBuffer/vboId$slice seg) vboId 4)
       seg)]

   :get-render-batch '[(defn get-render-batch
                         "rlRenderBatch type
  int bufferCount // Number of vertex buffers (multi-buffering support)
  int currentBuffer // Current buffer tracking in case of multi-buffering
  rlVertexBuffer * vertexBuffer // Dynamic buffer(s) for vertex data
  rlDrawCall * draws // Draw calls array, depends on textureId
  int drawCounter // Draw calls counter
  float currentDepth // Current depth value for next draw"
                         [:CARETMemorySegment seg]
                         (let [buffer-count (rayclj.rlgl.rlRenderBatch/bufferCount$get seg)]
                           {:bufferCount buffer-count,
                            :currentBuffer (rayclj.rlgl.rlRenderBatch/currentBuffer$get seg)
                            :vertexBuffer (get-vertex-buffer-array (rayclj.rlgl.rlRenderBatch/vertexBuffer$get seg) buffer-count)
                            :draws (get-draw-call-array (rayclj.rlgl.rlRenderBatch/draws$get seg) defines/default-batch-drawcalls)
                            :drawCounter (rayclj.rlgl.rlRenderBatch/drawCounter$get seg)
                            :currentDepth (rayclj.rlgl.rlRenderBatch/currentDepth$get seg)}))]

   :set-render-batch '[(defn set-render-batch
                         "rlRenderBatch type
  int bufferCount // Number of vertex buffers (multi-buffering support)
  int currentBuffer // Current buffer tracking in case of multi-buffering
  rlVertexBuffer * vertexBuffer // Dynamic buffer(s) for vertex data
  rlDrawCall * draws // Draw calls array, depends on textureId
  int drawCounter // Draw calls counter
  float currentDepth // Current depth value for next draw"
                         [:CARETMemorySegment seg
                          {:keys [bufferCount currentBuffer vertexBuffer draws drawCounter
                                  currentDepth]}]
                         (rayclj.rlgl.rlRenderBatch/bufferCount$set seg bufferCount)
                         (rayclj.rlgl.rlRenderBatch/currentBuffer$set seg currentBuffer)
                         ; (set-vertex-buffer-array (rayclj.rlgl.rlRenderBatch/vertexBuffer$get seg) vertexBuffer bufferCount)
                         (rayclj.rlgl.rlRenderBatch/vertexBuffer$set seg (vertex-buffer-array vertexBuffer bufferCount))
                         ; (set-draw-call-array (rayclj.rlgl.rlRenderBatch/draws$get seg) draws defines/default-batch-drawcalls)
                         (rayclj.rlgl.rlRenderBatch/draws$set seg (draw-call-array draws defines/default-batch-drawcalls))
                         (rayclj.rlgl.rlRenderBatch/drawCounter$set seg drawCounter)
                         (rayclj.rlgl.rlRenderBatch/currentDepth$set seg currentDepth)
                         seg)]})

(def functions
  {:draw-line-strip '[(defn draw-line-strip
                        "Draw lines sequence (using gl lines)
  [Vector2 * points, int pointCount, Color color] -> void"
                        ([points point-count color]
                         (raylib_h/DrawLineStrip (rstructs/vector2-array points point-count)
                                                 point-count
                                                 (rstructs/color color)))
                        ([points color]
                         (raylib_h/DrawLineStrip (rstructs/vector2-array points)
                                                 (count points)
                                                 (rstructs/color color))))]
   :draw-triangle-fan '[(defn draw-triangle-fan
                          "Draw a triangle fan defined by points (first vertex is the center)
  [Vector2 * points, int pointCount, Color color] -> void"
                          ([points point-count color]
                           (raylib_h/DrawTriangleFan (rstructs/vector2-array points point-count)
                                                     point-count
                                                     (rstructs/color color)))
                          ([points color]
                           (raylib_h/DrawTriangleFan (rstructs/vector2-array points)
                                                     (count points)
                                                     (rstructs/color color))))]
   :draw-triangle-strip '[(defn draw-triangle-strip
                            "Draw a triangle strip defined by points
  [Vector2 * points, int pointCount, Color color] -> void"
                            ([points point-count color]
                             (raylib_h/DrawTriangleStrip (rstructs/vector2-array points point-count)
                                                         point-count
                                                         (rstructs/color color)))
                            ([points color]
                             (raylib_h/DrawTriangleStrip (rstructs/vector2-array points)
                                                         (count points)
                                                         (rstructs/color color))))]

   :draw-spline-linear '[(defn draw-spline-linear
                           "Draw spline: Linear, minimum 2 points
  [Vector2 * points, int pointCount, float thick, Color color] -> void"
                           ([points point-count thick color]
                            (raylib_h/DrawSplineLinear (rstructs/vector2-array points point-count)
                                                       point-count
                                                       thick
                                                       (rstructs/color color)))
                           ([points thick color]
                            (raylib_h/DrawSplineLinear (rstructs/vector2-array points)
                                                       (count points)
                                                       thick
                                                       (rstructs/color color))))]

   :draw-spline-basis '[(defn draw-spline-basis
                          "Draw spline: B-Spline, minimum 4 points
  [Vector2 * points, int pointCount, float thick, Color color] -> void"
                          ([points point-count thick color]
                           (raylib_h/DrawSplineBasis (rstructs/vector2-array points point-count)
                                                     point-count
                                                     thick
                                                     (rstructs/color color)))
                          ([points thick color]
                           (raylib_h/DrawSplineBasis (rstructs/vector2-array points)
                                                     (count points)
                                                     thick
                                                     (rstructs/color color))))]

   :draw-spline-catmull-rom '[(defn draw-spline-catmull-rom
                                "Draw spline: Catmull-Rom, minimum 4 points
  [Vector2 * points, int pointCount, float thick, Color color] -> void"
                                ([points point-count thick color]
                                 (raylib_h/DrawSplineCatmullRom (rstructs/vector2-array points point-count)
                                                                point-count
                                                                thick
                                                                (rstructs/color color)))
                                ([points thick color]
                                 (raylib_h/DrawSplineCatmullRom (rstructs/vector2-array points)
                                                                (count points)
                                                                thick
                                                                (rstructs/color color))))]

   :draw-spline-bezier-quadratic '[(defn draw-spline-bezier-quadratic
                                     "Draw spline: Quadratic Bezier, minimum 3 points (1 control point): [p1, c2, p3, c4...]
  [Vector2 * points, int pointCount, float thick, Color color] -> void"
                                     ([points point-count thick color]
                                      (raylib_h/DrawSplineBezierQuadratic (rstructs/vector2-array points point-count)
                                                                          point-count
                                                                          thick
                                                                          (rstructs/color color)))
                                     ([points thick color]
                                      (raylib_h/DrawSplineBezierQuadratic (rstructs/vector2-array points)
                                                                          (count points)
                                                                          thick
                                                                          (rstructs/color color))))]
   :draw-spline-bezier-cubic '[(defn draw-spline-bezier-cubic
                                 "Draw spline: Cubic Bezier, minimum 4 points (2 control points): [p1, c2, c3, p4, c5, c6...]
  [Vector2 * points, int pointCount, float thick, Color color] -> void"
                                 ([points point-count thick color]
                                  (raylib_h/DrawSplineBezierCubic (rstructs/vector2 points point-count)
                                                                  point-count
                                                                  thick
                                                                  (rstructs/color color)))
                                 ([points thick color]
                                  (raylib_h/DrawSplineBezierCubic (rstructs/vector2 points)
                                                                  (count points)
                                                                  thick
                                                                  (rstructs/color color))))]

   :check-collision-point-poly? '[(defn check-collision-point-poly?
                                    "Check if point is within a polygon described by array of vertices
  [Vector2 point, Vector2 * points, int pointCount] -> bool"
                                    ([point points point-count]
                                     (raylib_h/CheckCollisionPointPoly (rstructs/vector2 point)
                                                                       (rstructs/vector2-array points point-count)
                                                                       point-count))
                                    ([point points]
                                     (raylib_h/CheckCollisionPointPoly (rstructs/vector2 point)
                                                                       (rstructs/vector2-array points)
                                                                       (count points))))]

   :draw-triangle-strip3d '[(defn draw-triangle-strip3d
                              "Draw a triangle strip defined by points
  [Vector3 * points, int pointCount, Color color] -> void"
                              ([points point-count color]
                               (raylib_h/DrawTriangleStrip3D (rstructs/vector3-array points point-count)
                                                             point-count
                                                             (rstructs/color color)))
                              ([points color]
                               (raylib_h/DrawTriangleStrip3D (rstructs/vector3-array points)
                                                             (count points)
                                                             (rstructs/color color))))]})
