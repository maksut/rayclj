// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$20 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$20() {}
    static final StructLayout const$0 = MemoryLayout.structLayout(
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
    ).withName("BoundingBox");
    static final StructLayout const$1 = MemoryLayout.structLayout(
        JAVA_INT.withName("frameCount"),
        JAVA_INT.withName("sampleRate"),
        JAVA_INT.withName("sampleSize"),
        JAVA_INT.withName("channels"),
        RuntimeHelper.POINTER.withName("data")
    ).withName("Wave");
    static final VarHandle const$2 = constants$20.const$1.varHandle(MemoryLayout.PathElement.groupElement("frameCount"));
    static final VarHandle const$3 = constants$20.const$1.varHandle(MemoryLayout.PathElement.groupElement("sampleRate"));
    static final VarHandle const$4 = constants$20.const$1.varHandle(MemoryLayout.PathElement.groupElement("sampleSize"));
    static final VarHandle const$5 = constants$20.const$1.varHandle(MemoryLayout.PathElement.groupElement("channels"));
}

