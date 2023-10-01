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

(defmacro ^:private with-segment
  "Creates a segment defined by klass and sets the fields.
  Names of the arguments significant!

  Example: '(with-segment arena Vector2 x y) expands to

    (let* [seg (.allocate arena (Vector2/$LAYOUT))]
      (Vector2/x$set seg x)
      (Vector2/y$set seg y)
      seg)
  "
  [^Arena arena klass & fields]
  (let [to-symbol (fn [& args] (symbol (apply str args)))
        set-fn (fn [sym] (to-symbol klass "/" sym "$set"))  ; eg: Vector2/x$set
        seg-sym (gensym "seg")
        ; eg: (Vector2/x$set seg x)
        set-field (fn [field] `(~(set-fn field) ~seg-sym ~(to-symbol field)))]
    `(let [~seg-sym (.allocate ~arena (~(to-symbol klass "/$LAYOUT")))]
       ~@(map set-field fields)
       ~seg-sym)))

(defn v2
  ([^Arena arena [x y]] (with-segment arena Vector2 x y))
  ([v] (v2 (Arena/ofAuto) v)))

(defn v3
  ([^Arena arena [x y z]] (with-segment arena Vector3 x y z))
  ([v] (v3 (Arena/ofAuto) v)))

(defn v4
  ([^Arena arena [x y z w]] (with-segment arena Vector4 x y z w))
  ([v] (v4 (Arena/ofAuto) v)))

(def quaternion v4) ;; 4 components (Vector4 alias)

(defn matrix
  "Matrix, 4x4 components, column major, OpenGL style, right-handed"
  [^Arena arena
   [m0 m4 m8 m12
    m1 m5 m9 m13
    m2 m6 m10 m14
    m3 m7 m11 m15]]
  (with-segment arena Matrix
    m0 m4 m8 m12
    m1 m5 m9 m13
    m2 m6 m10 m14
    m3 m7 m11 m15))

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
  {::null          0,        ;; Key: NULL, used for no key pressed
   ;; Alphanumeric keys
   ::apostrophe    39,       ;; Key: '
   ::comma         44,       ;; Key: ,
   ::minus         45,       ;; Key: -
   ::period        46,       ;; Key: .
   ::slash         47,       ;; Key: /
   ::zero          48,       ;; Key: 0
   ::one           49,       ;; Key: 1
   ::two           50,       ;; Key: 2
   ::three         51,       ;; Key: 3
   ::four          52,       ;; Key: 4
   ::five          53,       ;; Key: 5
   ::six           54,       ;; Key: 6
   ::seven         55,       ;; Key: 7
   ::eight         56,       ;; Key: 8
   ::nine          57,       ;; Key: 9
   ::semicolon     59,       ;; Key: ;
   ::equal         61,       ;; Key: =
   ::a             65,       ;; Key: A | a
   ::b             66,       ;; Key: B | b
   ::c             67,       ;; Key: C | c
   ::d             68,       ;; Key: D | d
   ::e             69,       ;; Key: E | e
   ::f             70,       ;; Key: F | f
   ::g             71,       ;; Key: G | g
   ::h             72,       ;; Key: H | h
   ::i             73,       ;; Key: I | i
   ::j             74,       ;; Key: J | j
   ::k             75,       ;; Key: K | k
   ::l             76,       ;; Key: L | l
   ::m             77,       ;; Key: M | m
   ::n             78,       ;; Key: N | n
   ::o             79,       ;; Key: O | o
   ::p             80,       ;; Key: P | p
   ::q             81,       ;; Key: Q | q
   ::r             82,       ;; Key: R | r
   ::s             83,       ;; Key: S | s
   ::t             84,       ;; Key: T | t
   ::u             85,       ;; Key: U | u
   ::v             86,       ;; Key: V | v
   ::w             87,       ;; Key: W | w
   ::x             88,       ;; Key: X | x
   ::y             89,       ;; Key: Y | y
   ::z             90,       ;; Key: Z | z
   ::left-bracket  91,       ;; Key: [
   ::backslash     92,       ;; Key: '\'
   ::right-bracket 93,       ;; Key: ]
   ::grave         96,       ;; Key: `
   ;; Function keys
   ::space         32,       ;; Key: Space
   ::escape        256,      ;; Key: Esc
   ::enter         257,      ;; Key: Enter
   ::tab           258,      ;; Key: Tab
   ::backspace     259,      ;; Key: Backspace
   ::insert        260,      ;; Key: Ins
   ::delete        261,      ;; Key: Del
   ::right         262,      ;; Key: Cursor right
   ::left          263,      ;; Key: Cursor left
   ::down          264,      ;; Key: Cursor down
   ::up            265,      ;; Key: Cursor up
   ::page-up       266,      ;; Key: Page up
   ::page-down     267,      ;; Key: Page down
   ::home          268,      ;; Key: Home
   ::end           269,      ;; Key: End
   ::caps-lock     280,      ;; Key: Caps lock
   ::scroll-lock   281,      ;; Key: Scroll down
   ::num-lock      282,      ;; Key: Num lock
   ::print-screen  283,      ;; Key: Print screen
   ::pause         284,      ;; Key: Pause
   ::f1            290,      ;; Key: F1
   ::f2            291,      ;; Key: F2
   ::f3            292,      ;; Key: F3
   ::f4            293,      ;; Key: F4
   ::f5            294,      ;; Key: F5
   ::f6            295,      ;; Key: F6
   ::f7            296,      ;; Key: F7
   ::f8            297,      ;; Key: F8
   ::f9            298,      ;; Key: F9
   ::f10           299,      ;; Key: F10
   ::f11           300,      ;; Key: F11
   ::f12           301,      ;; Key: F12
   ::left-shift    340,      ;; Key: Shift left
   ::left-control  341,      ;; Key: Control left
   ::left-alt      342,      ;; Key: Alt left
   ::left-super    343,      ;; Key: Super left
   ::right-shift   344,      ;; Key: Shift right
   ::right-control 345,      ;; Key: Control right
   ::right-alt     346,      ;; Key: Alt right
   ::right-super   347,      ;; Key: Super right
   ::kb-menu       348,      ;; Key: KB menu
   ;; Keypad keys
   ::kp-0          320,      ;; Key: Keypad 0
   ::kp-1          321,      ;; Key: Keypad 1
   ::kp-2          322,      ;; Key: Keypad 2
   ::kp-3          323,      ;; Key: Keypad 3
   ::kp-4          324,      ;; Key: Keypad 4
   ::kp-5          325,      ;; Key: Keypad 5
   ::kp-6          326,      ;; Key: Keypad 6
   ::kp-7          327,      ;; Key: Keypad 7
   ::kp-8          328,      ;; Key: Keypad 8
   ::kp-9          329,      ;; Key: Keypad 9
   ::kp-decimal    330,      ;; Key: Keypad .
   ::kp-divide     331,      ;; Key: Keypad /
   ::kp-multiply   332,      ;; Key: Keypad *
   ::kp-subtract   333,      ;; Key: Keypad -
   ::kp-add        334,      ;; Key: Keypad +
   ::kp-enter      335,      ;; Key: Keypad Enter
   ::kp-equal      336,      ;; Key: Keypad =
   ;; Android key buttons
   ::back          4,        ;; Key: Android back button
   ::menu          82,       ;; Key: Android menu button
   ::volume-up     24,       ;; Key: Android volume up button
   ::volume-down   25        ;; Key: Android volume down button
   })

;;
;; Window related functions
;;

(defn init-window!
  ([arena width height title] (raylib_h/InitWindow width height (string arena title)))
  ([width height title] (raylib_h/InitWindow width height (string title))))

(defn window-should-close? [] (raylib_h/WindowShouldClose))
(defn close-window! [] (raylib_h/CloseWindow))

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

;;
;; Input-related functions: keyboard
;;

(defn- keycode [key] (if (keyword? key) (keyboard-key key) key))

(defn is-key-pressed? [key] (raylib_h/IsKeyPressed (keycode key)))
(defn is-key-down? [key] (raylib_h/IsKeyDown (keycode key)))
(defn is-key-released? [key] (raylib_h/IsKeyReleased (keycode key)))
(defn is-key-up? [key] (raylib_h/IsKeyUp (keycode key)))
(defn set-exit-key! [key] (raylib_h/SetExitKey (keycode key)))
(defn get-key-pressed! [] (raylib_h/GetKeyPressed))
(defn get-char-pressed! [] (raylib_h/GetCharPressed))

;;
;; Basic shapes drawing functions
;;

(defn draw-circle-v!
  ([arena center-v radius c]
   (raylib_h/DrawCircleV (v2 arena center-v) radius (color arena c)))
  ([v radius c] (draw-circle-v! (Arena/ofAuto) v radius c)))

(comment
   ;; raylib example2
  )
