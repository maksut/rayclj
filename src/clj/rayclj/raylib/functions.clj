(ns rayclj.raylib.functions
  (:require [rayclj.raylib.enums :as renums]
            [rayclj.raylib.structs :as rstructs]
            [rayclj.memory :as memory])
  (:import [rayclj.raylib raylib_h]))

(set! *warn-on-reflection* true)

;;
;; Utility Functions
;;

(defmacro with-drawing
  "Evaluates body between begin-drawing and end-drawing calls"
  [& body]
  `(do
     (begin-drawing)
     ~@body
     (end-drawing)))

(defmacro with-mode2d
  "Evaluates body between begin-mode2d and end-mode2d calls."
  [camera & body]
  `(do
     (begin-mode2d ~camera)
     ~@body
     (end-mode2d)))

(defmacro with-mode3d
  "Evaluates body between begin-mode3d and end-mode3d calls."
  [camera & body]
  `(do
     (begin-mode3d ~camera)
     ~@body
     (end-mode3d)))

(defmacro with-texture-mode
  "Evaluates body between begin-texture-mode and end-texture-mode calls."
  [target & body]
  `(do
     (begin-texture-mode ~target)
     ~@body
     (end-texture-mode)))

(defmacro with-shader-mode
  "Evaluates body between begin-shader-mode and end-shader-mode calls."
  [shader & body]
  `(do
     (begin-shader-mode ~shader)
     ~@body
     (end-shader-mode)))

(defmacro with-blend-mode
  "Evaluates body between begin-blend-mode and end-blend-mode calls."
  [mode & body]
  `(do
     (begin-blend-mode ~mode)
     ~@body
     (end-blend-mode)))

(defmacro with-scissor-mode
  "Evaluates body between begin-scissor-mode and end-scissor-mode calls."
  [x y width height & body]
  `(do
     (begin-scissor-mode ~x ~y ~width ~height)
     ~@body
     (end-scissor-mode)))

(defmacro with-vr-stereo-mode
  "Evaluates body between begin-vr-stereo-mode and end-vr-stereo-mode calls."
  [config & body]
  `(do
     (begin-vr-stereo-mode ~config)
     ~@body
     (end-vr-stereo-mode)))

;;
;; Raylib Functions
;;

(defn init-window
  "Initialize window and OpenGL context
  [int width, int height, const char * title] -> void"
  [width height title]
  (raylib_h/InitWindow width height (memory/string title)))

(defn close-window
  "Close window and unload OpenGL context
  [] -> void"
  []
  (raylib_h/CloseWindow))

(defn window-should-close?
  "Check if application should close (KEY_ESCAPE pressed or windows close icon clicked)
  [] -> bool"
  []
  (raylib_h/WindowShouldClose))

(defn window-ready?
  "Check if window has been initialized successfully
  [] -> bool"
  []
  (raylib_h/IsWindowReady))

(defn window-fullscreen?
  "Check if window is currently fullscreen
  [] -> bool"
  []
  (raylib_h/IsWindowFullscreen))

(defn window-hidden?
  "Check if window is currently hidden (only PLATFORM_DESKTOP)
  [] -> bool"
  []
  (raylib_h/IsWindowHidden))

(defn window-minimized?
  "Check if window is currently minimized (only PLATFORM_DESKTOP)
  [] -> bool"
  []
  (raylib_h/IsWindowMinimized))

(defn window-maximized?
  "Check if window is currently maximized (only PLATFORM_DESKTOP)
  [] -> bool"
  []
  (raylib_h/IsWindowMaximized))

(defn window-focused?
  "Check if window is currently focused (only PLATFORM_DESKTOP)
  [] -> bool"
  []
  (raylib_h/IsWindowFocused))

(defn window-resized?
  "Check if window has been resized last frame
  [] -> bool"
  []
  (raylib_h/IsWindowResized))

(defn window-state?
  "Check if one specific window flag is enabled
  [unsigned int flag] -> bool"
  [flag]
  (raylib_h/IsWindowState (if (keyword? flag) (renums/config-flags flag) flag)))

(defn set-window-state
  "Set window configuration state using flags (only PLATFORM_DESKTOP)
  [unsigned int flags] -> void"
  [flags]
  (raylib_h/SetWindowState
    (if (keyword? flags) (renums/config-flags flags) flags)))

(defn clear-window-state
  "Clear window configuration state flags
  [unsigned int flags] -> void"
  [flags]
  (raylib_h/ClearWindowState
    (if (keyword? flags) (renums/config-flags flags) flags)))

(defn toggle-fullscreen
  "Toggle window state: fullscreen/windowed (only PLATFORM_DESKTOP)
  [] -> void"
  []
  (raylib_h/ToggleFullscreen))

(defn toggle-borderless-windowed
  "Toggle window state: borderless windowed (only PLATFORM_DESKTOP)
  [] -> void"
  []
  (raylib_h/ToggleBorderlessWindowed))

(defn maximize-window
  "Set window state: maximized, if resizable (only PLATFORM_DESKTOP)
  [] -> void"
  []
  (raylib_h/MaximizeWindow))

(defn minimize-window
  "Set window state: minimized, if resizable (only PLATFORM_DESKTOP)
  [] -> void"
  []
  (raylib_h/MinimizeWindow))

(defn restore-window
  "Set window state: not minimized/maximized (only PLATFORM_DESKTOP)
  [] -> void"
  []
  (raylib_h/RestoreWindow))

(defn set-window-icon
  "Set icon for window (single image, RGBA 32bit, only PLATFORM_DESKTOP)
  [Image image] -> void"
  [image]
  (raylib_h/SetWindowIcon (rstructs/image image)))

(defn set-window-icons
  "Set icon for window (multiple images, RGBA 32bit, only PLATFORM_DESKTOP)
  [Image * images, int count] -> void"
  [images count]
  (raylib_h/SetWindowIcons (rstructs/image images) count))

(defn set-window-title
  "Set title for window (only PLATFORM_DESKTOP and PLATFORM_WEB)
  [const char * title] -> void"
  [title]
  (raylib_h/SetWindowTitle (memory/string title)))

(defn set-window-position
  "Set window position on screen (only PLATFORM_DESKTOP)
  [int x, int y] -> void"
  [x y]
  (raylib_h/SetWindowPosition x y))

(defn set-window-monitor
  "Set monitor for the current window
  [int monitor] -> void"
  [monitor]
  (raylib_h/SetWindowMonitor monitor))

(defn set-window-min-size
  "Set window minimum dimensions (for FLAG_WINDOW_RESIZABLE)
  [int width, int height] -> void"
  [width height]
  (raylib_h/SetWindowMinSize width height))

(defn set-window-max-size
  "Set window maximum dimensions (for FLAG_WINDOW_RESIZABLE)
  [int width, int height] -> void"
  [width height]
  (raylib_h/SetWindowMaxSize width height))

(defn set-window-size
  "Set window dimensions
  [int width, int height] -> void"
  [width height]
  (raylib_h/SetWindowSize width height))

(defn set-window-opacity
  "Set window opacity [0.0f..1.0f] (only PLATFORM_DESKTOP)
  [float opacity] -> void"
  [opacity]
  (raylib_h/SetWindowOpacity opacity))

(defn set-window-focused
  "Set window focused (only PLATFORM_DESKTOP)
  [] -> void"
  []
  (raylib_h/SetWindowFocused))

(defn get-window-handle
  "Get native window handle
  [] -> void *"
  []
  (raylib_h/GetWindowHandle))

(defn get-screen-width
  "Get current screen width
  [] -> int"
  []
  (raylib_h/GetScreenWidth))

(defn get-screen-height
  "Get current screen height
  [] -> int"
  []
  (raylib_h/GetScreenHeight))

(defn get-render-width
  "Get current render width (it considers HiDPI)
  [] -> int"
  []
  (raylib_h/GetRenderWidth))

(defn get-render-height
  "Get current render height (it considers HiDPI)
  [] -> int"
  []
  (raylib_h/GetRenderHeight))

(defn get-monitor-count
  "Get number of connected monitors
  [] -> int"
  []
  (raylib_h/GetMonitorCount))

(defn get-current-monitor
  "Get current connected monitor
  [] -> int"
  []
  (raylib_h/GetCurrentMonitor))

(defn get-monitor-position
  "Get specified monitor position
  [int monitor] -> Vector2"
  [monitor]
  (rstructs/get-vector2 (raylib_h/GetMonitorPosition memory/*current-arena*
                                                     monitor)))

(defn get-monitor-width
  "Get specified monitor width (current video mode used by monitor)
  [int monitor] -> int"
  [monitor]
  (raylib_h/GetMonitorWidth monitor))

(defn get-monitor-height
  "Get specified monitor height (current video mode used by monitor)
  [int monitor] -> int"
  [monitor]
  (raylib_h/GetMonitorHeight monitor))

(defn get-monitor-physical-width
  "Get specified monitor physical width in millimetres
  [int monitor] -> int"
  [monitor]
  (raylib_h/GetMonitorPhysicalWidth monitor))

(defn get-monitor-physical-height
  "Get specified monitor physical height in millimetres
  [int monitor] -> int"
  [monitor]
  (raylib_h/GetMonitorPhysicalHeight monitor))

(defn get-monitor-refresh-rate
  "Get specified monitor refresh rate
  [int monitor] -> int"
  [monitor]
  (raylib_h/GetMonitorRefreshRate monitor))

(defn get-window-position
  "Get window position XY on monitor
  [] -> Vector2"
  []
  (rstructs/get-vector2 (raylib_h/GetWindowPosition memory/*current-arena*)))

(defn get-window-scale-dpi
  "Get window scale DPI factor
  [] -> Vector2"
  []
  (rstructs/get-vector2 (raylib_h/GetWindowScaleDPI memory/*current-arena*)))

(defn get-monitor-name
  "Get the human-readable, UTF-8 encoded name of the specified monitor
  [int monitor] -> const char *"
  [monitor]
  (raylib_h/GetMonitorName monitor))

(defn set-clipboard-text
  "Set clipboard text content
  [const char * text] -> void"
  [text]
  (raylib_h/SetClipboardText (memory/string text)))

(defn get-clipboard-text
  "Get clipboard text content
  [] -> const char *"
  []
  (raylib_h/GetClipboardText))

(defn enable-event-waiting
  "Enable waiting for events on EndDrawing(), no automatic event polling
  [] -> void"
  []
  (raylib_h/EnableEventWaiting))

(defn disable-event-waiting
  "Disable waiting for events on EndDrawing(), automatic events polling
  [] -> void"
  []
  (raylib_h/DisableEventWaiting))

(defn show-cursor "Shows cursor
  [] -> void" [] (raylib_h/ShowCursor))

(defn hide-cursor "Hides cursor
  [] -> void" [] (raylib_h/HideCursor))

(defn cursor-hidden?
  "Check if cursor is not visible
  [] -> bool"
  []
  (raylib_h/IsCursorHidden))

(defn enable-cursor
  "Enables cursor (unlock cursor)
  [] -> void"
  []
  (raylib_h/EnableCursor))

(defn disable-cursor
  "Disables cursor (lock cursor)
  [] -> void"
  []
  (raylib_h/DisableCursor))

(defn cursor-on-screen?
  "Check if cursor is on the screen
  [] -> bool"
  []
  (raylib_h/IsCursorOnScreen))

(defn clear-background
  "Set background color (framebuffer clear color)
  [Color color] -> void"
  [color]
  (raylib_h/ClearBackground (rstructs/color color)))

(defn begin-drawing
  "Setup canvas (framebuffer) to start drawing
  [] -> void"
  []
  (raylib_h/BeginDrawing))

(defn end-drawing
  "End canvas drawing and swap buffers (double buffering)
  [] -> void"
  []
  (raylib_h/EndDrawing))

(defn begin-mode2d
  "Begin 2D mode with custom camera (2D)
  [Camera2D camera] -> void"
  [camera]
  (raylib_h/BeginMode2D (rstructs/camera2d camera)))

(defn end-mode2d
  "Ends 2D mode with custom camera
  [] -> void"
  []
  (raylib_h/EndMode2D))

(defn begin-mode3d
  "Begin 3D mode with custom camera (3D)
  [Camera3D camera] -> void"
  [camera]
  (raylib_h/BeginMode3D (rstructs/camera3d camera)))

(defn end-mode3d
  "Ends 3D mode and returns to default 2D orthographic mode
  [] -> void"
  []
  (raylib_h/EndMode3D))

(defn begin-texture-mode
  "Begin drawing to render texture
  [RenderTexture2D target] -> void"
  [target]
  (raylib_h/BeginTextureMode (rstructs/render-texture target)))

(defn end-texture-mode
  "Ends drawing to render texture
  [] -> void"
  []
  (raylib_h/EndTextureMode))

(defn begin-shader-mode
  "Begin custom shader drawing
  [Shader shader] -> void"
  [shader]
  (raylib_h/BeginShaderMode (rstructs/shader shader)))

(defn end-shader-mode
  "End custom shader drawing (use default shader)
  [] -> void"
  []
  (raylib_h/EndShaderMode))

(defn begin-blend-mode
  "Begin blending mode (alpha, additive, multiplied, subtract, custom)
  [int mode] -> void"
  [mode]
  (raylib_h/BeginBlendMode (if (keyword? mode) (renums/blend-mode mode) mode)))

(defn end-blend-mode
  "End blending mode (reset to default: alpha blending)
  [] -> void"
  []
  (raylib_h/EndBlendMode))

(defn begin-scissor-mode
  "Begin scissor mode (define screen area for following drawing)
  [int x, int y, int width, int height] -> void"
  [x y width height]
  (raylib_h/BeginScissorMode x y width height))

(defn end-scissor-mode
  "End scissor mode
  [] -> void"
  []
  (raylib_h/EndScissorMode))

(defn begin-vr-stereo-mode
  "Begin stereo rendering (requires VR simulator)
  [VrStereoConfig config] -> void"
  [config]
  (raylib_h/BeginVrStereoMode (rstructs/vr-stereo-config config)))

(defn end-vr-stereo-mode
  "End stereo rendering (requires VR simulator)
  [] -> void"
  []
  (raylib_h/EndVrStereoMode))

(defn load-vr-stereo-config
  "Load VR stereo config for VR simulator device parameters
  [VrDeviceInfo device] -> VrStereoConfig"
  [device]
  (rstructs/get-vr-stereo-config (raylib_h/LoadVrStereoConfig
                                   memory/*current-arena*
                                   (rstructs/vr-device-info device))))

(defn unload-vr-stereo-config
  "Unload VR stereo config
  [VrStereoConfig config] -> void"
  [config]
  (raylib_h/UnloadVrStereoConfig (rstructs/vr-stereo-config config)))

(defn load-shader
  "Load shader from files and bind default locations
  [const char * vsFileName, const char * fsFileName] -> Shader"
  [vs-file-name fs-file-name]
  (rstructs/get-shader (raylib_h/LoadShader memory/*current-arena*
                                            (memory/string vs-file-name)
                                            (memory/string fs-file-name))))

(defn load-shader-from-memory
  "Load shader from code strings and bind default locations
  [const char * vsCode, const char * fsCode] -> Shader"
  [vs-code fs-code]
  (rstructs/get-shader (raylib_h/LoadShaderFromMemory memory/*current-arena*
                                                      (memory/string vs-code)
                                                      (memory/string fs-code))))

(defn shader-ready?
  "Check if a shader is ready
  [Shader shader] -> bool"
  [shader]
  (raylib_h/IsShaderReady (rstructs/shader shader)))

(defn get-shader-location
  "Get shader uniform location
  [Shader shader, const char * uniformName] -> int"
  [shader uniform-name]
  (raylib_h/GetShaderLocation (rstructs/shader shader)
                              (memory/string uniform-name)))

(defn get-shader-location-attrib
  "Get shader attribute location
  [Shader shader, const char * attribName] -> int"
  [shader attrib-name]
  (raylib_h/GetShaderLocationAttrib (rstructs/shader shader)
                                    (memory/string attrib-name)))

(defn set-shader-value
  "Set shader uniform value
  [Shader shader, int locIndex, const void * value, int uniformType] -> void"
  [shader loc-index value uniform-type]
  (raylib_h/SetShaderValue (rstructs/shader shader)
                           loc-index
                           value
                           (if (keyword? uniform-type)
                             (renums/shader-uniform-data-type uniform-type)
                             uniform-type)))

(defn set-shader-value-v
  "Set shader uniform value vector
  [Shader shader, int locIndex, const void * value, int uniformType, int count] -> void"
  [shader loc-index value uniform-type count]
  (raylib_h/SetShaderValueV (rstructs/shader shader)
                            loc-index
                            value
                            (if (keyword? uniform-type)
                              (renums/shader-uniform-data-type uniform-type)
                              uniform-type)
                            count))

(defn set-shader-value-matrix
  "Set shader uniform value (matrix 4x4)
  [Shader shader, int locIndex, Matrix mat] -> void"
  [shader loc-index mat]
  (raylib_h/SetShaderValueMatrix (rstructs/shader shader)
                                 loc-index
                                 (rstructs/matrix mat)))

(defn set-shader-value-texture
  "Set shader uniform value for texture (sampler2d)
  [Shader shader, int locIndex, Texture2D texture] -> void"
  [shader loc-index texture]
  (raylib_h/SetShaderValueTexture (rstructs/shader shader)
                                  loc-index
                                  (rstructs/texture texture)))

(defn unload-shader
  "Unload shader from GPU memory (VRAM)
  [Shader shader] -> void"
  [shader]
  (raylib_h/UnloadShader (rstructs/shader shader)))

(defn get-mouse-ray
  "Get a ray trace from mouse position
  [Vector2 mousePosition, Camera camera] -> Ray"
  [mouse-position camera]
  (rstructs/get-ray (raylib_h/GetMouseRay memory/*current-arena*
                                          (rstructs/vector2 mouse-position)
                                          (rstructs/camera3d camera))))

(defn get-camera-matrix
  "Get camera transform matrix (view matrix)
  [Camera camera] -> Matrix"
  [camera]
  (rstructs/get-matrix (raylib_h/GetCameraMatrix memory/*current-arena*
                                                 (rstructs/camera3d camera))))

(defn get-camera-matrix2d
  "Get camera 2d transform matrix
  [Camera2D camera] -> Matrix"
  [camera]
  (rstructs/get-matrix (raylib_h/GetCameraMatrix2D memory/*current-arena*
                                                   (rstructs/camera2d camera))))

(defn get-world-to-screen
  "Get the screen space position for a 3d world space position
  [Vector3 position, Camera camera] -> Vector2"
  [position camera]
  (rstructs/get-vector2 (raylib_h/GetWorldToScreen memory/*current-arena*
                                                   (rstructs/vector3 position)
                                                   (rstructs/camera3d camera))))

(defn get-screen-to-world2d
  "Get the world space position for a 2d camera screen space position
  [Vector2 position, Camera2D camera] -> Vector2"
  [position camera]
  (rstructs/get-vector2 (raylib_h/GetScreenToWorld2D memory/*current-arena*
                                                     (rstructs/vector2 position)
                                                     (rstructs/camera2d
                                                       camera))))

(defn get-world-to-screen-ex
  "Get size position for a 3d world space position
  [Vector3 position, Camera camera, int width, int height] -> Vector2"
  [position camera width height]
  (rstructs/get-vector2 (raylib_h/GetWorldToScreenEx memory/*current-arena*
                                                     (rstructs/vector3 position)
                                                     (rstructs/camera3d camera)
                                                     width
                                                     height)))

(defn get-world-to-screen2d
  "Get the screen space position for a 2d camera world space position
  [Vector2 position, Camera2D camera] -> Vector2"
  [position camera]
  (rstructs/get-vector2 (raylib_h/GetWorldToScreen2D memory/*current-arena*
                                                     (rstructs/vector2 position)
                                                     (rstructs/camera2d
                                                       camera))))

(defn set-target-fps
  "Set target FPS (maximum)
  [int fps] -> void"
  [fps]
  (raylib_h/SetTargetFPS fps))

(defn get-frame-time
  "Get time in seconds for last frame drawn (delta time)
  [] -> float"
  []
  (raylib_h/GetFrameTime))

(defn get-time
  "Get elapsed time in seconds since InitWindow()
  [] -> double"
  []
  (raylib_h/GetTime))

(defn get-fps "Get current FPS
  [] -> int" [] (raylib_h/GetFPS))

(defn swap-screen-buffer
  "Swap back buffer with front buffer (screen drawing)
  [] -> void"
  []
  (raylib_h/SwapScreenBuffer))

(defn poll-input-events
  "Register all input events
  [] -> void"
  []
  (raylib_h/PollInputEvents))

(defn wait-time
  "Wait for some time (halt program execution)
  [double seconds] -> void"
  [seconds]
  (raylib_h/WaitTime seconds))

(defn set-random-seed
  "Set the seed for the random number generator
  [unsigned int seed] -> void"
  [seed]
  (raylib_h/SetRandomSeed seed))

(defn get-random-value
  "Get a random value between min and max (both included)
  [int min, int max] -> int"
  [min max]
  (raylib_h/GetRandomValue min max))

(defn load-random-sequence
  "Load random values sequence, no values repeated
  [unsigned int count, int min, int max] -> int *"
  [count min max]
  (raylib_h/LoadRandomSequence count min max))

(defn unload-random-sequence
  "Unload random values sequence
  [int * sequence] -> void"
  [sequence]
  (raylib_h/UnloadRandomSequence sequence))

(defn take-screenshot
  "Takes a screenshot of current screen (filename extension defines format)
  [const char * fileName] -> void"
  [file-name]
  (raylib_h/TakeScreenshot (memory/string file-name)))

(defn set-config-flags
  "Setup init configuration flags (view FLAGS)
  [unsigned int flags] -> void"
  [flags]
  (raylib_h/SetConfigFlags
    (if (keyword? flags) (renums/config-flags flags) flags)))

(defn open-url
  "Open URL with default system browser (if available)
  [const char * url] -> void"
  [url]
  (raylib_h/OpenURL (memory/string url)))

(defn trace-log
  "Show trace log messages (LOG_DEBUG, LOG_INFO, LOG_WARNING, LOG_ERROR...)
  [int logLevel, const char * text, ... args] -> void"
  [log-level text args]
  (raylib_h/TraceLog
    (if (keyword? log-level) (renums/trace-log-level log-level) log-level)
    (memory/string text)
    args))

(defn set-trace-log-level
  "Set the current threshold (minimum) log level
  [int logLevel] -> void"
  [log-level]
  (raylib_h/SetTraceLogLevel
    (if (keyword? log-level) (renums/trace-log-level log-level) log-level)))

(defn mem-alloc
  "Internal memory allocator
  [unsigned int size] -> void *"
  [size]
  (raylib_h/MemAlloc size))

(defn mem-realloc
  "Internal memory reallocator
  [void * ptr, unsigned int size] -> void *"
  [ptr size]
  (raylib_h/MemRealloc ptr size))

(defn mem-free
  "Internal memory free
  [void * ptr] -> void"
  [ptr]
  (raylib_h/MemFree ptr))

(defn set-trace-log-callback
  "Set custom trace log
  [TraceLogCallback callback] -> void"
  [callback]
  (raylib_h/SetTraceLogCallback callback))

(defn set-load-file-data-callback
  "Set custom file binary data loader
  [LoadFileDataCallback callback] -> void"
  [callback]
  (raylib_h/SetLoadFileDataCallback callback))

(defn set-save-file-data-callback
  "Set custom file binary data saver
  [SaveFileDataCallback callback] -> void"
  [callback]
  (raylib_h/SetSaveFileDataCallback callback))

(defn set-load-file-text-callback
  "Set custom file text data loader
  [LoadFileTextCallback callback] -> void"
  [callback]
  (raylib_h/SetLoadFileTextCallback callback))

(defn set-save-file-text-callback
  "Set custom file text data saver
  [SaveFileTextCallback callback] -> void"
  [callback]
  (raylib_h/SetSaveFileTextCallback callback))

(defn load-file-data
  "Load file data as byte array (read)
  [const char * fileName, int * dataSize] -> unsigned char *"
  [file-name data-size]
  (raylib_h/LoadFileData (memory/string file-name) data-size))

(defn unload-file-data
  "Unload file data allocated by LoadFileData()
  [unsigned char * data] -> void"
  [data]
  (raylib_h/UnloadFileData data))

(defn save-file-data?
  "Save data to file from byte array (write), returns true on success
  [const char * fileName, void * data, int dataSize] -> bool"
  [file-name data data-size]
  (raylib_h/SaveFileData (memory/string file-name) data data-size))

(defn export-data-as-code?
  "Export data to code (.h), returns true on success
  [const unsigned char * data, int dataSize, const char * fileName] -> bool"
  [data data-size file-name]
  (raylib_h/ExportDataAsCode data data-size (memory/string file-name)))

(defn load-file-text
  "Load text data from file (read), returns a '\\0' terminated string
  [const char * fileName] -> char *"
  [file-name]
  (raylib_h/LoadFileText (memory/string file-name)))

(defn unload-file-text
  "Unload file text data allocated by LoadFileText()
  [char * text] -> void"
  [text]
  (raylib_h/UnloadFileText text))

(defn save-file-text?
  "Save text data to file (write), string must be '\\0' terminated, returns true on success
  [const char * fileName, char * text] -> bool"
  [file-name text]
  (raylib_h/SaveFileText (memory/string file-name) text))

(defn file-exists?
  "Check if file exists
  [const char * fileName] -> bool"
  [file-name]
  (raylib_h/FileExists (memory/string file-name)))

(defn directory-exists?
  "Check if a directory path exists
  [const char * dirPath] -> bool"
  [dir-path]
  (raylib_h/DirectoryExists (memory/string dir-path)))

(defn file-extension?
  "Check file extension (including point: .png, .wav)
  [const char * fileName, const char * ext] -> bool"
  [file-name ext]
  (raylib_h/IsFileExtension (memory/string file-name) (memory/string ext)))

(defn get-file-length
  "Get file length in bytes (NOTE: GetFileSize() conflicts with windows.h)
  [const char * fileName] -> int"
  [file-name]
  (raylib_h/GetFileLength (memory/string file-name)))

(defn get-file-extension
  "Get pointer to extension for a filename string (includes dot: '.png')
  [const char * fileName] -> const char *"
  [file-name]
  (raylib_h/GetFileExtension (memory/string file-name)))

(defn get-file-name
  "Get pointer to filename for a path string
  [const char * filePath] -> const char *"
  [file-path]
  (raylib_h/GetFileName (memory/string file-path)))

(defn get-file-name-without-ext
  "Get filename string without extension (uses static string)
  [const char * filePath] -> const char *"
  [file-path]
  (raylib_h/GetFileNameWithoutExt (memory/string file-path)))

(defn get-directory-path
  "Get full path for a given fileName with path (uses static string)
  [const char * filePath] -> const char *"
  [file-path]
  (raylib_h/GetDirectoryPath (memory/string file-path)))

(defn get-prev-directory-path
  "Get previous directory path for a given path (uses static string)
  [const char * dirPath] -> const char *"
  [dir-path]
  (raylib_h/GetPrevDirectoryPath (memory/string dir-path)))

(defn get-working-directory
  "Get current working directory (uses static string)
  [] -> const char *"
  []
  (raylib_h/GetWorkingDirectory))

(defn get-application-directory
  "Get the directory of the running application (uses static string)
  [] -> const char *"
  []
  (raylib_h/GetApplicationDirectory))

(defn change-directory?
  "Change working directory, return true on success
  [const char * dir] -> bool"
  [dir]
  (raylib_h/ChangeDirectory (memory/string dir)))

(defn path-file?
  "Check if a given path is a file or a directory
  [const char * path] -> bool"
  [path]
  (raylib_h/IsPathFile (memory/string path)))

(defn load-directory-files
  "Load directory filepaths
  [const char * dirPath] -> FilePathList"
  [dir-path]
  (rstructs/get-file-path-list (raylib_h/LoadDirectoryFiles
                                 memory/*current-arena*
                                 (memory/string dir-path))))

(defn load-directory-files-ex
  "Load directory filepaths with extension filtering and recursive directory scan
  [const char * basePath, const char * filter, bool scanSubdirs] -> FilePathList"
  [base-path filter scan-subdirs]
  (rstructs/get-file-path-list (raylib_h/LoadDirectoryFilesEx
                                 memory/*current-arena*
                                 (memory/string base-path)
                                 (memory/string filter)
                                 scan-subdirs)))

(defn unload-directory-files
  "Unload filepaths
  [FilePathList files] -> void"
  [files]
  (raylib_h/UnloadDirectoryFiles (rstructs/file-path-list files)))

(defn file-dropped?
  "Check if a file has been dropped into window
  [] -> bool"
  []
  (raylib_h/IsFileDropped))

(defn load-dropped-files
  "Load dropped filepaths
  [] -> FilePathList"
  []
  (rstructs/get-file-path-list (raylib_h/LoadDroppedFiles
                                 memory/*current-arena*)))

(defn unload-dropped-files
  "Unload dropped filepaths
  [FilePathList files] -> void"
  [files]
  (raylib_h/UnloadDroppedFiles (rstructs/file-path-list files)))

(defn get-file-mod-time
  "Get file modification time (last write time)
  [const char * fileName] -> long"
  [file-name]
  (raylib_h/GetFileModTime (memory/string file-name)))

(defn compress-data
  "Compress data (DEFLATE algorithm), memory must be MemFree()
  [const unsigned char * data, int dataSize, int * compDataSize] -> unsigned char *"
  [data data-size comp-data-size]
  (raylib_h/CompressData data data-size comp-data-size))

(defn decompress-data
  "Decompress data (DEFLATE algorithm), memory must be MemFree()
  [const unsigned char * compData, int compDataSize, int * dataSize] -> unsigned char *"
  [comp-data comp-data-size data-size]
  (raylib_h/DecompressData comp-data comp-data-size data-size))

(defn encode-data-base64
  "Encode data to Base64 string, memory must be MemFree()
  [const unsigned char * data, int dataSize, int * outputSize] -> char *"
  [data data-size output-size]
  (raylib_h/EncodeDataBase64 data data-size output-size))

(defn decode-data-base64
  "Decode Base64 string data, memory must be MemFree()
  [const unsigned char * data, int * outputSize] -> unsigned char *"
  [data output-size]
  (raylib_h/DecodeDataBase64 data output-size))

(defn load-automation-event-list
  "Load automation events list from file, NULL for empty list, capacity = MAX_AUTOMATION_EVENTS
  [const char * fileName] -> AutomationEventList"
  [file-name]
  (rstructs/get-automation-event-list (raylib_h/LoadAutomationEventList
                                        memory/*current-arena*
                                        (memory/string file-name))))

(defn unload-automation-event-list
  "Unload automation events list from file
  [AutomationEventList * list] -> void"
  [list]
  (raylib_h/UnloadAutomationEventList (rstructs/automation-event-list list)))

(defn export-automation-event-list?
  "Export automation events list as text file
  [AutomationEventList list, const char * fileName] -> bool"
  [list file-name]
  (raylib_h/ExportAutomationEventList (rstructs/automation-event-list list)
                                      (memory/string file-name)))

(defn set-automation-event-list
  "Set automation event list to record to
  [AutomationEventList * list] -> void"
  [list]
  (raylib_h/SetAutomationEventList (rstructs/automation-event-list list)))

(defn set-automation-event-base-frame
  "Set automation event internal base frame to start recording
  [int frame] -> void"
  [frame]
  (raylib_h/SetAutomationEventBaseFrame frame))

(defn start-automation-event-recording
  "Start recording automation events (AutomationEventList must be set)
  [] -> void"
  []
  (raylib_h/StartAutomationEventRecording))

(defn stop-automation-event-recording
  "Stop recording automation events
  [] -> void"
  []
  (raylib_h/StopAutomationEventRecording))

(defn play-automation-event
  "Play a recorded automation event
  [AutomationEvent event] -> void"
  [event]
  (raylib_h/PlayAutomationEvent (rstructs/automation-event event)))

(defn key-pressed?
  "Check if a key has been pressed once
  [int key] -> bool"
  [key]
  (raylib_h/IsKeyPressed (if (keyword? key) (renums/keyboard-key key) key)))

(defn key-pressed-repeat?
  "Check if a key has been pressed again (Only PLATFORM_DESKTOP)
  [int key] -> bool"
  [key]
  (raylib_h/IsKeyPressedRepeat
    (if (keyword? key) (renums/keyboard-key key) key)))

(defn key-down?
  "Check if a key is being pressed
  [int key] -> bool"
  [key]
  (raylib_h/IsKeyDown (if (keyword? key) (renums/keyboard-key key) key)))

(defn key-released?
  "Check if a key has been released once
  [int key] -> bool"
  [key]
  (raylib_h/IsKeyReleased (if (keyword? key) (renums/keyboard-key key) key)))

(defn key-up?
  "Check if a key is NOT being pressed
  [int key] -> bool"
  [key]
  (raylib_h/IsKeyUp (if (keyword? key) (renums/keyboard-key key) key)))

(defn get-key-pressed
  "Get key pressed (keycode), call it multiple times for keys queued, returns 0 when the queue is empty
  [] -> int"
  []
  (raylib_h/GetKeyPressed))

(defn get-char-pressed
  "Get char pressed (unicode), call it multiple times for chars queued, returns 0 when the queue is empty
  [] -> int"
  []
  (raylib_h/GetCharPressed))

(defn set-exit-key
  "Set a custom key to exit program (default is ESC)
  [int key] -> void"
  [key]
  (raylib_h/SetExitKey (if (keyword? key) (renums/keyboard-key key) key)))

(defn gamepad-available?
  "Check if a gamepad is available
  [int gamepad] -> bool"
  [gamepad]
  (raylib_h/IsGamepadAvailable gamepad))

(defn get-gamepad-name
  "Get gamepad internal name id
  [int gamepad] -> const char *"
  [gamepad]
  (raylib_h/GetGamepadName gamepad))

(defn gamepad-button-pressed?
  "Check if a gamepad button has been pressed once
  [int gamepad, int button] -> bool"
  [gamepad button]
  (raylib_h/IsGamepadButtonPressed
    gamepad
    (if (keyword? button) (renums/gamepad-button button) button)))

(defn gamepad-button-down?
  "Check if a gamepad button is being pressed
  [int gamepad, int button] -> bool"
  [gamepad button]
  (raylib_h/IsGamepadButtonDown
    gamepad
    (if (keyword? button) (renums/gamepad-button button) button)))

(defn gamepad-button-released?
  "Check if a gamepad button has been released once
  [int gamepad, int button] -> bool"
  [gamepad button]
  (raylib_h/IsGamepadButtonReleased
    gamepad
    (if (keyword? button) (renums/gamepad-button button) button)))

(defn gamepad-button-up?
  "Check if a gamepad button is NOT being pressed
  [int gamepad, int button] -> bool"
  [gamepad button]
  (raylib_h/IsGamepadButtonUp
    gamepad
    (if (keyword? button) (renums/gamepad-button button) button)))

(defn get-gamepad-button-pressed
  "Get the last gamepad button pressed
  [] -> int"
  []
  (raylib_h/GetGamepadButtonPressed))

(defn get-gamepad-axis-count
  "Get gamepad axis count for a gamepad
  [int gamepad] -> int"
  [gamepad]
  (raylib_h/GetGamepadAxisCount gamepad))

(defn get-gamepad-axis-movement
  "Get axis movement value for a gamepad axis
  [int gamepad, int axis] -> float"
  [gamepad axis]
  (raylib_h/GetGamepadAxisMovement
    gamepad
    (if (keyword? axis) (renums/gamepad-axis axis) axis)))

(defn set-gamepad-mappings
  "Set internal gamepad mappings (SDL_GameControllerDB)
  [const char * mappings] -> int"
  [mappings]
  (raylib_h/SetGamepadMappings (memory/string mappings)))

(defn mouse-button-pressed?
  "Check if a mouse button has been pressed once
  [int button] -> bool"
  [button]
  (raylib_h/IsMouseButtonPressed
    (if (keyword? button) (renums/mouse-button button) button)))

(defn mouse-button-down?
  "Check if a mouse button is being pressed
  [int button] -> bool"
  [button]
  (raylib_h/IsMouseButtonDown
    (if (keyword? button) (renums/mouse-button button) button)))

(defn mouse-button-released?
  "Check if a mouse button has been released once
  [int button] -> bool"
  [button]
  (raylib_h/IsMouseButtonReleased
    (if (keyword? button) (renums/mouse-button button) button)))

(defn mouse-button-up?
  "Check if a mouse button is NOT being pressed
  [int button] -> bool"
  [button]
  (raylib_h/IsMouseButtonUp
    (if (keyword? button) (renums/mouse-button button) button)))

(defn get-mouse-x "Get mouse position X
  [] -> int" [] (raylib_h/GetMouseX))

(defn get-mouse-y "Get mouse position Y
  [] -> int" [] (raylib_h/GetMouseY))

(defn get-mouse-position
  "Get mouse position XY
  [] -> Vector2"
  []
  (rstructs/get-vector2 (raylib_h/GetMousePosition memory/*current-arena*)))

(defn get-mouse-delta
  "Get mouse delta between frames
  [] -> Vector2"
  []
  (rstructs/get-vector2 (raylib_h/GetMouseDelta memory/*current-arena*)))

(defn set-mouse-position
  "Set mouse position XY
  [int x, int y] -> void"
  [x y]
  (raylib_h/SetMousePosition x y))

(defn set-mouse-offset
  "Set mouse offset
  [int offsetX, int offsetY] -> void"
  [offset-x offset-y]
  (raylib_h/SetMouseOffset offset-x offset-y))

(defn set-mouse-scale
  "Set mouse scaling
  [float scaleX, float scaleY] -> void"
  [scale-x scale-y]
  (raylib_h/SetMouseScale scale-x scale-y))

(defn get-mouse-wheel-move
  "Get mouse wheel movement for X or Y, whichever is larger
  [] -> float"
  []
  (raylib_h/GetMouseWheelMove))

(defn get-mouse-wheel-move-v
  "Get mouse wheel movement for both X and Y
  [] -> Vector2"
  []
  (rstructs/get-vector2 (raylib_h/GetMouseWheelMoveV memory/*current-arena*)))

(defn set-mouse-cursor
  "Set mouse cursor
  [int cursor] -> void"
  [cursor]
  (raylib_h/SetMouseCursor
    (if (keyword? cursor) (renums/mouse-cursor cursor) cursor)))

(defn get-touch-x
  "Get touch position X for touch point 0 (relative to screen size)
  [] -> int"
  []
  (raylib_h/GetTouchX))

(defn get-touch-y
  "Get touch position Y for touch point 0 (relative to screen size)
  [] -> int"
  []
  (raylib_h/GetTouchY))

(defn get-touch-position
  "Get touch position XY for a touch point index (relative to screen size)
  [int index] -> Vector2"
  [index]
  (rstructs/get-vector2 (raylib_h/GetTouchPosition memory/*current-arena*
                                                   index)))

(defn get-touch-point-id
  "Get touch point identifier for given index
  [int index] -> int"
  [index]
  (raylib_h/GetTouchPointId index))

(defn get-touch-point-count
  "Get number of touch points
  [] -> int"
  []
  (raylib_h/GetTouchPointCount))

(defn set-gestures-enabled
  "Enable a set of gestures using flags
  [unsigned int flags] -> void"
  [flags]
  (raylib_h/SetGesturesEnabled
    (if (keyword? flags) (renums/gesture flags) flags)))

(defn gesture-detected?
  "Check if a gesture have been detected
  [unsigned int gesture] -> bool"
  [gesture]
  (raylib_h/IsGestureDetected
    (if (keyword? gesture) (renums/gesture gesture) gesture)))

(defn get-gesture-detected
  "Get latest detected gesture
  [] -> int"
  []
  (raylib_h/GetGestureDetected))

(defn get-gesture-hold-duration
  "Get gesture hold time in milliseconds
  [] -> float"
  []
  (raylib_h/GetGestureHoldDuration))

(defn get-gesture-drag-vector
  "Get gesture drag vector
  [] -> Vector2"
  []
  (rstructs/get-vector2 (raylib_h/GetGestureDragVector memory/*current-arena*)))

(defn get-gesture-drag-angle
  "Get gesture drag angle
  [] -> float"
  []
  (raylib_h/GetGestureDragAngle))

(defn get-gesture-pinch-vector
  "Get gesture pinch delta
  [] -> Vector2"
  []
  (rstructs/get-vector2 (raylib_h/GetGesturePinchVector
                          memory/*current-arena*)))

(defn get-gesture-pinch-angle
  "Get gesture pinch angle
  [] -> float"
  []
  (raylib_h/GetGesturePinchAngle))

(defn update-camera
  "Update camera position for selected mode
  [Camera * camera, int mode] -> void"
  [camera mode]
  (raylib_h/UpdateCamera (rstructs/camera3d camera) mode))

(defn update-camera-pro
  "Update camera movement/rotation
  [Camera * camera, Vector3 movement, Vector3 rotation, float zoom] -> void"
  [camera movement rotation zoom]
  (raylib_h/UpdateCameraPro (rstructs/camera3d camera)
                            (rstructs/vector3 movement)
                            (rstructs/vector3 rotation)
                            zoom))

(defn set-shapes-texture
  "Set texture and rectangle to be used on shapes drawing
  [Texture2D texture, Rectangle source] -> void"
  [texture source]
  (raylib_h/SetShapesTexture (rstructs/texture texture)
                             (rstructs/rectangle source)))

(defn draw-pixel
  "Draw a pixel
  [int posX, int posY, Color color] -> void"
  [pos-x pos-y color]
  (raylib_h/DrawPixel pos-x pos-y (rstructs/color color)))

(defn draw-pixel-v
  "Draw a pixel (Vector version)
  [Vector2 position, Color color] -> void"
  [position color]
  (raylib_h/DrawPixelV (rstructs/vector2 position) (rstructs/color color)))

(defn draw-line
  "Draw a line
  [int startPosX, int startPosY, int endPosX, int endPosY, Color color] -> void"
  [start-pos-x start-pos-y end-pos-x end-pos-y color]
  (raylib_h/DrawLine start-pos-x
                     start-pos-y
                     end-pos-x
                     end-pos-y
                     (rstructs/color color)))

(defn draw-line-v
  "Draw a line (using gl lines)
  [Vector2 startPos, Vector2 endPos, Color color] -> void"
  [start-pos end-pos color]
  (raylib_h/DrawLineV (rstructs/vector2 start-pos)
                      (rstructs/vector2 end-pos)
                      (rstructs/color color)))

(defn draw-line-ex
  "Draw a line (using triangles/quads)
  [Vector2 startPos, Vector2 endPos, float thick, Color color] -> void"
  [start-pos end-pos thick color]
  (raylib_h/DrawLineEx (rstructs/vector2 start-pos)
                       (rstructs/vector2 end-pos)
                       thick
                       (rstructs/color color)))

(defn draw-line-strip
  "Draw lines sequence (using gl lines)
  [Vector2 * points, int pointCount, Color color] -> void"
  ([points point-count color]
   (raylib_h/DrawLineStrip (rstructs/vector2-array points point-count)
                           point-count
                           (rstructs/color color)))
  ([points color]
   (raylib_h/DrawLineStrip (rstructs/vector2-array points)
                           (count points)
                           (rstructs/color color))))

(defn draw-line-bezier
  "Draw line segment cubic-bezier in-out interpolation
  [Vector2 startPos, Vector2 endPos, float thick, Color color] -> void"
  [start-pos end-pos thick color]
  (raylib_h/DrawLineBezier (rstructs/vector2 start-pos)
                           (rstructs/vector2 end-pos)
                           thick
                           (rstructs/color color)))

(defn draw-circle
  "Draw a color-filled circle
  [int centerX, int centerY, float radius, Color color] -> void"
  [center-x center-y radius color]
  (raylib_h/DrawCircle center-x center-y radius (rstructs/color color)))

(defn draw-circle-sector
  "Draw a piece of a circle
  [Vector2 center, float radius, float startAngle, float endAngle, int segments, Color color] -> void"
  [center radius start-angle end-angle segments color]
  (raylib_h/DrawCircleSector (rstructs/vector2 center)
                             radius
                             start-angle
                             end-angle
                             segments
                             (rstructs/color color)))

(defn draw-circle-sector-lines
  "Draw circle sector outline
  [Vector2 center, float radius, float startAngle, float endAngle, int segments, Color color] -> void"
  [center radius start-angle end-angle segments color]
  (raylib_h/DrawCircleSectorLines (rstructs/vector2 center)
                                  radius
                                  start-angle
                                  end-angle
                                  segments
                                  (rstructs/color color)))

(defn draw-circle-gradient
  "Draw a gradient-filled circle
  [int centerX, int centerY, float radius, Color color1, Color color2] -> void"
  [center-x center-y radius color1 color2]
  (raylib_h/DrawCircleGradient center-x
                               center-y
                               radius
                               (rstructs/color color1)
                               (rstructs/color color2)))

(defn draw-circle-v
  "Draw a color-filled circle (Vector version)
  [Vector2 center, float radius, Color color] -> void"
  [center radius color]
  (raylib_h/DrawCircleV (rstructs/vector2 center)
                        radius
                        (rstructs/color color)))

(defn draw-circle-lines
  "Draw circle outline
  [int centerX, int centerY, float radius, Color color] -> void"
  [center-x center-y radius color]
  (raylib_h/DrawCircleLines center-x center-y radius (rstructs/color color)))

(defn draw-circle-lines-v
  "Draw circle outline (Vector version)
  [Vector2 center, float radius, Color color] -> void"
  [center radius color]
  (raylib_h/DrawCircleLinesV (rstructs/vector2 center)
                             radius
                             (rstructs/color color)))

(defn draw-ellipse
  "Draw ellipse
  [int centerX, int centerY, float radiusH, float radiusV, Color color] -> void"
  [center-x center-y radius-h radius-v color]
  (raylib_h/DrawEllipse center-x
                        center-y
                        radius-h
                        radius-v
                        (rstructs/color color)))

(defn draw-ellipse-lines
  "Draw ellipse outline
  [int centerX, int centerY, float radiusH, float radiusV, Color color] -> void"
  [center-x center-y radius-h radius-v color]
  (raylib_h/DrawEllipseLines center-x
                             center-y
                             radius-h
                             radius-v
                             (rstructs/color color)))

(defn draw-ring
  "Draw ring
  [Vector2 center, float innerRadius, float outerRadius, float startAngle, float endAngle, int segments, Color color] -> void"
  [center inner-radius outer-radius start-angle end-angle segments color]
  (raylib_h/DrawRing (rstructs/vector2 center)
                     inner-radius
                     outer-radius
                     start-angle
                     end-angle
                     segments
                     (rstructs/color color)))

(defn draw-ring-lines
  "Draw ring outline
  [Vector2 center, float innerRadius, float outerRadius, float startAngle, float endAngle, int segments, Color color] -> void"
  [center inner-radius outer-radius start-angle end-angle segments color]
  (raylib_h/DrawRingLines (rstructs/vector2 center)
                          inner-radius
                          outer-radius
                          start-angle
                          end-angle
                          segments
                          (rstructs/color color)))

(defn draw-rectangle
  "Draw a color-filled rectangle
  [int posX, int posY, int width, int height, Color color] -> void"
  [pos-x pos-y width height color]
  (raylib_h/DrawRectangle pos-x pos-y width height (rstructs/color color)))

(defn draw-rectangle-v
  "Draw a color-filled rectangle (Vector version)
  [Vector2 position, Vector2 size, Color color] -> void"
  [position size color]
  (raylib_h/DrawRectangleV (rstructs/vector2 position)
                           (rstructs/vector2 size)
                           (rstructs/color color)))

(defn draw-rectangle-rec
  "Draw a color-filled rectangle
  [Rectangle rec, Color color] -> void"
  [rec color]
  (raylib_h/DrawRectangleRec (rstructs/rectangle rec) (rstructs/color color)))

(defn draw-rectangle-pro
  "Draw a color-filled rectangle with pro parameters
  [Rectangle rec, Vector2 origin, float rotation, Color color] -> void"
  [rec origin rotation color]
  (raylib_h/DrawRectanglePro (rstructs/rectangle rec)
                             (rstructs/vector2 origin)
                             rotation
                             (rstructs/color color)))

(defn draw-rectangle-gradient-v
  "Draw a vertical-gradient-filled rectangle
  [int posX, int posY, int width, int height, Color color1, Color color2] -> void"
  [pos-x pos-y width height color1 color2]
  (raylib_h/DrawRectangleGradientV pos-x
                                   pos-y
                                   width
                                   height
                                   (rstructs/color color1)
                                   (rstructs/color color2)))

(defn draw-rectangle-gradient-h
  "Draw a horizontal-gradient-filled rectangle
  [int posX, int posY, int width, int height, Color color1, Color color2] -> void"
  [pos-x pos-y width height color1 color2]
  (raylib_h/DrawRectangleGradientH pos-x
                                   pos-y
                                   width
                                   height
                                   (rstructs/color color1)
                                   (rstructs/color color2)))

(defn draw-rectangle-gradient-ex
  "Draw a gradient-filled rectangle with custom vertex colors
  [Rectangle rec, Color col1, Color col2, Color col3, Color col4] -> void"
  [rec col1 col2 col3 col4]
  (raylib_h/DrawRectangleGradientEx (rstructs/rectangle rec)
                                    (rstructs/color col1)
                                    (rstructs/color col2)
                                    (rstructs/color col3)
                                    (rstructs/color col4)))

(defn draw-rectangle-lines
  "Draw rectangle outline
  [int posX, int posY, int width, int height, Color color] -> void"
  [pos-x pos-y width height color]
  (raylib_h/DrawRectangleLines pos-x pos-y width height (rstructs/color color)))

(defn draw-rectangle-lines-ex
  "Draw rectangle outline with extended parameters
  [Rectangle rec, float lineThick, Color color] -> void"
  [rec line-thick color]
  (raylib_h/DrawRectangleLinesEx (rstructs/rectangle rec)
                                 line-thick
                                 (rstructs/color color)))

(defn draw-rectangle-rounded
  "Draw rectangle with rounded edges
  [Rectangle rec, float roundness, int segments, Color color] -> void"
  [rec roundness segments color]
  (raylib_h/DrawRectangleRounded (rstructs/rectangle rec)
                                 roundness
                                 segments
                                 (rstructs/color color)))

(defn draw-rectangle-rounded-lines
  "Draw rectangle with rounded edges outline
  [Rectangle rec, float roundness, int segments, float lineThick, Color color] -> void"
  [rec roundness segments line-thick color]
  (raylib_h/DrawRectangleRoundedLines (rstructs/rectangle rec)
                                      roundness
                                      segments
                                      line-thick
                                      (rstructs/color color)))

(defn draw-triangle
  "Draw a color-filled triangle (vertex in counter-clockwise order!)
  [Vector2 v1, Vector2 v2, Vector2 v3, Color color] -> void"
  [v1 v2 v3 color]
  (raylib_h/DrawTriangle (rstructs/vector2 v1)
                         (rstructs/vector2 v2)
                         (rstructs/vector2 v3)
                         (rstructs/color color)))

(defn draw-triangle-lines
  "Draw triangle outline (vertex in counter-clockwise order!)
  [Vector2 v1, Vector2 v2, Vector2 v3, Color color] -> void"
  [v1 v2 v3 color]
  (raylib_h/DrawTriangleLines (rstructs/vector2 v1)
                              (rstructs/vector2 v2)
                              (rstructs/vector2 v3)
                              (rstructs/color color)))

(defn draw-triangle-fan
  "Draw a triangle fan defined by points (first vertex is the center)
  [Vector2 * points, int pointCount, Color color] -> void"
  ([points point-count color]
   (raylib_h/DrawTriangleFan (rstructs/vector2-array points point-count)
                             point-count
                             (rstructs/color color)))
  ([points color]
   (raylib_h/DrawTriangleFan (rstructs/vector2-array points)
                             (count points)
                             (rstructs/color color))))

(defn draw-triangle-strip
  "Draw a triangle strip defined by points
  [Vector2 * points, int pointCount, Color color] -> void"
  ([points point-count color]
   (raylib_h/DrawTriangleStrip (rstructs/vector2-array points point-count)
                               point-count
                               (rstructs/color color)))
  ([points color]
   (raylib_h/DrawTriangleStrip (rstructs/vector2-array points)
                               (count points)
                               (rstructs/color color))))

(defn draw-poly
  "Draw a regular polygon (Vector version)
  [Vector2 center, int sides, float radius, float rotation, Color color] -> void"
  [center sides radius rotation color]
  (raylib_h/DrawPoly (rstructs/vector2 center)
                     sides
                     radius
                     rotation
                     (rstructs/color color)))

(defn draw-poly-lines
  "Draw a polygon outline of n sides
  [Vector2 center, int sides, float radius, float rotation, Color color] -> void"
  [center sides radius rotation color]
  (raylib_h/DrawPolyLines (rstructs/vector2 center)
                          sides
                          radius
                          rotation
                          (rstructs/color color)))

(defn draw-poly-lines-ex
  "Draw a polygon outline of n sides with extended parameters
  [Vector2 center, int sides, float radius, float rotation, float lineThick, Color color] -> void"
  [center sides radius rotation line-thick color]
  (raylib_h/DrawPolyLinesEx (rstructs/vector2 center)
                            sides
                            radius
                            rotation
                            line-thick
                            (rstructs/color color)))

(defn draw-spline-linear
  "Draw spline: Linear, minimum 2 points
  [Vector2 * points, int pointCount, float thick, Color color] -> void"
  ([points point-count thick color]
   (raylib_h/DrawSplineLinear (rstructs/vector2-array points point-count)
                              point-count
                              thick
                              (rstructs/color color)))
  ([points thick color]
   (raylib_h/DrawSplineLinear (rstructs/vector2-array points)
                              (count points)
                              thick
                              (rstructs/color color))))

(defn draw-spline-basis
  "Draw spline: B-Spline, minimum 4 points
  [Vector2 * points, int pointCount, float thick, Color color] -> void"
  ([points point-count thick color]
   (raylib_h/DrawSplineBasis (rstructs/vector2-array points point-count)
                             point-count
                             thick
                             (rstructs/color color)))
  ([points thick color]
   (raylib_h/DrawSplineBasis (rstructs/vector2-array points)
                             (count points)
                             thick
                             (rstructs/color color))))

(defn draw-spline-catmull-rom
  "Draw spline: Catmull-Rom, minimum 4 points
  [Vector2 * points, int pointCount, float thick, Color color] -> void"
  ([points point-count thick color]
   (raylib_h/DrawSplineCatmullRom (rstructs/vector2-array points point-count)
                                  point-count
                                  thick
                                  (rstructs/color color)))
  ([points thick color]
   (raylib_h/DrawSplineCatmullRom (rstructs/vector2-array points)
                                  (count points)
                                  thick
                                  (rstructs/color color))))

(defn draw-spline-bezier-quadratic
  "Draw spline: Quadratic Bezier, minimum 3 points (1 control point): [p1, c2, p3, c4...]
  [Vector2 * points, int pointCount, float thick, Color color] -> void"
  ([points point-count thick color]
   (raylib_h/DrawSplineBezierQuadratic (rstructs/vector2-array points
                                                               point-count)
                                       point-count
                                       thick
                                       (rstructs/color color)))
  ([points thick color]
   (raylib_h/DrawSplineBezierQuadratic (rstructs/vector2-array points)
                                       (count points)
                                       thick
                                       (rstructs/color color))))

(defn draw-spline-bezier-cubic
  "Draw spline: Cubic Bezier, minimum 4 points (2 control points): [p1, c2, c3, p4, c5, c6...]
  [Vector2 * points, int pointCount, float thick, Color color] -> void"
  ([points point-count thick color]
   (raylib_h/DrawSplineBezierCubic (rstructs/vector2-array points point-count)
                                   point-count
                                   thick
                                   (rstructs/color color)))
  ([points thick color]
   (raylib_h/DrawSplineBezierCubic (rstructs/vector2 points)
                                   (count points)
                                   thick
                                   (rstructs/color color))))

(defn draw-spline-segment-linear
  "Draw spline segment: Linear, 2 points
  [Vector2 p1, Vector2 p2, float thick, Color color] -> void"
  [p1 p2 thick color]
  (raylib_h/DrawSplineSegmentLinear (rstructs/vector2 p1)
                                    (rstructs/vector2 p2)
                                    thick
                                    (rstructs/color color)))

(defn draw-spline-segment-basis
  "Draw spline segment: B-Spline, 4 points
  [Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4, float thick, Color color] -> void"
  [p1 p2 p3 p4 thick color]
  (raylib_h/DrawSplineSegmentBasis (rstructs/vector2 p1)
                                   (rstructs/vector2 p2)
                                   (rstructs/vector2 p3)
                                   (rstructs/vector2 p4)
                                   thick
                                   (rstructs/color color)))

(defn draw-spline-segment-catmull-rom
  "Draw spline segment: Catmull-Rom, 4 points
  [Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4, float thick, Color color] -> void"
  [p1 p2 p3 p4 thick color]
  (raylib_h/DrawSplineSegmentCatmullRom (rstructs/vector2 p1)
                                        (rstructs/vector2 p2)
                                        (rstructs/vector2 p3)
                                        (rstructs/vector2 p4)
                                        thick
                                        (rstructs/color color)))

(defn draw-spline-segment-bezier-quadratic
  "Draw spline segment: Quadratic Bezier, 2 points, 1 control point
  [Vector2 p1, Vector2 c2, Vector2 p3, float thick, Color color] -> void"
  [p1 c2 p3 thick color]
  (raylib_h/DrawSplineSegmentBezierQuadratic (rstructs/vector2 p1)
                                             (rstructs/vector2 c2)
                                             (rstructs/vector2 p3)
                                             thick
                                             (rstructs/color color)))

(defn draw-spline-segment-bezier-cubic
  "Draw spline segment: Cubic Bezier, 2 points, 2 control points
  [Vector2 p1, Vector2 c2, Vector2 c3, Vector2 p4, float thick, Color color] -> void"
  [p1 c2 c3 p4 thick color]
  (raylib_h/DrawSplineSegmentBezierCubic (rstructs/vector2 p1)
                                         (rstructs/vector2 c2)
                                         (rstructs/vector2 c3)
                                         (rstructs/vector2 p4)
                                         thick
                                         (rstructs/color color)))

(defn get-spline-point-linear
  "Get (evaluate) spline point: Linear
  [Vector2 startPos, Vector2 endPos, float t] -> Vector2"
  [start-pos end-pos t]
  (rstructs/get-vector2 (raylib_h/GetSplinePointLinear
                          memory/*current-arena*
                          (rstructs/vector2 start-pos)
                          (rstructs/vector2 end-pos)
                          t)))

(defn get-spline-point-basis
  "Get (evaluate) spline point: B-Spline
  [Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4, float t] -> Vector2"
  [p1 p2 p3 p4 t]
  (rstructs/get-vector2 (raylib_h/GetSplinePointBasis memory/*current-arena*
                                                      (rstructs/vector2 p1)
                                                      (rstructs/vector2 p2)
                                                      (rstructs/vector2 p3)
                                                      (rstructs/vector2 p4)
                                                      t)))

(defn get-spline-point-catmull-rom
  "Get (evaluate) spline point: Catmull-Rom
  [Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4, float t] -> Vector2"
  [p1 p2 p3 p4 t]
  (rstructs/get-vector2 (raylib_h/GetSplinePointCatmullRom
                          memory/*current-arena*
                          (rstructs/vector2 p1)
                          (rstructs/vector2 p2)
                          (rstructs/vector2 p3)
                          (rstructs/vector2 p4)
                          t)))

(defn get-spline-point-bezier-quad
  "Get (evaluate) spline point: Quadratic Bezier
  [Vector2 p1, Vector2 c2, Vector2 p3, float t] -> Vector2"
  [p1 c2 p3 t]
  (rstructs/get-vector2 (raylib_h/GetSplinePointBezierQuad
                          memory/*current-arena*
                          (rstructs/vector2 p1)
                          (rstructs/vector2 c2)
                          (rstructs/vector2 p3)
                          t)))

(defn get-spline-point-bezier-cubic
  "Get (evaluate) spline point: Cubic Bezier
  [Vector2 p1, Vector2 c2, Vector2 c3, Vector2 p4, float t] -> Vector2"
  [p1 c2 c3 p4 t]
  (rstructs/get-vector2 (raylib_h/GetSplinePointBezierCubic
                          memory/*current-arena*
                          (rstructs/vector2 p1)
                          (rstructs/vector2 c2)
                          (rstructs/vector2 c3)
                          (rstructs/vector2 p4)
                          t)))

(defn check-collision-recs?
  "Check collision between two rectangles
  [Rectangle rec1, Rectangle rec2] -> bool"
  [rec1 rec2]
  (raylib_h/CheckCollisionRecs (rstructs/rectangle rec1)
                               (rstructs/rectangle rec2)))

(defn check-collision-circles?
  "Check collision between two circles
  [Vector2 center1, float radius1, Vector2 center2, float radius2] -> bool"
  [center1 radius1 center2 radius2]
  (raylib_h/CheckCollisionCircles (rstructs/vector2 center1)
                                  radius1
                                  (rstructs/vector2 center2)
                                  radius2))

(defn check-collision-circle-rec?
  "Check collision between circle and rectangle
  [Vector2 center, float radius, Rectangle rec] -> bool"
  [center radius rec]
  (raylib_h/CheckCollisionCircleRec (rstructs/vector2 center)
                                    radius
                                    (rstructs/rectangle rec)))

(defn check-collision-point-rec?
  "Check if point is inside rectangle
  [Vector2 point, Rectangle rec] -> bool"
  [point rec]
  (raylib_h/CheckCollisionPointRec (rstructs/vector2 point)
                                   (rstructs/rectangle rec)))

(defn check-collision-point-circle?
  "Check if point is inside circle
  [Vector2 point, Vector2 center, float radius] -> bool"
  [point center radius]
  (raylib_h/CheckCollisionPointCircle (rstructs/vector2 point)
                                      (rstructs/vector2 center)
                                      radius))

(defn check-collision-point-triangle?
  "Check if point is inside a triangle
  [Vector2 point, Vector2 p1, Vector2 p2, Vector2 p3] -> bool"
  [point p1 p2 p3]
  (raylib_h/CheckCollisionPointTriangle (rstructs/vector2 point)
                                        (rstructs/vector2 p1)
                                        (rstructs/vector2 p2)
                                        (rstructs/vector2 p3)))

(defn check-collision-point-poly?
  "Check if point is within a polygon described by array of vertices
  [Vector2 point, Vector2 * points, int pointCount] -> bool"
  ([point points point-count]
   (raylib_h/CheckCollisionPointPoly (rstructs/vector2 point)
                                     (rstructs/vector2-array points point-count)
                                     point-count))
  ([point points]
   (raylib_h/CheckCollisionPointPoly (rstructs/vector2 point)
                                     (rstructs/vector2-array points)
                                     (count points))))

(defn check-collision-lines?
  "Check the collision between two lines defined by two points each, returns collision point by reference
  [Vector2 startPos1, Vector2 endPos1, Vector2 startPos2, Vector2 endPos2, Vector2 * collisionPoint] -> bool"
  [start-pos1 end-pos1 start-pos2 end-pos2 collision-point]
  (raylib_h/CheckCollisionLines (rstructs/vector2 start-pos1)
                                (rstructs/vector2 end-pos1)
                                (rstructs/vector2 start-pos2)
                                (rstructs/vector2 end-pos2)
                                (rstructs/vector2 collision-point)))

(defn check-collision-point-line?
  "Check if point belongs to line created between two points [p1] and [p2] with defined margin in pixels [threshold]
  [Vector2 point, Vector2 p1, Vector2 p2, int threshold] -> bool"
  [point p1 p2 threshold]
  (raylib_h/CheckCollisionPointLine (rstructs/vector2 point)
                                    (rstructs/vector2 p1)
                                    (rstructs/vector2 p2)
                                    threshold))

(defn get-collision-rec
  "Get collision rectangle for two rectangles collision
  [Rectangle rec1, Rectangle rec2] -> Rectangle"
  [rec1 rec2]
  (rstructs/get-rectangle (raylib_h/GetCollisionRec memory/*current-arena*
                                                    (rstructs/rectangle rec1)
                                                    (rstructs/rectangle rec2))))

(defn load-image
  "Load image from file into CPU memory (RAM)
  [const char * fileName] -> Image"
  [file-name]
  (rstructs/get-image (raylib_h/LoadImage memory/*current-arena*
                                          (memory/string file-name))))

(defn load-image-raw
  "Load image from RAW file data
  [const char * fileName, int width, int height, int format, int headerSize] -> Image"
  [file-name width height format header-size]
  (rstructs/get-image (raylib_h/LoadImageRaw memory/*current-arena*
                                             (memory/string file-name)
                                             width
                                             height
                                             (if (keyword? format)
                                               (renums/pixel-format format)
                                               format)
                                             header-size)))

(defn load-image-svg
  "Load image from SVG file data or string with specified size
  [const char * fileNameOrString, int width, int height] -> Image"
  [file-name-or-string width height]
  (rstructs/get-image (raylib_h/LoadImageSvg memory/*current-arena*
                                             (memory/string file-name-or-string)
                                             width
                                             height)))

(defn load-image-anim
  "Load image sequence from file (frames appended to image.data)
  [const char * fileName, int * frames] -> Image"
  [file-name frames]
  (rstructs/get-image (raylib_h/LoadImageAnim memory/*current-arena*
                                              (memory/string file-name)
                                              frames)))

(defn load-image-from-memory
  "Load image from memory buffer, fileType refers to extension: i.e. '.png'
  [const char * fileType, const unsigned char * fileData, int dataSize] -> Image"
  [file-type file-data data-size]
  (rstructs/get-image (raylib_h/LoadImageFromMemory memory/*current-arena*
                                                    (memory/string file-type)
                                                    file-data
                                                    data-size)))

(defn load-image-from-texture
  "Load image from GPU texture data
  [Texture2D texture] -> Image"
  [texture]
  (rstructs/get-image (raylib_h/LoadImageFromTexture memory/*current-arena*
                                                     (rstructs/texture
                                                       texture))))

(defn load-image-from-screen
  "Load image from screen buffer and (screenshot)
  [] -> Image"
  []
  (rstructs/get-image (raylib_h/LoadImageFromScreen memory/*current-arena*)))

(defn image-ready?
  "Check if an image is ready
  [Image image] -> bool"
  [image]
  (raylib_h/IsImageReady (rstructs/image image)))

(defn unload-image
  "Unload image from CPU memory (RAM)
  [Image image] -> void"
  [image]
  (raylib_h/UnloadImage (rstructs/image image)))

(defn export-image?
  "Export image data to file, returns true on success
  [Image image, const char * fileName] -> bool"
  [image file-name]
  (raylib_h/ExportImage (rstructs/image image) (memory/string file-name)))

(defn export-image-to-memory
  "Export image to memory buffer
  [Image image, const char * fileType, int * fileSize] -> unsigned char *"
  [image file-type file-size]
  (raylib_h/ExportImageToMemory (rstructs/image image)
                                (memory/string file-type)
                                file-size))

(defn export-image-as-code?
  "Export image as code file defining an array of bytes, returns true on success
  [Image image, const char * fileName] -> bool"
  [image file-name]
  (raylib_h/ExportImageAsCode (rstructs/image image) (memory/string file-name)))

(defn gen-image-color
  "Generate image: plain color
  [int width, int height, Color color] -> Image"
  [width height color]
  (rstructs/get-image (raylib_h/GenImageColor memory/*current-arena*
                                              width
                                              height
                                              (rstructs/color color))))

(defn gen-image-gradient-linear
  "Generate image: linear gradient, direction in degrees [0..360], 0=Vertical gradient
  [int width, int height, int direction, Color start, Color end] -> Image"
  [width height direction start end]
  (rstructs/get-image (raylib_h/GenImageGradientLinear memory/*current-arena*
                                                       width
                                                       height
                                                       direction
                                                       (rstructs/color start)
                                                       (rstructs/color end))))

(defn gen-image-gradient-radial
  "Generate image: radial gradient
  [int width, int height, float density, Color inner, Color outer] -> Image"
  [width height density inner outer]
  (rstructs/get-image (raylib_h/GenImageGradientRadial memory/*current-arena*
                                                       width
                                                       height
                                                       density
                                                       (rstructs/color inner)
                                                       (rstructs/color outer))))

(defn gen-image-gradient-square
  "Generate image: square gradient
  [int width, int height, float density, Color inner, Color outer] -> Image"
  [width height density inner outer]
  (rstructs/get-image (raylib_h/GenImageGradientSquare memory/*current-arena*
                                                       width
                                                       height
                                                       density
                                                       (rstructs/color inner)
                                                       (rstructs/color outer))))

(defn gen-image-checked
  "Generate image: checked
  [int width, int height, int checksX, int checksY, Color col1, Color col2] -> Image"
  [width height checks-x checks-y col1 col2]
  (rstructs/get-image (raylib_h/GenImageChecked memory/*current-arena*
                                                width
                                                height
                                                checks-x
                                                checks-y
                                                (rstructs/color col1)
                                                (rstructs/color col2))))

(defn gen-image-white-noise
  "Generate image: white noise
  [int width, int height, float factor] -> Image"
  [width height factor]
  (rstructs/get-image
    (raylib_h/GenImageWhiteNoise memory/*current-arena* width height factor)))

(defn gen-image-perlin-noise
  "Generate image: perlin noise
  [int width, int height, int offsetX, int offsetY, float scale] -> Image"
  [width height offset-x offset-y scale]
  (rstructs/get-image (raylib_h/GenImagePerlinNoise memory/*current-arena*
                                                    width
                                                    height
                                                    offset-x
                                                    offset-y
                                                    scale)))

(defn gen-image-cellular
  "Generate image: cellular algorithm, bigger tileSize means bigger cells
  [int width, int height, int tileSize] -> Image"
  [width height tile-size]
  (rstructs/get-image
    (raylib_h/GenImageCellular memory/*current-arena* width height tile-size)))

(defn gen-image-text
  "Generate image: grayscale image from text data
  [int width, int height, const char * text] -> Image"
  [width height text]
  (rstructs/get-image (raylib_h/GenImageText memory/*current-arena*
                                             width
                                             height
                                             (memory/string text))))

(defn image-copy
  "Create an image duplicate (useful for transformations)
  [Image image] -> Image"
  [image]
  (rstructs/get-image (raylib_h/ImageCopy memory/*current-arena*
                                          (rstructs/image image))))

(defn image-from-image
  "Create an image from another image piece
  [Image image, Rectangle rec] -> Image"
  [image rec]
  (rstructs/get-image (raylib_h/ImageFromImage memory/*current-arena*
                                               (rstructs/image image)
                                               (rstructs/rectangle rec))))

(defn image-text
  "Create an image from text (default font)
  [const char * text, int fontSize, Color color] -> Image"
  [text font-size color]
  (rstructs/get-image (raylib_h/ImageText memory/*current-arena*
                                          (memory/string text)
                                          font-size
                                          (rstructs/color color))))

(defn image-text-ex
  "Create an image from text (custom sprite font)
  [Font font, const char * text, float fontSize, float spacing, Color tint] -> Image"
  [font text font-size spacing tint]
  (rstructs/get-image (raylib_h/ImageTextEx memory/*current-arena*
                                            (rstructs/font font)
                                            (memory/string text)
                                            font-size
                                            spacing
                                            (rstructs/color tint))))

(defn image-format
  "Convert image data to desired format
  [Image * image, int newFormat] -> void"
  [image new-format]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageFormat
      first-arg
      (if (keyword? new-format) (renums/pixel-format new-format) new-format))
    (rstructs/get-image first-arg)))

(defn image-to-pot
  "Convert image to POT (power-of-two)
  [Image * image, Color fill] -> void"
  [image fill]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageToPOT first-arg (rstructs/color fill))
    (rstructs/get-image first-arg)))

(defn image-crop
  "Crop an image to a defined rectangle
  [Image * image, Rectangle crop] -> void"
  [image crop]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageCrop first-arg (rstructs/rectangle crop))
    (rstructs/get-image first-arg)))

(defn image-alpha-crop
  "Crop image depending on alpha value
  [Image * image, float threshold] -> void"
  [image threshold]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageAlphaCrop first-arg threshold)
    (rstructs/get-image first-arg)))

(defn image-alpha-clear
  "Clear alpha channel to desired color
  [Image * image, Color color, float threshold] -> void"
  [image color threshold]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageAlphaClear first-arg (rstructs/color color) threshold)
    (rstructs/get-image first-arg)))

(defn image-alpha-mask
  "Apply alpha mask to image
  [Image * image, Image alphaMask] -> void"
  [image alpha-mask]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageAlphaMask first-arg (rstructs/image alpha-mask))
    (rstructs/get-image first-arg)))

(defn image-alpha-premultiply
  "Premultiply alpha channel
  [Image * image] -> void"
  [image]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageAlphaPremultiply first-arg)
    (rstructs/get-image first-arg)))

(defn image-blur-gaussian
  "Apply Gaussian blur using a box blur approximation
  [Image * image, int blurSize] -> void"
  [image blur-size]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageBlurGaussian first-arg blur-size)
    (rstructs/get-image first-arg)))

(defn image-resize
  "Resize image (Bicubic scaling algorithm)
  [Image * image, int newWidth, int newHeight] -> void"
  [image new-width new-height]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageResize first-arg new-width new-height)
    (rstructs/get-image first-arg)))

(defn image-resize-nn
  "Resize image (Nearest-Neighbor scaling algorithm)
  [Image * image, int newWidth, int newHeight] -> void"
  [image new-width new-height]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageResizeNN first-arg new-width new-height)
    (rstructs/get-image first-arg)))

(defn image-resize-canvas
  "Resize canvas and fill with color
  [Image * image, int newWidth, int newHeight, int offsetX, int offsetY, Color fill] -> void"
  [image new-width new-height offset-x offset-y fill]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageResizeCanvas first-arg
                                new-width
                                new-height
                                offset-x
                                offset-y
                                (rstructs/color fill))
    (rstructs/get-image first-arg)))

(defn image-mipmaps
  "Compute all mipmap levels for a provided image
  [Image * image] -> void"
  [image]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageMipmaps first-arg)
    (rstructs/get-image first-arg)))

(defn image-dither
  "Dither image data to 16bpp or lower (Floyd-Steinberg dithering)
  [Image * image, int rBpp, int gBpp, int bBpp, int aBpp] -> void"
  [image r-bpp g-bpp b-bpp a-bpp]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageDither first-arg r-bpp g-bpp b-bpp a-bpp)
    (rstructs/get-image first-arg)))

(defn image-flip-vertical
  "Flip image vertically
  [Image * image] -> void"
  [image]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageFlipVertical first-arg)
    (rstructs/get-image first-arg)))

(defn image-flip-horizontal
  "Flip image horizontally
  [Image * image] -> void"
  [image]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageFlipHorizontal first-arg)
    (rstructs/get-image first-arg)))

(defn image-rotate
  "Rotate image by input angle in degrees (-359 to 359)
  [Image * image, int degrees] -> void"
  [image degrees]
  (raylib_h/ImageRotate (rstructs/image image) degrees))

(defn image-rotate-cw
  "Rotate image clockwise 90deg
  [Image * image] -> void"
  [image]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageRotateCW first-arg)
    (rstructs/get-image first-arg)))

(defn image-rotate-ccw
  "Rotate image counter-clockwise 90deg
  [Image * image] -> void"
  [image]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageRotateCCW first-arg)
    (rstructs/get-image first-arg)))

(defn image-color-tint
  "Modify image color: tint
  [Image * image, Color color] -> void"
  [image color]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageColorTint first-arg (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-color-invert
  "Modify image color: invert
  [Image * image] -> void"
  [image]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageColorInvert first-arg)
    (rstructs/get-image first-arg)))

(defn image-color-grayscale
  "Modify image color: grayscale
  [Image * image] -> void"
  [image]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageColorGrayscale first-arg)
    (rstructs/get-image first-arg)))

(defn image-color-contrast
  "Modify image color: contrast (-100 to 100)
  [Image * image, float contrast] -> void"
  [image contrast]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageColorContrast first-arg contrast)
    (rstructs/get-image first-arg)))

(defn image-color-brightness
  "Modify image color: brightness (-255 to 255)
  [Image * image, int brightness] -> void"
  [image brightness]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageColorBrightness first-arg brightness)
    (rstructs/get-image first-arg)))

(defn image-color-replace
  "Modify image color: replace color
  [Image * image, Color color, Color replace] -> void"
  [image color replace]
  (let [first-arg (rstructs/image image)]
    (raylib_h/ImageColorReplace first-arg
                                (rstructs/color color)
                                (rstructs/color replace))
    (rstructs/get-image first-arg)))

(defn load-image-colors
  "Load color data from image as a Color array (RGBA - 32bit)
  [Image image] -> Color *"
  [image]
  (raylib_h/LoadImageColors (rstructs/image image)))

(defn load-image-palette
  "Load colors palette from image as a Color array (RGBA - 32bit)
  [Image image, int maxPaletteSize, int * colorCount] -> Color *"
  [image max-palette-size color-count]
  (raylib_h/LoadImagePalette (rstructs/image image)
                             max-palette-size
                             color-count))

(defn unload-image-colors
  "Unload color data loaded with LoadImageColors()
  [Color * colors] -> void"
  [colors]
  (raylib_h/UnloadImageColors (rstructs/color colors)))

(defn unload-image-palette
  "Unload colors palette loaded with LoadImagePalette()
  [Color * colors] -> void"
  [colors]
  (raylib_h/UnloadImagePalette (rstructs/color colors)))

(defn get-image-alpha-border
  "Get image alpha border rectangle
  [Image image, float threshold] -> Rectangle"
  [image threshold]
  (rstructs/get-rectangle (raylib_h/GetImageAlphaBorder memory/*current-arena*
                                                        (rstructs/image image)
                                                        threshold)))

(defn get-image-color
  "Get image pixel color at (x, y) position
  [Image image, int x, int y] -> Color"
  [image x y]
  (rstructs/get-color
    (raylib_h/GetImageColor memory/*current-arena* (rstructs/image image) x y)))

(defn image-clear-background
  "Clear image background with given color
  [Image * dst, Color color] -> void"
  [dst color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageClearBackground first-arg (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-pixel
  "Draw pixel within an image
  [Image * dst, int posX, int posY, Color color] -> void"
  [dst pos-x pos-y color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawPixel first-arg pos-x pos-y (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-pixel-v
  "Draw pixel within an image (Vector version)
  [Image * dst, Vector2 position, Color color] -> void"
  [dst position color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawPixelV first-arg
                              (rstructs/vector2 position)
                              (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-line
  "Draw line within an image
  [Image * dst, int startPosX, int startPosY, int endPosX, int endPosY, Color color] -> void"
  [dst start-pos-x start-pos-y end-pos-x end-pos-y color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawLine first-arg
                            start-pos-x
                            start-pos-y
                            end-pos-x
                            end-pos-y
                            (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-line-v
  "Draw line within an image (Vector version)
  [Image * dst, Vector2 start, Vector2 end, Color color] -> void"
  [dst start end color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawLineV first-arg
                             (rstructs/vector2 start)
                             (rstructs/vector2 end)
                             (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-circle
  "Draw a filled circle within an image
  [Image * dst, int centerX, int centerY, int radius, Color color] -> void"
  [dst center-x center-y radius color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawCircle first-arg
                              center-x
                              center-y
                              radius
                              (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-circle-v
  "Draw a filled circle within an image (Vector version)
  [Image * dst, Vector2 center, int radius, Color color] -> void"
  [dst center radius color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawCircleV first-arg
                               (rstructs/vector2 center)
                               radius
                               (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-circle-lines
  "Draw circle outline within an image
  [Image * dst, int centerX, int centerY, int radius, Color color] -> void"
  [dst center-x center-y radius color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawCircleLines first-arg
                                   center-x
                                   center-y
                                   radius
                                   (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-circle-lines-v
  "Draw circle outline within an image (Vector version)
  [Image * dst, Vector2 center, int radius, Color color] -> void"
  [dst center radius color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawCircleLinesV first-arg
                                    (rstructs/vector2 center)
                                    radius
                                    (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-rectangle
  "Draw rectangle within an image
  [Image * dst, int posX, int posY, int width, int height, Color color] -> void"
  [dst pos-x pos-y width height color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawRectangle first-arg
                                 pos-x
                                 pos-y
                                 width
                                 height
                                 (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-rectangle-v
  "Draw rectangle within an image (Vector version)
  [Image * dst, Vector2 position, Vector2 size, Color color] -> void"
  [dst position size color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawRectangleV first-arg
                                  (rstructs/vector2 position)
                                  (rstructs/vector2 size)
                                  (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-rectangle-rec
  "Draw rectangle within an image
  [Image * dst, Rectangle rec, Color color] -> void"
  [dst rec color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawRectangleRec first-arg
                                    (rstructs/rectangle rec)
                                    (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-rectangle-lines
  "Draw rectangle lines within an image
  [Image * dst, Rectangle rec, int thick, Color color] -> void"
  [dst rec thick color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawRectangleLines first-arg
                                      (rstructs/rectangle rec)
                                      thick
                                      (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw
  "Draw a source image within a destination image (tint applied to source)
  [Image * dst, Image src, Rectangle srcRec, Rectangle dstRec, Color tint] -> void"
  [dst src src-rec dst-rec tint]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDraw first-arg
                        (rstructs/image src)
                        (rstructs/rectangle src-rec)
                        (rstructs/rectangle dst-rec)
                        (rstructs/color tint))
    (rstructs/get-image first-arg)))

(defn image-draw-text
  "Draw text (using default font) within an image (destination)
  [Image * dst, const char * text, int posX, int posY, int fontSize, Color color] -> void"
  [dst text pos-x pos-y font-size color]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawText first-arg
                            (memory/string text)
                            pos-x
                            pos-y
                            font-size
                            (rstructs/color color))
    (rstructs/get-image first-arg)))

(defn image-draw-text-ex
  "Draw text (custom sprite font) within an image (destination)
  [Image * dst, Font font, const char * text, Vector2 position, float fontSize, float spacing, Color tint] -> void"
  [dst font text position font-size spacing tint]
  (let [first-arg (rstructs/image dst)]
    (raylib_h/ImageDrawTextEx first-arg
                              (rstructs/font font)
                              (memory/string text)
                              (rstructs/vector2 position)
                              font-size
                              spacing
                              (rstructs/color tint))
    (rstructs/get-image first-arg)))

(defn load-texture
  "Load texture from file into GPU memory (VRAM)
  [const char * fileName] -> Texture2D"
  [file-name]
  (rstructs/get-texture (raylib_h/LoadTexture memory/*current-arena*
                                              (memory/string file-name))))

(defn load-texture-from-image
  "Load texture from image data
  [Image image] -> Texture2D"
  [image]
  (rstructs/get-texture (raylib_h/LoadTextureFromImage memory/*current-arena*
                                                       (rstructs/image image))))

(defn load-texture-cubemap
  "Load cubemap from image, multiple image cubemap layouts supported
  [Image image, int layout] -> TextureCubemap"
  [image layout]
  (rstructs/get-texture
    (raylib_h/LoadTextureCubemap
      memory/*current-arena*
      (rstructs/image image)
      (if (keyword? layout) (renums/cubemap-layout layout) layout))))

(defn load-render-texture
  "Load texture for rendering (framebuffer)
  [int width, int height] -> RenderTexture2D"
  [width height]
  (rstructs/get-render-texture
    (raylib_h/LoadRenderTexture memory/*current-arena* width height)))

(defn texture-ready?
  "Check if a texture is ready
  [Texture2D texture] -> bool"
  [texture]
  (raylib_h/IsTextureReady (rstructs/texture texture)))

(defn unload-texture
  "Unload texture from GPU memory (VRAM)
  [Texture2D texture] -> void"
  [texture]
  (raylib_h/UnloadTexture (rstructs/texture texture)))

(defn render-texture-ready?
  "Check if a render texture is ready
  [RenderTexture2D target] -> bool"
  [target]
  (raylib_h/IsRenderTextureReady (rstructs/render-texture target)))

(defn unload-render-texture
  "Unload render texture from GPU memory (VRAM)
  [RenderTexture2D target] -> void"
  [target]
  (raylib_h/UnloadRenderTexture (rstructs/render-texture target)))

(defn update-texture
  "Update GPU texture with new data
  [Texture2D texture, const void * pixels] -> void"
  [texture pixels]
  (raylib_h/UpdateTexture (rstructs/texture texture) pixels))

(defn update-texture-rec
  "Update GPU texture rectangle with new data
  [Texture2D texture, Rectangle rec, const void * pixels] -> void"
  [texture rec pixels]
  (raylib_h/UpdateTextureRec (rstructs/texture texture)
                             (rstructs/rectangle rec)
                             pixels))

(defn gen-texture-mipmaps
  "Generate GPU mipmaps for a texture
  [Texture2D * texture] -> void"
  [texture]
  (raylib_h/GenTextureMipmaps (rstructs/texture texture)))

(defn set-texture-filter
  "Set texture scaling filter mode
  [Texture2D texture, int filter] -> void"
  [texture filter]
  (raylib_h/SetTextureFilter
    (rstructs/texture texture)
    (if (keyword? filter) (renums/texture-filter filter) filter)))

(defn set-texture-wrap
  "Set texture wrapping mode
  [Texture2D texture, int wrap] -> void"
  [texture wrap]
  (raylib_h/SetTextureWrap
    (rstructs/texture texture)
    (if (keyword? wrap) (renums/texture-wrap wrap) wrap)))

(defn draw-texture
  "Draw a Texture2D
  [Texture2D texture, int posX, int posY, Color tint] -> void"
  [texture pos-x pos-y tint]
  (raylib_h/DrawTexture (rstructs/texture texture)
                        pos-x
                        pos-y
                        (rstructs/color tint)))

(defn draw-texture-v
  "Draw a Texture2D with position defined as Vector2
  [Texture2D texture, Vector2 position, Color tint] -> void"
  [texture position tint]
  (raylib_h/DrawTextureV (rstructs/texture texture)
                         (rstructs/vector2 position)
                         (rstructs/color tint)))

(defn draw-texture-ex
  "Draw a Texture2D with extended parameters
  [Texture2D texture, Vector2 position, float rotation, float scale, Color tint] -> void"
  [texture position rotation scale tint]
  (raylib_h/DrawTextureEx (rstructs/texture texture)
                          (rstructs/vector2 position)
                          rotation
                          scale
                          (rstructs/color tint)))

(defn draw-texture-rec
  "Draw a part of a texture defined by a rectangle
  [Texture2D texture, Rectangle source, Vector2 position, Color tint] -> void"
  [texture source position tint]
  (raylib_h/DrawTextureRec (rstructs/texture texture)
                           (rstructs/rectangle source)
                           (rstructs/vector2 position)
                           (rstructs/color tint)))

(defn draw-texture-pro
  "Draw a part of a texture defined by a rectangle with 'pro' parameters
  [Texture2D texture, Rectangle source, Rectangle dest, Vector2 origin, float rotation, Color tint] -> void"
  [texture source dest origin rotation tint]
  (raylib_h/DrawTexturePro (rstructs/texture texture)
                           (rstructs/rectangle source)
                           (rstructs/rectangle dest)
                           (rstructs/vector2 origin)
                           rotation
                           (rstructs/color tint)))

(defn draw-texture-npatch
  "Draws a texture (or part of it) that stretches or shrinks nicely
  [Texture2D texture, NPatchInfo nPatchInfo, Rectangle dest, Vector2 origin, float rotation, Color tint] -> void"
  [texture n-patch-info dest origin rotation tint]
  (raylib_h/DrawTextureNPatch (rstructs/texture texture)
                              (rstructs/npatch-info n-patch-info)
                              (rstructs/rectangle dest)
                              (rstructs/vector2 origin)
                              rotation
                              (rstructs/color tint)))

(defn fade
  "Get color with alpha applied, alpha goes from 0.0f to 1.0f
  [Color color, float alpha] -> Color"
  [color alpha]
  (rstructs/get-color
    (raylib_h/Fade memory/*current-arena* (rstructs/color color) alpha)))

(defn color-to-int
  "Get hexadecimal value for a Color
  [Color color] -> int"
  [color]
  (raylib_h/ColorToInt (rstructs/color color)))

(defn color-normalize
  "Get Color normalized as float [0..1]
  [Color color] -> Vector4"
  [color]
  (rstructs/get-vector4 (raylib_h/ColorNormalize memory/*current-arena*
                                                 (rstructs/color color))))

(defn color-from-normalized
  "Get Color from normalized values [0..1]
  [Vector4 normalized] -> Color"
  [normalized]
  (rstructs/get-color (raylib_h/ColorFromNormalized memory/*current-arena*
                                                    (rstructs/vector4
                                                      normalized))))

(defn color-to-hsv
  "Get HSV values for a Color, hue [0..360], saturation/value [0..1]
  [Color color] -> Vector3"
  [color]
  (rstructs/get-vector3 (raylib_h/ColorToHSV memory/*current-arena*
                                             (rstructs/color color))))

(defn color-from-hsv
  "Get a Color from HSV values, hue [0..360], saturation/value [0..1]
  [float hue, float saturation, float value] -> Color"
  [hue saturation value]
  (rstructs/get-color
    (raylib_h/ColorFromHSV memory/*current-arena* hue saturation value)))

(defn color-tint
  "Get color multiplied with another color
  [Color color, Color tint] -> Color"
  [color tint]
  (rstructs/get-color (raylib_h/ColorTint memory/*current-arena*
                                          (rstructs/color color)
                                          (rstructs/color tint))))

(defn color-brightness
  "Get color with brightness correction, brightness factor goes from -1.0f to 1.0f
  [Color color, float factor] -> Color"
  [color factor]
  (rstructs/get-color (raylib_h/ColorBrightness memory/*current-arena*
                                                (rstructs/color color)
                                                factor)))

(defn color-contrast
  "Get color with contrast correction, contrast values between -1.0f and 1.0f
  [Color color, float contrast] -> Color"
  [color contrast]
  (rstructs/get-color (raylib_h/ColorContrast memory/*current-arena*
                                              (rstructs/color color)
                                              contrast)))

(defn color-alpha
  "Get color with alpha applied, alpha goes from 0.0f to 1.0f
  [Color color, float alpha] -> Color"
  [color alpha]
  (rstructs/get-color
    (raylib_h/ColorAlpha memory/*current-arena* (rstructs/color color) alpha)))

(defn color-alpha-blend
  "Get src alpha-blended into dst color with tint
  [Color dst, Color src, Color tint] -> Color"
  [dst src tint]
  (rstructs/get-color (raylib_h/ColorAlphaBlend memory/*current-arena*
                                                (rstructs/color dst)
                                                (rstructs/color src)
                                                (rstructs/color tint))))

(defn get-color
  "Get Color structure from hexadecimal value
  [unsigned int hexValue] -> Color"
  [hex-value]
  (rstructs/get-color (raylib_h/GetColor memory/*current-arena* hex-value)))

(defn get-pixel-color
  "Get Color from a source pixel pointer of certain format
  [void * srcPtr, int format] -> Color"
  [src-ptr format]
  (rstructs/get-color
    (raylib_h/GetPixelColor memory/*current-arena* src-ptr format)))

(defn set-pixel-color
  "Set color formatted into destination pixel pointer
  [void * dstPtr, Color color, int format] -> void"
  [dst-ptr color format]
  (raylib_h/SetPixelColor dst-ptr (rstructs/color color) format))

(defn get-pixel-data-size
  "Get pixel data size in bytes for certain format
  [int width, int height, int format] -> int"
  [width height format]
  (raylib_h/GetPixelDataSize width height format))

(defn get-font-default
  "Get the default Font
  [] -> Font"
  []
  (rstructs/get-font (raylib_h/GetFontDefault memory/*current-arena*)))

(defn load-font
  "Load font from file into GPU memory (VRAM)
  [const char * fileName] -> Font"
  [file-name]
  (rstructs/get-font (raylib_h/LoadFont memory/*current-arena*
                                        (memory/string file-name))))

(defn load-font-ex
  "Load font from file with extended parameters, use NULL for codepoints and 0 for codepointCount to load the default character setFont
  [const char * fileName, int fontSize, int * codepoints, int codepointCount] -> Font"
  [file-name font-size codepoints codepoint-count]
  (rstructs/get-font (raylib_h/LoadFontEx memory/*current-arena*
                                          (memory/string file-name)
                                          font-size
                                          codepoints
                                          codepoint-count)))

(defn load-font-from-image
  "Load font from Image (XNA style)
  [Image image, Color key, int firstChar] -> Font"
  [image key first-char]
  (rstructs/get-font (raylib_h/LoadFontFromImage memory/*current-arena*
                                                 (rstructs/image image)
                                                 (rstructs/color key)
                                                 first-char)))

(defn load-font-from-memory
  "Load font from memory buffer, fileType refers to extension: i.e. '.ttf'
  [const char * fileType, const unsigned char * fileData, int dataSize, int fontSize, int * codepoints, int codepointCount] -> Font"
  [file-type file-data data-size font-size codepoints codepoint-count]
  (rstructs/get-font (raylib_h/LoadFontFromMemory memory/*current-arena*
                                                  (memory/string file-type)
                                                  file-data
                                                  data-size
                                                  font-size
                                                  codepoints
                                                  codepoint-count)))

(defn font-ready?
  "Check if a font is ready
  [Font font] -> bool"
  [font]
  (raylib_h/IsFontReady (rstructs/font font)))

(defn load-font-data
  "Load font data for further use
  [const unsigned char * fileData, int dataSize, int fontSize, int * codepoints, int codepointCount, int type] -> GlyphInfo *"
  [file-data data-size font-size codepoints codepoint-count type]
  (raylib_h/LoadFontData file-data
                         data-size
                         font-size
                         codepoints
                         codepoint-count
                         (if (keyword? type) (renums/font-type type) type)))

(defn gen-image-font-atlas
  "Generate image font atlas using chars info
  [const GlyphInfo * glyphs, Rectangle ** glyphRecs, int glyphCount, int fontSize, int padding, int packMethod] -> Image"
  [glyphs glyph-recs glyph-count font-size padding pack-method]
  (rstructs/get-image (raylib_h/GenImageFontAtlas memory/*current-arena*
                                                  (rstructs/glyph-info glyphs)
                                                  (rstructs/rectangle
                                                    glyph-recs)
                                                  glyph-count
                                                  font-size
                                                  padding
                                                  pack-method)))

(defn unload-font-data
  "Unload font chars info data (RAM)
  [GlyphInfo * glyphs, int glyphCount] -> void"
  [glyphs glyph-count]
  (raylib_h/UnloadFontData (rstructs/glyph-info glyphs) glyph-count))

(defn unload-font
  "Unload font from GPU memory (VRAM)
  [Font font] -> void"
  [font]
  (raylib_h/UnloadFont (rstructs/font font)))

(defn export-font-as-code?
  "Export font as code file, returns true on success
  [Font font, const char * fileName] -> bool"
  [font file-name]
  (raylib_h/ExportFontAsCode (rstructs/font font) (memory/string file-name)))

(defn draw-fps
  "Draw current FPS
  [int posX, int posY] -> void"
  [pos-x pos-y]
  (raylib_h/DrawFPS pos-x pos-y))

(defn draw-text
  "Draw text (using default font)
  [const char * text, int posX, int posY, int fontSize, Color color] -> void"
  [text pos-x pos-y font-size color]
  (raylib_h/DrawText (memory/string text)
                     pos-x
                     pos-y
                     font-size
                     (rstructs/color color)))

(defn draw-text-ex
  "Draw text using font and additional parameters
  [Font font, const char * text, Vector2 position, float fontSize, float spacing, Color tint] -> void"
  [font text position font-size spacing tint]
  (raylib_h/DrawTextEx (rstructs/font font)
                       (memory/string text)
                       (rstructs/vector2 position)
                       font-size
                       spacing
                       (rstructs/color tint)))

(defn draw-text-pro
  "Draw text using Font and pro parameters (rotation)
  [Font font, const char * text, Vector2 position, Vector2 origin, float rotation, float fontSize, float spacing, Color tint] -> void"
  [font text position origin rotation font-size spacing tint]
  (raylib_h/DrawTextPro (rstructs/font font)
                        (memory/string text)
                        (rstructs/vector2 position)
                        (rstructs/vector2 origin)
                        rotation
                        font-size
                        spacing
                        (rstructs/color tint)))

(defn draw-text-codepoint
  "Draw one character (codepoint)
  [Font font, int codepoint, Vector2 position, float fontSize, Color tint] -> void"
  [font codepoint position font-size tint]
  (raylib_h/DrawTextCodepoint (rstructs/font font)
                              codepoint
                              (rstructs/vector2 position)
                              font-size
                              (rstructs/color tint)))

(defn draw-text-codepoints
  "Draw multiple character (codepoint)
  [Font font, const int * codepoints, int codepointCount, Vector2 position, float fontSize, float spacing, Color tint] -> void"
  [font codepoints codepoint-count position font-size spacing tint]
  (raylib_h/DrawTextCodepoints (rstructs/font font)
                               codepoints
                               codepoint-count
                               (rstructs/vector2 position)
                               font-size
                               spacing
                               (rstructs/color tint)))

(defn set-text-line-spacing
  "Set vertical line spacing when drawing with line-breaks
  [int spacing] -> void"
  [spacing]
  (raylib_h/SetTextLineSpacing spacing))

(defn measure-text
  "Measure string width for default font
  [const char * text, int fontSize] -> int"
  [text font-size]
  (raylib_h/MeasureText (memory/string text) font-size))

(defn measure-text-ex
  "Measure string size for Font
  [Font font, const char * text, float fontSize, float spacing] -> Vector2"
  [font text font-size spacing]
  (rstructs/get-vector2 (raylib_h/MeasureTextEx memory/*current-arena*
                                                (rstructs/font font)
                                                (memory/string text)
                                                font-size
                                                spacing)))

(defn get-glyph-index
  "Get glyph index position in font for a codepoint (unicode character), fallback to '?' if not found
  [Font font, int codepoint] -> int"
  [font codepoint]
  (raylib_h/GetGlyphIndex (rstructs/font font) codepoint))

(defn get-glyph-info
  "Get glyph font info data for a codepoint (unicode character), fallback to '?' if not found
  [Font font, int codepoint] -> GlyphInfo"
  [font codepoint]
  (rstructs/get-glyph-info (raylib_h/GetGlyphInfo memory/*current-arena*
                                                  (rstructs/font font)
                                                  codepoint)))

(defn get-glyph-atlas-rec
  "Get glyph rectangle in font atlas for a codepoint (unicode character), fallback to '?' if not found
  [Font font, int codepoint] -> Rectangle"
  [font codepoint]
  (rstructs/get-rectangle (raylib_h/GetGlyphAtlasRec memory/*current-arena*
                                                     (rstructs/font font)
                                                     codepoint)))

(defn load-utf8
  "Load UTF-8 text encoded from codepoints array
  [const int * codepoints, int length] -> char *"
  [codepoints length]
  (raylib_h/LoadUTF8 codepoints length))

(defn unload-utf8
  "Unload UTF-8 text encoded from codepoints array
  [char * text] -> void"
  [text]
  (raylib_h/UnloadUTF8 text))

(defn load-codepoints
  "Load all codepoints from a UTF-8 text string, codepoints count returned by parameter
  [const char * text, int * count] -> int *"
  [text count]
  (raylib_h/LoadCodepoints (memory/string text) count))

(defn unload-codepoints
  "Unload codepoints data from memory
  [int * codepoints] -> void"
  [codepoints]
  (raylib_h/UnloadCodepoints codepoints))

(defn get-codepoint-count
  "Get total number of codepoints in a UTF-8 encoded string
  [const char * text] -> int"
  [text]
  (raylib_h/GetCodepointCount (memory/string text)))

(defn get-codepoint
  "Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
  [const char * text, int * codepointSize] -> int"
  [text codepoint-size]
  (raylib_h/GetCodepoint (memory/string text) codepoint-size))

(defn get-codepoint-next
  "Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
  [const char * text, int * codepointSize] -> int"
  [text codepoint-size]
  (raylib_h/GetCodepointNext (memory/string text) codepoint-size))

(defn get-codepoint-previous
  "Get previous codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
  [const char * text, int * codepointSize] -> int"
  [text codepoint-size]
  (raylib_h/GetCodepointPrevious (memory/string text) codepoint-size))

(defn codepoint-to-utf8
  "Encode one codepoint into UTF-8 byte array (array length returned as parameter)
  [int codepoint, int * utf8Size] -> const char *"
  [codepoint utf8size]
  (raylib_h/CodepointToUTF8 codepoint utf8size))

(defn text-copy
  "Copy one string to another, returns bytes copied
  [char * dst, const char * src] -> int"
  [dst src]
  (raylib_h/TextCopy dst (memory/string src)))

(defn text-is-equal?
  "Check if two text string are equal
  [const char * text1, const char * text2] -> bool"
  [text1 text2]
  (raylib_h/TextIsEqual (memory/string text1) (memory/string text2)))

(defn text-length
  "Get text length, checks for '\\0' ending
  [const char * text] -> unsigned int"
  [text]
  (raylib_h/TextLength (memory/string text)))

(defn text-format
  "Text formatting with variables (sprintf() style)
  [const char * text, ... args] -> const char *"
  [text args]
  (raylib_h/TextFormat (memory/string text) args))

(defn text-subtext
  "Get a piece of a text string
  [const char * text, int position, int length] -> const char *"
  [text position length]
  (raylib_h/TextSubtext (memory/string text) position length))

(defn text-replace
  "Replace text string (WARNING: memory must be freed!)
  [char * text, const char * replace, const char * by] -> char *"
  [text replace by]
  (raylib_h/TextReplace text (memory/string replace) (memory/string by)))

(defn text-insert
  "Insert text in a position (WARNING: memory must be freed!)
  [const char * text, const char * insert, int position] -> char *"
  [text insert position]
  (raylib_h/TextInsert (memory/string text) (memory/string insert) position))

(defn text-join
  "Join text strings with delimiter
  [const char ** textList, int count, const char * delimiter] -> const char *"
  [text-list count delimiter]
  (raylib_h/TextJoin text-list count (memory/string delimiter)))

(defn text-split
  "Split text into multiple strings
  [const char * text, char delimiter, int * count] -> const char **"
  [text delimiter count]
  (raylib_h/TextSplit (memory/string text) delimiter count))

(defn text-append
  "Append text at specific position and move cursor!
  [char * text, const char * append, int * position] -> void"
  [text append position]
  (raylib_h/TextAppend text (memory/string append) position))

(defn text-find-index
  "Find first text occurrence within a string
  [const char * text, const char * find] -> int"
  [text find]
  (raylib_h/TextFindIndex (memory/string text) (memory/string find)))

(defn text-to-upper
  "Get upper case version of provided string
  [const char * text] -> const char *"
  [text]
  (raylib_h/TextToUpper (memory/string text)))

(defn text-to-lower
  "Get lower case version of provided string
  [const char * text] -> const char *"
  [text]
  (raylib_h/TextToLower (memory/string text)))

(defn text-to-pascal
  "Get Pascal case notation version of provided string
  [const char * text] -> const char *"
  [text]
  (raylib_h/TextToPascal (memory/string text)))

(defn text-to-integer
  "Get integer value from text (negative values not supported)
  [const char * text] -> int"
  [text]
  (raylib_h/TextToInteger (memory/string text)))

(defn draw-line3d
  "Draw a line in 3D world space
  [Vector3 startPos, Vector3 endPos, Color color] -> void"
  [start-pos end-pos color]
  (raylib_h/DrawLine3D (rstructs/vector3 start-pos)
                       (rstructs/vector3 end-pos)
                       (rstructs/color color)))

(defn draw-point3d
  "Draw a point in 3D space, actually a small line
  [Vector3 position, Color color] -> void"
  [position color]
  (raylib_h/DrawPoint3D (rstructs/vector3 position) (rstructs/color color)))

(defn draw-circle3d
  "Draw a circle in 3D world space
  [Vector3 center, float radius, Vector3 rotationAxis, float rotationAngle, Color color] -> void"
  [center radius rotation-axis rotation-angle color]
  (raylib_h/DrawCircle3D (rstructs/vector3 center)
                         radius
                         (rstructs/vector3 rotation-axis)
                         rotation-angle
                         (rstructs/color color)))

(defn draw-triangle3d
  "Draw a color-filled triangle (vertex in counter-clockwise order!)
  [Vector3 v1, Vector3 v2, Vector3 v3, Color color] -> void"
  [v1 v2 v3 color]
  (raylib_h/DrawTriangle3D (rstructs/vector3 v1)
                           (rstructs/vector3 v2)
                           (rstructs/vector3 v3)
                           (rstructs/color color)))

(defn draw-triangle-strip3d
  "Draw a triangle strip defined by points
  [Vector3 * points, int pointCount, Color color] -> void"
  ([points point-count color]
   (raylib_h/DrawTriangleStrip3D (rstructs/vector3-array points point-count)
                                 point-count
                                 (rstructs/color color)))
  ([points color]
   (raylib_h/DrawTriangleStrip3D (rstructs/vector3-array points)
                                 (count points)
                                 (rstructs/color color))))

(defn draw-cube
  "Draw cube
  [Vector3 position, float width, float height, float length, Color color] -> void"
  [position width height length color]
  (raylib_h/DrawCube (rstructs/vector3 position)
                     width
                     height
                     length
                     (rstructs/color color)))

(defn draw-cube-v
  "Draw cube (Vector version)
  [Vector3 position, Vector3 size, Color color] -> void"
  [position size color]
  (raylib_h/DrawCubeV (rstructs/vector3 position)
                      (rstructs/vector3 size)
                      (rstructs/color color)))

(defn draw-cube-wires
  "Draw cube wires
  [Vector3 position, float width, float height, float length, Color color] -> void"
  [position width height length color]
  (raylib_h/DrawCubeWires (rstructs/vector3 position)
                          width
                          height
                          length
                          (rstructs/color color)))

(defn draw-cube-wires-v
  "Draw cube wires (Vector version)
  [Vector3 position, Vector3 size, Color color] -> void"
  [position size color]
  (raylib_h/DrawCubeWiresV (rstructs/vector3 position)
                           (rstructs/vector3 size)
                           (rstructs/color color)))

(defn draw-sphere
  "Draw sphere
  [Vector3 centerPos, float radius, Color color] -> void"
  [center-pos radius color]
  (raylib_h/DrawSphere (rstructs/vector3 center-pos)
                       radius
                       (rstructs/color color)))

(defn draw-sphere-ex
  "Draw sphere with extended parameters
  [Vector3 centerPos, float radius, int rings, int slices, Color color] -> void"
  [center-pos radius rings slices color]
  (raylib_h/DrawSphereEx (rstructs/vector3 center-pos)
                         radius
                         rings
                         slices
                         (rstructs/color color)))

(defn draw-sphere-wires
  "Draw sphere wires
  [Vector3 centerPos, float radius, int rings, int slices, Color color] -> void"
  [center-pos radius rings slices color]
  (raylib_h/DrawSphereWires (rstructs/vector3 center-pos)
                            radius
                            rings
                            slices
                            (rstructs/color color)))

(defn draw-cylinder
  "Draw a cylinder/cone
  [Vector3 position, float radiusTop, float radiusBottom, float height, int slices, Color color] -> void"
  [position radius-top radius-bottom height slices color]
  (raylib_h/DrawCylinder (rstructs/vector3 position)
                         radius-top
                         radius-bottom
                         height
                         slices
                         (rstructs/color color)))

(defn draw-cylinder-ex
  "Draw a cylinder with base at startPos and top at endPos
  [Vector3 startPos, Vector3 endPos, float startRadius, float endRadius, int sides, Color color] -> void"
  [start-pos end-pos start-radius end-radius sides color]
  (raylib_h/DrawCylinderEx (rstructs/vector3 start-pos)
                           (rstructs/vector3 end-pos)
                           start-radius
                           end-radius
                           sides
                           (rstructs/color color)))

(defn draw-cylinder-wires
  "Draw a cylinder/cone wires
  [Vector3 position, float radiusTop, float radiusBottom, float height, int slices, Color color] -> void"
  [position radius-top radius-bottom height slices color]
  (raylib_h/DrawCylinderWires (rstructs/vector3 position)
                              radius-top
                              radius-bottom
                              height
                              slices
                              (rstructs/color color)))

(defn draw-cylinder-wires-ex
  "Draw a cylinder wires with base at startPos and top at endPos
  [Vector3 startPos, Vector3 endPos, float startRadius, float endRadius, int sides, Color color] -> void"
  [start-pos end-pos start-radius end-radius sides color]
  (raylib_h/DrawCylinderWiresEx (rstructs/vector3 start-pos)
                                (rstructs/vector3 end-pos)
                                start-radius
                                end-radius
                                sides
                                (rstructs/color color)))

(defn draw-capsule
  "Draw a capsule with the center of its sphere caps at startPos and endPos
  [Vector3 startPos, Vector3 endPos, float radius, int slices, int rings, Color color] -> void"
  [start-pos end-pos radius slices rings color]
  (raylib_h/DrawCapsule (rstructs/vector3 start-pos)
                        (rstructs/vector3 end-pos)
                        radius
                        slices
                        rings
                        (rstructs/color color)))

(defn draw-capsule-wires
  "Draw capsule wireframe with the center of its sphere caps at startPos and endPos
  [Vector3 startPos, Vector3 endPos, float radius, int slices, int rings, Color color] -> void"
  [start-pos end-pos radius slices rings color]
  (raylib_h/DrawCapsuleWires (rstructs/vector3 start-pos)
                             (rstructs/vector3 end-pos)
                             radius
                             slices
                             rings
                             (rstructs/color color)))

(defn draw-plane
  "Draw a plane XZ
  [Vector3 centerPos, Vector2 size, Color color] -> void"
  [center-pos size color]
  (raylib_h/DrawPlane (rstructs/vector3 center-pos)
                      (rstructs/vector2 size)
                      (rstructs/color color)))

(defn draw-ray
  "Draw a ray line
  [Ray ray, Color color] -> void"
  [ray color]
  (raylib_h/DrawRay (rstructs/ray ray) (rstructs/color color)))

(defn draw-grid
  "Draw a grid (centered at (0, 0, 0))
  [int slices, float spacing] -> void"
  [slices spacing]
  (raylib_h/DrawGrid slices spacing))

(defn load-model
  "Load model from files (meshes and materials)
  [const char * fileName] -> Model"
  [file-name]
  (rstructs/get-model (raylib_h/LoadModel memory/*current-arena*
                                          (memory/string file-name))))

(defn load-model-from-mesh
  "Load model from generated mesh (default material)
  [Mesh mesh] -> Model"
  [mesh]
  (rstructs/get-model (raylib_h/LoadModelFromMesh memory/*current-arena*
                                                  (rstructs/mesh mesh))))

(defn model-ready?
  "Check if a model is ready
  [Model model] -> bool"
  [model]
  (raylib_h/IsModelReady (rstructs/model model)))

(defn unload-model
  "Unload model (including meshes) from memory (RAM and/or VRAM)
  [Model model] -> void"
  [model]
  (raylib_h/UnloadModel (rstructs/model model)))

(defn get-model-bounding-box
  "Compute model bounding box limits (considers all meshes)
  [Model model] -> BoundingBox"
  [model]
  (rstructs/get-bounding-box (raylib_h/GetModelBoundingBox
                               memory/*current-arena*
                               (rstructs/model model))))

(defn draw-model
  "Draw a model (with texture if set)
  [Model model, Vector3 position, float scale, Color tint] -> void"
  [model position scale tint]
  (raylib_h/DrawModel (rstructs/model model)
                      (rstructs/vector3 position)
                      scale
                      (rstructs/color tint)))

(defn draw-model-ex
  "Draw a model with extended parameters
  [Model model, Vector3 position, Vector3 rotationAxis, float rotationAngle, Vector3 scale, Color tint] -> void"
  [model position rotation-axis rotation-angle scale tint]
  (raylib_h/DrawModelEx (rstructs/model model)
                        (rstructs/vector3 position)
                        (rstructs/vector3 rotation-axis)
                        rotation-angle
                        (rstructs/vector3 scale)
                        (rstructs/color tint)))

(defn draw-model-wires
  "Draw a model wires (with texture if set)
  [Model model, Vector3 position, float scale, Color tint] -> void"
  [model position scale tint]
  (raylib_h/DrawModelWires (rstructs/model model)
                           (rstructs/vector3 position)
                           scale
                           (rstructs/color tint)))

(defn draw-model-wires-ex
  "Draw a model wires (with texture if set) with extended parameters
  [Model model, Vector3 position, Vector3 rotationAxis, float rotationAngle, Vector3 scale, Color tint] -> void"
  [model position rotation-axis rotation-angle scale tint]
  (raylib_h/DrawModelWiresEx (rstructs/model model)
                             (rstructs/vector3 position)
                             (rstructs/vector3 rotation-axis)
                             rotation-angle
                             (rstructs/vector3 scale)
                             (rstructs/color tint)))

(defn draw-bounding-box
  "Draw bounding box (wires)
  [BoundingBox box, Color color] -> void"
  [box color]
  (raylib_h/DrawBoundingBox (rstructs/bounding-box box) (rstructs/color color)))

(defn draw-billboard
  "Draw a billboard texture
  [Camera camera, Texture2D texture, Vector3 position, float size, Color tint] -> void"
  [camera texture position size tint]
  (raylib_h/DrawBillboard (rstructs/camera3d camera)
                          (rstructs/texture texture)
                          (rstructs/vector3 position)
                          size
                          (rstructs/color tint)))

(defn draw-billboard-rec
  "Draw a billboard texture defined by source
  [Camera camera, Texture2D texture, Rectangle source, Vector3 position, Vector2 size, Color tint] -> void"
  [camera texture source position size tint]
  (raylib_h/DrawBillboardRec (rstructs/camera3d camera)
                             (rstructs/texture texture)
                             (rstructs/rectangle source)
                             (rstructs/vector3 position)
                             (rstructs/vector2 size)
                             (rstructs/color tint)))

(defn draw-billboard-pro
  "Draw a billboard texture defined by source and rotation
  [Camera camera, Texture2D texture, Rectangle source, Vector3 position, Vector3 up, Vector2 size, Vector2 origin, float rotation, Color tint] -> void"
  [camera texture source position up size origin rotation tint]
  (raylib_h/DrawBillboardPro (rstructs/camera3d camera)
                             (rstructs/texture texture)
                             (rstructs/rectangle source)
                             (rstructs/vector3 position)
                             (rstructs/vector3 up)
                             (rstructs/vector2 size)
                             (rstructs/vector2 origin)
                             rotation
                             (rstructs/color tint)))

(defn upload-mesh
  "Upload mesh vertex data in GPU and provide VAO/VBO ids
  [Mesh * mesh, bool dynamic] -> void"
  [mesh dynamic]
  (raylib_h/UploadMesh (rstructs/mesh mesh) dynamic))

(defn update-mesh-buffer
  "Update mesh vertex data in GPU for a specific buffer index
  [Mesh mesh, int index, const void * data, int dataSize, int offset] -> void"
  [mesh index data data-size offset]
  (raylib_h/UpdateMeshBuffer (rstructs/mesh mesh) index data data-size offset))

(defn unload-mesh
  "Unload mesh data from CPU and GPU
  [Mesh mesh] -> void"
  [mesh]
  (raylib_h/UnloadMesh (rstructs/mesh mesh)))

(defn draw-mesh
  "Draw a 3d mesh with material and transform
  [Mesh mesh, Material material, Matrix transform] -> void"
  [mesh material transform]
  (raylib_h/DrawMesh (rstructs/mesh mesh)
                     (rstructs/material material)
                     (rstructs/matrix transform)))

(defn draw-mesh-instanced
  "Draw multiple mesh instances with material and different transforms
  [Mesh mesh, Material material, const Matrix * transforms, int instances] -> void"
  [mesh material transforms instances]
  (raylib_h/DrawMeshInstanced (rstructs/mesh mesh)
                              (rstructs/material material)
                              (rstructs/matrix transforms)
                              instances))

(defn export-mesh?
  "Export mesh data to file, returns true on success
  [Mesh mesh, const char * fileName] -> bool"
  [mesh file-name]
  (raylib_h/ExportMesh (rstructs/mesh mesh) (memory/string file-name)))

(defn get-mesh-bounding-box
  "Compute mesh bounding box limits
  [Mesh mesh] -> BoundingBox"
  [mesh]
  (rstructs/get-bounding-box
    (raylib_h/GetMeshBoundingBox memory/*current-arena* (rstructs/mesh mesh))))

(defn gen-mesh-tangents
  "Compute mesh tangents
  [Mesh * mesh] -> void"
  [mesh]
  (raylib_h/GenMeshTangents (rstructs/mesh mesh)))

(defn gen-mesh-poly
  "Generate polygonal mesh
  [int sides, float radius] -> Mesh"
  [sides radius]
  (rstructs/get-mesh
    (raylib_h/GenMeshPoly memory/*current-arena* sides radius)))

(defn gen-mesh-plane
  "Generate plane mesh (with subdivisions)
  [float width, float length, int resX, int resZ] -> Mesh"
  [width length res-x res-z]
  (rstructs/get-mesh
    (raylib_h/GenMeshPlane memory/*current-arena* width length res-x res-z)))

(defn gen-mesh-cube
  "Generate cuboid mesh
  [float width, float height, float length] -> Mesh"
  [width height length]
  (rstructs/get-mesh
    (raylib_h/GenMeshCube memory/*current-arena* width height length)))

(defn gen-mesh-sphere
  "Generate sphere mesh (standard sphere)
  [float radius, int rings, int slices] -> Mesh"
  [radius rings slices]
  (rstructs/get-mesh
    (raylib_h/GenMeshSphere memory/*current-arena* radius rings slices)))

(defn gen-mesh-hemi-sphere
  "Generate half-sphere mesh (no bottom cap)
  [float radius, int rings, int slices] -> Mesh"
  [radius rings slices]
  (rstructs/get-mesh
    (raylib_h/GenMeshHemiSphere memory/*current-arena* radius rings slices)))

(defn gen-mesh-cylinder
  "Generate cylinder mesh
  [float radius, float height, int slices] -> Mesh"
  [radius height slices]
  (rstructs/get-mesh
    (raylib_h/GenMeshCylinder memory/*current-arena* radius height slices)))

(defn gen-mesh-cone
  "Generate cone/pyramid mesh
  [float radius, float height, int slices] -> Mesh"
  [radius height slices]
  (rstructs/get-mesh
    (raylib_h/GenMeshCone memory/*current-arena* radius height slices)))

(defn gen-mesh-torus
  "Generate torus mesh
  [float radius, float size, int radSeg, int sides] -> Mesh"
  [radius size rad-seg sides]
  (rstructs/get-mesh
    (raylib_h/GenMeshTorus memory/*current-arena* radius size rad-seg sides)))

(defn gen-mesh-knot
  "Generate trefoil knot mesh
  [float radius, float size, int radSeg, int sides] -> Mesh"
  [radius size rad-seg sides]
  (rstructs/get-mesh
    (raylib_h/GenMeshKnot memory/*current-arena* radius size rad-seg sides)))

(defn gen-mesh-heightmap
  "Generate heightmap mesh from image data
  [Image heightmap, Vector3 size] -> Mesh"
  [heightmap size]
  (rstructs/get-mesh (raylib_h/GenMeshHeightmap memory/*current-arena*
                                                (rstructs/image heightmap)
                                                (rstructs/vector3 size))))

(defn gen-mesh-cubicmap
  "Generate cubes-based map mesh from image data
  [Image cubicmap, Vector3 cubeSize] -> Mesh"
  [cubicmap cube-size]
  (rstructs/get-mesh (raylib_h/GenMeshCubicmap memory/*current-arena*
                                               (rstructs/image cubicmap)
                                               (rstructs/vector3 cube-size))))

(defn load-materials
  "Load materials from model file
  [const char * fileName, int * materialCount] -> Material *"
  [file-name material-count]
  (raylib_h/LoadMaterials (memory/string file-name) material-count))

(defn load-material-default
  "Load default material (Supports: DIFFUSE, SPECULAR, NORMAL maps)
  [] -> Material"
  []
  (rstructs/get-material (raylib_h/LoadMaterialDefault memory/*current-arena*)))

(defn material-ready?
  "Check if a material is ready
  [Material material] -> bool"
  [material]
  (raylib_h/IsMaterialReady (rstructs/material material)))

(defn unload-material
  "Unload material from GPU memory (VRAM)
  [Material material] -> void"
  [material]
  (raylib_h/UnloadMaterial (rstructs/material material)))

(defn set-material-texture
  "Set texture for a material map type (MATERIAL_MAP_DIFFUSE, MATERIAL_MAP_SPECULAR...)
  [Material * material, int mapType, Texture2D texture] -> void"
  [material map-type texture]
  (raylib_h/SetMaterialTexture (rstructs/material material)
                               map-type
                               (rstructs/texture texture)))

(defn set-model-mesh-material
  "Set material for a mesh
  [Model * model, int meshId, int materialId] -> void"
  [model mesh-id material-id]
  (raylib_h/SetModelMeshMaterial (rstructs/model model) mesh-id material-id))

(defn load-model-animations
  "Load model animations from file
  [const char * fileName, int * animCount] -> ModelAnimation *"
  [file-name anim-count]
  (raylib_h/LoadModelAnimations (memory/string file-name) anim-count))

(defn update-model-animation
  "Update model animation pose
  [Model model, ModelAnimation anim, int frame] -> void"
  [model anim frame]
  (raylib_h/UpdateModelAnimation (rstructs/model model)
                                 (rstructs/model-animation anim)
                                 frame))

(defn unload-model-animation
  "Unload animation data
  [ModelAnimation anim] -> void"
  [anim]
  (raylib_h/UnloadModelAnimation (rstructs/model-animation anim)))

(defn unload-model-animations
  "Unload animation array data
  [ModelAnimation * animations, int animCount] -> void"
  [animations anim-count]
  (raylib_h/UnloadModelAnimations (rstructs/model-animation animations)
                                  anim-count))

(defn model-animation-valid?
  "Check model animation skeleton match
  [Model model, ModelAnimation anim] -> bool"
  [model anim]
  (raylib_h/IsModelAnimationValid (rstructs/model model)
                                  (rstructs/model-animation anim)))

(defn check-collision-spheres?
  "Check collision between two spheres
  [Vector3 center1, float radius1, Vector3 center2, float radius2] -> bool"
  [center1 radius1 center2 radius2]
  (raylib_h/CheckCollisionSpheres (rstructs/vector3 center1)
                                  radius1
                                  (rstructs/vector3 center2)
                                  radius2))

(defn check-collision-boxes?
  "Check collision between two bounding boxes
  [BoundingBox box1, BoundingBox box2] -> bool"
  [box1 box2]
  (raylib_h/CheckCollisionBoxes (rstructs/bounding-box box1)
                                (rstructs/bounding-box box2)))

(defn check-collision-box-sphere?
  "Check collision between box and sphere
  [BoundingBox box, Vector3 center, float radius] -> bool"
  [box center radius]
  (raylib_h/CheckCollisionBoxSphere (rstructs/bounding-box box)
                                    (rstructs/vector3 center)
                                    radius))

(defn get-ray-collision-sphere
  "Get collision info between ray and sphere
  [Ray ray, Vector3 center, float radius] -> RayCollision"
  [ray center radius]
  (rstructs/get-ray-collision (raylib_h/GetRayCollisionSphere
                                memory/*current-arena*
                                (rstructs/ray ray)
                                (rstructs/vector3 center)
                                radius)))

(defn get-ray-collision-box
  "Get collision info between ray and box
  [Ray ray, BoundingBox box] -> RayCollision"
  [ray box]
  (rstructs/get-ray-collision (raylib_h/GetRayCollisionBox
                                memory/*current-arena*
                                (rstructs/ray ray)
                                (rstructs/bounding-box box))))

(defn get-ray-collision-mesh
  "Get collision info between ray and mesh
  [Ray ray, Mesh mesh, Matrix transform] -> RayCollision"
  [ray mesh transform]
  (rstructs/get-ray-collision (raylib_h/GetRayCollisionMesh
                                memory/*current-arena*
                                (rstructs/ray ray)
                                (rstructs/mesh mesh)
                                (rstructs/matrix transform))))

(defn get-ray-collision-triangle
  "Get collision info between ray and triangle
  [Ray ray, Vector3 p1, Vector3 p2, Vector3 p3] -> RayCollision"
  [ray p1 p2 p3]
  (rstructs/get-ray-collision (raylib_h/GetRayCollisionTriangle
                                memory/*current-arena*
                                (rstructs/ray ray)
                                (rstructs/vector3 p1)
                                (rstructs/vector3 p2)
                                (rstructs/vector3 p3))))

(defn get-ray-collision-quad
  "Get collision info between ray and quad
  [Ray ray, Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4] -> RayCollision"
  [ray p1 p2 p3 p4]
  (rstructs/get-ray-collision (raylib_h/GetRayCollisionQuad
                                memory/*current-arena*
                                (rstructs/ray ray)
                                (rstructs/vector3 p1)
                                (rstructs/vector3 p2)
                                (rstructs/vector3 p3)
                                (rstructs/vector3 p4))))

(defn init-audio-device
  "Initialize audio device and context
  [] -> void"
  []
  (raylib_h/InitAudioDevice))

(defn close-audio-device
  "Close the audio device and context
  [] -> void"
  []
  (raylib_h/CloseAudioDevice))

(defn audio-device-ready?
  "Check if audio device has been initialized successfully
  [] -> bool"
  []
  (raylib_h/IsAudioDeviceReady))

(defn set-master-volume
  "Set master volume (listener)
  [float volume] -> void"
  [volume]
  (raylib_h/SetMasterVolume volume))

(defn get-master-volume
  "Get master volume (listener)
  [] -> float"
  []
  (raylib_h/GetMasterVolume))

(defn load-wave
  "Load wave data from file
  [const char * fileName] -> Wave"
  [file-name]
  (rstructs/get-wave (raylib_h/LoadWave memory/*current-arena*
                                        (memory/string file-name))))

(defn load-wave-from-memory
  "Load wave from memory buffer, fileType refers to extension: i.e. '.wav'
  [const char * fileType, const unsigned char * fileData, int dataSize] -> Wave"
  [file-type file-data data-size]
  (rstructs/get-wave (raylib_h/LoadWaveFromMemory memory/*current-arena*
                                                  (memory/string file-type)
                                                  file-data
                                                  data-size)))

(defn wave-ready?
  "Checks if wave data is ready
  [Wave wave] -> bool"
  [wave]
  (raylib_h/IsWaveReady (rstructs/wave wave)))

(defn load-sound
  "Load sound from file
  [const char * fileName] -> Sound"
  [file-name]
  (rstructs/get-sound (raylib_h/LoadSound memory/*current-arena*
                                          (memory/string file-name))))

(defn load-sound-from-wave
  "Load sound from wave data
  [Wave wave] -> Sound"
  [wave]
  (rstructs/get-sound (raylib_h/LoadSoundFromWave memory/*current-arena*
                                                  (rstructs/wave wave))))

(defn load-sound-alias
  "Create a new sound that shares the same sample data as the source sound, does not own the sound data
  [Sound source] -> Sound"
  [source]
  (rstructs/get-sound (raylib_h/LoadSoundAlias memory/*current-arena*
                                               (rstructs/sound source))))

(defn sound-ready?
  "Checks if a sound is ready
  [Sound sound] -> bool"
  [sound]
  (raylib_h/IsSoundReady (rstructs/sound sound)))

(defn update-sound
  "Update sound buffer with new data
  [Sound sound, const void * data, int sampleCount] -> void"
  [sound data sample-count]
  (raylib_h/UpdateSound (rstructs/sound sound) data sample-count))

(defn unload-wave
  "Unload wave data
  [Wave wave] -> void"
  [wave]
  (raylib_h/UnloadWave (rstructs/wave wave)))

(defn unload-sound
  "Unload sound
  [Sound sound] -> void"
  [sound]
  (raylib_h/UnloadSound (rstructs/sound sound)))

(defn unload-sound-alias
  "Unload a sound alias (does not deallocate sample data)
  [Sound alias] -> void"
  [alias]
  (raylib_h/UnloadSoundAlias (rstructs/sound alias)))

(defn export-wave?
  "Export wave data to file, returns true on success
  [Wave wave, const char * fileName] -> bool"
  [wave file-name]
  (raylib_h/ExportWave (rstructs/wave wave) (memory/string file-name)))

(defn export-wave-as-code?
  "Export wave sample data to code (.h), returns true on success
  [Wave wave, const char * fileName] -> bool"
  [wave file-name]
  (raylib_h/ExportWaveAsCode (rstructs/wave wave) (memory/string file-name)))

(defn play-sound
  "Play a sound
  [Sound sound] -> void"
  [sound]
  (raylib_h/PlaySound (rstructs/sound sound)))

(defn stop-sound
  "Stop playing a sound
  [Sound sound] -> void"
  [sound]
  (raylib_h/StopSound (rstructs/sound sound)))

(defn pause-sound
  "Pause a sound
  [Sound sound] -> void"
  [sound]
  (raylib_h/PauseSound (rstructs/sound sound)))

(defn resume-sound
  "Resume a paused sound
  [Sound sound] -> void"
  [sound]
  (raylib_h/ResumeSound (rstructs/sound sound)))

(defn sound-playing?
  "Check if a sound is currently playing
  [Sound sound] -> bool"
  [sound]
  (raylib_h/IsSoundPlaying (rstructs/sound sound)))

(defn set-sound-volume
  "Set volume for a sound (1.0 is max level)
  [Sound sound, float volume] -> void"
  [sound volume]
  (raylib_h/SetSoundVolume (rstructs/sound sound) volume))

(defn set-sound-pitch
  "Set pitch for a sound (1.0 is base level)
  [Sound sound, float pitch] -> void"
  [sound pitch]
  (raylib_h/SetSoundPitch (rstructs/sound sound) pitch))

(defn set-sound-pan
  "Set pan for a sound (0.5 is center)
  [Sound sound, float pan] -> void"
  [sound pan]
  (raylib_h/SetSoundPan (rstructs/sound sound) pan))

(defn wave-copy
  "Copy a wave to a new wave
  [Wave wave] -> Wave"
  [wave]
  (rstructs/get-wave (raylib_h/WaveCopy memory/*current-arena*
                                        (rstructs/wave wave))))

(defn wave-crop
  "Crop a wave to defined samples range
  [Wave * wave, int initSample, int finalSample] -> void"
  [wave init-sample final-sample]
  (raylib_h/WaveCrop (rstructs/wave wave) init-sample final-sample))

(defn wave-format
  "Convert wave data to desired format
  [Wave * wave, int sampleRate, int sampleSize, int channels] -> void"
  [wave sample-rate sample-size channels]
  (raylib_h/WaveFormat (rstructs/wave wave) sample-rate sample-size channels))

(defn load-wave-samples
  "Load samples data from wave as a 32bit float data array
  [Wave wave] -> float *"
  [wave]
  (raylib_h/LoadWaveSamples (rstructs/wave wave)))

(defn unload-wave-samples
  "Unload samples data loaded with LoadWaveSamples()
  [float * samples] -> void"
  [samples]
  (raylib_h/UnloadWaveSamples samples))

(defn load-music-stream
  "Load music stream from file
  [const char * fileName] -> Music"
  [file-name]
  (rstructs/get-music (raylib_h/LoadMusicStream memory/*current-arena*
                                                (memory/string file-name))))

(defn load-music-stream-from-memory
  "Load music stream from data
  [const char * fileType, const unsigned char * data, int dataSize] -> Music"
  [file-type data data-size]
  (rstructs/get-music (raylib_h/LoadMusicStreamFromMemory memory/*current-arena*
                                                          (memory/string
                                                            file-type)
                                                          data
                                                          data-size)))

(defn music-ready?
  "Checks if a music stream is ready
  [Music music] -> bool"
  [music]
  (raylib_h/IsMusicReady (rstructs/music music)))

(defn unload-music-stream
  "Unload music stream
  [Music music] -> void"
  [music]
  (raylib_h/UnloadMusicStream (rstructs/music music)))

(defn play-music-stream
  "Start music playing
  [Music music] -> void"
  [music]
  (raylib_h/PlayMusicStream (rstructs/music music)))

(defn music-stream-playing?
  "Check if music is playing
  [Music music] -> bool"
  [music]
  (raylib_h/IsMusicStreamPlaying (rstructs/music music)))

(defn update-music-stream
  "Updates buffers for music streaming
  [Music music] -> void"
  [music]
  (raylib_h/UpdateMusicStream (rstructs/music music)))

(defn stop-music-stream
  "Stop music playing
  [Music music] -> void"
  [music]
  (raylib_h/StopMusicStream (rstructs/music music)))

(defn pause-music-stream
  "Pause music playing
  [Music music] -> void"
  [music]
  (raylib_h/PauseMusicStream (rstructs/music music)))

(defn resume-music-stream
  "Resume playing paused music
  [Music music] -> void"
  [music]
  (raylib_h/ResumeMusicStream (rstructs/music music)))

(defn seek-music-stream
  "Seek music to a position (in seconds)
  [Music music, float position] -> void"
  [music position]
  (raylib_h/SeekMusicStream (rstructs/music music) position))

(defn set-music-volume
  "Set volume for music (1.0 is max level)
  [Music music, float volume] -> void"
  [music volume]
  (raylib_h/SetMusicVolume (rstructs/music music) volume))

(defn set-music-pitch
  "Set pitch for a music (1.0 is base level)
  [Music music, float pitch] -> void"
  [music pitch]
  (raylib_h/SetMusicPitch (rstructs/music music) pitch))

(defn set-music-pan
  "Set pan for a music (0.5 is center)
  [Music music, float pan] -> void"
  [music pan]
  (raylib_h/SetMusicPan (rstructs/music music) pan))

(defn get-music-time-length
  "Get music time length (in seconds)
  [Music music] -> float"
  [music]
  (raylib_h/GetMusicTimeLength (rstructs/music music)))

(defn get-music-time-played
  "Get current music time played (in seconds)
  [Music music] -> float"
  [music]
  (raylib_h/GetMusicTimePlayed (rstructs/music music)))

(defn load-audio-stream
  "Load audio stream (to stream raw audio pcm data)
  [unsigned int sampleRate, unsigned int sampleSize, unsigned int channels] -> AudioStream"
  [sample-rate sample-size channels]
  (rstructs/get-audio-stream (raylib_h/LoadAudioStream memory/*current-arena*
                                                       sample-rate
                                                       sample-size
                                                       channels)))

(defn audio-stream-ready?
  "Checks if an audio stream is ready
  [AudioStream stream] -> bool"
  [stream]
  (raylib_h/IsAudioStreamReady (rstructs/audio-stream stream)))

(defn unload-audio-stream
  "Unload audio stream and free memory
  [AudioStream stream] -> void"
  [stream]
  (raylib_h/UnloadAudioStream (rstructs/audio-stream stream)))

(defn update-audio-stream
  "Update audio stream buffers with data
  [AudioStream stream, const void * data, int frameCount] -> void"
  [stream data frame-count]
  (raylib_h/UpdateAudioStream (rstructs/audio-stream stream) data frame-count))

(defn audio-stream-processed?
  "Check if any audio stream buffers requires refill
  [AudioStream stream] -> bool"
  [stream]
  (raylib_h/IsAudioStreamProcessed (rstructs/audio-stream stream)))

(defn play-audio-stream
  "Play audio stream
  [AudioStream stream] -> void"
  [stream]
  (raylib_h/PlayAudioStream (rstructs/audio-stream stream)))

(defn pause-audio-stream
  "Pause audio stream
  [AudioStream stream] -> void"
  [stream]
  (raylib_h/PauseAudioStream (rstructs/audio-stream stream)))

(defn resume-audio-stream
  "Resume audio stream
  [AudioStream stream] -> void"
  [stream]
  (raylib_h/ResumeAudioStream (rstructs/audio-stream stream)))

(defn audio-stream-playing?
  "Check if audio stream is playing
  [AudioStream stream] -> bool"
  [stream]
  (raylib_h/IsAudioStreamPlaying (rstructs/audio-stream stream)))

(defn stop-audio-stream
  "Stop audio stream
  [AudioStream stream] -> void"
  [stream]
  (raylib_h/StopAudioStream (rstructs/audio-stream stream)))

(defn set-audio-stream-volume
  "Set volume for audio stream (1.0 is max level)
  [AudioStream stream, float volume] -> void"
  [stream volume]
  (raylib_h/SetAudioStreamVolume (rstructs/audio-stream stream) volume))

(defn set-audio-stream-pitch
  "Set pitch for audio stream (1.0 is base level)
  [AudioStream stream, float pitch] -> void"
  [stream pitch]
  (raylib_h/SetAudioStreamPitch (rstructs/audio-stream stream) pitch))

(defn set-audio-stream-pan
  "Set pan for audio stream (0.5 is centered)
  [AudioStream stream, float pan] -> void"
  [stream pan]
  (raylib_h/SetAudioStreamPan (rstructs/audio-stream stream) pan))

(defn set-audio-stream-buffer-size-default
  "Default size for new audio streams
  [int size] -> void"
  [size]
  (raylib_h/SetAudioStreamBufferSizeDefault size))

(defn set-audio-stream-callback
  "Audio thread callback to request new data
  [AudioStream stream, AudioCallback callback] -> void"
  [stream callback]
  (raylib_h/SetAudioStreamCallback (rstructs/audio-stream stream) callback))

(defn attach-audio-stream-processor
  "Attach audio stream processor to stream, receives the samples as <float>s
  [AudioStream stream, AudioCallback processor] -> void"
  [stream processor]
  (raylib_h/AttachAudioStreamProcessor (rstructs/audio-stream stream)
                                       processor))

(defn detach-audio-stream-processor
  "Detach audio stream processor from stream
  [AudioStream stream, AudioCallback processor] -> void"
  [stream processor]
  (raylib_h/DetachAudioStreamProcessor (rstructs/audio-stream stream)
                                       processor))

(defn attach-audio-mixed-processor
  "Attach audio stream processor to the entire audio pipeline, receives the samples as <float>s
  [AudioCallback processor] -> void"
  [processor]
  (raylib_h/AttachAudioMixedProcessor processor))

(defn detach-audio-mixed-processor
  "Detach audio stream processor from the entire audio pipeline
  [AudioCallback processor] -> void"
  [processor]
  (raylib_h/DetachAudioMixedProcessor processor))

