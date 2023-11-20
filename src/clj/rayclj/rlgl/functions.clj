(ns rayclj.rlgl.functions
  (:require [rayclj.arena :as rarena]
            [rayclj.arrays :refer [string]]
            [rayclj.rlgl.structs :as rstructs])
  (:import [rayclj.rlgl rlgl_h]))

(set! *warn-on-reflection* true)

;;
;; RLGL Function definitions
;;

(defn matrix-mode
  "Choose the current matrix to be transformed
  [int mode] -> void"
  [mode]
  (rlgl_h/rlMatrixMode mode))

(defn push-matrix
  "Push the current matrix to stack
  [] -> void"
  []
  (rlgl_h/rlPushMatrix))

(defn pop-matrix
  "Pop latest inserted matrix from stack
  [] -> void"
  []
  (rlgl_h/rlPopMatrix))

(defn load-identity
  "Reset current matrix to identity matrix
  [] -> void"
  []
  (rlgl_h/rlLoadIdentity))

(defn translatef
  "Multiply the current matrix by a translation matrix
  [float x, float y, float z] -> void"
  [x y z]
  (rlgl_h/rlTranslatef x y z))

(defn rotatef
  "Multiply the current matrix by a rotation matrix
  [float angle, float x, float y, float z] -> void"
  [angle x y z]
  (rlgl_h/rlRotatef angle x y z))

(defn scalef
  "Multiply the current matrix by a scaling matrix
  [float x, float y, float z] -> void"
  [x y z]
  (rlgl_h/rlScalef x y z))

(defn mult-matrixf
  "Multiply the current matrix by another matrix
  [const float * matf] -> void"
  [matf]
  (rlgl_h/rlMultMatrixf matf))

(defn frustum
  "
  [double left, double right, double bottom, double top, double znear, double zfar] -> void"
  [left right bottom top znear zfar]
  (rlgl_h/rlFrustum left right bottom top znear zfar))

(defn ortho
  "
  [double left, double right, double bottom, double top, double znear, double zfar] -> void"
  [left right bottom top znear zfar]
  (rlgl_h/rlOrtho left right bottom top znear zfar))

(defn viewport
  "Set the viewport area
  [int x, int y, int width, int height] -> void"
  [x y width height]
  (rlgl_h/rlViewport x y width height))

(defn begin
  "Initialize drawing mode (how to organize vertex)
  [int mode] -> void"
  [mode]
  (rlgl_h/rlBegin mode))

(defn end "Finish vertex providing
  [] -> void" [] (rlgl_h/rlEnd))

(defn vertex2i
  "Define one vertex (position) - 2 int
  [int x, int y] -> void"
  [x y]
  (rlgl_h/rlVertex2i x y))

(defn vertex2f
  "Define one vertex (position) - 2 float
  [float x, float y] -> void"
  [x y]
  (rlgl_h/rlVertex2f x y))

(defn vertex3f
  "Define one vertex (position) - 3 float
  [float x, float y, float z] -> void"
  [x y z]
  (rlgl_h/rlVertex3f x y z))

(defn tex-coord2f
  "Define one vertex (texture coordinate) - 2 float
  [float x, float y] -> void"
  [x y]
  (rlgl_h/rlTexCoord2f x y))

(defn normal3f
  "Define one vertex (normal) - 3 float
  [float x, float y, float z] -> void"
  [x y z]
  (rlgl_h/rlNormal3f x y z))

(defn color4ub
  "Define one vertex (color) - 4 byte
  [unsigned char r, unsigned char g, unsigned char b, unsigned char a] -> void"
  [r g b a]
  (rlgl_h/rlColor4ub r g b a))

(defn color3f
  "Define one vertex (color) - 3 float
  [float x, float y, float z] -> void"
  [x y z]
  (rlgl_h/rlColor3f x y z))

(defn color4f
  "Define one vertex (color) - 4 float
  [float x, float y, float z, float w] -> void"
  [x y z w]
  (rlgl_h/rlColor4f x y z w))

(defn enable-vertex-array?
  "Enable vertex array (VAO, if supported)
  [unsigned int vaoId] -> bool"
  [vao-id]
  (rlgl_h/rlEnableVertexArray vao-id))

(defn disable-vertex-array
  "Disable vertex array (VAO, if supported)
  [] -> void"
  []
  (rlgl_h/rlDisableVertexArray))

(defn enable-vertex-buffer
  "Enable vertex buffer (VBO)
  [unsigned int id] -> void"
  [id]
  (rlgl_h/rlEnableVertexBuffer id))

(defn disable-vertex-buffer
  "Disable vertex buffer (VBO)
  [] -> void"
  []
  (rlgl_h/rlDisableVertexBuffer))

(defn enable-vertex-buffer-element
  "Enable vertex buffer element (VBO element)
  [unsigned int id] -> void"
  [id]
  (rlgl_h/rlEnableVertexBufferElement id))

(defn disable-vertex-buffer-element
  "Disable vertex buffer element (VBO element)
  [] -> void"
  []
  (rlgl_h/rlDisableVertexBufferElement))

(defn enable-vertex-attribute
  "Enable vertex attribute index
  [unsigned int index] -> void"
  [index]
  (rlgl_h/rlEnableVertexAttribute index))

(defn disable-vertex-attribute
  "Disable vertex attribute index
  [unsigned int index] -> void"
  [index]
  (rlgl_h/rlDisableVertexAttribute index))

(defn active-texture-slot
  "Select and active a texture slot
  [int slot] -> void"
  [slot]
  (rlgl_h/rlActiveTextureSlot slot))

(defn enable-texture
  "Enable texture
  [unsigned int id] -> void"
  [id]
  (rlgl_h/rlEnableTexture id))

(defn disable-texture
  "Disable texture
  [] -> void"
  []
  (rlgl_h/rlDisableTexture))

(defn enable-texture-cubemap
  "Enable texture cubemap
  [unsigned int id] -> void"
  [id]
  (rlgl_h/rlEnableTextureCubemap id))

(defn disable-texture-cubemap
  "Disable texture cubemap
  [] -> void"
  []
  (rlgl_h/rlDisableTextureCubemap))

(defn texture-parameters
  "Set texture parameters (filter, wrap)
  [unsigned int id, int param, int value] -> void"
  [id param value]
  (rlgl_h/rlTextureParameters id param value))

(defn cubemap-parameters
  "Set cubemap parameters (filter, wrap)
  [unsigned int id, int param, int value] -> void"
  [id param value]
  (rlgl_h/rlCubemapParameters id param value))

(defn enable-shader
  "Enable shader program
  [unsigned int id] -> void"
  [id]
  (rlgl_h/rlEnableShader id))

(defn disable-shader
  "Disable shader program
  [] -> void"
  []
  (rlgl_h/rlDisableShader))

(defn enable-framebuffer
  "Enable render texture (fbo)
  [unsigned int id] -> void"
  [id]
  (rlgl_h/rlEnableFramebuffer id))

(defn disable-framebuffer
  "Disable render texture (fbo), return to default framebuffer
  [] -> void"
  []
  (rlgl_h/rlDisableFramebuffer))

(defn active-draw-buffers
  "Activate multiple draw color buffers
  [int count] -> void"
  [count]
  (rlgl_h/rlActiveDrawBuffers count))

(defn blit-framebuffer
  "Blit active framebuffer to main framebuffer
  [int srcX, int srcY, int srcWidth, int srcHeight, int dstX, int dstY, int dstWidth, int dstHeight, int bufferMask] -> void"
  [src-x src-y src-width src-height dst-x dst-y dst-width dst-height
   buffer-mask]
  (rlgl_h/rlBlitFramebuffer src-x
                            src-y
                            src-width
                            src-height
                            dst-x
                            dst-y
                            dst-width
                            dst-height
                            buffer-mask))

(defn enable-color-blend
  "Enable color blending
  [] -> void"
  []
  (rlgl_h/rlEnableColorBlend))

(defn disable-color-blend
  "Disable color blending
  [] -> void"
  []
  (rlgl_h/rlDisableColorBlend))

(defn enable-depth-test
  "Enable depth test
  [] -> void"
  []
  (rlgl_h/rlEnableDepthTest))

(defn disable-depth-test
  "Disable depth test
  [] -> void"
  []
  (rlgl_h/rlDisableDepthTest))

(defn enable-depth-mask
  "Enable depth write
  [] -> void"
  []
  (rlgl_h/rlEnableDepthMask))

(defn disable-depth-mask
  "Disable depth write
  [] -> void"
  []
  (rlgl_h/rlDisableDepthMask))

(defn enable-backface-culling
  "Enable backface culling
  [] -> void"
  []
  (rlgl_h/rlEnableBackfaceCulling))

(defn disable-backface-culling
  "Disable backface culling
  [] -> void"
  []
  (rlgl_h/rlDisableBackfaceCulling))

(defn set-cull-face
  "Set face culling mode
  [int mode] -> void"
  [mode]
  (rlgl_h/rlSetCullFace mode))

(defn enable-scissor-test
  "Enable scissor test
  [] -> void"
  []
  (rlgl_h/rlEnableScissorTest))

(defn disable-scissor-test
  "Disable scissor test
  [] -> void"
  []
  (rlgl_h/rlDisableScissorTest))

(defn scissor
  "Scissor test
  [int x, int y, int width, int height] -> void"
  [x y width height]
  (rlgl_h/rlScissor x y width height))

(defn enable-wire-mode
  "Enable wire mode
  [] -> void"
  []
  (rlgl_h/rlEnableWireMode))

(defn enable-point-mode
  "Enable point mode
  [] -> void"
  []
  (rlgl_h/rlEnablePointMode))

(defn disable-wire-mode
  "Disable wire mode ( and point ) maybe rename
  [] -> void"
  []
  (rlgl_h/rlDisableWireMode))

(defn set-line-width
  "Set the line drawing width
  [float width] -> void"
  [width]
  (rlgl_h/rlSetLineWidth width))

(defn get-line-width
  "Get the line drawing width
  [] -> float"
  []
  (rlgl_h/rlGetLineWidth))

(defn enable-smooth-lines
  "Enable line aliasing
  [] -> void"
  []
  (rlgl_h/rlEnableSmoothLines))

(defn disable-smooth-lines
  "Disable line aliasing
  [] -> void"
  []
  (rlgl_h/rlDisableSmoothLines))

(defn enable-stereo-render
  "Enable stereo rendering
  [] -> void"
  []
  (rlgl_h/rlEnableStereoRender))

(defn disable-stereo-render
  "Disable stereo rendering
  [] -> void"
  []
  (rlgl_h/rlDisableStereoRender))

(defn is-stereo-render-enabled?
  "Check if stereo render is enabled
  [] -> bool"
  []
  (rlgl_h/rlIsStereoRenderEnabled))

(defn clear-color
  "Clear color buffer with color
  [unsigned char r, unsigned char g, unsigned char b, unsigned char a] -> void"
  [r g b a]
  (rlgl_h/rlClearColor r g b a))

(defn clear-screen-buffers
  "Clear used screen buffers (color and depth)
  [] -> void"
  []
  (rlgl_h/rlClearScreenBuffers))

(defn check-errors
  "Check and log OpenGL error codes
  [] -> void"
  []
  (rlgl_h/rlCheckErrors))

(defn set-blend-mode
  "Set blending mode
  [int mode] -> void"
  [mode]
  (rlgl_h/rlSetBlendMode mode))

(defn set-blend-factors
  "Set blending mode factor and equation (using OpenGL factors)
  [int glSrcFactor, int glDstFactor, int glEquation] -> void"
  [gl-src-factor gl-dst-factor gl-equation]
  (rlgl_h/rlSetBlendFactors gl-src-factor gl-dst-factor gl-equation))

(defn set-blend-factors-separate
  "Set blending mode factors and equations separately (using OpenGL factors)
  [int glSrcRGB, int glDstRGB, int glSrcAlpha, int glDstAlpha, int glEqRGB, int glEqAlpha] -> void"
  [gl-src-rgb gl-dst-rgb gl-src-alpha gl-dst-alpha gl-eq-rgb gl-eq-alpha]
  (rlgl_h/rlSetBlendFactorsSeparate gl-src-rgb
                                    gl-dst-rgb
                                    gl-src-alpha
                                    gl-dst-alpha
                                    gl-eq-rgb
                                    gl-eq-alpha))

(defn rlgl-init
  "Initialize rlgl (buffers, shaders, textures, states)
  [int width, int height] -> void"
  [width height]
  (rlgl_h/rlglInit width height))

(defn rlgl-close
  "De-initialize rlgl (buffers, shaders, textures)
  [] -> void"
  []
  (rlgl_h/rlglClose))

(defn load-extensions
  "Load OpenGL extensions (loader function required)
  [void * loader] -> void"
  [loader]
  (rlgl_h/rlLoadExtensions loader))

(defn get-version
  "Get current OpenGL version
  [] -> int"
  []
  (rlgl_h/rlGetVersion))

(defn set-framebuffer-width
  "Set current framebuffer width
  [int width] -> void"
  [width]
  (rlgl_h/rlSetFramebufferWidth width))

(defn get-framebuffer-width
  "Get default framebuffer width
  [] -> int"
  []
  (rlgl_h/rlGetFramebufferWidth))

(defn set-framebuffer-height
  "Set current framebuffer height
  [int height] -> void"
  [height]
  (rlgl_h/rlSetFramebufferHeight height))

(defn get-framebuffer-height
  "Get default framebuffer height
  [] -> int"
  []
  (rlgl_h/rlGetFramebufferHeight))

(defn get-texture-id-default
  "Get default texture id
  [] -> unsigned int"
  []
  (rlgl_h/rlGetTextureIdDefault))

(defn get-shader-id-default
  "Get default shader id
  [] -> unsigned int"
  []
  (rlgl_h/rlGetShaderIdDefault))

(defn get-shader-locs-default
  "Get default shader locations
  [] -> int *"
  []
  (rlgl_h/rlGetShaderLocsDefault))

(defn load-render-batch
  "Load a render batch system
  [int numBuffers, int bufferElements] -> rlRenderBatch"
  [num-buffers buffer-elements]
  (rstructs/get-render-batch (rlgl_h/rlLoadRenderBatch rarena/*current-arena*
                                                       num-buffers
                                                       buffer-elements)))

(defn unload-render-batch
  "Unload render batch system
  [rlRenderBatch batch] -> void"
  [batch]
  (rlgl_h/rlUnloadRenderBatch (rstructs/render-batch batch)))

(defn draw-render-batch
  "Draw render batch data (Update->Draw->Reset)
  [rlRenderBatch * batch] -> void"
  [batch]
  (rlgl_h/rlDrawRenderBatch (rstructs/render-batch batch)))

(defn set-render-batch-active
  "Set the active render batch for rlgl (NULL for default internal)
  [rlRenderBatch * batch] -> void"
  [batch]
  (rlgl_h/rlSetRenderBatchActive (rstructs/render-batch batch)))

(defn draw-render-batch-active
  "Update and draw internal render batch
  [] -> void"
  []
  (rlgl_h/rlDrawRenderBatchActive))

(defn check-render-batch-limit?
  "Check internal buffer overflow for a given number of vertex
  [int vCount] -> bool"
  [v-count]
  (rlgl_h/rlCheckRenderBatchLimit v-count))

(defn set-texture
  "Set current texture for render batch and check buffers limits
  [unsigned int id] -> void"
  [id]
  (rlgl_h/rlSetTexture id))

(defn load-vertex-array
  "Load vertex array (vao) if supported
  [] -> unsigned int"
  []
  (rlgl_h/rlLoadVertexArray))

(defn load-vertex-buffer
  "Load a vertex buffer attribute
  [const void * buffer, int size, bool dynamic] -> unsigned int"
  [buffer size dynamic]
  (rlgl_h/rlLoadVertexBuffer buffer size dynamic))

(defn load-vertex-buffer-element
  "Load a new attributes element buffer
  [const void * buffer, int size, bool dynamic] -> unsigned int"
  [buffer size dynamic]
  (rlgl_h/rlLoadVertexBufferElement buffer size dynamic))

(defn update-vertex-buffer
  "Update GPU buffer with new data
  [unsigned int bufferId, const void * data, int dataSize, int offset] -> void"
  [buffer-id data data-size offset]
  (rlgl_h/rlUpdateVertexBuffer buffer-id data data-size offset))

(defn update-vertex-buffer-elements
  "Update vertex buffer elements with new data
  [unsigned int id, const void * data, int dataSize, int offset] -> void"
  [id data data-size offset]
  (rlgl_h/rlUpdateVertexBufferElements id data data-size offset))

(defn unload-vertex-array
  "
  [unsigned int vaoId] -> void"
  [vao-id]
  (rlgl_h/rlUnloadVertexArray vao-id))

(defn unload-vertex-buffer
  "
  [unsigned int vboId] -> void"
  [vbo-id]
  (rlgl_h/rlUnloadVertexBuffer vbo-id))

(defn set-vertex-attribute
  "
  [unsigned int index, int compSize, int type, bool normalized, int stride, const void * pointer] -> void"
  [index comp-size type normalized stride pointer]
  (rlgl_h/rlSetVertexAttribute index comp-size type normalized stride pointer))

(defn set-vertex-attribute-divisor
  "
  [unsigned int index, int divisor] -> void"
  [index divisor]
  (rlgl_h/rlSetVertexAttributeDivisor index divisor))

(defn set-vertex-attribute-default
  "Set vertex attribute default value
  [int locIndex, const void * value, int attribType, int count] -> void"
  [loc-index value attrib-type count]
  (rlgl_h/rlSetVertexAttributeDefault loc-index value attrib-type count))

(defn draw-vertex-array
  "
  [int offset, int count] -> void"
  [offset count]
  (rlgl_h/rlDrawVertexArray offset count))

(defn draw-vertex-array-elements
  "
  [int offset, int count, const void * buffer] -> void"
  [offset count buffer]
  (rlgl_h/rlDrawVertexArrayElements offset count buffer))

(defn draw-vertex-array-instanced
  "
  [int offset, int count, int instances] -> void"
  [offset count instances]
  (rlgl_h/rlDrawVertexArrayInstanced offset count instances))

(defn draw-vertex-array-elements-instanced
  "
  [int offset, int count, const void * buffer, int instances] -> void"
  [offset count buffer instances]
  (rlgl_h/rlDrawVertexArrayElementsInstanced offset count buffer instances))

(defn load-texture
  "Load texture in GPU
  [const void * data, int width, int height, int format, int mipmapCount] -> unsigned int"
  [data width height format mipmap-count]
  (rlgl_h/rlLoadTexture data width height format mipmap-count))

(defn load-texture-depth
  "Load depth texture/renderbuffer (to be attached to fbo)
  [int width, int height, bool useRenderBuffer] -> unsigned int"
  [width height use-render-buffer]
  (rlgl_h/rlLoadTextureDepth width height use-render-buffer))

(defn load-texture-cubemap
  "Load texture cubemap
  [const void * data, int size, int format] -> unsigned int"
  [data size format]
  (rlgl_h/rlLoadTextureCubemap data size format))

(defn update-texture
  "Update GPU texture with new data
  [unsigned int id, int offsetX, int offsetY, int width, int height, int format, const void * data] -> void"
  [id offset-x offset-y width height format data]
  (rlgl_h/rlUpdateTexture id offset-x offset-y width height format data))

(defn get-gl-texture-formats
  "Get OpenGL internal formats
  [int format, unsigned int * glInternalFormat, unsigned int * glFormat, unsigned int * glType] -> void"
  [format gl-internal-format gl-format gl-type]
  (rlgl_h/rlGetGlTextureFormats format gl-internal-format gl-format gl-type))

(defn get-pixel-format-name
  "Get name string for pixel format
  [unsigned int format] -> const char *"
  [format]
  (rlgl_h/rlGetPixelFormatName format))

(defn unload-texture
  "Unload texture from GPU memory
  [unsigned int id] -> void"
  [id]
  (rlgl_h/rlUnloadTexture id))

(defn gen-texture-mipmaps
  "Generate mipmap data for selected texture
  [unsigned int id, int width, int height, int format, int * mipmaps] -> void"
  [id width height format mipmaps]
  (rlgl_h/rlGenTextureMipmaps id width height format mipmaps))

(defn read-texture-pixels
  "Read texture pixel data
  [unsigned int id, int width, int height, int format] -> void *"
  [id width height format]
  (rlgl_h/rlReadTexturePixels id width height format))

(defn read-screen-pixels
  "Read screen pixel data (color buffer)
  [int width, int height] -> unsigned char *"
  [width height]
  (rlgl_h/rlReadScreenPixels width height))

(defn load-framebuffer
  "Load an empty framebuffer
  [int width, int height] -> unsigned int"
  [width height]
  (rlgl_h/rlLoadFramebuffer width height))

(defn framebuffer-attach
  "Attach texture/renderbuffer to a framebuffer
  [unsigned int fboId, unsigned int texId, int attachType, int texType, int mipLevel] -> void"
  [fbo-id tex-id attach-type tex-type mip-level]
  (rlgl_h/rlFramebufferAttach fbo-id tex-id attach-type tex-type mip-level))

(defn framebuffer-complete?
  "Verify framebuffer is complete
  [unsigned int id] -> bool"
  [id]
  (rlgl_h/rlFramebufferComplete id))

(defn unload-framebuffer
  "Delete framebuffer from GPU
  [unsigned int id] -> void"
  [id]
  (rlgl_h/rlUnloadFramebuffer id))

(defn load-shader-code
  "Load shader from code strings
  [const char * vsCode, const char * fsCode] -> unsigned int"
  [vs-code fs-code]
  (rlgl_h/rlLoadShaderCode (string vs-code) (string fs-code)))

(defn compile-shader
  "Compile custom shader and return shader id (type: RL_VERTEX_SHADER, RL_FRAGMENT_SHADER, RL_COMPUTE_SHADER)
  [const char * shaderCode, int type] -> unsigned int"
  [shader-code type]
  (rlgl_h/rlCompileShader (string shader-code) type))

(defn load-shader-program
  "Load custom shader program
  [unsigned int vShaderId, unsigned int fShaderId] -> unsigned int"
  [v-shader-id f-shader-id]
  (rlgl_h/rlLoadShaderProgram v-shader-id f-shader-id))

(defn unload-shader-program
  "Unload shader program
  [unsigned int id] -> void"
  [id]
  (rlgl_h/rlUnloadShaderProgram id))

(defn get-location-uniform
  "Get shader location uniform
  [unsigned int shaderId, const char * uniformName] -> int"
  [shader-id uniform-name]
  (rlgl_h/rlGetLocationUniform shader-id (string uniform-name)))

(defn get-location-attrib
  "Get shader location attribute
  [unsigned int shaderId, const char * attribName] -> int"
  [shader-id attrib-name]
  (rlgl_h/rlGetLocationAttrib shader-id (string attrib-name)))

(defn set-uniform
  "Set shader value uniform
  [int locIndex, const void * value, int uniformType, int count] -> void"
  [loc-index value uniform-type count]
  (rlgl_h/rlSetUniform loc-index value uniform-type count))

(defn set-uniform-matrix
  "Set shader value matrix
  [int locIndex, Matrix mat] -> void"
  [loc-index mat]
  (rlgl_h/rlSetUniformMatrix loc-index (rstructs/matrix mat)))

(defn set-uniform-sampler
  "Set shader value sampler
  [int locIndex, unsigned int textureId] -> void"
  [loc-index texture-id]
  (rlgl_h/rlSetUniformSampler loc-index texture-id))

(defn set-shader
  "Set shader currently active (id and locations)
  [unsigned int id, int * locs] -> void"
  [id locs]
  (rlgl_h/rlSetShader id locs))

(defn load-compute-shader-program
  "Load compute shader program
  [unsigned int shaderId] -> unsigned int"
  [shader-id]
  (rlgl_h/rlLoadComputeShaderProgram shader-id))

(defn compute-shader-dispatch
  "Dispatch compute shader (equivalent to *draw* for graphics pipeline)
  [unsigned int groupX, unsigned int groupY, unsigned int groupZ] -> void"
  [group-x group-y group-z]
  (rlgl_h/rlComputeShaderDispatch group-x group-y group-z))

(defn load-shader-buffer
  "Load shader storage buffer object (SSBO)
  [unsigned int size, const void * data, int usageHint] -> unsigned int"
  [size data usage-hint]
  (rlgl_h/rlLoadShaderBuffer size data usage-hint))

(defn unload-shader-buffer
  "Unload shader storage buffer object (SSBO)
  [unsigned int ssboId] -> void"
  [ssbo-id]
  (rlgl_h/rlUnloadShaderBuffer ssbo-id))

(defn update-shader-buffer
  "Update SSBO buffer data
  [unsigned int id, const void * data, unsigned int dataSize, unsigned int offset] -> void"
  [id data data-size offset]
  (rlgl_h/rlUpdateShaderBuffer id data data-size offset))

(defn bind-shader-buffer
  "Bind SSBO buffer
  [unsigned int id, unsigned int index] -> void"
  [id index]
  (rlgl_h/rlBindShaderBuffer id index))

(defn read-shader-buffer
  "Read SSBO buffer data (GPU->CPU)
  [unsigned int id, void * dest, unsigned int count, unsigned int offset] -> void"
  [id dest count offset]
  (rlgl_h/rlReadShaderBuffer id dest count offset))

(defn copy-shader-buffer
  "Copy SSBO data between buffers
  [unsigned int destId, unsigned int srcId, unsigned int destOffset, unsigned int srcOffset, unsigned int count] -> void"
  [dest-id src-id dest-offset src-offset count]
  (rlgl_h/rlCopyShaderBuffer dest-id src-id dest-offset src-offset count))

(defn get-shader-buffer-size
  "Get SSBO buffer size
  [unsigned int id] -> unsigned int"
  [id]
  (rlgl_h/rlGetShaderBufferSize id))

(defn bind-image-texture
  "Bind image texture
  [unsigned int id, unsigned int index, int format, bool readonly] -> void"
  [id index format readonly]
  (rlgl_h/rlBindImageTexture id index format readonly))

(defn get-matrix-modelview
  "Get internal modelview matrix
  [] -> Matrix"
  []
  (rstructs/get-matrix (rlgl_h/rlGetMatrixModelview rarena/*current-arena*)))

(defn get-matrix-projection
  "Get internal projection matrix
  [] -> Matrix"
  []
  (rstructs/get-matrix (rlgl_h/rlGetMatrixProjection rarena/*current-arena*)))

(defn get-matrix-transform
  "Get internal accumulated transform matrix
  [] -> Matrix"
  []
  (rstructs/get-matrix (rlgl_h/rlGetMatrixTransform rarena/*current-arena*)))

(defn get-matrix-projection-stereo
  "Get internal projection matrix for stereo render (selected eye)
  [int eye] -> Matrix"
  [eye]
  (rstructs/get-matrix
   (rlgl_h/rlGetMatrixProjectionStereo rarena/*current-arena* eye)))

(defn get-matrix-view-offset-stereo
  "Get internal view offset matrix for stereo render (selected eye)
  [int eye] -> Matrix"
  [eye]
  (rstructs/get-matrix
   (rlgl_h/rlGetMatrixViewOffsetStereo rarena/*current-arena* eye)))

(defn set-matrix-projection
  "Set a custom projection matrix (replaces internal projection matrix)
  [Matrix proj] -> void"
  [proj]
  (rlgl_h/rlSetMatrixProjection (rstructs/matrix proj)))

(defn set-matrix-modelview
  "Set a custom modelview matrix (replaces internal modelview matrix)
  [Matrix view] -> void"
  [view]
  (rlgl_h/rlSetMatrixModelview (rstructs/matrix view)))

(defn set-matrix-projection-stereo
  "Set eyes projection matrices for stereo rendering
  [Matrix right, Matrix left] -> void"
  [right left]
  (rlgl_h/rlSetMatrixProjectionStereo (rstructs/matrix right)
                                      (rstructs/matrix left)))

(defn set-matrix-view-offset-stereo
  "Set eyes view offsets matrices for stereo rendering
  [Matrix right, Matrix left] -> void"
  [right left]
  (rlgl_h/rlSetMatrixViewOffsetStereo (rstructs/matrix right)
                                      (rstructs/matrix left)))

(defn load-draw-cube
  "Load and draw a cube
  [] -> void"
  []
  (rlgl_h/rlLoadDrawCube))

(defn load-draw-quad
  "Load and draw a quad
  [] -> void"
  []
  (rlgl_h/rlLoadDrawQuad))

