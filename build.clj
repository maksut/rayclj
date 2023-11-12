(ns build
  (:require [clojure.tools.build.api :as b]
            [clojure.java.shell :refer [sh]]
            [clojure.java.io :as io]
            [gen.core :as gen]))

(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))

(defn absolute-path [relative-path]
  (.getCanonicalPath (io/file relative-path)))

(defn execute [& args]
  (let [cmd (str args)
        {:keys [exit] :as result} (apply sh args)]
    (when (not= exit 0)
      (throw (ex-info "Command failed" {:cmd cmd :result result})))
    result))

(defn clean [_]
  (b/delete {:path "java/generated"})
  (b/delete {:path "target"}))

(defn jgenerate-one [jextract-dir raylib-inc-dir header-name]
  (let [output-dir (absolute-path "./java/generated/")
        {:keys [out err]} (execute
                           "./jextract"
                           "--source"
                           "--target-package" header-name
                           "-lraylib"
                           "--output" output-dir
                           (absolute-path (io/file raylib-inc-dir (str header-name ".h")))
                           :dir (absolute-path jextract-dir))]
    (println "OUT: " out)
    (println "ERR: " err)))

(defn jgenerate [{:keys [jextract-dir raylib-inc-dir]}]
  (let [headers ["raylib", "rlgl"]]
    (map #(jgenerate-one jextract-dir raylib-inc-dir  %) headers)))

(defn jcompile [_]
  (b/javac {:src-dirs ["java/generated"]
            :class-dir class-dir
            :basis basis
            :javac-opts ["--release" "21" "--enable-preview"]}))

(defn copy-artifact [{:keys [raylib-dir install-dir]} source dest]
  (let [dest-file (io/file install-dir dest)]
    (io/make-parents dest-file)
    (io/copy (io/file raylib-dir source) dest-file)))

(defn build-raylib [{:keys [raylib-dir] :as config}]
  (let [src-dir (absolute-path (io/file raylib-dir "src"))]
    (execute "make" "RAYLIB_LIBTYPE=SHARED" :dir src-dir)
    (copy-artifact config "src/libraylib.so" "lib/libraylib.so")
    (copy-artifact config "src/raylib.h" "include/raylib.h")
    (copy-artifact config "src/rlgl.h" "include/rlgl.h")
    (println "raylib build is successful")))

(defn run-raylib-parser [{:keys [raylib-dir] :as config}]
  (let [parser-dir (absolute-path (io/file raylib-dir "parser"))
        run-parser (fn [header-name]
                     (execute
                      "./raylib_parser"
                      "--input" (format "../src/%s.h" header-name)
                      "--output" (format "output/%s_api.json" header-name)
                      "--format" "JSON"
                      :dir parser-dir))]
    (execute "make" :dir parser-dir) ; build the parser first
    (run-parser "raylib")
    (run-parser "rlgl")
    (copy-artifact config "parser/output/raylib_api.json" "api/raylib_api.json")
    (copy-artifact config "parser/output/rlgl_api.json" "api/rlgl_api.json")
    (println "parser run is successful")))

(comment
  ; builds raylib from source code and puts output in the :install-dir
  (build-raylib {:raylib-dir (io/file (System/getProperty "user.home") "oss/raylib/")
                 :install-dir (io/file (str "./native/raylib_linux_amd64"))})

  ; builds raylib parser, runs it and puts the output in the :install-dir
  (run-raylib-parser {:raylib-dir (io/file (System/getProperty "user.home") "oss/raylib/")
                      :install-dir (io/file (str "./native/raylib_linux_amd64"))})

  (clean nil)

  ; generates java code from raylib headers
  (jgenerate {:raylib-inc-dir "./native/raylib_linux_amd64/include/"
              :jextract-dir
              (io/file (System/getProperty "user.home") ".local/share/applications/jextract-21/bin")})

  ; compiles the generated java code
  (jcompile nil)

  ; generates all clojure code for the bindings by using the parser output
  ; generated clojure code uses the generated java code
  (gen/generate-all))
