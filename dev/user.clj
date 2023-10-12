(ns user
  (:require [clojure.java.javadoc :as javadoc]))

(javadoc/add-local-javadoc "/usr/share/doc/java-openjdk/api/java.base")

(comment
  (import '[java.lang.foreign MemorySegment])
  (javadoc/javadoc MemorySegment))
