// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$55 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$55() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(JAVA_BOOLEAN,
        RuntimeHelper.POINTER,
        JAVA_INT,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "ExportDataAsCode",
        constants$55.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "LoadFileText",
        constants$28.const$2
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "UnloadFileText",
        constants$33.const$4
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "SaveFileText",
        constants$28.const$5
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.of(JAVA_BOOLEAN,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "FileExists",
        constants$55.const$5
    );
}


