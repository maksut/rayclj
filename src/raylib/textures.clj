(ns raylib.textures "Texture Loading and Drawing Functions (Module: textures)"
    (:require [raylib.arena :as rarena]
              [raylib.core :as rcore]
              [raylib.structs :as rstructs])
    (:import [raylib raylib_h]
             [java.lang.foreign Arena ValueLayout MemorySegment]))

(set! *warn-on-reflection* true)

;; TODO: utility macro, move elsewhere
(defmacro apply-return-first [f & args]
  "applies f to args then returns value of first arg"
  `(let [first-arg# ~(first args)]
     (~f first-arg# ~@(rest args))
     first-arg#))

;; Image loading functions
;; NOTE: These functions do not require GPU access

(defn load-image!
  "Load image from file into CPU memory (RAM)"
  ([^Arena arena file-name] (raylib_h/LoadImage arena (rcore/string arena file-name)))
  ([file-name]
   (rstructs/get-image
    (raylib_h/LoadImage rarena/*current-arena* (rcore/string file-name)))))

(defn load-image-raw!
  "Load image from RAW file data"
  ([^Arena arena file-name width height format header-size]
   (raylib_h/LoadImageRaw arena (rcore/string arena file-name) width height format header-size))
  ([file-name width height format header-size]
   (rstructs/get-image
    (raylib_h/LoadImageRaw
     rarena/*current-arena*
     (rcore/string file-name)
     width
     height
     format
     header-size))))

; TODO: needs testing
(defn load-image-anim!
  "Load image sequence from file (frames appended to image.data)
   Load animated image data
     - Image.data buffer includes all frames: [image#0][image#1][image#2][...]
     - Number of frames is returned through 'frames' parameter
     - All frames are returned in RGBA format
     - Frames delay data is discarded"
  ([^Arena arena file-name]
   (let [frames-seg (.allocate arena ValueLayout/JAVA_INT)
         file-name (rcore/string arena file-name)
         image-seg (raylib_h/LoadImageAnim arena file-name frames-seg)
         frames (.get frames-seg ValueLayout/JAVA_INT 0)]
     [image-seg frames]))
  ([file-name]
   (let [[image-seg frames] (load-image-anim! rarena/*current-arena* file-name)]
     [(rstructs/get-image image-seg) frames])))

(defn load-image-from-memory!
  "Load image from memory buffer, fileType refers to extension: i.e. '.png'"
  ([^Arena arena file-type file-data data-size]
   (raylib_h/LoadImageFromMemory arena (rcore/string arena file-type) file-data data-size))
  ([file-type file-data data-size]
   (rstructs/get-image
    (raylib_h/LoadImageFromMemory rarena/*current-arena* (rcore/string file-type) file-data data-size))))

(defn load-image-from-texture!
  "Load image from GPU texture data"
  ([^Arena arena texture] (raylib_h/LoadImageFromTexture arena (rstructs/texture-2d arena texture)))
  ([texture]
   (rstructs/get-image
    (raylib_h/LoadImageFromTexture rarena/*current-arena* (rstructs/texture-2d texture)))))

(defn load-image-from-screen!
  "Load image from screen buffer and (screenshot)"
  ([^Arena arena] (raylib_h/LoadImageFromScreen arena))
  ([] (rstructs/get-image (raylib_h/LoadImageFromScreen rarena/*current-arena*))))

(defn is-image-ready?
  "Check if an image is ready"
  ([image] (raylib_h/IsImageReady (rstructs/image image))))

(defn unload-image!
  "Unload image from CPU memory (RAM)"
  ([image] (raylib_h/UnloadImage (rstructs/image image))))

(defn export-image?
  "Export image data to file, returns true on success"
  ([image file-name] (raylib_h/ExportImage (rstructs/image image) (rcore/string file-name))))

(defn export-image-as-code?
  "Export image as code file defining an array of bytes, returns true on success"
  ([image file-name] (raylib_h/ExportImageAsCode (rstructs/image image) (rcore/string file-name))))

;; Image generation functions
(defn gen-image-color!
  "Generate image: plain color"
  ([^Arena arena width height color]
   (raylib_h/GenImageColor arena width height (rstructs/color arena color)))
  ([width height color]
   (rstructs/get-image
    (raylib_h/GenImageColor rarena/*current-arena* width height (rstructs/color color)))))

(defn gen-image-gradient-v!
  "Generate image: vertical gradient"
  ([^Arena arena width height top-color bottom-color]
   (raylib_h/GenImageGradientV
    arena
    width
    height
    (rstructs/color arena top-color)
    (rstructs/color arena bottom-color)))
  ([width height top-color bottom-color]
   (rstructs/get-image (raylib_h/GenImageGradientV
                        rarena/*current-arena*
                        width
                        height
                        (rstructs/color top-color)
                        (rstructs/color bottom-color)))))

(defn gen-image-gradient-h!
  "Generate image: horizontal gradient"
  ([^Arena arena width height left-color right-color]
   (raylib_h/GenImageGradientH
    arena
    width
    height
    (rstructs/color arena left-color)
    (rstructs/color arena right-color)))
  ([width height left-color right-color]
   (rstructs/get-image (raylib_h/GenImageGradientH
                        rarena/*current-arena*
                        width
                        height
                        (rstructs/color left-color)
                        (rstructs/color right-color)))))

(defn gen-image-gradient-radial!
  "Generate image: radial gradient"
  ([^Arena arena width height density color-inner color-outer]
   (raylib_h/GenImageGradientRadial
    arena
    width
    height
    density
    (rstructs/color arena color-inner)
    (rstructs/color arena color-outer)))
  ([width height density color-inner color-outer]
   (rstructs/get-image (raylib_h/GenImageGradientRadial
                        rarena/*current-arena*
                        width
                        height
                        density
                        (rstructs/color color-inner)
                        (rstructs/color color-outer)))))

(defn gen-image-checked!
  "Generate image: checked"
  ([^Arena arena width height checks-x checks-y color1 color2]
   (raylib_h/GenImageChecked
    arena
    width
    height
    checks-x
    checks-y
    (rstructs/color arena color1)
    (rstructs/color arena color2)))
  ([width height checks-x checks-y color1 color2]
   (rstructs/get-image (raylib_h/GenImageChecked
                        rarena/*current-arena*
                        width
                        height
                        checks-x
                        checks-y
                        (rstructs/color color1)
                        (rstructs/color color2)))))

(defn gen-image-white-noise!
  "Generate image: white noise"
  ([^Arena arena width height factor]
   (raylib_h/GenImageWhiteNoise arena width height factor))
  ([width height factor]
   (rstructs/get-image (raylib_h/GenImageWhiteNoise rarena/*current-arena* width height factor))))

(defn gen-image-perlin-noise!
  "Generate image: perlin noise"
  ([^Arena arena width height offset-x offset-y scale]
   (raylib_h/GenImagePerlinNoise arena width height offset-x offset-y scale))
  ([width height offset-x offset-y scale]
   (rstructs/get-image (raylib_h/GenImagePerlinNoise rarena/*current-arena* width height offset-x offset-y scale))))

(defn gen-image-cellular!
  "Generate image: cellular algorithm, bigger tileSize means bigger cells"
  ([^Arena arena width height tile-size]
   (raylib_h/GenImageCellular arena width height tile-size))
  ([width height tile-size]
   (rstructs/get-image (raylib_h/GenImageCellular rarena/*current-arena* width height tile-size))))

(defn gen-image-text!
  "Generate image: grayscale image from text data"
  ([^Arena arena width height text]
   (raylib_h/GenImageText arena width height (rcore/string arena text)))
  ([width height text]
   (rstructs/get-image (raylib_h/GenImageText rarena/*current-arena* width height (rcore/string text)))))

;; Image manipulation functions
(defn image-copy!
  "Create an image duplicate (useful for transformations)"
  ([^Arena arena image] (raylib_h/ImageCopy arena (rstructs/image image)))
  ([image] (rstructs/get-image (raylib_h/ImageCopy rarena/*current-arena* (rstructs/image image)))))

(defn image-from-image!
  "Create an image from another image piece"
  ([^Arena arena image rectangle]
   (raylib_h/ImageFromImage arena (rstructs/image arena image) (rstructs/rectangle arena rectangle)))
  ([image rectangle]
   (raylib_h/ImageFromImage rarena/*current-arena* (rstructs/image image) (rstructs/rectangle rectangle))))

(defn image-text!
  "Create an image from another image piece"
  ([^Arena arena text font-size color]
   (raylib_h/ImageText arena (rcore/string arena text) font-size (rstructs/color arena color)))
  ([text font-size color]
   (rstructs/get-image
    (raylib_h/ImageText rarena/*current-arena* (rcore/string text) font-size (rstructs/color color)))))

(defn image-text-ex!
  "Create an image from text (custom sprite font)"
  ([^Arena arena font text font-size spacing tint-color]
   (raylib_h/ImageTextEx
    arena
    (rstructs/font arena font)
    (rcore/string arena text)
    font-size
    spacing
    (rstructs/color arena tint-color)))
  ([font text font-size spacing tint-color]
   (rstructs/get-image
    (raylib_h/ImageTextEx
     rarena/*current-arena*
     (rstructs/font font)
     (rcore/string text)
     font-size
     spacing
     (rstructs/color tint-color)))))

(defn image-format!
  "Convert image data to desired format"
  ([^Arena arena image new-format]
   (apply-return-first raylib_h/ImageFormat (rstructs/image arena image) new-format))
  ([image new-format]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageFormat (rstructs/image image) new-format))))

(defn image-to-pot!
  "Convert image to POT (power-of-two)"
  ([^Arena arena image fill-color]
   (apply-return-first raylib_h/ImageToPOT (rstructs/image arena image) (rstructs/color arena fill-color)))
  ([image fill-color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageToPOT (rstructs/image image) (rstructs/color fill-color)))))

(defn image-crop!
  "Crop an image to a defined rectangle"
  ([^Arena arena image rectangle-crop]
   (apply-return-first raylib_h/ImageCrop (rstructs/image arena image) (rstructs/rectangle arena rectangle-crop)))
  ([image rectangle-crop]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageCrop (rstructs/image image) (rstructs/rectangle rectangle-crop)))))

(defn image-alpha-crop!
  "Crop image depending on alpha value"
  ([^Arena arena image threshold]
   (apply-return-first raylib_h/ImageAlphaCrop (rstructs/image arena image) threshold))
  ([image threshold]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageAlphaCrop (rstructs/image image) threshold))))

(defn image-alpha-clear!
  "Clear alpha channel to desired color"
  ([^Arena arena image color threshold]
   (apply-return-first raylib_h/ImageAlphaClear (rstructs/image arena image) (rstructs/color arena color) threshold))
  ([image color threshold]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageAlphaClear (rstructs/image image) (rstructs/color color) threshold))))

(defn image-alpha-mask!
  "Apply alpha mask to image"
  ([^Arena arena image alpha-mask]
   (apply-return-first raylib_h/ImageAlphaMask (rstructs/image arena image) (rstructs/image arena alpha-mask)))
  ([image alpha-mask]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageAlphaMask (rstructs/image image) (rstructs/image alpha-mask)))))

(defn image-alpha-premultiply!
  "Premultiply alpha channel"
  ([^Arena arena image]
   (apply-return-first raylib_h/ImageAlphaPremultiply (rstructs/image arena image)))
  ([image]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageAlphaPremultiply (rstructs/image image)))))

(defn image-blur-gaussian!
  "Apply Gaussian blur using a box blur approximation"
  ([^Arena arena image blur-size]
   (apply-return-first raylib_h/ImageBlurGaussian (rstructs/image arena image) blur-size))
  ([image blur-size]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageBlurGaussian (rstructs/image image) blur-size))))

(defn image-resize!
  "Resize image (Bicubic scaling algorithm)"
  ([^Arena arena image new-width new-height]
   (apply-return-first raylib_h/ImageResize (rstructs/image arena image) new-width new-height))
  ([image new-width new-height]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageResize (rstructs/image image) new-width new-height))))

(defn image-resize-nn!
  "Resize image (Nearest-Neighbor scaling algorithm)"
  ([^Arena arena image new-width new-height]
   (apply-return-first raylib_h/ImageResizeNN (rstructs/image arena image) new-width new-height))
  ([image new-width new-height]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageResizeNN (rstructs/image image) new-width new-height))))

(defn image-resize-canvas!
  "Resize canvas and fill with color"
  ([^Arena arena image new-width new-height offset-x offset-y fill-color]
   (apply-return-first raylib_h/ImageResizeCanvas
                       (rstructs/image arena image)
                       new-width
                       new-height
                       offset-x
                       offset-y
                       (rstructs/color arena fill-color)))
  ([image new-width new-height offset-x offset-y fill-color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageResizeCanvas
                        (rstructs/image image)
                        new-width
                        new-height
                        offset-x
                        offset-y
                        (rstructs/color fill-color)))))

(defn image-mipmaps!
  "Compute all mipmap levels for a provided image"
  ([^Arena arena image]
   (apply-return-first raylib_h/ImageMipmaps (rstructs/image arena image)))
  ([image]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageMipmaps (rstructs/image image)))))

(defn image-dither!
  "Dither image data to 16bpp or lower (Floyd-Steinberg dithering)"
  ([^Arena arena image r-bpp g-bpp b-bpp a-bpp]
   (apply-return-first raylib_h/ImageDither (rstructs/image arena image) r-bpp g-bpp b-bpp a-bpp))
  ([image r-bpp g-bpp b-bpp a-bpp]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDither (rstructs/image image) r-bpp g-bpp b-bpp a-bpp))))

(defn image-flip-vertical!
  "Flip image vertically"
  ([^Arena arena image]
   (apply-return-first raylib_h/ImageFlipVertical (rstructs/image arena image)))
  ([image]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageFlipVertical (rstructs/image image)))))

(defn image-flip-horizontal!
  "Flip image horizontally"
  ([^Arena arena image]
   (apply-return-first raylib_h/ImageFlipHorizontal (rstructs/image arena image)))
  ([image]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageFlipHorizontal (rstructs/image image)))))

(defn image-rotate-cw!
  "Rotate image clockwise 90deg"
  ([^Arena arena image]
   (apply-return-first raylib_h/ImageRotateCW (rstructs/image arena image)))
  ([image]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageRotateCW (rstructs/image image)))))

(defn image-rotate-ccw!
  "Rotate image counter-clockwise 90deg"
  ([^Arena arena image]
   (apply-return-first raylib_h/ImageRotateCCW (rstructs/image arena image)))
  ([image]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageRotateCCW (rstructs/image image)))))

(defn image-color-tint!
  "Modify image color: tint"
  ([^Arena arena image color]
   (apply-return-first raylib_h/ImageColorTint (rstructs/image arena image) (rstructs/color arena color)))
  ([image color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageColorTint (rstructs/image image) (rstructs/color color)))))

(defn image-color-invert!
  "Modify image color: invert"
  ([^Arena arena image]
   (apply-return-first raylib_h/ImageColorInvert (rstructs/image arena image)))
  ([image]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageColorInvert (rstructs/image image)))))

(defn image-color-grayscale!
  "Modify image color: grayscale"
  ([^Arena arena image]
   (apply-return-first raylib_h/ImageColorGrayscale (rstructs/image arena image)))
  ([image]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageColorGrayscale (rstructs/image image)))))

(defn image-color-contrast!
  "Modify image color: contrast (-100 to 100)"
  ([^Arena arena image contrast]
   (apply-return-first raylib_h/ImageColorContrast (rstructs/image arena image) contrast))
  ([image contrast]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageColorContrast (rstructs/image image) contrast))))

(defn image-color-brightness!
  "Modify image color: brightness (-255 to 255)"
  ([^Arena arena image brightness]
   (apply-return-first raylib_h/ImageColorBrightness (rstructs/image arena image) brightness))
  ([image brightness]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageColorBrightness (rstructs/image image) brightness))))

(defn image-color-replace!
  "Modify image color: replace color"
  ([^Arena arena image color replace]
   (apply-return-first raylib_h/ImageColorReplace
                       (rstructs/image arena image)
                       (rstructs/color arena color)
                       (rstructs/color arena replace)))
  ([image color replace]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageColorReplace
                        (rstructs/image image)
                        (rstructs/color color)
                        (rstructs/color replace)))))

; TODO: test return value of this function
(defn load-image-colors!
  "Load color data from image as a Color array (RGBA - 32bit)"
  ([^Arena arena image] (raylib_h/LoadImageColors (rstructs/image arena image)))
  ([image]
   (let [image (rstructs/image image)
         {:keys [width height]} (rstructs/get-image image)
         colors (raylib_h/LoadImageColors image)] ; a color array of size=height*width
     (rstructs/color-array colors (* width height)))))

; TODO: test the function
; RLAPI Color *LoadImagePalette(Image image, int maxPaletteSize, int *colorCount);                         ;; Load colors palette from image as a Color array (RGBA - 32bit)
(defn load-image-palette!
  "Load colors palette from image as a Color array (RGBA - 32bit)"
  ([^Arena arena image max-palette-size]
   (let [color-count-seg (.allocate arena (ValueLayout/JAVA_INT))
         image (rstructs/image arena image)
         palette-seg (raylib_h/LoadImagePalette image max-palette-size color-count-seg) ; a color aray of size=max-palette-size
         color-count (.get color-count-seg ValueLayout/JAVA_INT 0)
         palette-seg (rstructs/color-array palette-seg color-count)]
     [palette-seg color-count]))
  ([image max-palette-size]
   (let [[palette-seg color-count] (load-image-palette! rarena/*current-arena* image max-palette-size)]
     (rstructs/color-array palette-seg color-count))))

(defn unload-image-colors!
  "Unload color data loaded with LoadImageColors()"
  [^MemorySegment color-array-seg] (raylib_h/UnloadImageColors color-array-seg))

(defn unload-image-palette!
  "Unload colors palette loaded with LoadImagePalette()"
  ([^MemorySegment color-array-seg] (raylib_h/UnloadImagePalette color-array-seg)))

(defn get-image-alpha-border!
  "Get image alpha border rectangle"
  ([^Arena arena image threshold] (raylib_h/GetImageAlphaBorder arena (rstructs/image arena image) threshold))
  ([image threshold]
   (let [image (rstructs/image image)
         rect-seg (raylib_h/GetImageAlphaBorder rarena/*current-arena* image threshold)]
     (rstructs/get-rectangle rect-seg))))

(defn get-image-color!
  "Get image pixel color at (x, y) position"
  ([^Arena arena image x y] (raylib_h/GetImageColor arena (rstructs/image arena image) x y))
  ([image x y]
   (let [image (rstructs/image image)
         color-seg (raylib_h/GetImageColor rarena/*current-arena* image x y)]
     (rstructs/get-color color-seg))))

;; Image drawing functions
;; NOTE: Image software-rendering functions (CPU)
(defn image-clear-background!
  "Clear image background with given color"
  ([^Arena arena image color]
   (apply-return-first raylib_h/ImageClearBackground (rstructs/image arena image) (rstructs/color arena color)))
  ([image color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageClearBackground (rstructs/image image) (rstructs/color color)))))

(defn image-draw-pixel!
  "Draw pixel within an image"
  ([^Arena arena image pos-x pos-y color]
   (apply-return-first raylib_h/ImageDrawPixel (rstructs/image arena image) pos-x pos-y (rstructs/color arena color)))
  ([image pos-x pos-y color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawPixel (rstructs/image image) pos-x pos-y (rstructs/color color)))))

(defn image-draw-pixel-v!
  "Draw pixel within an image (Vector version)"
  ([^Arena arena image position-v color]
   (apply-return-first
    raylib_h/ImageDrawPixelV
    (rstructs/image arena image)
    (rstructs/vector2 arena position-v)
    (rstructs/color arena color)))
  ([image position-v color]
   (rstructs/get-image
    (apply-return-first
     raylib_h/ImageDrawPixelV
     (rstructs/image image)
     (rstructs/vector2 position-v)
     (rstructs/color color)))))

(defn image-draw-line!
  "Draw line within an image"
  ([^Arena arena image start-pos-x start-pos-y end-pos-x end-pos-y color]
   (apply-return-first
    raylib_h/ImageDrawLine (rstructs/image arena image) start-pos-x start-pos-y end-pos-x end-pos-y (rstructs/color arena color)))
  ([image start-pos-x start-pos-y end-pos-x end-pos-y color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawLine
                        (rstructs/image image) start-pos-x start-pos-y end-pos-x end-pos-y (rstructs/color color)))))

(defn image-draw-line-v!
  "Draw line within an image (Vector version)"
  ([^Arena arena image start-v end-v color]
   (apply-return-first raylib_h/ImageDrawLineV
                       (rstructs/image arena image)
                       (rstructs/vector2 arena start-v)
                       (rstructs/vector2 arena end-v)
                       (rstructs/color arena color)))
  ([image start-v end-v color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawLineV
                        (rstructs/image image)
                        (rstructs/vector2 start-v)
                        (rstructs/vector2 end-v)
                        (rstructs/color color)))))

(defn image-draw-circle!
  "Draw a filled circle within an image"
  ([^Arena arena image center-x center-y radius color]
   (apply-return-first raylib_h/ImageDrawCircle (rstructs/image arena image) center-x center-y radius (rstructs/color arena color)))
  ([image center-x center-y radius color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawCircle (rstructs/image image) center-x center-y radius (rstructs/color color)))))

(defn image-draw-circle-v!
  "Draw a filled circle within an image (Vector version)"
  ([^Arena arena image center-v radius color]
   (apply-return-first raylib_h/ImageDrawCircleV
                       (rstructs/image arena image)
                       (rstructs/vector2 arena center-v)
                       radius
                       (rstructs/color arena color)))
  ([image center-v radius color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawCircleV
                        (rstructs/image image)
                        (rstructs/vector2 center-v)
                        radius
                        (rstructs/color color)))))

(defn image-draw-circle-lines!
  "Draw circle outline within an image"
  ([^Arena arena image center-x center-y radius color]
   (apply-return-first raylib_h/ImageDrawCircleLines
                       (rstructs/image arena image) center-x center-y radius (rstructs/color arena color)))
  ([image center-x center-y radius color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawCircleLines
                        (rstructs/image image) center-x center-y radius (rstructs/color color)))))

(defn image-draw-circle-lines-v!
  "Draw circle outline within an image (Vector version)"
  ([^Arena arena image center-v radius color]
   (apply-return-first raylib_h/ImageDrawCircleLinesV
                       (rstructs/image arena image)
                       (rstructs/vector2 arena center-v)
                       radius
                       (rstructs/color arena color)))
  ([image center-v radius color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawCircleLinesV
                        (rstructs/image image)
                        (rstructs/vector2 center-v)
                        radius
                        (rstructs/color color)))))

(defn image-draw-rectangle!
  "Draw rectangle within an image"
  ([^Arena arena image pos-x pos-y width height color]
   (apply-return-first raylib_h/ImageDrawRectangle
                       (rstructs/image arena image) pos-x pos-y width height (rstructs/color arena color)))
  ([image pos-x pos-y width height color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawRectangle
                        (rstructs/image image) pos-x pos-y width height (rstructs/color color)))))

(defn image-draw-rectangle-v!
  "Draw rectangle within an image (Vector version)"
  ([^Arena arena image pos-v size-v color]
   (apply-return-first raylib_h/ImageDrawRectangleV
                       (rstructs/image arena image)
                       (rstructs/vector2 arena pos-v)
                       (rstructs/vector2 arena size-v)
                       (rstructs/color arena color)))
  ([image pos-v size-v color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawRectangleV
                        (rstructs/image image)
                        (rstructs/vector2 pos-v)
                        (rstructs/vector2 size-v)
                        (rstructs/color color)))))

(defn image-draw-rectangle-rec!
  "Draw rectangle within an image"
  ([^Arena arena image rec color]
   (apply-return-first raylib_h/ImageDrawRectangleRec
                       (rstructs/image arena image)
                       (rstructs/rectangle arena rec)
                       (rstructs/color arena color)))
  ([image rec color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawRectangleRec
                        (rstructs/image image)
                        (rstructs/rectangle rec)
                        (rstructs/color color)))))

(defn image-draw-rectangle-lines!
  "Draw rectangle lines within an image"
  ([^Arena arena image rec thick color]
   (apply-return-first raylib_h/ImageDrawRectangleLines
                       (rstructs/image arena image)
                       (rstructs/rectangle arena rec)
                       thick
                       (rstructs/color arena color)))
  ([image rec thick color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawRectangleLines
                        (rstructs/image image)
                        (rstructs/rectangle rec)
                        thick
                        (rstructs/color color)))))

(defn image-draw!
  "Draw a source image within a destination image (tint applied to source)"
  ([^Arena arena image src-image src-rec dst-rec tint-color]
   (apply-return-first raylib_h/ImageDraw
                       (rstructs/image arena image)
                       (rstructs/image arena src-image)
                       (rstructs/rectangle arena src-rec)
                       (rstructs/rectangle arena dst-rec)
                       (rstructs/color arena tint-color)))
  ([image src-image src-rec dst-rec tint-color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDraw
                        (rstructs/image image)
                        (rstructs/image src-image)
                        (rstructs/rectangle src-rec)
                        (rstructs/rectangle dst-rec)
                        (rstructs/color tint-color)))))

(defn image-draw-text!
  "Draw text (using default font) within an image (destination)"
  ([^Arena arena image text pos-x pos-y font-size color]
   (apply-return-first raylib_h/ImageDrawText
                       (rstructs/image arena image)
                       (rcore/string arena text)
                       pos-x
                       pos-y
                       font-size
                       (rstructs/color arena color)))
  ([image text pos-x pos-y font-size color]
   (rstructs/get-image (apply-return-first
                        raylib_h/ImageDrawText
                        (rstructs/image image)
                        (rcore/string text)
                        pos-x
                        pos-y
                        font-size
                        (rstructs/color color)))))

(defn image-draw-text-ex!
  "Draw text (custom sprite font) within an image (destination)"
  ([^Arena arena image font text position-v font-size spacing tint-color]
   (apply-return-first raylib_h/ImageDrawTextEx
                       (rstructs/image arena image)
                       (rstructs/font font)
                       (rcore/string arena text)
                       (rstructs/vector2 position-v)
                       font-size
                       spacing
                       (rstructs/color arena tint-color)))
  ([image font text position-v font-size spacing tint-color]
   (rstructs/get-image
    (apply-return-first raylib_h/ImageDrawTextEx
                        (rstructs/image image)
                        (rstructs/font font)
                        (rcore/string text)
                        (rstructs/vector2 position-v)
                        font-size
                        spacing
                        (rstructs/color tint-color)))))

;; Texture loading functions
;; NOTE: These functions require GPU access

(defn load-texture!
  "Load texture from file into GPU memory (VRAM)"
  ([^Arena arena file-name]
   (raylib_h/LoadTexture arena (rcore/string arena file-name)))
  ([file-name]
   (rstructs/get-texture
    (raylib_h/LoadTexture rarena/*current-arena* (rcore/string file-name)))))

(defn load-texture-from-image!
  "Load texture from image data"
  ([^Arena arena image]
   (raylib_h/LoadTextureFromImage arena (rstructs/image arena image)))
  ([image]
   (rstructs/get-texture
    (raylib_h/LoadTextureFromImage rarena/*current-arena* (rstructs/image image)))))

(defn texture-cubemap!
  "Load cubemap from image, multiple image cubemap layouts supported"
  ([^Arena arena image layout] (raylib_h/LoadTextureCubemap arena (rstructs/image arena image) layout))
  ([image layout]
   (rstructs/get-texture
    (raylib_h/LoadTextureCubemap rarena/*current-arena* (rstructs/image image) layout))))

(defn load-render-texture!
  "Load texture for rendering (framebuffer)"
  ([^Arena arena width height] (raylib_h/LoadRenderTexture arena width height))
  ([width height]
   (rstructs/get-render-texture
    (raylib_h/LoadRenderTexture rarena/*current-arena* width height))))

(defn texture-ready?
  "Check if a texture is ready"
  ([^Arena arena texture] (raylib_h/IsTextureReady (rstructs/texture-2d arena texture)))
  ([texture] (raylib_h/IsTextureReady (rstructs/texture-2d texture))))

(defn unload-texture!
  "Unload texture from GPU memory (VRAM)"
  ([^Arena arena texture] (raylib_h/UnloadTexture (rstructs/texture-2d arena texture)))
  ([texture] (raylib_h/UnloadTexture (rstructs/texture-2d texture))))

(defn render-texture-ready?
  "Check if a render texture is ready"
  ([^Arena arena target] (raylib_h/IsRenderTextureReady (rstructs/render-texture-2d arena target)))
  ([target] (raylib_h/IsRenderTextureReady (rstructs/render-texture-2d target))))

(defn unload-render-texture!
  "Unload render texture from GPU memory (VRAM)"
  ([^Arena arena texture] (raylib_h/UnloadRenderTexture (rstructs/render-texture-2d arena texture)))
  ([texture] (raylib_h/UnloadRenderTexture (rstructs/render-texture-2d texture))))

(defn update-texture!
  "Update GPU texture with new data"
  ([^Arena arena texture pixels]
   (raylib_h/UpdateTexture (rstructs/texture-2d arena texture) pixels))
  ([texture pixels]
   (raylib_h/UpdateTexture (rstructs/texture-2d texture) pixels)))

(defn update-texture-rec!
  "Update GPU texture rectangle with new data"
  ([^Arena arena texture rec pixels]
   (raylib_h/UpdateTextureRec
    (rstructs/texture-2d arena texture)
    (rstructs/rectangle arena rec) pixels))
  ([texture rec pixels]
   (raylib_h/UpdateTextureRec (rstructs/texture-2d texture) (rstructs/rectangle rec) pixels)))

;; Texture configuration functions
(defn gen-texture-mipmaps!
  "Generate GPU mipmaps for a texture"
  ([^Arena arena texture] (raylib_h/GenTextureMipmaps (rstructs/texture-2d arena texture)))
  ([texture] (raylib_h/GenTextureMipmaps (rstructs/texture-2d texture))))

(defn set-texture-filter!
  "Set texture scaling filter mode"
  ([^Arena arena texture filter]
   (raylib_h/SetTextureFilter (rstructs/texture-2d arena texture) filter))
  ([texture filter]
   (raylib_h/SetTextureFilter (rstructs/texture-2d texture) filter)))

(defn set-texture-wrap!
  "Set texture wrapping mode"
  ([^Arena arena texture wrap]
   (raylib_h/SetTextureWrap (rstructs/texture-2d arena texture) wrap))
  ([texture wrap]
   (raylib_h/SetTextureWrap (rstructs/texture-2d texture) wrap)))

;; Texture drawing functions
(defn draw-texture!
  "Draw a Texture2D"
  ([^Arena arena texture x y tint]
   (raylib_h/DrawTexture (rstructs/texture-2d arena texture) x y (rstructs/color arena tint)))
  ([texture x y tint]
   (raylib_h/DrawTexture (rstructs/texture-2d texture) x y (rstructs/color tint))))

(defn draw-texture-v!
  "Draw a Texture2D with position defined as Vector2"
  ([^Arena arena texture position-v tint-color]
   (raylib_h/DrawTextureV
    (rstructs/texture-2d arena texture)
    (rstructs/vector2 arena position-v)
    (rstructs/color arena tint-color)))
  ([texture position-v tint-color]
   (raylib_h/DrawTextureV
    (rstructs/texture-2d texture)
    (rstructs/vector2 position-v)
    (rstructs/color tint-color))))

(defn draw-texture-ex!
  "Draw a Texture2D with extended parameters"
  ([^Arena arena texture position-v rotation scale tint-color]
   (raylib_h/DrawTextureEx
    (rstructs/texture-2d arena texture)
    (rstructs/vector2 arena position-v)
    rotation
    scale
    (rstructs/color arena tint-color)))
  ([texture position-v rotation scale tint-color]
   (raylib_h/DrawTextureEx
    (rstructs/texture-2d texture)
    (rstructs/vector2 position-v)
    rotation
    scale
    (rstructs/color tint-color))))

(defn draw-texture-rec!
  "Draw a part of a texture defined by a rectangle"
  ([^Arena arena texture source-rec position-v tint-color]
   (raylib_h/DrawTextureRec
    (rstructs/texture-2d arena texture)
    (rstructs/rectangle arena source-rec)
    (rstructs/vector2 arena position-v)
    (rstructs/color arena tint-color)))
  ([texture source-rec position-v tint-color]
   (raylib_h/DrawTextureRec
    (rstructs/texture-2d texture)
    (rstructs/rectangle source-rec)
    (rstructs/vector2 position-v)
    (rstructs/color tint-color))))

(defn draw-texture-pro!
  "Draw a part of a texture defined by a rectangle with 'pro' parameters"
  ([^Arena arena texture src-rec dest-rec origin-v rotation tint-color]
   (raylib_h/DrawTexturePro
    (rstructs/texture-2d arena texture)
    (rstructs/rectangle arena src-rec)
    (rstructs/rectangle arena dest-rec)
    (rstructs/vector2 arena origin-v)
    rotation
    (rstructs/color arena tint-color)))
  ([texture src-rec dest-rec origin-v rotation tint-color]
   (raylib_h/DrawTexturePro
    (rstructs/texture-2d texture)
    (rstructs/rectangle src-rec)
    (rstructs/rectangle dest-rec)
    (rstructs/vector2 origin-v)
    rotation
    (rstructs/color tint-color))))

(defn draw-texture-n-patch!
  "Draws a texture (or part of it) that stretches or shrinks nicely"
  ([^Arena arena texture npatch-info dest-rec origin-v rotation tint-color]
   (raylib_h/DrawTextureNPatch
    (rstructs/texture-2d arena texture)
    (rstructs/npatch-info arena npatch-info)
    (rstructs/rectangle arena dest-rec)
    (rstructs/vector2 arena origin-v)
    rotation
    (rstructs/color arena tint-color)))
  ([texture npatch-info dest-rec origin-v rotation tint-color]
   (raylib_h/DrawTextureNPatch
    (rstructs/texture-2d texture)
    (rstructs/npatch-info npatch-info)
    (rstructs/rectangle dest-rec)
    (rstructs/vector2 origin-v)
    rotation
    (rstructs/color tint-color))))

;; Color/pixel related functions
(defn fade!
  "Get color with alpha applied, alpha goes from 0.0f to 1.0f"
  ([^Arena arena color alpha]
   (raylib_h/Fade arena (rstructs/color arena color) alpha))
  ([color alpha]
   (rstructs/get-color
    (raylib_h/Fade rarena/*current-arena* (rstructs/color color) alpha))))

(defn color-to-int
  "Get hexadecimal value for a Color"
  [color] (raylib_h/ColorToInt (rstructs/color color)))

(defn color-normalize!
  "Get Color normalized as float [0..1]"
  ([^Arena arena color] (raylib_h/ColorNormalize arena (rstructs/color arena color)))
  ([color]
   (rstructs/get-vector4
    (raylib_h/ColorNormalize rarena/*current-arena* (rstructs/color color)))))

(defn color-from-normalized!
  "Get Color from normalized values [0..1]"
  ([^Arena arena normalized]
   (raylib_h/ColorFromNormalized arena (rstructs/vector4 arena normalized)))
  ([normalized]
   (rstructs/get-color
    (raylib_h/ColorFromNormalized rarena/*current-arena* (rstructs/vector4 normalized)))))

(defn color-to-hsv!
  "Get HSV values for a Color, hue [0..360], saturation/value [0..1]"
  ([^Arena arena color]
   (raylib_h/ColorToHSV arena (rstructs/color arena color)))
  ([color]
   (rstructs/get-vector3
    (raylib_h/ColorToHSV rarena/*current-arena* (rstructs/color color)))))

(defn color-from-hsv!
  "Get a Color from HSV values, hue [0..360], saturation/value [0..1]"
  ([^Arena arena hue saturation value]
   (raylib_h/ColorFromHSV arena hue saturation value))
  ([hue saturation value]
   (rstructs/get-color
    (raylib_h/ColorFromHSV rarena/*current-arena* hue saturation value))))

(defn color-tint!
  "Get color multiplied with another color"
  ([^Arena arena color tint-color]
   (raylib_h/ColorTint arena (rstructs/color arena color) (rstructs/color arena tint-color)))
  ([color tint]
   (rstructs/get-color
    (raylib_h/ColorTint rarena/*current-arena* (rstructs/color color) (rstructs/color tint)))))

(defn color-brightness!
  "Get color with brightness correction, brightness factor goes from -1.0f to 1.0f"
  ([^Arena arena color factor]
   (raylib_h/ColorBrightness arena (rstructs/color arena color) factor))
  ([color factor]
   (rstructs/get-color
    (raylib_h/ColorBrightness rarena/*current-arena* (rstructs/color color) factor))))

(defn color-contrast!
  "Get color with contrast correction, contrast values between -1.0f and 1.0f"
  ([^Arena arena color contrast]
   (raylib_h/ColorContrast arena (rstructs/color arena color) contrast))
  ([color contrast]
   (rstructs/get-color
    (raylib_h/ColorContrast rarena/*current-arena* (rstructs/color color) contrast))))

(defn color-alpha!
  "Get color with alpha applied, alpha goes from 0.0f to 1.0f"
  ([^Arena arena color alpha]
   (raylib_h/ColorAlpha arena (rstructs/color arena color) alpha))
  ([color alpha]
   (rstructs/get-color
    (raylib_h/ColorAlpha rarena/*current-arena* (rstructs/color color) alpha))))

(defn color-alpha-blend!
  "Get src alpha-blended into dst color with tint"
  ([^Arena arena dst-color src-color tint-color]
   (raylib_h/ColorAlphaBlend
    arena
    (rstructs/color arena dst-color)
    (rstructs/color arena src-color)
    (rstructs/color arena tint-color)))
  ([dst-color src-color tint-color]
   (rstructs/get-color
    (raylib_h/ColorAlphaBlend
     rarena/*current-arena*
     (rstructs/color dst-color)
     (rstructs/color src-color)
     (rstructs/color tint-color)))))

(defn get-color!
  "Get Color structure from hexadecimal value"
  ([^Arena arena color] (raylib_h/GetColor arena (rstructs/color arena color)))
  ([color]
   (rstructs/get-color
    (raylib_h/GetColor rarena/*current-arena* (rstructs/color color)))))

; TODO: what is a pixel pointer?
; RLAPI Color GetPixelColor(void *srcPtr, int format);                        ;; Get Color from a source pixel pointer of certain format
(defn get-pixel-color!
  "Get Color from a source pixel pointer of certain format"
  ([^Arena arena src-ptr format] (raylib_h/GetPixelColor arena src-ptr format))
  ([src-ptr format]
   (rstructs/get-color
    (raylib_h/GetPixelColor rarena/*current-arena* src-ptr format))))

; TODO: what is a pixel pointer?
; RLAPI void SetPixelColor(void *dstPtr, Color color, int format);            ;; Set color formatted into destination pixel pointer
(defn set-pixel-color!
  "Set color formatted into destination pixel pointer"
  ([^Arena arena dst-ptr color format]
   (raylib_h/SetPixelColor dst-ptr (rstructs/color arena color) format))
  ([dst-ptr color format]
   (raylib_h/SetPixelColor dst-ptr (rstructs/color color) format)))

(defn get-pixel-data-size!
  "Get pixel data size in bytes for certain format"
  [width height format] (raylib_h/GetPixelDataSize width height format))
