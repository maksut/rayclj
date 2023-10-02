(ns raylib
  (:import [raylib raylib_h Color Vector2 Vector3 Vector4 Matrix]
           [java.lang.foreign Arena MemorySegment]))

(set! *warn-on-reflection* true)

;;
;; some basic colors
;;

(def ^:private predefined-colors
  {::lightgray {:r 200 :g 200 :b 200 :a 255}  ;; Light Gray
   ::gray {:r 130 :g 130 :b 130 :a 255}       ;; Gray
   ::darkgray {:r 80 :g 80 :b 80 :a 255}      ;; Dark Gray
   ::yellow {:r 253 :g 249 :b 0 :a 255}       ;; Yellow
   ::gold {:r 255 :g 203 :b 0 :a 255}         ;; Gold
   ::orange {:r 255 :g 161 :b 0 :a 255}       ;; Orange
   ::ping {:r 255 :g 109 :b 194 :a 255}       ;; Pink
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
   ::raywhite {:r 245 :g 245 :b 245 :a 255}}) ;; Raylib White (raylib logo)

;;
;; utilities
;;

(defn string ^MemorySegment
  ([str] (string (Arena/ofAuto) str))
  ([^Arena arena str]
   (.allocateUtf8String arena str)))

;;
;; Structures Definition
;;

(defmacro vec->seg [layout setters]
  (let [seg (gensym "seg")
        args (repeatedly (count setters) #(gensym "arg"))]
    `(fn f#
       ([^Arena arena# [~@args]]
        (let [~seg (.allocate arena# ~layout)]
          ~@(map #(list %1 seg %2) setters args)
          ~seg))
       ([v#] (f# (Arena/ofAuto) v#)))))

(defmacro seg->vec [getters]
  (let [seg (gensym "seg")]
    `(fn [~seg]
       [~@(map #(list % seg) getters)])))

(def v2
  (vec->seg (Vector2/$LAYOUT) [Vector2/x$set Vector2/y$set]))

(def v3
  (vec->seg (Vector3/$LAYOUT) [Vector3/x$set Vector3/y$set Vector3/z$set]))

(def v4
  (vec->seg (Vector4/$LAYOUT) [Vector4/x$set Vector4/y$set Vector4/z$set Vector4/w$set]))

(def ^:private to-v2 (seg->vec [Vector2/x$get Vector2/y$get]))

(def quaternion v4) ;; 4 components (Vector4 alias)

(def matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed"
  (vec->seg (Matrix/$LAYOUT)
            [Matrix/m0$set Matrix/m4$set Matrix/m8$set Matrix/m12$set
             Matrix/m1$set Matrix/m5$set Matrix/m9$set Matrix/m13$set
             Matrix/m2$set Matrix/m6$set Matrix/m10$set Matrix/m14$set
             Matrix/m3$set Matrix/m7$set Matrix/m11$set Matrix/m15$set]))

(defn color
  ([^Arena arena c]
   (let [c (if (keyword? c) (predefined-colors c) c)
         {:keys [r g b a]} c
         seg (.allocate arena (Color/$LAYOUT))]
     (Color/r$set seg (unchecked-byte r))
     (Color/g$set seg (unchecked-byte g))
     (Color/b$set seg (unchecked-byte b))
     (Color/a$set seg (unchecked-byte a))
     seg))
  ([c] (color (Arena/ofAuto) c)))

;;
;; Enumerators Definition
;;

;; Keyboard keys (US keyboard layout)
;; NOTE: Use GetKeyPressed() to allow redefining required keys for alternative layouts
(def ^:private keyboard-key
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
(def ^:private mouse-button
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
(def ^:private gamepad-button
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
(def ^:private gamepad-axis
  {::left-x        0 ;; Gamepad left stick X axis
   ::left-y        1 ;; Gamepad left stick Y axis
   ::right-x       2 ;; Gamepad right stick X axis
   ::right-y       3 ;; Gamepad right stick Y axis
   ::left-trigger  4 ;; Gamepad back trigger left, pressure level: [1..-1]
   ::right-trigger 5 ;; Gamepad back trigger right, pressure level: [1..-1]
   })

;;------------------------------------------------------------------------------------
;; Window and Graphics Device Functions (Module: core)
;;------------------------------------------------------------------------------------

(defn init-window!
  "Initialize window and OpenGL context"
  ([arena width height title] (raylib_h/InitWindow width height (string arena title)))
  ([width height title] (raylib_h/InitWindow width height (string title))))

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
  ([arena c] (raylib_h/ClearBackground (color arena c)))
  ([c] (raylib_h/ClearBackground (color c))))

(defn begin-drawing! [] (raylib_h/BeginDrawing))
(defn end-drawing! [] (raylib_h/EndDrawing))

(defn draw-text!
  ([arena text x y font-size c]
   (raylib_h/DrawText (string arena text) x y font-size (color arena c)))
  ([text x y font-size c] (draw-text! (Arena/ofAuto) text x y font-size c)))

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
(defn- keycode [key] (if (keyword? key) (keyboard-key key) key))

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
  ([^Arena arena] (to-v2 (raylib_h/GetMousePosition arena)))
  ([] (get-mouse-position! (Arena/ofAuto))))

(defn get-mouse-delta!
  "Get mouse delta between frames"
  ([^Arena arena] (to-v2 (raylib_h/GetMouseDelta arena)))
  ([] (get-mouse-delta! (Arena/ofAuto))))

(defn set-mouse-position!
  "Set mouse position XY"
  [x y] (raylib_h/SetMousePosition x y))

(defn set-mouse-offset!
  "Set mouse offset"
  ([offset-x offset-y] (raylib_h/SetMouseOffset offset-x offset-y)))

(defn set-mouse-scale!
  "Set mouse scaling"
  ([scale-x scale-y] (raylib_h/SetMouseScale scale-x scale-y)))

(defn get-mouse-wheel-move!
  "Get mouse wheel movement for X or Y, whichever is larger"
  ([] (raylib_h/GetMouseWheelMove)))

;; TODO: return value is a vector2
(defn get-mouse-wheel-move-v!
  "Get mouse wheel movement for both X and Y"
  ([^Arena arena] (raylib_h/GetMouseWheelMoveV arena))
  ([] (get-mouse-wheel-move-v! (Arena/ofAuto))))

(defn set-mouse-cursor!
  "Set mouse cursor"
  ([cursor] (raylib_h/SetMouseCursor (mouse-cursor cursor))))

;; Input-related functions: touch
(defn get-touch-x!
  "Get touch position X for touch point 0 (relative to screen size)"
  ([] (raylib_h/GetTouchX)))

(defn get-touch-y!
  "Get touch position Y for touch point 0 (relative to screen size)"
  ([] (raylib_h/GetTouchY)))

;; TODO: return value is a vector2
(defn get-touch-position!
  "Get touch position XY for a touch point index (relative to screen size)"
  ([^Arena arena index] (raylib_h/GetTouchPosition arena index))
  ([index] (get-touch-position! (Arena/ofAuto) index)))

(defn get-touch-point-id!
  "Get touch point identifier for given index"
  ([index] (raylib_h/GetTouchPointId index)))

(defn get-touch-point-count!
  "Get number of touch points"
  ([] (raylib_h/GetTouchPointCount)))

;;
;; Basic shapes drawing functions
;;

(defn draw-circle-v!
  ([arena center-v radius c]
   (raylib_h/DrawCircleV (v2 arena center-v) radius (color arena c)))
  ([v radius c] (draw-circle-v! (Arena/ofAuto) v radius c)))

(comment
  (macroexpand '(vec->seg Vector2/$LAYOUT [Vector2/x$set Vector2/y$set]))
  (macroexpand '(seg->vec [Vector2/x$get Vector2/y$get]))

  (def f (vec->seg (Vector2/$LAYOUT) [Vector2/x$set Vector2/y$set]))
  (def g (seg->vec [Vector2/x$get Vector2/y$get]))

  (let [seg (f [1 2])]
    (g seg)))
