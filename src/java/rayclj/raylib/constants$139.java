// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$139 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$139() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(MemoryLayout.structLayout(
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
            JAVA_INT.withName("vertexCount"),
            JAVA_INT.withName("triangleCount"),
            RuntimeHelper.POINTER.withName("vertices"),
            RuntimeHelper.POINTER.withName("texcoords"),
            RuntimeHelper.POINTER.withName("texcoords2"),
            RuntimeHelper.POINTER.withName("normals"),
            RuntimeHelper.POINTER.withName("tangents"),
            RuntimeHelper.POINTER.withName("colors"),
            RuntimeHelper.POINTER.withName("indices"),
            RuntimeHelper.POINTER.withName("animVertices"),
            RuntimeHelper.POINTER.withName("animNormals"),
            RuntimeHelper.POINTER.withName("boneIds"),
            RuntimeHelper.POINTER.withName("boneWeights"),
            JAVA_INT.withName("vaoId"),
            MemoryLayout.paddingLayout(4),
            RuntimeHelper.POINTER.withName("vboId")
        ).withName("Mesh")
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "GetMeshBoundingBox",
        constants$139.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "GenMeshTangents",
        constants$33.const$4
    );
    static final FunctionDescriptor const$3 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_INT.withName("vertexCount"),
        JAVA_INT.withName("triangleCount"),
        RuntimeHelper.POINTER.withName("vertices"),
        RuntimeHelper.POINTER.withName("texcoords"),
        RuntimeHelper.POINTER.withName("texcoords2"),
        RuntimeHelper.POINTER.withName("normals"),
        RuntimeHelper.POINTER.withName("tangents"),
        RuntimeHelper.POINTER.withName("colors"),
        RuntimeHelper.POINTER.withName("indices"),
        RuntimeHelper.POINTER.withName("animVertices"),
        RuntimeHelper.POINTER.withName("animNormals"),
        RuntimeHelper.POINTER.withName("boneIds"),
        RuntimeHelper.POINTER.withName("boneWeights"),
        JAVA_INT.withName("vaoId"),
        MemoryLayout.paddingLayout(4),
        RuntimeHelper.POINTER.withName("vboId")
    ).withName("Mesh"),
        JAVA_INT,
        JAVA_FLOAT
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "GenMeshPoly",
        constants$139.const$3
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_INT.withName("vertexCount"),
        JAVA_INT.withName("triangleCount"),
        RuntimeHelper.POINTER.withName("vertices"),
        RuntimeHelper.POINTER.withName("texcoords"),
        RuntimeHelper.POINTER.withName("texcoords2"),
        RuntimeHelper.POINTER.withName("normals"),
        RuntimeHelper.POINTER.withName("tangents"),
        RuntimeHelper.POINTER.withName("colors"),
        RuntimeHelper.POINTER.withName("indices"),
        RuntimeHelper.POINTER.withName("animVertices"),
        RuntimeHelper.POINTER.withName("animNormals"),
        RuntimeHelper.POINTER.withName("boneIds"),
        RuntimeHelper.POINTER.withName("boneWeights"),
        JAVA_INT.withName("vaoId"),
        MemoryLayout.paddingLayout(4),
        RuntimeHelper.POINTER.withName("vboId")
    ).withName("Mesh"),
        JAVA_FLOAT,
        JAVA_FLOAT,
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "GenMeshPlane",
        constants$139.const$5
    );
}


