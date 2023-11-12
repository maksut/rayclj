(ns rlgl.enums)

;;
;; RLGL Enums
;;

(def gl-version
  "OpenGL version"
  {:11    1 ;; OpenGL 1.1
   :21    2 ;; OpenGL 2.1 (GLSL 120)
   :33    3 ;; OpenGL 3.3 (GLSL 330)
   :43    4 ;; OpenGL 4.3 (using GLSL 330)
   :es-20 5 ;; OpenGL ES 2.0 (GLSL 100)
   :es-30 6 ;; OpenGL ES 3.0 (GLSL 300 es)
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

(def pixel-format
  "Texture pixel formats"
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

(def blend-mode
  "Color blending modes (pre-defined)"
  {:alpha             0 ;; Blend textures considering alpha (default)
   :additive          1 ;; Blend textures adding colors
   :multiplied        2 ;; Blend textures multiplying colors
   :add-colors        3 ;; Blend textures adding colors (alternative)
   :subtract-colors   4 ;; Blend textures subtracting colors (alternative)
   :alpha-premultiply 5 ;; Blend premultiplied textures considering alpha
   :custom            6 ;; Blend textures using custom src/dst factors (use rlSetBlendFactors())
   :custom-separate   7 ;; Blend textures using custom src/dst factors (use rlSetBlendFactorsSeparate())
  })

(def shader-location-index
  "Shader location point type"
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
   :map-albedo        15 ;; Shader location: sampler2d texture: albedo (same as: RL_SHADER_LOC_MAP_DIFFUSE)
   :map-metalness     16 ;; Shader location: sampler2d texture: metalness (same as: RL_SHADER_LOC_MAP_SPECULAR)
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

(def framebuffer-attach-type
  "Framebuffer attachment type"
  {:color-channel0 0 ;; Framebuffer attachment type: color 0
   :color-channel1 1 ;; Framebuffer attachment type: color 1
   :color-channel2 2 ;; Framebuffer attachment type: color 2
   :color-channel3 3 ;; Framebuffer attachment type: color 3
   :color-channel4 4 ;; Framebuffer attachment type: color 4
   :color-channel5 5 ;; Framebuffer attachment type: color 5
   :color-channel6 6 ;; Framebuffer attachment type: color 6
   :color-channel7 7 ;; Framebuffer attachment type: color 7
   :depth          100 ;; Framebuffer attachment type: depth
   :stencil        200 ;; Framebuffer attachment type: stencil
  })

(def framebuffer-attach-texture-type
  "Framebuffer texture attachment type"
  {:cubemap-positive-x 0 ;; Framebuffer texture attachment type: cubemap, +X side
   :cubemap-negative-x 1 ;; Framebuffer texture attachment type: cubemap, -X side
   :cubemap-positive-y 2 ;; Framebuffer texture attachment type: cubemap, +Y side
   :cubemap-negative-y 3 ;; Framebuffer texture attachment type: cubemap, -Y side
   :cubemap-positive-z 4 ;; Framebuffer texture attachment type: cubemap, +Z side
   :cubemap-negative-z 5 ;; Framebuffer texture attachment type: cubemap, -Z side
   :texture2d          100 ;; Framebuffer texture attachment type: texture2d
   :renderbuffer       200 ;; Framebuffer texture attachment type: renderbuffer
  })

(def cull-mode
  "Face culling mode"
  {:front 0 ;;
   :back  1 ;;
  })

