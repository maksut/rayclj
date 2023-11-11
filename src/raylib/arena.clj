(ns raylib.arena
  (:import [java.lang.foreign Arena]))

(def ^Arena auto-arena (Arena/ofAuto))

(def ^Arena ^:dynamic *current-arena*  (Arena/ofAuto))

(def ^Arena global-arena (Arena/global))

(defn confined-arena ^Arena [] (Arena/ofConfined))
