(ns raylib.audio "Audio Loading and Playing Functions (Module: audio)")

; typedef void (*AudioCallback)(void *bufferData, unsigned int frames);

;; Audio device management functions
; RLAPI void InitAudioDevice(void);                                     ;; Initialize audio device and context
; RLAPI void CloseAudioDevice(void);                                    ;; Close the audio device and context
; RLAPI bool IsAudioDeviceReady(void);                                  ;; Check if audio device has been initialized successfully
; RLAPI void SetMasterVolume(float volume);                             ;; Set master volume (listener)

;; Wave/Sound loading/unloading functions
; RLAPI Wave LoadWave(const char *fileName);                            ;; Load wave data from file
; RLAPI Wave LoadWaveFromMemory(const char *fileType, const unsigned char *fileData, int dataSize); ;; Load wave from memory buffer, fileType refers to extension: i.e. '.wav'
; RLAPI bool IsWaveReady(Wave wave);                                    ;; Checks if wave data is ready
; RLAPI Sound LoadSound(const char *fileName);                          ;; Load sound from file
; RLAPI Sound LoadSoundFromWave(Wave wave);                             ;; Load sound from wave data
; RLAPI bool IsSoundReady(Sound sound);                                 ;; Checks if a sound is ready
; RLAPI void UpdateSound(Sound sound, const void *data, int sampleCount); ;; Update sound buffer with new data
; RLAPI void UnloadWave(Wave wave);                                     ;; Unload wave data
; RLAPI void UnloadSound(Sound sound);                                  ;; Unload sound
; RLAPI bool ExportWave(Wave wave, const char *fileName);               ;; Export wave data to file, returns true on success
; RLAPI bool ExportWaveAsCode(Wave wave, const char *fileName);         ;; Export wave sample data to code (.h), returns true on success

;; Wave/Sound management functions
; RLAPI void PlaySound(Sound sound);                                    ;; Play a sound
; RLAPI void StopSound(Sound sound);                                    ;; Stop playing a sound
; RLAPI void PauseSound(Sound sound);                                   ;; Pause a sound
; RLAPI void ResumeSound(Sound sound);                                  ;; Resume a paused sound
; RLAPI bool IsSoundPlaying(Sound sound);                               ;; Check if a sound is currently playing
; RLAPI void SetSoundVolume(Sound sound, float volume);                 ;; Set volume for a sound (1.0 is max level)
; RLAPI void SetSoundPitch(Sound sound, float pitch);                   ;; Set pitch for a sound (1.0 is base level)
; RLAPI void SetSoundPan(Sound sound, float pan);                       ;; Set pan for a sound (0.5 is center)
; RLAPI Wave WaveCopy(Wave wave);                                       ;; Copy a wave to a new wave
; RLAPI void WaveCrop(Wave *wave, int initSample, int finalSample);     ;; Crop a wave to defined samples range
; RLAPI void WaveFormat(Wave *wave, int sampleRate, int sampleSize, int channels); ;; Convert wave data to desired format
; RLAPI float *LoadWaveSamples(Wave wave);                              ;; Load samples data from wave as a 32bit float data array
; RLAPI void UnloadWaveSamples(float *samples);                         ;; Unload samples data loaded with LoadWaveSamples()

;; Music management functions
; RLAPI Music LoadMusicStream(const char *fileName);                    ;; Load music stream from file
; RLAPI Music LoadMusicStreamFromMemory(const char *fileType, const unsigned char *data, int dataSize); ;; Load music stream from data
; RLAPI bool IsMusicReady(Music music);                                 ;; Checks if a music stream is ready
; RLAPI void UnloadMusicStream(Music music);                            ;; Unload music stream
; RLAPI void PlayMusicStream(Music music);                              ;; Start music playing
; RLAPI bool IsMusicStreamPlaying(Music music);                         ;; Check if music is playing
; RLAPI void UpdateMusicStream(Music music);                            ;; Updates buffers for music streaming
; RLAPI void StopMusicStream(Music music);                              ;; Stop music playing
; RLAPI void PauseMusicStream(Music music);                             ;; Pause music playing
; RLAPI void ResumeMusicStream(Music music);                            ;; Resume playing paused music
; RLAPI void SeekMusicStream(Music music, float position);              ;; Seek music to a position (in seconds)
; RLAPI void SetMusicVolume(Music music, float volume);                 ;; Set volume for music (1.0 is max level)
; RLAPI void SetMusicPitch(Music music, float pitch);                   ;; Set pitch for a music (1.0 is base level)
; RLAPI void SetMusicPan(Music music, float pan);                       ;; Set pan for a music (0.5 is center)
; RLAPI float GetMusicTimeLength(Music music);                          ;; Get music time length (in seconds)
; RLAPI float GetMusicTimePlayed(Music music);                          ;; Get current music time played (in seconds)

;; AudioStream management functions
; RLAPI AudioStream LoadAudioStream(unsigned int sampleRate, unsigned int sampleSize, unsigned int channels); ;; Load audio stream (to stream raw audio pcm data)
; RLAPI bool IsAudioStreamReady(AudioStream stream);                    ;; Checks if an audio stream is ready
; RLAPI void UnloadAudioStream(AudioStream stream);                     ;; Unload audio stream and free memory
; RLAPI void UpdateAudioStream(AudioStream stream, const void *data, int frameCount); ;; Update audio stream buffers with data
; RLAPI bool IsAudioStreamProcessed(AudioStream stream);                ;; Check if any audio stream buffers requires refill
; RLAPI void PlayAudioStream(AudioStream stream);                       ;; Play audio stream
; RLAPI void PauseAudioStream(AudioStream stream);                      ;; Pause audio stream
; RLAPI void ResumeAudioStream(AudioStream stream);                     ;; Resume audio stream
; RLAPI bool IsAudioStreamPlaying(AudioStream stream);                  ;; Check if audio stream is playing
; RLAPI void StopAudioStream(AudioStream stream);                       ;; Stop audio stream
; RLAPI void SetAudioStreamVolume(AudioStream stream, float volume);    ;; Set volume for audio stream (1.0 is max level)
; RLAPI void SetAudioStreamPitch(AudioStream stream, float pitch);      ;; Set pitch for audio stream (1.0 is base level)
; RLAPI void SetAudioStreamPan(AudioStream stream, float pan);          ;; Set pan for audio stream (0.5 is centered)
; RLAPI void SetAudioStreamBufferSizeDefault(int size);                 ;; Default size for new audio streams
; RLAPI void SetAudioStreamCallback(AudioStream stream, AudioCallback callback);  ;; Audio thread callback to request new data

; RLAPI void AttachAudioStreamProcessor(AudioStream stream, AudioCallback processor); ;; Attach audio stream processor to stream
; RLAPI void DetachAudioStreamProcessor(AudioStream stream, AudioCallback processor); ;; Detach audio stream processor from stream

; RLAPI void AttachAudioMixedProcessor(AudioCallback processor); ;; Attach audio stream processor to the entire audio pipeline
; RLAPI void DetachAudioMixedProcessor(AudioCallback processor); ;; Detach audio stream processor from the entire audio pipeline


