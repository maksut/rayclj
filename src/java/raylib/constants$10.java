// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$10 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$10() {}
    static final VarHandle const$0 = constants$9.const$5.varHandle(MemoryLayout.PathElement.groupElement("value"));
    static final VarHandle const$1 = constants$9.const$5.varHandle(MemoryLayout.PathElement.groupElement("offsetX"));
    static final VarHandle const$2 = constants$9.const$5.varHandle(MemoryLayout.PathElement.groupElement("offsetY"));
    static final VarHandle const$3 = constants$9.const$5.varHandle(MemoryLayout.PathElement.groupElement("advanceX"));
    static final StructLayout const$4 = MemoryLayout.structLayout(
        JAVA_INT.withName("baseSize"),
        JAVA_INT.withName("glyphCount"),
        JAVA_INT.withName("glyphPadding"),
        MemoryLayout.structLayout(
            JAVA_INT.withName("id"),
            JAVA_INT.withName("width"),
            JAVA_INT.withName("height"),
            JAVA_INT.withName("mipmaps"),
            JAVA_INT.withName("format")
        ).withName("texture"),
        RuntimeHelper.POINTER.withName("recs"),
        RuntimeHelper.POINTER.withName("glyphs")
    ).withName("Font");
    static final VarHandle const$5 = constants$10.const$4.varHandle(MemoryLayout.PathElement.groupElement("baseSize"));
}


