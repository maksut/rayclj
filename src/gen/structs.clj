(ns gen.structs
  (:require [raylib.arena :as rarena]
            [gen.enums :as renums])
  (:import
   [java.lang.foreign Arena MemoryLayout MemorySegment]))

(set! *warn-on-reflection* true)

;;
;; Structures Definition
;;

;; TODO: move these utility fns into an impl ns
(defn as-slice
  [^MemorySegment seg index element-size]
  (.asSlice seg (* index element-size)))

(defn get-array-fn
  [^MemoryLayout elem-layout get-fn]
  (let [element-size (.byteSize elem-layout)]
    (fn [^MemorySegment seg size]
      (mapv (fn [i] (get-fn (as-slice seg i element-size))) (range size)))))

(defn array-fn
  [^MemoryLayout elem-layout set-fn]
  (let [element-size (.byteSize elem-layout)]
    (fn to-array
      ([^Arena arena elems]
       (let [size (count elems)
             array-layout (MemoryLayout/sequenceLayout size elem-layout)
             seg (.allocate arena array-layout)]
         (dorun
          (map-indexed (fn [i elem] (set-fn (as-slice seg i element-size) elem)) elems))
         seg))
      ([elems]
       (if (instance? MemorySegment elems)
         elems
         (to-array rarena/*current-arena* elems))))))

(defn get-vector2
  "Vector2, 2 components
  float x // Vector x component
  float y // Vector y component"
  [^MemorySegment seg]
  [(raylib.Vector2/x$get seg) (raylib.Vector2/y$get seg)])

(defn set-vector2
  "Vector2, 2 components
  float x // Vector x component
  float y // Vector y component"
  [^MemorySegment seg [x y]]
  (raylib.Vector2/x$set seg x)
  (raylib.Vector2/y$set seg y)
  seg)

(defn vector2
  "Vector2, 2 components
  float x // Vector x component
  float y // Vector y component"
  ([^Arena arena v] (set-vector2 (.allocate arena (raylib.Vector2/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (vector2 rarena/*current-arena* v))))

(def vector2-array (array-fn (raylib.Vector2/$LAYOUT) set-vector2))

(def get-vector2-array (get-array-fn (raylib.Vector2/$LAYOUT) get-vector2))

(defn get-vector3
  "Vector3, 3 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component"
  [^MemorySegment seg]
  [(raylib.Vector3/x$get seg) (raylib.Vector3/y$get seg)
   (raylib.Vector3/z$get seg)])

(defn set-vector3
  "Vector3, 3 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component"
  [^MemorySegment seg [x y z]]
  (raylib.Vector3/x$set seg x)
  (raylib.Vector3/y$set seg y)
  (raylib.Vector3/z$set seg z)
  seg)

(defn vector3
  "Vector3, 3 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component"
  ([^Arena arena v] (set-vector3 (.allocate arena (raylib.Vector3/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (vector3 rarena/*current-arena* v))))

(def vector3-array (array-fn (raylib.Vector3/$LAYOUT) set-vector3))

(def get-vector3-array (get-array-fn (raylib.Vector3/$LAYOUT) get-vector3))

(defn get-vector4
  "Vector4, 4 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component
  float w // Vector w component"
  [^MemorySegment seg]
  [(raylib.Vector4/x$get seg) (raylib.Vector4/y$get seg)
   (raylib.Vector4/z$get seg) (raylib.Vector4/w$get seg)])

(defn set-vector4
  "Vector4, 4 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component
  float w // Vector w component"
  [^MemorySegment seg [x y z w]]
  (raylib.Vector4/x$set seg x)
  (raylib.Vector4/y$set seg y)
  (raylib.Vector4/z$set seg z)
  (raylib.Vector4/w$set seg w)
  seg)

(defn vector4
  "Vector4, 4 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component
  float w // Vector w component"
  ([^Arena arena v] (set-vector4 (.allocate arena (raylib.Vector4/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (vector4 rarena/*current-arena* v))))

(def vector4-array (array-fn (raylib.Vector4/$LAYOUT) set-vector4))

(def get-vector4-array (get-array-fn (raylib.Vector4/$LAYOUT) get-vector4))

(defn get-matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  [^MemorySegment seg]
  [(raylib.Matrix/m0$get seg) (raylib.Matrix/m4$get seg)
   (raylib.Matrix/m8$get seg) (raylib.Matrix/m12$get seg)
   (raylib.Matrix/m1$get seg) (raylib.Matrix/m5$get seg)
   (raylib.Matrix/m9$get seg) (raylib.Matrix/m13$get seg)
   (raylib.Matrix/m2$get seg) (raylib.Matrix/m6$get seg)
   (raylib.Matrix/m10$get seg) (raylib.Matrix/m14$get seg)
   (raylib.Matrix/m3$get seg) (raylib.Matrix/m7$get seg)
   (raylib.Matrix/m11$get seg) (raylib.Matrix/m15$get seg)])

(defn set-matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  [^MemorySegment seg [m0 m4 m8 m12 m1 m5 m9 m13 m2 m6 m10 m14 m3 m7 m11 m15]]
  (raylib.Matrix/m0$set seg m0)
  (raylib.Matrix/m4$set seg m4)
  (raylib.Matrix/m8$set seg m8)
  (raylib.Matrix/m12$set seg m12)
  (raylib.Matrix/m1$set seg m1)
  (raylib.Matrix/m5$set seg m5)
  (raylib.Matrix/m9$set seg m9)
  (raylib.Matrix/m13$set seg m13)
  (raylib.Matrix/m2$set seg m2)
  (raylib.Matrix/m6$set seg m6)
  (raylib.Matrix/m10$set seg m10)
  (raylib.Matrix/m14$set seg m14)
  (raylib.Matrix/m3$set seg m3)
  (raylib.Matrix/m7$set seg m7)
  (raylib.Matrix/m11$set seg m11)
  (raylib.Matrix/m15$set seg m15)
  seg)

(defn matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  ([^Arena arena v] (set-matrix (.allocate arena (raylib.Matrix/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (matrix rarena/*current-arena* v))))

(def matrix-array (array-fn (raylib.Matrix/$LAYOUT) set-matrix))

(def get-matrix-array (get-array-fn (raylib.Matrix/$LAYOUT) get-matrix))

(defn get-color
  "Color, 4 components, R8G8B8A8 (32bit)
  unsigned char r; // Color red value
  unsigned char g; // Color green value
  unsigned char b; // Color blue value
  unsigned char a; // Color alpha value"
  [^MemorySegment seg]
  {:r (raylib.Color/r$get seg),
   :g (raylib.Color/g$get seg),
   :b (raylib.Color/b$get seg),
   :a (raylib.Color/a$get seg)})

(defn set-color
  [^MemorySegment seg {:keys [r g b a]}]
  (raylib.Color/r$set seg (unchecked-byte r))
  (raylib.Color/g$set seg (unchecked-byte g))
  (raylib.Color/b$set seg (unchecked-byte b))
  (raylib.Color/a$set seg (unchecked-byte a))
  seg)

(declare predefined-colors)

(defn color
  ([^Arena arena c]
   (if (keyword? c)
     (predefined-colors c)
     (set-color (.allocate arena (raylib.Color/$LAYOUT)) c)))
  ([c] (if (instance? MemorySegment c) c (color rarena/*current-arena* c))))

(def predefined-colors
  (into {}
        (map (fn [[k v]] [k (color rarena/global-arena v)])
          renums/predefined-colors)))

(def color-array (array-fn (raylib.Color/$LAYOUT) set-color))

(def get-color-array (get-array-fn (raylib.Color/$LAYOUT) get-color))

(defn get-rectangle
  "Rectangle, 4 components
  float x // Rectangle top-left corner position x
  float y // Rectangle top-left corner position y
  float width // Rectangle width
  float height // Rectangle height"
  [^MemorySegment seg]
  {:x (raylib.Rectangle/x$get seg),
   :y (raylib.Rectangle/y$get seg),
   :width (raylib.Rectangle/width$get seg),
   :height (raylib.Rectangle/height$get seg)})

(defn set-rectangle
  "Rectangle, 4 components
  float x // Rectangle top-left corner position x
  float y // Rectangle top-left corner position y
  float width // Rectangle width
  float height // Rectangle height"
  [^MemorySegment seg {:keys [x y width height]}]
  (raylib.Rectangle/x$set seg x)
  (raylib.Rectangle/y$set seg y)
  (raylib.Rectangle/width$set seg width)
  (raylib.Rectangle/height$set seg height)
  seg)

(defn rectangle
  "Rectangle, 4 components
  float x // Rectangle top-left corner position x
  float y // Rectangle top-left corner position y
  float width // Rectangle width
  float height // Rectangle height"
  ([^Arena arena v]
   (set-rectangle (.allocate arena (raylib.Rectangle/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (rectangle rarena/*current-arena* v))))

(def rectangle-array (array-fn (raylib.Rectangle/$LAYOUT) set-rectangle))

(def get-rectangle-array
  (get-array-fn (raylib.Rectangle/$LAYOUT) get-rectangle))

(defn get-image
  "Image, pixel data stored in CPU memory (RAM)
  void * data // Image raw data
  int width // Image base width
  int height // Image base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  [^MemorySegment seg]
  {:data (raylib.Image/data$get seg),
   :width (raylib.Image/width$get seg),
   :height (raylib.Image/height$get seg),
   :mipmaps (raylib.Image/mipmaps$get seg),
   :format (raylib.Image/format$get seg)})

(defn set-image
  "Image, pixel data stored in CPU memory (RAM)
  void * data // Image raw data
  int width // Image base width
  int height // Image base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  [^MemorySegment seg {:keys [data width height mipmaps format]}]
  (raylib.Image/data$set seg data)
  (raylib.Image/width$set seg width)
  (raylib.Image/height$set seg height)
  (raylib.Image/mipmaps$set seg mipmaps)
  (raylib.Image/format$set seg format)
  seg)

(defn image
  "Image, pixel data stored in CPU memory (RAM)
  void * data // Image raw data
  int width // Image base width
  int height // Image base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  ([^Arena arena v] (set-image (.allocate arena (raylib.Image/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (image rarena/*current-arena* v))))

(def image-array (array-fn (raylib.Image/$LAYOUT) set-image))

(def get-image-array (get-array-fn (raylib.Image/$LAYOUT) get-image))

(defn get-texture
  "Texture, tex data stored in GPU memory (VRAM)
  unsigned int id // OpenGL texture id
  int width // Texture base width
  int height // Texture base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  [^MemorySegment seg]
  {:id (raylib.Texture/id$get seg),
   :width (raylib.Texture/width$get seg),
   :height (raylib.Texture/height$get seg),
   :mipmaps (raylib.Texture/mipmaps$get seg),
   :format (raylib.Texture/format$get seg)})

(defn set-texture
  "Texture, tex data stored in GPU memory (VRAM)
  unsigned int id // OpenGL texture id
  int width // Texture base width
  int height // Texture base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  [^MemorySegment seg {:keys [id width height mipmaps format]}]
  (raylib.Texture/id$set seg id)
  (raylib.Texture/width$set seg width)
  (raylib.Texture/height$set seg height)
  (raylib.Texture/mipmaps$set seg mipmaps)
  (raylib.Texture/format$set seg format)
  seg)

(defn texture
  "Texture, tex data stored in GPU memory (VRAM)
  unsigned int id // OpenGL texture id
  int width // Texture base width
  int height // Texture base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  ([^Arena arena v] (set-texture (.allocate arena (raylib.Texture/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (texture rarena/*current-arena* v))))

(def texture-array (array-fn (raylib.Texture/$LAYOUT) set-texture))

(def get-texture-array (get-array-fn (raylib.Texture/$LAYOUT) get-texture))

(defn get-render-texture
  "RenderTexture, fbo for texture rendering
  unsigned int id // OpenGL framebuffer object id
  Texture texture // Color buffer attachment texture
  Texture depth // Depth buffer attachment texture"
  [^MemorySegment seg]
  {:id (raylib.RenderTexture/id$get seg),
   :texture (get-texture (raylib.RenderTexture/texture$slice seg)),
   :depth (get-texture (raylib.RenderTexture/depth$slice seg))})

(defn set-render-texture
  "RenderTexture, fbo for texture rendering
  unsigned int id // OpenGL framebuffer object id
  Texture texture // Color buffer attachment texture
  Texture depth // Depth buffer attachment texture"
  [^MemorySegment seg {:keys [id texture depth]}]
  (raylib.RenderTexture/id$set seg id)
  (set-texture (raylib.RenderTexture/texture$slice seg) texture)
  (set-texture (raylib.RenderTexture/depth$slice seg) depth)
  seg)

(defn render-texture
  "RenderTexture, fbo for texture rendering
  unsigned int id // OpenGL framebuffer object id
  Texture texture // Color buffer attachment texture
  Texture depth // Depth buffer attachment texture"
  ([^Arena arena v]
   (set-render-texture (.allocate arena (raylib.RenderTexture/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (render-texture rarena/*current-arena* v))))

(def render-texture-array
  (array-fn (raylib.RenderTexture/$LAYOUT) set-render-texture))

(def get-render-texture-array
  (get-array-fn (raylib.RenderTexture/$LAYOUT) get-render-texture))

(defn get-npatch-info
  "NPatchInfo, n-patch layout info
  Rectangle source // Texture source rectangle
  int left // Left border offset
  int top // Top border offset
  int right // Right border offset
  int bottom // Bottom border offset
  int layout // Layout of the n-patch: 3x3, 1x3 or 3x1"
  [^MemorySegment seg]
  {:source (get-rectangle (raylib.NPatchInfo/source$slice seg)),
   :left (raylib.NPatchInfo/left$get seg),
   :top (raylib.NPatchInfo/top$get seg),
   :right (raylib.NPatchInfo/right$get seg),
   :bottom (raylib.NPatchInfo/bottom$get seg),
   :layout (raylib.NPatchInfo/layout$get seg)})

(defn set-npatch-info
  "NPatchInfo, n-patch layout info
  Rectangle source // Texture source rectangle
  int left // Left border offset
  int top // Top border offset
  int right // Right border offset
  int bottom // Bottom border offset
  int layout // Layout of the n-patch: 3x3, 1x3 or 3x1"
  [^MemorySegment seg {:keys [source left top right bottom layout]}]
  (set-rectangle (raylib.NPatchInfo/source$slice seg) source)
  (raylib.NPatchInfo/left$set seg left)
  (raylib.NPatchInfo/top$set seg top)
  (raylib.NPatchInfo/right$set seg right)
  (raylib.NPatchInfo/bottom$set seg bottom)
  (raylib.NPatchInfo/layout$set seg layout)
  seg)

(defn npatch-info
  "NPatchInfo, n-patch layout info
  Rectangle source // Texture source rectangle
  int left // Left border offset
  int top // Top border offset
  int right // Right border offset
  int bottom // Bottom border offset
  int layout // Layout of the n-patch: 3x3, 1x3 or 3x1"
  ([^Arena arena v]
   (set-npatch-info (.allocate arena (raylib.NPatchInfo/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (npatch-info rarena/*current-arena* v))))

(def npatch-info-array (array-fn (raylib.NPatchInfo/$LAYOUT) set-npatch-info))

(def get-npatch-info-array
  (get-array-fn (raylib.NPatchInfo/$LAYOUT) get-npatch-info))

(defn get-glyph-info
  "GlyphInfo, font characters glyphs info
  int value // Character value (Unicode)
  int offsetX // Character offset X when drawing
  int offsetY // Character offset Y when drawing
  int advanceX // Character advance position X
  Image image // Character image data"
  [^MemorySegment seg]
  {:value (raylib.GlyphInfo/value$get seg),
   :offsetX (raylib.GlyphInfo/offsetX$get seg),
   :offsetY (raylib.GlyphInfo/offsetY$get seg),
   :advanceX (raylib.GlyphInfo/advanceX$get seg),
   :image (get-image (raylib.GlyphInfo/image$slice seg))})

(defn set-glyph-info
  "GlyphInfo, font characters glyphs info
  int value // Character value (Unicode)
  int offsetX // Character offset X when drawing
  int offsetY // Character offset Y when drawing
  int advanceX // Character advance position X
  Image image // Character image data"
  [^MemorySegment seg {:keys [value offsetX offsetY advanceX image]}]
  (raylib.GlyphInfo/value$set seg value)
  (raylib.GlyphInfo/offsetX$set seg offsetX)
  (raylib.GlyphInfo/offsetY$set seg offsetY)
  (raylib.GlyphInfo/advanceX$set seg advanceX)
  (set-image (raylib.GlyphInfo/image$slice seg) image)
  seg)

(defn glyph-info
  "GlyphInfo, font characters glyphs info
  int value // Character value (Unicode)
  int offsetX // Character offset X when drawing
  int offsetY // Character offset Y when drawing
  int advanceX // Character advance position X
  Image image // Character image data"
  ([^Arena arena v]
   (set-glyph-info (.allocate arena (raylib.GlyphInfo/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (glyph-info rarena/*current-arena* v))))

(def glyph-info-array (array-fn (raylib.GlyphInfo/$LAYOUT) set-glyph-info))

(def get-glyph-info-array
  (get-array-fn (raylib.GlyphInfo/$LAYOUT) get-glyph-info))

(defn get-font
  "Font, font texture and GlyphInfo array data
  int baseSize // Base size (default chars height)
  int glyphCount // Number of glyph characters
  int glyphPadding // Padding around the glyph characters
  Texture2D texture // Texture atlas containing the glyphs
  Rectangle * recs // Rectangles in texture for the glyphs
  GlyphInfo * glyphs // Glyphs info data"
  [^MemorySegment seg]
  {:baseSize (raylib.Font/baseSize$get seg),
   :glyphCount (raylib.Font/glyphCount$get seg),
   :glyphPadding (raylib.Font/glyphPadding$get seg),
   :texture (get-texture (raylib.Font/texture$slice seg)),
   :recs (raylib.Font/recs$get seg),
   :glyphs (raylib.Font/glyphs$get seg)})

(defn set-font
  "Font, font texture and GlyphInfo array data
  int baseSize // Base size (default chars height)
  int glyphCount // Number of glyph characters
  int glyphPadding // Padding around the glyph characters
  Texture2D texture // Texture atlas containing the glyphs
  Rectangle * recs // Rectangles in texture for the glyphs
  GlyphInfo * glyphs // Glyphs info data"
  [^MemorySegment seg
   {:keys [baseSize glyphCount glyphPadding texture recs glyphs]}]
  (raylib.Font/baseSize$set seg baseSize)
  (raylib.Font/glyphCount$set seg glyphCount)
  (raylib.Font/glyphPadding$set seg glyphPadding)
  (set-texture (raylib.Font/texture$slice seg) texture)
  (raylib.Font/recs$set seg recs)
  (raylib.Font/glyphs$set seg glyphs)
  seg)

(defn font
  "Font, font texture and GlyphInfo array data
  int baseSize // Base size (default chars height)
  int glyphCount // Number of glyph characters
  int glyphPadding // Padding around the glyph characters
  Texture2D texture // Texture atlas containing the glyphs
  Rectangle * recs // Rectangles in texture for the glyphs
  GlyphInfo * glyphs // Glyphs info data"
  ([^Arena arena v] (set-font (.allocate arena (raylib.Font/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (font rarena/*current-arena* v))))

(def font-array (array-fn (raylib.Font/$LAYOUT) set-font))

(def get-font-array (get-array-fn (raylib.Font/$LAYOUT) get-font))

(defn get-camera3d
  "Camera, defines position/orientation in 3d space
  Vector3 position // Camera position
  Vector3 target // Camera target it looks-at
  Vector3 up // Camera up vector (rotation over its axis)
  float fovy // Camera field-of-view aperture in Y (degrees) in perspective, used as near plane width in orthographic
  int projection // Camera projection: CAMERA_PERSPECTIVE or CAMERA_ORTHOGRAPHIC"
  [^MemorySegment seg]
  {:position (get-vector3 (raylib.Camera3D/position$slice seg)),
   :target (get-vector3 (raylib.Camera3D/target$slice seg)),
   :up (get-vector3 (raylib.Camera3D/up$slice seg)),
   :fovy (raylib.Camera3D/fovy$get seg),
   :projection (raylib.Camera3D/projection$get seg)})

(defn set-camera3d
  "Camera, defines position/orientation in 3d space
  Vector3 position // Camera position
  Vector3 target // Camera target it looks-at
  Vector3 up // Camera up vector (rotation over its axis)
  float fovy // Camera field-of-view aperture in Y (degrees) in perspective, used as near plane width in orthographic
  int projection // Camera projection: CAMERA_PERSPECTIVE or CAMERA_ORTHOGRAPHIC"
  [^MemorySegment seg {:keys [position target up fovy projection]}]
  (set-vector3 (raylib.Camera3D/position$slice seg) position)
  (set-vector3 (raylib.Camera3D/target$slice seg) target)
  (set-vector3 (raylib.Camera3D/up$slice seg) up)
  (raylib.Camera3D/fovy$set seg fovy)
  (raylib.Camera3D/projection$set seg projection)
  seg)

(defn camera3d
  "Camera, defines position/orientation in 3d space
  Vector3 position // Camera position
  Vector3 target // Camera target it looks-at
  Vector3 up // Camera up vector (rotation over its axis)
  float fovy // Camera field-of-view aperture in Y (degrees) in perspective, used as near plane width in orthographic
  int projection // Camera projection: CAMERA_PERSPECTIVE or CAMERA_ORTHOGRAPHIC"
  ([^Arena arena v]
   (set-camera3d (.allocate arena (raylib.Camera3D/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (camera3d rarena/*current-arena* v))))

(def camera3d-array (array-fn (raylib.Camera3D/$LAYOUT) set-camera3d))

(def get-camera3d-array (get-array-fn (raylib.Camera3D/$LAYOUT) get-camera3d))

(defn get-camera2d
  "Camera2D, defines position/orientation in 2d space
  Vector2 offset // Camera offset (displacement from target)
  Vector2 target // Camera target (rotation and zoom origin)
  float rotation // Camera rotation in degrees
  float zoom // Camera zoom (scaling), should be 1.0f by default"
  [^MemorySegment seg]
  {:offset (get-vector2 (raylib.Camera2D/offset$slice seg)),
   :target (get-vector2 (raylib.Camera2D/target$slice seg)),
   :rotation (raylib.Camera2D/rotation$get seg),
   :zoom (raylib.Camera2D/zoom$get seg)})

(defn set-camera2d
  "Camera2D, defines position/orientation in 2d space
  Vector2 offset // Camera offset (displacement from target)
  Vector2 target // Camera target (rotation and zoom origin)
  float rotation // Camera rotation in degrees
  float zoom // Camera zoom (scaling), should be 1.0f by default"
  [^MemorySegment seg {:keys [offset target rotation zoom]}]
  (set-vector2 (raylib.Camera2D/offset$slice seg) offset)
  (set-vector2 (raylib.Camera2D/target$slice seg) target)
  (raylib.Camera2D/rotation$set seg rotation)
  (raylib.Camera2D/zoom$set seg zoom)
  seg)

(defn camera2d
  "Camera2D, defines position/orientation in 2d space
  Vector2 offset // Camera offset (displacement from target)
  Vector2 target // Camera target (rotation and zoom origin)
  float rotation // Camera rotation in degrees
  float zoom // Camera zoom (scaling), should be 1.0f by default"
  ([^Arena arena v]
   (set-camera2d (.allocate arena (raylib.Camera2D/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (camera2d rarena/*current-arena* v))))

(def camera2d-array (array-fn (raylib.Camera2D/$LAYOUT) set-camera2d))

(def get-camera2d-array (get-array-fn (raylib.Camera2D/$LAYOUT) get-camera2d))

(defn get-mesh
  "Mesh, vertex data and vao/vbo
  int vertexCount // Number of vertices stored in arrays
  int triangleCount // Number of triangles stored (indexed or not)
  float * vertices // Vertex position (XYZ - 3 components per vertex) (shader-location = 0)
  float * texcoords // Vertex texture coordinates (UV - 2 components per vertex) (shader-location = 1)
  float * texcoords2 // Vertex texture second coordinates (UV - 2 components per vertex) (shader-location = 5)
  float * normals // Vertex normals (XYZ - 3 components per vertex) (shader-location = 2)
  float * tangents // Vertex tangents (XYZW - 4 components per vertex) (shader-location = 4)
  unsigned char * colors // Vertex colors (RGBA - 4 components per vertex) (shader-location = 3)
  unsigned short * indices // Vertex indices (in case vertex data comes indexed)
  float * animVertices // Animated vertex positions (after bones transformations)
  float * animNormals // Animated normals (after bones transformations)
  unsigned char * boneIds // Vertex bone ids, max 255 bone ids, up to 4 bones influence by vertex (skinning)
  float * boneWeights // Vertex bone weight, up to 4 bones influence by vertex (skinning)
  unsigned int vaoId // OpenGL Vertex Array Object id
  unsigned int * vboId // OpenGL Vertex Buffer Objects id (default vertex data)"
  [^MemorySegment seg]
  {:vertexCount (raylib.Mesh/vertexCount$get seg),
   :triangleCount (raylib.Mesh/triangleCount$get seg),
   :vertices (raylib.Mesh/vertices$get seg),
   :texcoords (raylib.Mesh/texcoords$get seg),
   :texcoords2 (raylib.Mesh/texcoords2$get seg),
   :normals (raylib.Mesh/normals$get seg),
   :tangents (raylib.Mesh/tangents$get seg),
   :colors (raylib.Mesh/colors$get seg),
   :indices (raylib.Mesh/indices$get seg),
   :animVertices (raylib.Mesh/animVertices$get seg),
   :animNormals (raylib.Mesh/animNormals$get seg),
   :boneIds (raylib.Mesh/boneIds$get seg),
   :boneWeights (raylib.Mesh/boneWeights$get seg),
   :vaoId (raylib.Mesh/vaoId$get seg),
   :vboId (raylib.Mesh/vboId$get seg)})

(defn set-mesh
  "Mesh, vertex data and vao/vbo
  int vertexCount // Number of vertices stored in arrays
  int triangleCount // Number of triangles stored (indexed or not)
  float * vertices // Vertex position (XYZ - 3 components per vertex) (shader-location = 0)
  float * texcoords // Vertex texture coordinates (UV - 2 components per vertex) (shader-location = 1)
  float * texcoords2 // Vertex texture second coordinates (UV - 2 components per vertex) (shader-location = 5)
  float * normals // Vertex normals (XYZ - 3 components per vertex) (shader-location = 2)
  float * tangents // Vertex tangents (XYZW - 4 components per vertex) (shader-location = 4)
  unsigned char * colors // Vertex colors (RGBA - 4 components per vertex) (shader-location = 3)
  unsigned short * indices // Vertex indices (in case vertex data comes indexed)
  float * animVertices // Animated vertex positions (after bones transformations)
  float * animNormals // Animated normals (after bones transformations)
  unsigned char * boneIds // Vertex bone ids, max 255 bone ids, up to 4 bones influence by vertex (skinning)
  float * boneWeights // Vertex bone weight, up to 4 bones influence by vertex (skinning)
  unsigned int vaoId // OpenGL Vertex Array Object id
  unsigned int * vboId // OpenGL Vertex Buffer Objects id (default vertex data)"
  [^MemorySegment seg
   {:keys [vertexCount triangleCount vertices texcoords texcoords2 normals
           tangents colors indices animVertices animNormals boneIds boneWeights
           vaoId vboId]}]
  (raylib.Mesh/vertexCount$set seg vertexCount)
  (raylib.Mesh/triangleCount$set seg triangleCount)
  (raylib.Mesh/vertices$set seg vertices)
  (raylib.Mesh/texcoords$set seg texcoords)
  (raylib.Mesh/texcoords2$set seg texcoords2)
  (raylib.Mesh/normals$set seg normals)
  (raylib.Mesh/tangents$set seg tangents)
  (raylib.Mesh/colors$set seg colors)
  (raylib.Mesh/indices$set seg indices)
  (raylib.Mesh/animVertices$set seg animVertices)
  (raylib.Mesh/animNormals$set seg animNormals)
  (raylib.Mesh/boneIds$set seg boneIds)
  (raylib.Mesh/boneWeights$set seg boneWeights)
  (raylib.Mesh/vaoId$set seg vaoId)
  (raylib.Mesh/vboId$set seg vboId)
  seg)

(defn mesh
  "Mesh, vertex data and vao/vbo
  int vertexCount // Number of vertices stored in arrays
  int triangleCount // Number of triangles stored (indexed or not)
  float * vertices // Vertex position (XYZ - 3 components per vertex) (shader-location = 0)
  float * texcoords // Vertex texture coordinates (UV - 2 components per vertex) (shader-location = 1)
  float * texcoords2 // Vertex texture second coordinates (UV - 2 components per vertex) (shader-location = 5)
  float * normals // Vertex normals (XYZ - 3 components per vertex) (shader-location = 2)
  float * tangents // Vertex tangents (XYZW - 4 components per vertex) (shader-location = 4)
  unsigned char * colors // Vertex colors (RGBA - 4 components per vertex) (shader-location = 3)
  unsigned short * indices // Vertex indices (in case vertex data comes indexed)
  float * animVertices // Animated vertex positions (after bones transformations)
  float * animNormals // Animated normals (after bones transformations)
  unsigned char * boneIds // Vertex bone ids, max 255 bone ids, up to 4 bones influence by vertex (skinning)
  float * boneWeights // Vertex bone weight, up to 4 bones influence by vertex (skinning)
  unsigned int vaoId // OpenGL Vertex Array Object id
  unsigned int * vboId // OpenGL Vertex Buffer Objects id (default vertex data)"
  ([^Arena arena v] (set-mesh (.allocate arena (raylib.Mesh/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (mesh rarena/*current-arena* v))))

(def mesh-array (array-fn (raylib.Mesh/$LAYOUT) set-mesh))

(def get-mesh-array (get-array-fn (raylib.Mesh/$LAYOUT) get-mesh))

(defn get-shader
  "Shader
  unsigned int id // Shader program id
  int * locs // Shader locations array (RL_MAX_SHADER_LOCATIONS)"
  [^MemorySegment seg]
  {:id (raylib.Shader/id$get seg), :locs (raylib.Shader/locs$get seg)})

(defn set-shader
  "Shader
  unsigned int id // Shader program id
  int * locs // Shader locations array (RL_MAX_SHADER_LOCATIONS)"
  [^MemorySegment seg {:keys [id locs]}]
  (raylib.Shader/id$set seg id)
  (raylib.Shader/locs$set seg locs)
  seg)

(defn shader
  "Shader
  unsigned int id // Shader program id
  int * locs // Shader locations array (RL_MAX_SHADER_LOCATIONS)"
  ([^Arena arena v] (set-shader (.allocate arena (raylib.Shader/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (shader rarena/*current-arena* v))))

(def shader-array (array-fn (raylib.Shader/$LAYOUT) set-shader))

(def get-shader-array (get-array-fn (raylib.Shader/$LAYOUT) get-shader))

(defn get-material-map
  "MaterialMap
  Texture2D texture // Material map texture
  Color color // Material map color
  float value // Material map value"
  [^MemorySegment seg]
  {:texture (get-texture (raylib.MaterialMap/texture$slice seg)),
   :color (get-color (raylib.MaterialMap/color$slice seg)),
   :value (raylib.MaterialMap/value$get seg)})

(defn set-material-map
  "MaterialMap
  Texture2D texture // Material map texture
  Color color // Material map color
  float value // Material map value"
  [^MemorySegment seg {:keys [texture color value]}]
  (set-texture (raylib.MaterialMap/texture$slice seg) texture)
  (set-color (raylib.MaterialMap/color$slice seg) color)
  (raylib.MaterialMap/value$set seg value)
  seg)

(defn material-map
  "MaterialMap
  Texture2D texture // Material map texture
  Color color // Material map color
  float value // Material map value"
  ([^Arena arena v]
   (set-material-map (.allocate arena (raylib.MaterialMap/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (material-map rarena/*current-arena* v))))

(def material-map-array
  (array-fn (raylib.MaterialMap/$LAYOUT) set-material-map))

(def get-material-map-array
  (get-array-fn (raylib.MaterialMap/$LAYOUT) get-material-map))

(defn get-transform
  "Transform, vertex transformation data
  Vector3 translation // Translation
  Quaternion rotation // Rotation
  Vector3 scale // Scale"
  [^MemorySegment seg]
  {:translation (get-vector3 (raylib.Transform/translation$slice seg)),
   :rotation (get-vector4 (raylib.Transform/rotation$slice seg)),
   :scale (get-vector3 (raylib.Transform/scale$slice seg))})

(defn set-transform
  "Transform, vertex transformation data
  Vector3 translation // Translation
  Quaternion rotation // Rotation
  Vector3 scale // Scale"
  [^MemorySegment seg {:keys [translation rotation scale]}]
  (set-vector3 (raylib.Transform/translation$slice seg) translation)
  (set-vector4 (raylib.Transform/rotation$slice seg) rotation)
  (set-vector3 (raylib.Transform/scale$slice seg) scale)
  seg)

(defn transform
  "Transform, vertex transformation data
  Vector3 translation // Translation
  Quaternion rotation // Rotation
  Vector3 scale // Scale"
  ([^Arena arena v]
   (set-transform (.allocate arena (raylib.Transform/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (transform rarena/*current-arena* v))))

(def transform-array (array-fn (raylib.Transform/$LAYOUT) set-transform))

(def get-transform-array
  (get-array-fn (raylib.Transform/$LAYOUT) get-transform))

(defn get-model
  "Model, meshes, materials and animation data
  Matrix transform // Local transform matrix
  int meshCount // Number of meshes
  int materialCount // Number of materials
  Mesh * meshes // Meshes array
  Material * materials // Materials array
  int * meshMaterial // Mesh material number
  int boneCount // Number of bones
  BoneInfo * bones // Bones information (skeleton)
  Transform * bindPose // Bones base transformation (pose)"
  [^MemorySegment seg]
  {:transform (get-matrix (raylib.Model/transform$slice seg)),
   :meshCount (raylib.Model/meshCount$get seg),
   :materialCount (raylib.Model/materialCount$get seg),
   :meshes (raylib.Model/meshes$get seg),
   :materials (raylib.Model/materials$get seg),
   :meshMaterial (raylib.Model/meshMaterial$get seg),
   :boneCount (raylib.Model/boneCount$get seg),
   :bones (raylib.Model/bones$get seg),
   :bindPose (raylib.Model/bindPose$get seg)})

(defn set-model
  "Model, meshes, materials and animation data
  Matrix transform // Local transform matrix
  int meshCount // Number of meshes
  int materialCount // Number of materials
  Mesh * meshes // Meshes array
  Material * materials // Materials array
  int * meshMaterial // Mesh material number
  int boneCount // Number of bones
  BoneInfo * bones // Bones information (skeleton)
  Transform * bindPose // Bones base transformation (pose)"
  [^MemorySegment seg
   {:keys [transform meshCount materialCount meshes materials meshMaterial
           boneCount bones bindPose]}]
  (set-matrix (raylib.Model/transform$slice seg) transform)
  (raylib.Model/meshCount$set seg meshCount)
  (raylib.Model/materialCount$set seg materialCount)
  (raylib.Model/meshes$set seg meshes)
  (raylib.Model/materials$set seg materials)
  (raylib.Model/meshMaterial$set seg meshMaterial)
  (raylib.Model/boneCount$set seg boneCount)
  (raylib.Model/bones$set seg bones)
  (raylib.Model/bindPose$set seg bindPose)
  seg)

(defn model
  "Model, meshes, materials and animation data
  Matrix transform // Local transform matrix
  int meshCount // Number of meshes
  int materialCount // Number of materials
  Mesh * meshes // Meshes array
  Material * materials // Materials array
  int * meshMaterial // Mesh material number
  int boneCount // Number of bones
  BoneInfo * bones // Bones information (skeleton)
  Transform * bindPose // Bones base transformation (pose)"
  ([^Arena arena v] (set-model (.allocate arena (raylib.Model/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (model rarena/*current-arena* v))))

(def model-array (array-fn (raylib.Model/$LAYOUT) set-model))

(def get-model-array (get-array-fn (raylib.Model/$LAYOUT) get-model))

(defn get-model-animation
  "ModelAnimation
  int boneCount // Number of bones
  int frameCount // Number of animation frames
  BoneInfo * bones // Bones information (skeleton)
  Transform ** framePoses // Poses array by frame"
  [^MemorySegment seg]
  {:boneCount (raylib.ModelAnimation/boneCount$get seg),
   :frameCount (raylib.ModelAnimation/frameCount$get seg),
   :bones (raylib.ModelAnimation/bones$get seg),
   :framePoses (raylib.ModelAnimation/framePoses$get seg)})

(defn set-model-animation
  "ModelAnimation
  int boneCount // Number of bones
  int frameCount // Number of animation frames
  BoneInfo * bones // Bones information (skeleton)
  Transform ** framePoses // Poses array by frame"
  [^MemorySegment seg {:keys [boneCount frameCount bones framePoses]}]
  (raylib.ModelAnimation/boneCount$set seg boneCount)
  (raylib.ModelAnimation/frameCount$set seg frameCount)
  (raylib.ModelAnimation/bones$set seg bones)
  (raylib.ModelAnimation/framePoses$set seg framePoses)
  seg)

(defn model-animation
  "ModelAnimation
  int boneCount // Number of bones
  int frameCount // Number of animation frames
  BoneInfo * bones // Bones information (skeleton)
  Transform ** framePoses // Poses array by frame"
  ([^Arena arena v]
   (set-model-animation (.allocate arena (raylib.ModelAnimation/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (model-animation rarena/*current-arena* v))))

(def model-animation-array
  (array-fn (raylib.ModelAnimation/$LAYOUT) set-model-animation))

(def get-model-animation-array
  (get-array-fn (raylib.ModelAnimation/$LAYOUT) get-model-animation))

(defn get-ray
  "Ray, ray for raycasting
  Vector3 position // Ray position (origin)
  Vector3 direction // Ray direction"
  [^MemorySegment seg]
  {:position (get-vector3 (raylib.Ray/position$slice seg)),
   :direction (get-vector3 (raylib.Ray/direction$slice seg))})

(defn set-ray
  "Ray, ray for raycasting
  Vector3 position // Ray position (origin)
  Vector3 direction // Ray direction"
  [^MemorySegment seg {:keys [position direction]}]
  (set-vector3 (raylib.Ray/position$slice seg) position)
  (set-vector3 (raylib.Ray/direction$slice seg) direction)
  seg)

(defn ray
  "Ray, ray for raycasting
  Vector3 position // Ray position (origin)
  Vector3 direction // Ray direction"
  ([^Arena arena v] (set-ray (.allocate arena (raylib.Ray/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (ray rarena/*current-arena* v))))

(def ray-array (array-fn (raylib.Ray/$LAYOUT) set-ray))

(def get-ray-array (get-array-fn (raylib.Ray/$LAYOUT) get-ray))

(defn get-ray-collision
  "RayCollision, ray hit information
  bool hit // Did the ray hit something?
  float distance // Distance to the nearest hit
  Vector3 point // Point of the nearest hit
  Vector3 normal // Surface normal of hit"
  [^MemorySegment seg]
  {:hit (raylib.RayCollision/hit$get seg),
   :distance (raylib.RayCollision/distance$get seg),
   :point (get-vector3 (raylib.RayCollision/point$slice seg)),
   :normal (get-vector3 (raylib.RayCollision/normal$slice seg))})

(defn set-ray-collision
  "RayCollision, ray hit information
  bool hit // Did the ray hit something?
  float distance // Distance to the nearest hit
  Vector3 point // Point of the nearest hit
  Vector3 normal // Surface normal of hit"
  [^MemorySegment seg {:keys [hit distance point normal]}]
  (raylib.RayCollision/hit$set seg hit)
  (raylib.RayCollision/distance$set seg distance)
  (set-vector3 (raylib.RayCollision/point$slice seg) point)
  (set-vector3 (raylib.RayCollision/normal$slice seg) normal)
  seg)

(defn ray-collision
  "RayCollision, ray hit information
  bool hit // Did the ray hit something?
  float distance // Distance to the nearest hit
  Vector3 point // Point of the nearest hit
  Vector3 normal // Surface normal of hit"
  ([^Arena arena v]
   (set-ray-collision (.allocate arena (raylib.RayCollision/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (ray-collision rarena/*current-arena* v))))

(def ray-collision-array
  (array-fn (raylib.RayCollision/$LAYOUT) set-ray-collision))

(def get-ray-collision-array
  (get-array-fn (raylib.RayCollision/$LAYOUT) get-ray-collision))

(defn get-bounding-box
  "BoundingBox
  Vector3 min // Minimum vertex box-corner
  Vector3 max // Maximum vertex box-corner"
  [^MemorySegment seg]
  {:min (get-vector3 (raylib.BoundingBox/min$slice seg)),
   :max (get-vector3 (raylib.BoundingBox/max$slice seg))})

(defn set-bounding-box
  "BoundingBox
  Vector3 min // Minimum vertex box-corner
  Vector3 max // Maximum vertex box-corner"
  [^MemorySegment seg {:keys [min max]}]
  (set-vector3 (raylib.BoundingBox/min$slice seg) min)
  (set-vector3 (raylib.BoundingBox/max$slice seg) max)
  seg)

(defn bounding-box
  "BoundingBox
  Vector3 min // Minimum vertex box-corner
  Vector3 max // Maximum vertex box-corner"
  ([^Arena arena v]
   (set-bounding-box (.allocate arena (raylib.BoundingBox/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (bounding-box rarena/*current-arena* v))))

(def bounding-box-array
  (array-fn (raylib.BoundingBox/$LAYOUT) set-bounding-box))

(def get-bounding-box-array
  (get-array-fn (raylib.BoundingBox/$LAYOUT) get-bounding-box))

(defn get-wave
  "Wave, audio wave data
  unsigned int frameCount // Total number of frames (considering channels)
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)
  void * data // Buffer data pointer"
  [^MemorySegment seg]
  {:frameCount (raylib.Wave/frameCount$get seg),
   :sampleRate (raylib.Wave/sampleRate$get seg),
   :sampleSize (raylib.Wave/sampleSize$get seg),
   :channels (raylib.Wave/channels$get seg),
   :data (raylib.Wave/data$get seg)})

(defn set-wave
  "Wave, audio wave data
  unsigned int frameCount // Total number of frames (considering channels)
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)
  void * data // Buffer data pointer"
  [^MemorySegment seg {:keys [frameCount sampleRate sampleSize channels data]}]
  (raylib.Wave/frameCount$set seg frameCount)
  (raylib.Wave/sampleRate$set seg sampleRate)
  (raylib.Wave/sampleSize$set seg sampleSize)
  (raylib.Wave/channels$set seg channels)
  (raylib.Wave/data$set seg data)
  seg)

(defn wave
  "Wave, audio wave data
  unsigned int frameCount // Total number of frames (considering channels)
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)
  void * data // Buffer data pointer"
  ([^Arena arena v] (set-wave (.allocate arena (raylib.Wave/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (wave rarena/*current-arena* v))))

(def wave-array (array-fn (raylib.Wave/$LAYOUT) set-wave))

(def get-wave-array (get-array-fn (raylib.Wave/$LAYOUT) get-wave))

(defn get-audio-stream
  "AudioStream, custom audio stream
  rAudioBuffer * buffer // Pointer to internal data used by the audio system
  rAudioProcessor * processor // Pointer to internal data processor, useful for audio effects
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)"
  [^MemorySegment seg]
  {:buffer (raylib.AudioStream/buffer$get seg),
   :processor (raylib.AudioStream/processor$get seg),
   :sampleRate (raylib.AudioStream/sampleRate$get seg),
   :sampleSize (raylib.AudioStream/sampleSize$get seg),
   :channels (raylib.AudioStream/channels$get seg)})

(defn set-audio-stream
  "AudioStream, custom audio stream
  rAudioBuffer * buffer // Pointer to internal data used by the audio system
  rAudioProcessor * processor // Pointer to internal data processor, useful for audio effects
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)"
  [^MemorySegment seg {:keys [buffer processor sampleRate sampleSize channels]}]
  (raylib.AudioStream/buffer$set seg buffer)
  (raylib.AudioStream/processor$set seg processor)
  (raylib.AudioStream/sampleRate$set seg sampleRate)
  (raylib.AudioStream/sampleSize$set seg sampleSize)
  (raylib.AudioStream/channels$set seg channels)
  seg)

(defn audio-stream
  "AudioStream, custom audio stream
  rAudioBuffer * buffer // Pointer to internal data used by the audio system
  rAudioProcessor * processor // Pointer to internal data processor, useful for audio effects
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)"
  ([^Arena arena v]
   (set-audio-stream (.allocate arena (raylib.AudioStream/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (audio-stream rarena/*current-arena* v))))

(def audio-stream-array
  (array-fn (raylib.AudioStream/$LAYOUT) set-audio-stream))

(def get-audio-stream-array
  (get-array-fn (raylib.AudioStream/$LAYOUT) get-audio-stream))

(defn get-sound
  "Sound
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)"
  [^MemorySegment seg]
  {:stream (get-audio-stream (raylib.Sound/stream$slice seg)),
   :frameCount (raylib.Sound/frameCount$get seg)})

(defn set-sound
  "Sound
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)"
  [^MemorySegment seg {:keys [stream frameCount]}]
  (set-audio-stream (raylib.Sound/stream$slice seg) stream)
  (raylib.Sound/frameCount$set seg frameCount)
  seg)

(defn sound
  "Sound
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)"
  ([^Arena arena v] (set-sound (.allocate arena (raylib.Sound/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (sound rarena/*current-arena* v))))

(def sound-array (array-fn (raylib.Sound/$LAYOUT) set-sound))

(def get-sound-array (get-array-fn (raylib.Sound/$LAYOUT) get-sound))

(defn get-music
  "Music, audio stream, anything longer than ~10 seconds should be streamed
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)
  bool looping // Music looping enable
  int ctxType // Type of music context (audio filetype)
  void * ctxData // Audio context data, depends on type"
  [^MemorySegment seg]
  {:stream (get-audio-stream (raylib.Music/stream$slice seg)),
   :frameCount (raylib.Music/frameCount$get seg),
   :looping (raylib.Music/looping$get seg),
   :ctxType (raylib.Music/ctxType$get seg),
   :ctxData (raylib.Music/ctxData$get seg)})

(defn set-music
  "Music, audio stream, anything longer than ~10 seconds should be streamed
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)
  bool looping // Music looping enable
  int ctxType // Type of music context (audio filetype)
  void * ctxData // Audio context data, depends on type"
  [^MemorySegment seg {:keys [stream frameCount looping ctxType ctxData]}]
  (set-audio-stream (raylib.Music/stream$slice seg) stream)
  (raylib.Music/frameCount$set seg frameCount)
  (raylib.Music/looping$set seg looping)
  (raylib.Music/ctxType$set seg ctxType)
  (raylib.Music/ctxData$set seg ctxData)
  seg)

(defn music
  "Music, audio stream, anything longer than ~10 seconds should be streamed
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)
  bool looping // Music looping enable
  int ctxType // Type of music context (audio filetype)
  void * ctxData // Audio context data, depends on type"
  ([^Arena arena v] (set-music (.allocate arena (raylib.Music/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (music rarena/*current-arena* v))))

(def music-array (array-fn (raylib.Music/$LAYOUT) set-music))

(def get-music-array (get-array-fn (raylib.Music/$LAYOUT) get-music))

(defn get-file-path-list
  "File path list
  unsigned int capacity // Filepaths max entries
  unsigned int count // Filepaths entries count
  char ** paths // Filepaths entries"
  [^MemorySegment seg]
  {:capacity (raylib.FilePathList/capacity$get seg),
   :count (raylib.FilePathList/count$get seg),
   :paths (raylib.FilePathList/paths$get seg)})

(defn set-file-path-list
  "File path list
  unsigned int capacity // Filepaths max entries
  unsigned int count // Filepaths entries count
  char ** paths // Filepaths entries"
  [^MemorySegment seg {:keys [capacity count paths]}]
  (raylib.FilePathList/capacity$set seg capacity)
  (raylib.FilePathList/count$set seg count)
  (raylib.FilePathList/paths$set seg paths)
  seg)

(defn file-path-list
  "File path list
  unsigned int capacity // Filepaths max entries
  unsigned int count // Filepaths entries count
  char ** paths // Filepaths entries"
  ([^Arena arena v]
   (set-file-path-list (.allocate arena (raylib.FilePathList/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (file-path-list rarena/*current-arena* v))))

(def file-path-list-array
  (array-fn (raylib.FilePathList/$LAYOUT) set-file-path-list))

(def get-file-path-list-array
  (get-array-fn (raylib.FilePathList/$LAYOUT) get-file-path-list))

