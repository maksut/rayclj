(ns gen.raylib.enums)

(def predefined-colors
  "Some Basic Colors
   NOTE: Custom raylib color palette for amazing visuals on WHITE background"
  {:lightgray {:r 200 :g 200 :b 200 :a 255}  ;; Light Gray
   :gray {:r 130 :g 130 :b 130 :a 255}       ;; Gray
   :darkgray {:r 80 :g 80 :b 80 :a 255}      ;; Dark Gray
   :yellow {:r 253 :g 249 :b 0 :a 255}       ;; Yellow
   :gold {:r 255 :g 203 :b 0 :a 255}         ;; Gold
   :orange {:r 255 :g 161 :b 0 :a 255}       ;; Orange
   :pink {:r 255 :g 109 :b 194 :a 255}       ;; Pink
   :red {:r 230 :g 41 :b 55 :a 255}          ;; Red
   :maroon {:r 190 :g 33 :b 55 :a 255}       ;; Maroon
   :green {:r 0 :g 228 :b 48 :a 255}         ;; Green
   :lime {:r 0 :g 158 :b 47 :a 255}          ;; Lime
   :darkgreen {:r 0 :g 117 :b 44 :a 255}     ;; Dark Green
   :skyblue {:r 102 :g 191 :b 255 :a 255}    ;; Sky Blue
   :blue {:r 0 :g 121 :b 241 :a 255}         ;; Blue
   :darkblue {:r 0 :g 82 :b 172 :a 255}      ;; Dark Blue
   :purple {:r 200 :g 122 :b 255 :a 255}     ;; Purple
   :violet {:r 135 :g 60 :b 190 :a 255}      ;; Violet
   :darkpurple {:r 112 :g 31 :b 126 :a 255}  ;; Dark Purple
   :beige {:r 211 :g 176 :b 131 :a 255}      ;; Beige
   :brown {:r 127 :g 106 :b 79 :a 255}       ;; Brown
   :darkbrown {:r 76 :g 63 :b 47 :a 255}     ;; Dark Brown
   :white {:r 255 :g 255 :b 255 :a 255}      ;; White
   :black {:r 0 :g 0 :b 0 :a 255}            ;; Black
   :blank {:r 0 :g 0 :b 0 :a 0}              ;; Blank (Transparent)
   :magenta {:r 255 :g 0 :b 255 :a 255}      ;; Magenta
   :raywhite {:r 245 :g 245 :b 245 :a 255}}) ;; Raylib White (raylib logo)

(def config-flags
  "System/Window config flags"
  {:vsync-hint               64 ;; Set to try enabling V-Sync on GPU
   :fullscreen-mode          2 ;; Set to run program in fullscreen
   :window-resizable         4 ;; Set to allow resizable window
   :window-undecorated       8 ;; Set to disable window decoration (frame and buttons)
   :window-hidden            128 ;; Set to hide window
   :window-minimized         512 ;; Set to minimize window (iconify)
   :window-maximized         1024 ;; Set to maximize window (expanded to monitor)
   :window-unfocused         2048 ;; Set to window non focused
   :window-topmost           4096 ;; Set to window always on top
   :window-always-run        256 ;; Set to allow windows running while minimized
   :window-transparent       16  ;; Set to allow transparent framebuffer
   :window-highdpi           8192 ;; Set to support HighDPI
   :window-mouse-passthrough 16384 ;; Set to support mouse passthrough, only supported when FLAG_WINDOW_UNDECORATED
   :borderless-windowed-mode 32768 ;; Set to run program in borderless windowed mode
   :msaa-4x-hint             32 ;; Set to try enabling MSAA 4X
   :interlaced-hint          65536 ;; Set to try enabling interlaced video format (for V3D)
  })

(def trace-log-level
  "Trace log level"
  {:all     0 ;; Display all logs
   :trace   1 ;; Trace logging, intended for internal use only
   :debug   2 ;; Debug logging, used for internal debugging, it should be disabled on release builds
   :info    3 ;; Info logging, used for program execution info
   :warning 4 ;; Warning logging, used on recoverable failures
   :error   5 ;; Error logging, used on unrecoverable failures
   :fatal   6 ;; Fatal logging, used to abort program: exit(EXIT_FAILURE)
   :none    7 ;; Disable logging
  })

(def keyboard-key
  "Keyboard keys (US keyboard layout)"
  {:null          0 ;; Key: NULL, used for no key pressed
   :apostrophe    39 ;; Key: '
   :comma         44 ;; Key: ,
   :minus         45 ;; Key: -
   :period        46 ;; Key: .
   :slash         47 ;; Key: /
   :zero          48 ;; Key: 0
   :one           49 ;; Key: 1
   :two           50 ;; Key: 2
   :three         51 ;; Key: 3
   :four          52 ;; Key: 4
   :five          53 ;; Key: 5
   :six           54 ;; Key: 6
   :seven         55 ;; Key: 7
   :eight         56 ;; Key: 8
   :nine          57 ;; Key: 9
   :semicolon     59 ;; Key: ;
   :equal         61 ;; Key: =
   :a             65 ;; Key: A | a
   :b             66 ;; Key: B | b
   :c             67 ;; Key: C | c
   :d             68 ;; Key: D | d
   :e             69 ;; Key: E | e
   :f             70 ;; Key: F | f
   :g             71 ;; Key: G | g
   :h             72 ;; Key: H | h
   :i             73 ;; Key: I | i
   :j             74 ;; Key: J | j
   :k             75 ;; Key: K | k
   :l             76 ;; Key: L | l
   :m             77 ;; Key: M | m
   :n             78 ;; Key: N | n
   :o             79 ;; Key: O | o
   :p             80 ;; Key: P | p
   :q             81 ;; Key: Q | q
   :r             82 ;; Key: R | r
   :s             83 ;; Key: S | s
   :t             84 ;; Key: T | t
   :u             85 ;; Key: U | u
   :v             86 ;; Key: V | v
   :w             87 ;; Key: W | w
   :x             88 ;; Key: X | x
   :y             89 ;; Key: Y | y
   :z             90 ;; Key: Z | z
   :left-bracket  91 ;; Key: [
   :backslash     92 ;; Key: '\'
   :right-bracket 93 ;; Key: ]
   :grave         96 ;; Key: `
   :space         32 ;; Key: Space
   :escape        256 ;; Key: Esc
   :enter         257 ;; Key: Enter
   :tab           258 ;; Key: Tab
   :backspace     259 ;; Key: Backspace
   :insert        260 ;; Key: Ins
   :delete        261 ;; Key: Del
   :right         262 ;; Key: Cursor right
   :left          263 ;; Key: Cursor left
   :down          264 ;; Key: Cursor down
   :up            265 ;; Key: Cursor up
   :page-up       266 ;; Key: Page up
   :page-down     267 ;; Key: Page down
   :home          268 ;; Key: Home
   :end           269 ;; Key: End
   :caps-lock     280 ;; Key: Caps lock
   :scroll-lock   281 ;; Key: Scroll down
   :num-lock      282 ;; Key: Num lock
   :print-screen  283 ;; Key: Print screen
   :pause         284 ;; Key: Pause
   :f1            290 ;; Key: F1
   :f2            291 ;; Key: F2
   :f3            292 ;; Key: F3
   :f4            293 ;; Key: F4
   :f5            294 ;; Key: F5
   :f6            295 ;; Key: F6
   :f7            296 ;; Key: F7
   :f8            297 ;; Key: F8
   :f9            298 ;; Key: F9
   :f10           299 ;; Key: F10
   :f11           300 ;; Key: F11
   :f12           301 ;; Key: F12
   :left-shift    340 ;; Key: Shift left
   :left-control  341 ;; Key: Control left
   :left-alt      342 ;; Key: Alt left
   :left-super    343 ;; Key: Super left
   :right-shift   344 ;; Key: Shift right
   :right-control 345 ;; Key: Control right
   :right-alt     346 ;; Key: Alt right
   :right-super   347 ;; Key: Super right
   :kb-menu       348 ;; Key: KB menu
   :kp-0          320 ;; Key: Keypad 0
   :kp-1          321 ;; Key: Keypad 1
   :kp-2          322 ;; Key: Keypad 2
   :kp-3          323 ;; Key: Keypad 3
   :kp-4          324 ;; Key: Keypad 4
   :kp-5          325 ;; Key: Keypad 5
   :kp-6          326 ;; Key: Keypad 6
   :kp-7          327 ;; Key: Keypad 7
   :kp-8          328 ;; Key: Keypad 8
   :kp-9          329 ;; Key: Keypad 9
   :kp-decimal    330 ;; Key: Keypad .
   :kp-divide     331 ;; Key: Keypad /
   :kp-multiply   332 ;; Key: Keypad *
   :kp-subtract   333 ;; Key: Keypad -
   :kp-add        334 ;; Key: Keypad +
   :kp-enter      335 ;; Key: Keypad Enter
   :kp-equal      336 ;; Key: Keypad =
   :back          4 ;; Key: Android back button
   :menu          82 ;; Key: Android menu button
   :volume-up     24 ;; Key: Android volume up button
   :volume-down   25 ;; Key: Android volume down button
  })

(def mouse-button
  "Mouse buttons"
  {:left    0 ;; Mouse button left
   :right   1 ;; Mouse button right
   :middle  2 ;; Mouse button middle (pressed wheel)
   :side    3 ;; Mouse button side (advanced mouse device)
   :extra   4 ;; Mouse button extra (advanced mouse device)
   :forward 5 ;; Mouse button forward (advanced mouse device)
   :back    6 ;; Mouse button back (advanced mouse device)
  })

(def mouse-cursor
  "Mouse cursor"
  {:default       0 ;; Default pointer shape
   :arrow         1 ;; Arrow shape
   :ibeam         2 ;; Text writing cursor shape
   :crosshair     3 ;; Cross shape
   :pointing-hand 4 ;; Pointing hand cursor
   :resize-ew     5 ;; Horizontal resize/move arrow shape
   :resize-ns     6 ;; Vertical resize/move arrow shape
   :resize-nwse   7 ;; Top-left to bottom-right diagonal resize/move arrow shape
   :resize-nesw   8 ;; The top-right to bottom-left diagonal resize/move arrow shape
   :resize-all    9 ;; The omnidirectional resize/move cursor shape
   :not-allowed   10 ;; The operation-not-allowed shape
  })

(def gamepad-button
  "Gamepad buttons"
  {:unknown          0 ;; Unknown button, just for error checking
   :left-face-up     1 ;; Gamepad left DPAD up button
   :left-face-right  2 ;; Gamepad left DPAD right button
   :left-face-down   3 ;; Gamepad left DPAD down button
   :left-face-left   4 ;; Gamepad left DPAD left button
   :right-face-up    5 ;; Gamepad right button up (i.e. PS3: Triangle, Xbox: Y)
   :right-face-right 6 ;; Gamepad right button right (i.e. PS3: Square, Xbox: X)
   :right-face-down  7 ;; Gamepad right button down (i.e. PS3: Cross, Xbox: A)
   :right-face-left  8 ;; Gamepad right button left (i.e. PS3: Circle, Xbox: B)
   :left-trigger-1   9 ;; Gamepad top/back trigger left (first), it could be a trailing button
   :left-trigger-2   10 ;; Gamepad top/back trigger left (second), it could be a trailing button
   :right-trigger-1  11 ;; Gamepad top/back trigger right (one), it could be a trailing button
   :right-trigger-2  12 ;; Gamepad top/back trigger right (second), it could be a trailing button
   :middle-left      13 ;; Gamepad center buttons, left one (i.e. PS3: Select)
   :middle           14 ;; Gamepad center buttons, middle one (i.e. PS3: PS, Xbox: XBOX)
   :middle-right     15 ;; Gamepad center buttons, right one (i.e. PS3: Start)
   :left-thumb       16 ;; Gamepad joystick pressed button left
   :right-thumb      17 ;; Gamepad joystick pressed button right
  })

(def gamepad-axis
  "Gamepad axis"
  {:left-x        0 ;; Gamepad left stick X axis
   :left-y        1 ;; Gamepad left stick Y axis
   :right-x       2 ;; Gamepad right stick X axis
   :right-y       3 ;; Gamepad right stick Y axis
   :left-trigger  4 ;; Gamepad back trigger left, pressure level: [1..-1]
   :right-trigger 5 ;; Gamepad back trigger right, pressure level: [1..-1]
  })

(def material-map-index
  "Material map index"
  {:albedo     0 ;; Albedo material (same as: MATERIAL_MAP_DIFFUSE)
   :metalness  1 ;; Metalness material (same as: MATERIAL_MAP_SPECULAR)
   :normal     2 ;; Normal material
   :roughness  3 ;; Roughness material
   :occlusion  4 ;; Ambient occlusion material
   :emission   5 ;; Emission material
   :height     6 ;; Heightmap material
   :cubemap    7 ;; Cubemap material (NOTE: Uses GL_TEXTURE_CUBE_MAP)
   :irradiance 8 ;; Irradiance material (NOTE: Uses GL_TEXTURE_CUBE_MAP)
   :prefilter  9 ;; Prefilter material (NOTE: Uses GL_TEXTURE_CUBE_MAP)
   :brdf       10 ;; Brdf material
  })

(def shader-location-index
  "Shader location index"
  {:vertex-position   0 ;; Shader location: vertex attribute: position
   :vertex-texcoord01 1 ;; Shader location: vertex attribute: texcoord01
   :vertex-texcoord02 2 ;; Shader location: vertex attribute: texcoord02
   :vertex-normal     3 ;; Shader location: vertex attribute: normal
   :vertex-tangent    4 ;; Shader location: vertex attribute: tangent
   :vertex-color      5 ;; Shader location: vertex attribute: color
   :matrix-mvp        6 ;; Shader location: matrix uniform: model-view-projection
   :matrix-view       7 ;; Shader location: matrix uniform: view (camera transform)
   :matrix-projection 8 ;; Shader location: matrix uniform: projection
   :matrix-model      9 ;; Shader location: matrix uniform: model (transform)
   :matrix-normal     10 ;; Shader location: matrix uniform: normal
   :vector-view       11 ;; Shader location: vector uniform: view
   :color-diffuse     12 ;; Shader location: vector uniform: diffuse color
   :color-specular    13 ;; Shader location: vector uniform: specular color
   :color-ambient     14 ;; Shader location: vector uniform: ambient color
   :map-albedo        15 ;; Shader location: sampler2d texture: albedo (same as: SHADER_LOC_MAP_DIFFUSE)
   :map-metalness     16 ;; Shader location: sampler2d texture: metalness (same as: SHADER_LOC_MAP_SPECULAR)
   :map-normal        17 ;; Shader location: sampler2d texture: normal
   :map-roughness     18 ;; Shader location: sampler2d texture: roughness
   :map-occlusion     19 ;; Shader location: sampler2d texture: occlusion
   :map-emission      20 ;; Shader location: sampler2d texture: emission
   :map-height        21 ;; Shader location: sampler2d texture: height
   :map-cubemap       22 ;; Shader location: samplerCube texture: cubemap
   :map-irradiance    23 ;; Shader location: samplerCube texture: irradiance
   :map-prefilter     24 ;; Shader location: samplerCube texture: prefilter
   :map-brdf          25 ;; Shader location: sampler2d texture: brdf
  })

(def shader-uniform-data-type
  "Shader uniform data type"
  {:float     0 ;; Shader uniform type: float
   :vec2      1 ;; Shader uniform type: vec2 (2 float)
   :vec3      2 ;; Shader uniform type: vec3 (3 float)
   :vec4      3 ;; Shader uniform type: vec4 (4 float)
   :int       4 ;; Shader uniform type: int
   :ivec2     5 ;; Shader uniform type: ivec2 (2 int)
   :ivec3     6 ;; Shader uniform type: ivec3 (3 int)
   :ivec4     7 ;; Shader uniform type: ivec4 (4 int)
   :sampler2d 8 ;; Shader uniform type: sampler2d
  })

(def shader-attribute-data-type
  "Shader attribute data types"
  {:float 0 ;; Shader attribute type: float
   :vec2  1 ;; Shader attribute type: vec2 (2 float)
   :vec3  2 ;; Shader attribute type: vec3 (3 float)
   :vec4  3 ;; Shader attribute type: vec4 (4 float)
  })

(def pixel-format
  "Pixel formats"
  {:uncompressed-grayscale    1 ;; 8 bit per pixel (no alpha)
   :uncompressed-gray-alpha   2 ;; 8*2 bpp (2 channels)
   :uncompressed-r5g6b5       3 ;; 16 bpp
   :uncompressed-r8g8b8       4 ;; 24 bpp
   :uncompressed-r5g5b5a1     5 ;; 16 bpp (1 bit alpha)
   :uncompressed-r4g4b4a4     6 ;; 16 bpp (4 bit alpha)
   :uncompressed-r8g8b8a8     7 ;; 32 bpp
   :uncompressed-r32          8 ;; 32 bpp (1 channel - float)
   :uncompressed-r32g32b32    9 ;; 32*3 bpp (3 channels - float)
   :uncompressed-r32g32b32a32 10 ;; 32*4 bpp (4 channels - float)
   :uncompressed-r16          11 ;; 16 bpp (1 channel - half float)
   :uncompressed-r16g16b16    12 ;; 16*3 bpp (3 channels - half float)
   :uncompressed-r16g16b16a16 13 ;; 16*4 bpp (4 channels - half float)
   :compressed-dxt1-rgb       14 ;; 4 bpp (no alpha)
   :compressed-dxt1-rgba      15 ;; 4 bpp (1 bit alpha)
   :compressed-dxt3-rgba      16 ;; 8 bpp
   :compressed-dxt5-rgba      17 ;; 8 bpp
   :compressed-etc1-rgb       18 ;; 4 bpp
   :compressed-etc2-rgb       19 ;; 4 bpp
   :compressed-etc2-eac-rgba  20 ;; 8 bpp
   :compressed-pvrt-rgb       21 ;; 4 bpp
   :compressed-pvrt-rgba      22 ;; 4 bpp
   :compressed-astc-4x4-rgba  23 ;; 8 bpp
   :compressed-astc-8x8-rgba  24 ;; 2 bpp
  })

(def texture-filter
  "Texture parameters: filter mode"
  {:point           0 ;; No filter, just pixel approximation
   :bilinear        1 ;; Linear filtering
   :trilinear       2 ;; Trilinear filtering (linear with mipmaps)
   :anisotropic-4x  3 ;; Anisotropic filtering 4x
   :anisotropic-8x  4 ;; Anisotropic filtering 8x
   :anisotropic-16x 5 ;; Anisotropic filtering 16x
  })

(def texture-wrap
  "Texture parameters: wrap mode"
  {:repeat        0 ;; Repeats texture in tiled mode
   :clamp         1 ;; Clamps texture to edge pixel in tiled mode
   :mirror-repeat 2 ;; Mirrors and repeats the texture in tiled mode
   :mirror-clamp  3 ;; Mirrors and clamps to border the texture in tiled mode
  })

(def cubemap-layout
  "Cubemap layouts"
  {:auto-detect         0 ;; Automatically detect layout type
   :line-vertical       1 ;; Layout is defined by a vertical line with faces
   :line-horizontal     2 ;; Layout is defined by a horizontal line with faces
   :cross-three-by-four 3 ;; Layout is defined by a 3x4 cross with cubemap faces
   :cross-four-by-three 4 ;; Layout is defined by a 4x3 cross with cubemap faces
   :panorama            5 ;; Layout is defined by a panorama image (equirrectangular map)
  })

(def font-type
  "Font type, defines generation method"
  {:default 0 ;; Default font generation, anti-aliased
   :bitmap  1 ;; Bitmap font generation, no anti-aliasing
   :sdf     2 ;; SDF font generation, requires external shader
  })

(def blend-mode
  "Color blending modes (pre-defined)"
  {:alpha             0 ;; Blend textures considering alpha (default)
   :additive          1 ;; Blend textures adding colors
   :multiplied        2 ;; Blend textures multiplying colors
   :add-colors        3 ;; Blend textures adding colors (alternative)
   :subtract-colors   4 ;; Blend textures subtracting colors (alternative)
   :alpha-premultiply 5 ;; Blend premultiplied textures considering alpha
   :custom            6 ;; Blend textures using custom src/dst factors (use rlSetBlendFactors())
   :custom-separate   7 ;; Blend textures using custom rgb/alpha separate src/dst factors (use rlSetBlendFactorsSeparate())
  })

(def gesture
  "Gesture"
  {:none        0 ;; No gesture
   :tap         1 ;; Tap gesture
   :doubletap   2 ;; Double tap gesture
   :hold        4 ;; Hold gesture
   :drag        8 ;; Drag gesture
   :swipe-right 16 ;; Swipe right gesture
   :swipe-left  32 ;; Swipe left gesture
   :swipe-up    64 ;; Swipe up gesture
   :swipe-down  128 ;; Swipe down gesture
   :pinch-in    256 ;; Pinch in gesture
   :pinch-out   512 ;; Pinch out gesture
  })

(def camera-mode
  "Camera system modes"
  {:custom       0 ;; Custom camera
   :free         1 ;; Free camera
   :orbital      2 ;; Orbital camera
   :first-person 3 ;; First person camera
   :third-person 4 ;; Third person camera
  })

(def camera-projection
  "Camera projection"
  {:perspective  0 ;; Perspective projection
   :orthographic 1 ;; Orthographic projection
  })

(def npatch-layout
  "N-patch layout"
  {:nine-patch 0 ;; Npatch layout: 3x3 tiles
   :three-patch-vertical 1 ;; Npatch layout: 1x3 tiles
   :three-patch-horizontal 2 ;; Npatch layout: 3x1 tiles
  })

