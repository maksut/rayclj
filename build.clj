(ns build
  (:require [clojure.tools.build.api :as b]
            [clojure.java.shell :refer [sh]]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def lib 'org.clojars.maksut/rayclj)
(def version (format "0.0.%s" (b/git-count-revs nil)))
(def target-dir "target")
(def class-dir (str target-dir "/classes"))
(def basis (b/create-basis {:project "deps.edn"}))
(def jar-file (str target-dir "/" (name lib) ".jar"))

(defn- absolute-path [& paths]
  (.getCanonicalPath ^java.io.File (apply io/file paths)))

(defn- execute [& args]
  (let [cmd (str args)
        {:keys [exit] :as result} (apply sh args)]
    (when (not= exit 0)
      (throw (ex-info "Command failed" {:cmd cmd :result result})))
    result))

(defn clean
  "Delete target folder"
  [_]
  (b/delete {:path target-dir}))

(defn clean-generated
  "Delete generated java and clojure files"
  [_]
  (b/delete {:path "src/java/rayclj/raylib"})
  (b/delete {:path "src/java/rayclj/rlgl"})
  (b/delete {:path "src/clj/rayclj/raylib"})
  (b/delete {:path "src/clj/rayclj/rlgl"}))

(defn- inject-library-resolver
  "Injects LibraryResolver into generated RuntimeHelper.java file"
  [runtime-file]
  (let [replace
        (fn [line]
          (if (re-find #"SymbolLookup loaderLookup =" line)
            "        SymbolLookup loaderLookup = SymbolLookup.libraryLookup(rayclj.LibraryResolver.ResolveRaylib(), Arena.global());"
            line))
        lines (string/split-lines (slurp runtime-file))
        lines (map replace lines)
        replaced (string/join "\n" lines)]
    (spit runtime-file replaced)))

(defn- generate-one
  "Generates java code for one raylib header"
  [jextract-dir raylib-inc-dir header-name]
  (let [output-dir (absolute-path "src/java")
        {:keys [out err exit]} (execute
                                "./jextract"
                                "--source"
                                "--target-package" (str "rayclj." header-name)
                                "--output" output-dir
                                (absolute-path raylib-inc-dir (str header-name ".h"))
                                :dir (absolute-path jextract-dir))]
    (when (= exit 0)
      (inject-library-resolver (io/file output-dir "rayclj" header-name "RuntimeHelper.java")))

    (println "OUT: " out)
    (println "ERR: " err)))

(defn generate-java
  "Generates java code for raylib headers"
  [{:keys [jextract-dir raylib-inc-dir]}]
  (let [headers ["raylib", "rlgl"]]
    (map #(generate-one jextract-dir raylib-inc-dir  %) headers)))

(defn compile-java
  "Compiles generated java code"
  [_]
  (b/javac {:src-dirs ["src/java"]
            :class-dir class-dir
            :basis basis
            :javac-opts ["--release" "21" "--enable-preview"]}))

(defn- copy-artifact
  "Copy raylib-dir/source into inastall-dir/dest"
  [{:keys [raylib-dir install-dir]} source dest]
  (b/copy-file
   {:src (str (io/file raylib-dir source))
    :target (str (io/file install-dir dest))}))

(defn- extract
  "Extact an archive file into the target directory"
  [file target-dir]
  (let [command (cond
                  (string/ends-with? file ".zip") ["unzip"]
                  (string/ends-with? file ".tar.gz") ["tar" "-xzf"]
                  (string/ends-with? file ".tar.xz") ["tar" "-xJf"]
                  :else (throw (ex-info "Unknown archive type" {:file file})))]
    (apply execute (conj command file :dir target-dir))))

(defn- download
  "Fetch the url and save it as a file. Works with binaries."
  [url target-file]
  (with-open [in-stream (io/input-stream (java.net.URL. url))
              out-stream (io/output-stream (io/file target-file))]
    (io/copy in-stream out-stream)))

(defn- download-and-extract
  "Fetch the file with baseurl/filename into target-dir and extract it"
  [baseurl filename target-dir]
  (when-not (.exists (io/file target-dir filename))
    (let [url (str baseurl filename)
          target-file (io/file target-dir filename)]
      (println "Downloading " url)
      (io/make-parents target-file)
      (download url target-file)
      (extract filename target-dir))))

(defn download-and-extract-raylib
  [{:keys [target-dir]}]
  (doseq [file ["raylib-5.0_linux_amd64.tar.gz"
                "raylib-5.0_macos.tar.gz"
                "raylib-5.0_win64_msvc16.zip"]]
    (download-and-extract
     "https://github.com/raysan5/raylib/releases/download/5.0/"
     file
     target-dir)))

(defn prep
  "Prepares the library by downloading raylib releases and compiling java code"
  [opts]
  (download-and-extract-raylib {:target-dir "native"})
  (compile-java opts))

(defn build-raylib
  "Builds raylib in raylib-dir sources and copies the build artifacts into install-dir"
  [{:keys [raylib-dir] :as config}]
  (let [src-dir (absolute-path raylib-dir "src")]
    (execute "make" "RAYLIB_LIBTYPE=SHARED" :dir src-dir)
    (copy-artifact config "src/libraylib.so" "lib/libraylib.so")
    (copy-artifact config "src/raylib.h" "include/raylib.h")
    (copy-artifact config "src/rlgl.h" "include/rlgl.h")
    (println "raylib build is successful")))

(defn run-raylib-parser
  "Builds raylib parser in raylib-dir sources, runs it on headers in install-dir/include
  and puts the output jsons into the :install-dir/api folder"
  [{:keys [raylib-dir install-dir] :as config}]
  (let [parser-dir (absolute-path raylib-dir "parser")
        run-parser (fn [header-name]
                     (execute
                      "./raylib_parser"
                      "--input" (.getAbsolutePath (io/file install-dir "include" (str header-name ".h")))
                      "--output" (format "output/%s_api.json" header-name)
                      "--format" "JSON"
                      :dir parser-dir))]
    (execute "make" :dir parser-dir) ; build the parser first
    (run-parser "raylib")
    (run-parser "rlgl")
    (copy-artifact config "parser/output/raylib_api.json" "api/raylib_api.json")
    (copy-artifact config "parser/output/rlgl_api.json" "api/rlgl_api.json")
    (println "parser run is successful")))

(defn pom
  "Generates a pom file in classes/META-INF directory. Also copies it into target"
  [_]
  (b/write-pom {:class-dir class-dir
                :lib lib
                :version version
                :basis basis
                :src-dirs ["src"]})
  (b/copy-file {:src (b/pom-path {:lib lib :class-dir class-dir})
                :target (str target-dir "/pom.xml")}))

(defn jar
  "Generates a pom, compiles java and clj files and builds a jar file"
  [opts]
  (pom opts)
  (prep opts)

  ;; Also AOT compile clj code, so the user has to provide only --enable-preview jvm flag
  (b/compile-clj {:basis basis
                  :src-dirs ["src/clj"]
                  :class-dir class-dir
                  :java-opts ["--enable-preview"
                              "--enable-native-access=ALL-UNNAMED"]})

  (b/copy-dir {:src-dirs ["src/clj" "src/java"]
               :target-dir (str class-dir "/src")})

  (doseq [file ["raylib-5.0_linux_amd64/lib/libraylib.so"
                "raylib-5.0_macos/lib/libraylib.dylib"
                "raylib-5.0_win64_msvc16/lib/raylib.dll"]]
    (b/copy-file {:src (str "native/" file)
                  :target (str class-dir "/" file)}))

  (b/jar {:class-dir class-dir
          :jar-file jar-file}))

(comment
  ; Optional - builds raylib from source code and puts output in the :install-dir
  (build-raylib {:raylib-dir (io/file (System/getProperty "user.home") "oss/raylib/")
                 :install-dir "./native/raylib-5.0_linux_amd64"})

  ; Prebuilt raylib releases
  (download-and-extract-raylib {:target-dir "native"})

  ; Builds raylib parser, runs it and puts the output in the :install-dir
  (run-raylib-parser {:raylib-dir (io/file (System/getProperty "user.home") "oss/raylib")
                      :install-dir "./native/raylib-5.0_linux_amd64"})

  ; generates all clojure code for the bindings by using the parser output
  ; generated clojure code uses the generated java code
  #_:clj-kondo/ignore
  (gen.core/generate-all "native/raylib-5.0_linux_amd64/api")

  (clean-generated nil)

  (clean nil)

  ; Generates java code from raylib headers
  (generate-java {:raylib-inc-dir
                  "./native/raylib-5.0_linux_amd64/include"
                  :jextract-dir
                  (io/file (System/getProperty "user.home") ".local/share/applications/jextract-21/bin")})

  ; downloads raylib and compiles the generated java code
  (prep nil)

  (jar nil))
