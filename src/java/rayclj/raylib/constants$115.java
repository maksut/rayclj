// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$115 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$115() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_BYTE.withName("r"),
        JAVA_BYTE.withName("g"),
        JAVA_BYTE.withName("b"),
        JAVA_BYTE.withName("a")
    ).withName("Color"),
        JAVA_INT
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "GetColor",
        constants$115.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_BYTE.withName("r"),
        JAVA_BYTE.withName("g"),
        JAVA_BYTE.withName("b"),
        JAVA_BYTE.withName("a")
    ).withName("Color"),
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "GetPixelColor",
        constants$115.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
        RuntimeHelper.POINTER,
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color"),
        JAVA_INT
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "SetPixelColor",
        constants$115.const$4
    );
}


