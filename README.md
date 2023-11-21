# rayclj
Raylib 5.0 bindings for Clojure. Thin wrapper around raylib.h and rlgl.h.

**REQUIRES:** java21 and `--enable-preview` jdk flag.

### Naming
Raylib function/struct/enum names are converted into kebab-case:
```
ImageDrawCircleLines -> image-draw-circle-lines`
BoneInfo -> bone-info
```

For rlgl, `rl` prefix is dropped because it lives in a separate namespace:
```
rlPushMatrix -> push-matrix
```

Functions starting with "Is" has a question mark instead:
```
IsWindowReady -> window-ready?
```

Enumeration and pre-defined color arguments can be provided with regular keywords (see enum.clj):
```
(clear-background :white)
(key-down? :right)
````

Vectors and matrices can be represented with regular clojure vectors.
```
(draw-triangle [0 0] [0 10] [10 10] :darkblue)
```

Other structs can be represented with clojure maps.
```
(clear-background {:r 255 :g 0 :b 255 :a 255})
(image-crop img {:x 100 :y 10 :width 280 :height 380})
```

### Performance
Do not use clojure if performance is a hard requirement.
Trade off here is performance vs productivity. Who am I kidding. It is performance vs fun. Clojure is fun.

Java's new FFM api is designed to be more performant than JNA and close to JNI.
And clojure can get really close to java's performance. But one has to leave idiomatic clojure to get there (eg. by not using immutable data structures).

Bunnymark example:
```
implementation        | explanation                       | bunnies | relative performance
----------------------|-----------------------------------|---------|---------------------
textures_bunnymark.c  | basic example in C, not optimised | 180000  | 1x
bunnymark.clj         | rayclj, not optimised             |  46000  | 0.26x
bunnymark_mutable.clj | rayclj, mutable data structures   |  95000  | 0.52x
```

### TODO:
- Figure out how to distribute this. Include native lib in the jar?
- Hello world starter according to above.
- Port more examples.
- Provide simple convenience macros like `with-confined-arena`, `with-matrix`, `with-drawing`.
- Consider embedding memory segments into maps (eg. embed raw MemorySegment in a rectagle map). It may cut some of the map -> MemorySegment conversions in the game loop.
