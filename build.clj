(ns build
  (:require [clojure.tools.build.api :as b]
            [clojure.java.shell :refer [sh]])
  (:import [java.io File]))

(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))

(defn absolute-path [^String relative-path]
  (.getCanonicalPath (File. relative-path)))

(defn execute [& args]
  (let [cmd (str args)
        {:keys [exit out err]} (apply sh args)]
    (println "cmd: " cmd)
    (println "exit: " exit)
    (println "out: " out)
    (println "err: " err)))

(defn clean [_]
  (b/delete {:path "java/generated"})
  (b/delete {:path "target"}))

(defn jgenerate [{:keys [jextract-dir]}]
  (let [raylib_h (absolute-path "./native/raylib-4.5.0_linux_amd64/include/raylib.h")
        output (absolute-path "./java/generated/")]
    (execute
     "./jextract"
     "--source"
     "--target-package" "raylib"
     "-lraylib"
     "--output" output
     raylib_h
     :dir jextract-dir)))

(defn jcompile [_]
  (b/javac {:src-dirs ["java/generated"]
            :class-dir class-dir
            :basis basis
            :javac-opts ["--release" "21" "--enable-preview"]}))

(comment
  (clean nil)
  (jgenerate {:jextract-dir "/home/maksut/.local/share/applications/jextract-21/bin"})
  (jcompile nil))
