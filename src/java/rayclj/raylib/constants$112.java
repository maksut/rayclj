// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$112 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$112() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_BYTE.withName("r"),
        JAVA_BYTE.withName("g"),
        JAVA_BYTE.withName("b"),
        JAVA_BYTE.withName("a")
    ).withName("Color"),
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color"),
        JAVA_FLOAT
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "Fade",
        constants$112.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(JAVA_INT,
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color")
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "ColorToInt",
        constants$112.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_FLOAT.withName("x"),
        JAVA_FLOAT.withName("y"),
        JAVA_FLOAT.withName("z"),
        JAVA_FLOAT.withName("w")
    ).withName("Vector4"),
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color")
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "ColorNormalize",
        constants$112.const$4
    );
}


