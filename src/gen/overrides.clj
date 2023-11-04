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
   ['(defn set-color [:CARETMemorySegment seg {:keys [r g b a]}]
       (raylib.Color/r$set seg (unchecked-byte r))
       (raylib.Color/g$set seg (unchecked-byte g))
       (raylib.Color/b$set seg (unchecked-byte b))
       (raylib.Color/a$set seg (unchecked-byte a))
       seg)]

   :color
   ['(declare predefined-colors)
    '(defn color
       ([:CARETArena arena c]
        (if (keyword? c)
          (predefined-colors c)
          (set-color (.allocate arena (raylib.Color/$LAYOUT)) c)))
       ([c]
        (if (instance? MemorySegment c)
          c
          (color rarena/*current-arena* c))))
    '(def predefined-colors
       (into
        {}
        (map
         (fn [[k v]] [k (color rarena/global-arena v)])
         renums/predefined-colors)))]})
