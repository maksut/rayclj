// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$38 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$38() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_FLOAT.withName("x"),
        JAVA_FLOAT.withName("y")
    ).withName("Vector2"));
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "GetWindowPosition",
        constants$38.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "GetWindowScaleDPI",
        constants$38.const$0
    );
    static final FunctionDescriptor const$3 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "GetMonitorName",
        constants$38.const$3
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "SetClipboardText",
        constants$33.const$4
    );
}


