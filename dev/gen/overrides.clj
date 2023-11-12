(ns gen.overrides)

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
    "  unsigned char a; // Color alpha value")})

(def definitions
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
       ([:CARETArena arena c]
        (if (keyword? c)
          (predefined-colors c)
          (set-color (.allocate arena (rayclj.raylib.Color/$LAYOUT)) c)))
       ([c]
        (if (instance? MemorySegment c)
          c
          (color rarena/*current-arena* c))))
    '(def predefined-colors
       (into
        {}
        (map
         (fn [[k v]] [k (color rarena/global-arena v)])
         renums/predefined-colors)))]

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
       ([:CARETArena arena :CARETMemorySegment seg {:keys [base-size glyph-count glyph-padding texture recs glyphs]}]
        (rayclj.raylib.Font/baseSize$set seg base-size)
        (rayclj.raylib.Font/glyphCount$set seg glyph-count)
        (rayclj.raylib.Font/glyphPadding$set seg glyph-padding)
        (set-texture (rayclj.raylib.Font/texture$slice seg) texture)
        (rayclj.raylib.Font/recs$set seg (rectangle-array arena recs))
        (rayclj.raylib.Font/glyphs$set seg (glyph-info-array arena glyphs))
        seg)
       ([^MemorySegment seg font]
        (set-font rarena/*current-arena* seg font)))]})
