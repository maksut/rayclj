// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$134 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$134() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.ofVoid(
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
        ).withName("Model")
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "UnloadModel",
        constants$134.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(MemoryLayout.structLayout(
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("min"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("max")
    ).withName("BoundingBox"),
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
        ).withName("Model")
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "GetModelBoundingBox",
        constants$134.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
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
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3"),
        JAVA_FLOAT,
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("Color")
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "DrawModel",
        constants$134.const$4
    );
}


