// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$26 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$26() {}
    static final VarHandle const$0 = constants$25.const$4.varHandle(MemoryLayout.PathElement.groupElement("type"));
    static final StructLayout const$1 = MemoryLayout.structLayout(
        JAVA_INT.withName("capacity"),
        JAVA_INT.withName("count"),
        RuntimeHelper.POINTER.withName("events")
    ).withName("AutomationEventList");
    static final VarHandle const$2 = constants$26.const$1.varHandle(MemoryLayout.PathElement.groupElement("capacity"));
    static final VarHandle const$3 = constants$26.const$1.varHandle(MemoryLayout.PathElement.groupElement("count"));
    static final VarHandle const$4 = constants$26.const$1.varHandle(MemoryLayout.PathElement.groupElement("events"));
    static final FunctionDescriptor const$5 = FunctionDescriptor.ofVoid(
        JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
}


