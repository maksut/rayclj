// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$86 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$86() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(JAVA_BOOLEAN,
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "CheckCollisionPointPoly",
        constants$86.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(JAVA_BOOLEAN,
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "CheckCollisionLines",
        constants$86.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(JAVA_BOOLEAN,
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y")
        ).withName("Vector2"),
        JAVA_INT
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "CheckCollisionPointLine",
        constants$86.const$4
    );
}


