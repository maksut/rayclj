(ns raylib
  (:import
   [raylib
    raylib_h
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

;;
;; Enumerators Definition
;;

;; Keyboard keys (US keyboard layout)
;; NOTE: Use GetKeyPressed() to allow redefining required keys for alternative layouts
(def keyboard-key
  {::null          0        ;; Key: NULL, used for no key pressed
   ;; Alphanumeric keys
   ::apostrophe    39       ;; Key: '
   ::comma         44       ;; Key: ,
   ::minus         45       ;; Key: -
   ::period        46       ;; Key: .
   ::slash         47       ;; Key: /
   ::zero          48       ;; Key: 0
   ::one           49       ;; Key: 1
   ::two           50       ;; Key: 2
   ::three         51       ;; Key: 3
   ::four          52       ;; Key: 4
   ::five          53       ;; Key: 5
   ::six           54       ;; Key: 6
   ::seven         55       ;; Key: 7
   ::eight         56       ;; Key: 8
   ::nine          57       ;; Key: 9
   ::semicolon     59       ;; Key: ;
   ::equal         61       ;; Key: =
   ::a             65       ;; Key: A | a
   ::b             66       ;; Key: B | b
   ::c             67       ;; Key: C | c
   ::d             68       ;; Key: D | d
   ::e             69       ;; Key: E | e
   ::f             70       ;; Key: F | f
   ::g             71       ;; Key: G | g
   ::h             72       ;; Key: H | h
   ::i             73       ;; Key: I | i
   ::j             74       ;; Key: J | j
   ::k             75       ;; Key: K | k
   ::l             76       ;; Key: L | l
   ::m             77       ;; Key: M | m
   ::n             78       ;; Key: N | n
   ::o             79       ;; Key: O | o
   ::p             80       ;; Key: P | p
   ::q             81       ;; Key: Q | q
   ::r             82       ;; Key: R | r
   ::s             83       ;; Key: S | s
   ::t             84       ;; Key: T | t
   ::u             85       ;; Key: U | u
   ::v             86       ;; Key: V | v
   ::w             87       ;; Key: W | w
   ::x             88       ;; Key: X | x
   ::y             89       ;; Key: Y | y
   ::z             90       ;; Key: Z | z
   ::left-bracket  91       ;; Key: [
   ::backslash     92       ;; Key: '\'
   ::right-bracket 93       ;; Key: ]
   ::grave         96       ;; Key: `
   ;; Function keys
   ::space         32       ;; Key: Space
   ::escape        256      ;; Key: Esc
   ::enter         257      ;; Key: Enter
   ::tab           258      ;; Key: Tab
   ::backspace     259      ;; Key: Backspace
   ::insert        260      ;; Key: Ins
   ::delete        261      ;; Key: Del
   ::right         262      ;; Key: Cursor right
   ::left          263      ;; Key: Cursor left
   ::down          264      ;; Key: Cursor down
   ::up            265      ;; Key: Cursor up
   ::page-up       266      ;; Key: Page up
   ::page-down     267      ;; Key: Page down
   ::home          268      ;; Key: Home
   ::end           269      ;; Key: End
   ::caps-lock     280      ;; Key: Caps lock
   ::scroll-lock   281      ;; Key: Scroll down
   ::num-lock      282      ;; Key: Num lock
   ::print-screen  283      ;; Key: Print screen
   ::pause         284      ;; Key: Pause
   ::f1            290      ;; Key: F1
   ::f2            291      ;; Key: F2
   ::f3            292      ;; Key: F3
   ::f4            293      ;; Key: F4
   ::f5            294      ;; Key: F5
   ::f6            295      ;; Key: F6
   ::f7            296      ;; Key: F7
   ::f8            297      ;; Key: F8
   ::f9            298      ;; Key: F9
   ::f10           299      ;; Key: F10
   ::f11           300      ;; Key: F11
   ::f12           301      ;; Key: F12
   ::left-shift    340      ;; Key: Shift left
   ::left-control  341      ;; Key: Control left
   ::left-alt      342      ;; Key: Alt left
   ::left-super    343      ;; Key: Super left
   ::right-shift   344      ;; Key: Shift right
   ::right-control 345      ;; Key: Control right
   ::right-alt     346      ;; Key: Alt right
   ::right-super   347      ;; Key: Super right
   ::kb-menu       348      ;; Key: KB menu
   ;; Keypad keys
   ::kp-0          320      ;; Key: Keypad 0
   ::kp-1          321      ;; Key: Keypad 1
   ::kp-2          322      ;; Key: Keypad 2
   ::kp-3          323      ;; Key: Keypad 3
   ::kp-4          324      ;; Key: Keypad 4
   ::kp-5          325      ;; Key: Keypad 5
   ::kp-6          326      ;; Key: Keypad 6
   ::kp-7          327      ;; Key: Keypad 7
   ::kp-8          328      ;; Key: Keypad 8
   ::kp-9          329      ;; Key: Keypad 9
   ::kp-decimal    330      ;; Key: Keypad .
   ::kp-divide     331      ;; Key: Keypad /
   ::kp-multiply   332      ;; Key: Keypad *
   ::kp-subtract   333      ;; Key: Keypad -
   ::kp-add        334      ;; Key: Keypad +
   ::kp-enter      335      ;; Key: Keypad Enter
   ::kp-equal      336      ;; Key: Keypad =
   ;; Android key buttons
   ::back          4        ;; Key: Android back button
   ::menu          82       ;; Key: Android menu button
   ::volume-up     24       ;; Key: Android volume up button
   ::volume-down   25        ;; Key: Android volume down button
   })

;; Mouse buttons
(def mouse-button
  {::left    0 ;; Mouse button left
   ::right   1 ;; Mouse button right
   ::middle  2 ;; Mouse button middle (pressed wheel)
   ::side    3 ;; Mouse button side (advanced mouse device)
   ::extra   4 ;; Mouse button extra (advanced mouse device)
   ::forward 5 ;; Mouse button forward (advanced mouse device)
   ::back    6 ;; Mouse button back (advanced mouse device)
   })

;; Mouse cursor
(def ^:private mouse-cursor
  {::default        0   ;; Default pointer shape
   ::arrow          1   ;; Arrow shape
   ::ibeam          2   ;; Text writing cursor shape
   ::crosshair      3   ;; Cross shape
   ::pointing-hand  4   ;; Pointing hand cursor
   ::resize-ew      5   ;; Horizontal resize/move arrow shape
   ::resize-ns      6   ;; Vertical resize/move arrow shape
   ::resize-nwse    7   ;; Top-left to bottom-right diagonal resize/move arrow shape
   ::resize-nesw    8   ;; The top-right to bottom-left diagonal resize/move arrow shape
   ::resize-all     9   ;; The omnidirectional resize/move cursor shape
   ::not-allowed    10  ;; The operation-not-allowed shape
   })

;; Gamepad buttons
(def gamepad-button
  {::unknown           0   ;; Unknown button, just for error checking
   ::left-face-up      1   ;; Gamepad left DPAD up button
   ::left-face-right   2   ;; Gamepad left DPAD right button
   ::left-face-down    3   ;; Gamepad left DPAD down button
   ::left-face-left    4   ;; Gamepad left DPAD left button
   ::right-face-up     5   ;; Gamepad right button up (i.e. PS3: Triangle, Xbox: Y)
   ::right-face-right  6   ;; Gamepad right button right (i.e. PS3: Square, Xbox: X)
   ::right-face-down   7   ;; Gamepad right button down (i.e. PS3: Cross, Xbox: A)
   ::right-face-left   8   ;; Gamepad right button left (i.e. PS3: Circle, Xbox: B)
   ::left-trigger-1    9   ;; Gamepad top/back trigger left (first), it could be a trailing button
   ::left-trigger-2    10  ;; Gamepad top/back trigger left (second), it could be a trailing button
   ::right-trigger-1   11  ;; Gamepad top/back trigger right (one), it could be a trailing button
   ::right-trigger-2   12  ;; Gamepad top/back trigger right (second), it could be a trailing button
   ::middle-left       13  ;; Gamepad center buttons, left one (i.e. PS3: Select)
   ::middle            14  ;; Gamepad center buttons, middle one (i.e. PS3: PS, Xbox: XBOX)
   ::middle-right      15  ;; Gamepad center buttons, right one (i.e. PS3: Start)
   ::left-thumb        16  ;; Gamepad joystick pressed button left
   ::right-thumb       17  ;; Gamepad joystick pressed button right
   })

;; Gamepad axis
(def gamepad-axis
  {::left-x        0 ;; Gamepad left stick X axis
   ::left-y        1 ;; Gamepad left stick Y axis
   ::right-x       2 ;; Gamepad right stick X axis
   ::right-y       3 ;; Gamepad right stick Y axis
   ::left-trigger  4 ;; Gamepad back trigger left, pressure level: [1..-1]
   ::right-trigger 5 ;; Gamepad back trigger right, pressure level: [1..-1]
   })

;;
;; Utility Functions
;;

(defn keycode [key] (if (keyword? key) (keyboard-key key) key))

(def ^Arena auto-arena (Arena/ofAuto))
(def ^Arena global-arena (Arena/global))

(defn get-string [^MemorySegment seg]
  (.getUtf8String seg 0))

(defn string
  ([str] (.allocateUtf8String auto-arena str))
  ([^Arena arena str] (.allocateUtf8String arena str)))

;;
;; Structures Definition
;;

(defn get-vector2 [^MemorySegment seg]
  [(Vector2/x$get seg) (Vector2/y$get seg)])

(defn set-vector2! [^MemorySegment seg [x y]]
  (Vector2/x$set seg x)
  (Vector2/y$set seg y)
  seg)

(defn vector2
  "Vector2, 2 components"
  ([^Arena arena v]
   (set-vector2! (.allocate arena (Vector2/$LAYOUT)) v))
  ([v] (vector2 auto-arena v)))

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
  ([v] (vector3 auto-arena v)))

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
  ([v] (vector4 auto-arena v)))

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
  ([m] (matrix auto-arena m)))

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
  ([c] (color auto-arena c)))

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
  ([r] (rectangle auto-arena r)))

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
  ([img] (image auto-arena img)))

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
  ([texture] (texture auto-arena texture)))

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
  ([t] (render-texture auto-arena t)))

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
  ([camera] (camera-3d auto-arena camera)))

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
  ([camera] (camera-2d auto-arena camera)))

;;
;; some basic colors
;;

(def predefined-colors
  (into
   {}
   (map (fn [[k v]] [k (color global-arena v)])
        {::lightgray {:r 200 :g 200 :b 200 :a 255}  ;; Light Gray
         ::gray {:r 130 :g 130 :b 130 :a 255}       ;; Gray
         ::darkgray {:r 80 :g 80 :b 80 :a 255}      ;; Dark Gray
         ::yellow {:r 253 :g 249 :b 0 :a 255}       ;; Yellow
         ::gold {:r 255 :g 203 :b 0 :a 255}         ;; Gold
         ::orange {:r 255 :g 161 :b 0 :a 255}       ;; Orange
         ::pink {:r 255 :g 109 :b 194 :a 255}       ;; Pink
         ::red {:r 230 :g 41 :b 55 :a 255}          ;; Red
         ::maroon {:r 190 :g 33 :b 55 :a 255}       ;; Maroon
         ::green {:r 0 :g 228 :b 48 :a 255}         ;; Green
         ::lime {:r 0 :g 158 :b 47 :a 255}          ;; Lime
         ::darkgreen {:r 0 :g 117 :b 44 :a 255}     ;; Dark Green
         ::skyblue {:r 102 :g 191 :b 255 :a 255}    ;; Sky Blue
         ::blue {:r 0 :g 121 :b 241 :a 255}         ;; Blue
         ::darkblue {:r 0 :g 82 :b 172 :a 255}      ;; Dark Blue
         ::purple {:r 200 :g 122 :b 255 :a 255}     ;; Purple
         ::violet {:r 135 :g 60 :b 190 :a 255}      ;; Violet
         ::darkpurple {:r 112 :g 31 :b 126 :a 255}  ;; Dark Purple
         ::beige {:r 211 :g 176 :b 131 :a 255}      ;; Beige
         ::brown {:r 127 :g 106 :b 79 :a 255}       ;; Brown
         ::darkbrown {:r 76 :g 63 :b 47 :a 255}     ;; Dark Brown
         ::white {:r 255 :g 255 :b 255 :a 255}      ;; White
         ::black {:r 0 :g 0 :b 0 :a 255}            ;; Black
         ::blank {:r 0 :g 0 :b 0 :a 0}              ;; Blank (Transparent)
         ::magenta {:r 255 :g 0 :b 255 :a 255}      ;; Magenta
         ::raywhite {:r 245 :g 245 :b 245 :a 255}}  ;; Raylib White (raylib logo)
        )))
;;------------------------------------------------------------------------------------
;; Window and Graphics Device Functions (Module: core)
;;------------------------------------------------------------------------------------

(defn init-window!
  "Initialize window and OpenGL context"
  [width height title] (raylib_h/InitWindow width height title))

(defn window-should-close?
  "Check if KEY_ESCAPE pressed or Close icon pressed"
  [] (raylib_h/WindowShouldClose))

(defn close-window!
  "Close window and unload OpenGL context"
  [] (raylib_h/CloseWindow))

;;
;; Drawing-related functions
;;

(defn clear-background!
  "Set background color (framebuffer clear color)"
  [color] (raylib_h/ClearBackground color))

(defn begin-drawing!
  "Setup canvas (framebuffer) to start drawing"
  [] (raylib_h/BeginDrawing))

(defn end-drawing!
  "End canvas drawing and swap buffers (double buffering)"
  [] (raylib_h/EndDrawing))

(defn begin-mode-3d!
  "Begin 3D mode with custom camera (3D)"
  [camera] (raylib_h/BeginMode3D camera))

(defn end-mode-3d!
  "Ends 3D mode and returns to default 2D orthographic mode"
  [] (raylib_h/EndMode3D))

(defn draw-text!
  [text x y font-size color]
  (raylib_h/DrawText text x y font-size color))

; RLAPI void BeginTextureMode(RenderTexture2D target);              // Begin drawing to render texture
; RLAPI void EndTextureMode(void);                                  // Ends drawing to render texture
; RLAPI void BeginShaderMode(Shader shader);                        // Begin custom shader drawing
; RLAPI void EndShaderMode(void);                                   // End custom shader drawing (use default shader)
; RLAPI void BeginBlendMode(int mode);                              // Begin blending mode (alpha, additive, multiplied, subtract, custom)
; RLAPI void EndBlendMode(void);                                    // End blending mode (reset to default: alpha blending)
; RLAPI void BeginScissorMode(int x, int y, int width, int height); // Begin scissor mode (define screen area for following drawing)
; RLAPI void EndScissorMode(void);                                  // End scissor mode
; RLAPI void BeginVrStereoMode(VrStereoConfig config);              // Begin stereo rendering (requires VR simulator)
; RLAPI void EndVrStereoMode(void);                                 // End stereo rendering (requires VR simulator)

;;
;; Timing-related functions
;;

(defn set-target-fps! [fps] (raylib_h/SetTargetFPS fps))
(defn get-fps! [] (raylib_h/GetFPS))
(defn get-frame-time! [] (raylib_h/GetFrameTime))
(defn get-time! [] (raylib_h/GetTime))

;;------------------------------------------------------------------------------------
;; Input Handling Functions (Module: core)
;;------------------------------------------------------------------------------------

;; Input-related functions: keyboard
(defn is-key-pressed?
  "Check if a key has been pressed once"
  [key] (raylib_h/IsKeyPressed (keycode key)))

(defn is-key-down?
  "Check if a key has been pressed again (Only PLATFORM_DESKTOP)"
  [key] (raylib_h/IsKeyDown (keycode key)))

(defn is-key-released?
  "Check if a key is being pressed"
  [key] (raylib_h/IsKeyReleased (keycode key)))

(defn is-key-up?
  "Check if a key has been released once"
  [key] (raylib_h/IsKeyUp (keycode key)))

(defn set-exit-key!
  "Set a custom key to exit program (default is ESC)"
  [key] (raylib_h/SetExitKey (keycode key)))

(defn get-key-pressed!
  "Get key pressed (keycode), call it multiple times for keys queued, returns 0 when the queue is empty"
  [] (raylib_h/GetKeyPressed))

(defn get-char-pressed!
  "Get char pressed (unicode), call it multiple times for chars queued, returns 0 when the queue is empty"
  [] (raylib_h/GetCharPressed))

;; Input-related functions: gamepads
(defn is-gamepad-available?
  "Check if a gamepad is available"
  [gamepad] (raylib_h/IsGamepadAvailable gamepad))

;; TODO: string return value
(defn get-gamepad-name!
  "Get gamepad internal name id"
  [gamepad] (raylib_h/GetGamepadName gamepad))

(defn gamepad-button-down?
  "Check if a gamepad button is being pressed"
  [gamepad button] (raylib_h/IsGamepadButtonDown gamepad (gamepad-button button)))

(defn gamepad-button-released?
  "Check if a gamepad button has been released once"
  [gamepad button] (raylib_h/IsGamepadButtonReleased gamepad (gamepad-button button)))

(defn gamepad-button-up?
  "Check if a gamepad button is NOT being pressed"
  [gamepad button] (raylib_h/IsGamepadButtonUp gamepad (gamepad-button button)))

(defn get-gamepad-button-pressed!
  "Get the last gamepad button pressed"
  [] (gamepad-button (raylib_h/GetGamepadButtonPressed)))

(defn get-gamepad-axis-count!
  "Get gamepad axis count for a gamepad"
  [gamepad] (raylib_h/GetGamepadAxisCount gamepad))

(defn get-gamepad-axis-movement!
  "Get axis movement value for a gamepad axis"
  [gamepad axis] (raylib_h/GetGamepadAxisMovement gamepad (gamepad-axis axis)))

(defn set-gamepad-mappings!
  "Set internal gamepad mappings (SDL_GameControllerDB)"
  [mappings] (raylib_h/SetGamepadMappings mappings))

;; Input-related functions: mouse
(defn mouse-button-pressed?
  "Check if a mouse button has been pressed once"
  [button] (raylib_h/IsMouseButtonPressed (mouse-button button)))

(defn mouse-button-down?
  "Check if a mouse button is being pressed"
  [button] (raylib_h/IsMouseButtonDown (mouse-button button)))

(defn mouse-button-released?
  "Check if a mouse button has been released once"
  [button] (raylib_h/IsMouseButtonReleased (mouse-button button)))

(defn mouse-button-up?
  "Check if a mouse button is NOT being pressed"
  [button] (raylib_h/IsMouseButtonUp (mouse-button button)))

(defn get-mouse-x!
  "Get mouse position X"
  [] (raylib_h/GetMouseX))

(defn get-mouse-y!
  "Get mouse position Y"
  [] (raylib_h/GetMouseY))

(defn get-mouse-position!
  "Get mouse position XY"
  ([^Arena arena] (raylib_h/GetMousePosition arena))
  ([] (raylib_h/GetMousePosition auto-arena)))

(defn get-mouse-delta!
  "Get mouse delta between frames"
  ([^Arena arena] (raylib_h/GetMouseDelta arena))
  ([] (raylib_h/GetMouseDelta auto-arena)))

(defn set-mouse-position!
  "Set mouse position XY"
  [x y] (raylib_h/SetMousePosition x y))

(defn set-mouse-offset!
  "Set mouse offset"
  [offset-x offset-y] (raylib_h/SetMouseOffset offset-x offset-y))

(defn set-mouse-scale!
  "Set mouse scaling"
  [scale-x scale-y] (raylib_h/SetMouseScale scale-x scale-y))

(defn get-mouse-wheel-move!
  "Get mouse wheel movement for X or Y, whichever is larger"
  [] (raylib_h/GetMouseWheelMove))

(defn get-mouse-wheel-move-v!
  "Get mouse wheel movement for both X and Y"
  ([^Arena arena] (raylib_h/GetMouseWheelMoveV arena))
  ([] (raylib_h/GetMouseWheelMoveV auto-arena)))

(defn set-mouse-cursor!
  "Set mouse cursor"
  [cursor] (raylib_h/SetMouseCursor (mouse-cursor cursor)))

;; Input-related functions: touch
(defn get-touch-x!
  "Get touch position X for touch point 0 (relative to screen size)"
  [] (raylib_h/GetTouchX))

(defn get-touch-y!
  "Get touch position Y for touch point 0 (relative to screen size)"
  [] (raylib_h/GetTouchY))

(defn get-touch-position!
  "Get touch position XY for a touch point index (relative to screen size)"
  ([^Arena arena index] (raylib_h/GetTouchPosition arena index))
  ([index] (raylib_h/GetTouchPosition auto-arena index)))

(defn get-touch-point-id!
  "Get touch point identifier for given index"
  [index] (raylib_h/GetTouchPointId index))

(defn get-touch-point-count!
  "Get number of touch points"
  [] (raylib_h/GetTouchPointCount))

;;
;; Basic shapes drawing functions
;;

(defn draw-circle-v!
  [center-v radius color]
  (raylib_h/DrawCircleV center-v radius color))

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
         ([v#] (f# auto-arena v#)))))

  (defmacro seg->vec [getters]
    (let [seg (gensym "seg")]
      `(fn [~seg]
         [~@(map #(list % seg) getters)])))

  (def to-v2 (seg->vec [Vector2/x$get Vector2/y$get]))
  (def vector2 (vec->seg (Vector2/$LAYOUT) [Vector2/x$set Vector2/y$set]))

  (macroexpand '(vec->seg Vector2/$LAYOUT [Vector2/x$set Vector2/y$set]))
  (macroexpand '(seg->vec [Vector2/x$get Vector2/y$get]))

  (def f (vec->seg (Vector2/$LAYOUT) [Vector2/x$set Vector2/y$set]))
  (def g (seg->vec [Vector2/x$get Vector2/y$get]))

  (let [seg (f [1 2])]
    (g seg)))
