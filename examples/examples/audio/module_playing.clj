(ns examples.audio.module-playing
  (:require [rayclj.raylib.functions :as rl]))

(def MAX_CIRCLES 64)

(def colors [:orange :red :gold :lime :blue :violet :brown :lightgray :pink
             :yellow :green :skyblue :purple :beige])

(defn new-circle
  [screen-width screen-height]
  (let [radius (rl/get-random-value 10 40)]
    {:alpha 0.0
     :radius radius
     :position [(rl/get-random-value radius (- screen-width radius))
                (rl/get-random-value radius (- screen-height radius))]
     :speed (/ (rl/get-random-value 1 100) 2000.0)
     :color (nth colors (rl/get-random-value 0 13))}))

(defn update-circle
  [screen-width screen-height {:keys [alpha radius speed] :as circle}]
  (if (neg? alpha)
    (new-circle screen-width screen-height)
    (let [new-alpha (+ alpha speed)]
      (assoc circle
             :alpha new-alpha
             :speed (if (>= new-alpha 1.0) (* speed -1) speed)
             :radius (+ radius (* speed 10))))))

(defn -main []
  (let [screen-width 800 screen-height 450]
     ;; Initialization
    (rl/set-config-flags :msaa-4x-hint) ; NOTE: Try to enable MSAA 4X
    (rl/init-window screen-width screen-height, "raylib [audio] example - module playing (streaming)")

    (rl/init-audio-device) ; Initialize audio device

    (rl/set-target-fps 60) ; Set our game to run at 60 frames-per-second

    (let [music (rl/load-music-stream "examples/examples/audio/resources/mini1111.xm")
          music (assoc music :looping false)]

      (rl/play-music-stream music)

      ;; Main game loop
      (loop [pitch 1.0
             circles (repeatedly MAX_CIRCLES #(new-circle screen-width screen-height))]
        (when-not (rl/window-should-close?) ; Detect window close button or ESC key
          ;; Update
          (let [pitch (cond
                        (rl/key-down? :down) (- pitch 0.01)
                        (rl/key-down? :up) (+ pitch 0.01)
                        :else pitch)
                ;; Get timePlayed scaled to bar dimensions
                time-played  (* (/ (rl/get-music-time-played music) (rl/get-music-time-length music)) (- screen-width 40))

                playing? (rl/music-stream-playing? music)

                ;; updating circles
                circles (if playing?
                          (mapv #(update-circle screen-width screen-height %) circles)
                          circles)]
            (rl/update-music-stream music) ; Update music buffer with new stream data

            ;; Restart music playing (stop and play)
            (when (rl/key-pressed? :space)
              (if playing?
                (rl/stop-music-stream music)
                (rl/play-music-stream music)))

            ;; Pause/Resume music playing
            (when (rl/key-pressed? :p)
              (if playing?
                (rl/pause-music-stream music)
                (rl/resume-music-stream music)))

            (rl/set-music-pitch music pitch)

            (rl/with-drawing
              (rl/clear-background :raywhite)

              ;; Draw circles
              (doseq [{:keys [alpha radius position color]} circles]
                (rl/draw-circle-v position radius (rl/fade color alpha)))

              ;; Draw time bar
              (rl/draw-rectangle 20 (- screen-height 20 12) (- screen-width 40) 12 :lightgray)
              (rl/draw-rectangle 20 (- screen-height 20 12) time-played 12 :maroon)
              (rl/draw-rectangle-lines 20 (- screen-height 20 12) (- screen-width 40) 12 :gray)

              ;; Draw help instructions
              (rl/draw-rectangle 20 20 425 145 :white)
              (rl/draw-rectangle-lines 20 20 425 145 :gray)
              (rl/draw-text "PRESS SPACE TO RESTART MUSIC" 40 40 20 :black)
              (rl/draw-text "PRESS P TO PAUSE/RESUME" 40 70 20 :black)
              (rl/draw-text "PRESS UP/DOWN TO CHANGE SPEED" 40 100 20 :black)
              (rl/draw-text (rl/text-format "SPEED: %f" (object-array [pitch])) 40 130 20 :maroon))
            (recur pitch circles))))

      ;; De-Initialization
      (rl/unload-music-stream music) ; Unload music stream buffers from RAM
      (rl/close-audio-device)        ; Close audio device (music streaming is automatically stopped)
      (rl/close-window))))           ; Close window and OpenGL context
