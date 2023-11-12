(ns rayclj.rlgl.structs
  (:require [rayclj.arena :as rarena]
            [rayclj.arrays :refer [get-unsigned-int-array
                                   set-unsigned-int-array
                                   array-fn
                                   get-array-fn
                                   set-array-fn]])
  (:import
   [java.lang.foreign Arena MemorySegment]))

(set! *warn-on-reflection* true)

;;
;; RLGL Structures Definitions
;;

(defn get-matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  [^MemorySegment seg]
  [(rayclj.rlgl.Matrix/m0$get seg) (rayclj.rlgl.Matrix/m4$get seg)
   (rayclj.rlgl.Matrix/m8$get seg) (rayclj.rlgl.Matrix/m12$get seg)
   (rayclj.rlgl.Matrix/m1$get seg) (rayclj.rlgl.Matrix/m5$get seg)
   (rayclj.rlgl.Matrix/m9$get seg) (rayclj.rlgl.Matrix/m13$get seg)
   (rayclj.rlgl.Matrix/m2$get seg) (rayclj.rlgl.Matrix/m6$get seg)
   (rayclj.rlgl.Matrix/m10$get seg) (rayclj.rlgl.Matrix/m14$get seg)
   (rayclj.rlgl.Matrix/m3$get seg) (rayclj.rlgl.Matrix/m7$get seg)
   (rayclj.rlgl.Matrix/m11$get seg) (rayclj.rlgl.Matrix/m15$get seg)])

(defn set-matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  [^MemorySegment seg [m0 m4 m8 m12 m1 m5 m9 m13 m2 m6 m10 m14 m3 m7 m11 m15]]
  (rayclj.rlgl.Matrix/m0$set seg m0)
  (rayclj.rlgl.Matrix/m4$set seg m4)
  (rayclj.rlgl.Matrix/m8$set seg m8)
  (rayclj.rlgl.Matrix/m12$set seg m12)
  (rayclj.rlgl.Matrix/m1$set seg m1)
  (rayclj.rlgl.Matrix/m5$set seg m5)
  (rayclj.rlgl.Matrix/m9$set seg m9)
  (rayclj.rlgl.Matrix/m13$set seg m13)
  (rayclj.rlgl.Matrix/m2$set seg m2)
  (rayclj.rlgl.Matrix/m6$set seg m6)
  (rayclj.rlgl.Matrix/m10$set seg m10)
  (rayclj.rlgl.Matrix/m14$set seg m14)
  (rayclj.rlgl.Matrix/m3$set seg m3)
  (rayclj.rlgl.Matrix/m7$set seg m7)
  (rayclj.rlgl.Matrix/m11$set seg m11)
  (rayclj.rlgl.Matrix/m15$set seg m15)
  seg)

(defn matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  ([^Arena arena v]
   (set-matrix (.allocate arena (rayclj.rlgl.Matrix/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (matrix rarena/*current-arena* v))))

(def matrix-array (array-fn (rayclj.rlgl.Matrix/$LAYOUT) set-matrix))

(def get-matrix-array (get-array-fn (rayclj.rlgl.Matrix/$LAYOUT) get-matrix))

(def set-matrix-array (set-array-fn (rayclj.rlgl.Matrix/$LAYOUT) set-matrix))

(defn get-vertex-buffer
  "Dynamic vertex buffers (position + texcoords + colors + indices arrays)
  int elementCount // Number of elements in the buffer (QUADS)
  float * vertices // Vertex position (XYZ - 3 components per vertex) (shader-location = 0)
  float * texcoords // Vertex texture coordinates (UV - 2 components per vertex) (shader-location = 1)
  unsigned char * colors // Vertex colors (RGBA - 4 components per vertex) (shader-location = 3)
  #if defined(GRAPHICS_API_OPENGL_11) || defined(GRAPHICS_API_OPENunsigned int * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  unsigned int * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  #endif indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  #if defined(GRAPHICS_API_OPENGL_ES2) indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  unsigned short * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  #endif vaoId // OpenGL Vertex Array Object id
  unsigned int vaoId // OpenGL Vertex Array Object id
  unsigned int[4] vboId // OpenGL Vertex Buffer Objects id (4 types of vertex data)"
  [^MemorySegment seg]
  {:elementCount (rayclj.rlgl.rlVertexBuffer/elementCount$get seg),
   :vertices (rayclj.rlgl.rlVertexBuffer/vertices$get seg),
   :texcoords (rayclj.rlgl.rlVertexBuffer/texcoords$get seg),
   :colors (rayclj.rlgl.rlVertexBuffer/colors$get seg),
   :indices (rayclj.rlgl.rlVertexBuffer/indices$get seg),
   :vaoId (rayclj.rlgl.rlVertexBuffer/vaoId$get seg),
   :vboId (get-unsigned-int-array (rayclj.rlgl.rlVertexBuffer/vboId$slice seg)
                                  4)})

(defn set-vertex-buffer
  "Dynamic vertex buffers (position + texcoords + colors + indices arrays)
  int elementCount // Number of elements in the buffer (QUADS)
  float * vertices // Vertex position (XYZ - 3 components per vertex) (shader-location = 0)
  float * texcoords // Vertex texture coordinates (UV - 2 components per vertex) (shader-location = 1)
  unsigned char * colors // Vertex colors (RGBA - 4 components per vertex) (shader-location = 3)
  #if defined(GRAPHICS_API_OPENGL_11) || defined(GRAPHICS_API_OPENunsigned int * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  unsigned int * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  #endif indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  #if defined(GRAPHICS_API_OPENGL_ES2) indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  unsigned short * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  #endif vaoId // OpenGL Vertex Array Object id
  unsigned int vaoId // OpenGL Vertex Array Object id
  unsigned int[4] vboId // OpenGL Vertex Buffer Objects id (4 types of vertex data)"
  [^MemorySegment seg
   {:keys [elementCount vertices texcoords colors indices indices indices
           indices indices vaoId vaoId vboId]}]
  (rayclj.rlgl.rlVertexBuffer/elementCount$set seg elementCount)
  (rayclj.rlgl.rlVertexBuffer/vertices$set seg vertices)
  (rayclj.rlgl.rlVertexBuffer/texcoords$set seg texcoords)
  (rayclj.rlgl.rlVertexBuffer/colors$set seg colors)
  (rayclj.rlgl.rlVertexBuffer/indices$set seg indices)
  (rayclj.rlgl.rlVertexBuffer/indices$set seg indices)
  (rayclj.rlgl.rlVertexBuffer/indices$set seg indices)
  (rayclj.rlgl.rlVertexBuffer/indices$set seg indices)
  (rayclj.rlgl.rlVertexBuffer/indices$set seg indices)
  (rayclj.rlgl.rlVertexBuffer/vaoId$set seg vaoId)
  (rayclj.rlgl.rlVertexBuffer/vaoId$set seg vaoId)
  (set-unsigned-int-array (rayclj.rlgl.rlVertexBuffer/vboId$slice seg) vboId 4)
  seg)

(defn vertex-buffer
  "Dynamic vertex buffers (position + texcoords + colors + indices arrays)
  int elementCount // Number of elements in the buffer (QUADS)
  float * vertices // Vertex position (XYZ - 3 components per vertex) (shader-location = 0)
  float * texcoords // Vertex texture coordinates (UV - 2 components per vertex) (shader-location = 1)
  unsigned char * colors // Vertex colors (RGBA - 4 components per vertex) (shader-location = 3)
  #if defined(GRAPHICS_API_OPENGL_11) || defined(GRAPHICS_API_OPENunsigned int * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  unsigned int * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  #endif indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  #if defined(GRAPHICS_API_OPENGL_ES2) indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  unsigned short * indices // Vertex indices (in case vertex data comes indexed) (6 indices per quad)
  #endif vaoId // OpenGL Vertex Array Object id
  unsigned int vaoId // OpenGL Vertex Array Object id
  unsigned int[4] vboId // OpenGL Vertex Buffer Objects id (4 types of vertex data)"
  ([^Arena arena v]
   (set-vertex-buffer (.allocate arena (rayclj.rlgl.rlVertexBuffer/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (vertex-buffer rarena/*current-arena* v))))

(def vertex-buffer-array
  (array-fn (rayclj.rlgl.rlVertexBuffer/$LAYOUT) set-vertex-buffer))

(def get-vertex-buffer-array
  (get-array-fn (rayclj.rlgl.rlVertexBuffer/$LAYOUT) get-vertex-buffer))

(def set-vertex-buffer-array
  (set-array-fn (rayclj.rlgl.rlVertexBuffer/$LAYOUT) set-vertex-buffer))

(defn get-draw-call
  "of those state-change happens (this is done in core module)
  int mode // Drawing mode: LINES, TRIANGLES, QUADS
  int vertexCount // Number of vertex of the draw
  int vertexAlignment // Number of vertex required for index alignment (LINES, TRIANGLES)
  unsigned int textureId // Texture id to be used on the draw -> Use to create new draw call if changes"
  [^MemorySegment seg]
  {:mode (rayclj.rlgl.rlDrawCall/mode$get seg),
   :vertexCount (rayclj.rlgl.rlDrawCall/vertexCount$get seg),
   :vertexAlignment (rayclj.rlgl.rlDrawCall/vertexAlignment$get seg),
   :textureId (rayclj.rlgl.rlDrawCall/textureId$get seg)})

(defn set-draw-call
  "of those state-change happens (this is done in core module)
  int mode // Drawing mode: LINES, TRIANGLES, QUADS
  int vertexCount // Number of vertex of the draw
  int vertexAlignment // Number of vertex required for index alignment (LINES, TRIANGLES)
  unsigned int textureId // Texture id to be used on the draw -> Use to create new draw call if changes"
  [^MemorySegment seg {:keys [mode vertexCount vertexAlignment textureId]}]
  (rayclj.rlgl.rlDrawCall/mode$set seg mode)
  (rayclj.rlgl.rlDrawCall/vertexCount$set seg vertexCount)
  (rayclj.rlgl.rlDrawCall/vertexAlignment$set seg vertexAlignment)
  (rayclj.rlgl.rlDrawCall/textureId$set seg textureId)
  seg)

(defn draw-call
  "of those state-change happens (this is done in core module)
  int mode // Drawing mode: LINES, TRIANGLES, QUADS
  int vertexCount // Number of vertex of the draw
  int vertexAlignment // Number of vertex required for index alignment (LINES, TRIANGLES)
  unsigned int textureId // Texture id to be used on the draw -> Use to create new draw call if changes"
  ([^Arena arena v]
   (set-draw-call (.allocate arena (rayclj.rlgl.rlDrawCall/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (draw-call rarena/*current-arena* v))))

(def draw-call-array (array-fn (rayclj.rlgl.rlDrawCall/$LAYOUT) set-draw-call))

(def get-draw-call-array
  (get-array-fn (rayclj.rlgl.rlDrawCall/$LAYOUT) get-draw-call))

(def set-draw-call-array
  (set-array-fn (rayclj.rlgl.rlDrawCall/$LAYOUT) set-draw-call))

(defn get-render-batch
  "rlRenderBatch type
  int bufferCount // Number of vertex buffers (multi-buffering support)
  int currentBuffer // Current buffer tracking in case of multi-buffering
  rlVertexBuffer * vertexBuffer // Dynamic buffer(s) for vertex data
  rlDrawCall * draws // Draw calls array, depends on textureId
  int drawCounter // Draw calls counter
  float currentDepth // Current depth value for next draw"
  [^MemorySegment seg]
  {:bufferCount (rayclj.rlgl.rlRenderBatch/bufferCount$get seg),
   :currentBuffer (rayclj.rlgl.rlRenderBatch/currentBuffer$get seg),
   :vertexBuffer (rayclj.rlgl.rlRenderBatch/vertexBuffer$get seg),
   :draws (rayclj.rlgl.rlRenderBatch/draws$get seg),
   :drawCounter (rayclj.rlgl.rlRenderBatch/drawCounter$get seg),
   :currentDepth (rayclj.rlgl.rlRenderBatch/currentDepth$get seg)})

(defn set-render-batch
  "rlRenderBatch type
  int bufferCount // Number of vertex buffers (multi-buffering support)
  int currentBuffer // Current buffer tracking in case of multi-buffering
  rlVertexBuffer * vertexBuffer // Dynamic buffer(s) for vertex data
  rlDrawCall * draws // Draw calls array, depends on textureId
  int drawCounter // Draw calls counter
  float currentDepth // Current depth value for next draw"
  [^MemorySegment seg
   {:keys [bufferCount currentBuffer vertexBuffer draws drawCounter
           currentDepth]}]
  (rayclj.rlgl.rlRenderBatch/bufferCount$set seg bufferCount)
  (rayclj.rlgl.rlRenderBatch/currentBuffer$set seg currentBuffer)
  (rayclj.rlgl.rlRenderBatch/vertexBuffer$set seg vertexBuffer)
  (rayclj.rlgl.rlRenderBatch/draws$set seg draws)
  (rayclj.rlgl.rlRenderBatch/drawCounter$set seg drawCounter)
  (rayclj.rlgl.rlRenderBatch/currentDepth$set seg currentDepth)
  seg)

(defn render-batch
  "rlRenderBatch type
  int bufferCount // Number of vertex buffers (multi-buffering support)
  int currentBuffer // Current buffer tracking in case of multi-buffering
  rlVertexBuffer * vertexBuffer // Dynamic buffer(s) for vertex data
  rlDrawCall * draws // Draw calls array, depends on textureId
  int drawCounter // Draw calls counter
  float currentDepth // Current depth value for next draw"
  ([^Arena arena v]
   (set-render-batch (.allocate arena (rayclj.rlgl.rlRenderBatch/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (render-batch rarena/*current-arena* v))))

(def render-batch-array
  (array-fn (rayclj.rlgl.rlRenderBatch/$LAYOUT) set-render-batch))

(def get-render-batch-array
  (get-array-fn (rayclj.rlgl.rlRenderBatch/$LAYOUT) get-render-batch))

(def set-render-batch-array
  (set-array-fn (rayclj.rlgl.rlRenderBatch/$LAYOUT) set-render-batch))

