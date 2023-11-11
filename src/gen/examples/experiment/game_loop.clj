(ns gen.examples.experiment.game-loop
  (:require [gen.raylib.functions :as rl]))

(defn update-state [{:keys [hex-color]}]
  (let [hex-position (rl/get-mouse-position)
        hex-color
        (cond
          (rl/mouse-button-pressed? :left) :maroon
          (rl/mouse-button-pressed? :middle) :lime
          (rl/mouse-button-pressed? :right) :darkblue
          (rl/mouse-button-pressed? :side) :purple
          (rl/mouse-button-pressed? :extra) :yellow
          (rl/mouse-button-pressed? :forward) :orange
          (rl/mouse-button-pressed? :back) :beige
          :else hex-color)]
    {:hex-position hex-position :hex-color hex-color}))

(defn draw-state [{:keys [hex-position hex-color]}]
  (rl/with-drawing
    (rl/clear-background :white)
    (rl/draw-poly hex-position 6 40 45 hex-color)
    (rl/draw-text "move ball with mouse and click mouse button to change color" 10 10 20 :darkgray)))

(defn draw-error-state [{:keys [error]}]
  (rl/with-drawing
    (rl/clear-background :white)
    (rl/draw-text (str error) 10 10 20 :red)))

(def initial-state {:hex-color :darkblue})

(defonce game-state (atom {}))

(defn game-loop [state]
  (try
    (if (:error state)
      (draw-error-state state)
      ; update and draw state
      (let [new-state (#'update-state state)]
        (#'draw-state (reset! game-state new-state))))

    ; break the loop for interrupt exceptions
    (catch InterruptedException e (throw e))

    ; but don't break it for anything else to keep live coding alive
    (catch Throwable e
      (.printStackTrace e)
      (swap! game-state assoc :error (.getMessage e))))

  ; if the window closed, Esc pressed or the thread is interrupted then stop the loop
  (when-not (or (rl/window-should-close?) (.isInterrupted (Thread/currentThread)))
    (recur @game-state)))

(defn game-init []
  (let [screen-width 800 screen-height 450]
    (rl/init-window screen-width screen-height "game loop for live coding example")
    (rl/set-target-fps 60)
    (try
      (game-loop initial-state)
      (catch InterruptedException _ nil)) ; quit the loop silently on interrupt
    (rl/close-window)))

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
