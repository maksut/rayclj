(ns examples.shapes.logo-raylib-anim
  (:require [rayclj.raylib.functions :as rl]
            [rayclj.memory :as memory]))

(def initial-state
  {:frames-counter 0
   :letters-count 0

   :top-side-rec-width 16
   :left-side-rec-height 16

   :bottom-side-rec-width 16
   :right-side-rec-height 16

   :state-index 0  ;; Tracking animation states (State Machine)
   :alpha 1.0      ;; Useful for fading
   })

(defn update-state-0
  "State 0: Small box blinking"
  [{:keys [frames-counter] :as state}]
  (if (= frames-counter 119)
    (assoc state :state-index 1 :frames-counter 0)
    (update state :frames-counter inc)))

(defn update-state-1
  "State 1: Top and left bars growing"
  [{:keys [top-side-rec-width left-side-rec-height] :as state}]
  (if (= top-side-rec-width 256)
    (assoc state :state-index 2)
    (assoc state
           :top-side-rec-width (+ top-side-rec-width 4)
           :left-side-rec-height (+ left-side-rec-height 4))))

(defn update-state-2
  "State 2: Bottom and right bars growing"
  [{:keys [bottom-side-rec-width right-side-rec-height] :as state}]
  (if (= bottom-side-rec-width 256)
    (assoc state :state-index 3)
    (assoc state
           :bottom-side-rec-width (+ bottom-side-rec-width 4)
           :right-side-rec-height (+ right-side-rec-height 4))))

(defn update-state-3
  "State 3: Letters appearing (one by one)"
  [{:keys [frames-counter letters-count alpha] :as state}]
  (if (>= letters-count 9) ;; When all letters have appeared, just fade out everything
    (if (> alpha 0)
      (assoc state :alpha (- alpha 0.02))
      (assoc state :state-index 4 :alpha 0.0))
    (let [frames-counter (inc frames-counter)]
      (if (= 0 (mod frames-counter 12)) ;; Every 12 frames, one more letter!
        (assoc state :letters-count (inc letters-count) :frames-counter 0)
        (assoc state :frames-counter frames-counter)))))

(defn update-state-4
  "State 4: Reset and Replay"
  [state {:keys [:r-key-pressed?]}]
  (if r-key-pressed?
    (merge state initial-state)
    state))

(defn update-state
  [{:keys [state-index] :as state} inputs]
  (case (long state-index)
    0 (update-state-0 state)
    1 (update-state-1 state)
    2 (update-state-2 state)
    3 (update-state-3 state)
    4 (update-state-4 state inputs)))

(defn draw-state
  [{:keys [state-index
           frames-counter
           logo-position-x
           logo-position-y
           top-side-rec-width
           left-side-rec-height
           right-side-rec-height
           bottom-side-rec-width
           letters-count
           alpha]}]
  (rl/with-drawing
    (rl/clear-background :raywhite)

    (case (long state-index)
      0 (when (not= 0 (mod (quot frames-counter 15) 2))
          (rl/draw-rectangle logo-position-x logo-position-y 16 16 :black))
      1 (do
          (rl/draw-rectangle logo-position-x logo-position-y top-side-rec-width 16 :black)
          (rl/draw-rectangle logo-position-x logo-position-y 16 left-side-rec-height :black))
      2 (do
          (rl/draw-rectangle logo-position-x logo-position-y top-side-rec-width 16 :black)
          (rl/draw-rectangle logo-position-x logo-position-y 16 left-side-rec-height :black)

          (rl/draw-rectangle (+ logo-position-x 240)  logo-position-y 16 right-side-rec-height :black)
          (rl/draw-rectangle logo-position-x  (+ logo-position-y 240) bottom-side-rec-width 16 :black))
      3 (do
          (rl/draw-rectangle logo-position-x logo-position-y top-side-rec-width 16 (rl/fade :black alpha))
          (rl/draw-rectangle logo-position-x (+ logo-position-y 16) 16 (- left-side-rec-height 32) (rl/fade :black alpha))

          (rl/draw-rectangle (+ logo-position-x 240)  (+ logo-position-y 16) 16 (- right-side-rec-height 32) (rl/fade :black alpha))
          (rl/draw-rectangle logo-position-x  (+ logo-position-y 240) bottom-side-rec-width 16 (rl/fade :black alpha))

          (let [hs-width (/ (rl/get-screen-width) 2)
                hs-height (/ (rl/get-screen-height) 2)]
            (rl/draw-rectangle (- hs-width 112) (- hs-height 112) 224 224 (rl/fade :raywhite alpha))
            (rl/draw-text (rl/text-subtext "raylib" 0 letters-count) (- hs-width 44) (+ hs-height 48) 50 (rl/fade :black alpha))))

      4 (rl/draw-text "[R] REPLAY" 340 200 20 :gray))

    (rl/draw-fps 10 10)))

(defn tick
  "Update game state and draw it"
  [state]
  (memory/with-confined-arena
    (let [inputs {:r-key-pressed? (rl/key-pressed? :r)}
          new-state (update-state state inputs)]
      (draw-state new-state)
      new-state)))

(defn -main []
  (let [screen-width 800
        screen-height 450
        logo-position-x (- (/ screen-width 2) 128)
        logo-position-y (- (/ screen-height 2) 128)]

    ;; Initialization
    (rl/init-window screen-width screen-height "raylib [shapes] example - raylib logo animation")

    (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

    ;; Main game loop
    (loop [state (assoc initial-state
                        :logo-position-x logo-position-x
                        :logo-position-y logo-position-y)]

      (when-not (rl/window-should-close?)
        (recur (tick state))))

    (rl/close-window))) ;; Close window and OpenGL context

(comment
  (-main))
