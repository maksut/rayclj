// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$94 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$94() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("data"),
        JAVA_INT.withName("width"),
        JAVA_INT.withName("height"),
        JAVA_INT.withName("mipmaps"),
        JAVA_INT.withName("format")
    ).withName("Image"),
        MemoryLayout.structLayout(
            RuntimeHelper.POINTER.withName("data"),
            JAVA_INT.withName("width"),
            JAVA_INT.withName("height"),
            JAVA_INT.withName("mipmaps"),
            JAVA_INT.withName("format")
        ).withName("Image"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("width"),
            JAVA_FLOAT.withName("height")
        ).withName("Rectangle")
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "ImageFromImage",
        constants$94.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("data"),
        JAVA_INT.withName("width"),
        JAVA_INT.withName("height"),
        JAVA_INT.withName("mipmaps"),
        JAVA_INT.withName("format")
    ).withName("Image"),
        RuntimeHelper.POINTER,
        JAVA_INT,
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color")
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "ImageText",
        constants$94.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("data"),
        JAVA_INT.withName("width"),
        JAVA_INT.withName("height"),
        JAVA_INT.withName("mipmaps"),
        JAVA_INT.withName("format")
    ).withName("Image"),
        MemoryLayout.structLayout(
            JAVA_INT.withName("baseSize"),
            JAVA_INT.withName("glyphCount"),
            JAVA_INT.withName("glyphPadding"),
            MemoryLayout.structLayout(
                JAVA_INT.withName("id"),
                JAVA_INT.withName("width"),
                JAVA_INT.withName("height"),
                JAVA_INT.withName("mipmaps"),
                JAVA_INT.withName("format")
            ).withName("texture"),
            RuntimeHelper.POINTER.withName("recs"),
            RuntimeHelper.POINTER.withName("glyphs")
        ).withName("Font"),
        RuntimeHelper.POINTER,
        JAVA_FLOAT,
        JAVA_FLOAT,
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color")
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "ImageTextEx",
        constants$94.const$4
    );
}

