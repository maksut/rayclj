(ns examples.experiment.game-loop
  (:require [raylib.core :as rc]
            [raylib.text :as rt]
            [raylib.shapes :as rs]
            [raylib.enums :as enums]))

(defn update-state [{:keys [hex-color]}]
  (let [hex-position (rc/get-mouse-position!)
        hex-color
        (cond
          (rc/mouse-button-pressed? ::enums/left) ::enums/maroon
          (rc/mouse-button-pressed? ::enums/middle) ::enums/lime
          (rc/mouse-button-pressed? ::enums/right) ::enums/darkblue
          (rc/mouse-button-pressed? ::enums/side) ::enums/purple
          (rc/mouse-button-pressed? ::enums/extra) ::enums/yellow
          (rc/mouse-button-pressed? ::enums/forward) ::enums/orange
          (rc/mouse-button-pressed? ::enums/back) ::enums/beige
          :else hex-color)]
    {:hex-position hex-position :hex-color hex-color}))

(defn draw-state [{:keys [hex-position hex-color]}]
  (rc/with-drawing!
    (rc/clear-background! ::enums/white)
    (rs/draw-poly! hex-position 6 40 45 hex-color)
    (rt/draw-text! "move ball with mouse and click mouse button to change color" 10 10 20 ::enums/darkgray)))

(defn draw-error-state [{:keys [error]}]
  (rc/with-drawing!
    (rc/clear-background! ::enums/white)
    (rt/draw-text! (str error) 10 10 20 ::enums/red)))

(def initial-state {:hex-color ::enums/darkblue})

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
  (when-not (or (rc/window-should-close?) (.isInterrupted (Thread/currentThread)))
    (recur @game-state)))

(defn game-init []
  (let [screen-width 800 screen-height 450]
    (rc/init-window! screen-width screen-height "game loop for live coding example")
    (rc/set-target-fps! 60)
    (try
      (game-loop initial-state)
      (catch InterruptedException _ nil)) ; quit the loop silently on interrupt
    (rc/close-window!)))

(comment
  ; start the game in another thread
  (def game (future (game-init)))

  ; change the state
  (swap! game-state assoc :hex-color ::enums/yellow)

  ; reset the state
  (reset! game-state initial-state)

  ; after fixing the state, remember to clear the error
  (swap! game-state assoc :error nil)

  ; stop the game with an interrupt
  (future-cancel game))
