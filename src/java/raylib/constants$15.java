// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$15 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$15() {}
    static final VarHandle const$0 = constants$12.const$4.varHandle(MemoryLayout.PathElement.groupElement("vaoId"));
    static final VarHandle const$1 = constants$12.const$4.varHandle(MemoryLayout.PathElement.groupElement("vboId"));
    static final StructLayout const$2 = MemoryLayout.structLayout(
        JAVA_INT.withName("id"),
        MemoryLayout.paddingLayout(4),
        RuntimeHelper.POINTER.withName("locs")
    ).withName("Shader");
    static final VarHandle const$3 = constants$15.const$2.varHandle(MemoryLayout.PathElement.groupElement("id"));
    static final VarHandle const$4 = constants$15.const$2.varHandle(MemoryLayout.PathElement.groupElement("locs"));
    static final StructLayout const$5 = MemoryLayout.structLayout(
        MemoryLayout.structLayout(
            JAVA_INT.withName("id"),
            JAVA_INT.withName("width"),
            JAVA_INT.withName("height"),
            JAVA_INT.withName("mipmaps"),
            JAVA_INT.withName("format")
        ).withName("texture"),
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("r"),
            JAVA_BYTE.withName("g"),
            JAVA_BYTE.withName("b"),
            JAVA_BYTE.withName("a")
        ).withName("color"),
        JAVA_FLOAT.withName("value")
    ).withName("MaterialMap");
}

