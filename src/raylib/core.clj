(ns raylib.core
  (:require [raylib.arena :as rarena]
            [raylib.enums :as renums]
            [raylib.structs :as rstructs])
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

;;------------------------------------------------------------------------------------
;; Window and Graphics Device Functions (Module: core)
;;------------------------------------------------------------------------------------

(defn init-window!
  "Initialize window and OpenGL context"
  [width height title] (raylib_h/InitWindow width height (string title)))

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

(defn set-window-position!
  "Set window position on screen (only PLATFORM_DESKTOP)"
  [x y] (raylib_h/SetWindowPosition x y))

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
  [color] (raylib_h/ClearBackground (rstructs/color color)))

(defn begin-drawing!
  "Setup canvas (framebuffer) to start drawing"
  [] (raylib_h/BeginDrawing))

(defn end-drawing!
  "End canvas drawing and swap buffers (double buffering)"
  [] (raylib_h/EndDrawing))

(defmacro with-drawing! [& body]
  `(binding [rarena/*current-arena* (rarena/confined-arena!)]
     (try
       (begin-drawing!)
       ~@body
       (end-drawing!)
       (finally
         (.close rarena/*current-arena*)))))

(defn begin-mode-3d!
  "Begin 3D mode with custom camera (3D)"
  [camera] (raylib_h/BeginMode3D (rstructs/camera-3d camera)))

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

(defn set-target-fps!
  "Set target FPS (maximum)"
  [fps] (raylib_h/SetTargetFPS fps))

(defn get-fps!
  "Get current FPS"
  [] (raylib_h/GetFPS))

(defn get-frame-time!
  "Get time in seconds for last frame drawn (delta time)"
  [] (raylib_h/GetFrameTime))

(defn get-time!
  "Get elapsed time in seconds since InitWindow()"
  [] (raylib_h/GetTime))

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

(defn get-gamepad-name!
  "Get gamepad internal name id"
  [gamepad] (raylib_h/GetGamepadName gamepad))

(defn gamepad-button-down?
  "Check if a gamepad button is being pressed"
  [gamepad button] (raylib_h/IsGamepadButtonDown gamepad (renums/gamepad-button button)))

(defn gamepad-button-released?
  "Check if a gamepad button has been released once"
  [gamepad button] (raylib_h/IsGamepadButtonReleased gamepad (renums/gamepad-button button)))

(defn gamepad-button-up?
  "Check if a gamepad button is NOT being pressed"
  [gamepad button] (raylib_h/IsGamepadButtonUp gamepad (renums/gamepad-button button)))

(defn get-gamepad-button-pressed!
  "Get the last gamepad button pressed"
  [] (renums/gamepad-button (raylib_h/GetGamepadButtonPressed)))

(defn get-gamepad-axis-count!
  "Get gamepad axis count for a gamepad"
  [gamepad] (raylib_h/GetGamepadAxisCount gamepad))

(defn get-gamepad-axis-movement!
  "Get axis movement value for a gamepad axis"
  [gamepad axis] (raylib_h/GetGamepadAxisMovement gamepad (renums/gamepad-axis axis)))

(defn set-gamepad-mappings!
  "Set internal gamepad mappings (SDL_GameControllerDB)"
  [mappings] (raylib_h/SetGamepadMappings mappings))

;; Input-related functions: mouse
(defn mouse-button-pressed?
  "Check if a mouse button has been pressed once"
  [button] (raylib_h/IsMouseButtonPressed (renums/mouse-button button)))

(defn mouse-button-down?
  "Check if a mouse button is being pressed"
  [button] (raylib_h/IsMouseButtonDown (renums/mouse-button button)))

(defn mouse-button-released?
  "Check if a mouse button has been released once"
  [button] (raylib_h/IsMouseButtonReleased (renums/mouse-button button)))

(defn mouse-button-up?
  "Check if a mouse button is NOT being pressed"
  [button] (raylib_h/IsMouseButtonUp (renums/mouse-button button)))

(defn get-mouse-x!
  "Get mouse position X"
  [] (raylib_h/GetMouseX))

(defn get-mouse-y!
  "Get mouse position Y"
  [] (raylib_h/GetMouseY))

(defn get-mouse-position!
  "Get mouse position XY"
  ([^Arena arena] (raylib_h/GetMousePosition arena))
  ([] (rstructs/get-vector2 (raylib_h/GetMousePosition rarena/*current-arena*))))

(defn get-mouse-delta!
  "Get mouse delta between frames"
  ([^Arena arena] (raylib_h/GetMouseDelta arena))
  ([] (rstructs/get-vector2 (raylib_h/GetMouseDelta rarena/*current-arena*))))

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
  ([] (rstructs/get-vector2 (raylib_h/GetMouseWheelMoveV rarena/*current-arena*))))

(defn set-mouse-cursor!
  "Set mouse cursor"
  [cursor] (raylib_h/SetMouseCursor (renums/mouse-cursor cursor)))

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
  ([index] (rstructs/get-vector2 (raylib_h/GetTouchPosition rarena/*current-arena* index))))

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
