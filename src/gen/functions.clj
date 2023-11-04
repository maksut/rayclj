(ns gen.functions
  (:require [raylib.arena :as rarena]
            [gen.enums :as renums]
            [gen.structs :as rstructs])
  (:import [raylib raylib_h]
           [java.lang.foreign Arena MemorySegment]))

(set! *warn-on-reflection* true)

;;
;; Utility Functions
;;

(defn keycode [key] (if (keyword? key) (renums/keyboard-key key) key))

(defn get-string [^MemorySegment seg]
  (.getUtf8String seg 0))

(defn string
  ([str] (.allocateUtf8String rarena/*current-arena* str))
  ([^Arena arena str] (.allocateUtf8String arena str)))

(defn init-window
  "Initialize window and OpenGL context
  int width
  int height
  const char * title"
  ([^Arena arena width height title]
   (raylib_h/InitWindow width height (string arena title)))
  ([width height title] (raylib_h/InitWindow width height (string title))))

(defn window-should-close?
  "Check if KEY_ESCAPE pressed or Close icon pressed"
  []
  (raylib_h/WindowShouldClose))

(defn close-window
  "Close window and unload OpenGL context"
  []
  (raylib_h/CloseWindow))

(defn window-ready?
  "Check if window has been initialized successfully"
  []
  (raylib_h/IsWindowReady))

(defn window-fullscreen?
  "Check if window is currently fullscreen"
  []
  (raylib_h/IsWindowFullscreen))

(defn window-hidden?
  "Check if window is currently hidden (only PLATFORM_DESKTOP)"
  []
  (raylib_h/IsWindowHidden))

(defn window-minimized?
  "Check if window is currently minimized (only PLATFORM_DESKTOP)"
  []
  (raylib_h/IsWindowMinimized))

(defn window-maximized?
  "Check if window is currently maximized (only PLATFORM_DESKTOP)"
  []
  (raylib_h/IsWindowMaximized))

(defn window-focused?
  "Check if window is currently focused (only PLATFORM_DESKTOP)"
  []
  (raylib_h/IsWindowFocused))

(defn window-resized?
  "Check if window has been resized last frame"
  []
  (raylib_h/IsWindowResized))

(defn window-state?
  "Check if one specific window flag is enabled
  unsigned int flag"
  [flag]
  (raylib_h/IsWindowState flag))

(defn set-window-state
  "Set window configuration state using flags (only PLATFORM_DESKTOP)
  unsigned int flags"
  [flags]
  (raylib_h/SetWindowState flags))

(defn clear-window-state
  "Clear window configuration state flags
  unsigned int flags"
  [flags]
  (raylib_h/ClearWindowState flags))

(defn toggle-fullscreen
  "Toggle window state: fullscreen/windowed (only PLATFORM_DESKTOP)"
  []
  (raylib_h/ToggleFullscreen))

(defn maximize-window
  "Set window state: maximized, if resizable (only PLATFORM_DESKTOP)"
  []
  (raylib_h/MaximizeWindow))

(defn minimize-window
  "Set window state: minimized, if resizable (only PLATFORM_DESKTOP)"
  []
  (raylib_h/MinimizeWindow))

(defn restore-window
  "Set window state: not minimized/maximized (only PLATFORM_DESKTOP)"
  []
  (raylib_h/RestoreWindow))

(defn set-window-icon
  "Set icon for window (single image, RGBA 32bit, only PLATFORM_DESKTOP)
  Image image"
  [image]
  (raylib_h/SetWindowIcon (rstructs/image image)))

(defn set-window-icons
  "Set icon for window (multiple images, RGBA 32bit, only PLATFORM_DESKTOP)
  Image * images
  int count"
  [images count]
  (raylib_h/SetWindowIcons images count))

(defn set-window-title
  "Set title for window (only PLATFORM_DESKTOP)
  const char * title"
  ([^Arena arena title] (raylib_h/SetWindowTitle (string arena title)))
  ([title] (raylib_h/SetWindowTitle (string title))))

(defn set-window-position
  "Set window position on screen (only PLATFORM_DESKTOP)
  int x
  int y"
  [x y]
  (raylib_h/SetWindowPosition x y))

(defn set-window-monitor
  "Set monitor for the current window (fullscreen mode)
  int monitor"
  [monitor]
  (raylib_h/SetWindowMonitor monitor))

(defn set-window-min-size
  "Set window minimum dimensions (for FLAG_WINDOW_RESIZABLE)
  int width
  int height"
  [width height]
  (raylib_h/SetWindowMinSize width height))

(defn set-window-size
  "Set window dimensions
  int width
  int height"
  [width height]
  (raylib_h/SetWindowSize width height))

(defn set-window-opacity
  "Set window opacity [0.0f..1.0f] (only PLATFORM_DESKTOP)
  float opacity"
  [opacity]
  (raylib_h/SetWindowOpacity opacity))

(defn get-window-handle
  "Get native window handle"
  []
  (raylib_h/GetWindowHandle))

(defn get-screen-width "Get current screen width" [] (raylib_h/GetScreenWidth))

(defn get-screen-height
  "Get current screen height"
  []
  (raylib_h/GetScreenHeight))

(defn get-render-width
  "Get current render width (it considers HiDPI)"
  []
  (raylib_h/GetRenderWidth))

(defn get-render-height
  "Get current render height (it considers HiDPI)"
  []
  (raylib_h/GetRenderHeight))

(defn get-monitor-count
  "Get number of connected monitors"
  []
  (raylib_h/GetMonitorCount))

(defn get-current-monitor
  "Get current connected monitor"
  []
  (raylib_h/GetCurrentMonitor))

(defn get-monitor-position
  "Get specified monitor position
  int monitor"
  ([^Arena arena monitor] (raylib_h/GetMonitorPosition arena monitor))
  ([monitor]
   (rstructs/vector2 (raylib_h/GetMonitorPosition rarena/*current-arena*
                                                  monitor))))

(defn get-monitor-width
  "Get specified monitor width (current video mode used by monitor)
  int monitor"
  [monitor]
  (raylib_h/GetMonitorWidth monitor))

(defn get-monitor-height
  "Get specified monitor height (current video mode used by monitor)
  int monitor"
  [monitor]
  (raylib_h/GetMonitorHeight monitor))

(defn get-monitor-physical-width
  "Get specified monitor physical width in millimetres
  int monitor"
  [monitor]
  (raylib_h/GetMonitorPhysicalWidth monitor))

(defn get-monitor-physical-height
  "Get specified monitor physical height in millimetres
  int monitor"
  [monitor]
  (raylib_h/GetMonitorPhysicalHeight monitor))

(defn get-monitor-refresh-rate
  "Get specified monitor refresh rate
  int monitor"
  [monitor]
  (raylib_h/GetMonitorRefreshRate monitor))

(defn get-window-position
  "Get window position XY on monitor"
  ([^Arena arena] (raylib_h/GetWindowPosition arena))
  ([] (rstructs/vector2 (raylib_h/GetWindowPosition rarena/*current-arena*))))

(defn get-window-scale-dpi
  "Get window scale DPI factor"
  ([^Arena arena] (raylib_h/GetWindowScaleDPI arena))
  ([] (rstructs/vector2 (raylib_h/GetWindowScaleDPI rarena/*current-arena*))))

(defn get-monitor-name
  "Get the human-readable, UTF-8 encoded name of the primary monitor
  int monitor"
  [monitor]
  (raylib_h/GetMonitorName monitor))

(defn set-clipboard-text
  "Set clipboard text content
  const char * text"
  ([^Arena arena text] (raylib_h/SetClipboardText (string arena text)))
  ([text] (raylib_h/SetClipboardText (string text))))

(defn get-clipboard-text
  "Get clipboard text content"
  []
  (raylib_h/GetClipboardText))

(defn enable-event-waiting
  "Enable waiting for events on EndDrawing(), no automatic event polling"
  []
  (raylib_h/EnableEventWaiting))

(defn disable-event-waiting
  "Disable waiting for events on EndDrawing(), automatic events polling"
  []
  (raylib_h/DisableEventWaiting))

(defn swap-screen-buffer
  "Swap back buffer with front buffer (screen drawing)"
  []
  (raylib_h/SwapScreenBuffer))

(defn poll-input-events
  "Register all input events"
  []
  (raylib_h/PollInputEvents))

(defn wait-time
  "Wait for some time (halt program execution)
  double seconds"
  [seconds]
  (raylib_h/WaitTime seconds))

(defn show-cursor "Shows cursor" [] (raylib_h/ShowCursor))

(defn hide-cursor "Hides cursor" [] (raylib_h/HideCursor))

(defn cursor-hidden?
  "Check if cursor is not visible"
  []
  (raylib_h/IsCursorHidden))

(defn enable-cursor "Enables cursor (unlock cursor)" [] (raylib_h/EnableCursor))

(defn disable-cursor
  "Disables cursor (lock cursor)"
  []
  (raylib_h/DisableCursor))

(defn cursor-on-screen?
  "Check if cursor is on the screen"
  []
  (raylib_h/IsCursorOnScreen))

(defn clear-background
  "Set background color (framebuffer clear color)
  Color color"
  [color]
  (raylib_h/ClearBackground (rstructs/color color)))

(defn begin-drawing
  "Setup canvas (framebuffer) to start drawing"
  []
  (raylib_h/BeginDrawing))

(defn end-drawing
  "End canvas drawing and swap buffers (double buffering)"
  []
  (raylib_h/EndDrawing))

(defn begin-mode2d
  "Begin 2D mode with custom camera (2D)
  Camera2D camera"
  [camera]
  (raylib_h/BeginMode2D (rstructs/camera2d camera)))

(defn end-mode2d "Ends 2D mode with custom camera" [] (raylib_h/EndMode2D))

(defn begin-mode3d
  "Begin 3D mode with custom camera (3D)
  Camera3D camera"
  [camera]
  (raylib_h/BeginMode3D (rstructs/camera3d camera)))

(defn end-mode3d
  "Ends 3D mode and returns to default 2D orthographic mode"
  []
  (raylib_h/EndMode3D))

(defn begin-texture-mode
  "Begin drawing to render texture
  RenderTexture2D target"
  [target]
  (raylib_h/BeginTextureMode (rstructs/render-texture target)))

(defn end-texture-mode
  "Ends drawing to render texture"
  []
  (raylib_h/EndTextureMode))

(defn begin-shader-mode
  "Begin custom shader drawing
  Shader shader"
  [shader]
  (raylib_h/BeginShaderMode (rstructs/shader shader)))

(defn end-shader-mode
  "End custom shader drawing (use default shader)"
  []
  (raylib_h/EndShaderMode))

(defn begin-blend-mode
  "Begin blending mode (alpha, additive, multiplied, subtract, custom)
  int mode"
  [mode]
  (raylib_h/BeginBlendMode mode))

(defn end-blend-mode
  "End blending mode (reset to default: alpha blending)"
  []
  (raylib_h/EndBlendMode))

(defn begin-scissor-mode
  "Begin scissor mode (define screen area for following drawing)
  int x
  int y
  int width
  int height"
  [x y width height]
  (raylib_h/BeginScissorMode x y width height))

(defn end-scissor-mode "End scissor mode" [] (raylib_h/EndScissorMode))

(defn begin-vr-stereo-mode
  "Begin stereo rendering (requires VR simulator)
  VrStereoConfig config"
  [config]
  (raylib_h/BeginVrStereoMode config))

(defn end-vr-stereo-mode
  "End stereo rendering (requires VR simulator)"
  []
  (raylib_h/EndVrStereoMode))

(defn unload-vr-stereo-config
  "Unload VR stereo config
  VrStereoConfig config"
  [config]
  (raylib_h/UnloadVrStereoConfig config))

(defn load-shader
  "Load shader from files and bind default locations
  const char * vsFileName
  const char * fsFileName"
  ([^Arena arena vs-file-name fs-file-name]
   (raylib_h/LoadShader arena
                        (string arena vs-file-name)
                        (string arena fs-file-name)))
  ([vs-file-name fs-file-name]
   (rstructs/shader (raylib_h/LoadShader rarena/*current-arena*
                                         (string vs-file-name)
                                         (string fs-file-name)))))

(defn load-shader-from-memory
  "Load shader from code strings and bind default locations
  const char * vsCode
  const char * fsCode"
  ([^Arena arena vs-code fs-code]
   (raylib_h/LoadShaderFromMemory arena
                                  (string arena vs-code)
                                  (string arena fs-code)))
  ([vs-code fs-code]
   (rstructs/shader (raylib_h/LoadShaderFromMemory rarena/*current-arena*
                                                   (string vs-code)
                                                   (string fs-code)))))

(defn shader-ready?
  "Check if a shader is ready
  Shader shader"
  [shader]
  (raylib_h/IsShaderReady (rstructs/shader shader)))

(defn get-shader-location
  "Get shader uniform location
  Shader shader
  const char * uniformName"
  ([^Arena arena shader uniform-name]
   (raylib_h/GetShaderLocation (rstructs/shader arena shader)
                               (string arena uniform-name)))
  ([shader uniform-name]
   (raylib_h/GetShaderLocation (rstructs/shader shader) (string uniform-name))))

(defn get-shader-location-attrib
  "Get shader attribute location
  Shader shader
  const char * attribName"
  ([^Arena arena shader attrib-name]
   (raylib_h/GetShaderLocationAttrib (rstructs/shader arena shader)
                                     (string arena attrib-name)))
  ([shader attrib-name]
   (raylib_h/GetShaderLocationAttrib (rstructs/shader shader)
                                     (string attrib-name))))

(defn set-shader-value
  "Set shader uniform value
  Shader shader
  int locIndex
  const void * value
  int uniformType"
  [shader loc-index value uniform-type]
  (raylib_h/SetShaderValue (rstructs/shader shader)
                           loc-index
                           value
                           uniform-type))

(defn set-shader-value-v
  "Set shader uniform value vector
  Shader shader
  int locIndex
  const void * value
  int uniformType
  int count"
  [shader loc-index value uniform-type count]
  (raylib_h/SetShaderValueV (rstructs/shader shader)
                            loc-index
                            value
                            uniform-type
                            count))

(defn set-shader-value-matrix
  "Set shader uniform value (matrix 4x4)
  Shader shader
  int locIndex
  Matrix mat"
  [shader loc-index mat]
  (raylib_h/SetShaderValueMatrix (rstructs/shader shader)
                                 loc-index
                                 (rstructs/matrix mat)))

(defn set-shader-value-texture
  "Set shader uniform value for texture (sampler2d)
  Shader shader
  int locIndex
  Texture2D texture"
  [shader loc-index texture]
  (raylib_h/SetShaderValueTexture (rstructs/shader shader)
                                  loc-index
                                  (rstructs/texture texture)))

(defn unload-shader
  "Unload shader from GPU memory (VRAM)
  Shader shader"
  [shader]
  (raylib_h/UnloadShader (rstructs/shader shader)))

(defn get-mouse-ray
  "Get a ray trace from mouse position
  Vector2 mousePosition
  Camera camera"
  ([^Arena arena mouse-position camera]
   (raylib_h/GetMouseRay arena
                         (rstructs/vector2 arena mouse-position)
                         (rstructs/camera3d arena camera)))
  ([mouse-position camera]
   (rstructs/ray (raylib_h/GetMouseRay rarena/*current-arena*
                                       (rstructs/vector2 mouse-position)
                                       (rstructs/camera3d camera)))))

(defn get-camera-matrix
  "Get camera transform matrix (view matrix)
  Camera camera"
  ([^Arena arena camera]
   (raylib_h/GetCameraMatrix arena (rstructs/camera3d arena camera)))
  ([camera]
   (rstructs/matrix (raylib_h/GetCameraMatrix rarena/*current-arena*
                                              (rstructs/camera3d camera)))))

(defn get-camera-matrix2d
  "Get camera 2d transform matrix
  Camera2D camera"
  ([^Arena arena camera]
   (raylib_h/GetCameraMatrix2D arena (rstructs/camera2d arena camera)))
  ([camera]
   (rstructs/matrix (raylib_h/GetCameraMatrix2D rarena/*current-arena*
                                                (rstructs/camera2d camera)))))

(defn get-world-to-screen
  "Get the screen space position for a 3d world space position
  Vector3 position
  Camera camera"
  ([^Arena arena position camera]
   (raylib_h/GetWorldToScreen arena
                              (rstructs/vector3 arena position)
                              (rstructs/camera3d arena camera)))
  ([position camera]
   (rstructs/vector2 (raylib_h/GetWorldToScreen rarena/*current-arena*
                                                (rstructs/vector3 position)
                                                (rstructs/camera3d camera)))))

(defn get-screen-to-world2d
  "Get the world space position for a 2d camera screen space position
  Vector2 position
  Camera2D camera"
  ([^Arena arena position camera]
   (raylib_h/GetScreenToWorld2D arena
                                (rstructs/vector2 arena position)
                                (rstructs/camera2d arena camera)))
  ([position camera]
   (rstructs/vector2 (raylib_h/GetScreenToWorld2D rarena/*current-arena*
                                                  (rstructs/vector2 position)
                                                  (rstructs/camera2d camera)))))

(defn get-world-to-screen-ex
  "Get size position for a 3d world space position
  Vector3 position
  Camera camera
  int width
  int height"
  ([^Arena arena position camera width height]
   (raylib_h/GetWorldToScreenEx arena
                                (rstructs/vector3 arena position)
                                (rstructs/camera3d arena camera)
                                width
                                height))
  ([position camera width height]
   (rstructs/vector2 (raylib_h/GetWorldToScreenEx rarena/*current-arena*
                                                  (rstructs/vector3 position)
                                                  (rstructs/camera3d camera)
                                                  width
                                                  height))))

(defn get-world-to-screen2d
  "Get the screen space position for a 2d camera world space position
  Vector2 position
  Camera2D camera"
  ([^Arena arena position camera]
   (raylib_h/GetWorldToScreen2D arena
                                (rstructs/vector2 arena position)
                                (rstructs/camera2d arena camera)))
  ([position camera]
   (rstructs/vector2 (raylib_h/GetWorldToScreen2D rarena/*current-arena*
                                                  (rstructs/vector2 position)
                                                  (rstructs/camera2d camera)))))

(defn set-target-fps
  "Set target FPS (maximum)
  int fps"
  [fps]
  (raylib_h/SetTargetFPS fps))

(defn get-fps "Get current FPS" [] (raylib_h/GetFPS))

(defn get-frame-time
  "Get time in seconds for last frame drawn (delta time)"
  []
  (raylib_h/GetFrameTime))

(defn get-time
  "Get elapsed time in seconds since InitWindow()"
  []
  (raylib_h/GetTime))

(defn get-random-value
  "Get a random value between min and max (both included)
  int min
  int max"
  [min max]
  (raylib_h/GetRandomValue min max))

(defn set-random-seed
  "Set the seed for the random number generator
  unsigned int seed"
  [seed]
  (raylib_h/SetRandomSeed seed))

(defn take-screenshot
  "Takes a screenshot of current screen (filename extension defines format)
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/TakeScreenshot (string arena file-name)))
  ([file-name] (raylib_h/TakeScreenshot (string file-name))))

(defn set-config-flags
  "Setup init configuration flags (view FLAGS)
  unsigned int flags"
  [flags]
  (raylib_h/SetConfigFlags flags))

(defn trace-log
  "Show trace log messages (LOG_DEBUG, LOG_INFO, LOG_WARNING, LOG_ERROR...)
  int logLevel
  const char * text
  ... args"
  ([^Arena arena log-level text args]
   (raylib_h/TraceLog log-level (string arena text) args))
  ([log-level text args] (raylib_h/TraceLog log-level (string text) args)))

(defn set-trace-log-level
  "Set the current threshold (minimum) log level
  int logLevel"
  [log-level]
  (raylib_h/SetTraceLogLevel log-level))

(defn mem-alloc
  "Internal memory allocator
  unsigned int size"
  [size]
  (raylib_h/MemAlloc size))

(defn mem-realloc
  "Internal memory reallocator
  void * ptr
  unsigned int size"
  [ptr size]
  (raylib_h/MemRealloc ptr size))

(defn mem-free
  "Internal memory free
  void * ptr"
  [ptr]
  (raylib_h/MemFree ptr))

(defn open-url
  "Open URL with default system browser (if available)
  const char * url"
  ([^Arena arena url] (raylib_h/OpenURL (string arena url)))
  ([url] (raylib_h/OpenURL (string url))))

(defn set-trace-log-callback
  "Set custom trace log
  TraceLogCallback callback"
  [callback]
  (raylib_h/SetTraceLogCallback callback))

(defn set-load-file-data-callback
  "Set custom file binary data loader
  LoadFileDataCallback callback"
  [callback]
  (raylib_h/SetLoadFileDataCallback callback))

(defn set-save-file-data-callback
  "Set custom file binary data saver
  SaveFileDataCallback callback"
  [callback]
  (raylib_h/SetSaveFileDataCallback callback))

(defn set-load-file-text-callback
  "Set custom file text data loader
  LoadFileTextCallback callback"
  [callback]
  (raylib_h/SetLoadFileTextCallback callback))

(defn set-save-file-text-callback
  "Set custom file text data saver
  SaveFileTextCallback callback"
  [callback]
  (raylib_h/SetSaveFileTextCallback callback))

(defn load-file-data
  "Load file data as byte array (read)
  const char * fileName
  unsigned int * bytesRead"
  ([^Arena arena file-name bytes-read]
   (raylib_h/LoadFileData (string arena file-name) bytes-read))
  ([file-name bytes-read]
   (raylib_h/LoadFileData (string file-name) bytes-read)))

(defn unload-file-data
  "Unload file data allocated by LoadFileData()
  unsigned char * data"
  [data]
  (raylib_h/UnloadFileData data))

(defn save-file-data?
  "Save data to file from byte array (write), returns true on success
  const char * fileName
  void * data
  unsigned int bytesToWrite"
  ([^Arena arena file-name data bytes-to-write]
   (raylib_h/SaveFileData (string arena file-name) data bytes-to-write))
  ([file-name data bytes-to-write]
   (raylib_h/SaveFileData (string file-name) data bytes-to-write)))

(defn export-data-as-code?
  "Export data to code (.h), returns true on success
  const unsigned char * data
  unsigned int size
  const char * fileName"
  ([^Arena arena data size file-name]
   (raylib_h/ExportDataAsCode data size (string arena file-name)))
  ([data size file-name]
   (raylib_h/ExportDataAsCode data size (string file-name))))

(defn load-file-text
  "Load text data from file (read), returns a '\\0' terminated string
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/LoadFileText (string arena file-name)))
  ([file-name] (raylib_h/LoadFileText (string file-name))))

(defn unload-file-text
  "Unload file text data allocated by LoadFileText()
  char * text"
  [text]
  (raylib_h/UnloadFileText text))

(defn save-file-text?
  "Save text data to file (write), string must be '\\0' terminated, returns true on success
  const char * fileName
  char * text"
  ([^Arena arena file-name text]
   (raylib_h/SaveFileText (string arena file-name) text))
  ([file-name text] (raylib_h/SaveFileText (string file-name) text)))

(defn file-exists?
  "Check if file exists
  const char * fileName"
  ([^Arena arena file-name] (raylib_h/FileExists (string arena file-name)))
  ([file-name] (raylib_h/FileExists (string file-name))))

(defn directory-exists?
  "Check if a directory path exists
  const char * dirPath"
  ([^Arena arena dir-path]
   (raylib_h/DirectoryExists (string arena dir-path)))
  ([dir-path] (raylib_h/DirectoryExists (string dir-path))))

(defn file-extension?
  "Check file extension (including point: .png, .wav)
  const char * fileName
  const char * ext"
  ([^Arena arena file-name ext]
   (raylib_h/IsFileExtension (string arena file-name) (string arena ext)))
  ([file-name ext] (raylib_h/IsFileExtension (string file-name) (string ext))))

(defn get-file-length
  "Get file length in bytes (NOTE: GetFileSize() conflicts with windows.h)
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/GetFileLength (string arena file-name)))
  ([file-name] (raylib_h/GetFileLength (string file-name))))

(defn get-file-extension
  "Get pointer to extension for a filename string (includes dot: '.png')
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/GetFileExtension (string arena file-name)))
  ([file-name] (raylib_h/GetFileExtension (string file-name))))

(defn get-file-name
  "Get pointer to filename for a path string
  const char * filePath"
  ([^Arena arena file-path]
   (raylib_h/GetFileName (string arena file-path)))
  ([file-path] (raylib_h/GetFileName (string file-path))))

(defn get-file-name-without-ext
  "Get filename string without extension (uses static string)
  const char * filePath"
  ([^Arena arena file-path]
   (raylib_h/GetFileNameWithoutExt (string arena file-path)))
  ([file-path] (raylib_h/GetFileNameWithoutExt (string file-path))))

(defn get-directory-path
  "Get full path for a given fileName with path (uses static string)
  const char * filePath"
  ([^Arena arena file-path]
   (raylib_h/GetDirectoryPath (string arena file-path)))
  ([file-path] (raylib_h/GetDirectoryPath (string file-path))))

(defn get-prev-directory-path
  "Get previous directory path for a given path (uses static string)
  const char * dirPath"
  ([^Arena arena dir-path]
   (raylib_h/GetPrevDirectoryPath (string arena dir-path)))
  ([dir-path] (raylib_h/GetPrevDirectoryPath (string dir-path))))

(defn get-working-directory
  "Get current working directory (uses static string)"
  []
  (raylib_h/GetWorkingDirectory))

(defn get-application-directory
  "Get the directory if the running application (uses static string)"
  []
  (raylib_h/GetApplicationDirectory))

(defn change-directory?
  "Change working directory, return true on success
  const char * dir"
  ([^Arena arena dir] (raylib_h/ChangeDirectory (string arena dir)))
  ([dir] (raylib_h/ChangeDirectory (string dir))))

(defn path-file?
  "Check if a given path is a file or a directory
  const char * path"
  ([^Arena arena path] (raylib_h/IsPathFile (string arena path)))
  ([path] (raylib_h/IsPathFile (string path))))

(defn load-directory-files
  "Load directory filepaths
  const char * dirPath"
  ([^Arena arena dir-path]
   (raylib_h/LoadDirectoryFiles arena (string arena dir-path)))
  ([dir-path]
   (rstructs/file-path-list (raylib_h/LoadDirectoryFiles rarena/*current-arena*
                                                         (string dir-path)))))

(defn load-directory-files-ex
  "Load directory filepaths with extension filtering and recursive directory scan
  const char * basePath
  const char * filter
  bool scanSubdirs"
  ([^Arena arena base-path filter scan-subdirs]
   (raylib_h/LoadDirectoryFilesEx arena
                                  (string arena base-path)
                                  (string arena filter)
                                  scan-subdirs))
  ([base-path filter scan-subdirs]
   (rstructs/file-path-list (raylib_h/LoadDirectoryFilesEx
                              rarena/*current-arena*
                              (string base-path)
                              (string filter)
                              scan-subdirs))))

(defn unload-directory-files
  "Unload filepaths
  FilePathList files"
  [files]
  (raylib_h/UnloadDirectoryFiles (rstructs/file-path-list files)))

(defn file-dropped?
  "Check if a file has been dropped into window"
  []
  (raylib_h/IsFileDropped))

(defn load-dropped-files
  "Load dropped filepaths"
  ([^Arena arena] (raylib_h/LoadDroppedFiles arena))
  ([]
   (rstructs/file-path-list (raylib_h/LoadDroppedFiles
                              rarena/*current-arena*))))

(defn unload-dropped-files
  "Unload dropped filepaths
  FilePathList files"
  [files]
  (raylib_h/UnloadDroppedFiles (rstructs/file-path-list files)))

(defn get-file-mod-time
  "Get file modification time (last write time)
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/GetFileModTime (string arena file-name)))
  ([file-name] (raylib_h/GetFileModTime (string file-name))))

(defn compress-data
  "Compress data (DEFLATE algorithm), memory must be MemFree()
  const unsigned char * data
  int dataSize
  int * compDataSize"
  [data data-size comp-data-size]
  (raylib_h/CompressData data data-size comp-data-size))

(defn decompress-data
  "Decompress data (DEFLATE algorithm), memory must be MemFree()
  const unsigned char * compData
  int compDataSize
  int * dataSize"
  [comp-data comp-data-size data-size]
  (raylib_h/DecompressData comp-data comp-data-size data-size))

(defn encode-data-base64
  "Encode data to Base64 string, memory must be MemFree()
  const unsigned char * data
  int dataSize
  int * outputSize"
  [data data-size output-size]
  (raylib_h/EncodeDataBase64 data data-size output-size))

(defn decode-data-base64
  "Decode Base64 string data, memory must be MemFree()
  const unsigned char * data
  int * outputSize"
  [data output-size]
  (raylib_h/DecodeDataBase64 data output-size))

(defn key-pressed?
  "Check if a key has been pressed once
  int key"
  [key]
  (raylib_h/IsKeyPressed key))

(defn key-down?
  "Check if a key is being pressed
  int key"
  [key]
  (raylib_h/IsKeyDown key))

(defn key-released?
  "Check if a key has been released once
  int key"
  [key]
  (raylib_h/IsKeyReleased key))

(defn key-up?
  "Check if a key is NOT being pressed
  int key"
  [key]
  (raylib_h/IsKeyUp key))

(defn set-exit-key
  "Set a custom key to exit program (default is ESC)
  int key"
  [key]
  (raylib_h/SetExitKey key))

(defn get-key-pressed
  "Get key pressed (keycode), call it multiple times for keys queued, returns 0 when the queue is empty"
  []
  (raylib_h/GetKeyPressed))

(defn get-char-pressed
  "Get char pressed (unicode), call it multiple times for chars queued, returns 0 when the queue is empty"
  []
  (raylib_h/GetCharPressed))

(defn gamepad-available?
  "Check if a gamepad is available
  int gamepad"
  [gamepad]
  (raylib_h/IsGamepadAvailable gamepad))

(defn get-gamepad-name
  "Get gamepad internal name id
  int gamepad"
  [gamepad]
  (raylib_h/GetGamepadName gamepad))

(defn gamepad-button-pressed?
  "Check if a gamepad button has been pressed once
  int gamepad
  int button"
  [gamepad button]
  (raylib_h/IsGamepadButtonPressed gamepad button))

(defn gamepad-button-down?
  "Check if a gamepad button is being pressed
  int gamepad
  int button"
  [gamepad button]
  (raylib_h/IsGamepadButtonDown gamepad button))

(defn gamepad-button-released?
  "Check if a gamepad button has been released once
  int gamepad
  int button"
  [gamepad button]
  (raylib_h/IsGamepadButtonReleased gamepad button))

(defn gamepad-button-up?
  "Check if a gamepad button is NOT being pressed
  int gamepad
  int button"
  [gamepad button]
  (raylib_h/IsGamepadButtonUp gamepad button))

(defn get-gamepad-button-pressed
  "Get the last gamepad button pressed"
  []
  (raylib_h/GetGamepadButtonPressed))

(defn get-gamepad-axis-count
  "Get gamepad axis count for a gamepad
  int gamepad"
  [gamepad]
  (raylib_h/GetGamepadAxisCount gamepad))

(defn get-gamepad-axis-movement
  "Get axis movement value for a gamepad axis
  int gamepad
  int axis"
  [gamepad axis]
  (raylib_h/GetGamepadAxisMovement gamepad axis))

(defn set-gamepad-mappings
  "Set internal gamepad mappings (SDL_GameControllerDB)
  const char * mappings"
  ([^Arena arena mappings]
   (raylib_h/SetGamepadMappings (string arena mappings)))
  ([mappings] (raylib_h/SetGamepadMappings (string mappings))))

(defn mouse-button-pressed?
  "Check if a mouse button has been pressed once
  int button"
  [button]
  (raylib_h/IsMouseButtonPressed button))

(defn mouse-button-down?
  "Check if a mouse button is being pressed
  int button"
  [button]
  (raylib_h/IsMouseButtonDown button))

(defn mouse-button-released?
  "Check if a mouse button has been released once
  int button"
  [button]
  (raylib_h/IsMouseButtonReleased button))

(defn mouse-button-up?
  "Check if a mouse button is NOT being pressed
  int button"
  [button]
  (raylib_h/IsMouseButtonUp button))

(defn get-mouse-x "Get mouse position X" [] (raylib_h/GetMouseX))

(defn get-mouse-y "Get mouse position Y" [] (raylib_h/GetMouseY))

(defn get-mouse-position
  "Get mouse position XY"
  ([^Arena arena] (raylib_h/GetMousePosition arena))
  ([] (rstructs/vector2 (raylib_h/GetMousePosition rarena/*current-arena*))))

(defn get-mouse-delta
  "Get mouse delta between frames"
  ([^Arena arena] (raylib_h/GetMouseDelta arena))
  ([] (rstructs/vector2 (raylib_h/GetMouseDelta rarena/*current-arena*))))

(defn set-mouse-position
  "Set mouse position XY
  int x
  int y"
  [x y]
  (raylib_h/SetMousePosition x y))

(defn set-mouse-offset
  "Set mouse offset
  int offsetX
  int offsetY"
  [offset-x offset-y]
  (raylib_h/SetMouseOffset offset-x offset-y))

(defn set-mouse-scale
  "Set mouse scaling
  float scaleX
  float scaleY"
  [scale-x scale-y]
  (raylib_h/SetMouseScale scale-x scale-y))

(defn get-mouse-wheel-move
  "Get mouse wheel movement for X or Y, whichever is larger"
  []
  (raylib_h/GetMouseWheelMove))

(defn get-mouse-wheel-move-v
  "Get mouse wheel movement for both X and Y"
  ([^Arena arena] (raylib_h/GetMouseWheelMoveV arena))
  ([] (rstructs/vector2 (raylib_h/GetMouseWheelMoveV rarena/*current-arena*))))

(defn set-mouse-cursor
  "Set mouse cursor
  int cursor"
  [cursor]
  (raylib_h/SetMouseCursor cursor))

(defn get-touch-x
  "Get touch position X for touch point 0 (relative to screen size)"
  []
  (raylib_h/GetTouchX))

(defn get-touch-y
  "Get touch position Y for touch point 0 (relative to screen size)"
  []
  (raylib_h/GetTouchY))

(defn get-touch-position
  "Get touch position XY for a touch point index (relative to screen size)
  int index"
  ([^Arena arena index] (raylib_h/GetTouchPosition arena index))
  ([index]
   (rstructs/vector2 (raylib_h/GetTouchPosition rarena/*current-arena* index))))

(defn get-touch-point-id
  "Get touch point identifier for given index
  int index"
  [index]
  (raylib_h/GetTouchPointId index))

(defn get-touch-point-count
  "Get number of touch points"
  []
  (raylib_h/GetTouchPointCount))

(defn set-gestures-enabled
  "Enable a set of gestures using flags
  unsigned int flags"
  [flags]
  (raylib_h/SetGesturesEnabled flags))

(defn gesture-detected?
  "Check if a gesture have been detected
  int gesture"
  [gesture]
  (raylib_h/IsGestureDetected gesture))

(defn get-gesture-detected
  "Get latest detected gesture"
  []
  (raylib_h/GetGestureDetected))

(defn get-gesture-hold-duration
  "Get gesture hold time in milliseconds"
  []
  (raylib_h/GetGestureHoldDuration))

(defn get-gesture-drag-vector
  "Get gesture drag vector"
  ([^Arena arena] (raylib_h/GetGestureDragVector arena))
  ([]
   (rstructs/vector2 (raylib_h/GetGestureDragVector rarena/*current-arena*))))

(defn get-gesture-drag-angle
  "Get gesture drag angle"
  []
  (raylib_h/GetGestureDragAngle))

(defn get-gesture-pinch-vector
  "Get gesture pinch delta"
  ([^Arena arena] (raylib_h/GetGesturePinchVector arena))
  ([]
   (rstructs/vector2 (raylib_h/GetGesturePinchVector rarena/*current-arena*))))

(defn get-gesture-pinch-angle
  "Get gesture pinch angle"
  []
  (raylib_h/GetGesturePinchAngle))

(defn update-camera
  "Update camera position for selected mode
  Camera * camera
  int mode"
  [camera mode]
  (raylib_h/UpdateCamera camera mode))

(defn update-camera-pro
  "Update camera movement/rotation
  Camera * camera
  Vector3 movement
  Vector3 rotation
  float zoom"
  [camera movement rotation zoom]
  (raylib_h/UpdateCameraPro camera
                            (rstructs/vector3 movement)
                            (rstructs/vector3 rotation)
                            zoom))

(defn set-shapes-texture
  "Set texture and rectangle to be used on shapes drawing
  Texture2D texture
  Rectangle source"
  [texture source]
  (raylib_h/SetShapesTexture (rstructs/texture texture)
                             (rstructs/rectangle source)))

(defn draw-pixel
  "Draw a pixel
  int posX
  int posY
  Color color"
  [pos-x pos-y color]
  (raylib_h/DrawPixel pos-x pos-y (rstructs/color color)))

(defn draw-pixel-v
  "Draw a pixel (Vector version)
  Vector2 position
  Color color"
  [position color]
  (raylib_h/DrawPixelV (rstructs/vector2 position) (rstructs/color color)))

(defn draw-line
  "Draw a line
  int startPosX
  int startPosY
  int endPosX
  int endPosY
  Color color"
  [start-pos-x start-pos-y end-pos-x end-pos-y color]
  (raylib_h/DrawLine start-pos-x
                     start-pos-y
                     end-pos-x
                     end-pos-y
                     (rstructs/color color)))

(defn draw-line-v
  "Draw a line (Vector version)
  Vector2 startPos
  Vector2 endPos
  Color color"
  [start-pos end-pos color]
  (raylib_h/DrawLineV (rstructs/vector2 start-pos)
                      (rstructs/vector2 end-pos)
                      (rstructs/color color)))

(defn draw-line-ex
  "Draw a line defining thickness
  Vector2 startPos
  Vector2 endPos
  float thick
  Color color"
  [start-pos end-pos thick color]
  (raylib_h/DrawLineEx (rstructs/vector2 start-pos)
                       (rstructs/vector2 end-pos)
                       thick
                       (rstructs/color color)))

(defn draw-line-bezier
  "Draw a line using cubic-bezier curves in-out
  Vector2 startPos
  Vector2 endPos
  float thick
  Color color"
  [start-pos end-pos thick color]
  (raylib_h/DrawLineBezier (rstructs/vector2 start-pos)
                           (rstructs/vector2 end-pos)
                           thick
                           (rstructs/color color)))

(defn draw-line-bezier-quad
  "Draw line using quadratic bezier curves with a control point
  Vector2 startPos
  Vector2 endPos
  Vector2 controlPos
  float thick
  Color color"
  [start-pos end-pos control-pos thick color]
  (raylib_h/DrawLineBezierQuad (rstructs/vector2 start-pos)
                               (rstructs/vector2 end-pos)
                               (rstructs/vector2 control-pos)
                               thick
                               (rstructs/color color)))

(defn draw-line-bezier-cubic
  "Draw line using cubic bezier curves with 2 control points
  Vector2 startPos
  Vector2 endPos
  Vector2 startControlPos
  Vector2 endControlPos
  float thick
  Color color"
  [start-pos end-pos start-control-pos end-control-pos thick color]
  (raylib_h/DrawLineBezierCubic (rstructs/vector2 start-pos)
                                (rstructs/vector2 end-pos)
                                (rstructs/vector2 start-control-pos)
                                (rstructs/vector2 end-control-pos)
                                thick
                                (rstructs/color color)))

(defn draw-line-strip
  "Draw lines sequence
  Vector2 * points
  int pointCount
  Color color"
  [points point-count color]
  (raylib_h/DrawLineStrip points point-count (rstructs/color color)))

(defn draw-circle
  "Draw a color-filled circle
  int centerX
  int centerY
  float radius
  Color color"
  [center-x center-y radius color]
  (raylib_h/DrawCircle center-x center-y radius (rstructs/color color)))

(defn draw-circle-sector
  "Draw a piece of a circle
  Vector2 center
  float radius
  float startAngle
  float endAngle
  int segments
  Color color"
  [center radius start-angle end-angle segments color]
  (raylib_h/DrawCircleSector (rstructs/vector2 center)
                             radius
                             start-angle
                             end-angle
                             segments
                             (rstructs/color color)))

(defn draw-circle-sector-lines
  "Draw circle sector outline
  Vector2 center
  float radius
  float startAngle
  float endAngle
  int segments
  Color color"
  [center radius start-angle end-angle segments color]
  (raylib_h/DrawCircleSectorLines (rstructs/vector2 center)
                                  radius
                                  start-angle
                                  end-angle
                                  segments
                                  (rstructs/color color)))

(defn draw-circle-gradient
  "Draw a gradient-filled circle
  int centerX
  int centerY
  float radius
  Color color1
  Color color2"
  [center-x center-y radius color1 color2]
  (raylib_h/DrawCircleGradient center-x
                               center-y
                               radius
                               (rstructs/color color1)
                               (rstructs/color color2)))

(defn draw-circle-v
  "Draw a color-filled circle (Vector version)
  Vector2 center
  float radius
  Color color"
  [center radius color]
  (raylib_h/DrawCircleV (rstructs/vector2 center)
                        radius
                        (rstructs/color color)))

(defn draw-circle-lines
  "Draw circle outline
  int centerX
  int centerY
  float radius
  Color color"
  [center-x center-y radius color]
  (raylib_h/DrawCircleLines center-x center-y radius (rstructs/color color)))

(defn draw-ellipse
  "Draw ellipse
  int centerX
  int centerY
  float radiusH
  float radiusV
  Color color"
  [center-x center-y radius-h radius-v color]
  (raylib_h/DrawEllipse center-x
                        center-y
                        radius-h
                        radius-v
                        (rstructs/color color)))

(defn draw-ellipse-lines
  "Draw ellipse outline
  int centerX
  int centerY
  float radiusH
  float radiusV
  Color color"
  [center-x center-y radius-h radius-v color]
  (raylib_h/DrawEllipseLines center-x
                             center-y
                             radius-h
                             radius-v
                             (rstructs/color color)))

(defn draw-ring
  "Draw ring
  Vector2 center
  float innerRadius
  float outerRadius
  float startAngle
  float endAngle
  int segments
  Color color"
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
  Vector2 center
  float innerRadius
  float outerRadius
  float startAngle
  float endAngle
  int segments
  Color color"
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
  int posX
  int posY
  int width
  int height
  Color color"
  [pos-x pos-y width height color]
  (raylib_h/DrawRectangle pos-x pos-y width height (rstructs/color color)))

(defn draw-rectangle-v
  "Draw a color-filled rectangle (Vector version)
  Vector2 position
  Vector2 size
  Color color"
  [position size color]
  (raylib_h/DrawRectangleV (rstructs/vector2 position)
                           (rstructs/vector2 size)
                           (rstructs/color color)))

(defn draw-rectangle-rec
  "Draw a color-filled rectangle
  Rectangle rec
  Color color"
  [rec color]
  (raylib_h/DrawRectangleRec (rstructs/rectangle rec) (rstructs/color color)))

(defn draw-rectangle-pro
  "Draw a color-filled rectangle with pro parameters
  Rectangle rec
  Vector2 origin
  float rotation
  Color color"
  [rec origin rotation color]
  (raylib_h/DrawRectanglePro (rstructs/rectangle rec)
                             (rstructs/vector2 origin)
                             rotation
                             (rstructs/color color)))

(defn draw-rectangle-gradient-v
  "Draw a vertical-gradient-filled rectangle
  int posX
  int posY
  int width
  int height
  Color color1
  Color color2"
  [pos-x pos-y width height color1 color2]
  (raylib_h/DrawRectangleGradientV pos-x
                                   pos-y
                                   width
                                   height
                                   (rstructs/color color1)
                                   (rstructs/color color2)))

(defn draw-rectangle-gradient-h
  "Draw a horizontal-gradient-filled rectangle
  int posX
  int posY
  int width
  int height
  Color color1
  Color color2"
  [pos-x pos-y width height color1 color2]
  (raylib_h/DrawRectangleGradientH pos-x
                                   pos-y
                                   width
                                   height
                                   (rstructs/color color1)
                                   (rstructs/color color2)))

(defn draw-rectangle-gradient-ex
  "Draw a gradient-filled rectangle with custom vertex colors
  Rectangle rec
  Color col1
  Color col2
  Color col3
  Color col4"
  [rec col1 col2 col3 col4]
  (raylib_h/DrawRectangleGradientEx (rstructs/rectangle rec)
                                    (rstructs/color col1)
                                    (rstructs/color col2)
                                    (rstructs/color col3)
                                    (rstructs/color col4)))

(defn draw-rectangle-lines
  "Draw rectangle outline
  int posX
  int posY
  int width
  int height
  Color color"
  [pos-x pos-y width height color]
  (raylib_h/DrawRectangleLines pos-x pos-y width height (rstructs/color color)))

(defn draw-rectangle-lines-ex
  "Draw rectangle outline with extended parameters
  Rectangle rec
  float lineThick
  Color color"
  [rec line-thick color]
  (raylib_h/DrawRectangleLinesEx (rstructs/rectangle rec)
                                 line-thick
                                 (rstructs/color color)))

(defn draw-rectangle-rounded
  "Draw rectangle with rounded edges
  Rectangle rec
  float roundness
  int segments
  Color color"
  [rec roundness segments color]
  (raylib_h/DrawRectangleRounded (rstructs/rectangle rec)
                                 roundness
                                 segments
                                 (rstructs/color color)))

(defn draw-rectangle-rounded-lines
  "Draw rectangle with rounded edges outline
  Rectangle rec
  float roundness
  int segments
  float lineThick
  Color color"
  [rec roundness segments line-thick color]
  (raylib_h/DrawRectangleRoundedLines (rstructs/rectangle rec)
                                      roundness
                                      segments
                                      line-thick
                                      (rstructs/color color)))

(defn draw-triangle
  "Draw a color-filled triangle (vertex in counter-clockwise order!)
  Vector2 v1
  Vector2 v2
  Vector2 v3
  Color color"
  [v1 v2 v3 color]
  (raylib_h/DrawTriangle (rstructs/vector2 v1)
                         (rstructs/vector2 v2)
                         (rstructs/vector2 v3)
                         (rstructs/color color)))

(defn draw-triangle-lines
  "Draw triangle outline (vertex in counter-clockwise order!)
  Vector2 v1
  Vector2 v2
  Vector2 v3
  Color color"
  [v1 v2 v3 color]
  (raylib_h/DrawTriangleLines (rstructs/vector2 v1)
                              (rstructs/vector2 v2)
                              (rstructs/vector2 v3)
                              (rstructs/color color)))

(defn draw-triangle-fan
  "Draw a triangle fan defined by points (first vertex is the center)
  Vector2 * points
  int pointCount
  Color color"
  [points point-count color]
  (raylib_h/DrawTriangleFan points point-count (rstructs/color color)))

(defn draw-triangle-strip
  "Draw a triangle strip defined by points
  Vector2 * points
  int pointCount
  Color color"
  [points point-count color]
  (raylib_h/DrawTriangleStrip points point-count (rstructs/color color)))

(defn draw-poly
  "Draw a regular polygon (Vector version)
  Vector2 center
  int sides
  float radius
  float rotation
  Color color"
  [center sides radius rotation color]
  (raylib_h/DrawPoly (rstructs/vector2 center)
                     sides
                     radius
                     rotation
                     (rstructs/color color)))

(defn draw-poly-lines
  "Draw a polygon outline of n sides
  Vector2 center
  int sides
  float radius
  float rotation
  Color color"
  [center sides radius rotation color]
  (raylib_h/DrawPolyLines (rstructs/vector2 center)
                          sides
                          radius
                          rotation
                          (rstructs/color color)))

(defn draw-poly-lines-ex
  "Draw a polygon outline of n sides with extended parameters
  Vector2 center
  int sides
  float radius
  float rotation
  float lineThick
  Color color"
  [center sides radius rotation line-thick color]
  (raylib_h/DrawPolyLinesEx (rstructs/vector2 center)
                            sides
                            radius
                            rotation
                            line-thick
                            (rstructs/color color)))

(defn check-collision-recs?
  "Check collision between two rectangles
  Rectangle rec1
  Rectangle rec2"
  [rec1 rec2]
  (raylib_h/CheckCollisionRecs (rstructs/rectangle rec1)
                               (rstructs/rectangle rec2)))

(defn check-collision-circles?
  "Check collision between two circles
  Vector2 center1
  float radius1
  Vector2 center2
  float radius2"
  [center1 radius1 center2 radius2]
  (raylib_h/CheckCollisionCircles (rstructs/vector2 center1)
                                  radius1
                                  (rstructs/vector2 center2)
                                  radius2))

(defn check-collision-circle-rec?
  "Check collision between circle and rectangle
  Vector2 center
  float radius
  Rectangle rec"
  [center radius rec]
  (raylib_h/CheckCollisionCircleRec (rstructs/vector2 center)
                                    radius
                                    (rstructs/rectangle rec)))

(defn check-collision-point-rec?
  "Check if point is inside rectangle
  Vector2 point
  Rectangle rec"
  [point rec]
  (raylib_h/CheckCollisionPointRec (rstructs/vector2 point)
                                   (rstructs/rectangle rec)))

(defn check-collision-point-circle?
  "Check if point is inside circle
  Vector2 point
  Vector2 center
  float radius"
  [point center radius]
  (raylib_h/CheckCollisionPointCircle (rstructs/vector2 point)
                                      (rstructs/vector2 center)
                                      radius))

(defn check-collision-point-triangle?
  "Check if point is inside a triangle
  Vector2 point
  Vector2 p1
  Vector2 p2
  Vector2 p3"
  [point p1 p2 p3]
  (raylib_h/CheckCollisionPointTriangle (rstructs/vector2 point)
                                        (rstructs/vector2 p1)
                                        (rstructs/vector2 p2)
                                        (rstructs/vector2 p3)))

(defn check-collision-point-poly?
  "Check if point is within a polygon described by array of vertices
  Vector2 point
  Vector2 * points
  int pointCount"
  [point points point-count]
  (raylib_h/CheckCollisionPointPoly (rstructs/vector2 point)
                                    points
                                    point-count))

(defn check-collision-lines?
  "Check the collision between two lines defined by two points each, returns collision point by reference
  Vector2 startPos1
  Vector2 endPos1
  Vector2 startPos2
  Vector2 endPos2
  Vector2 * collisionPoint"
  [start-pos1 end-pos1 start-pos2 end-pos2 collision-point]
  (raylib_h/CheckCollisionLines (rstructs/vector2 start-pos1)
                                (rstructs/vector2 end-pos1)
                                (rstructs/vector2 start-pos2)
                                (rstructs/vector2 end-pos2)
                                collision-point))

(defn check-collision-point-line?
  "Check if point belongs to line created between two points [p1] and [p2] with defined margin in pixels [threshold]
  Vector2 point
  Vector2 p1
  Vector2 p2
  int threshold"
  [point p1 p2 threshold]
  (raylib_h/CheckCollisionPointLine (rstructs/vector2 point)
                                    (rstructs/vector2 p1)
                                    (rstructs/vector2 p2)
                                    threshold))

(defn get-collision-rec
  "Get collision rectangle for two rectangles collision
  Rectangle rec1
  Rectangle rec2"
  ([^Arena arena rec1 rec2]
   (raylib_h/GetCollisionRec arena
                             (rstructs/rectangle arena rec1)
                             (rstructs/rectangle arena rec2)))
  ([rec1 rec2]
   (rstructs/rectangle (raylib_h/GetCollisionRec rarena/*current-arena*
                                                 (rstructs/rectangle rec1)
                                                 (rstructs/rectangle rec2)))))

(defn load-image
  "Load image from file into CPU memory (RAM)
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/LoadImage arena (string arena file-name)))
  ([file-name]
   (rstructs/image (raylib_h/LoadImage rarena/*current-arena*
                                       (string file-name)))))

(defn load-image-raw
  "Load image from RAW file data
  const char * fileName
  int width
  int height
  int format
  int headerSize"
  ([^Arena arena file-name width height format header-size]
   (raylib_h/LoadImageRaw arena
                          (string arena file-name)
                          width
                          height
                          format
                          header-size))
  ([file-name width height format header-size]
   (rstructs/image (raylib_h/LoadImageRaw rarena/*current-arena*
                                          (string file-name)
                                          width
                                          height
                                          format
                                          header-size))))

(defn load-image-anim
  "Load image sequence from file (frames appended to image.data)
  const char * fileName
  int * frames"
  ([^Arena arena file-name frames]
   (raylib_h/LoadImageAnim arena (string arena file-name) frames))
  ([file-name frames]
   (rstructs/image (raylib_h/LoadImageAnim rarena/*current-arena*
                                           (string file-name)
                                           frames))))

(defn load-image-from-memory
  "Load image from memory buffer, fileType refers to extension: i.e. '.png'
  const char * fileType
  const unsigned char * fileData
  int dataSize"
  ([^Arena arena file-type file-data data-size]
   (raylib_h/LoadImageFromMemory arena
                                 (string arena file-type)
                                 file-data
                                 data-size))
  ([file-type file-data data-size]
   (rstructs/image (raylib_h/LoadImageFromMemory rarena/*current-arena*
                                                 (string file-type)
                                                 file-data
                                                 data-size))))

(defn load-image-from-texture
  "Load image from GPU texture data
  Texture2D texture"
  ([^Arena arena texture]
   (raylib_h/LoadImageFromTexture arena (rstructs/texture arena texture)))
  ([texture]
   (rstructs/image (raylib_h/LoadImageFromTexture rarena/*current-arena*
                                                  (rstructs/texture texture)))))

(defn load-image-from-screen
  "Load image from screen buffer and (screenshot)"
  ([^Arena arena] (raylib_h/LoadImageFromScreen arena))
  ([] (rstructs/image (raylib_h/LoadImageFromScreen rarena/*current-arena*))))

(defn image-ready?
  "Check if an image is ready
  Image image"
  [image]
  (raylib_h/IsImageReady (rstructs/image image)))

(defn unload-image
  "Unload image from CPU memory (RAM)
  Image image"
  [image]
  (raylib_h/UnloadImage (rstructs/image image)))

(defn export-image?
  "Export image data to file, returns true on success
  Image image
  const char * fileName"
  ([^Arena arena image file-name]
   (raylib_h/ExportImage (rstructs/image arena image) (string arena file-name)))
  ([image file-name]
   (raylib_h/ExportImage (rstructs/image image) (string file-name))))

(defn export-image-as-code?
  "Export image as code file defining an array of bytes, returns true on success
  Image image
  const char * fileName"
  ([^Arena arena image file-name]
   (raylib_h/ExportImageAsCode (rstructs/image arena image)
                               (string arena file-name)))
  ([image file-name]
   (raylib_h/ExportImageAsCode (rstructs/image image) (string file-name))))

(defn gen-image-color
  "Generate image: plain color
  int width
  int height
  Color color"
  ([^Arena arena width height color]
   (raylib_h/GenImageColor arena width height (rstructs/color arena color)))
  ([width height color]
   (rstructs/image (raylib_h/GenImageColor rarena/*current-arena*
                                           width
                                           height
                                           (rstructs/color color)))))

(defn gen-image-gradient-v
  "Generate image: vertical gradient
  int width
  int height
  Color top
  Color bottom"
  ([^Arena arena width height top bottom]
   (raylib_h/GenImageGradientV arena
                               width
                               height
                               (rstructs/color arena top)
                               (rstructs/color arena bottom)))
  ([width height top bottom]
   (rstructs/image (raylib_h/GenImageGradientV rarena/*current-arena*
                                               width
                                               height
                                               (rstructs/color top)
                                               (rstructs/color bottom)))))

(defn gen-image-gradient-h
  "Generate image: horizontal gradient
  int width
  int height
  Color left
  Color right"
  ([^Arena arena width height left right]
   (raylib_h/GenImageGradientH arena
                               width
                               height
                               (rstructs/color arena left)
                               (rstructs/color arena right)))
  ([width height left right]
   (rstructs/image (raylib_h/GenImageGradientH rarena/*current-arena*
                                               width
                                               height
                                               (rstructs/color left)
                                               (rstructs/color right)))))

(defn gen-image-gradient-radial
  "Generate image: radial gradient
  int width
  int height
  float density
  Color inner
  Color outer"
  ([^Arena arena width height density inner outer]
   (raylib_h/GenImageGradientRadial arena
                                    width
                                    height
                                    density
                                    (rstructs/color arena inner)
                                    (rstructs/color arena outer)))
  ([width height density inner outer]
   (rstructs/image (raylib_h/GenImageGradientRadial rarena/*current-arena*
                                                    width
                                                    height
                                                    density
                                                    (rstructs/color inner)
                                                    (rstructs/color outer)))))

(defn gen-image-checked
  "Generate image: checked
  int width
  int height
  int checksX
  int checksY
  Color col1
  Color col2"
  ([^Arena arena width height checks-x checks-y col1 col2]
   (raylib_h/GenImageChecked arena
                             width
                             height
                             checks-x
                             checks-y
                             (rstructs/color arena col1)
                             (rstructs/color arena col2)))
  ([width height checks-x checks-y col1 col2]
   (rstructs/image (raylib_h/GenImageChecked rarena/*current-arena*
                                             width
                                             height
                                             checks-x
                                             checks-y
                                             (rstructs/color col1)
                                             (rstructs/color col2)))))

(defn gen-image-white-noise
  "Generate image: white noise
  int width
  int height
  float factor"
  ([^Arena arena width height factor]
   (raylib_h/GenImageWhiteNoise arena width height factor))
  ([width height factor]
   (rstructs/image
     (raylib_h/GenImageWhiteNoise rarena/*current-arena* width height factor))))

(defn gen-image-perlin-noise
  "Generate image: perlin noise
  int width
  int height
  int offsetX
  int offsetY
  float scale"
  ([^Arena arena width height offset-x offset-y scale]
   (raylib_h/GenImagePerlinNoise arena width height offset-x offset-y scale))
  ([width height offset-x offset-y scale]
   (rstructs/image (raylib_h/GenImagePerlinNoise rarena/*current-arena*
                                                 width
                                                 height
                                                 offset-x
                                                 offset-y
                                                 scale))))

(defn gen-image-cellular
  "Generate image: cellular algorithm, bigger tileSize means bigger cells
  int width
  int height
  int tileSize"
  ([^Arena arena width height tile-size]
   (raylib_h/GenImageCellular arena width height tile-size))
  ([width height tile-size]
   (rstructs/image (raylib_h/GenImageCellular rarena/*current-arena*
                                              width
                                              height
                                              tile-size))))

(defn gen-image-text
  "Generate image: grayscale image from text data
  int width
  int height
  const char * text"
  ([^Arena arena width height text]
   (raylib_h/GenImageText arena width height (string arena text)))
  ([width height text]
   (rstructs/image (raylib_h/GenImageText rarena/*current-arena*
                                          width
                                          height
                                          (string text)))))

(defn image-copy
  "Create an image duplicate (useful for transformations)
  Image image"
  ([^Arena arena image]
   (raylib_h/ImageCopy arena (rstructs/image arena image)))
  ([image]
   (rstructs/image (raylib_h/ImageCopy rarena/*current-arena*
                                       (rstructs/image image)))))

(defn image-from-image
  "Create an image from another image piece
  Image image
  Rectangle rec"
  ([^Arena arena image rec]
   (raylib_h/ImageFromImage arena
                            (rstructs/image arena image)
                            (rstructs/rectangle arena rec)))
  ([image rec]
   (rstructs/image (raylib_h/ImageFromImage rarena/*current-arena*
                                            (rstructs/image image)
                                            (rstructs/rectangle rec)))))

(defn image-text
  "Create an image from text (default font)
  const char * text
  int fontSize
  Color color"
  ([^Arena arena text font-size color]
   (raylib_h/ImageText arena
                       (string arena text)
                       font-size
                       (rstructs/color arena color)))
  ([text font-size color]
   (rstructs/image (raylib_h/ImageText rarena/*current-arena*
                                       (string text)
                                       font-size
                                       (rstructs/color color)))))

(defn image-text-ex
  "Create an image from text (custom sprite font)
  Font font
  const char * text
  float fontSize
  float spacing
  Color tint"
  ([^Arena arena font text font-size spacing tint]
   (raylib_h/ImageTextEx arena
                         (rstructs/font arena font)
                         (string arena text)
                         font-size
                         spacing
                         (rstructs/color arena tint)))
  ([font text font-size spacing tint]
   (rstructs/image (raylib_h/ImageTextEx rarena/*current-arena*
                                         (rstructs/font font)
                                         (string text)
                                         font-size
                                         spacing
                                         (rstructs/color tint)))))

(defn image-format
  "Convert image data to desired format
  Image * image
  int newFormat"
  [image new-format]
  (raylib_h/ImageFormat image new-format))

(defn image-to-pot
  "Convert image to POT (power-of-two)
  Image * image
  Color fill"
  [image fill]
  (raylib_h/ImageToPOT image (rstructs/color fill)))

(defn image-crop
  "Crop an image to a defined rectangle
  Image * image
  Rectangle crop"
  [image crop]
  (raylib_h/ImageCrop image (rstructs/rectangle crop)))

(defn image-alpha-crop
  "Crop image depending on alpha value
  Image * image
  float threshold"
  [image threshold]
  (raylib_h/ImageAlphaCrop image threshold))

(defn image-alpha-clear
  "Clear alpha channel to desired color
  Image * image
  Color color
  float threshold"
  [image color threshold]
  (raylib_h/ImageAlphaClear image (rstructs/color color) threshold))

(defn image-alpha-mask
  "Apply alpha mask to image
  Image * image
  Image alphaMask"
  [image alpha-mask]
  (raylib_h/ImageAlphaMask image (rstructs/image alpha-mask)))

(defn image-alpha-premultiply
  "Premultiply alpha channel
  Image * image"
  [image]
  (raylib_h/ImageAlphaPremultiply image))

(defn image-blur-gaussian
  "Apply Gaussian blur using a box blur approximation
  Image * image
  int blurSize"
  [image blur-size]
  (raylib_h/ImageBlurGaussian image blur-size))

(defn image-resize
  "Resize image (Bicubic scaling algorithm)
  Image * image
  int newWidth
  int newHeight"
  [image new-width new-height]
  (raylib_h/ImageResize image new-width new-height))

(defn image-resize-nn
  "Resize image (Nearest-Neighbor scaling algorithm)
  Image * image
  int newWidth
  int newHeight"
  [image new-width new-height]
  (raylib_h/ImageResizeNN image new-width new-height))

(defn image-resize-canvas
  "Resize canvas and fill with color
  Image * image
  int newWidth
  int newHeight
  int offsetX
  int offsetY
  Color fill"
  [image new-width new-height offset-x offset-y fill]
  (raylib_h/ImageResizeCanvas image
                              new-width
                              new-height
                              offset-x
                              offset-y
                              (rstructs/color fill)))

(defn image-mipmaps
  "Compute all mipmap levels for a provided image
  Image * image"
  [image]
  (raylib_h/ImageMipmaps image))

(defn image-dither
  "Dither image data to 16bpp or lower (Floyd-Steinberg dithering)
  Image * image
  int rBpp
  int gBpp
  int bBpp
  int aBpp"
  [image r-bpp g-bpp b-bpp a-bpp]
  (raylib_h/ImageDither image r-bpp g-bpp b-bpp a-bpp))

(defn image-flip-vertical
  "Flip image vertically
  Image * image"
  [image]
  (raylib_h/ImageFlipVertical image))

(defn image-flip-horizontal
  "Flip image horizontally
  Image * image"
  [image]
  (raylib_h/ImageFlipHorizontal image))

(defn image-rotate-cw
  "Rotate image clockwise 90deg
  Image * image"
  [image]
  (raylib_h/ImageRotateCW image))

(defn image-rotate-ccw
  "Rotate image counter-clockwise 90deg
  Image * image"
  [image]
  (raylib_h/ImageRotateCCW image))

(defn image-color-tint
  "Modify image color: tint
  Image * image
  Color color"
  [image color]
  (raylib_h/ImageColorTint image (rstructs/color color)))

(defn image-color-invert
  "Modify image color: invert
  Image * image"
  [image]
  (raylib_h/ImageColorInvert image))

(defn image-color-grayscale
  "Modify image color: grayscale
  Image * image"
  [image]
  (raylib_h/ImageColorGrayscale image))

(defn image-color-contrast
  "Modify image color: contrast (-100 to 100)
  Image * image
  float contrast"
  [image contrast]
  (raylib_h/ImageColorContrast image contrast))

(defn image-color-brightness
  "Modify image color: brightness (-255 to 255)
  Image * image
  int brightness"
  [image brightness]
  (raylib_h/ImageColorBrightness image brightness))

(defn image-color-replace
  "Modify image color: replace color
  Image * image
  Color color
  Color replace"
  [image color replace]
  (raylib_h/ImageColorReplace image
                              (rstructs/color color)
                              (rstructs/color replace)))

(defn load-image-colors
  "Load color data from image as a Color array (RGBA - 32bit)
  Image image"
  [image]
  (raylib_h/LoadImageColors (rstructs/image image)))

(defn load-image-palette
  "Load colors palette from image as a Color array (RGBA - 32bit)
  Image image
  int maxPaletteSize
  int * colorCount"
  [image max-palette-size color-count]
  (raylib_h/LoadImagePalette (rstructs/image image)
                             max-palette-size
                             color-count))

(defn unload-image-colors
  "Unload color data loaded with LoadImageColors()
  Color * colors"
  [colors]
  (raylib_h/UnloadImageColors colors))

(defn unload-image-palette
  "Unload colors palette loaded with LoadImagePalette()
  Color * colors"
  [colors]
  (raylib_h/UnloadImagePalette colors))

(defn get-image-alpha-border
  "Get image alpha border rectangle
  Image image
  float threshold"
  ([^Arena arena image threshold]
   (raylib_h/GetImageAlphaBorder arena (rstructs/image arena image) threshold))
  ([image threshold]
   (rstructs/rectangle (raylib_h/GetImageAlphaBorder rarena/*current-arena*
                                                     (rstructs/image image)
                                                     threshold))))

(defn get-image-color
  "Get image pixel color at (x, y) position
  Image image
  int x
  int y"
  ([^Arena arena image x y]
   (raylib_h/GetImageColor arena (rstructs/image arena image) x y))
  ([image x y]
   (rstructs/color (raylib_h/GetImageColor rarena/*current-arena*
                                           (rstructs/image image)
                                           x
                                           y))))

(defn image-clear-background
  "Clear image background with given color
  Image * dst
  Color color"
  [dst color]
  (raylib_h/ImageClearBackground dst (rstructs/color color)))

(defn image-draw-pixel
  "Draw pixel within an image
  Image * dst
  int posX
  int posY
  Color color"
  [dst pos-x pos-y color]
  (raylib_h/ImageDrawPixel dst pos-x pos-y (rstructs/color color)))

(defn image-draw-pixel-v
  "Draw pixel within an image (Vector version)
  Image * dst
  Vector2 position
  Color color"
  [dst position color]
  (raylib_h/ImageDrawPixelV dst
                            (rstructs/vector2 position)
                            (rstructs/color color)))

(defn image-draw-line
  "Draw line within an image
  Image * dst
  int startPosX
  int startPosY
  int endPosX
  int endPosY
  Color color"
  [dst start-pos-x start-pos-y end-pos-x end-pos-y color]
  (raylib_h/ImageDrawLine dst
                          start-pos-x
                          start-pos-y
                          end-pos-x
                          end-pos-y
                          (rstructs/color color)))

(defn image-draw-line-v
  "Draw line within an image (Vector version)
  Image * dst
  Vector2 start
  Vector2 end
  Color color"
  [dst start end color]
  (raylib_h/ImageDrawLineV dst
                           (rstructs/vector2 start)
                           (rstructs/vector2 end)
                           (rstructs/color color)))

(defn image-draw-circle
  "Draw a filled circle within an image
  Image * dst
  int centerX
  int centerY
  int radius
  Color color"
  [dst center-x center-y radius color]
  (raylib_h/ImageDrawCircle dst
                            center-x
                            center-y
                            radius
                            (rstructs/color color)))

(defn image-draw-circle-v
  "Draw a filled circle within an image (Vector version)
  Image * dst
  Vector2 center
  int radius
  Color color"
  [dst center radius color]
  (raylib_h/ImageDrawCircleV dst
                             (rstructs/vector2 center)
                             radius
                             (rstructs/color color)))

(defn image-draw-circle-lines
  "Draw circle outline within an image
  Image * dst
  int centerX
  int centerY
  int radius
  Color color"
  [dst center-x center-y radius color]
  (raylib_h/ImageDrawCircleLines dst
                                 center-x
                                 center-y
                                 radius
                                 (rstructs/color color)))

(defn image-draw-circle-lines-v
  "Draw circle outline within an image (Vector version)
  Image * dst
  Vector2 center
  int radius
  Color color"
  [dst center radius color]
  (raylib_h/ImageDrawCircleLinesV dst
                                  (rstructs/vector2 center)
                                  radius
                                  (rstructs/color color)))

(defn image-draw-rectangle
  "Draw rectangle within an image
  Image * dst
  int posX
  int posY
  int width
  int height
  Color color"
  [dst pos-x pos-y width height color]
  (raylib_h/ImageDrawRectangle dst
                               pos-x
                               pos-y
                               width
                               height
                               (rstructs/color color)))

(defn image-draw-rectangle-v
  "Draw rectangle within an image (Vector version)
  Image * dst
  Vector2 position
  Vector2 size
  Color color"
  [dst position size color]
  (raylib_h/ImageDrawRectangleV dst
                                (rstructs/vector2 position)
                                (rstructs/vector2 size)
                                (rstructs/color color)))

(defn image-draw-rectangle-rec
  "Draw rectangle within an image
  Image * dst
  Rectangle rec
  Color color"
  [dst rec color]
  (raylib_h/ImageDrawRectangleRec dst
                                  (rstructs/rectangle rec)
                                  (rstructs/color color)))

(defn image-draw-rectangle-lines
  "Draw rectangle lines within an image
  Image * dst
  Rectangle rec
  int thick
  Color color"
  [dst rec thick color]
  (raylib_h/ImageDrawRectangleLines dst
                                    (rstructs/rectangle rec)
                                    thick
                                    (rstructs/color color)))

(defn image-draw
  "Draw a source image within a destination image (tint applied to source)
  Image * dst
  Image src
  Rectangle srcRec
  Rectangle dstRec
  Color tint"
  [dst src src-rec dst-rec tint]
  (raylib_h/ImageDraw dst
                      (rstructs/image src)
                      (rstructs/rectangle src-rec)
                      (rstructs/rectangle dst-rec)
                      (rstructs/color tint)))

(defn image-draw-text
  "Draw text (using default font) within an image (destination)
  Image * dst
  const char * text
  int posX
  int posY
  int fontSize
  Color color"
  ([^Arena arena dst text pos-x pos-y font-size color]
   (raylib_h/ImageDrawText dst
                           (string arena text)
                           pos-x
                           pos-y
                           font-size
                           (rstructs/color arena color)))
  ([dst text pos-x pos-y font-size color]
   (raylib_h/ImageDrawText dst
                           (string text)
                           pos-x
                           pos-y
                           font-size
                           (rstructs/color color))))

(defn image-draw-text-ex
  "Draw text (custom sprite font) within an image (destination)
  Image * dst
  Font font
  const char * text
  Vector2 position
  float fontSize
  float spacing
  Color tint"
  ([^Arena arena dst font text position font-size spacing tint]
   (raylib_h/ImageDrawTextEx dst
                             (rstructs/font arena font)
                             (string arena text)
                             (rstructs/vector2 arena position)
                             font-size
                             spacing
                             (rstructs/color arena tint)))
  ([dst font text position font-size spacing tint]
   (raylib_h/ImageDrawTextEx dst
                             (rstructs/font font)
                             (string text)
                             (rstructs/vector2 position)
                             font-size
                             spacing
                             (rstructs/color tint))))

(defn load-texture
  "Load texture from file into GPU memory (VRAM)
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/LoadTexture arena (string arena file-name)))
  ([file-name]
   (rstructs/texture (raylib_h/LoadTexture rarena/*current-arena*
                                           (string file-name)))))

(defn load-texture-from-image
  "Load texture from image data
  Image image"
  ([^Arena arena image]
   (raylib_h/LoadTextureFromImage arena (rstructs/image arena image)))
  ([image]
   (rstructs/texture (raylib_h/LoadTextureFromImage rarena/*current-arena*
                                                    (rstructs/image image)))))

(defn load-texture-cubemap
  "Load cubemap from image, multiple image cubemap layouts supported
  Image image
  int layout"
  ([^Arena arena image layout]
   (raylib_h/LoadTextureCubemap arena (rstructs/image arena image) layout))
  ([image layout]
   (rstructs/texture (raylib_h/LoadTextureCubemap rarena/*current-arena*
                                                  (rstructs/image image)
                                                  layout))))

(defn load-render-texture
  "Load texture for rendering (framebuffer)
  int width
  int height"
  ([^Arena arena width height]
   (raylib_h/LoadRenderTexture arena width height))
  ([width height]
   (rstructs/render-texture
     (raylib_h/LoadRenderTexture rarena/*current-arena* width height))))

(defn texture-ready?
  "Check if a texture is ready
  Texture2D texture"
  [texture]
  (raylib_h/IsTextureReady (rstructs/texture texture)))

(defn unload-texture
  "Unload texture from GPU memory (VRAM)
  Texture2D texture"
  [texture]
  (raylib_h/UnloadTexture (rstructs/texture texture)))

(defn render-texture-ready?
  "Check if a render texture is ready
  RenderTexture2D target"
  [target]
  (raylib_h/IsRenderTextureReady (rstructs/render-texture target)))

(defn unload-render-texture
  "Unload render texture from GPU memory (VRAM)
  RenderTexture2D target"
  [target]
  (raylib_h/UnloadRenderTexture (rstructs/render-texture target)))

(defn update-texture
  "Update GPU texture with new data
  Texture2D texture
  const void * pixels"
  [texture pixels]
  (raylib_h/UpdateTexture (rstructs/texture texture) pixels))

(defn update-texture-rec
  "Update GPU texture rectangle with new data
  Texture2D texture
  Rectangle rec
  const void * pixels"
  [texture rec pixels]
  (raylib_h/UpdateTextureRec (rstructs/texture texture)
                             (rstructs/rectangle rec)
                             pixels))

(defn gen-texture-mipmaps
  "Generate GPU mipmaps for a texture
  Texture2D * texture"
  [texture]
  (raylib_h/GenTextureMipmaps texture))

(defn set-texture-filter
  "Set texture scaling filter mode
  Texture2D texture
  int filter"
  [texture filter]
  (raylib_h/SetTextureFilter (rstructs/texture texture) filter))

(defn set-texture-wrap
  "Set texture wrapping mode
  Texture2D texture
  int wrap"
  [texture wrap]
  (raylib_h/SetTextureWrap (rstructs/texture texture) wrap))

(defn draw-texture
  "Draw a Texture2D
  Texture2D texture
  int posX
  int posY
  Color tint"
  [texture pos-x pos-y tint]
  (raylib_h/DrawTexture (rstructs/texture texture)
                        pos-x
                        pos-y
                        (rstructs/color tint)))

(defn draw-texture-v
  "Draw a Texture2D with position defined as Vector2
  Texture2D texture
  Vector2 position
  Color tint"
  [texture position tint]
  (raylib_h/DrawTextureV (rstructs/texture texture)
                         (rstructs/vector2 position)
                         (rstructs/color tint)))

(defn draw-texture-ex
  "Draw a Texture2D with extended parameters
  Texture2D texture
  Vector2 position
  float rotation
  float scale
  Color tint"
  [texture position rotation scale tint]
  (raylib_h/DrawTextureEx (rstructs/texture texture)
                          (rstructs/vector2 position)
                          rotation
                          scale
                          (rstructs/color tint)))

(defn draw-texture-rec
  "Draw a part of a texture defined by a rectangle
  Texture2D texture
  Rectangle source
  Vector2 position
  Color tint"
  [texture source position tint]
  (raylib_h/DrawTextureRec (rstructs/texture texture)
                           (rstructs/rectangle source)
                           (rstructs/vector2 position)
                           (rstructs/color tint)))

(defn draw-texture-pro
  "Draw a part of a texture defined by a rectangle with 'pro' parameters
  Texture2D texture
  Rectangle source
  Rectangle dest
  Vector2 origin
  float rotation
  Color tint"
  [texture source dest origin rotation tint]
  (raylib_h/DrawTexturePro (rstructs/texture texture)
                           (rstructs/rectangle source)
                           (rstructs/rectangle dest)
                           (rstructs/vector2 origin)
                           rotation
                           (rstructs/color tint)))

(defn draw-texture-npatch
  "Draws a texture (or part of it) that stretches or shrinks nicely
  Texture2D texture
  NPatchInfo nPatchInfo
  Rectangle dest
  Vector2 origin
  float rotation
  Color tint"
  [texture n-patch-info dest origin rotation tint]
  (raylib_h/DrawTextureNPatch (rstructs/texture texture)
                              (rstructs/npatch-info n-patch-info)
                              (rstructs/rectangle dest)
                              (rstructs/vector2 origin)
                              rotation
                              (rstructs/color tint)))

(defn fade
  "Get color with alpha applied, alpha goes from 0.0f to 1.0f
  Color color
  float alpha"
  ([^Arena arena color alpha]
   (raylib_h/Fade arena (rstructs/color arena color) alpha))
  ([color alpha]
   (rstructs/color
     (raylib_h/Fade rarena/*current-arena* (rstructs/color color) alpha))))

(defn color-to-int
  "Get hexadecimal value for a Color
  Color color"
  [color]
  (raylib_h/ColorToInt (rstructs/color color)))

(defn color-normalize
  "Get Color normalized as float [0..1]
  Color color"
  ([^Arena arena color]
   (raylib_h/ColorNormalize arena (rstructs/color arena color)))
  ([color]
   (rstructs/vector4 (raylib_h/ColorNormalize rarena/*current-arena*
                                              (rstructs/color color)))))

(defn color-from-normalized
  "Get Color from normalized values [0..1]
  Vector4 normalized"
  ([^Arena arena normalized]
   (raylib_h/ColorFromNormalized arena (rstructs/vector4 arena normalized)))
  ([normalized]
   (rstructs/color (raylib_h/ColorFromNormalized rarena/*current-arena*
                                                 (rstructs/vector4
                                                   normalized)))))

(defn color-to-hsv
  "Get HSV values for a Color, hue [0..360], saturation/value [0..1]
  Color color"
  ([^Arena arena color]
   (raylib_h/ColorToHSV arena (rstructs/color arena color)))
  ([color]
   (rstructs/vector3 (raylib_h/ColorToHSV rarena/*current-arena*
                                          (rstructs/color color)))))

(defn color-from-hsv
  "Get a Color from HSV values, hue [0..360], saturation/value [0..1]
  float hue
  float saturation
  float value"
  ([^Arena arena hue saturation value]
   (raylib_h/ColorFromHSV arena hue saturation value))
  ([hue saturation value]
   (rstructs/color
     (raylib_h/ColorFromHSV rarena/*current-arena* hue saturation value))))

(defn color-tint
  "Get color multiplied with another color
  Color color
  Color tint"
  ([^Arena arena color tint]
   (raylib_h/ColorTint arena
                       (rstructs/color arena color)
                       (rstructs/color arena tint)))
  ([color tint]
   (rstructs/color (raylib_h/ColorTint rarena/*current-arena*
                                       (rstructs/color color)
                                       (rstructs/color tint)))))

(defn color-brightness
  "Get color with brightness correction, brightness factor goes from -1.0f to 1.0f
  Color color
  float factor"
  ([^Arena arena color factor]
   (raylib_h/ColorBrightness arena (rstructs/color arena color) factor))
  ([color factor]
   (rstructs/color (raylib_h/ColorBrightness rarena/*current-arena*
                                             (rstructs/color color)
                                             factor))))

(defn color-contrast
  "Get color with contrast correction, contrast values between -1.0f and 1.0f
  Color color
  float contrast"
  ([^Arena arena color contrast]
   (raylib_h/ColorContrast arena (rstructs/color arena color) contrast))
  ([color contrast]
   (rstructs/color (raylib_h/ColorContrast rarena/*current-arena*
                                           (rstructs/color color)
                                           contrast))))

(defn color-alpha
  "Get color with alpha applied, alpha goes from 0.0f to 1.0f
  Color color
  float alpha"
  ([^Arena arena color alpha]
   (raylib_h/ColorAlpha arena (rstructs/color arena color) alpha))
  ([color alpha]
   (rstructs/color (raylib_h/ColorAlpha rarena/*current-arena*
                                        (rstructs/color color)
                                        alpha))))

(defn color-alpha-blend
  "Get src alpha-blended into dst color with tint
  Color dst
  Color src
  Color tint"
  ([^Arena arena dst src tint]
   (raylib_h/ColorAlphaBlend arena
                             (rstructs/color arena dst)
                             (rstructs/color arena src)
                             (rstructs/color arena tint)))
  ([dst src tint]
   (rstructs/color (raylib_h/ColorAlphaBlend rarena/*current-arena*
                                             (rstructs/color dst)
                                             (rstructs/color src)
                                             (rstructs/color tint)))))

(defn get-color
  "Get Color structure from hexadecimal value
  unsigned int hexValue"
  ([^Arena arena hex-value] (raylib_h/GetColor arena hex-value))
  ([hex-value]
   (rstructs/color (raylib_h/GetColor rarena/*current-arena* hex-value))))

(defn get-pixel-color
  "Get Color from a source pixel pointer of certain format
  void * srcPtr
  int format"
  ([^Arena arena src-ptr format]
   (raylib_h/GetPixelColor arena src-ptr format))
  ([src-ptr format]
   (rstructs/color
     (raylib_h/GetPixelColor rarena/*current-arena* src-ptr format))))

(defn set-pixel-color
  "Set color formatted into destination pixel pointer
  void * dstPtr
  Color color
  int format"
  [dst-ptr color format]
  (raylib_h/SetPixelColor dst-ptr (rstructs/color color) format))

(defn get-pixel-data-size
  "Get pixel data size in bytes for certain format
  int width
  int height
  int format"
  [width height format]
  (raylib_h/GetPixelDataSize width height format))

(defn get-font-default
  "Get the default Font"
  ([^Arena arena] (raylib_h/GetFontDefault arena))
  ([] (rstructs/font (raylib_h/GetFontDefault rarena/*current-arena*))))

(defn load-font
  "Load font from file into GPU memory (VRAM)
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/LoadFont arena (string arena file-name)))
  ([file-name]
   (rstructs/font (raylib_h/LoadFont rarena/*current-arena*
                                     (string file-name)))))

(defn load-font-ex
  "Load font from file with extended parameters, use NULL for fontChars and 0 for glyphCount to load the default character set
  const char * fileName
  int fontSize
  int * fontChars
  int glyphCount"
  ([^Arena arena file-name font-size font-chars glyph-count]
   (raylib_h/LoadFontEx arena
                        (string arena file-name)
                        font-size
                        font-chars
                        glyph-count))
  ([file-name font-size font-chars glyph-count]
   (rstructs/font (raylib_h/LoadFontEx rarena/*current-arena*
                                       (string file-name)
                                       font-size
                                       font-chars
                                       glyph-count))))

(defn load-font-from-image
  "Load font from Image (XNA style)
  Image image
  Color key
  int firstChar"
  ([^Arena arena image key first-char]
   (raylib_h/LoadFontFromImage arena
                               (rstructs/image arena image)
                               (rstructs/color arena key)
                               first-char))
  ([image key first-char]
   (rstructs/font (raylib_h/LoadFontFromImage rarena/*current-arena*
                                              (rstructs/image image)
                                              (rstructs/color key)
                                              first-char))))

(defn load-font-from-memory
  "Load font from memory buffer, fileType refers to extension: i.e. '.ttf'
  const char * fileType
  const unsigned char * fileData
  int dataSize
  int fontSize
  int * fontChars
  int glyphCount"
  ([^Arena arena file-type file-data data-size font-size font-chars
    glyph-count]
   (raylib_h/LoadFontFromMemory arena
                                (string arena file-type)
                                file-data
                                data-size
                                font-size
                                font-chars
                                glyph-count))
  ([file-type file-data data-size font-size font-chars glyph-count]
   (rstructs/font (raylib_h/LoadFontFromMemory rarena/*current-arena*
                                               (string file-type)
                                               file-data
                                               data-size
                                               font-size
                                               font-chars
                                               glyph-count))))

(defn font-ready?
  "Check if a font is ready
  Font font"
  [font]
  (raylib_h/IsFontReady (rstructs/font font)))

(defn load-font-data
  "Load font data for further use
  const unsigned char * fileData
  int dataSize
  int fontSize
  int * fontChars
  int glyphCount
  int type"
  [file-data data-size font-size font-chars glyph-count type]
  (raylib_h/LoadFontData file-data
                         data-size
                         font-size
                         font-chars
                         glyph-count
                         type))

(defn gen-image-font-atlas
  "Generate image font atlas using chars info
  const GlyphInfo * chars
  Rectangle ** recs
  int glyphCount
  int fontSize
  int padding
  int packMethod"
  ([^Arena arena chars recs glyph-count font-size padding pack-method]
   (raylib_h/GenImageFontAtlas arena
                               chars
                               recs
                               glyph-count
                               font-size
                               padding
                               pack-method))
  ([chars recs glyph-count font-size padding pack-method]
   (rstructs/image (raylib_h/GenImageFontAtlas rarena/*current-arena*
                                               chars
                                               recs
                                               glyph-count
                                               font-size
                                               padding
                                               pack-method))))

(defn unload-font-data
  "Unload font chars info data (RAM)
  GlyphInfo * chars
  int glyphCount"
  [chars glyph-count]
  (raylib_h/UnloadFontData chars glyph-count))

(defn unload-font
  "Unload font from GPU memory (VRAM)
  Font font"
  [font]
  (raylib_h/UnloadFont (rstructs/font font)))

(defn export-font-as-code?
  "Export font as code file, returns true on success
  Font font
  const char * fileName"
  ([^Arena arena font file-name]
   (raylib_h/ExportFontAsCode (rstructs/font arena font)
                              (string arena file-name)))
  ([font file-name]
   (raylib_h/ExportFontAsCode (rstructs/font font) (string file-name))))

(defn draw-fps
  "Draw current FPS
  int posX
  int posY"
  [pos-x pos-y]
  (raylib_h/DrawFPS pos-x pos-y))

(defn draw-text
  "Draw text (using default font)
  const char * text
  int posX
  int posY
  int fontSize
  Color color"
  ([^Arena arena text pos-x pos-y font-size color]
   (raylib_h/DrawText (string arena text)
                      pos-x
                      pos-y
                      font-size
                      (rstructs/color arena color)))
  ([text pos-x pos-y font-size color]
   (raylib_h/DrawText (string text)
                      pos-x
                      pos-y
                      font-size
                      (rstructs/color color))))

(defn draw-text-ex
  "Draw text using font and additional parameters
  Font font
  const char * text
  Vector2 position
  float fontSize
  float spacing
  Color tint"
  ([^Arena arena font text position font-size spacing tint]
   (raylib_h/DrawTextEx (rstructs/font arena font)
                        (string arena text)
                        (rstructs/vector2 arena position)
                        font-size
                        spacing
                        (rstructs/color arena tint)))
  ([font text position font-size spacing tint]
   (raylib_h/DrawTextEx (rstructs/font font)
                        (string text)
                        (rstructs/vector2 position)
                        font-size
                        spacing
                        (rstructs/color tint))))

(defn draw-text-pro
  "Draw text using Font and pro parameters (rotation)
  Font font
  const char * text
  Vector2 position
  Vector2 origin
  float rotation
  float fontSize
  float spacing
  Color tint"
  ([^Arena arena font text position origin rotation font-size spacing tint]
   (raylib_h/DrawTextPro (rstructs/font arena font)
                         (string arena text)
                         (rstructs/vector2 arena position)
                         (rstructs/vector2 arena origin)
                         rotation
                         font-size
                         spacing
                         (rstructs/color arena tint)))
  ([font text position origin rotation font-size spacing tint]
   (raylib_h/DrawTextPro (rstructs/font font)
                         (string text)
                         (rstructs/vector2 position)
                         (rstructs/vector2 origin)
                         rotation
                         font-size
                         spacing
                         (rstructs/color tint))))

(defn draw-text-codepoint
  "Draw one character (codepoint)
  Font font
  int codepoint
  Vector2 position
  float fontSize
  Color tint"
  [font codepoint position font-size tint]
  (raylib_h/DrawTextCodepoint (rstructs/font font)
                              codepoint
                              (rstructs/vector2 position)
                              font-size
                              (rstructs/color tint)))

(defn draw-text-codepoints
  "Draw multiple character (codepoint)
  Font font
  const int * codepoints
  int count
  Vector2 position
  float fontSize
  float spacing
  Color tint"
  [font codepoints count position font-size spacing tint]
  (raylib_h/DrawTextCodepoints (rstructs/font font)
                               codepoints
                               count
                               (rstructs/vector2 position)
                               font-size
                               spacing
                               (rstructs/color tint)))

(defn measure-text
  "Measure string width for default font
  const char * text
  int fontSize"
  ([^Arena arena text font-size]
   (raylib_h/MeasureText (string arena text) font-size))
  ([text font-size] (raylib_h/MeasureText (string text) font-size)))

(defn measure-text-ex
  "Measure string size for Font
  Font font
  const char * text
  float fontSize
  float spacing"
  ([^Arena arena font text font-size spacing]
   (raylib_h/MeasureTextEx arena
                           (rstructs/font arena font)
                           (string arena text)
                           font-size
                           spacing))
  ([font text font-size spacing]
   (rstructs/vector2 (raylib_h/MeasureTextEx rarena/*current-arena*
                                             (rstructs/font font)
                                             (string text)
                                             font-size
                                             spacing))))

(defn get-glyph-index
  "Get glyph index position in font for a codepoint (unicode character), fallback to '?' if not found
  Font font
  int codepoint"
  [font codepoint]
  (raylib_h/GetGlyphIndex (rstructs/font font) codepoint))

(defn get-glyph-info
  "Get glyph font info data for a codepoint (unicode character), fallback to '?' if not found
  Font font
  int codepoint"
  ([^Arena arena font codepoint]
   (raylib_h/GetGlyphInfo arena (rstructs/font arena font) codepoint))
  ([font codepoint]
   (rstructs/glyph-info (raylib_h/GetGlyphInfo rarena/*current-arena*
                                               (rstructs/font font)
                                               codepoint))))

(defn get-glyph-atlas-rec
  "Get glyph rectangle in font atlas for a codepoint (unicode character), fallback to '?' if not found
  Font font
  int codepoint"
  ([^Arena arena font codepoint]
   (raylib_h/GetGlyphAtlasRec arena (rstructs/font arena font) codepoint))
  ([font codepoint]
   (rstructs/rectangle (raylib_h/GetGlyphAtlasRec rarena/*current-arena*
                                                  (rstructs/font font)
                                                  codepoint))))

(defn load-utf8
  "Load UTF-8 text encoded from codepoints array
  const int * codepoints
  int length"
  [codepoints length]
  (raylib_h/LoadUTF8 codepoints length))

(defn unload-utf8
  "Unload UTF-8 text encoded from codepoints array
  char * text"
  [text]
  (raylib_h/UnloadUTF8 text))

(defn load-codepoints
  "Load all codepoints from a UTF-8 text string, codepoints count returned by parameter
  const char * text
  int * count"
  ([^Arena arena text count]
   (raylib_h/LoadCodepoints (string arena text) count))
  ([text count] (raylib_h/LoadCodepoints (string text) count)))

(defn unload-codepoints
  "Unload codepoints data from memory
  int * codepoints"
  [codepoints]
  (raylib_h/UnloadCodepoints codepoints))

(defn get-codepoint-count
  "Get total number of codepoints in a UTF-8 encoded string
  const char * text"
  ([^Arena arena text] (raylib_h/GetCodepointCount (string arena text)))
  ([text] (raylib_h/GetCodepointCount (string text))))

(defn get-codepoint
  "Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
  const char * text
  int * codepointSize"
  ([^Arena arena text codepoint-size]
   (raylib_h/GetCodepoint (string arena text) codepoint-size))
  ([text codepoint-size] (raylib_h/GetCodepoint (string text) codepoint-size)))

(defn get-codepoint-next
  "Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
  const char * text
  int * codepointSize"
  ([^Arena arena text codepoint-size]
   (raylib_h/GetCodepointNext (string arena text) codepoint-size))
  ([text codepoint-size]
   (raylib_h/GetCodepointNext (string text) codepoint-size)))

(defn get-codepoint-previous
  "Get previous codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
  const char * text
  int * codepointSize"
  ([^Arena arena text codepoint-size]
   (raylib_h/GetCodepointPrevious (string arena text) codepoint-size))
  ([text codepoint-size]
   (raylib_h/GetCodepointPrevious (string text) codepoint-size)))

(defn codepoint-to-utf8
  "Encode one codepoint into UTF-8 byte array (array length returned as parameter)
  int codepoint
  int * utf8Size"
  [codepoint utf8size]
  (raylib_h/CodepointToUTF8 codepoint utf8size))

(defn text-copy
  "Copy one string to another, returns bytes copied
  char * dst
  const char * src"
  ([^Arena arena dst src] (raylib_h/TextCopy dst (string arena src)))
  ([dst src] (raylib_h/TextCopy dst (string src))))

(defn text-is-equal?
  "Check if two text string are equal
  const char * text1
  const char * text2"
  ([^Arena arena text1 text2]
   (raylib_h/TextIsEqual (string arena text1) (string arena text2)))
  ([text1 text2] (raylib_h/TextIsEqual (string text1) (string text2))))

(defn text-length
  "Get text length, checks for '\\0' ending
  const char * text"
  ([^Arena arena text] (raylib_h/TextLength (string arena text)))
  ([text] (raylib_h/TextLength (string text))))

(defn text-format
  "Text formatting with variables (sprintf() style)
  const char * text
  ... args"
  ([^Arena arena text args] (raylib_h/TextFormat (string arena text) args))
  ([text args] (raylib_h/TextFormat (string text) args)))

(defn text-subtext
  "Get a piece of a text string
  const char * text
  int position
  int length"
  ([^Arena arena text position length]
   (raylib_h/TextSubtext (string arena text) position length))
  ([text position length] (raylib_h/TextSubtext (string text) position length)))

(defn text-replace
  "Replace text string (WARNING: memory must be freed!)
  char * text
  const char * replace
  const char * by"
  ([^Arena arena text replace by]
   (raylib_h/TextReplace text (string arena replace) (string arena by)))
  ([text replace by] (raylib_h/TextReplace text (string replace) (string by))))

(defn text-insert
  "Insert text in a position (WARNING: memory must be freed!)
  const char * text
  const char * insert
  int position"
  ([^Arena arena text insert position]
   (raylib_h/TextInsert (string arena text) (string arena insert) position))
  ([text insert position]
   (raylib_h/TextInsert (string text) (string insert) position)))

(defn text-join
  "Join text strings with delimiter
  const char ** textList
  int count
  const char * delimiter"
  ([^Arena arena text-list count delimiter]
   (raylib_h/TextJoin text-list count (string arena delimiter)))
  ([text-list count delimiter]
   (raylib_h/TextJoin text-list count (string delimiter))))

(defn text-split
  "Split text into multiple strings
  const char * text
  char delimiter
  int * count"
  ([^Arena arena text delimiter count]
   (raylib_h/TextSplit (string arena text) delimiter count))
  ([text delimiter count] (raylib_h/TextSplit (string text) delimiter count)))

(defn text-append
  "Append text at specific position and move cursor!
  char * text
  const char * append
  int * position"
  ([^Arena arena text append position]
   (raylib_h/TextAppend text (string arena append) position))
  ([text append position] (raylib_h/TextAppend text (string append) position)))

(defn text-find-index
  "Find first text occurrence within a string
  const char * text
  const char * find"
  ([^Arena arena text find]
   (raylib_h/TextFindIndex (string arena text) (string arena find)))
  ([text find] (raylib_h/TextFindIndex (string text) (string find))))

(defn text-to-upper
  "Get upper case version of provided string
  const char * text"
  ([^Arena arena text] (raylib_h/TextToUpper (string arena text)))
  ([text] (raylib_h/TextToUpper (string text))))

(defn text-to-lower
  "Get lower case version of provided string
  const char * text"
  ([^Arena arena text] (raylib_h/TextToLower (string arena text)))
  ([text] (raylib_h/TextToLower (string text))))

(defn text-to-pascal
  "Get Pascal case notation version of provided string
  const char * text"
  ([^Arena arena text] (raylib_h/TextToPascal (string arena text)))
  ([text] (raylib_h/TextToPascal (string text))))

(defn text-to-integer
  "Get integer value from text (negative values not supported)
  const char * text"
  ([^Arena arena text] (raylib_h/TextToInteger (string arena text)))
  ([text] (raylib_h/TextToInteger (string text))))

(defn draw-line3d
  "Draw a line in 3D world space
  Vector3 startPos
  Vector3 endPos
  Color color"
  [start-pos end-pos color]
  (raylib_h/DrawLine3D (rstructs/vector3 start-pos)
                       (rstructs/vector3 end-pos)
                       (rstructs/color color)))

(defn draw-point3d
  "Draw a point in 3D space, actually a small line
  Vector3 position
  Color color"
  [position color]
  (raylib_h/DrawPoint3D (rstructs/vector3 position) (rstructs/color color)))

(defn draw-circle3d
  "Draw a circle in 3D world space
  Vector3 center
  float radius
  Vector3 rotationAxis
  float rotationAngle
  Color color"
  [center radius rotation-axis rotation-angle color]
  (raylib_h/DrawCircle3D (rstructs/vector3 center)
                         radius
                         (rstructs/vector3 rotation-axis)
                         rotation-angle
                         (rstructs/color color)))

(defn draw-triangle3d
  "Draw a color-filled triangle (vertex in counter-clockwise order!)
  Vector3 v1
  Vector3 v2
  Vector3 v3
  Color color"
  [v1 v2 v3 color]
  (raylib_h/DrawTriangle3D (rstructs/vector3 v1)
                           (rstructs/vector3 v2)
                           (rstructs/vector3 v3)
                           (rstructs/color color)))

(defn draw-triangle-strip3d
  "Draw a triangle strip defined by points
  Vector3 * points
  int pointCount
  Color color"
  [points point-count color]
  (raylib_h/DrawTriangleStrip3D points point-count (rstructs/color color)))

(defn draw-cube
  "Draw cube
  Vector3 position
  float width
  float height
  float length
  Color color"
  [position width height length color]
  (raylib_h/DrawCube (rstructs/vector3 position)
                     width
                     height
                     length
                     (rstructs/color color)))

(defn draw-cube-v
  "Draw cube (Vector version)
  Vector3 position
  Vector3 size
  Color color"
  [position size color]
  (raylib_h/DrawCubeV (rstructs/vector3 position)
                      (rstructs/vector3 size)
                      (rstructs/color color)))

(defn draw-cube-wires
  "Draw cube wires
  Vector3 position
  float width
  float height
  float length
  Color color"
  [position width height length color]
  (raylib_h/DrawCubeWires (rstructs/vector3 position)
                          width
                          height
                          length
                          (rstructs/color color)))

(defn draw-cube-wires-v
  "Draw cube wires (Vector version)
  Vector3 position
  Vector3 size
  Color color"
  [position size color]
  (raylib_h/DrawCubeWiresV (rstructs/vector3 position)
                           (rstructs/vector3 size)
                           (rstructs/color color)))

(defn draw-sphere
  "Draw sphere
  Vector3 centerPos
  float radius
  Color color"
  [center-pos radius color]
  (raylib_h/DrawSphere (rstructs/vector3 center-pos)
                       radius
                       (rstructs/color color)))

(defn draw-sphere-ex
  "Draw sphere with extended parameters
  Vector3 centerPos
  float radius
  int rings
  int slices
  Color color"
  [center-pos radius rings slices color]
  (raylib_h/DrawSphereEx (rstructs/vector3 center-pos)
                         radius
                         rings
                         slices
                         (rstructs/color color)))

(defn draw-sphere-wires
  "Draw sphere wires
  Vector3 centerPos
  float radius
  int rings
  int slices
  Color color"
  [center-pos radius rings slices color]
  (raylib_h/DrawSphereWires (rstructs/vector3 center-pos)
                            radius
                            rings
                            slices
                            (rstructs/color color)))

(defn draw-cylinder
  "Draw a cylinder/cone
  Vector3 position
  float radiusTop
  float radiusBottom
  float height
  int slices
  Color color"
  [position radius-top radius-bottom height slices color]
  (raylib_h/DrawCylinder (rstructs/vector3 position)
                         radius-top
                         radius-bottom
                         height
                         slices
                         (rstructs/color color)))

(defn draw-cylinder-ex
  "Draw a cylinder with base at startPos and top at endPos
  Vector3 startPos
  Vector3 endPos
  float startRadius
  float endRadius
  int sides
  Color color"
  [start-pos end-pos start-radius end-radius sides color]
  (raylib_h/DrawCylinderEx (rstructs/vector3 start-pos)
                           (rstructs/vector3 end-pos)
                           start-radius
                           end-radius
                           sides
                           (rstructs/color color)))

(defn draw-cylinder-wires
  "Draw a cylinder/cone wires
  Vector3 position
  float radiusTop
  float radiusBottom
  float height
  int slices
  Color color"
  [position radius-top radius-bottom height slices color]
  (raylib_h/DrawCylinderWires (rstructs/vector3 position)
                              radius-top
                              radius-bottom
                              height
                              slices
                              (rstructs/color color)))

(defn draw-cylinder-wires-ex
  "Draw a cylinder wires with base at startPos and top at endPos
  Vector3 startPos
  Vector3 endPos
  float startRadius
  float endRadius
  int sides
  Color color"
  [start-pos end-pos start-radius end-radius sides color]
  (raylib_h/DrawCylinderWiresEx (rstructs/vector3 start-pos)
                                (rstructs/vector3 end-pos)
                                start-radius
                                end-radius
                                sides
                                (rstructs/color color)))

(defn draw-capsule
  "Draw a capsule with the center of its sphere caps at startPos and endPos
  Vector3 startPos
  Vector3 endPos
  float radius
  int slices
  int rings
  Color color"
  [start-pos end-pos radius slices rings color]
  (raylib_h/DrawCapsule (rstructs/vector3 start-pos)
                        (rstructs/vector3 end-pos)
                        radius
                        slices
                        rings
                        (rstructs/color color)))

(defn draw-capsule-wires
  "Draw capsule wireframe with the center of its sphere caps at startPos and endPos
  Vector3 startPos
  Vector3 endPos
  float radius
  int slices
  int rings
  Color color"
  [start-pos end-pos radius slices rings color]
  (raylib_h/DrawCapsuleWires (rstructs/vector3 start-pos)
                             (rstructs/vector3 end-pos)
                             radius
                             slices
                             rings
                             (rstructs/color color)))

(defn draw-plane
  "Draw a plane XZ
  Vector3 centerPos
  Vector2 size
  Color color"
  [center-pos size color]
  (raylib_h/DrawPlane (rstructs/vector3 center-pos)
                      (rstructs/vector2 size)
                      (rstructs/color color)))

(defn draw-ray
  "Draw a ray line
  Ray ray
  Color color"
  [ray color]
  (raylib_h/DrawRay (rstructs/ray ray) (rstructs/color color)))

(defn draw-grid
  "Draw a grid (centered at (0, 0, 0))
  int slices
  float spacing"
  [slices spacing]
  (raylib_h/DrawGrid slices spacing))

(defn load-model
  "Load model from files (meshes and materials)
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/LoadModel arena (string arena file-name)))
  ([file-name]
   (rstructs/model (raylib_h/LoadModel rarena/*current-arena*
                                       (string file-name)))))

(defn load-model-from-mesh
  "Load model from generated mesh (default material)
  Mesh mesh"
  ([^Arena arena mesh]
   (raylib_h/LoadModelFromMesh arena (rstructs/mesh arena mesh)))
  ([mesh]
   (rstructs/model (raylib_h/LoadModelFromMesh rarena/*current-arena*
                                               (rstructs/mesh mesh)))))

(defn model-ready?
  "Check if a model is ready
  Model model"
  [model]
  (raylib_h/IsModelReady (rstructs/model model)))

(defn unload-model
  "Unload model (including meshes) from memory (RAM and/or VRAM)
  Model model"
  [model]
  (raylib_h/UnloadModel (rstructs/model model)))

(defn get-model-bounding-box
  "Compute model bounding box limits (considers all meshes)
  Model model"
  ([^Arena arena model]
   (raylib_h/GetModelBoundingBox arena (rstructs/model arena model)))
  ([model]
   (rstructs/bounding-box (raylib_h/GetModelBoundingBox rarena/*current-arena*
                                                        (rstructs/model
                                                          model)))))

(defn draw-model
  "Draw a model (with texture if set)
  Model model
  Vector3 position
  float scale
  Color tint"
  [model position scale tint]
  (raylib_h/DrawModel (rstructs/model model)
                      (rstructs/vector3 position)
                      scale
                      (rstructs/color tint)))

(defn draw-model-ex
  "Draw a model with extended parameters
  Model model
  Vector3 position
  Vector3 rotationAxis
  float rotationAngle
  Vector3 scale
  Color tint"
  [model position rotation-axis rotation-angle scale tint]
  (raylib_h/DrawModelEx (rstructs/model model)
                        (rstructs/vector3 position)
                        (rstructs/vector3 rotation-axis)
                        rotation-angle
                        (rstructs/vector3 scale)
                        (rstructs/color tint)))

(defn draw-model-wires
  "Draw a model wires (with texture if set)
  Model model
  Vector3 position
  float scale
  Color tint"
  [model position scale tint]
  (raylib_h/DrawModelWires (rstructs/model model)
                           (rstructs/vector3 position)
                           scale
                           (rstructs/color tint)))

(defn draw-model-wires-ex
  "Draw a model wires (with texture if set) with extended parameters
  Model model
  Vector3 position
  Vector3 rotationAxis
  float rotationAngle
  Vector3 scale
  Color tint"
  [model position rotation-axis rotation-angle scale tint]
  (raylib_h/DrawModelWiresEx (rstructs/model model)
                             (rstructs/vector3 position)
                             (rstructs/vector3 rotation-axis)
                             rotation-angle
                             (rstructs/vector3 scale)
                             (rstructs/color tint)))

(defn draw-bounding-box
  "Draw bounding box (wires)
  BoundingBox box
  Color color"
  [box color]
  (raylib_h/DrawBoundingBox (rstructs/bounding-box box) (rstructs/color color)))

(defn draw-billboard
  "Draw a billboard texture
  Camera camera
  Texture2D texture
  Vector3 position
  float size
  Color tint"
  [camera texture position size tint]
  (raylib_h/DrawBillboard (rstructs/camera3d camera)
                          (rstructs/texture texture)
                          (rstructs/vector3 position)
                          size
                          (rstructs/color tint)))

(defn draw-billboard-rec
  "Draw a billboard texture defined by source
  Camera camera
  Texture2D texture
  Rectangle source
  Vector3 position
  Vector2 size
  Color tint"
  [camera texture source position size tint]
  (raylib_h/DrawBillboardRec (rstructs/camera3d camera)
                             (rstructs/texture texture)
                             (rstructs/rectangle source)
                             (rstructs/vector3 position)
                             (rstructs/vector2 size)
                             (rstructs/color tint)))

(defn draw-billboard-pro
  "Draw a billboard texture defined by source and rotation
  Camera camera
  Texture2D texture
  Rectangle source
  Vector3 position
  Vector3 up
  Vector2 size
  Vector2 origin
  float rotation
  Color tint"
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
  Mesh * mesh
  bool dynamic"
  [mesh dynamic]
  (raylib_h/UploadMesh mesh dynamic))

(defn update-mesh-buffer
  "Update mesh vertex data in GPU for a specific buffer index
  Mesh mesh
  int index
  const void * data
  int dataSize
  int offset"
  [mesh index data data-size offset]
  (raylib_h/UpdateMeshBuffer (rstructs/mesh mesh) index data data-size offset))

(defn unload-mesh
  "Unload mesh data from CPU and GPU
  Mesh mesh"
  [mesh]
  (raylib_h/UnloadMesh (rstructs/mesh mesh)))

(defn draw-mesh
  "Draw a 3d mesh with material and transform
  Mesh mesh
  Material material
  Matrix transform"
  [mesh material transform]
  (raylib_h/DrawMesh (rstructs/mesh mesh) material (rstructs/matrix transform)))

(defn draw-mesh-instanced
  "Draw multiple mesh instances with material and different transforms
  Mesh mesh
  Material material
  const Matrix * transforms
  int instances"
  [mesh material transforms instances]
  (raylib_h/DrawMeshInstanced (rstructs/mesh mesh)
                              material
                              transforms
                              instances))

(defn export-mesh?
  "Export mesh data to file, returns true on success
  Mesh mesh
  const char * fileName"
  ([^Arena arena mesh file-name]
   (raylib_h/ExportMesh (rstructs/mesh arena mesh) (string arena file-name)))
  ([mesh file-name]
   (raylib_h/ExportMesh (rstructs/mesh mesh) (string file-name))))

(defn get-mesh-bounding-box
  "Compute mesh bounding box limits
  Mesh mesh"
  ([^Arena arena mesh]
   (raylib_h/GetMeshBoundingBox arena (rstructs/mesh arena mesh)))
  ([mesh]
   (rstructs/bounding-box (raylib_h/GetMeshBoundingBox rarena/*current-arena*
                                                       (rstructs/mesh mesh)))))

(defn gen-mesh-tangents
  "Compute mesh tangents
  Mesh * mesh"
  [mesh]
  (raylib_h/GenMeshTangents mesh))

(defn gen-mesh-poly
  "Generate polygonal mesh
  int sides
  float radius"
  ([^Arena arena sides radius] (raylib_h/GenMeshPoly arena sides radius))
  ([sides radius]
   (rstructs/mesh (raylib_h/GenMeshPoly rarena/*current-arena* sides radius))))

(defn gen-mesh-plane
  "Generate plane mesh (with subdivisions)
  float width
  float length
  int resX
  int resZ"
  ([^Arena arena width length res-x res-z]
   (raylib_h/GenMeshPlane arena width length res-x res-z))
  ([width length res-x res-z]
   (rstructs/mesh
     (raylib_h/GenMeshPlane rarena/*current-arena* width length res-x res-z))))

(defn gen-mesh-cube
  "Generate cuboid mesh
  float width
  float height
  float length"
  ([^Arena arena width height length]
   (raylib_h/GenMeshCube arena width height length))
  ([width height length]
   (rstructs/mesh
     (raylib_h/GenMeshCube rarena/*current-arena* width height length))))

(defn gen-mesh-sphere
  "Generate sphere mesh (standard sphere)
  float radius
  int rings
  int slices"
  ([^Arena arena radius rings slices]
   (raylib_h/GenMeshSphere arena radius rings slices))
  ([radius rings slices]
   (rstructs/mesh
     (raylib_h/GenMeshSphere rarena/*current-arena* radius rings slices))))

(defn gen-mesh-hemi-sphere
  "Generate half-sphere mesh (no bottom cap)
  float radius
  int rings
  int slices"
  ([^Arena arena radius rings slices]
   (raylib_h/GenMeshHemiSphere arena radius rings slices))
  ([radius rings slices]
   (rstructs/mesh
     (raylib_h/GenMeshHemiSphere rarena/*current-arena* radius rings slices))))

(defn gen-mesh-cylinder
  "Generate cylinder mesh
  float radius
  float height
  int slices"
  ([^Arena arena radius height slices]
   (raylib_h/GenMeshCylinder arena radius height slices))
  ([radius height slices]
   (rstructs/mesh
     (raylib_h/GenMeshCylinder rarena/*current-arena* radius height slices))))

(defn gen-mesh-cone
  "Generate cone/pyramid mesh
  float radius
  float height
  int slices"
  ([^Arena arena radius height slices]
   (raylib_h/GenMeshCone arena radius height slices))
  ([radius height slices]
   (rstructs/mesh
     (raylib_h/GenMeshCone rarena/*current-arena* radius height slices))))

(defn gen-mesh-torus
  "Generate torus mesh
  float radius
  float size
  int radSeg
  int sides"
  ([^Arena arena radius size rad-seg sides]
   (raylib_h/GenMeshTorus arena radius size rad-seg sides))
  ([radius size rad-seg sides]
   (rstructs/mesh
     (raylib_h/GenMeshTorus rarena/*current-arena* radius size rad-seg sides))))

(defn gen-mesh-knot
  "Generate trefoil knot mesh
  float radius
  float size
  int radSeg
  int sides"
  ([^Arena arena radius size rad-seg sides]
   (raylib_h/GenMeshKnot arena radius size rad-seg sides))
  ([radius size rad-seg sides]
   (rstructs/mesh
     (raylib_h/GenMeshKnot rarena/*current-arena* radius size rad-seg sides))))

(defn gen-mesh-heightmap
  "Generate heightmap mesh from image data
  Image heightmap
  Vector3 size"
  ([^Arena arena heightmap size]
   (raylib_h/GenMeshHeightmap arena
                              (rstructs/image arena heightmap)
                              (rstructs/vector3 arena size)))
  ([heightmap size]
   (rstructs/mesh (raylib_h/GenMeshHeightmap rarena/*current-arena*
                                             (rstructs/image heightmap)
                                             (rstructs/vector3 size)))))

(defn gen-mesh-cubicmap
  "Generate cubes-based map mesh from image data
  Image cubicmap
  Vector3 cubeSize"
  ([^Arena arena cubicmap cube-size]
   (raylib_h/GenMeshCubicmap arena
                             (rstructs/image arena cubicmap)
                             (rstructs/vector3 arena cube-size)))
  ([cubicmap cube-size]
   (rstructs/mesh (raylib_h/GenMeshCubicmap rarena/*current-arena*
                                            (rstructs/image cubicmap)
                                            (rstructs/vector3 cube-size)))))

(defn load-materials
  "Load materials from model file
  const char * fileName
  int * materialCount"
  ([^Arena arena file-name material-count]
   (raylib_h/LoadMaterials (string arena file-name) material-count))
  ([file-name material-count]
   (raylib_h/LoadMaterials (string file-name) material-count)))

(defn material-ready?
  "Check if a material is ready
  Material material"
  [material]
  (raylib_h/IsMaterialReady material))

(defn unload-material
  "Unload material from GPU memory (VRAM)
  Material material"
  [material]
  (raylib_h/UnloadMaterial material))

(defn set-material-texture
  "Set texture for a material map type (MATERIAL_MAP_DIFFUSE, MATERIAL_MAP_SPECULAR...)
  Material * material
  int mapType
  Texture2D texture"
  [material map-type texture]
  (raylib_h/SetMaterialTexture material map-type (rstructs/texture texture)))

(defn set-model-mesh-material
  "Set material for a mesh
  Model * model
  int meshId
  int materialId"
  [model mesh-id material-id]
  (raylib_h/SetModelMeshMaterial model mesh-id material-id))

(defn load-model-animations
  "Load model animations from file
  const char * fileName
  unsigned int * animCount"
  ([^Arena arena file-name anim-count]
   (raylib_h/LoadModelAnimations (string arena file-name) anim-count))
  ([file-name anim-count]
   (raylib_h/LoadModelAnimations (string file-name) anim-count)))

(defn update-model-animation
  "Update model animation pose
  Model model
  ModelAnimation anim
  int frame"
  [model anim frame]
  (raylib_h/UpdateModelAnimation (rstructs/model model)
                                 (rstructs/model-animation anim)
                                 frame))

(defn unload-model-animation
  "Unload animation data
  ModelAnimation anim"
  [anim]
  (raylib_h/UnloadModelAnimation (rstructs/model-animation anim)))

(defn unload-model-animations
  "Unload animation array data
  ModelAnimation * animations
  unsigned int count"
  [animations count]
  (raylib_h/UnloadModelAnimations animations count))

(defn model-animation-valid?
  "Check model animation skeleton match
  Model model
  ModelAnimation anim"
  [model anim]
  (raylib_h/IsModelAnimationValid (rstructs/model model)
                                  (rstructs/model-animation anim)))

(defn check-collision-spheres?
  "Check collision between two spheres
  Vector3 center1
  float radius1
  Vector3 center2
  float radius2"
  [center1 radius1 center2 radius2]
  (raylib_h/CheckCollisionSpheres (rstructs/vector3 center1)
                                  radius1
                                  (rstructs/vector3 center2)
                                  radius2))

(defn check-collision-boxes?
  "Check collision between two bounding boxes
  BoundingBox box1
  BoundingBox box2"
  [box1 box2]
  (raylib_h/CheckCollisionBoxes (rstructs/bounding-box box1)
                                (rstructs/bounding-box box2)))

(defn check-collision-box-sphere?
  "Check collision between box and sphere
  BoundingBox box
  Vector3 center
  float radius"
  [box center radius]
  (raylib_h/CheckCollisionBoxSphere (rstructs/bounding-box box)
                                    (rstructs/vector3 center)
                                    radius))

(defn get-ray-collision-sphere
  "Get collision info between ray and sphere
  Ray ray
  Vector3 center
  float radius"
  ([^Arena arena ray center radius]
   (raylib_h/GetRayCollisionSphere arena
                                   (rstructs/ray arena ray)
                                   (rstructs/vector3 arena center)
                                   radius))
  ([ray center radius]
   (rstructs/ray-collision (raylib_h/GetRayCollisionSphere
                             rarena/*current-arena*
                             (rstructs/ray ray)
                             (rstructs/vector3 center)
                             radius))))

(defn get-ray-collision-box
  "Get collision info between ray and box
  Ray ray
  BoundingBox box"
  ([^Arena arena ray box]
   (raylib_h/GetRayCollisionBox arena
                                (rstructs/ray arena ray)
                                (rstructs/bounding-box arena box)))
  ([ray box]
   (rstructs/ray-collision (raylib_h/GetRayCollisionBox rarena/*current-arena*
                                                        (rstructs/ray ray)
                                                        (rstructs/bounding-box
                                                          box)))))

(defn get-ray-collision-mesh
  "Get collision info between ray and mesh
  Ray ray
  Mesh mesh
  Matrix transform"
  ([^Arena arena ray mesh transform]
   (raylib_h/GetRayCollisionMesh arena
                                 (rstructs/ray arena ray)
                                 (rstructs/mesh arena mesh)
                                 (rstructs/matrix arena transform)))
  ([ray mesh transform]
   (rstructs/ray-collision (raylib_h/GetRayCollisionMesh rarena/*current-arena*
                                                         (rstructs/ray ray)
                                                         (rstructs/mesh mesh)
                                                         (rstructs/matrix
                                                           transform)))))

(defn get-ray-collision-triangle
  "Get collision info between ray and triangle
  Ray ray
  Vector3 p1
  Vector3 p2
  Vector3 p3"
  ([^Arena arena ray p1 p2 p3]
   (raylib_h/GetRayCollisionTriangle arena
                                     (rstructs/ray arena ray)
                                     (rstructs/vector3 arena p1)
                                     (rstructs/vector3 arena p2)
                                     (rstructs/vector3 arena p3)))
  ([ray p1 p2 p3]
   (rstructs/ray-collision (raylib_h/GetRayCollisionTriangle
                             rarena/*current-arena*
                             (rstructs/ray ray)
                             (rstructs/vector3 p1)
                             (rstructs/vector3 p2)
                             (rstructs/vector3 p3)))))

(defn get-ray-collision-quad
  "Get collision info between ray and quad
  Ray ray
  Vector3 p1
  Vector3 p2
  Vector3 p3
  Vector3 p4"
  ([^Arena arena ray p1 p2 p3 p4]
   (raylib_h/GetRayCollisionQuad arena
                                 (rstructs/ray arena ray)
                                 (rstructs/vector3 arena p1)
                                 (rstructs/vector3 arena p2)
                                 (rstructs/vector3 arena p3)
                                 (rstructs/vector3 arena p4)))
  ([ray p1 p2 p3 p4]
   (rstructs/ray-collision (raylib_h/GetRayCollisionQuad rarena/*current-arena*
                                                         (rstructs/ray ray)
                                                         (rstructs/vector3 p1)
                                                         (rstructs/vector3 p2)
                                                         (rstructs/vector3 p3)
                                                         (rstructs/vector3
                                                           p4)))))

(defn init-audio-device
  "Initialize audio device and context"
  []
  (raylib_h/InitAudioDevice))

(defn close-audio-device
  "Close the audio device and context"
  []
  (raylib_h/CloseAudioDevice))

(defn audio-device-ready?
  "Check if audio device has been initialized successfully"
  []
  (raylib_h/IsAudioDeviceReady))

(defn set-master-volume
  "Set master volume (listener)
  float volume"
  [volume]
  (raylib_h/SetMasterVolume volume))

(defn load-wave
  "Load wave data from file
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/LoadWave arena (string arena file-name)))
  ([file-name]
   (rstructs/wave (raylib_h/LoadWave rarena/*current-arena*
                                     (string file-name)))))

(defn load-wave-from-memory
  "Load wave from memory buffer, fileType refers to extension: i.e. '.wav'
  const char * fileType
  const unsigned char * fileData
  int dataSize"
  ([^Arena arena file-type file-data data-size]
   (raylib_h/LoadWaveFromMemory arena
                                (string arena file-type)
                                file-data
                                data-size))
  ([file-type file-data data-size]
   (rstructs/wave (raylib_h/LoadWaveFromMemory rarena/*current-arena*
                                               (string file-type)
                                               file-data
                                               data-size))))

(defn wave-ready?
  "Checks if wave data is ready
  Wave wave"
  [wave]
  (raylib_h/IsWaveReady (rstructs/wave wave)))

(defn load-sound
  "Load sound from file
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/LoadSound arena (string arena file-name)))
  ([file-name]
   (rstructs/sound (raylib_h/LoadSound rarena/*current-arena*
                                       (string file-name)))))

(defn load-sound-from-wave
  "Load sound from wave data
  Wave wave"
  ([^Arena arena wave]
   (raylib_h/LoadSoundFromWave arena (rstructs/wave arena wave)))
  ([wave]
   (rstructs/sound (raylib_h/LoadSoundFromWave rarena/*current-arena*
                                               (rstructs/wave wave)))))

(defn sound-ready?
  "Checks if a sound is ready
  Sound sound"
  [sound]
  (raylib_h/IsSoundReady (rstructs/sound sound)))

(defn update-sound
  "Update sound buffer with new data
  Sound sound
  const void * data
  int sampleCount"
  [sound data sample-count]
  (raylib_h/UpdateSound (rstructs/sound sound) data sample-count))

(defn unload-wave
  "Unload wave data
  Wave wave"
  [wave]
  (raylib_h/UnloadWave (rstructs/wave wave)))

(defn unload-sound
  "Unload sound
  Sound sound"
  [sound]
  (raylib_h/UnloadSound (rstructs/sound sound)))

(defn export-wave?
  "Export wave data to file, returns true on success
  Wave wave
  const char * fileName"
  ([^Arena arena wave file-name]
   (raylib_h/ExportWave (rstructs/wave arena wave) (string arena file-name)))
  ([wave file-name]
   (raylib_h/ExportWave (rstructs/wave wave) (string file-name))))

(defn export-wave-as-code?
  "Export wave sample data to code (.h), returns true on success
  Wave wave
  const char * fileName"
  ([^Arena arena wave file-name]
   (raylib_h/ExportWaveAsCode (rstructs/wave arena wave)
                              (string arena file-name)))
  ([wave file-name]
   (raylib_h/ExportWaveAsCode (rstructs/wave wave) (string file-name))))

(defn play-sound
  "Play a sound
  Sound sound"
  [sound]
  (raylib_h/PlaySound (rstructs/sound sound)))

(defn stop-sound
  "Stop playing a sound
  Sound sound"
  [sound]
  (raylib_h/StopSound (rstructs/sound sound)))

(defn pause-sound
  "Pause a sound
  Sound sound"
  [sound]
  (raylib_h/PauseSound (rstructs/sound sound)))

(defn resume-sound
  "Resume a paused sound
  Sound sound"
  [sound]
  (raylib_h/ResumeSound (rstructs/sound sound)))

(defn sound-playing?
  "Check if a sound is currently playing
  Sound sound"
  [sound]
  (raylib_h/IsSoundPlaying (rstructs/sound sound)))

(defn set-sound-volume
  "Set volume for a sound (1.0 is max level)
  Sound sound
  float volume"
  [sound volume]
  (raylib_h/SetSoundVolume (rstructs/sound sound) volume))

(defn set-sound-pitch
  "Set pitch for a sound (1.0 is base level)
  Sound sound
  float pitch"
  [sound pitch]
  (raylib_h/SetSoundPitch (rstructs/sound sound) pitch))

(defn set-sound-pan
  "Set pan for a sound (0.5 is center)
  Sound sound
  float pan"
  [sound pan]
  (raylib_h/SetSoundPan (rstructs/sound sound) pan))

(defn wave-copy
  "Copy a wave to a new wave
  Wave wave"
  ([^Arena arena wave]
   (raylib_h/WaveCopy arena (rstructs/wave arena wave)))
  ([wave]
   (rstructs/wave (raylib_h/WaveCopy rarena/*current-arena*
                                     (rstructs/wave wave)))))

(defn wave-crop
  "Crop a wave to defined samples range
  Wave * wave
  int initSample
  int finalSample"
  [wave init-sample final-sample]
  (raylib_h/WaveCrop wave init-sample final-sample))

(defn wave-format
  "Convert wave data to desired format
  Wave * wave
  int sampleRate
  int sampleSize
  int channels"
  [wave sample-rate sample-size channels]
  (raylib_h/WaveFormat wave sample-rate sample-size channels))

(defn load-wave-samples
  "Load samples data from wave as a 32bit float data array
  Wave wave"
  [wave]
  (raylib_h/LoadWaveSamples (rstructs/wave wave)))

(defn unload-wave-samples
  "Unload samples data loaded with LoadWaveSamples()
  float * samples"
  [samples]
  (raylib_h/UnloadWaveSamples samples))

(defn load-music-stream
  "Load music stream from file
  const char * fileName"
  ([^Arena arena file-name]
   (raylib_h/LoadMusicStream arena (string arena file-name)))
  ([file-name]
   (rstructs/music (raylib_h/LoadMusicStream rarena/*current-arena*
                                             (string file-name)))))

(defn load-music-stream-from-memory
  "Load music stream from data
  const char * fileType
  const unsigned char * data
  int dataSize"
  ([^Arena arena file-type data data-size]
   (raylib_h/LoadMusicStreamFromMemory arena
                                       (string arena file-type)
                                       data
                                       data-size))
  ([file-type data data-size]
   (rstructs/music (raylib_h/LoadMusicStreamFromMemory rarena/*current-arena*
                                                       (string file-type)
                                                       data
                                                       data-size))))

(defn music-ready?
  "Checks if a music stream is ready
  Music music"
  [music]
  (raylib_h/IsMusicReady (rstructs/music music)))

(defn unload-music-stream
  "Unload music stream
  Music music"
  [music]
  (raylib_h/UnloadMusicStream (rstructs/music music)))

(defn play-music-stream
  "Start music playing
  Music music"
  [music]
  (raylib_h/PlayMusicStream (rstructs/music music)))

(defn music-stream-playing?
  "Check if music is playing
  Music music"
  [music]
  (raylib_h/IsMusicStreamPlaying (rstructs/music music)))

(defn update-music-stream
  "Updates buffers for music streaming
  Music music"
  [music]
  (raylib_h/UpdateMusicStream (rstructs/music music)))

(defn stop-music-stream
  "Stop music playing
  Music music"
  [music]
  (raylib_h/StopMusicStream (rstructs/music music)))

(defn pause-music-stream
  "Pause music playing
  Music music"
  [music]
  (raylib_h/PauseMusicStream (rstructs/music music)))

(defn resume-music-stream
  "Resume playing paused music
  Music music"
  [music]
  (raylib_h/ResumeMusicStream (rstructs/music music)))

(defn seek-music-stream
  "Seek music to a position (in seconds)
  Music music
  float position"
  [music position]
  (raylib_h/SeekMusicStream (rstructs/music music) position))

(defn set-music-volume
  "Set volume for music (1.0 is max level)
  Music music
  float volume"
  [music volume]
  (raylib_h/SetMusicVolume (rstructs/music music) volume))

(defn set-music-pitch
  "Set pitch for a music (1.0 is base level)
  Music music
  float pitch"
  [music pitch]
  (raylib_h/SetMusicPitch (rstructs/music music) pitch))

(defn set-music-pan
  "Set pan for a music (0.5 is center)
  Music music
  float pan"
  [music pan]
  (raylib_h/SetMusicPan (rstructs/music music) pan))

(defn get-music-time-length
  "Get music time length (in seconds)
  Music music"
  [music]
  (raylib_h/GetMusicTimeLength (rstructs/music music)))

(defn get-music-time-played
  "Get current music time played (in seconds)
  Music music"
  [music]
  (raylib_h/GetMusicTimePlayed (rstructs/music music)))

(defn load-audio-stream
  "Load audio stream (to stream raw audio pcm data)
  unsigned int sampleRate
  unsigned int sampleSize
  unsigned int channels"
  ([^Arena arena sample-rate sample-size channels]
   (raylib_h/LoadAudioStream arena sample-rate sample-size channels))
  ([sample-rate sample-size channels]
   (rstructs/audio-stream (raylib_h/LoadAudioStream rarena/*current-arena*
                                                    sample-rate
                                                    sample-size
                                                    channels))))

(defn audio-stream-ready?
  "Checks if an audio stream is ready
  AudioStream stream"
  [stream]
  (raylib_h/IsAudioStreamReady (rstructs/audio-stream stream)))

(defn unload-audio-stream
  "Unload audio stream and free memory
  AudioStream stream"
  [stream]
  (raylib_h/UnloadAudioStream (rstructs/audio-stream stream)))

(defn update-audio-stream
  "Update audio stream buffers with data
  AudioStream stream
  const void * data
  int frameCount"
  [stream data frame-count]
  (raylib_h/UpdateAudioStream (rstructs/audio-stream stream) data frame-count))

(defn audio-stream-processed?
  "Check if any audio stream buffers requires refill
  AudioStream stream"
  [stream]
  (raylib_h/IsAudioStreamProcessed (rstructs/audio-stream stream)))

(defn play-audio-stream
  "Play audio stream
  AudioStream stream"
  [stream]
  (raylib_h/PlayAudioStream (rstructs/audio-stream stream)))

(defn pause-audio-stream
  "Pause audio stream
  AudioStream stream"
  [stream]
  (raylib_h/PauseAudioStream (rstructs/audio-stream stream)))

(defn resume-audio-stream
  "Resume audio stream
  AudioStream stream"
  [stream]
  (raylib_h/ResumeAudioStream (rstructs/audio-stream stream)))

(defn audio-stream-playing?
  "Check if audio stream is playing
  AudioStream stream"
  [stream]
  (raylib_h/IsAudioStreamPlaying (rstructs/audio-stream stream)))

(defn stop-audio-stream
  "Stop audio stream
  AudioStream stream"
  [stream]
  (raylib_h/StopAudioStream (rstructs/audio-stream stream)))

(defn set-audio-stream-volume
  "Set volume for audio stream (1.0 is max level)
  AudioStream stream
  float volume"
  [stream volume]
  (raylib_h/SetAudioStreamVolume (rstructs/audio-stream stream) volume))

(defn set-audio-stream-pitch
  "Set pitch for audio stream (1.0 is base level)
  AudioStream stream
  float pitch"
  [stream pitch]
  (raylib_h/SetAudioStreamPitch (rstructs/audio-stream stream) pitch))

(defn set-audio-stream-pan
  "Set pan for audio stream (0.5 is centered)
  AudioStream stream
  float pan"
  [stream pan]
  (raylib_h/SetAudioStreamPan (rstructs/audio-stream stream) pan))

(defn set-audio-stream-buffer-size-default
  "Default size for new audio streams
  int size"
  [size]
  (raylib_h/SetAudioStreamBufferSizeDefault size))

(defn set-audio-stream-callback
  "Audio thread callback to request new data
  AudioStream stream
  AudioCallback callback"
  [stream callback]
  (raylib_h/SetAudioStreamCallback (rstructs/audio-stream stream) callback))

(defn attach-audio-stream-processor
  "Attach audio stream processor to stream
  AudioStream stream
  AudioCallback processor"
  [stream processor]
  (raylib_h/AttachAudioStreamProcessor (rstructs/audio-stream stream)
                                       processor))

(defn detach-audio-stream-processor
  "Detach audio stream processor from stream
  AudioStream stream
  AudioCallback processor"
  [stream processor]
  (raylib_h/DetachAudioStreamProcessor (rstructs/audio-stream stream)
                                       processor))

(defn attach-audio-mixed-processor
  "Attach audio stream processor to the entire audio pipeline
  AudioCallback processor"
  [processor]
  (raylib_h/AttachAudioMixedProcessor processor))

(defn detach-audio-mixed-processor
  "Detach audio stream processor from the entire audio pipeline
  AudioCallback processor"
  [processor]
  (raylib_h/DetachAudioMixedProcessor processor))

