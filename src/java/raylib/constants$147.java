// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$147 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$147() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_BOOLEAN.withName("hit"),
        MemoryLayout.paddingLayout(3),
        JAVA_FLOAT.withName("distance"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("point"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("normal")
    ).withName("RayCollision"),
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
            ).withName("direction")
        ).withName("Ray"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3")
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "GetRayCollisionQuad",
        constants$147.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.upcallHandle(AudioCallback.class, "apply", constants$33.const$2);
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        constants$33.const$2
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "InitAudioDevice",
        constants$29.const$4
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "CloseAudioDevice",
        constants$29.const$4
    );
}


