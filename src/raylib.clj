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

(defn set-vector2!
  ([^MemorySegment seg index [x y]]
   (Vector2/x$set seg index x)
   (Vector2/y$set seg index y)
   seg)
  ([^MemorySegment seg [x y]] (set-vector2! seg 0 [x y])))

(defn vector2
  "Vector2, 2 components"
  ([^Arena arena v] (set-vector2! (.allocate arena (Vector2/$LAYOUT)) v))
  ([v] (vector2 auto-arena v)))

(defn vector2-array
  ([^Arena arena vectors]
   (let [size (count vectors)
         seg (Vector2/allocateArray size arena)]
     (doall (map-indexed (fn [i v] (set-vector2! seg i v)) vectors))
     seg))
  ([vectors] (vector2-array auto-arena vectors)))

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

; // Window-related functions
; RLAPI void InitWindow(int width, int height, const char *title);  // Initialize window and OpenGL context
; RLAPI bool WindowShouldClose(void);                               // Check if KEY_ESCAPE pressed or Close icon pressed
; RLAPI void CloseWindow(void);                                     // Close window and unload OpenGL context
; RLAPI bool IsWindowReady(void);                                   // Check if window has been initialized successfully
; RLAPI bool IsWindowFullscreen(void);                              // Check if window is currently fullscreen
; RLAPI bool IsWindowHidden(void);                                  // Check if window is currently hidden (only PLATFORM_DESKTOP)
; RLAPI bool IsWindowMinimized(void);                               // Check if window is currently minimized (only PLATFORM_DESKTOP)
; RLAPI bool IsWindowMaximized(void);                               // Check if window is currently maximized (only PLATFORM_DESKTOP)
; RLAPI bool IsWindowFocused(void);                                 // Check if window is currently focused (only PLATFORM_DESKTOP)
; RLAPI bool IsWindowResized(void);                                 // Check if window has been resized last frame
; RLAPI bool IsWindowState(unsigned int flag);                      // Check if one specific window flag is enabled
; RLAPI void SetWindowState(unsigned int flags);                    // Set window configuration state using flags (only PLATFORM_DESKTOP)
; RLAPI void ClearWindowState(unsigned int flags);                  // Clear window configuration state flags
; RLAPI void ToggleFullscreen(void);                                // Toggle window state: fullscreen/windowed (only PLATFORM_DESKTOP)
; RLAPI void MaximizeWindow(void);                                  // Set window state: maximized, if resizable (only PLATFORM_DESKTOP)
; RLAPI void MinimizeWindow(void);                                  // Set window state: minimized, if resizable (only PLATFORM_DESKTOP)
; RLAPI void RestoreWindow(void);                                   // Set window state: not minimized/maximized (only PLATFORM_DESKTOP)
; RLAPI void SetWindowIcon(Image image);                            // Set icon for window (single image, RGBA 32bit, only PLATFORM_DESKTOP)
; RLAPI void SetWindowIcons(Image *images, int count);              // Set icon for window (multiple images, RGBA 32bit, only PLATFORM_DESKTOP)
; RLAPI void SetWindowTitle(const char *title);                     // Set title for window (only PLATFORM_DESKTOP)
; RLAPI void SetWindowPosition(int x, int y);                       // Set window position on screen (only PLATFORM_DESKTOP)
; RLAPI void SetWindowMonitor(int monitor);                         // Set monitor for the current window (fullscreen mode)
; RLAPI void SetWindowMinSize(int width, int height);               // Set window minimum dimensions (for FLAG_WINDOW_RESIZABLE)
; RLAPI void SetWindowSize(int width, int height);                  // Set window dimensions
; RLAPI void SetWindowOpacity(float opacity);                       // Set window opacity [0.0f..1.0f] (only PLATFORM_DESKTOP)
; RLAPI void *GetWindowHandle(void);                                // Get native window handle
; RLAPI int GetScreenWidth(void);                                   // Get current screen width
; RLAPI int GetScreenHeight(void);                                  // Get current screen height
; RLAPI int GetRenderWidth(void);                                   // Get current render width (it considers HiDPI)
; RLAPI int GetRenderHeight(void);                                  // Get current render height (it considers HiDPI)
; RLAPI int GetMonitorCount(void);                                  // Get number of connected monitors
; RLAPI int GetCurrentMonitor(void);                                // Get current connected monitor
; RLAPI Vector2 GetMonitorPosition(int monitor);                    // Get specified monitor position
; RLAPI int GetMonitorWidth(int monitor);                           // Get specified monitor width (current video mode used by monitor)
; RLAPI int GetMonitorHeight(int monitor);                          // Get specified monitor height (current video mode used by monitor)
; RLAPI int GetMonitorPhysicalWidth(int monitor);                   // Get specified monitor physical width in millimetres
; RLAPI int GetMonitorPhysicalHeight(int monitor);                  // Get specified monitor physical height in millimetres
; RLAPI int GetMonitorRefreshRate(int monitor);                     // Get specified monitor refresh rate
; RLAPI Vector2 GetWindowPosition(void);                            // Get window position XY on monitor
; RLAPI Vector2 GetWindowScaleDPI(void);                            // Get window scale DPI factor
; RLAPI const char *GetMonitorName(int monitor);                    // Get the human-readable, UTF-8 encoded name of the primary monitor
; RLAPI void SetClipboardText(const char *text);                    // Set clipboard text content
; RLAPI const char *GetClipboardText(void);                         // Get clipboard text content
; RLAPI void EnableEventWaiting(void);                              // Enable waiting for events on EndDrawing(), no automatic event polling
; RLAPI void DisableEventWaiting(void);                             // Disable waiting for events on EndDrawing(), automatic events polling

; // Custom frame control functions
; // NOTE: Those functions are intended for advance users that want full control over the frame processing
; // By default EndDrawing() does this job: draws everything + SwapScreenBuffer() + manage frame timing + PollInputEvents()
; // To avoid that behaviour and control frame processes manually, enable in config.h: SUPPORT_CUSTOM_FRAME_CONTROL
; RLAPI void SwapScreenBuffer(void);                                // Swap back buffer with front buffer (screen drawing)
; RLAPI void PollInputEvents(void);                                 // Register all input events
; RLAPI void WaitTime(double seconds);                              // Wait for some time (halt program execution)
;
; // Cursor-related functions
; RLAPI void ShowCursor(void);                                      // Shows cursor
; RLAPI void HideCursor(void);                                      // Hides cursor
; RLAPI bool IsCursorHidden(void);                                  // Check if cursor is not visible
; RLAPI void EnableCursor(void);                                    // Enables cursor (unlock cursor)
; RLAPI void DisableCursor(void);                                   // Disables cursor (lock cursor)
; RLAPI bool IsCursorOnScreen(void);                                // Check if cursor is on the screen

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

; // VR stereo config functions for VR simulator
; RLAPI VrStereoConfig LoadVrStereoConfig(VrDeviceInfo device);     // Load VR stereo config for VR simulator device parameters
; RLAPI void UnloadVrStereoConfig(VrStereoConfig config);           // Unload VR stereo config
;
; // Shader management functions
; // NOTE: Shader functionality is not available on OpenGL 1.1
; RLAPI Shader LoadShader(const char *vsFileName, const char *fsFileName);   // Load shader from files and bind default locations
; RLAPI Shader LoadShaderFromMemory(const char *vsCode, const char *fsCode); // Load shader from code strings and bind default locations
; RLAPI bool IsShaderReady(Shader shader);                                   // Check if a shader is ready
; RLAPI int GetShaderLocation(Shader shader, const char *uniformName);       // Get shader uniform location
; RLAPI int GetShaderLocationAttrib(Shader shader, const char *attribName);  // Get shader attribute location
; RLAPI void SetShaderValue(Shader shader, int locIndex, const void *value, int uniformType);               // Set shader uniform value
; RLAPI void SetShaderValueV(Shader shader, int locIndex, const void *value, int uniformType, int count);   // Set shader uniform value vector
; RLAPI void SetShaderValueMatrix(Shader shader, int locIndex, Matrix mat);         // Set shader uniform value (matrix 4x4)
; RLAPI void SetShaderValueTexture(Shader shader, int locIndex, Texture2D texture); // Set shader uniform value for texture (sampler2d)
; RLAPI void UnloadShader(Shader shader);                                    // Unload shader from GPU memory (VRAM)
;
; // Screen-space-related functions
; RLAPI Ray GetMouseRay(Vector2 mousePosition, Camera camera);      // Get a ray trace from mouse position
; RLAPI Matrix GetCameraMatrix(Camera camera);                      // Get camera transform matrix (view matrix)
; RLAPI Matrix GetCameraMatrix2D(Camera2D camera);                  // Get camera 2d transform matrix
; RLAPI Vector2 GetWorldToScreen(Vector3 position, Camera camera);  // Get the screen space position for a 3d world space position
; RLAPI Vector2 GetScreenToWorld2D(Vector2 position, Camera2D camera); // Get the world space position for a 2d camera screen space position
; RLAPI Vector2 GetWorldToScreenEx(Vector3 position, Camera camera, int width, int height); // Get size position for a 3d world space position
; RLAPI Vector2 GetWorldToScreen2D(Vector2 position, Camera2D camera); // Get the screen space position for a 2d camera world space position

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

; //------------------------------------------------------------------------------------
; // Gestures and Touch Handling Functions (Module: rgestures)
; //------------------------------------------------------------------------------------
; RLAPI void SetGesturesEnabled(unsigned int flags);      // Enable a set of gestures using flags
; RLAPI bool IsGestureDetected(int gesture);              // Check if a gesture have been detected
; RLAPI int GetGestureDetected(void);                     // Get latest detected gesture
; RLAPI float GetGestureHoldDuration(void);               // Get gesture hold time in milliseconds
; RLAPI Vector2 GetGestureDragVector(void);               // Get gesture drag vector
; RLAPI float GetGestureDragAngle(void);                  // Get gesture drag angle
; RLAPI Vector2 GetGesturePinchVector(void);              // Get gesture pinch delta
; RLAPI float GetGesturePinchAngle(void);                 // Get gesture pinch angle

; //------------------------------------------------------------------------------------
; // Camera System Functions (Module: rcamera)
; //------------------------------------------------------------------------------------
; RLAPI void UpdateCamera(Camera *camera, int mode);      // Update camera position for selected mode
; RLAPI void UpdateCameraPro(Camera *camera, Vector3 movement, Vector3 rotation, float zoom); // Update camera movement/rotation

;;------------------------------------------------------------------------------------
;; Basic Shapes Drawing Functions (Module: shapes)
;;------------------------------------------------------------------------------------

;; Set texture and rectangle to be used on shapes drawing
;; NOTE: It can be useful when using basic shapes and one single font,
;; defining a font char white rectangle would allow drawing everything in a single draw call

(defn set-shapes-texture!
  "Set texture and rectangle to be used on shapes drawing"
  [texture source] (raylib_h/SetShapesTexture texture source))

;; Basic shapes drawing functions

(defn draw-pixel!
  "Draw a pixel"
  [pos-x pos-y color] (raylib_h/DrawPixel pos-x pos-y color))

(defn draw-pixel-v!
  "Draw a pixel (Vector version)"
  [position color] (raylib_h/DrawPixelV position color))

(defn draw-line!
  "Draw a line"
  [start-pos-x start-pos-y end-pos-x end-pos-y color]
  (raylib_h/DrawLine start-pos-x start-pos-y end-pos-x end-pos-y color))

(defn draw-line-v!
  "Draw a line (Vector version)"
  [start-pos end-pos color] (raylib_h/DrawLineV start-pos end-pos color))

(defn draw-line-ex!
  "Draw a line defining thickness"
  [start-pos end-pos thick color] (raylib_h/DrawLineEx start-pos end-pos thick color))

(defn draw-line-bezier!
  "Draw a line using cubic-bezier curves in-out"
  [start-pos end-pos thick color] (raylib_h/DrawLineBezier start-pos end-pos thick color))

(defn draw-line-bezier-quad!
  "Draw line using quadratic bezier curves with a control point"
  [start-pos end-pos control-pos thick color]
  (raylib_h/DrawLineBezierQuad start-pos end-pos control-pos thick color))

(defn draw-line-bezier-cubic!
  "Draw line using cubic bezier curves with 2 control points"
  [start-pos end-pos start-control-pos end-control-pos thick color]
  (raylib_h/DrawLineBezierCubic start-pos end-pos start-control-pos end-control-pos thick color))

;;TODO: test this
; RLAPI void DrawLineStrip (Vector2 *points, int pointCount, Color color);
(defn draw-line-strip!
  "Draw lines sequence"
  [points point-count color] (raylib_h/DrawLineStrip points point-count color))

(defn draw-circle!
  "Draw a color-filled circle"
  [center-x center-y radius color] (raylib_h/DrawCircle center-x center-y radius color))

(defn draw-circle-sector!
  "Draw a piece of a circle"
  [center radius start-angle end-angle segments color]
  (raylib_h/DrawCircleSector center radius start-angle end-angle segments color))

(defn draw-circle-sector-lines!
  "Draw circle sector outline"
  [center radius start-angle end-angle segments color]
  (raylib_h/DrawCircleSectorLines center radius start-angle end-angle segments color))

(defn draw-circle-gradient!
  "Draw a gradient-filled circle"
  [center-x center-y radius color1 color2]
  (raylib_h/DrawCircleGradient center-x center-y radius color1 color2))

(defn draw-circle-v!
  "Draw a color-filled circle (Vector version)"
  [center-v radius color] (raylib_h/DrawCircleV center-v radius color))

(defn draw-circle-lines!
  "Draw circle outline"
  [center-x center-y radius color]
  (raylib_h/DrawCircleLines center-x center-y radius color))

(defn draw-ellipse!
  "Draw ellipse"
  [center-x center-y radius-h radius-v color]
  (raylib_h/DrawEllipse center-x center-y radius-h radius-v color))

(defn draw-ellipse-lines!
  "Draw ellipse outline"
  [center-x center-y radius-h radius-v color]
  (raylib_h/DrawEllipseLines center-x center-y radius-h radius-v color))

(defn draw-ring!
  "Draw ring"
  [center inner-radius outer-radius start-angle end-angle segments color]
  (raylib_h/DrawRing center inner-radius outer-radius start-angle end-angle segments color))

(defn draw-ring-lines!
  "Draw ring outline"
  [center inner-radius outer-radius start-angle end-angle segments color]
  (raylib_h/DrawRingLines center inner-radius outer-radius start-angle end-angle segments color))

(defn draw-rectangle!
  "Draw a color-filled rectangle"
  [pos-x pos-y width height color] (raylib_h/DrawRectangle pos-x pos-y width height color))

(defn draw-rectangle-v!
  "Draw a color-filled rectangle (Vector version)"
  [position size color] (raylib_h/DrawRectangleV position size color))

(defn draw-rectangle-rec!
  "Draw a color-filled rectangle"
  [rec color] (raylib_h/DrawRectangleRec rec color))

(defn draw-rectangle-pro!
  "Draw a color-filled rectangle with pro parameters"
  [rec origin rotation color] (raylib_h/DrawRectanglePro rec origin rotation color))

(defn draw-rectangle-gradient-v!
  "Draw a vertical-gradient-filled rectangle"
  [pos-x pos-y width height color1 color2]
  (raylib_h/DrawRectangleGradientV pos-x pos-y width height color1 color2))

(defn draw-rectangle-gradient-h!
  "Draw a horizontal-gradient-filled rectangle"
  [pos-x pos-y width height color1 color2]
  (raylib_h/DrawRectangleGradientH pos-x pos-y width height color1 color2))

(defn draw-rectangle-gradient-ex!
  "Draw a gradient-filled rectangle with custom vertex colors"
  [rec col1 col2 col3 col4] (raylib_h/DrawRectangleGradientEx rec col1 col2 col3 col4))

(defn draw-rectangle-lines!
  "Draw rectangle outline"
  [pos-x pos-y width height color] (raylib_h/DrawRectangleLines pos-x pos-y width height color))

(defn draw-rectangle-lines-ex!
  "Draw rectangle outline with extended parameters"
  [rec line-thick color] (raylib_h/DrawRectangleLinesEx rec line-thick color))

(defn draw-rectangle-rounded!
  "Draw rectangle with rounded edges"
  [rec roundness segments color] (raylib_h/DrawRectangleRounded rec roundness segments color))

(defn draw-rectangle-rounded-lines!
  "Draw rectangle with rounded edges outline"
  [rec roundness segments line-thick color]
  (raylib_h/DrawRectangleRoundedLines rec roundness segments line-thick color))

(defn draw-triangle!
  "Draw a color-filled triangle (vertex in counter-clockwise order!)"
  [v1 v2 v3 color] (raylib_h/DrawTriangle v1 v2 v3 color))

(defn draw-triangle-lines!
  "Draw triangle outline (vertex in counter-clockwise order!)"
  [v1 v2 v3 color] (raylib_h/DrawTriangleLines v1 v2 v3 color))

; TODO: test this
; RLAPI void DrawTriangleFan (Vector2 *points, int pointCount, Color color);
(defn draw-triangle-fan!
  "Draw a triangle fan defined by points (first vertex is the center)"
  [points point-count color] (raylib_h/DrawTriangleFan points point-count color))

; TODO: test this
; RLAPI void DrawTriangleStrip (Vector2 *points, int pointCount, Color color);
(defn draw-triangle-strip!
  "Draw a triangle strip defined by points"
  [points point-count color] (raylib_h/DrawTriangleStrip points point-count color))

(defn draw-poly!
  "Draw a regular polygon (Vector version)"
  [center sides radius rotation color] (raylib_h/DrawPoly center sides radius rotation color))

(defn draw-poly-lines!
  "Draw a polygon outline of n sides"
  [center sides radius rotation color] (raylib_h/DrawPolyLines center sides radius rotation color))

(defn draw-poly-lines-ex!
  "Draw a polygon outline of n sides with extended parameters"
  [center sides radius rotation line-thick color]
  (raylib_h/DrawPolyLinesEx center sides radius rotation line-thick color))

; // Basic shapes collision detection functions
; RLAPI bool CheckCollisionRecs(Rectangle rec1, Rectangle rec2);                                           // Check collision between two rectangles
; RLAPI bool CheckCollisionCircles(Vector2 center1, float radius1, Vector2 center2, float radius2);        // Check collision between two circles
; RLAPI bool CheckCollisionCircleRec(Vector2 center, float radius, Rectangle rec);                         // Check collision between circle and rectangle
; RLAPI bool CheckCollisionPointRec(Vector2 point, Rectangle rec);                                         // Check if point is inside rectangle
; RLAPI bool CheckCollisionPointCircle(Vector2 point, Vector2 center, float radius);                       // Check if point is inside circle
; RLAPI bool CheckCollisionPointTriangle(Vector2 point, Vector2 p1, Vector2 p2, Vector2 p3);               // Check if point is inside a triangle
; RLAPI bool CheckCollisionPointPoly(Vector2 point, Vector2 *points, int pointCount);                      // Check if point is within a polygon described by array of vertices
; RLAPI bool CheckCollisionLines(Vector2 startPos1, Vector2 endPos1, Vector2 startPos2, Vector2 endPos2, Vector2 *collisionPoint); // Check the collision between two lines defined by two points each, returns collision point by reference
; RLAPI bool CheckCollisionPointLine(Vector2 point, Vector2 p1, Vector2 p2, int threshold);                // Check if point belongs to line created between two points [p1] and [p2] with defined margin in pixels [threshold]
; RLAPI Rectangle GetCollisionRec(Rectangle rec1, Rectangle rec2);                                         // Get collision rectangle for two rectangles collision
;
; //------------------------------------------------------------------------------------
; // Texture Loading and Drawing Functions (Module: textures)
; //------------------------------------------------------------------------------------
;
; // Image loading functions
; // NOTE: These functions do not require GPU access
; RLAPI Image LoadImage(const char *fileName);                                                             // Load image from file into CPU memory (RAM)
; RLAPI Image LoadImageRaw(const char *fileName, int width, int height, int format, int headerSize);       // Load image from RAW file data
; RLAPI Image LoadImageAnim(const char *fileName, int *frames);                                            // Load image sequence from file (frames appended to image.data)
; RLAPI Image LoadImageFromMemory(const char *fileType, const unsigned char *fileData, int dataSize);      // Load image from memory buffer, fileType refers to extension: i.e. '.png'
; RLAPI Image LoadImageFromTexture(Texture2D texture);                                                     // Load image from GPU texture data
; RLAPI Image LoadImageFromScreen(void);                                                                   // Load image from screen buffer and (screenshot)
; RLAPI bool IsImageReady(Image image);                                                                    // Check if an image is ready
; RLAPI void UnloadImage(Image image);                                                                     // Unload image from CPU memory (RAM)
; RLAPI bool ExportImage(Image image, const char *fileName);                                               // Export image data to file, returns true on success
; RLAPI bool ExportImageAsCode(Image image, const char *fileName);                                         // Export image as code file defining an array of bytes, returns true on success
;
; // Image generation functions
; RLAPI Image GenImageColor(int width, int height, Color color);                                           // Generate image: plain color
; RLAPI Image GenImageGradientV(int width, int height, Color top, Color bottom);                           // Generate image: vertical gradient
; RLAPI Image GenImageGradientH(int width, int height, Color left, Color right);                           // Generate image: horizontal gradient
; RLAPI Image GenImageGradientRadial(int width, int height, float density, Color inner, Color outer);      // Generate image: radial gradient
; RLAPI Image GenImageChecked(int width, int height, int checksX, int checksY, Color col1, Color col2);    // Generate image: checked
; RLAPI Image GenImageWhiteNoise(int width, int height, float factor);                                     // Generate image: white noise
; RLAPI Image GenImagePerlinNoise(int width, int height, int offsetX, int offsetY, float scale);           // Generate image: perlin noise
; RLAPI Image GenImageCellular(int width, int height, int tileSize);                                       // Generate image: cellular algorithm, bigger tileSize means bigger cells
; RLAPI Image GenImageText(int width, int height, const char *text);                                       // Generate image: grayscale image from text data
;
; // Image manipulation functions
; RLAPI Image ImageCopy(Image image);                                                                      // Create an image duplicate (useful for transformations)
; RLAPI Image ImageFromImage(Image image, Rectangle rec);                                                  // Create an image from another image piece
; RLAPI Image ImageText(const char *text, int fontSize, Color color);                                      // Create an image from text (default font)
; RLAPI Image ImageTextEx(Font font, const char *text, float fontSize, float spacing, Color tint);         // Create an image from text (custom sprite font)
; RLAPI void ImageFormat(Image *image, int newFormat);                                                     // Convert image data to desired format
; RLAPI void ImageToPOT(Image *image, Color fill);                                                         // Convert image to POT (power-of-two)
; RLAPI void ImageCrop(Image *image, Rectangle crop);                                                      // Crop an image to a defined rectangle
; RLAPI void ImageAlphaCrop(Image *image, float threshold);                                                // Crop image depending on alpha value
; RLAPI void ImageAlphaClear(Image *image, Color color, float threshold);                                  // Clear alpha channel to desired color
; RLAPI void ImageAlphaMask(Image *image, Image alphaMask);                                                // Apply alpha mask to image
; RLAPI void ImageAlphaPremultiply(Image *image);                                                          // Premultiply alpha channel
; RLAPI void ImageBlurGaussian(Image *image, int blurSize);                                                // Apply Gaussian blur using a box blur approximation
; RLAPI void ImageResize(Image *image, int newWidth, int newHeight);                                       // Resize image (Bicubic scaling algorithm)
; RLAPI void ImageResizeNN(Image *image, int newWidth,int newHeight);                                      // Resize image (Nearest-Neighbor scaling algorithm)
; RLAPI void ImageResizeCanvas(Image *image, int newWidth, int newHeight, int offsetX, int offsetY, Color fill);  // Resize canvas and fill with color
; RLAPI void ImageMipmaps(Image *image);                                                                   // Compute all mipmap levels for a provided image
; RLAPI void ImageDither(Image *image, int rBpp, int gBpp, int bBpp, int aBpp);                            // Dither image data to 16bpp or lower (Floyd-Steinberg dithering)
; RLAPI void ImageFlipVertical(Image *image);                                                              // Flip image vertically
; RLAPI void ImageFlipHorizontal(Image *image);                                                            // Flip image horizontally
; RLAPI void ImageRotateCW(Image *image);                                                                  // Rotate image clockwise 90deg
; RLAPI void ImageRotateCCW(Image *image);                                                                 // Rotate image counter-clockwise 90deg
; RLAPI void ImageColorTint(Image *image, Color color);                                                    // Modify image color: tint
; RLAPI void ImageColorInvert(Image *image);                                                               // Modify image color: invert
; RLAPI void ImageColorGrayscale(Image *image);                                                            // Modify image color: grayscale
; RLAPI void ImageColorContrast(Image *image, float contrast);                                             // Modify image color: contrast (-100 to 100)
; RLAPI void ImageColorBrightness(Image *image, int brightness);                                           // Modify image color: brightness (-255 to 255)
; RLAPI void ImageColorReplace(Image *image, Color color, Color replace);                                  // Modify image color: replace color
; RLAPI Color *LoadImageColors(Image image);                                                               // Load color data from image as a Color array (RGBA - 32bit)
; RLAPI Color *LoadImagePalette(Image image, int maxPaletteSize, int *colorCount);                         // Load colors palette from image as a Color array (RGBA - 32bit)
; RLAPI void UnloadImageColors(Color *colors);                                                             // Unload color data loaded with LoadImageColors()
; RLAPI void UnloadImagePalette(Color *colors);                                                            // Unload colors palette loaded with LoadImagePalette()
; RLAPI Rectangle GetImageAlphaBorder(Image image, float threshold);                                       // Get image alpha border rectangle
; RLAPI Color GetImageColor(Image image, int x, int y);                                                    // Get image pixel color at (x, y) position
;
; // Image drawing functions
; // NOTE: Image software-rendering functions (CPU)
; RLAPI void ImageClearBackground(Image *dst, Color color);                                                // Clear image background with given color
; RLAPI void ImageDrawPixel(Image *dst, int posX, int posY, Color color);                                  // Draw pixel within an image
; RLAPI void ImageDrawPixelV(Image *dst, Vector2 position, Color color);                                   // Draw pixel within an image (Vector version)
; RLAPI void ImageDrawLine(Image *dst, int startPosX, int startPosY, int endPosX, int endPosY, Color color); // Draw line within an image
; RLAPI void ImageDrawLineV(Image *dst, Vector2 start, Vector2 end, Color color);                          // Draw line within an image (Vector version)
; RLAPI void ImageDrawCircle(Image *dst, int centerX, int centerY, int radius, Color color);               // Draw a filled circle within an image
; RLAPI void ImageDrawCircleV(Image *dst, Vector2 center, int radius, Color color);                        // Draw a filled circle within an image (Vector version)
; RLAPI void ImageDrawCircleLines(Image *dst, int centerX, int centerY, int radius, Color color);          // Draw circle outline within an image
; RLAPI void ImageDrawCircleLinesV(Image *dst, Vector2 center, int radius, Color color);                   // Draw circle outline within an image (Vector version)
; RLAPI void ImageDrawRectangle(Image *dst, int posX, int posY, int width, int height, Color color);       // Draw rectangle within an image
; RLAPI void ImageDrawRectangleV(Image *dst, Vector2 position, Vector2 size, Color color);                 // Draw rectangle within an image (Vector version)
; RLAPI void ImageDrawRectangleRec(Image *dst, Rectangle rec, Color color);                                // Draw rectangle within an image
; RLAPI void ImageDrawRectangleLines(Image *dst, Rectangle rec, int thick, Color color);                   // Draw rectangle lines within an image
; RLAPI void ImageDraw(Image *dst, Image src, Rectangle srcRec, Rectangle dstRec, Color tint);             // Draw a source image within a destination image (tint applied to source)
; RLAPI void ImageDrawText(Image *dst, const char *text, int posX, int posY, int fontSize, Color color);   // Draw text (using default font) within an image (destination)
; RLAPI void ImageDrawTextEx(Image *dst, Font font, const char *text, Vector2 position, float fontSize, float spacing, Color tint); // Draw text (custom sprite font) within an image (destination)
;
; // Texture loading functions
; // NOTE: These functions require GPU access
; RLAPI Texture2D LoadTexture(const char *fileName);                                                       // Load texture from file into GPU memory (VRAM)
; RLAPI Texture2D LoadTextureFromImage(Image image);                                                       // Load texture from image data
; RLAPI TextureCubemap LoadTextureCubemap(Image image, int layout);                                        // Load cubemap from image, multiple image cubemap layouts supported
; RLAPI RenderTexture2D LoadRenderTexture(int width, int height);                                          // Load texture for rendering (framebuffer)
; RLAPI bool IsTextureReady(Texture2D texture);                                                            // Check if a texture is ready
; RLAPI void UnloadTexture(Texture2D texture);                                                             // Unload texture from GPU memory (VRAM)
; RLAPI bool IsRenderTextureReady(RenderTexture2D target);                                                       // Check if a render texture is ready
; RLAPI void UnloadRenderTexture(RenderTexture2D target);                                                  // Unload render texture from GPU memory (VRAM)
; RLAPI void UpdateTexture(Texture2D texture, const void *pixels);                                         // Update GPU texture with new data
; RLAPI void UpdateTextureRec(Texture2D texture, Rectangle rec, const void *pixels);                       // Update GPU texture rectangle with new data
;
; // Texture configuration functions
; RLAPI void GenTextureMipmaps(Texture2D *texture);                                                        // Generate GPU mipmaps for a texture
; RLAPI void SetTextureFilter(Texture2D texture, int filter);                                              // Set texture scaling filter mode
; RLAPI void SetTextureWrap(Texture2D texture, int wrap);                                                  // Set texture wrapping mode
;
; // Texture drawing functions
; RLAPI void DrawTexture(Texture2D texture, int posX, int posY, Color tint);                               // Draw a Texture2D
; RLAPI void DrawTextureV(Texture2D texture, Vector2 position, Color tint);                                // Draw a Texture2D with position defined as Vector2
; RLAPI void DrawTextureEx(Texture2D texture, Vector2 position, float rotation, float scale, Color tint);  // Draw a Texture2D with extended parameters
; RLAPI void DrawTextureRec(Texture2D texture, Rectangle source, Vector2 position, Color tint);            // Draw a part of a texture defined by a rectangle
; RLAPI void DrawTexturePro(Texture2D texture, Rectangle source, Rectangle dest, Vector2 origin, float rotation, Color tint); // Draw a part of a texture defined by a rectangle with 'pro' parameters
; RLAPI void DrawTextureNPatch(Texture2D texture, NPatchInfo nPatchInfo, Rectangle dest, Vector2 origin, float rotation, Color tint); // Draws a texture (or part of it) that stretches or shrinks nicely
;
; // Color/pixel related functions
; RLAPI Color Fade(Color color, float alpha);                                 // Get color with alpha applied, alpha goes from 0.0f to 1.0f
; RLAPI int ColorToInt(Color color);                                          // Get hexadecimal value for a Color
; RLAPI Vector4 ColorNormalize(Color color);                                  // Get Color normalized as float [0..1]
; RLAPI Color ColorFromNormalized(Vector4 normalized);                        // Get Color from normalized values [0..1]
; RLAPI Vector3 ColorToHSV(Color color);                                      // Get HSV values for a Color, hue [0..360], saturation/value [0..1]
; RLAPI Color ColorFromHSV(float hue, float saturation, float value);         // Get a Color from HSV values, hue [0..360], saturation/value [0..1]
; RLAPI Color ColorTint(Color color, Color tint);                             // Get color multiplied with another color
; RLAPI Color ColorBrightness(Color color, float factor);                     // Get color with brightness correction, brightness factor goes from -1.0f to 1.0f
; RLAPI Color ColorContrast(Color color, float contrast);                     // Get color with contrast correction, contrast values between -1.0f and 1.0f
; RLAPI Color ColorAlpha(Color color, float alpha);                           // Get color with alpha applied, alpha goes from 0.0f to 1.0f
; RLAPI Color ColorAlphaBlend(Color dst, Color src, Color tint);              // Get src alpha-blended into dst color with tint
; RLAPI Color GetColor(unsigned int hexValue);                                // Get Color structure from hexadecimal value
; RLAPI Color GetPixelColor(void *srcPtr, int format);                        // Get Color from a source pixel pointer of certain format
; RLAPI void SetPixelColor(void *dstPtr, Color color, int format);            // Set color formatted into destination pixel pointer
; RLAPI int GetPixelDataSize(int width, int height, int format);              // Get pixel data size in bytes for certain format
;
; //------------------------------------------------------------------------------------
; // Font Loading and Text Drawing Functions (Module: text)
; //------------------------------------------------------------------------------------
;
; // Font loading/unloading functions
; RLAPI Font GetFontDefault(void);                                                            // Get the default Font
; RLAPI Font LoadFont(const char *fileName);                                                  // Load font from file into GPU memory (VRAM)
; RLAPI Font LoadFontEx(const char *fileName, int fontSize, int *fontChars, int glyphCount);  // Load font from file with extended parameters, use NULL for fontChars and 0 for glyphCount to load the default character set
; RLAPI Font LoadFontFromImage(Image image, Color key, int firstChar);                        // Load font from Image (XNA style)
; RLAPI Font LoadFontFromMemory(const char *fileType, const unsigned char *fileData, int dataSize, int fontSize, int *fontChars, int glyphCount); // Load font from memory buffer, fileType refers to extension: i.e. '.ttf'
; RLAPI bool IsFontReady(Font font);                                                          // Check if a font is ready
; RLAPI GlyphInfo *LoadFontData(const unsigned char *fileData, int dataSize, int fontSize, int *fontChars, int glyphCount, int type); // Load font data for further use
; RLAPI Image GenImageFontAtlas(const GlyphInfo *chars, Rectangle **recs, int glyphCount, int fontSize, int padding, int packMethod); // Generate image font atlas using chars info
; RLAPI void UnloadFontData(GlyphInfo *chars, int glyphCount);                                // Unload font chars info data (RAM)
; RLAPI void UnloadFont(Font font);                                                           // Unload font from GPU memory (VRAM)
; RLAPI bool ExportFontAsCode(Font font, const char *fileName);                               // Export font as code file, returns true on success

; // Text drawing functions
; RLAPI void DrawFPS(int posX, int posY);                                                     // Draw current FPS
; RLAPI void DrawText(const char *text, int posX, int posY, int fontSize, Color color);       // Draw text (using default font)

(defn draw-text!
  "Draw text (using default font)"
  [text x y font-size color] (raylib_h/DrawText text x y font-size color))

; RLAPI void DrawTextEx(Font font, const char *text, Vector2 position, float fontSize, float spacing, Color tint); // Draw text using font and additional parameters
; RLAPI void DrawTextPro(Font font, const char *text, Vector2 position, Vector2 origin, float rotation, float fontSize, float spacing, Color tint); // Draw text using Font and pro parameters (rotation)
; RLAPI void DrawTextCodepoint(Font font, int codepoint, Vector2 position, float fontSize, Color tint); // Draw one character (codepoint)
; RLAPI void DrawTextCodepoints(Font font, const int *codepoints, int count, Vector2 position, float fontSize, float spacing, Color tint); // Draw multiple character (codepoint)

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
