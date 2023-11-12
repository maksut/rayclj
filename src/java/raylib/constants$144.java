// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$144 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$144() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.ofVoid(
        MemoryLayout.structLayout(
            JAVA_INT.withName("boneCount"),
            JAVA_INT.withName("frameCount"),
            RuntimeHelper.POINTER.withName("bones"),
            RuntimeHelper.POINTER.withName("framePoses"),
            MemoryLayout.sequenceLayout(32, JAVA_BYTE).withName("name")
        ).withName("ModelAnimation")
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "UnloadModelAnimation",
        constants$144.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "UnloadModelAnimations",
        constants$33.const$2
    );
    static final FunctionDescriptor const$3 = FunctionDescriptor.of(JAVA_BOOLEAN,
        MemoryLayout.structLayout(
            MemoryLayout.structLayout(
                JAVA_FLOAT.withName("m0"),
                JAVA_FLOAT.withName("m4"),
                JAVA_FLOAT.withName("m8"),
                JAVA_FLOAT.withName("m12"),
                JAVA_FLOAT.withName("m1"),
                JAVA_FLOAT.withName("m5"),
                JAVA_FLOAT.withName("m9"),
                JAVA_FLOAT.withName("m13"),
                JAVA_FLOAT.withName("m2"),
                JAVA_FLOAT.withName("m6"),
                JAVA_FLOAT.withName("m10"),
                JAVA_FLOAT.withName("m14"),
                JAVA_FLOAT.withName("m3"),
                JAVA_FLOAT.withName("m7"),
                JAVA_FLOAT.withName("m11"),
                JAVA_FLOAT.withName("m15")
            ).withName("transform"),
            JAVA_INT.withName("meshCount"),
            JAVA_INT.withName("materialCount"),
            RuntimeHelper.POINTER.withName("meshes"),
            RuntimeHelper.POINTER.withName("materials"),
            RuntimeHelper.POINTER.withName("meshMaterial"),
            JAVA_INT.withName("boneCount"),
            MemoryLayout.paddingLayout(4),
            RuntimeHelper.POINTER.withName("bones"),
            RuntimeHelper.POINTER.withName("bindPose")
        ).withName("Model"),
        MemoryLayout.structLayout(
            JAVA_INT.withName("boneCount"),
            JAVA_INT.withName("frameCount"),
            RuntimeHelper.POINTER.withName("bones"),
            RuntimeHelper.POINTER.withName("framePoses"),
            MemoryLayout.sequenceLayout(32, JAVA_BYTE).withName("name")
        ).withName("ModelAnimation")
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "IsModelAnimationValid",
        constants$144.const$3
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.of(JAVA_BOOLEAN,
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3"),
        JAVA_FLOAT,
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3"),
        JAVA_FLOAT
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "CheckCollisionSpheres",
        constants$144.const$5
    );
}

