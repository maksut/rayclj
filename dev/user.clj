(ns user
  (:require [clojure.java.javadoc :as javadoc]
            [portal.api :as p]
            [clj-async-profiler.core :as prof]))

(javadoc/add-local-javadoc "/usr/share/doc/java-openjdk/api/java.base")

(defonce p
  (do
    (add-tap #'p/submit)
    (p/open)))

(defn profiler []
  (prof/serve-ui 8080) ; Serve on port 8080
  ;; then evaluate a form with: (prof/profile (...))
  )

(comment
  (profiler)

  (deref p) ;; selected item in portal inspector
  )
