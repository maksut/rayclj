// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$71 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$71() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.ofVoid(
        MemoryLayout.structLayout(
            JAVA_INT.withName("id"),
            JAVA_INT.withName("width"),
            JAVA_INT.withName("height"),
            JAVA_INT.withName("mipmaps"),
            JAVA_INT.withName("format")
        ).withName("Texture"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("width"),
            JAVA_FLOAT.withName("height")
        ).withName("Rectangle")
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "SetShapesTexture",
        constants$71.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.ofVoid(
        JAVA_INT,
        JAVA_INT,
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color")
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "DrawPixel",
        constants$71.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color")
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "DrawPixelV",
        constants$71.const$4
    );
}


