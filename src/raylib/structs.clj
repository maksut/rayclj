(ns raylib.structs
  (:require [raylib.arena :as rarena]
            [raylib.enums :as renums])
  (:import
   [raylib
    Vector2
    Vector3
    Vector4
    Matrix
    Color
    Rectangle
    Image
    Texture
    RenderTexture
    Camera3D
    Camera2D]
   [java.lang.foreign Arena MemorySegment]))

(set! *warn-on-reflection* true)

(defn passthrough "passthrough val if MemorySegment otherwise apply f"
  ([val f]
   (if (instance? MemorySegment val)
     val
     (f val)))
  ([val f arg1]
   (if (instance? MemorySegment val)
     val
     (f arg1 val))))

;;
;; Structures Definition
;;

(defn get-vector2 [^MemorySegment seg]
  [(Vector2/x$get seg) (Vector2/y$get seg)])

(defn set-vector2!
  ([^MemorySegment seg index [x y]]
   (Vector2/x$set seg index x)
   (Vector2/y$set seg index y)
   seg)
  ([^MemorySegment seg [x y]] (set-vector2! seg 0 [x y])))

(defn vector2
  "Vector2, 2 components"
  ([^Arena arena v] (set-vector2! (.allocate arena (Vector2/$LAYOUT)) v))
  ([v] (passthrough v vector2 rarena/*current-arena*)))

(defn vector2-array
  ([^Arena arena vectors]
   (let [size (count vectors)
         seg (Vector2/allocateArray size arena)]
     (doall (map-indexed (fn [i v] (set-vector2! seg i v)) vectors))
     seg))
  ([vectors] (vector2-array rarena/*current-arena* vectors)))

(defn get-vector3 [^MemorySegment seg]
  [(Vector3/x$get seg)
   (Vector3/y$get seg)
   (Vector3/z$get seg)])

(defn set-vector3! [^MemorySegment seg [x y z]]
  (Vector3/x$set seg x)
  (Vector3/y$set seg y)
  (Vector3/z$set seg z)
  seg)

(defn vector3
  "Vector3, 3 components"
  ([^Arena arena v]
   (set-vector3! (.allocate arena (Vector3/$LAYOUT)) v))
  ([v] (passthrough v vector3 rarena/*current-arena*)))

(defn get-vector4 [^MemorySegment seg]
  [(Vector4/x$get seg)
   (Vector4/y$get seg)
   (Vector4/z$get seg)
   (Vector4/z$get seg)])

(defn set-vector4! [^MemorySegment seg [x y z w]]
  (Vector4/x$set seg x)
  (Vector4/y$set seg y)
  (Vector4/z$set seg z)
  (Vector4/z$set seg w)
  seg)

(defn vector4
  "Vector4, 4 components"
  ([^Arena arena v]
   (set-vector4! (.allocate arena (Vector3/$LAYOUT)) v))
  ([v] (passthrough v vector4 rarena/*current-arena*)))

(def quaternion
  "Quaternion, 4 components (Vector4 alias)"
  vector4)

(defn get-matrix
  [^MemorySegment seg]
  [(Matrix/m0$get seg) (Matrix/m4$get seg) (Matrix/m8$get seg) (Matrix/m12$get seg)
   (Matrix/m1$get seg) (Matrix/m5$get seg) (Matrix/m9$get seg) (Matrix/m13$get seg)
   (Matrix/m2$get seg) (Matrix/m6$get seg) (Matrix/m10$get seg) (Matrix/m14$get seg)
   (Matrix/m3$get seg) (Matrix/m7$get seg) (Matrix/m11$get seg) (Matrix/m15$get seg)])

(defn set-matrix!
  [^MemorySegment seg
   [m0 m4 m8 m12
    m1 m5 m9 m13
    m2 m6 m10 m14
    m3 m7 m11 m15]]
  (Matrix/m0$set seg m0) (Matrix/m4$set seg m4) (Matrix/m8$set seg m8) (Matrix/m12$set seg m12)
  (Matrix/m1$set seg m1) (Matrix/m5$set seg m5) (Matrix/m9$set seg m9) (Matrix/m13$set seg m13)
  (Matrix/m2$set seg m2) (Matrix/m6$set seg m6) (Matrix/m10$set seg m10) (Matrix/m14$set seg m14)
  (Matrix/m3$set seg m3) (Matrix/m7$set seg m7) (Matrix/m11$set seg m11) (Matrix/m15$set seg m15)
  seg)

(defn matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed
   float m0, m4, m8, m12;  // Matrix first row (4 components)
   float m1, m5, m9, m13;  // Matrix second row (4 components)
   float m2, m6, m10, m14; // Matrix third row (4 components)
   float m3, m7, m11, m15; // Matrix fourth row (4 components)"
  ([^Arena arena m]
   (set-matrix! (.allocate arena (Vector3/$LAYOUT)) m))
  ([m] (passthrough m matrix rarena/*current-arena*)))

(defn get-color [^MemorySegment seg]
  {:r (Color/r$get seg)
   :g (Color/g$get seg)
   :b (Color/b$get seg)
   :a (Color/a$get seg)})

(defn set-color! [^MemorySegment seg {:keys [r g b a]}]
  (Color/r$set seg (unchecked-byte r))
  (Color/g$set seg (unchecked-byte g))
  (Color/b$set seg (unchecked-byte b))
  (Color/a$set seg (unchecked-byte a))
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
     (set-color! (.allocate arena (Color/$LAYOUT)) c)))
  ([c] (passthrough c color rarena/*current-arena*)))

(def predefined-colors
  (into
   {}
   (map
    (fn [[k v]] [k (color rarena/global-arena v)])
    renums/predefined-colors)))

(defn get-rectangle [^MemorySegment seg]
  {:x (Rectangle/x$get seg)
   :y (Rectangle/y$get seg)
   :width (Rectangle/width$get seg)
   :height (Rectangle/height$get seg)})

(defn set-rectangle! [^MemorySegment seg {:keys [x y width height]}]
  (Rectangle/x$set seg x)
  (Rectangle/y$set seg y)
  (Rectangle/width$set seg width)
  (Rectangle/height$set seg height)
  seg)

(defn rectangle
  "Rectangle, 4 components
   float x;      // Rectangle top-left corner position x
   float y;      // Rectangle top-left corner position y
   float width;  // Rectangle width
   float height; // Rectangle height"
  ([^Arena arena r]
   (set-rectangle! (.allocate arena (Rectangle/$LAYOUT)) r))
  ([r] (passthrough r rectangle rarena/*current-arena*)))

(defn get-image [^MemorySegment seg]
  {:data (Image/data$get seg)
   :width (Image/width$get seg)
   :height (Image/height$get seg)
   :mipmaps (Image/mipmaps$get seg)
   :format (Image/format$get seg)})

(defn set-image! [^MemorySegment seg {:keys [data width height mipmaps format]}]
  (Image/data$set seg data)
  (Image/width$set seg width)
  (Image/height$set seg height)
  (Image/mipmaps$set seg mipmaps)
  (Image/format$set seg format)
  seg)

;; TODO: figure out *data
(defn image
  "Image, pixel data stored in CPU memory (RAM)
   void *data;  // Image raw data
   int width;   // Image base width
   int height;  // Image base height
   int mipmaps; // Mipmap levels, 1 by default
   int format;  // Data format (PixelFormat type)"
  ([^Arena arena img]
   (set-image! (.allocate arena (Image/$LAYOUT)) img))
  ([img] (passthrough img image rarena/*current-arena*)))

(defn get-texture [^MemorySegment seg]
  {:id (Texture/id$get seg)
   :width (Texture/width$get seg)
   :height (Texture/height$get seg)
   :mipmaps (Texture/mipmaps$get seg)
   :format (Texture/format$get seg)})

(defn set-texture! [^MemorySegment seg {:keys [id width height mipmaps format]}]
  (Texture/id$set seg id)
  (Texture/width$set seg width)
  (Texture/height$set seg height)
  (Texture/mipmaps$set seg mipmaps)
  (Texture/format$set seg format)
  seg)

(defn texture
  "Texture, tex data stored in GPU memory (VRAM)
   unsigned int id; // OpenGL texture id
   int width;       // Texture base width
   int height;      // Texture base height
   int mipmaps;     // Mipmap levels, 1 by default
   int format;      // Data format (PixelFormat type)"
  ([^Arena arena t]
   (set-texture! (.allocate arena (Texture/$LAYOUT)) t))
  ([t] (passthrough t texture rarena/*current-arena*)))

(def texture-2d
  "Texture2D, same as Texture"
  texture)

(def texture-cubemap
  "TextureCubemap, same as Texture"
  texture)

(defn get-render-texture [^MemorySegment seg]
  {:id (RenderTexture/id$get seg)
   :texture (get-texture (RenderTexture/texture$slice seg))
   :depth (get-texture (RenderTexture/depth$slice seg))})

(defn set-render-texture! [^MemorySegment seg {:keys [id texture depth]}]
  (RenderTexture/id$set seg id)
  (set-texture! (RenderTexture/texture$slice seg) texture)
  (set-texture! (RenderTexture/depth$slice seg) depth)
  seg)

(defn render-texture
  "RenderTexture, fbo for texture rendering
   unsigned int id; // OpenGL framebuffer object id
   Texture texture; // Color buffer attachment texture
   Texture depth;   // Depth buffer attachment texture"
  ([^Arena arena t]
   (set-render-texture! (.allocate arena (RenderTexture/$LAYOUT)) t))
  ([t] (passthrough t render-texture rarena/*current-arena*)))

(def render-texture-2d
  "RenderTexture2D, same as RenderTexture"
  render-texture)

(defn get-camera-3d [^MemorySegment seg]
  {:position (get-vector3 (Camera3D/position$slice seg))
   :target (get-vector3 (Camera3D/target$slice seg))
   :up (get-vector3 (Camera3D/up$slice seg))
   :fovy (Camera3D/fovy$get seg)
   :projection (Camera3D/projection$get seg)})

(defn set-camera-3d! [^MemorySegment seg {:keys [position target up fovy projection]}]
  (set-vector3! (Camera3D/position$slice seg) position)
  (set-vector3! (Camera3D/target$slice seg) target)
  (set-vector3! (Camera3D/up$slice seg) up)
  (Camera3D/fovy$set seg fovy)
  (Camera3D/projection$set seg projection)
  seg)

(defn camera-3d
  "Camera, defines position/orientation in 3d space
   Vector3 position; // Camera position
   Vector3 target;   // Camera target it looks-at
   Vector3 up;       // Camera up vector (rotation over its axis)
   float fovy;       // Camera field-of-view aperture in Y (degrees) in perspective, used as near plane width in orthographic
   int projection;   // Camera projection: CAMERA_PERSPECTIVE or CAMERA_ORTHOGRAPHIC"
  ([^Arena arena camera]
   (set-camera-3d! (.allocate arena (Camera3D/$LAYOUT)) camera))
  ([camera] (passthrough camera camera-3d rarena/*current-arena*)))

(defn get-camera-2d [^MemorySegment seg]
  {:offset (get-vector2 (Camera2D/offset$slice seg))
   :target (get-vector2 (Camera2D/target$slice seg))
   :rotation (Camera2D/rotation$get seg)
   :zoom (Camera2D/zoom$get seg)})

(defn set-camera-2d! [^MemorySegment seg {:keys [offset target rotation zoom]}]
  (set-vector2! (Camera2D/offset$slice seg) offset)
  (set-vector2! (Camera2D/target$slice seg) target)
  (Camera2D/rotation$set seg rotation)
  (Camera2D/zoom$set seg zoom)
  seg)

(defn camera-2d
  "Camera2D, defines position/orientation in 2d space
   Vector2 offset; // Camera offset (displacement from target)
   Vector2 target; // Camera target (rotation and zoom origin)
   float rotation; // Camera rotation in degrees
   float zoom;     // Camera zoom (scaling), should be 1.0f by default"
  ([^Arena arena camera]
   (set-camera-2d! (.allocate arena (Camera2D/$LAYOUT)) camera))
  ([camera] (passthrough camera camera-2d rarena/*current-arena*)))

(comment
  ; an attempt to use macros. but hard to read
  (defmacro vec->seg [layout setters]
    (let [seg (gensym "seg")
          args (repeatedly (count setters) #(gensym "arg"))]
      `(fn f#
         ([^Arena arena# [~@args]]
          (let [~seg (.allocate arena# ~layout)]
            ~@(map #(list %1 seg %2) setters args)
            ~seg))
         ([v#] (f# *current-arena* v#)))))

  (defmacro seg->vec [getters]
    (let [seg (gensym "seg")]
      `(fn [~seg]
         [~@(map #(list % seg) getters)])))

  (macroexpand '(vec->seg Vector2/$LAYOUT [Vector2/x$set Vector2/y$set]))
  (macroexpand '(seg->vec [Vector2/x$get Vector2/y$get])))
