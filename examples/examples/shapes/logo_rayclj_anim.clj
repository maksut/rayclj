(ns examples.shapes.logo-rayclj-anim
  (:require [rayclj.raylib.functions :as rl]
            [rayclj.raylib.structs :as structs]
            [rayclj.memory :as memory]))

(def initial-state
  {:frames-counter 0
   :letters-count 0

   :top-side-rec-width 16
   :left-side-rec-height 16

   :bottom-side-rec-width 16
   :right-side-rec-height 16

   :state-index 0       ;; Tracking animation states (State Machine)
   :alpha 1.0           ;; Useful for fading the rectangles and text
   :clj-logo-alpha 0.0  ;; for fading the clj logo
   })

(defn update-state-0
  "State 0: Small box blinking"
  [{:keys [frames-counter] :as state}]
  (let [alpha (if (= 0 (mod (quot frames-counter 15) 2)) 0.0 1.0)]
    (if (= frames-counter 119)
      (assoc state :state-index 1 :frames-counter 0 :alpha 1.0)
      (assoc state :frames-counter (inc frames-counter) :alpha alpha))))

(defn update-state-1
  "State 1: Top and left bars growing"
  [{:keys [top-side-rec-width left-side-rec-height] :as state}]
  (if (= top-side-rec-width 240)
    (assoc state :state-index 2 :frames-counter 0)
    (assoc state
           :top-side-rec-width (+ top-side-rec-width 4)
           :left-side-rec-height (+ left-side-rec-height 4))))

(defn update-state-2
  "State 2: Bottom and right bars growing"
  [{:keys [bottom-side-rec-width right-side-rec-height] :as state}]
  (if (= bottom-side-rec-width 224)
    (assoc state :state-index 3 :frames-counter 0)
    (assoc state
           :bottom-side-rec-width (+ bottom-side-rec-width 4)
           :right-side-rec-height (+ right-side-rec-height 4))))

(defn update-state-3
  "State 3: Letters appearing (one by one)"
  [{:keys [frames-counter] :as state}]
  (if (>= frames-counter (* 12 9))
    (assoc state :state-index 4 :frames-counter 0 :letters-count 0)
    (let [frames-counter (inc frames-counter)
          letters-index (quot frames-counter 12) ; every 12 frames increment letters index
          letters-count (if (>= letters-index 6)
                          (- 6 (mod letters-index 6)) ; count back from "raylib" to "ray"
                          letters-index)]
      (assoc state :letters-count letters-count :frames-counter frames-counter))))

(defn update-state-4
  "State 4: Letters morhping from 'raylib' to 'rayclj'"
  [{:keys [frames-counter alpha clj-logo-alpha] :as state}]
  (let [frames-counter (inc frames-counter)
        letters-index (quot frames-counter 12)]
    (if (>= frames-counter (* 12 32)) ;; When all letters have appeared, just fade out everything
      (if (> alpha 0)
        (assoc state :alpha (- alpha 0.02) :clj-logo-alpha (- clj-logo-alpha 0.02))
        (assoc state :state-index 5 :alpha 0.0 :clj-logo-alpha 0.0))
      (assoc state
             :letters-count letters-index
             :frames-counter frames-counter
             :clj-logo-alpha (min 1.0 (+ clj-logo-alpha 0.02))))))

(defn update-state-5
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
    4 (update-state-4 state)
    5 (update-state-5 state inputs)))

(defn draw-state
  [{:keys [state-index
           logo-position-x
           logo-position-y
           top-side-rec-width
           left-side-rec-height
           right-side-rec-height
           bottom-side-rec-width
           letters-count
           alpha
           clj-logo-alpha]}
   resources]
  (rl/with-drawing
    (rl/clear-background :raywhite)

    (let [black-color (rl/fade :black alpha)
          clj-color (rl/fade {:r 99 :g 179 :b 50 :a 255} alpha)]
      ; top-left rect dot
      (rl/draw-rectangle logo-position-x logo-position-y 16 16 black-color)

      ; left and top rectangles
      (when (> state-index 0)
        (rl/draw-rectangle (+ logo-position-x 16) logo-position-y top-side-rec-width 16 black-color)
        (rl/draw-rectangle logo-position-x (+ logo-position-y 16) 16 left-side-rec-height black-color))

      ; right and bottom rectangles
      (when (> state-index 1)
        (rl/draw-rectangle (+ logo-position-x 240) (+ logo-position-y 16) 16 right-side-rec-height black-color)
        (rl/draw-rectangle (+ logo-position-x 16) (+ logo-position-y 240) bottom-side-rec-width 16 black-color))

      ; bottom-right rect dot
      (when (> state-index 2)
        (rl/draw-rectangle (+ logo-position-x 240) (+ logo-position-y 240) 16 16 black-color))

      ; raylib text
      (when (= state-index 3)
        (rl/draw-text (rl/text-subtext "raylib" 0 letters-count) (+ logo-position-x 84) (+ logo-position-y 176) 50 black-color))

      ; rayclj text
      (when (= state-index 4)
        (rl/draw-text "ray" (+ logo-position-x 84) (+ logo-position-y 176) 50 black-color)
        (rl/draw-text (rl/text-subtext "clj" 0 letters-count) (+ logo-position-x 174) (+ logo-position-y 176) 50 clj-color)
        (rl/draw-texture (:clj-logo resources) (+ logo-position-x 172) (+ logo-position-y 18) (rl/fade :white clj-logo-alpha))))

    ; replay
    (when (= state-index 5)
      (let [screen-width (rl/get-screen-width)
            screen-height (rl/get-screen-height)]
        (rl/draw-text "[R] REPLAY" (- (/ screen-width 2) 60) (- screen-height 30) 20 :gray))

      (rl/draw-fps 10 10))))

(defn tick
  "Update game state and draw it"
  [state resources]
  (memory/with-confined-arena
    (let [inputs {:r-key-pressed? (rl/key-pressed? :r)
                  :s-key-pressed? (rl/key-pressed? :s)}
          new-state (update-state state inputs)]
      (draw-state new-state resources)
      new-state)))

(defn -main []
  (let [margin-x 16
        margin-y 16
        screen-width (+ (* 2 margin-x) 256)
        screen-height (+ (* 2 margin-y) 256)
        logo-position-x margin-x
        logo-position-y margin-y]

    ;; Initialization
    (rl/init-window screen-width screen-height "raylib [shapes] example - raylib logo animation")

    (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

    (let [resources {:clj-logo (rl/load-texture "examples/examples/shapes/resources/Clojure_logo.png")}]
      ;; Main game loop
      (loop [state (assoc initial-state
                          :logo-position-x logo-position-x
                          :logo-position-y logo-position-y)]
        (when-not (rl/window-should-close?)
          (recur (#'tick state resources)))))

    (rl/close-window))) ;; Close window and OpenGL context

(comment (-main))
