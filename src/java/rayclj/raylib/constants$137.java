// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$137 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$137() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.ofVoid(
        RuntimeHelper.POINTER,
        JAVA_BOOLEAN
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "UploadMesh",
        constants$137.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.ofVoid(
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
        ).withName("Mesh"),
        JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "UpdateMeshBuffer",
        constants$137.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
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
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "UnloadMesh",
        constants$137.const$4
    );
}

