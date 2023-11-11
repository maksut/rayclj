(ns rlgl.structs
  (:require [raylib.arena :as rarena]
            [rlgl.enums :as renums])
  (:import
   [java.lang.foreign Arena MemoryLayout MemorySegment ValueLayout]))

(set! *warn-on-reflection* true)

;;
;; Utility functions
;;

(defn get-string [^MemorySegment seg]
  (.getUtf8String seg 0))

(defn set-string [^MemorySegment seg val max-len]
  (when (> (count val) max-len)
    (throw (ex-info "String too long" {:val val :max-len max-len})))
  (.setUtf8String seg 0 val))

(defn string
  ([str] (.allocateUtf8String rarena/*current-arena* str))
  ([^Arena arena str] (.allocateUtf8String arena str)))

(defn get-unsigned-int-array [^MemorySegment seg size]
  (let [layout (ValueLayout/JAVA_INT)]
    (mapv (fn [i] (.getAtIndex seg layout (long i))) (range size))))

(defn set-unsigned-int-array
  [^MemorySegment seg elems max-size]
  (when (> (count elems) max-size)
    (throw (ex-info "Int array too long" {:elems elems :max-size max-size})))
  (let [layout (ValueLayout/JAVA_INT)]
    (dorun
     (map-indexed (fn [i elem] (.setAtIndex seg layout (long i) (int elem))) elems)))
  seg)

;; TODO: move these utility fns into an impl ns
(defn as-slice
  [^MemorySegment seg index element-size]
  (.asSlice seg (* index element-size)))

(defn get-array-fn
  [^MemoryLayout elem-layout get-fn]
  (let [element-size (.byteSize elem-layout)]
    (fn [^MemorySegment seg size]
      (mapv (fn [i] (get-fn (as-slice seg i element-size))) (range size)))))

(defn set-array-fn
  [^MemoryLayout elem-layout set-fn]
  (let [element-size (.byteSize elem-layout)]
    (fn set-array
      ([^MemorySegment seg elems max-size]
       (when (> (count elems) max-size) (throw (ex-info "Array too long" {:elems elems :max-size max-size})))
       (set-array seg elems))
      ([^MemorySegment seg elems]
       (dorun
        (map-indexed (fn [i elem] (set-fn (as-slice seg i element-size) elem)) elems))
       seg))))

(defn array-fn
  [^MemoryLayout elem-layout set-fn]
  (let [set-array (set-array-fn elem-layout set-fn)]
    (fn to-array
      ([^Arena arena elems]
       (let [array-layout (MemoryLayout/sequenceLayout (count elems) elem-layout)
             seg (.allocate arena array-layout)]
         (set-array seg elems)))
      ([elems]
       (if (instance? MemorySegment elems)
         elems
         (to-array rarena/*current-arena* elems))))))

;;
;; Structures Definitions
;;

(defn get-matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  [^MemorySegment seg]
  [(rlgl.Matrix/m0$get seg) (rlgl.Matrix/m4$get seg) (rlgl.Matrix/m8$get seg)
   (rlgl.Matrix/m12$get seg) (rlgl.Matrix/m1$get seg) (rlgl.Matrix/m5$get seg)
   (rlgl.Matrix/m9$get seg) (rlgl.Matrix/m13$get seg) (rlgl.Matrix/m2$get seg)
   (rlgl.Matrix/m6$get seg) (rlgl.Matrix/m10$get seg) (rlgl.Matrix/m14$get seg)
   (rlgl.Matrix/m3$get seg) (rlgl.Matrix/m7$get seg) (rlgl.Matrix/m11$get seg)
   (rlgl.Matrix/m15$get seg)])

(defn set-matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  [^MemorySegment seg [m0 m4 m8 m12 m1 m5 m9 m13 m2 m6 m10 m14 m3 m7 m11 m15]]
  (rlgl.Matrix/m0$set seg m0)
  (rlgl.Matrix/m4$set seg m4)
  (rlgl.Matrix/m8$set seg m8)
  (rlgl.Matrix/m12$set seg m12)
  (rlgl.Matrix/m1$set seg m1)
  (rlgl.Matrix/m5$set seg m5)
  (rlgl.Matrix/m9$set seg m9)
  (rlgl.Matrix/m13$set seg m13)
  (rlgl.Matrix/m2$set seg m2)
  (rlgl.Matrix/m6$set seg m6)
  (rlgl.Matrix/m10$set seg m10)
  (rlgl.Matrix/m14$set seg m14)
  (rlgl.Matrix/m3$set seg m3)
  (rlgl.Matrix/m7$set seg m7)
  (rlgl.Matrix/m11$set seg m11)
  (rlgl.Matrix/m15$set seg m15)
  seg)

(defn matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  ([^Arena arena v] (set-matrix (.allocate arena (rlgl.Matrix/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (matrix rarena/*current-arena* v))))

(def matrix-array (array-fn (rlgl.Matrix/$LAYOUT) set-matrix))

(def get-matrix-array (get-array-fn (rlgl.Matrix/$LAYOUT) get-matrix))

(def set-matrix-array (set-array-fn (rlgl.Matrix/$LAYOUT) set-matrix))

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
  {:elementCount (rlgl.rlVertexBuffer/elementCount$get seg),
   :vertices (rlgl.rlVertexBuffer/vertices$get seg),
   :texcoords (rlgl.rlVertexBuffer/texcoords$get seg),
   :colors (rlgl.rlVertexBuffer/colors$get seg),
   :indices (rlgl.rlVertexBuffer/indices$get seg),
   :vaoId (rlgl.rlVertexBuffer/vaoId$get seg),
   :vboId (get-unsigned-int-array (rlgl.rlVertexBuffer/vboId$slice seg) 4)})

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
  (rlgl.rlVertexBuffer/elementCount$set seg elementCount)
  (rlgl.rlVertexBuffer/vertices$set seg vertices)
  (rlgl.rlVertexBuffer/texcoords$set seg texcoords)
  (rlgl.rlVertexBuffer/colors$set seg colors)
  (rlgl.rlVertexBuffer/indices$set seg indices)
  (rlgl.rlVertexBuffer/indices$set seg indices)
  (rlgl.rlVertexBuffer/indices$set seg indices)
  (rlgl.rlVertexBuffer/indices$set seg indices)
  (rlgl.rlVertexBuffer/indices$set seg indices)
  (rlgl.rlVertexBuffer/vaoId$set seg vaoId)
  (rlgl.rlVertexBuffer/vaoId$set seg vaoId)
  (set-unsigned-int-array (rlgl.rlVertexBuffer/vboId$slice seg) vboId 4)
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
   (set-vertex-buffer (.allocate arena (rlgl.rlVertexBuffer/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (vertex-buffer rarena/*current-arena* v))))

(def vertex-buffer-array
  (array-fn (rlgl.rlVertexBuffer/$LAYOUT) set-vertex-buffer))

(def get-vertex-buffer-array
  (get-array-fn (rlgl.rlVertexBuffer/$LAYOUT) get-vertex-buffer))

(def set-vertex-buffer-array
  (set-array-fn (rlgl.rlVertexBuffer/$LAYOUT) set-vertex-buffer))

(defn get-draw-call
  "of those state-change happens (this is done in core module)
  int mode // Drawing mode: LINES, TRIANGLES, QUADS
  int vertexCount // Number of vertex of the draw
  int vertexAlignment // Number of vertex required for index alignment (LINES, TRIANGLES)
  unsigned int textureId // Texture id to be used on the draw -> Use to create new draw call if changes"
  [^MemorySegment seg]
  {:mode (rlgl.rlDrawCall/mode$get seg),
   :vertexCount (rlgl.rlDrawCall/vertexCount$get seg),
   :vertexAlignment (rlgl.rlDrawCall/vertexAlignment$get seg),
   :textureId (rlgl.rlDrawCall/textureId$get seg)})

(defn set-draw-call
  "of those state-change happens (this is done in core module)
  int mode // Drawing mode: LINES, TRIANGLES, QUADS
  int vertexCount // Number of vertex of the draw
  int vertexAlignment // Number of vertex required for index alignment (LINES, TRIANGLES)
  unsigned int textureId // Texture id to be used on the draw -> Use to create new draw call if changes"
  [^MemorySegment seg {:keys [mode vertexCount vertexAlignment textureId]}]
  (rlgl.rlDrawCall/mode$set seg mode)
  (rlgl.rlDrawCall/vertexCount$set seg vertexCount)
  (rlgl.rlDrawCall/vertexAlignment$set seg vertexAlignment)
  (rlgl.rlDrawCall/textureId$set seg textureId)
  seg)

(defn draw-call
  "of those state-change happens (this is done in core module)
  int mode // Drawing mode: LINES, TRIANGLES, QUADS
  int vertexCount // Number of vertex of the draw
  int vertexAlignment // Number of vertex required for index alignment (LINES, TRIANGLES)
  unsigned int textureId // Texture id to be used on the draw -> Use to create new draw call if changes"
  ([^Arena arena v]
   (set-draw-call (.allocate arena (rlgl.rlDrawCall/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (draw-call rarena/*current-arena* v))))

(def draw-call-array (array-fn (rlgl.rlDrawCall/$LAYOUT) set-draw-call))

(def get-draw-call-array (get-array-fn (rlgl.rlDrawCall/$LAYOUT) get-draw-call))

(def set-draw-call-array (set-array-fn (rlgl.rlDrawCall/$LAYOUT) set-draw-call))

(defn get-render-batch
  "rlRenderBatch type
  int bufferCount // Number of vertex buffers (multi-buffering support)
  int currentBuffer // Current buffer tracking in case of multi-buffering
  rlVertexBuffer * vertexBuffer // Dynamic buffer(s) for vertex data
  rlDrawCall * draws // Draw calls array, depends on textureId
  int drawCounter // Draw calls counter
  float currentDepth // Current depth value for next draw"
  [^MemorySegment seg]
  {:bufferCount (rlgl.rlRenderBatch/bufferCount$get seg),
   :currentBuffer (rlgl.rlRenderBatch/currentBuffer$get seg),
   :vertexBuffer (rlgl.rlRenderBatch/vertexBuffer$get seg),
   :draws (rlgl.rlRenderBatch/draws$get seg),
   :drawCounter (rlgl.rlRenderBatch/drawCounter$get seg),
   :currentDepth (rlgl.rlRenderBatch/currentDepth$get seg)})

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
  (rlgl.rlRenderBatch/bufferCount$set seg bufferCount)
  (rlgl.rlRenderBatch/currentBuffer$set seg currentBuffer)
  (rlgl.rlRenderBatch/vertexBuffer$set seg vertexBuffer)
  (rlgl.rlRenderBatch/draws$set seg draws)
  (rlgl.rlRenderBatch/drawCounter$set seg drawCounter)
  (rlgl.rlRenderBatch/currentDepth$set seg currentDepth)
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
   (set-render-batch (.allocate arena (rlgl.rlRenderBatch/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (render-batch rarena/*current-arena* v))))

(def render-batch-array
  (array-fn (rlgl.rlRenderBatch/$LAYOUT) set-render-batch))

(def get-render-batch-array
  (get-array-fn (rlgl.rlRenderBatch/$LAYOUT) get-render-batch))

(def set-render-batch-array
  (set-array-fn (rlgl.rlRenderBatch/$LAYOUT) set-render-batch))

