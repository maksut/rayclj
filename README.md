<img align="left" style="width:260px" src="https://github.com/maksut/rayclj/blob/main/examples/examples/shapes/resources/rayclj.gif" width="288px">


[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.maksut/rayclj.svg)](https://clojars.org/org.clojars.maksut/rayclj)

rayclj
======
[Raylib 5.0](https://github.com/raysan5/raylib) bindings for Clojure.

Thin wrapper around raylib.h and rlgl.h.

**REQUIRES:** java21 and `--enable-preview` jdk flag.

<br>

Usage
=====
Add rayclj as a dependency. For Clojure CLI/deps.edn:
```
org.clojars.maksut/rayclj {:mvn/version "0.0.52"}
```

Add these two jvm options into your alias:
```clojure
:jvm-opts [; for foreign function api
           "--enable-preview"
           "--enable-native-access=ALL-UNNAMED"]
```

Hello world:
```clojure
(ns basic-window
  (:require [rayclj.raylib.functions :as rl]))

(defn -main []
  (let [screen-width 800 screen-height 450]
    (rl/init-window screen-width screen-height "raylib [core] example - basic window")
    (rl/set-target-fps 60) ;; Set our game to run at 60 frames-per-second

    ;; Main game loop
    (while (not (rl/window-should-close?)) ;; Detect window close button or ESC key
      ;; Update your game state here

      ;; Draw
      (rl/with-drawing
        (rl/clear-background :white)
        (rl/draw-text "Hello, World!" 190 200 20 :lightgray)))

    ;; De-Initialization
    (rl/close-window))) ;; Close window and OpenGL context
```

See [more raylib examples](https://github.com/maksut/rayclj/tree/main/examples/examples) ported from raylib.

**Note:** Rayclj jar includes native libraries (eg. libraylib.so). By default it will export the raylib binary into your current working directory.
To provide your own raylib binary you can use `rayclj.library` property. For example:
```
:jvm-opts [; for foreign function api
           "--enable-preview"
           "--enable-native-access=ALL-UNNAMED"

           ; Optional raylib dynamic library.
           "-Drayclj.library=/some/directory/libraylib.so"]
```

Names
=====
Raylib function/struct/enum names are converted into kebab-case:
- `ImageDrawCircleLines` => `image-draw-circle-lines`
- `BoneInfo` => `bone-info`

For rlgl, `rl` prefix is dropped because it lives in a separate namespace:
- `rlPushMatrix` => `push-matrix`

Functions starting with "Is" has a question mark instead:
- `IsWindowReady` => `window-ready?`

Enumeration and pre-defined color arguments can be provided with regular keywords (see [raylib enum types](https://github.com/raysan5/raylib/wiki/raylib-enumerated-types)):
```clojure
(clear-background :white)
(key-down? :right)
````

Vectors and matrices can be represented with regular clojure vectors.
```clojure
(draw-triangle [0 0] [0 10] [10 10] :darkblue)
```

Other structs can be represented with clojure maps.
```clojure
(clear-background {:r 255 :g 0 :b 255 :a 255})
(image-crop img {:x 100 :y 10 :width 280 :height 380})
```

Performance
===========
Do not use clojure if performance is a hard requirement.
Trade off here is performance vs productivity. Who am I kidding. It is performance vs fun. Clojure is fun.

Java's new FFM api is designed to be more performant than JNA and close to JNI.
And clojure can get really close to java's performance. But one has to leave idiomatic clojure to get there (eg. by not using immutable data structures).

Bunnymark example:

|implementation        | explanation                       | bunnies | relative performance |
|----------------------|-----------------------------------|--------:|----------------------|
|[textures_bunnymark.c](https://github.com/raysan5/raylib/blob/master/examples/textures/textures_bunnymark.c)  | basic example in C, not optimised | 180000  | 1x                   |
|[bunnymark.clj](https://github.com/maksut/rayclj/blob/main/examples/examples/textures/bunnymark.clj)         | rayclj, not optimised             |  46000  | 0.26x                |
|[bunnymark_mutable.clj](https://github.com/maksut/rayclj/blob/main/examples/examples/textures/bunnymark_mutable.clj) | rayclj, mutable data structures   |  95000  | 0.52x                |
