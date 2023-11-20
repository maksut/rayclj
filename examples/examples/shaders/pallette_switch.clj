(ns examples.shaders.pallette-switch
  (:require
   [rayclj.raylib.functions :as rl]
   [rayclj.memory :as memory]))

(comment
  (rl/init-window 800 450 "raylib [shaders] example - color palette switch")

  (let [shader (rl/load-shader nil "resources/shaders/glsl330/palette_switch.fs")
        palette-loc (rl/get-shader-location shader "palette")]
    (rl/unload-shader shader)
    palette-loc)

  (rl/close-window))

(def palettes
  [(memory/int-array
    [;; 3-BIT RGB
     0, 0, 0,
     255, 0, 0,
     0, 255, 0,
     0, 0, 255,
     0, 255, 255,
     255, 0, 255,
     255, 255, 0,
     255, 255, 255])
   (memory/int-array
    [;; AMMO-8 (GameBoy-like)
     4, 12, 6,
     17, 35, 24,
     30, 58, 41,
     48, 93, 66,
     77, 128, 97,
     137, 162, 87,
     190, 220, 127,
     238, 255, 204])
   (memory/int-array
    [;; RKBV (2-strip film)
     21, 25, 26,
     138, 76, 88,
     217, 98, 117,
     230, 184, 193,
     69, 107, 115,
     75, 151, 166,
     165, 189, 194,
     255, 245, 247])])

(def MAX_PALETTES            3)
(def COLORS_PER_PALETTE      8)
(def VALUES_PER_COLOR        3)

(let [screen-width 800 screen-height 450]

  ; Initialization
  (rl/init-window screen-width screen-height "raylib [shaders] example - color palette switch")

   ; Load shader to be used on some parts drawing
   ; NOTE: Defining 0 (NULL) for vertex shader forces usage of internal default vertex shader
  (let [shader (rl/load-shader memory/null "examples/examples/shaders/resources/glsl330/palette_switch.fs")
        palette-loc (rl/get-shader-location shader "palette")
        current-palette 0;
        line-height (/ screen-height COLORS_PER_PALETTE)]

    (println "shader" shader)
    (println "palette-loc" palette-loc)

    (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

    ;; Main game loop
    (while (not (rl/window-should-close?)) ;; Detect window close button or ESC key
      ;; Update
      ;; Send new value to the shader to be used on drawing.
      ;; NOTE: We are sending RGB triplets w/o the alpha channel
      ;; TODO: add enums to this function SHADER_UNIFORM_IVEC3 is 6
      (rl/set-shader-value-v shader palette-loc (nth palettes current-palette) 6 COLORS_PER_PALETTE);

      ;; Draw
      (rl/with-drawing
        (rl/clear-background :white)

        (rl/begin-shader-mode shader)

        (loop [i 0]
          (rl/draw-rectangle 0 (* line-height i) (rl/get-screen-width) line-height {:r i :g i :b i :a 255})
          (when (< i COLORS_PER_PALETTE)
            (recur (inc i))))
;                 for (int i = 0; i < COLORS_PER_PALETTE; i++)
;                 {
;                     // Draw horizontal screen-wide rectangles with increasing "palette index"
;                     // The used palette index is encoded in the RGB components of the pixel
;                     DrawRectangle(0, lineHeight*i, GetScreenWidth(), lineHeight, (Color){ i, i, i, 255 });
;                 }
;
        (rl/end-shader-mode)))

        ; (rl/draw-text "Hello, World!" 190 200 20 :lightgray)))

    ;; De-Initialization
    (rl/unload-shader shader) ;; Unload shader
    (rl/close-window))) ;; Close window and OpenGL context

; #if defined(PLATFORM_DESKTOP)
;     #define GLSL_VERSION            330
; #else   // PLATFORM_ANDROID, PLATFORM_WEB
;     #define GLSL_VERSION            100
; #endif
;
; #define MAX_PALETTES            3
; #define COLORS_PER_PALETTE      8
; #define VALUES_PER_COLOR        3
;
; static const int palettes[MAX_PALETTES][COLORS_PER_PALETTE*VALUES_PER_COLOR] = {
;     {   // 3-BIT RGB
;         0, 0, 0,
;         255, 0, 0,
;         0, 255, 0,
;         0, 0, 255,
;         0, 255, 255,
;         255, 0, 255,
;         255, 255, 0,
;         255, 255, 255,
;     },
;     {   // AMMO-8 (GameBoy-like)
;         4, 12, 6,
;         17, 35, 24,
;         30, 58, 41,
;         48, 93, 66,
;         77, 128, 97,
;         137, 162, 87,
;         190, 220, 127,
;         238, 255, 204,
;     },
;     {   // RKBV (2-strip film)
;         21, 25, 26,
;         138, 76, 88,
;         217, 98, 117,
;         230, 184, 193,
;         69, 107, 115,
;         75, 151, 166,
;         165, 189, 194,
;         255, 245, 247,
;     }
; };
;
; static const char *paletteText[] = {
;     "3-BIT RGB",
;     "AMMO-8 (GameBoy-like)",
;     "RKBV (2-strip film)"
; };
;
; //------------------------------------------------------------------------------------
; // Program main entry point
; //------------------------------------------------------------------------------------
; int main(void)
; {
;     // Initialization
;     //--------------------------------------------------------------------------------------
;     const int screenWidth = 800;
;     const int screenHeight = 450;
;
;     InitWindow(screenWidth, screenHeight, "raylib [shaders] example - color palette switch");
;
;     // Load shader to be used on some parts drawing
;     // NOTE 1: Using GLSL 330 shader version, on OpenGL ES 2.0 use GLSL 100 shader version
;     // NOTE 2: Defining 0 (NULL) for vertex shader forces usage of internal default vertex shader
;     Shader shader = LoadShader(0, TextFormat("resources/shaders/glsl%i/palette_switch.fs", GLSL_VERSION));
;
;     // Get variable (uniform) location on the shader to connect with the program
;     // NOTE: If uniform variable could not be found in the shader, function returns -1
;     int paletteLoc = GetShaderLocation(shader, "palette");
;
;     int currentPalette = 0;
;     int lineHeight = screenHeight/COLORS_PER_PALETTE;
;
;     SetTargetFPS(60);                       // Set our game to run at 60 frames-per-second
;     //--------------------------------------------------------------------------------------
;
;     // Main game loop
;     while (!WindowShouldClose())            // Detect window close button or ESC key
;     {
;         // Update
;         //----------------------------------------------------------------------------------
;         if (IsKeyPressed(KEY_RIGHT)) currentPalette++;
;         else if (IsKeyPressed(KEY_LEFT)) currentPalette--;
;
;         if (currentPalette >= MAX_PALETTES) currentPalette = 0;
;         else if (currentPalette < 0) currentPalette = MAX_PALETTES - 1;
;
;         // Send new value to the shader to be used on drawing.
;         // NOTE: We are sending RGB triplets w/o the alpha channel
;         SetShaderValueV(shader, paletteLoc, palettes[currentPalette], SHADER_UNIFORM_IVEC3, COLORS_PER_PALETTE);
;         //----------------------------------------------------------------------------------
;
;         // Draw
;         //----------------------------------------------------------------------------------
;         BeginDrawing();
;
;             ClearBackground(RAYWHITE);
;
;             BeginShaderMode(shader);
;
;                 for (int i = 0; i < COLORS_PER_PALETTE; i++)
;                 {
;                     // Draw horizontal screen-wide rectangles with increasing "palette index"
;                     // The used palette index is encoded in the RGB components of the pixel
;                     DrawRectangle(0, lineHeight*i, GetScreenWidth(), lineHeight, (Color){ i, i, i, 255 });
;                 }
;
;             EndShaderMode();
;
;             DrawText("< >", 10, 10, 30, DARKBLUE);
;             DrawText("CURRENT PALETTE:", 60, 15, 20, RAYWHITE);
;             DrawText(paletteText[currentPalette], 300, 15, 20, RED);
;
;             DrawFPS(700, 15);
;
;         EndDrawing();
;         //----------------------------------------------------------------------------------
;     }
;
;     // De-Initialization
;     //--------------------------------------------------------------------------------------
;     UnloadShader(shader);       // Unload shader
;
;     CloseWindow();              // Close window and OpenGL context
;     //--------------------------------------------------------------------------------------
;
;     return 0;
; }
