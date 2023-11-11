(ns rlgl.defines
  (:refer-clojure :exclude [float min max]))

(def rlgl-version "4.5")

(def default-batch-buffer-elements 8192)

(def default-batch-buffers
  "Default number of batch buffers (multi-buffering)"
  1)

(def default-batch-drawcalls
  "Default number of batch draw calls (by state changes: mode, texture)"
  256)

(def default-batch-max-texture-units
  "Maximum number of textures units that can be activated on batch drawing (SetShaderValueTexture())"
  4)

(def max-matrix-stack-size "Maximum size of Matrix stack" 32)

(def max-shader-locations "Maximum number of shader locations supported" 32)

(def cull-distance-near "Default near cull distance" 0.01)

(def cull-distance-far "Default far cull distance" 1000.0)

(def texture-wrap-s "GL_TEXTURE_WRAP_S" 10242)

(def texture-wrap-t "GL_TEXTURE_WRAP_T" 10243)

(def texture-mag-filter "GL_TEXTURE_MAG_FILTER" 10240)

(def texture-min-filter "GL_TEXTURE_MIN_FILTER" 10241)

(def texture-filter-nearest "GL_NEAREST" 9728)

(def texture-filter-linear "GL_LINEAR" 9729)

(def texture-filter-mip-nearest "GL_NEAREST_MIPMAP_NEAREST" 9984)

(def texture-filter-nearest-mip-linear "GL_NEAREST_MIPMAP_LINEAR" 9986)

(def texture-filter-linear-mip-nearest "GL_LINEAR_MIPMAP_NEAREST" 9985)

(def texture-filter-mip-linear "GL_LINEAR_MIPMAP_LINEAR" 9987)

(def texture-filter-anisotropic "Anisotropic filter (custom identifier)" 12288)

(def texture-mipmap-bias-ratio
  "Texture mipmap bias, percentage ratio (custom identifier)"
  16384)

(def texture-wrap-repeat "GL_REPEAT" 10497)

(def texture-wrap-clamp "GL_CLAMP_TO_EDGE" 33071)

(def texture-wrap-mirror-repeat "GL_MIRRORED_REPEAT" 33648)

(def texture-wrap-mirror-clamp "GL_MIRROR_CLAMP_EXT" 34626)

(def modelview "GL_MODELVIEW" 5888)

(def projection "GL_PROJECTION" 5889)

(def texture "GL_TEXTURE" 5890)

(def lines "GL_LINES" 1)

(def triangles "GL_TRIANGLES" 4)

(def quads "GL_QUADS" 7)

(def unsigned-byte "GL_UNSIGNED_BYTE" 5121)

(def float "GL_FLOAT" 5126)

(def stream-draw "GL_STREAM_DRAW" 35040)

(def stream-read "GL_STREAM_READ" 35041)

(def stream-copy "GL_STREAM_COPY" 35042)

(def static-draw "GL_STATIC_DRAW" 35044)

(def static-read "GL_STATIC_READ" 35045)

(def static-copy "GL_STATIC_COPY" 35046)

(def dynamic-draw "GL_DYNAMIC_DRAW" 35048)

(def dynamic-read "GL_DYNAMIC_READ" 35049)

(def dynamic-copy "GL_DYNAMIC_COPY" 35050)

(def fragment-shader "GL_FRAGMENT_SHADER" 35632)

(def vertex-shader "GL_VERTEX_SHADER" 35633)

(def compute-shader "GL_COMPUTE_SHADER" 37305)

(def zero "GL_ZERO" 0)

(def one "GL_ONE" 1)

(def src-color "GL_SRC_COLOR" 768)

(def one-minus-src-color "GL_ONE_MINUS_SRC_COLOR" 769)

(def src-alpha "GL_SRC_ALPHA" 770)

(def one-minus-src-alpha "GL_ONE_MINUS_SRC_ALPHA" 771)

(def dst-alpha "GL_DST_ALPHA" 772)

(def one-minus-dst-alpha "GL_ONE_MINUS_DST_ALPHA" 773)

(def dst-color "GL_DST_COLOR" 774)

(def one-minus-dst-color "GL_ONE_MINUS_DST_COLOR" 775)

(def src-alpha-saturate "GL_SRC_ALPHA_SATURATE" 776)

(def constant-color "GL_CONSTANT_COLOR" 32769)

(def one-minus-constant-color "GL_ONE_MINUS_CONSTANT_COLOR" 32770)

(def constant-alpha "GL_CONSTANT_ALPHA" 32771)

(def one-minus-constant-alpha "GL_ONE_MINUS_CONSTANT_ALPHA" 32772)

(def func-add "GL_FUNC_ADD" 32774)

(def min "GL_MIN" 32775)

(def max "GL_MAX" 32776)

(def func-subtract "GL_FUNC_SUBTRACT" 32778)

(def func-reverse-subtract "GL_FUNC_REVERSE_SUBTRACT" 32779)

(def blend-equation "GL_BLEND_EQUATION" 32777)

(def blend-equation-rgb
  "GL_BLEND_EQUATION_RGB   // (Same as BLEND_EQUATION)"
  32777)

(def blend-equation-alpha "GL_BLEND_EQUATION_ALPHA" 34877)

(def blend-dst-rgb "GL_BLEND_DST_RGB" 32968)

(def blend-src-rgb "GL_BLEND_SRC_RGB" 32969)

(def blend-dst-alpha "GL_BLEND_DST_ALPHA" 32970)

(def blend-src-alpha "GL_BLEND_SRC_ALPHA" 32971)

(def blend-color "GL_BLEND_COLOR" 32773)

(def gl-shading-language-version 35724)

(def gl-compressed-rgb-s3tc-dxt1-ext 33776)

(def gl-compressed-rgba-s3tc-dxt1-ext 33777)

(def gl-compressed-rgba-s3tc-dxt3-ext 33778)

(def gl-compressed-rgba-s3tc-dxt5-ext 33779)

(def gl-etc1-rgb8-oes 36196)

(def gl-compressed-rgb8-etc2 37492)

(def gl-compressed-rgba8-etc2-eac 37496)

(def gl-compressed-rgb-pvrtc-4bppv1-img 35840)

(def gl-compressed-rgba-pvrtc-4bppv1-img 35842)

(def gl-compressed-rgba-astc-4x4-khr 37808)

(def gl-compressed-rgba-astc-8x8-khr 37815)

(def gl-max-texture-max-anisotropy-ext 34047)

(def gl-texture-max-anisotropy-ext 34046)

(def gl-unsigned-short-5-6-5 33635)

(def gl-unsigned-short-5-5-5-1 32820)

(def gl-unsigned-short-4-4-4-4 32819)

(def gl-luminance 6409)

(def gl-luminance-alpha 6410)

(def default-shader-attrib-name-position
  "Bound by default to shader location: 0"
  "vertexPosition")

(def default-shader-attrib-name-texcoord
  "Bound by default to shader location: 1"
  "vertexTexCoord")

(def default-shader-attrib-name-normal
  "Bound by default to shader location: 2"
  "vertexNormal")

(def default-shader-attrib-name-color
  "Bound by default to shader location: 3"
  "vertexColor")

(def default-shader-attrib-name-tangent
  "Bound by default to shader location: 4"
  "vertexTangent")

(def default-shader-attrib-name-texcoord2
  "Bound by default to shader location: 5"
  "vertexTexCoord2")

(def default-shader-uniform-name-mvp "model-view-projection matrix" "mvp")

(def default-shader-uniform-name-view "view matrix" "matView")

(def default-shader-uniform-name-projection "projection matrix" "matProjection")

(def default-shader-uniform-name-model "model matrix" "matModel")

(def default-shader-uniform-name-normal
  "normal matrix (transpose(inverse(matModelView))"
  "matNormal")

(def default-shader-uniform-name-color
  "color diffuse (base tint color, multiplied by texture color)"
  "colDiffuse")

(def default-shader-sampler2d-name-texture0
  "texture0 (texture slot active 0)"
  "texture0")

(def default-shader-sampler2d-name-texture1
  "texture1 (texture slot active 1)"
  "texture1")

(def default-shader-sampler2d-name-texture2
  "texture2 (texture slot active 2)"
  "texture2")

