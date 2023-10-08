(ns rlgl.core
  "An abstraction layer for multiple OpenGL versions (1.1, 2.1, 3.3 Core, 4.3 Core, ES 2.0)
   that provides a pseudo-OpenGL 1.1 immediate-mode style API (rlVertex, rlTranslate, rlRotate...)"
  (:import [rlgl rlgl_h]))

(set! *warn-on-reflection* true)

;; Matrix modes (equivalent to OpenGL)
(def modelview 0x1700)   ;; GL_MODELVIEW
(def projection 0x1701)  ;; GL_PROJECTION
(def texture 0x1702)     ;; GL_TEXTURE

;;------------------------------------------------------------------------------------
;; Functions Declaration - Matrix operations
;;------------------------------------------------------------------------------------

(defn matrix-mode!
  "Choose the current matrix to be transformed"
  [mode] (rlgl_h/rlMatrixMode mode))

(defn push-matrix!
  "Push the current matrix to stack"
  [] (rlgl_h/rlPushMatrix))

(defn pop-matrix!
  "Pop latest inserted matrix from stack"
  [] (rlgl_h/rlPopMatrix))

(defn load-identity!
  "Reset current matrix to identity matrix"
  [] (rlgl_h/rlLoadIdentity))

(defn translatef!
  "Multiply the current matrix by a translation matrix"
  [x y z] (rlgl_h/rlTranslatef x y z))

(defn rotatef!
  "Multiply the current matrix by a rotation matrix"
  [angle x y z] (rlgl_h/rlRotatef angle x y z))

(defn scalef!
  "Multiply the current matrix by a scaling matrix"
  [x y z] (rlgl_h/rlScalef x y z))

; RLAPI void rlMultMatrixf(const float *matf);                ;; Multiply the current matrix by another matrix

(defn frustum [left right bottom top, znear zfar]
  (rlgl_h/rlFrustum left right bottom top znear zfar))

(defn ortho [left right bottom top znear zfar]
  (rlgl_h/rlOrtho left right bottom top znear zfar))

(defn viewport [x y width height]
  (rlgl_h/rlViewport x y width height))

;;------------------------------------------------------------------------------------
;; Functions Declaration - Vertex level operations
;;------------------------------------------------------------------------------------
(defn begin!
  "Initialize drawing mode (how to organize vertex)"
  [mode] (rlgl_h/rlBegin mode))

(defn end!
  "Finish vertex providing"
  [] (rlgl_h/rlEnd))

; RLAPI void rlVertex2i(int x, int y);                  ;; Define one vertex (position) - 2 int
; RLAPI void rlVertex2f(float x, float y);              ;; Define one vertex (position) - 2 float
; RLAPI void rlVertex3f(float x, float y, float z);     ;; Define one vertex (position) - 3 float
; RLAPI void rlTexCoord2f(float x, float y);            ;; Define one vertex (texture coordinate) - 2 float
; RLAPI void rlNormal3f(float x, float y, float z);     ;; Define one vertex (normal) - 3 float
; RLAPI void rlColor4ub(unsigned char r, unsigned char g, unsigned char b, unsigned char a);  ;; Define one vertex (color) - 4 byte
; RLAPI void rlColor3f(float x, float y, float z);          ;; Define one vertex (color) - 3 float
; RLAPI void rlColor4f(float x, float y, float z, float w); ;; Define one vertex (color) - 4 float

;;------------------------------------------------------------------------------------
;; Functions Declaration - OpenGL style functions (common to 1.1, 3.3+, ES2)
;; NOTE: This functions are used to completely abstract raylib code from OpenGL layer,
;; some of them are direct wrappers over OpenGL calls, some others are custom
;;------------------------------------------------------------------------------------

;; Vertex buffers state
; RLAPI bool rlEnableVertexArray(unsigned int vaoId);     ;; Enable vertex array (VAO, if supported)
; RLAPI void rlDisableVertexArray(void);                  ;; Disable vertex array (VAO, if supported)
; RLAPI void rlEnableVertexBuffer(unsigned int id);       ;; Enable vertex buffer (VBO)
; RLAPI void rlDisableVertexBuffer(void);                 ;; Disable vertex buffer (VBO)
; RLAPI void rlEnableVertexBufferElement(unsigned int id);;; Enable vertex buffer element (VBO element)
; RLAPI void rlDisableVertexBufferElement(void);          ;; Disable vertex buffer element (VBO element)
; RLAPI void rlEnableVertexAttribute(unsigned int index); ;; Enable vertex attribute index
; RLAPI void rlDisableVertexAttribute(unsigned int index);;; Disable vertex attribute index
; #if defined(GRAPHICS_API_OPENGL_11)
; RLAPI void rlEnableStatePointer(int vertexAttribType, void *buffer);    ;; Enable attribute state pointer
; RLAPI void rlDisableStatePointer(int vertexAttribType);                 ;; Disable attribute state pointer
; #endif

;; Textures state
; RLAPI void rlActiveTextureSlot(int slot);               ;; Select and active a texture slot
; RLAPI void rlEnableTexture(unsigned int id);            ;; Enable texture
; RLAPI void rlDisableTexture(void);                      ;; Disable texture
; RLAPI void rlEnableTextureCubemap(unsigned int id);     ;; Enable texture cubemap
; RLAPI void rlDisableTextureCubemap(void);               ;; Disable texture cubemap
; RLAPI void rlTextureParameters(unsigned int id, int param, int value); ;; Set texture parameters (filter, wrap)
; RLAPI void rlCubemapParameters(unsigned int id, int param, int value); ;; Set cubemap parameters (filter, wrap)

;; Shader state
; RLAPI void rlEnableShader(unsigned int id);             ;; Enable shader program
; RLAPI void rlDisableShader(void);                       ;; Disable shader program

;; Framebuffer state
; RLAPI void rlEnableFramebuffer(unsigned int id);        ;; Enable render texture (fbo)
; RLAPI void rlDisableFramebuffer(void);                  ;; Disable render texture (fbo), return to default framebuffer
; RLAPI void rlActiveDrawBuffers(int count);              ;; Activate multiple draw color buffers

;; General render state
; RLAPI void rlEnableColorBlend(void);                     ;; Enable color blending
; RLAPI void rlDisableColorBlend(void);                   ;; Disable color blending
; RLAPI void rlEnableDepthTest(void);                     ;; Enable depth test
; RLAPI void rlDisableDepthTest(void);                    ;; Disable depth test
; RLAPI void rlEnableDepthMask(void);                     ;; Enable depth write
; RLAPI void rlDisableDepthMask(void);                    ;; Disable depth write
; RLAPI void rlEnableBackfaceCulling(void);               ;; Enable backface culling
; RLAPI void rlDisableBackfaceCulling(void);              ;; Disable backface culling
; RLAPI void rlSetCullFace(int mode);                     ;; Set face culling mode
; RLAPI void rlEnableScissorTest(void);                   ;; Enable scissor test
; RLAPI void rlDisableScissorTest(void);                  ;; Disable scissor test
; RLAPI void rlScissor(int x, int y, int width, int height); ;; Scissor test
; RLAPI void rlEnableWireMode(void);                      ;; Enable wire mode
; RLAPI void rlDisableWireMode(void);                     ;; Disable wire mode
; RLAPI void rlSetLineWidth(float width);                 ;; Set the line drawing width
; RLAPI float rlGetLineWidth(void);                       ;; Get the line drawing width
; RLAPI void rlEnableSmoothLines(void);                   ;; Enable line aliasing
; RLAPI void rlDisableSmoothLines(void);                  ;; Disable line aliasing
; RLAPI void rlEnableStereoRender(void);                  ;; Enable stereo rendering
; RLAPI void rlDisableStereoRender(void);                 ;; Disable stereo rendering
; RLAPI bool rlIsStereoRenderEnabled(void);               ;; Check if stereo render is enabled

; RLAPI void rlClearColor(unsigned char r, unsigned char g, unsigned char b, unsigned char a); ;; Clear color buffer with color
; RLAPI void rlClearScreenBuffers(void);                  ;; Clear used screen buffers (color and depth)
; RLAPI void rlCheckErrors(void);                         ;; Check and log OpenGL error codes
; RLAPI void rlSetBlendMode(int mode);                    ;; Set blending mode
; RLAPI void rlSetBlendFactors(int glSrcFactor, int glDstFactor, int glEquation); ;; Set blending mode factor and equation (using OpenGL factors)
; RLAPI void rlSetBlendFactorsSeparate(int glSrcRGB, int glDstRGB, int glSrcAlpha, int glDstAlpha, int glEqRGB, int glEqAlpha); ;; Set blending mode factors and equations separately (using OpenGL factors)

;;------------------------------------------------------------------------------------
;; Functions Declaration - rlgl functionality
;;------------------------------------------------------------------------------------
;; rlgl initialization functions
; RLAPI void rlglInit(int width, int height);             ;; Initialize rlgl (buffers, shaders, textures, states)
; RLAPI void rlglClose(void);                             ;; De-initialize rlgl (buffers, shaders, textures)
; RLAPI void rlLoadExtensions(void *loader);              ;; Load OpenGL extensions (loader function required)
; RLAPI int rlGetVersion(void);                           ;; Get current OpenGL version
; RLAPI void rlSetFramebufferWidth(int width);            ;; Set current framebuffer width
; RLAPI int rlGetFramebufferWidth(void);                  ;; Get default framebuffer width
; RLAPI void rlSetFramebufferHeight(int height);          ;; Set current framebuffer height
; RLAPI int rlGetFramebufferHeight(void);                 ;; Get default framebuffer height

; RLAPI unsigned int rlGetTextureIdDefault(void);         ;; Get default texture id
; RLAPI unsigned int rlGetShaderIdDefault(void);          ;; Get default shader id
; RLAPI int *rlGetShaderLocsDefault(void);                ;; Get default shader locations

;; Render batch management
;; NOTE: rlgl provides a default render batch to behave like OpenGL 1.1 immediate mode
;; but this render batch API is exposed in case of custom batches are required
; RLAPI rlRenderBatch rlLoadRenderBatch(int numBuffers, int bufferElements);  ;; Load a render batch system
; RLAPI void rlUnloadRenderBatch(rlRenderBatch batch);                        ;; Unload render batch system
; RLAPI void rlDrawRenderBatch(rlRenderBatch *batch);                         ;; Draw render batch data (Update->Draw->Reset)
; RLAPI void rlSetRenderBatchActive(rlRenderBatch *batch);                    ;; Set the active render batch for rlgl (NULL for default internal)
; RLAPI void rlDrawRenderBatchActive(void);                                   ;; Update and draw internal render batch
; RLAPI bool rlCheckRenderBatchLimit(int vCount);                             ;; Check internal buffer overflow for a given number of vertex

; RLAPI void rlSetTexture(unsigned int id);               ;; Set current texture for render batch and check buffers limits

;;------------------------------------------------------------------------------------------------------------------------

;; Vertex buffers management
; RLAPI unsigned int rlLoadVertexArray(void);                               ;; Load vertex array (vao) if supported
; RLAPI unsigned int rlLoadVertexBuffer(const void *buffer, int size, bool dynamic);            ;; Load a vertex buffer attribute
; RLAPI unsigned int rlLoadVertexBufferElement(const void *buffer, int size, bool dynamic);     ;; Load a new attributes element buffer
; RLAPI void rlUpdateVertexBuffer(unsigned int bufferId, const void *data, int dataSize, int offset);     ;; Update GPU buffer with new data
; RLAPI void rlUpdateVertexBufferElements(unsigned int id, const void *data, int dataSize, int offset);   ;; Update vertex buffer elements with new data
; RLAPI void rlUnloadVertexArray(unsigned int vaoId);
; RLAPI void rlUnloadVertexBuffer(unsigned int vboId);
; RLAPI void rlSetVertexAttribute(unsigned int index, int compSize, int type, bool normalized, int stride, const void *pointer);
; RLAPI void rlSetVertexAttributeDivisor(unsigned int index, int divisor);
; RLAPI void rlSetVertexAttributeDefault(int locIndex, const void *value, int attribType, int count); ;; Set vertex attribute default value
; RLAPI void rlDrawVertexArray(int offset, int count);
; RLAPI void rlDrawVertexArrayElements(int offset, int count, const void *buffer);
; RLAPI void rlDrawVertexArrayInstanced(int offset, int count, int instances);
; RLAPI void rlDrawVertexArrayElementsInstanced(int offset, int count, const void *buffer, int instances);

;; Textures management
; RLAPI unsigned int rlLoadTexture(const void *data, int width, int height, int format, int mipmapCount); ;; Load texture in GPU
; RLAPI unsigned int rlLoadTextureDepth(int width, int height, bool useRenderBuffer);               ;; Load depth texture/renderbuffer (to be attached to fbo)
; RLAPI unsigned int rlLoadTextureCubemap(const void *data, int size, int format);                        ;; Load texture cubemap
; RLAPI void rlUpdateTexture(unsigned int id, int offsetX, int offsetY, int width, int height, int format, const void *data);  ;; Update GPU texture with new data
; RLAPI void rlGetGlTextureFormats(int format, unsigned int *glInternalFormat, unsigned int *glFormat, unsigned int *glType);  ;; Get OpenGL internal formats
; RLAPI const char *rlGetPixelFormatName(unsigned int format);              ;; Get name string for pixel format
; RLAPI void rlUnloadTexture(unsigned int id);                              ;; Unload texture from GPU memory
; RLAPI void rlGenTextureMipmaps(unsigned int id, int width, int height, int format, int *mipmaps); ;; Generate mipmap data for selected texture
; RLAPI void *rlReadTexturePixels(unsigned int id, int width, int height, int format);              ;; Read texture pixel data
; RLAPI unsigned char *rlReadScreenPixels(int width, int height);           ;; Read screen pixel data (color buffer)

;; Framebuffer management (fbo)
; RLAPI unsigned int rlLoadFramebuffer(int width, int height);              ;; Load an empty framebuffer
; RLAPI void rlFramebufferAttach(unsigned int fboId, unsigned int texId, int attachType, int texType, int mipLevel);  ;; Attach texture/renderbuffer to a framebuffer
; RLAPI bool rlFramebufferComplete(unsigned int id);                        ;; Verify framebuffer is complete
; RLAPI void rlUnloadFramebuffer(unsigned int id);                          ;; Delete framebuffer from GPU

;; Shaders management
; RLAPI unsigned int rlLoadShaderCode(const char *vsCode, const char *fsCode);    ;; Load shader from code strings
; RLAPI unsigned int rlCompileShader(const char *shaderCode, int type);           ;; Compile custom shader and return shader id (type: RL_VERTEX_SHADER, RL_FRAGMENT_SHADER, RL_COMPUTE_SHADER)
; RLAPI unsigned int rlLoadShaderProgram(unsigned int vShaderId, unsigned int fShaderId); ;; Load custom shader program
; RLAPI void rlUnloadShaderProgram(unsigned int id);                              ;; Unload shader program
; RLAPI int rlGetLocationUniform(unsigned int shaderId, const char *uniformName); ;; Get shader location uniform
; RLAPI int rlGetLocationAttrib(unsigned int shaderId, const char *attribName);   ;; Get shader location attribute
; RLAPI void rlSetUniform(int locIndex, const void *value, int uniformType, int count);   ;; Set shader value uniform
; RLAPI void rlSetUniformMatrix(int locIndex, Matrix mat);                        ;; Set shader value matrix
; RLAPI void rlSetUniformSampler(int locIndex, unsigned int textureId);           ;; Set shader value sampler
; RLAPI void rlSetShader(unsigned int id, int *locs);                             ;; Set shader currently active (id and locations)

;; Compute shader management
; RLAPI unsigned int rlLoadComputeShaderProgram(unsigned int shaderId);           ;; Load compute shader program
; RLAPI void rlComputeShaderDispatch(unsigned int groupX, unsigned int groupY, unsigned int groupZ);  ;; Dispatch compute shader (equivalent to *draw* for graphics pipeline)

;; Shader buffer storage object management (ssbo)
; RLAPI unsigned int rlLoadShaderBuffer(unsigned int size, const void *data, int usageHint); ;; Load shader storage buffer object (SSBO)
; RLAPI void rlUnloadShaderBuffer(unsigned int ssboId);                           ;; Unload shader storage buffer object (SSBO)
; RLAPI void rlUpdateShaderBuffer(unsigned int id, const void *data, unsigned int dataSize, unsigned int offset); ;; Update SSBO buffer data
; RLAPI void rlBindShaderBuffer(unsigned int id, unsigned int index);             ;; Bind SSBO buffer
; RLAPI void rlReadShaderBuffer(unsigned int id, void *dest, unsigned int count, unsigned int offset); ;; Read SSBO buffer data (GPU->CPU)
; RLAPI void rlCopyShaderBuffer(unsigned int destId, unsigned int srcId, unsigned int destOffset, unsigned int srcOffset, unsigned int count); ;; Copy SSBO data between buffers
; RLAPI unsigned int rlGetShaderBufferSize(unsigned int id);                      ;; Get SSBO buffer size

;; Buffer management
; RLAPI void rlBindImageTexture(unsigned int id, unsigned int index, int format, bool readonly);  ;; Bind image texture

;; Matrix state management
; RLAPI Matrix rlGetMatrixModelview(void);                                  ;; Get internal modelview matrix
; RLAPI Matrix rlGetMatrixProjection(void);                                 ;; Get internal projection matrix
; RLAPI Matrix rlGetMatrixTransform(void);                                  ;; Get internal accumulated transform matrix
; RLAPI Matrix rlGetMatrixProjectionStereo(int eye);                        ;; Get internal projection matrix for stereo render (selected eye)
; RLAPI Matrix rlGetMatrixViewOffsetStereo(int eye);                        ;; Get internal view offset matrix for stereo render (selected eye)
; RLAPI void rlSetMatrixProjection(Matrix proj);                            ;; Set a custom projection matrix (replaces internal projection matrix)
; RLAPI void rlSetMatrixModelview(Matrix view);                             ;; Set a custom modelview matrix (replaces internal modelview matrix)
; RLAPI void rlSetMatrixProjectionStereo(Matrix right, Matrix left);        ;; Set eyes projection matrices for stereo rendering
; RLAPI void rlSetMatrixViewOffsetStereo(Matrix right, Matrix left);        ;; Set eyes view offsets matrices for stereo rendering

;; Quick and dirty cube/quad buffers load->draw->unload
; RLAPI void rlLoadDrawCube(void);     ;; Load and draw a cube
; RLAPI void rlLoadDrawQuad(void);     ;; Load and draw a quad
