// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$72 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$72() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.ofVoid(
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color")
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "DrawLine",
        constants$72.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.ofVoid(
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
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
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "DrawLineV",
        constants$72.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        JAVA_FLOAT,
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color")
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "DrawLineEx",
        constants$72.const$4
    );
}


