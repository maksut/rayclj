(ns raylib.text "Font Loading and Text Drawing Functions (Module: text)"
    (:require
     [raylib.core :as rcore]
     [raylib.structs :as rstructs]
     [raylib.arena :as rarena])
    (:import [raylib raylib_h]
             [java.lang.foreign Arena]))

;; Font loading/unloading functions
; RLAPI Font GetFontDefault(void);                                                            ;; Get the default Font

; RLAPI Font LoadFont(const char *fileName);                                                  ;; Load font from file into GPU memory (VRAM)
(defn load-font!
  "Load font from file into GPU memory (VRAM)"
  ([^Arena arena file-name] (raylib_h/LoadFont arena (rcore/string arena file-name)))
  ([file-name]
   (rstructs/get-font
    (raylib_h/LoadFont rarena/*current-arena* (rcore/string file-name)))))

; RLAPI Font LoadFontEx(const char *fileName, int fontSize, int *fontChars, int glyphCount);  ;; Load font from file with extended parameters, use NULL for fontChars and 0 for glyphCount to load the default character set
; RLAPI Font LoadFontFromImage(Image image, Color key, int firstChar);                        ;; Load font from Image (XNA style)
; RLAPI Font LoadFontFromMemory(const char *fileType, const unsigned char *fileData, int dataSize, int fontSize, int *fontChars, int glyphCount); ;; Load font from memory buffer, fileType refers to extension: i.e. '.ttf'
; RLAPI bool IsFontReady(Font font);                                                          ;; Check if a font is ready
; RLAPI GlyphInfo *LoadFontData(const unsigned char *fileData, int dataSize, int fontSize, int *fontChars, int glyphCount, int type); ;; Load font data for further use
; RLAPI Image GenImageFontAtlas(const GlyphInfo *chars, Rectangle **recs, int glyphCount, int fontSize, int padding, int packMethod); ;; Generate image font atlas using chars info
; RLAPI void UnloadFontData(GlyphInfo *chars, int glyphCount);                                ;; Unload font chars info data (RAM)

; RLAPI void UnloadFont(Font font);                                                           ;; Unload font from GPU memory (VRAM)
(defn unload-font!
  "Unload font from GPU memory (VRAM)"
  [font] (raylib_h/UnloadFont (rstructs/font font)))

; RLAPI bool ExportFontAsCode(Font font, const char *fileName);                               ;; Export font as code file, returns true on success

;; Text drawing functions
(defn draw-fps!
  "Draw current FPS"
  [pos-x pos-y]
  (raylib_h/DrawFPS pos-x pos-y))

(defn draw-fps'!
  "Draw current FPS"
  [pos-x pos-y]
  ; implemeting our own DrawFPS because raylib's GetFPS has a static variable
  ; it hangs on fps value from previous windows
  (let [fps (rcore/get-fps'!)
        color (cond
                (< fps 15) :red
                (< fps 30) :orange
                :else :lime)
        fps (rcore/string (format "%2d FPS" fps))]
    (raylib_h/DrawText fps pos-x pos-y 20 (rstructs/color color))))

(defn draw-text!
  "Draw text (using default font)"
  [text x y font-size color] (raylib_h/DrawText (rcore/string text) x y font-size (rstructs/color color)))

; RLAPI void DrawTextEx(Font font, const char *text, Vector2 position, float fontSize, float spacing, Color tint); ;; Draw text using font and additional parameters
; RLAPI void DrawTextPro(Font font, const char *text, Vector2 position, Vector2 origin, float rotation, float fontSize, float spacing, Color tint); ;; Draw text using Font and pro parameters (rotation)
; RLAPI void DrawTextCodepoint(Font font, int codepoint, Vector2 position, float fontSize, Color tint); ;; Draw one character (codepoint)
; RLAPI void drawTextCodepoints(Font font, const int *codepoints, int count, Vector2 position, float fontSize, float spacing, Color tint); ;; Draw multiple character (codepoint)

;; Text font info functions
; RLAPI int MeasureText(const char *text, int fontSize);                                      ;; Measure string width for default font
; RLAPI Vector2 MeasureTextEx(Font font, const char *text, float fontSize, float spacing);    ;; Measure string size for Font
; RLAPI int GetGlyphIndex(Font font, int codepoint);                                          ;; Get glyph index position in font for a codepoint (unicode character), fallback to '?' if not found
; RLAPI GlyphInfo GetGlyphInfo(Font font, int codepoint);                                     ;; Get glyph font info data for a codepoint (unicode character), fallback to '?' if not found
; RLAPI Rectangle GetGlyphAtlasRec(Font font, int codepoint);                                 ;; Get glyph rectangle in font atlas for a codepoint (unicode character), fallback to '?' if not found

;; Text codepoints management functions (unicode characters)
; RLAPI char *LoadUTF8(const int *codepoints, int length);                ;; Load UTF-8 text encoded from codepoints array
; RLAPI void UnloadUTF8(char *text);                                      ;; Unload UTF-8 text encoded from codepoints array
; RLAPI int *LoadCodepoints(const char *text, int *count);                ;; Load all codepoints from a UTF-8 text string, codepoints count returned by parameter
; RLAPI void UnloadCodepoints(int *codepoints);                           ;; Unload codepoints data from memory
; RLAPI int GetCodepointCount(const char *text);                          ;; Get total number of codepoints in a UTF-8 encoded string
; RLAPI int GetCodepoint(const char *text, int *codepointSize);           ;; Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
; RLAPI int GetCodepointNext(const char *text, int *codepointSize);       ;; Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
; RLAPI int GetCodepointPrevious(const char *text, int *codepointSize);   ;; Get previous codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
; RLAPI const char *CodepointToUTF8(int codepoint, int *utf8Size);        ;; Encode one codepoint into UTF-8 byte array (array length returned as parameter)

;; Text strings management functions (no UTF-8 strings, only byte chars)
;; NOTE: Some strings allocate memory internally for returned strings, just be careful!
; RLAPI int TextCopy(char *dst, const char *src);                                             ;; Copy one string to another, returns bytes copied
; RLAPI bool TextIsEqual(const char *text1, const char *text2);                               ;; Check if two text string are equal
; RLAPI unsigned int TextLength(const char *text);                                            ;; Get text length, checks for '\0' ending
; RLAPI const char *TextFormat(const char *text, ...);                                        ;; Text formatting with variables (sprintf() style)
; RLAPI const char *TextSubtext(const char *text, int position, int length);                  ;; Get a piece of a text string
; RLAPI char *TextReplace(char *text, const char *replace, const char *by);                   ;; Replace text string (WARNING: memory must be freed!)
; RLAPI char *TextInsert(const char *text, const char *insert, int position);                 ;; Insert text in a position (WARNING: memory must be freed!)
; RLAPI const char *TextJoin(const char **textList, int count, const char *delimiter);        ;; Join text strings with delimiter
; RLAPI const char **TextSplit(const char *text, char delimiter, int *count);                 ;; Split text into multiple strings
; RLAPI void TextAppend(char *text, const char *append, int *position);                       ;; Append text at specific position and move cursor!
; RLAPI int TextFindIndex(const char *text, const char *find);                                ;; Find first text occurrence within a string
; RLAPI const char *TextToUpper(const char *text);                      ;; Get upper case version of provided string
; RLAPI const char *TextToLower(const char *text);                      ;; Get lower case version of provided string
; RLAPI const char *TextToPascal(const char *text);                     ;; Get Pascal case notation version of provided string
; RLAPI int TextToInteger(const char *text);                            ;; Get integer value from text (negative values not supported)
