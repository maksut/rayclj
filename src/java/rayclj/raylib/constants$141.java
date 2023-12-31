// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$141 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$141() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "GenMeshCone",
        constants$140.const$5
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "GenMeshTorus",
        constants$139.const$5
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "GenMeshKnot",
        constants$139.const$5
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
        MemoryLayout.structLayout(
            RuntimeHelper.POINTER.withName("data"),
            JAVA_INT.withName("width"),
            JAVA_INT.withName("height"),
            JAVA_INT.withName("mipmaps"),
            JAVA_INT.withName("format")
        ).withName("Image"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3")
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "GenMeshHeightmap",
        constants$141.const$3
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "GenMeshCubicmap",
        constants$141.const$3
    );
}


