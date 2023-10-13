(ns examples.experiment.top-down
  (:require [clojure.math :as math]
            [raylib.core :as rc]
            [raylib.text :as rt]
            [raylib.shapes :as rs]
            [rlgl.core :as gl]))

(defn vector2-add [v1 v2] (mapv + v1 v2))
(defn vector2-substract [v1 v2] (mapv - v1 v2))
(defn vector2-scale [[x y] scale] [(* x scale) (* y scale)])

(defn vector2-line-angle [[x1 y1] [x2 y2]]
  (math/to-degrees (math/atan2 (- y1 y2) (- x1 x2))))

(defn vector2-normalize [[x y]]
  (let [length (math/sqrt (+ (* x x) (* y y)))]
    (if (zero? length)
      [0 0]
      [(/ x length) (/ y length)])))

(def cursor-speed 100)

(defn update-cursor [state frame-time]
  (let [{:keys [rotation color]} (:cursor state)
        new-position (rc/get-mouse-position!)
        new-color
        (cond
          (rc/mouse-button-pressed? :left) :maroon
          (rc/mouse-button-pressed? :middle) :lime
          (rc/mouse-button-pressed? :right) :darkblue
          (rc/mouse-button-pressed? :side) :purple
          (rc/mouse-button-pressed? :extra) :yellow
          (rc/mouse-button-pressed? :forward) :orange
          (rc/mouse-button-pressed? :back) :beige
          :else color)]
    (assoc
     state
     :cursor
     {:position new-position
      :color new-color
      :rotation (mod (+ (* frame-time cursor-speed) rotation) 360)})))

(def player-speed 240) ; 240 units per second

(defn update-player-position [state frame-time]
  (let [move (* player-speed frame-time)]
    (update-in
     state
     [:player :position]
     (fn [position]
       (cond-> position
         (rc/is-key-down? :w) (vector2-add [0 (* -1 move)]) ;; up: y -= 2
         (rc/is-key-down? :a) (vector2-add [(* -1 move) 0]) ;; left: x -= 2
         (rc/is-key-down? :s) (vector2-add [0 move])        ;; down: y += 2
         (rc/is-key-down? :d) (vector2-add [move 0]))))))   ;; right: x += 2

(def bullet-speed 1000)

(defn update-player-shoot [state]
  (if (rc/mouse-button-pressed? :left)
    (let [player-pos (-> state :player :position)
          cursor-pos (-> state :cursor :position)
          direction (vector2-normalize (vector2-substract cursor-pos player-pos))
          velocity (vector2-scale direction bullet-speed)
          bullet {:position player-pos :velocity velocity}]
      (update-in state [:projectiles] conj bullet))
    state))

(defn update-one-projectile [frame-time {:keys [position velocity]}]
  {:position (vector2-add position (vector2-scale velocity frame-time))
   :velocity velocity})

(defn projectile-in-screen? [{:keys [width height]} {[x y] :position}]
  (and (> x 0) (< x width)
       (> y 0) (< y height)))

(defn update-projectiles [{:keys [projectiles screen] :as state} frame-time]
  (let [one-p (partial update-one-projectile frame-time)
        new-projectiles (map one-p projectiles)]
    (->> new-projectiles
         (filter (partial projectile-in-screen? screen))
         (assoc state :projectiles))))

(defn update-player-rotation [state]
  (let [cursor-pos (-> state :cursor :position)
        player-pos (-> state :player :position)
        rotation (vector2-line-angle player-pos cursor-pos)]
    (assoc-in state [:player :rotation] rotation)))

(defn update-state [state frame-time]
  (-> state
      (update-player-position frame-time)
      (update-cursor frame-time)
      (update-projectiles frame-time)
      (update-player-rotation)
      (update-player-shoot)))

(defn draw-player [{:keys [position rotation]}]
  (let [[x y] position]
    (gl/matrix-mode! gl/modelview)
    (gl/push-matrix!)
    (gl/translatef! x y 0)
    (gl/rotatef! rotation 0 0 1)
    (rs/draw-circle! 0 0 20 :maroon)
    (gl/rotatef! 90 0 0 1)
    (gl/translatef! 0 20 0)
    (rs/draw-poly! [0 0] 3 20 0 :purple)
    (gl/pop-matrix!)))

(defn draw-cursor [{:keys [position color rotation]}]
  (rs/draw-poly! position 6 20 rotation color))

(defn draw-projectile [{:keys [position]}]
  (let [[x y] position]
    (rs/draw-circle! x y 10 :yellow)))

(defn draw-state [{:keys [player cursor projectiles]}]
  (rc/with-drawing!
    (rc/clear-background! :white)
    (rt/draw-fps! 10 40)
    (draw-player player)
    (draw-cursor cursor)
    (doseq [p projectiles] (draw-projectile p))
    (rt/draw-text! "move ball with mouse and click mouse button to change color" 10 10 20 :darkgray)))

(defn draw-error-state [{:keys [error]}]
  (rc/with-drawing!
    (rc/clear-background! :white)
    (rt/draw-text! (str error) 10 10 20 :red)))

(def initial-state {:screen {:height 450 :width 800}
                    :player {:position [400 225] :rotation 0}
                    :cursor {:color :maroon :rotation 0}
                    :projectiles []})

(defonce game-state (atom {}))

(defn game-loop [state]
  (try
    (if (:error state)
      (draw-error-state state)
      ; update and draw state
      (let [frame-time (rc/get-frame-time!)
            new-state (#'update-state state frame-time)]
        (#'draw-state (reset! game-state new-state))))

    ; break the loop for interrupt exceptions
    (catch InterruptedException e (throw e))

    ; but don't break it for anything else to keep live coding alive
    (catch Throwable e
      (.printStackTrace e)
      (swap! game-state assoc :error (.getMessage e))))

  ; if the window closed, Esc pressed or the thread is interrupted then stop the loop
  (when-not (or (rc/window-should-close?) (.isInterrupted (Thread/currentThread)))
    (recur @game-state)))

(defn game-init []
  (let [{:keys [width height]} (:screen initial-state)]
    (rc/init-window! width height "topdown shooter")
    (try
      (game-loop initial-state)
      (catch InterruptedException _ nil)) ; quit the loop silently on interrupt
    (rc/close-window!)))

(comment
  ; start the game in another thread
  (def game (future (game-init)))

  ; change the state
  (swap! game-state assoc :hex-color :yellow)

  ; reset the state
  (reset! game-state initial-state)

  ; after fixing the state, remember to clear the error
  (swap! game-state assoc :error nil)

  ; stop the game with an interrupt
  (future-cancel game))
