// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$49 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$49() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_FLOAT.withName("x"),
        JAVA_FLOAT.withName("y")
    ).withName("Vector2"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3"),
        MemoryLayout.structLayout(
            MemoryLayout.structLayout(
                JAVA_FLOAT.withName("x"),
                JAVA_FLOAT.withName("y"),
                JAVA_FLOAT.withName("z")
            ).withName("position"),
            MemoryLayout.structLayout(
                JAVA_FLOAT.withName("x"),
                JAVA_FLOAT.withName("y"),
                JAVA_FLOAT.withName("z")
            ).withName("target"),
            MemoryLayout.structLayout(
                JAVA_FLOAT.withName("x"),
                JAVA_FLOAT.withName("y"),
                JAVA_FLOAT.withName("z")
            ).withName("up"),
            JAVA_FLOAT.withName("fovy"),
            JAVA_INT.withName("projection")
        ).withName("Camera3D"),
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "GetWorldToScreenEx",
        constants$49.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "GetWorldToScreen2D",
        constants$48.const$4
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "SetTargetFPS",
        constants$31.const$5
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(JAVA_FLOAT);
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "GetFrameTime",
        constants$49.const$4
    );
}


