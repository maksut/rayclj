// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$53 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$53() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "MemAlloc",
        constants$38.const$3
    );
    static final FunctionDescriptor const$1 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "MemRealloc",
        constants$53.const$1
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "MemFree",
        constants$33.const$4
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "SetTraceLogCallback",
        constants$33.const$4
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "SetLoadFileDataCallback",
        constants$33.const$4
    );
}


