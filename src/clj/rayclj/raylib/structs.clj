(ns rayclj.raylib.structs
  (:require [rayclj.arena :as rarena]
            [rayclj.raylib.enums :as renums]
            [rayclj.arrays :refer [get-float-array
                                   set-float-array
                                   get-char-array
                                   set-char-array
                                   get-int-array
                                   set-int-array
                                   array-fn
                                   get-array-fn
                                   set-array-fn]])
  (:import
   [java.lang.foreign Arena MemorySegment]))

(set! *warn-on-reflection* true)

;;
;; Raylib Structures Definitions
;;

(defn get-vector2
  "Vector2, 2 components
  float x // Vector x component
  float y // Vector y component"
  [^MemorySegment seg]
  [(rayclj.raylib.Vector2/x$get seg) (rayclj.raylib.Vector2/y$get seg)])

(defn set-vector2
  "Vector2, 2 components
  float x // Vector x component
  float y // Vector y component"
  [^MemorySegment seg [x y]]
  (rayclj.raylib.Vector2/x$set seg x)
  (rayclj.raylib.Vector2/y$set seg y)
  seg)

(defn vector2
  "Vector2, 2 components
  float x // Vector x component
  float y // Vector y component"
  ([^Arena arena v]
   (set-vector2 (.allocate arena (rayclj.raylib.Vector2/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (vector2 rarena/*current-arena* v))))

(def vector2-array (array-fn (rayclj.raylib.Vector2/$LAYOUT) set-vector2))

(def get-vector2-array
  (get-array-fn (rayclj.raylib.Vector2/$LAYOUT) get-vector2))

(def set-vector2-array
  (set-array-fn (rayclj.raylib.Vector2/$LAYOUT) set-vector2))

(defn get-vector3
  "Vector3, 3 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component"
  [^MemorySegment seg]
  [(rayclj.raylib.Vector3/x$get seg) (rayclj.raylib.Vector3/y$get seg)
   (rayclj.raylib.Vector3/z$get seg)])

(defn set-vector3
  "Vector3, 3 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component"
  [^MemorySegment seg [x y z]]
  (rayclj.raylib.Vector3/x$set seg x)
  (rayclj.raylib.Vector3/y$set seg y)
  (rayclj.raylib.Vector3/z$set seg z)
  seg)

(defn vector3
  "Vector3, 3 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component"
  ([^Arena arena v]
   (set-vector3 (.allocate arena (rayclj.raylib.Vector3/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (vector3 rarena/*current-arena* v))))

(def vector3-array (array-fn (rayclj.raylib.Vector3/$LAYOUT) set-vector3))

(def get-vector3-array
  (get-array-fn (rayclj.raylib.Vector3/$LAYOUT) get-vector3))

(def set-vector3-array
  (set-array-fn (rayclj.raylib.Vector3/$LAYOUT) set-vector3))

(defn get-vector4
  "Vector4, 4 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component
  float w // Vector w component"
  [^MemorySegment seg]
  [(rayclj.raylib.Vector4/x$get seg) (rayclj.raylib.Vector4/y$get seg)
   (rayclj.raylib.Vector4/z$get seg) (rayclj.raylib.Vector4/w$get seg)])

(defn set-vector4
  "Vector4, 4 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component
  float w // Vector w component"
  [^MemorySegment seg [x y z w]]
  (rayclj.raylib.Vector4/x$set seg x)
  (rayclj.raylib.Vector4/y$set seg y)
  (rayclj.raylib.Vector4/z$set seg z)
  (rayclj.raylib.Vector4/w$set seg w)
  seg)

(defn vector4
  "Vector4, 4 components
  float x // Vector x component
  float y // Vector y component
  float z // Vector z component
  float w // Vector w component"
  ([^Arena arena v]
   (set-vector4 (.allocate arena (rayclj.raylib.Vector4/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (vector4 rarena/*current-arena* v))))

(def vector4-array (array-fn (rayclj.raylib.Vector4/$LAYOUT) set-vector4))

(def get-vector4-array
  (get-array-fn (rayclj.raylib.Vector4/$LAYOUT) get-vector4))

(def set-vector4-array
  (set-array-fn (rayclj.raylib.Vector4/$LAYOUT) set-vector4))

(defn get-matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  [^MemorySegment seg]
  [(rayclj.raylib.Matrix/m0$get seg) (rayclj.raylib.Matrix/m4$get seg)
   (rayclj.raylib.Matrix/m8$get seg) (rayclj.raylib.Matrix/m12$get seg)
   (rayclj.raylib.Matrix/m1$get seg) (rayclj.raylib.Matrix/m5$get seg)
   (rayclj.raylib.Matrix/m9$get seg) (rayclj.raylib.Matrix/m13$get seg)
   (rayclj.raylib.Matrix/m2$get seg) (rayclj.raylib.Matrix/m6$get seg)
   (rayclj.raylib.Matrix/m10$get seg) (rayclj.raylib.Matrix/m14$get seg)
   (rayclj.raylib.Matrix/m3$get seg) (rayclj.raylib.Matrix/m7$get seg)
   (rayclj.raylib.Matrix/m11$get seg) (rayclj.raylib.Matrix/m15$get seg)])

(defn set-matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  [^MemorySegment seg [m0 m4 m8 m12 m1 m5 m9 m13 m2 m6 m10 m14 m3 m7 m11 m15]]
  (rayclj.raylib.Matrix/m0$set seg m0)
  (rayclj.raylib.Matrix/m4$set seg m4)
  (rayclj.raylib.Matrix/m8$set seg m8)
  (rayclj.raylib.Matrix/m12$set seg m12)
  (rayclj.raylib.Matrix/m1$set seg m1)
  (rayclj.raylib.Matrix/m5$set seg m5)
  (rayclj.raylib.Matrix/m9$set seg m9)
  (rayclj.raylib.Matrix/m13$set seg m13)
  (rayclj.raylib.Matrix/m2$set seg m2)
  (rayclj.raylib.Matrix/m6$set seg m6)
  (rayclj.raylib.Matrix/m10$set seg m10)
  (rayclj.raylib.Matrix/m14$set seg m14)
  (rayclj.raylib.Matrix/m3$set seg m3)
  (rayclj.raylib.Matrix/m7$set seg m7)
  (rayclj.raylib.Matrix/m11$set seg m11)
  (rayclj.raylib.Matrix/m15$set seg m15)
  seg)

(defn matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
  float m0, m4, m8,  m12 // Matrix first row (4 components)
  float m1, m5, m9,  m13 // Matrix second row (4 components)
  float m2, m6, m10, m14 // Matrix third row (4 components)
  float m3, m7, m11, m15 // Matrix fourth row (4 components)"
  ([^Arena arena v]
   (set-matrix (.allocate arena (rayclj.raylib.Matrix/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (matrix rarena/*current-arena* v))))

(def matrix-array (array-fn (rayclj.raylib.Matrix/$LAYOUT) set-matrix))

(def get-matrix-array (get-array-fn (rayclj.raylib.Matrix/$LAYOUT) get-matrix))

(def set-matrix-array (set-array-fn (rayclj.raylib.Matrix/$LAYOUT) set-matrix))

(defn get-color
  "Color, 4 components, R8G8B8A8 (32bit)
  unsigned char r; // Color red value
  unsigned char g; // Color green value
  unsigned char b; // Color blue value
  unsigned char a; // Color alpha value"
  [^MemorySegment seg]
  {:r (rayclj.raylib.Color/r$get seg),
   :g (rayclj.raylib.Color/g$get seg),
   :b (rayclj.raylib.Color/b$get seg),
   :a (rayclj.raylib.Color/a$get seg)})

(defn set-color
  "Color, 4 components, R8G8B8A8 (32bit)
  unsigned char r; // Color red value
  unsigned char g; // Color green value
  unsigned char b; // Color blue value
  unsigned char a; // Color alpha value"
  [^MemorySegment seg {:keys [r g b a]}]
  (rayclj.raylib.Color/r$set seg (unchecked-byte r))
  (rayclj.raylib.Color/g$set seg (unchecked-byte g))
  (rayclj.raylib.Color/b$set seg (unchecked-byte b))
  (rayclj.raylib.Color/a$set seg (unchecked-byte a))
  seg)

(declare predefined-colors)

(defn color
  "Color, 4 components, R8G8B8A8 (32bit)
  unsigned char r; // Color red value
  unsigned char g; // Color green value
  unsigned char b; // Color blue value
  unsigned char a; // Color alpha value"
  ([^Arena arena c]
   (if (keyword? c)
     (predefined-colors c)
     (set-color (.allocate arena (rayclj.raylib.Color/$LAYOUT)) c)))
  ([c] (if (instance? MemorySegment c) c (color rarena/*current-arena* c))))

(def predefined-colors
  (into {}
        (map (fn [[k v]] [k (color rarena/global-arena v)])
          renums/predefined-colors)))

(def color-array (array-fn (rayclj.raylib.Color/$LAYOUT) set-color))

(def get-color-array (get-array-fn (rayclj.raylib.Color/$LAYOUT) get-color))

(def set-color-array (set-array-fn (rayclj.raylib.Color/$LAYOUT) set-color))

(defn get-rectangle
  "Rectangle, 4 components
  float x // Rectangle top-left corner position x
  float y // Rectangle top-left corner position y
  float width // Rectangle width
  float height // Rectangle height"
  [^MemorySegment seg]
  {:x (rayclj.raylib.Rectangle/x$get seg),
   :y (rayclj.raylib.Rectangle/y$get seg),
   :width (rayclj.raylib.Rectangle/width$get seg),
   :height (rayclj.raylib.Rectangle/height$get seg)})

(defn set-rectangle
  "Rectangle, 4 components
  float x // Rectangle top-left corner position x
  float y // Rectangle top-left corner position y
  float width // Rectangle width
  float height // Rectangle height"
  [^MemorySegment seg {:keys [x y width height]}]
  (rayclj.raylib.Rectangle/x$set seg x)
  (rayclj.raylib.Rectangle/y$set seg y)
  (rayclj.raylib.Rectangle/width$set seg width)
  (rayclj.raylib.Rectangle/height$set seg height)
  seg)

(defn rectangle
  "Rectangle, 4 components
  float x // Rectangle top-left corner position x
  float y // Rectangle top-left corner position y
  float width // Rectangle width
  float height // Rectangle height"
  ([^Arena arena v]
   (set-rectangle (.allocate arena (rayclj.raylib.Rectangle/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (rectangle rarena/*current-arena* v))))

(def rectangle-array (array-fn (rayclj.raylib.Rectangle/$LAYOUT) set-rectangle))

(def get-rectangle-array
  (get-array-fn (rayclj.raylib.Rectangle/$LAYOUT) get-rectangle))

(def set-rectangle-array
  (set-array-fn (rayclj.raylib.Rectangle/$LAYOUT) set-rectangle))

(defn get-image
  "Image, pixel data stored in CPU memory (RAM)
  void * data // Image raw data
  int width // Image base width
  int height // Image base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  [^MemorySegment seg]
  {:data (rayclj.raylib.Image/data$get seg),
   :width (rayclj.raylib.Image/width$get seg),
   :height (rayclj.raylib.Image/height$get seg),
   :mipmaps (rayclj.raylib.Image/mipmaps$get seg),
   :format (rayclj.raylib.Image/format$get seg)})

(defn set-image
  "Image, pixel data stored in CPU memory (RAM)
  void * data // Image raw data
  int width // Image base width
  int height // Image base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  [^MemorySegment seg {:keys [data width height mipmaps format]}]
  (rayclj.raylib.Image/data$set seg data)
  (rayclj.raylib.Image/width$set seg width)
  (rayclj.raylib.Image/height$set seg height)
  (rayclj.raylib.Image/mipmaps$set seg mipmaps)
  (rayclj.raylib.Image/format$set seg format)
  seg)

(defn image
  "Image, pixel data stored in CPU memory (RAM)
  void * data // Image raw data
  int width // Image base width
  int height // Image base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  ([^Arena arena v]
   (set-image (.allocate arena (rayclj.raylib.Image/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (image rarena/*current-arena* v))))

(def image-array (array-fn (rayclj.raylib.Image/$LAYOUT) set-image))

(def get-image-array (get-array-fn (rayclj.raylib.Image/$LAYOUT) get-image))

(def set-image-array (set-array-fn (rayclj.raylib.Image/$LAYOUT) set-image))

(defn get-texture
  "Texture, tex data stored in GPU memory (VRAM)
  unsigned int id // OpenGL texture id
  int width // Texture base width
  int height // Texture base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  [^MemorySegment seg]
  {:id (rayclj.raylib.Texture/id$get seg),
   :width (rayclj.raylib.Texture/width$get seg),
   :height (rayclj.raylib.Texture/height$get seg),
   :mipmaps (rayclj.raylib.Texture/mipmaps$get seg),
   :format (rayclj.raylib.Texture/format$get seg)})

(defn set-texture
  "Texture, tex data stored in GPU memory (VRAM)
  unsigned int id // OpenGL texture id
  int width // Texture base width
  int height // Texture base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  [^MemorySegment seg {:keys [id width height mipmaps format]}]
  (rayclj.raylib.Texture/id$set seg id)
  (rayclj.raylib.Texture/width$set seg width)
  (rayclj.raylib.Texture/height$set seg height)
  (rayclj.raylib.Texture/mipmaps$set seg mipmaps)
  (rayclj.raylib.Texture/format$set seg format)
  seg)

(defn texture
  "Texture, tex data stored in GPU memory (VRAM)
  unsigned int id // OpenGL texture id
  int width // Texture base width
  int height // Texture base height
  int mipmaps // Mipmap levels, 1 by default
  int format // Data format (PixelFormat type)"
  ([^Arena arena v]
   (set-texture (.allocate arena (rayclj.raylib.Texture/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (texture rarena/*current-arena* v))))

(def texture-array (array-fn (rayclj.raylib.Texture/$LAYOUT) set-texture))

(def get-texture-array
  (get-array-fn (rayclj.raylib.Texture/$LAYOUT) get-texture))

(def set-texture-array
  (set-array-fn (rayclj.raylib.Texture/$LAYOUT) set-texture))

(defn get-render-texture
  "RenderTexture, fbo for texture rendering
  unsigned int id // OpenGL framebuffer object id
  Texture texture // Color buffer attachment texture
  Texture depth // Depth buffer attachment texture"
  [^MemorySegment seg]
  {:id (rayclj.raylib.RenderTexture/id$get seg),
   :texture (get-texture (rayclj.raylib.RenderTexture/texture$slice seg)),
   :depth (get-texture (rayclj.raylib.RenderTexture/depth$slice seg))})

(defn set-render-texture
  "RenderTexture, fbo for texture rendering
  unsigned int id // OpenGL framebuffer object id
  Texture texture // Color buffer attachment texture
  Texture depth // Depth buffer attachment texture"
  [^MemorySegment seg {:keys [id texture depth]}]
  (rayclj.raylib.RenderTexture/id$set seg id)
  (set-texture (rayclj.raylib.RenderTexture/texture$slice seg) texture)
  (set-texture (rayclj.raylib.RenderTexture/depth$slice seg) depth)
  seg)

(defn render-texture
  "RenderTexture, fbo for texture rendering
  unsigned int id // OpenGL framebuffer object id
  Texture texture // Color buffer attachment texture
  Texture depth // Depth buffer attachment texture"
  ([^Arena arena v]
   (set-render-texture (.allocate arena (rayclj.raylib.RenderTexture/$LAYOUT))
                       v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (render-texture rarena/*current-arena* v))))

(def render-texture-array
  (array-fn (rayclj.raylib.RenderTexture/$LAYOUT) set-render-texture))

(def get-render-texture-array
  (get-array-fn (rayclj.raylib.RenderTexture/$LAYOUT) get-render-texture))

(def set-render-texture-array
  (set-array-fn (rayclj.raylib.RenderTexture/$LAYOUT) set-render-texture))

(defn get-npatch-info
  "NPatchInfo, n-patch layout info
  Rectangle source // Texture source rectangle
  int left // Left border offset
  int top // Top border offset
  int right // Right border offset
  int bottom // Bottom border offset
  int layout // Layout of the n-patch: 3x3, 1x3 or 3x1"
  [^MemorySegment seg]
  {:source (get-rectangle (rayclj.raylib.NPatchInfo/source$slice seg)),
   :left (rayclj.raylib.NPatchInfo/left$get seg),
   :top (rayclj.raylib.NPatchInfo/top$get seg),
   :right (rayclj.raylib.NPatchInfo/right$get seg),
   :bottom (rayclj.raylib.NPatchInfo/bottom$get seg),
   :layout (rayclj.raylib.NPatchInfo/layout$get seg)})

(defn set-npatch-info
  "NPatchInfo, n-patch layout info
  Rectangle source // Texture source rectangle
  int left // Left border offset
  int top // Top border offset
  int right // Right border offset
  int bottom // Bottom border offset
  int layout // Layout of the n-patch: 3x3, 1x3 or 3x1"
  [^MemorySegment seg {:keys [source left top right bottom layout]}]
  (set-rectangle (rayclj.raylib.NPatchInfo/source$slice seg) source)
  (rayclj.raylib.NPatchInfo/left$set seg left)
  (rayclj.raylib.NPatchInfo/top$set seg top)
  (rayclj.raylib.NPatchInfo/right$set seg right)
  (rayclj.raylib.NPatchInfo/bottom$set seg bottom)
  (rayclj.raylib.NPatchInfo/layout$set seg layout)
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
   (set-npatch-info (.allocate arena (rayclj.raylib.NPatchInfo/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (npatch-info rarena/*current-arena* v))))

(def npatch-info-array
  (array-fn (rayclj.raylib.NPatchInfo/$LAYOUT) set-npatch-info))

(def get-npatch-info-array
  (get-array-fn (rayclj.raylib.NPatchInfo/$LAYOUT) get-npatch-info))

(def set-npatch-info-array
  (set-array-fn (rayclj.raylib.NPatchInfo/$LAYOUT) set-npatch-info))

(defn get-glyph-info
  "GlyphInfo, font characters glyphs info
  int value // Character value (Unicode)
  int offsetX // Character offset X when drawing
  int offsetY // Character offset Y when drawing
  int advanceX // Character advance position X
  Image image // Character image data"
  [^MemorySegment seg]
  {:value (rayclj.raylib.GlyphInfo/value$get seg),
   :offsetX (rayclj.raylib.GlyphInfo/offsetX$get seg),
   :offsetY (rayclj.raylib.GlyphInfo/offsetY$get seg),
   :advanceX (rayclj.raylib.GlyphInfo/advanceX$get seg),
   :image (get-image (rayclj.raylib.GlyphInfo/image$slice seg))})

(defn set-glyph-info
  "GlyphInfo, font characters glyphs info
  int value // Character value (Unicode)
  int offsetX // Character offset X when drawing
  int offsetY // Character offset Y when drawing
  int advanceX // Character advance position X
  Image image // Character image data"
  [^MemorySegment seg {:keys [value offsetX offsetY advanceX image]}]
  (rayclj.raylib.GlyphInfo/value$set seg value)
  (rayclj.raylib.GlyphInfo/offsetX$set seg offsetX)
  (rayclj.raylib.GlyphInfo/offsetY$set seg offsetY)
  (rayclj.raylib.GlyphInfo/advanceX$set seg advanceX)
  (set-image (rayclj.raylib.GlyphInfo/image$slice seg) image)
  seg)

(defn glyph-info
  "GlyphInfo, font characters glyphs info
  int value // Character value (Unicode)
  int offsetX // Character offset X when drawing
  int offsetY // Character offset Y when drawing
  int advanceX // Character advance position X
  Image image // Character image data"
  ([^Arena arena v]
   (set-glyph-info (.allocate arena (rayclj.raylib.GlyphInfo/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (glyph-info rarena/*current-arena* v))))

(def glyph-info-array
  (array-fn (rayclj.raylib.GlyphInfo/$LAYOUT) set-glyph-info))

(def get-glyph-info-array
  (get-array-fn (rayclj.raylib.GlyphInfo/$LAYOUT) get-glyph-info))

(def set-glyph-info-array
  (set-array-fn (rayclj.raylib.GlyphInfo/$LAYOUT) set-glyph-info))

(defn get-font
  "Font, font texture and GlyphInfo array data
  int baseSize // Base size (default chars height)
  int glyphCount // Number of glyph characters
  int glyphPadding // Padding around the glyph characters
  Texture2D texture // Texture atlas containing the glyphs
  Rectangle * recs // Rectangles in texture for the glyphs
  GlyphInfo * glyphs // Glyphs info data"
  [^MemorySegment seg]
  (let [array-size (rayclj.raylib.Font/glyphCount$get seg)]
    {:base-size (rayclj.raylib.Font/baseSize$get seg),
     :glyph-count array-size,
     :glyph-padding (rayclj.raylib.Font/glyphPadding$get seg),
     :texture (get-texture (rayclj.raylib.Font/texture$slice seg)),
     :recs (get-rectangle-array (rayclj.raylib.Font/recs$get seg) array-size),
     :glyphs (get-glyph-info-array (rayclj.raylib.Font/glyphs$get seg)
                                   array-size)}))

(defn set-font
  "Font, font texture and GlyphInfo array data
  int baseSize // Base size (default chars height)
  int glyphCount // Number of glyph characters
  int glyphPadding // Padding around the glyph characters
  Texture2D texture // Texture atlas containing the glyphs
  Rectangle * recs // Rectangles in texture for the glyphs
  GlyphInfo * glyphs // Glyphs info data"
  ([^Arena arena ^MemorySegment seg
    {:keys [base-size glyph-count glyph-padding texture recs glyphs]}]
   (rayclj.raylib.Font/baseSize$set seg base-size)
   (rayclj.raylib.Font/glyphCount$set seg glyph-count)
   (rayclj.raylib.Font/glyphPadding$set seg glyph-padding)
   (set-texture (rayclj.raylib.Font/texture$slice seg) texture)
   (rayclj.raylib.Font/recs$set seg (rectangle-array arena recs))
   (rayclj.raylib.Font/glyphs$set seg (glyph-info-array arena glyphs))
   seg)
  ([seg font] (set-font rarena/*current-arena* seg font)))

(defn font
  "Font, font texture and GlyphInfo array data
  int baseSize // Base size (default chars height)
  int glyphCount // Number of glyph characters
  int glyphPadding // Padding around the glyph characters
  Texture2D texture // Texture atlas containing the glyphs
  Rectangle * recs // Rectangles in texture for the glyphs
  GlyphInfo * glyphs // Glyphs info data"
  ([^Arena arena v] (set-font (.allocate arena (rayclj.raylib.Font/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (font rarena/*current-arena* v))))

(def font-array (array-fn (rayclj.raylib.Font/$LAYOUT) set-font))

(def get-font-array (get-array-fn (rayclj.raylib.Font/$LAYOUT) get-font))

(def set-font-array (set-array-fn (rayclj.raylib.Font/$LAYOUT) set-font))

(defn get-camera3d
  "Camera, defines position/orientation in 3d space
  Vector3 position // Camera position
  Vector3 target // Camera target it looks-at
  Vector3 up // Camera up vector (rotation over its axis)
  float fovy // Camera field-of-view aperture in Y (degrees) in perspective, used as near plane width in orthographic
  int projection // Camera projection: CAMERA_PERSPECTIVE or CAMERA_ORTHOGRAPHIC"
  [^MemorySegment seg]
  {:position (get-vector3 (rayclj.raylib.Camera3D/position$slice seg)),
   :target (get-vector3 (rayclj.raylib.Camera3D/target$slice seg)),
   :up (get-vector3 (rayclj.raylib.Camera3D/up$slice seg)),
   :fovy (rayclj.raylib.Camera3D/fovy$get seg),
   :projection (rayclj.raylib.Camera3D/projection$get seg)})

(defn set-camera3d
  "Camera, defines position/orientation in 3d space
  Vector3 position // Camera position
  Vector3 target // Camera target it looks-at
  Vector3 up // Camera up vector (rotation over its axis)
  float fovy // Camera field-of-view aperture in Y (degrees) in perspective, used as near plane width in orthographic
  int projection // Camera projection: CAMERA_PERSPECTIVE or CAMERA_ORTHOGRAPHIC"
  [^MemorySegment seg {:keys [position target up fovy projection]}]
  (set-vector3 (rayclj.raylib.Camera3D/position$slice seg) position)
  (set-vector3 (rayclj.raylib.Camera3D/target$slice seg) target)
  (set-vector3 (rayclj.raylib.Camera3D/up$slice seg) up)
  (rayclj.raylib.Camera3D/fovy$set seg fovy)
  (rayclj.raylib.Camera3D/projection$set seg projection)
  seg)

(defn camera3d
  "Camera, defines position/orientation in 3d space
  Vector3 position // Camera position
  Vector3 target // Camera target it looks-at
  Vector3 up // Camera up vector (rotation over its axis)
  float fovy // Camera field-of-view aperture in Y (degrees) in perspective, used as near plane width in orthographic
  int projection // Camera projection: CAMERA_PERSPECTIVE or CAMERA_ORTHOGRAPHIC"
  ([^Arena arena v]
   (set-camera3d (.allocate arena (rayclj.raylib.Camera3D/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (camera3d rarena/*current-arena* v))))

(def camera3d-array (array-fn (rayclj.raylib.Camera3D/$LAYOUT) set-camera3d))

(def get-camera3d-array
  (get-array-fn (rayclj.raylib.Camera3D/$LAYOUT) get-camera3d))

(def set-camera3d-array
  (set-array-fn (rayclj.raylib.Camera3D/$LAYOUT) set-camera3d))

(defn get-camera2d
  "Camera2D, defines position/orientation in 2d space
  Vector2 offset // Camera offset (displacement from target)
  Vector2 target // Camera target (rotation and zoom origin)
  float rotation // Camera rotation in degrees
  float zoom // Camera zoom (scaling), should be 1.0f by default"
  [^MemorySegment seg]
  {:offset (get-vector2 (rayclj.raylib.Camera2D/offset$slice seg)),
   :target (get-vector2 (rayclj.raylib.Camera2D/target$slice seg)),
   :rotation (rayclj.raylib.Camera2D/rotation$get seg),
   :zoom (rayclj.raylib.Camera2D/zoom$get seg)})

(defn set-camera2d
  "Camera2D, defines position/orientation in 2d space
  Vector2 offset // Camera offset (displacement from target)
  Vector2 target // Camera target (rotation and zoom origin)
  float rotation // Camera rotation in degrees
  float zoom // Camera zoom (scaling), should be 1.0f by default"
  [^MemorySegment seg {:keys [offset target rotation zoom]}]
  (set-vector2 (rayclj.raylib.Camera2D/offset$slice seg) offset)
  (set-vector2 (rayclj.raylib.Camera2D/target$slice seg) target)
  (rayclj.raylib.Camera2D/rotation$set seg rotation)
  (rayclj.raylib.Camera2D/zoom$set seg zoom)
  seg)

(defn camera2d
  "Camera2D, defines position/orientation in 2d space
  Vector2 offset // Camera offset (displacement from target)
  Vector2 target // Camera target (rotation and zoom origin)
  float rotation // Camera rotation in degrees
  float zoom // Camera zoom (scaling), should be 1.0f by default"
  ([^Arena arena v]
   (set-camera2d (.allocate arena (rayclj.raylib.Camera2D/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (camera2d rarena/*current-arena* v))))

(def camera2d-array (array-fn (rayclj.raylib.Camera2D/$LAYOUT) set-camera2d))

(def get-camera2d-array
  (get-array-fn (rayclj.raylib.Camera2D/$LAYOUT) get-camera2d))

(def set-camera2d-array
  (set-array-fn (rayclj.raylib.Camera2D/$LAYOUT) set-camera2d))

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
  {:vertexCount (rayclj.raylib.Mesh/vertexCount$get seg),
   :triangleCount (rayclj.raylib.Mesh/triangleCount$get seg),
   :vertices (rayclj.raylib.Mesh/vertices$get seg),
   :texcoords (rayclj.raylib.Mesh/texcoords$get seg),
   :texcoords2 (rayclj.raylib.Mesh/texcoords2$get seg),
   :normals (rayclj.raylib.Mesh/normals$get seg),
   :tangents (rayclj.raylib.Mesh/tangents$get seg),
   :colors (rayclj.raylib.Mesh/colors$get seg),
   :indices (rayclj.raylib.Mesh/indices$get seg),
   :animVertices (rayclj.raylib.Mesh/animVertices$get seg),
   :animNormals (rayclj.raylib.Mesh/animNormals$get seg),
   :boneIds (rayclj.raylib.Mesh/boneIds$get seg),
   :boneWeights (rayclj.raylib.Mesh/boneWeights$get seg),
   :vaoId (rayclj.raylib.Mesh/vaoId$get seg),
   :vboId (rayclj.raylib.Mesh/vboId$get seg)})

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
  (rayclj.raylib.Mesh/vertexCount$set seg vertexCount)
  (rayclj.raylib.Mesh/triangleCount$set seg triangleCount)
  (rayclj.raylib.Mesh/vertices$set seg vertices)
  (rayclj.raylib.Mesh/texcoords$set seg texcoords)
  (rayclj.raylib.Mesh/texcoords2$set seg texcoords2)
  (rayclj.raylib.Mesh/normals$set seg normals)
  (rayclj.raylib.Mesh/tangents$set seg tangents)
  (rayclj.raylib.Mesh/colors$set seg colors)
  (rayclj.raylib.Mesh/indices$set seg indices)
  (rayclj.raylib.Mesh/animVertices$set seg animVertices)
  (rayclj.raylib.Mesh/animNormals$set seg animNormals)
  (rayclj.raylib.Mesh/boneIds$set seg boneIds)
  (rayclj.raylib.Mesh/boneWeights$set seg boneWeights)
  (rayclj.raylib.Mesh/vaoId$set seg vaoId)
  (rayclj.raylib.Mesh/vboId$set seg vboId)
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
  ([^Arena arena v] (set-mesh (.allocate arena (rayclj.raylib.Mesh/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (mesh rarena/*current-arena* v))))

(def mesh-array (array-fn (rayclj.raylib.Mesh/$LAYOUT) set-mesh))

(def get-mesh-array (get-array-fn (rayclj.raylib.Mesh/$LAYOUT) get-mesh))

(def set-mesh-array (set-array-fn (rayclj.raylib.Mesh/$LAYOUT) set-mesh))

(defn get-shader
  "Shader
  unsigned int id // Shader program id
  int * locs // Shader locations array (RL_MAX_SHADER_LOCATIONS)"
  [^MemorySegment seg]
  {:id (rayclj.raylib.Shader/id$get seg),
   :locs (rayclj.raylib.Shader/locs$get seg)})

(defn set-shader
  "Shader
  unsigned int id // Shader program id
  int * locs // Shader locations array (RL_MAX_SHADER_LOCATIONS)"
  [^MemorySegment seg {:keys [id locs]}]
  (rayclj.raylib.Shader/id$set seg id)
  (rayclj.raylib.Shader/locs$set seg locs)
  seg)

(defn shader
  "Shader
  unsigned int id // Shader program id
  int * locs // Shader locations array (RL_MAX_SHADER_LOCATIONS)"
  ([^Arena arena v]
   (set-shader (.allocate arena (rayclj.raylib.Shader/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (shader rarena/*current-arena* v))))

(def shader-array (array-fn (rayclj.raylib.Shader/$LAYOUT) set-shader))

(def get-shader-array (get-array-fn (rayclj.raylib.Shader/$LAYOUT) get-shader))

(def set-shader-array (set-array-fn (rayclj.raylib.Shader/$LAYOUT) set-shader))

(defn get-material-map
  "MaterialMap
  Texture2D texture // Material map texture
  Color color // Material map color
  float value // Material map value"
  [^MemorySegment seg]
  {:texture (get-texture (rayclj.raylib.MaterialMap/texture$slice seg)),
   :color (get-color (rayclj.raylib.MaterialMap/color$slice seg)),
   :value (rayclj.raylib.MaterialMap/value$get seg)})

(defn set-material-map
  "MaterialMap
  Texture2D texture // Material map texture
  Color color // Material map color
  float value // Material map value"
  [^MemorySegment seg {:keys [texture color value]}]
  (set-texture (rayclj.raylib.MaterialMap/texture$slice seg) texture)
  (set-color (rayclj.raylib.MaterialMap/color$slice seg) color)
  (rayclj.raylib.MaterialMap/value$set seg value)
  seg)

(defn material-map
  "MaterialMap
  Texture2D texture // Material map texture
  Color color // Material map color
  float value // Material map value"
  ([^Arena arena v]
   (set-material-map (.allocate arena (rayclj.raylib.MaterialMap/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (material-map rarena/*current-arena* v))))

(def material-map-array
  (array-fn (rayclj.raylib.MaterialMap/$LAYOUT) set-material-map))

(def get-material-map-array
  (get-array-fn (rayclj.raylib.MaterialMap/$LAYOUT) get-material-map))

(def set-material-map-array
  (set-array-fn (rayclj.raylib.MaterialMap/$LAYOUT) set-material-map))

(defn get-material
  "Material, includes shader and maps
  Shader shader // Material shader
  MaterialMap * maps // Material maps array (MAX_MATERIAL_MAPS)
  float[4] params // Material generic parameters (if required)"
  [^MemorySegment seg]
  {:shader (get-shader (rayclj.raylib.Material/shader$slice seg)),
   :maps (rayclj.raylib.Material/maps$get seg),
   :params (get-float-array (rayclj.raylib.Material/params$slice seg) 4)})

(defn set-material
  "Material, includes shader and maps
  Shader shader // Material shader
  MaterialMap * maps // Material maps array (MAX_MATERIAL_MAPS)
  float[4] params // Material generic parameters (if required)"
  [^MemorySegment seg {:keys [shader maps params]}]
  (set-shader (rayclj.raylib.Material/shader$slice seg) shader)
  (rayclj.raylib.Material/maps$set seg maps)
  (set-float-array (rayclj.raylib.Material/params$slice seg) params 4)
  seg)

(defn material
  "Material, includes shader and maps
  Shader shader // Material shader
  MaterialMap * maps // Material maps array (MAX_MATERIAL_MAPS)
  float[4] params // Material generic parameters (if required)"
  ([^Arena arena v]
   (set-material (.allocate arena (rayclj.raylib.Material/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (material rarena/*current-arena* v))))

(def material-array (array-fn (rayclj.raylib.Material/$LAYOUT) set-material))

(def get-material-array
  (get-array-fn (rayclj.raylib.Material/$LAYOUT) get-material))

(def set-material-array
  (set-array-fn (rayclj.raylib.Material/$LAYOUT) set-material))

(defn get-transform
  "Transform, vertex transformation data
  Vector3 translation // Translation
  Quaternion rotation // Rotation
  Vector3 scale // Scale"
  [^MemorySegment seg]
  {:translation (get-vector3 (rayclj.raylib.Transform/translation$slice seg)),
   :rotation (get-vector4 (rayclj.raylib.Transform/rotation$slice seg)),
   :scale (get-vector3 (rayclj.raylib.Transform/scale$slice seg))})

(defn set-transform
  "Transform, vertex transformation data
  Vector3 translation // Translation
  Quaternion rotation // Rotation
  Vector3 scale // Scale"
  [^MemorySegment seg {:keys [translation rotation scale]}]
  (set-vector3 (rayclj.raylib.Transform/translation$slice seg) translation)
  (set-vector4 (rayclj.raylib.Transform/rotation$slice seg) rotation)
  (set-vector3 (rayclj.raylib.Transform/scale$slice seg) scale)
  seg)

(defn transform
  "Transform, vertex transformation data
  Vector3 translation // Translation
  Quaternion rotation // Rotation
  Vector3 scale // Scale"
  ([^Arena arena v]
   (set-transform (.allocate arena (rayclj.raylib.Transform/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (transform rarena/*current-arena* v))))

(def transform-array (array-fn (rayclj.raylib.Transform/$LAYOUT) set-transform))

(def get-transform-array
  (get-array-fn (rayclj.raylib.Transform/$LAYOUT) get-transform))

(def set-transform-array
  (set-array-fn (rayclj.raylib.Transform/$LAYOUT) set-transform))

(defn get-bone-info
  "Bone, skeletal animation bone
  char[32] name // Bone name
  int parent // Bone parent"
  [^MemorySegment seg]
  {:name (get-char-array (rayclj.raylib.BoneInfo/name$slice seg) 32),
   :parent (rayclj.raylib.BoneInfo/parent$get seg)})

(defn set-bone-info
  "Bone, skeletal animation bone
  char[32] name // Bone name
  int parent // Bone parent"
  [^MemorySegment seg {:keys [name parent]}]
  (set-char-array (rayclj.raylib.BoneInfo/name$slice seg) name 32)
  (rayclj.raylib.BoneInfo/parent$set seg parent)
  seg)

(defn bone-info
  "Bone, skeletal animation bone
  char[32] name // Bone name
  int parent // Bone parent"
  ([^Arena arena v]
   (set-bone-info (.allocate arena (rayclj.raylib.BoneInfo/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (bone-info rarena/*current-arena* v))))

(def bone-info-array (array-fn (rayclj.raylib.BoneInfo/$LAYOUT) set-bone-info))

(def get-bone-info-array
  (get-array-fn (rayclj.raylib.BoneInfo/$LAYOUT) get-bone-info))

(def set-bone-info-array
  (set-array-fn (rayclj.raylib.BoneInfo/$LAYOUT) set-bone-info))

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
  {:transform (get-matrix (rayclj.raylib.Model/transform$slice seg)),
   :meshCount (rayclj.raylib.Model/meshCount$get seg),
   :materialCount (rayclj.raylib.Model/materialCount$get seg),
   :meshes (rayclj.raylib.Model/meshes$get seg),
   :materials (rayclj.raylib.Model/materials$get seg),
   :meshMaterial (rayclj.raylib.Model/meshMaterial$get seg),
   :boneCount (rayclj.raylib.Model/boneCount$get seg),
   :bones (rayclj.raylib.Model/bones$get seg),
   :bindPose (rayclj.raylib.Model/bindPose$get seg)})

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
  (set-matrix (rayclj.raylib.Model/transform$slice seg) transform)
  (rayclj.raylib.Model/meshCount$set seg meshCount)
  (rayclj.raylib.Model/materialCount$set seg materialCount)
  (rayclj.raylib.Model/meshes$set seg meshes)
  (rayclj.raylib.Model/materials$set seg materials)
  (rayclj.raylib.Model/meshMaterial$set seg meshMaterial)
  (rayclj.raylib.Model/boneCount$set seg boneCount)
  (rayclj.raylib.Model/bones$set seg bones)
  (rayclj.raylib.Model/bindPose$set seg bindPose)
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
  ([^Arena arena v]
   (set-model (.allocate arena (rayclj.raylib.Model/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (model rarena/*current-arena* v))))

(def model-array (array-fn (rayclj.raylib.Model/$LAYOUT) set-model))

(def get-model-array (get-array-fn (rayclj.raylib.Model/$LAYOUT) get-model))

(def set-model-array (set-array-fn (rayclj.raylib.Model/$LAYOUT) set-model))

(defn get-model-animation
  "ModelAnimation
  int boneCount // Number of bones
  int frameCount // Number of animation frames
  BoneInfo * bones // Bones information (skeleton)
  Transform ** framePoses // Poses array by frame
  char[32] name // Animation name"
  [^MemorySegment seg]
  {:boneCount (rayclj.raylib.ModelAnimation/boneCount$get seg),
   :frameCount (rayclj.raylib.ModelAnimation/frameCount$get seg),
   :bones (rayclj.raylib.ModelAnimation/bones$get seg),
   :framePoses (rayclj.raylib.ModelAnimation/framePoses$get seg),
   :name (get-char-array (rayclj.raylib.ModelAnimation/name$slice seg) 32)})

(defn set-model-animation
  "ModelAnimation
  int boneCount // Number of bones
  int frameCount // Number of animation frames
  BoneInfo * bones // Bones information (skeleton)
  Transform ** framePoses // Poses array by frame
  char[32] name // Animation name"
  [^MemorySegment seg {:keys [boneCount frameCount bones framePoses name]}]
  (rayclj.raylib.ModelAnimation/boneCount$set seg boneCount)
  (rayclj.raylib.ModelAnimation/frameCount$set seg frameCount)
  (rayclj.raylib.ModelAnimation/bones$set seg bones)
  (rayclj.raylib.ModelAnimation/framePoses$set seg framePoses)
  (set-char-array (rayclj.raylib.ModelAnimation/name$slice seg) name 32)
  seg)

(defn model-animation
  "ModelAnimation
  int boneCount // Number of bones
  int frameCount // Number of animation frames
  BoneInfo * bones // Bones information (skeleton)
  Transform ** framePoses // Poses array by frame
  char[32] name // Animation name"
  ([^Arena arena v]
   (set-model-animation (.allocate arena (rayclj.raylib.ModelAnimation/$LAYOUT))
                        v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (model-animation rarena/*current-arena* v))))

(def model-animation-array
  (array-fn (rayclj.raylib.ModelAnimation/$LAYOUT) set-model-animation))

(def get-model-animation-array
  (get-array-fn (rayclj.raylib.ModelAnimation/$LAYOUT) get-model-animation))

(def set-model-animation-array
  (set-array-fn (rayclj.raylib.ModelAnimation/$LAYOUT) set-model-animation))

(defn get-ray
  "Ray, ray for raycasting
  Vector3 position // Ray position (origin)
  Vector3 direction // Ray direction"
  [^MemorySegment seg]
  {:position (get-vector3 (rayclj.raylib.Ray/position$slice seg)),
   :direction (get-vector3 (rayclj.raylib.Ray/direction$slice seg))})

(defn set-ray
  "Ray, ray for raycasting
  Vector3 position // Ray position (origin)
  Vector3 direction // Ray direction"
  [^MemorySegment seg {:keys [position direction]}]
  (set-vector3 (rayclj.raylib.Ray/position$slice seg) position)
  (set-vector3 (rayclj.raylib.Ray/direction$slice seg) direction)
  seg)

(defn ray
  "Ray, ray for raycasting
  Vector3 position // Ray position (origin)
  Vector3 direction // Ray direction"
  ([^Arena arena v] (set-ray (.allocate arena (rayclj.raylib.Ray/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (ray rarena/*current-arena* v))))

(def ray-array (array-fn (rayclj.raylib.Ray/$LAYOUT) set-ray))

(def get-ray-array (get-array-fn (rayclj.raylib.Ray/$LAYOUT) get-ray))

(def set-ray-array (set-array-fn (rayclj.raylib.Ray/$LAYOUT) set-ray))

(defn get-ray-collision
  "RayCollision, ray hit information
  bool hit // Did the ray hit something?
  float distance // Distance to the nearest hit
  Vector3 point // Point of the nearest hit
  Vector3 normal // Surface normal of hit"
  [^MemorySegment seg]
  {:hit (rayclj.raylib.RayCollision/hit$get seg),
   :distance (rayclj.raylib.RayCollision/distance$get seg),
   :point (get-vector3 (rayclj.raylib.RayCollision/point$slice seg)),
   :normal (get-vector3 (rayclj.raylib.RayCollision/normal$slice seg))})

(defn set-ray-collision
  "RayCollision, ray hit information
  bool hit // Did the ray hit something?
  float distance // Distance to the nearest hit
  Vector3 point // Point of the nearest hit
  Vector3 normal // Surface normal of hit"
  [^MemorySegment seg {:keys [hit distance point normal]}]
  (rayclj.raylib.RayCollision/hit$set seg hit)
  (rayclj.raylib.RayCollision/distance$set seg distance)
  (set-vector3 (rayclj.raylib.RayCollision/point$slice seg) point)
  (set-vector3 (rayclj.raylib.RayCollision/normal$slice seg) normal)
  seg)

(defn ray-collision
  "RayCollision, ray hit information
  bool hit // Did the ray hit something?
  float distance // Distance to the nearest hit
  Vector3 point // Point of the nearest hit
  Vector3 normal // Surface normal of hit"
  ([^Arena arena v]
   (set-ray-collision (.allocate arena (rayclj.raylib.RayCollision/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (ray-collision rarena/*current-arena* v))))

(def ray-collision-array
  (array-fn (rayclj.raylib.RayCollision/$LAYOUT) set-ray-collision))

(def get-ray-collision-array
  (get-array-fn (rayclj.raylib.RayCollision/$LAYOUT) get-ray-collision))

(def set-ray-collision-array
  (set-array-fn (rayclj.raylib.RayCollision/$LAYOUT) set-ray-collision))

(defn get-bounding-box
  "BoundingBox
  Vector3 min // Minimum vertex box-corner
  Vector3 max // Maximum vertex box-corner"
  [^MemorySegment seg]
  {:min (get-vector3 (rayclj.raylib.BoundingBox/min$slice seg)),
   :max (get-vector3 (rayclj.raylib.BoundingBox/max$slice seg))})

(defn set-bounding-box
  "BoundingBox
  Vector3 min // Minimum vertex box-corner
  Vector3 max // Maximum vertex box-corner"
  [^MemorySegment seg {:keys [min max]}]
  (set-vector3 (rayclj.raylib.BoundingBox/min$slice seg) min)
  (set-vector3 (rayclj.raylib.BoundingBox/max$slice seg) max)
  seg)

(defn bounding-box
  "BoundingBox
  Vector3 min // Minimum vertex box-corner
  Vector3 max // Maximum vertex box-corner"
  ([^Arena arena v]
   (set-bounding-box (.allocate arena (rayclj.raylib.BoundingBox/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (bounding-box rarena/*current-arena* v))))

(def bounding-box-array
  (array-fn (rayclj.raylib.BoundingBox/$LAYOUT) set-bounding-box))

(def get-bounding-box-array
  (get-array-fn (rayclj.raylib.BoundingBox/$LAYOUT) get-bounding-box))

(def set-bounding-box-array
  (set-array-fn (rayclj.raylib.BoundingBox/$LAYOUT) set-bounding-box))

(defn get-wave
  "Wave, audio wave data
  unsigned int frameCount // Total number of frames (considering channels)
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)
  void * data // Buffer data pointer"
  [^MemorySegment seg]
  {:frameCount (rayclj.raylib.Wave/frameCount$get seg),
   :sampleRate (rayclj.raylib.Wave/sampleRate$get seg),
   :sampleSize (rayclj.raylib.Wave/sampleSize$get seg),
   :channels (rayclj.raylib.Wave/channels$get seg),
   :data (rayclj.raylib.Wave/data$get seg)})

(defn set-wave
  "Wave, audio wave data
  unsigned int frameCount // Total number of frames (considering channels)
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)
  void * data // Buffer data pointer"
  [^MemorySegment seg {:keys [frameCount sampleRate sampleSize channels data]}]
  (rayclj.raylib.Wave/frameCount$set seg frameCount)
  (rayclj.raylib.Wave/sampleRate$set seg sampleRate)
  (rayclj.raylib.Wave/sampleSize$set seg sampleSize)
  (rayclj.raylib.Wave/channels$set seg channels)
  (rayclj.raylib.Wave/data$set seg data)
  seg)

(defn wave
  "Wave, audio wave data
  unsigned int frameCount // Total number of frames (considering channels)
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)
  void * data // Buffer data pointer"
  ([^Arena arena v] (set-wave (.allocate arena (rayclj.raylib.Wave/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (wave rarena/*current-arena* v))))

(def wave-array (array-fn (rayclj.raylib.Wave/$LAYOUT) set-wave))

(def get-wave-array (get-array-fn (rayclj.raylib.Wave/$LAYOUT) get-wave))

(def set-wave-array (set-array-fn (rayclj.raylib.Wave/$LAYOUT) set-wave))

(defn get-audio-stream
  "AudioStream, custom audio stream
  rAudioBuffer * buffer // Pointer to internal data used by the audio system
  rAudioProcessor * processor // Pointer to internal data processor, useful for audio effects
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)"
  [^MemorySegment seg]
  {:buffer (rayclj.raylib.AudioStream/buffer$get seg),
   :processor (rayclj.raylib.AudioStream/processor$get seg),
   :sampleRate (rayclj.raylib.AudioStream/sampleRate$get seg),
   :sampleSize (rayclj.raylib.AudioStream/sampleSize$get seg),
   :channels (rayclj.raylib.AudioStream/channels$get seg)})

(defn set-audio-stream
  "AudioStream, custom audio stream
  rAudioBuffer * buffer // Pointer to internal data used by the audio system
  rAudioProcessor * processor // Pointer to internal data processor, useful for audio effects
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)"
  [^MemorySegment seg {:keys [buffer processor sampleRate sampleSize channels]}]
  (rayclj.raylib.AudioStream/buffer$set seg buffer)
  (rayclj.raylib.AudioStream/processor$set seg processor)
  (rayclj.raylib.AudioStream/sampleRate$set seg sampleRate)
  (rayclj.raylib.AudioStream/sampleSize$set seg sampleSize)
  (rayclj.raylib.AudioStream/channels$set seg channels)
  seg)

(defn audio-stream
  "AudioStream, custom audio stream
  rAudioBuffer * buffer // Pointer to internal data used by the audio system
  rAudioProcessor * processor // Pointer to internal data processor, useful for audio effects
  unsigned int sampleRate // Frequency (samples per second)
  unsigned int sampleSize // Bit depth (bits per sample): 8, 16, 32 (24 not supported)
  unsigned int channels // Number of channels (1-mono, 2-stereo, ...)"
  ([^Arena arena v]
   (set-audio-stream (.allocate arena (rayclj.raylib.AudioStream/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (audio-stream rarena/*current-arena* v))))

(def audio-stream-array
  (array-fn (rayclj.raylib.AudioStream/$LAYOUT) set-audio-stream))

(def get-audio-stream-array
  (get-array-fn (rayclj.raylib.AudioStream/$LAYOUT) get-audio-stream))

(def set-audio-stream-array
  (set-array-fn (rayclj.raylib.AudioStream/$LAYOUT) set-audio-stream))

(defn get-sound
  "Sound
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)"
  [^MemorySegment seg]
  {:stream (get-audio-stream (rayclj.raylib.Sound/stream$slice seg)),
   :frameCount (rayclj.raylib.Sound/frameCount$get seg)})

(defn set-sound
  "Sound
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)"
  [^MemorySegment seg {:keys [stream frameCount]}]
  (set-audio-stream (rayclj.raylib.Sound/stream$slice seg) stream)
  (rayclj.raylib.Sound/frameCount$set seg frameCount)
  seg)

(defn sound
  "Sound
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)"
  ([^Arena arena v]
   (set-sound (.allocate arena (rayclj.raylib.Sound/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (sound rarena/*current-arena* v))))

(def sound-array (array-fn (rayclj.raylib.Sound/$LAYOUT) set-sound))

(def get-sound-array (get-array-fn (rayclj.raylib.Sound/$LAYOUT) get-sound))

(def set-sound-array (set-array-fn (rayclj.raylib.Sound/$LAYOUT) set-sound))

(defn get-music
  "Music, audio stream, anything longer than ~10 seconds should be streamed
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)
  bool looping // Music looping enable
  int ctxType // Type of music context (audio filetype)
  void * ctxData // Audio context data, depends on type"
  [^MemorySegment seg]
  {:stream (get-audio-stream (rayclj.raylib.Music/stream$slice seg)),
   :frameCount (rayclj.raylib.Music/frameCount$get seg),
   :looping (rayclj.raylib.Music/looping$get seg),
   :ctxType (rayclj.raylib.Music/ctxType$get seg),
   :ctxData (rayclj.raylib.Music/ctxData$get seg)})

(defn set-music
  "Music, audio stream, anything longer than ~10 seconds should be streamed
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)
  bool looping // Music looping enable
  int ctxType // Type of music context (audio filetype)
  void * ctxData // Audio context data, depends on type"
  [^MemorySegment seg {:keys [stream frameCount looping ctxType ctxData]}]
  (set-audio-stream (rayclj.raylib.Music/stream$slice seg) stream)
  (rayclj.raylib.Music/frameCount$set seg frameCount)
  (rayclj.raylib.Music/looping$set seg looping)
  (rayclj.raylib.Music/ctxType$set seg ctxType)
  (rayclj.raylib.Music/ctxData$set seg ctxData)
  seg)

(defn music
  "Music, audio stream, anything longer than ~10 seconds should be streamed
  AudioStream stream // Audio stream
  unsigned int frameCount // Total number of frames (considering channels)
  bool looping // Music looping enable
  int ctxType // Type of music context (audio filetype)
  void * ctxData // Audio context data, depends on type"
  ([^Arena arena v]
   (set-music (.allocate arena (rayclj.raylib.Music/$LAYOUT)) v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (music rarena/*current-arena* v))))

(def music-array (array-fn (rayclj.raylib.Music/$LAYOUT) set-music))

(def get-music-array (get-array-fn (rayclj.raylib.Music/$LAYOUT) get-music))

(def set-music-array (set-array-fn (rayclj.raylib.Music/$LAYOUT) set-music))

(defn get-vr-device-info
  "VrDeviceInfo, Head-Mounted-Display device parameters
  int hResolution // Horizontal resolution in pixels
  int vResolution // Vertical resolution in pixels
  float hScreenSize // Horizontal size in meters
  float vScreenSize // Vertical size in meters
  float vScreenCenter // Screen center in meters
  float eyeToScreenDistance // Distance between eye and display in meters
  float lensSeparationDistance // Lens separation distance in meters
  float interpupillaryDistance // IPD (distance between pupils) in meters
  float[4] lensDistortionValues // Lens distortion constant parameters
  float[4] chromaAbCorrection // Chromatic aberration correction parameters"
  [^MemorySegment seg]
  {:hResolution (rayclj.raylib.VrDeviceInfo/hResolution$get seg),
   :vResolution (rayclj.raylib.VrDeviceInfo/vResolution$get seg),
   :hScreenSize (rayclj.raylib.VrDeviceInfo/hScreenSize$get seg),
   :vScreenSize (rayclj.raylib.VrDeviceInfo/vScreenSize$get seg),
   :vScreenCenter (rayclj.raylib.VrDeviceInfo/vScreenCenter$get seg),
   :eyeToScreenDistance (rayclj.raylib.VrDeviceInfo/eyeToScreenDistance$get
                          seg),
   :lensSeparationDistance
     (rayclj.raylib.VrDeviceInfo/lensSeparationDistance$get seg),
   :interpupillaryDistance
     (rayclj.raylib.VrDeviceInfo/interpupillaryDistance$get seg),
   :lensDistortionValues
     (get-float-array (rayclj.raylib.VrDeviceInfo/lensDistortionValues$slice
                        seg)
                      4),
   :chromaAbCorrection
     (get-float-array (rayclj.raylib.VrDeviceInfo/chromaAbCorrection$slice seg)
                      4)})

(defn set-vr-device-info
  "VrDeviceInfo, Head-Mounted-Display device parameters
  int hResolution // Horizontal resolution in pixels
  int vResolution // Vertical resolution in pixels
  float hScreenSize // Horizontal size in meters
  float vScreenSize // Vertical size in meters
  float vScreenCenter // Screen center in meters
  float eyeToScreenDistance // Distance between eye and display in meters
  float lensSeparationDistance // Lens separation distance in meters
  float interpupillaryDistance // IPD (distance between pupils) in meters
  float[4] lensDistortionValues // Lens distortion constant parameters
  float[4] chromaAbCorrection // Chromatic aberration correction parameters"
  [^MemorySegment seg
   {:keys [hResolution vResolution hScreenSize vScreenSize vScreenCenter
           eyeToScreenDistance lensSeparationDistance interpupillaryDistance
           lensDistortionValues chromaAbCorrection]}]
  (rayclj.raylib.VrDeviceInfo/hResolution$set seg hResolution)
  (rayclj.raylib.VrDeviceInfo/vResolution$set seg vResolution)
  (rayclj.raylib.VrDeviceInfo/hScreenSize$set seg hScreenSize)
  (rayclj.raylib.VrDeviceInfo/vScreenSize$set seg vScreenSize)
  (rayclj.raylib.VrDeviceInfo/vScreenCenter$set seg vScreenCenter)
  (rayclj.raylib.VrDeviceInfo/eyeToScreenDistance$set seg eyeToScreenDistance)
  (rayclj.raylib.VrDeviceInfo/lensSeparationDistance$set seg
                                                         lensSeparationDistance)
  (rayclj.raylib.VrDeviceInfo/interpupillaryDistance$set seg
                                                         interpupillaryDistance)
  (set-float-array (rayclj.raylib.VrDeviceInfo/lensDistortionValues$slice seg)
                   lensDistortionValues
                   4)
  (set-float-array (rayclj.raylib.VrDeviceInfo/chromaAbCorrection$slice seg)
                   chromaAbCorrection
                   4)
  seg)

(defn vr-device-info
  "VrDeviceInfo, Head-Mounted-Display device parameters
  int hResolution // Horizontal resolution in pixels
  int vResolution // Vertical resolution in pixels
  float hScreenSize // Horizontal size in meters
  float vScreenSize // Vertical size in meters
  float vScreenCenter // Screen center in meters
  float eyeToScreenDistance // Distance between eye and display in meters
  float lensSeparationDistance // Lens separation distance in meters
  float interpupillaryDistance // IPD (distance between pupils) in meters
  float[4] lensDistortionValues // Lens distortion constant parameters
  float[4] chromaAbCorrection // Chromatic aberration correction parameters"
  ([^Arena arena v]
   (set-vr-device-info (.allocate arena (rayclj.raylib.VrDeviceInfo/$LAYOUT))
                       v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (vr-device-info rarena/*current-arena* v))))

(def vr-device-info-array
  (array-fn (rayclj.raylib.VrDeviceInfo/$LAYOUT) set-vr-device-info))

(def get-vr-device-info-array
  (get-array-fn (rayclj.raylib.VrDeviceInfo/$LAYOUT) get-vr-device-info))

(def set-vr-device-info-array
  (set-array-fn (rayclj.raylib.VrDeviceInfo/$LAYOUT) set-vr-device-info))

(defn get-vr-stereo-config
  "VrStereoConfig, VR stereo rendering configuration for simulator
  Matrix[2] projection // VR projection matrices (per eye)
  Matrix[2] viewOffset // VR view offset matrices (per eye)
  float[2] leftLensCenter // VR left lens center
  float[2] rightLensCenter // VR right lens center
  float[2] leftScreenCenter // VR left screen center
  float[2] rightScreenCenter // VR right screen center
  float[2] scale // VR distortion scale
  float[2] scaleIn // VR distortion scale in"
  [^MemorySegment seg]
  {:projection
     (get-matrix-array (rayclj.raylib.VrStereoConfig/projection$slice seg) 2),
   :viewOffset
     (get-matrix-array (rayclj.raylib.VrStereoConfig/viewOffset$slice seg) 2),
   :leftLensCenter (get-float-array
                     (rayclj.raylib.VrStereoConfig/leftLensCenter$slice seg)
                     2),
   :rightLensCenter (get-float-array
                      (rayclj.raylib.VrStereoConfig/rightLensCenter$slice seg)
                      2),
   :leftScreenCenter (get-float-array
                       (rayclj.raylib.VrStereoConfig/leftScreenCenter$slice seg)
                       2),
   :rightScreenCenter
     (get-float-array (rayclj.raylib.VrStereoConfig/rightScreenCenter$slice seg)
                      2),
   :scale (get-float-array (rayclj.raylib.VrStereoConfig/scale$slice seg) 2),
   :scaleIn (get-float-array (rayclj.raylib.VrStereoConfig/scaleIn$slice seg)
                             2)})

(defn set-vr-stereo-config
  "VrStereoConfig, VR stereo rendering configuration for simulator
  Matrix[2] projection // VR projection matrices (per eye)
  Matrix[2] viewOffset // VR view offset matrices (per eye)
  float[2] leftLensCenter // VR left lens center
  float[2] rightLensCenter // VR right lens center
  float[2] leftScreenCenter // VR left screen center
  float[2] rightScreenCenter // VR right screen center
  float[2] scale // VR distortion scale
  float[2] scaleIn // VR distortion scale in"
  [^MemorySegment seg
   {:keys [projection viewOffset leftLensCenter rightLensCenter leftScreenCenter
           rightScreenCenter scale scaleIn]}]
  (set-matrix-array (rayclj.raylib.VrStereoConfig/projection$slice seg)
                    projection
                    2)
  (set-matrix-array (rayclj.raylib.VrStereoConfig/viewOffset$slice seg)
                    viewOffset
                    2)
  (set-float-array (rayclj.raylib.VrStereoConfig/leftLensCenter$slice seg)
                   leftLensCenter
                   2)
  (set-float-array (rayclj.raylib.VrStereoConfig/rightLensCenter$slice seg)
                   rightLensCenter
                   2)
  (set-float-array (rayclj.raylib.VrStereoConfig/leftScreenCenter$slice seg)
                   leftScreenCenter
                   2)
  (set-float-array (rayclj.raylib.VrStereoConfig/rightScreenCenter$slice seg)
                   rightScreenCenter
                   2)
  (set-float-array (rayclj.raylib.VrStereoConfig/scale$slice seg) scale 2)
  (set-float-array (rayclj.raylib.VrStereoConfig/scaleIn$slice seg) scaleIn 2)
  seg)

(defn vr-stereo-config
  "VrStereoConfig, VR stereo rendering configuration for simulator
  Matrix[2] projection // VR projection matrices (per eye)
  Matrix[2] viewOffset // VR view offset matrices (per eye)
  float[2] leftLensCenter // VR left lens center
  float[2] rightLensCenter // VR right lens center
  float[2] leftScreenCenter // VR left screen center
  float[2] rightScreenCenter // VR right screen center
  float[2] scale // VR distortion scale
  float[2] scaleIn // VR distortion scale in"
  ([^Arena arena v]
   (set-vr-stereo-config (.allocate arena
                                    (rayclj.raylib.VrStereoConfig/$LAYOUT))
                         v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (vr-stereo-config rarena/*current-arena* v))))

(def vr-stereo-config-array
  (array-fn (rayclj.raylib.VrStereoConfig/$LAYOUT) set-vr-stereo-config))

(def get-vr-stereo-config-array
  (get-array-fn (rayclj.raylib.VrStereoConfig/$LAYOUT) get-vr-stereo-config))

(def set-vr-stereo-config-array
  (set-array-fn (rayclj.raylib.VrStereoConfig/$LAYOUT) set-vr-stereo-config))

(defn get-file-path-list
  "File path list
  unsigned int capacity // Filepaths max entries
  unsigned int count // Filepaths entries count
  char ** paths // Filepaths entries"
  [^MemorySegment seg]
  {:capacity (rayclj.raylib.FilePathList/capacity$get seg),
   :count (rayclj.raylib.FilePathList/count$get seg),
   :paths (rayclj.raylib.FilePathList/paths$get seg)})

(defn set-file-path-list
  "File path list
  unsigned int capacity // Filepaths max entries
  unsigned int count // Filepaths entries count
  char ** paths // Filepaths entries"
  [^MemorySegment seg {:keys [capacity count paths]}]
  (rayclj.raylib.FilePathList/capacity$set seg capacity)
  (rayclj.raylib.FilePathList/count$set seg count)
  (rayclj.raylib.FilePathList/paths$set seg paths)
  seg)

(defn file-path-list
  "File path list
  unsigned int capacity // Filepaths max entries
  unsigned int count // Filepaths entries count
  char ** paths // Filepaths entries"
  ([^Arena arena v]
   (set-file-path-list (.allocate arena (rayclj.raylib.FilePathList/$LAYOUT))
                       v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (file-path-list rarena/*current-arena* v))))

(def file-path-list-array
  (array-fn (rayclj.raylib.FilePathList/$LAYOUT) set-file-path-list))

(def get-file-path-list-array
  (get-array-fn (rayclj.raylib.FilePathList/$LAYOUT) get-file-path-list))

(def set-file-path-list-array
  (set-array-fn (rayclj.raylib.FilePathList/$LAYOUT) set-file-path-list))

(defn get-automation-event
  "Automation event
  unsigned int frame // Event frame
  unsigned int type // Event type (AutomationEventType)
  int[4] params // Event parameters (if required)"
  [^MemorySegment seg]
  {:frame (rayclj.raylib.AutomationEvent/frame$get seg),
   :type (rayclj.raylib.AutomationEvent/type$get seg),
   :params (get-int-array (rayclj.raylib.AutomationEvent/params$slice seg) 4)})

(defn set-automation-event
  "Automation event
  unsigned int frame // Event frame
  unsigned int type // Event type (AutomationEventType)
  int[4] params // Event parameters (if required)"
  [^MemorySegment seg {:keys [frame type params]}]
  (rayclj.raylib.AutomationEvent/frame$set seg frame)
  (rayclj.raylib.AutomationEvent/type$set seg type)
  (set-int-array (rayclj.raylib.AutomationEvent/params$slice seg) params 4)
  seg)

(defn automation-event
  "Automation event
  unsigned int frame // Event frame
  unsigned int type // Event type (AutomationEventType)
  int[4] params // Event parameters (if required)"
  ([^Arena arena v]
   (set-automation-event (.allocate arena
                                    (rayclj.raylib.AutomationEvent/$LAYOUT))
                         v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (automation-event rarena/*current-arena* v))))

(def automation-event-array
  (array-fn (rayclj.raylib.AutomationEvent/$LAYOUT) set-automation-event))

(def get-automation-event-array
  (get-array-fn (rayclj.raylib.AutomationEvent/$LAYOUT) get-automation-event))

(def set-automation-event-array
  (set-array-fn (rayclj.raylib.AutomationEvent/$LAYOUT) set-automation-event))

(defn get-automation-event-list
  "Automation event list
  unsigned int capacity // Events max entries (MAX_AUTOMATION_EVENTS)
  unsigned int count // Events entries count
  AutomationEvent * events // Events entries"
  [^MemorySegment seg]
  {:capacity (rayclj.raylib.AutomationEventList/capacity$get seg),
   :count (rayclj.raylib.AutomationEventList/count$get seg),
   :events (rayclj.raylib.AutomationEventList/events$get seg)})

(defn set-automation-event-list
  "Automation event list
  unsigned int capacity // Events max entries (MAX_AUTOMATION_EVENTS)
  unsigned int count // Events entries count
  AutomationEvent * events // Events entries"
  [^MemorySegment seg {:keys [capacity count events]}]
  (rayclj.raylib.AutomationEventList/capacity$set seg capacity)
  (rayclj.raylib.AutomationEventList/count$set seg count)
  (rayclj.raylib.AutomationEventList/events$set seg events)
  seg)

(defn automation-event-list
  "Automation event list
  unsigned int capacity // Events max entries (MAX_AUTOMATION_EVENTS)
  unsigned int count // Events entries count
  AutomationEvent * events // Events entries"
  ([^Arena arena v]
   (set-automation-event-list
     (.allocate arena (rayclj.raylib.AutomationEventList/$LAYOUT))
     v))
  ([v]
   (if (clojure.core/instance? MemorySegment v)
     v
     (automation-event-list rarena/*current-arena* v))))

(def automation-event-list-array
  (array-fn (rayclj.raylib.AutomationEventList/$LAYOUT)
            set-automation-event-list))

(def get-automation-event-list-array
  (get-array-fn (rayclj.raylib.AutomationEventList/$LAYOUT)
                get-automation-event-list))

(def set-automation-event-list-array
  (set-array-fn (rayclj.raylib.AutomationEventList/$LAYOUT)
                set-automation-event-list))

