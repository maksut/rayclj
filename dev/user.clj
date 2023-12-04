(ns user
  (:require [clojure.java.javadoc :as javadoc]
            [portal.api :as p]
            [clj-async-profiler.core :as prof]))

(javadoc/add-local-javadoc "/usr/share/doc/java-openjdk/api/java.base")

(defn portal []
  (p/open)
  (add-tap #'p/submit))

(defn profiler []
  (prof/serve-ui 8080) ; Serve on port 8080
  ;; then evaluate a form with: (prof/profile (...))
  )

(comment
  (portal)
  (profiler))
