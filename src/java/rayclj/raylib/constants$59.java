// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$59 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$59() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "IsFileDropped",
        constants$30.const$0
    );
    static final FunctionDescriptor const$1 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_INT.withName("capacity"),
        JAVA_INT.withName("count"),
        RuntimeHelper.POINTER.withName("paths")
    ).withName("FilePathList"));
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "LoadDroppedFiles",
        constants$59.const$1
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "UnloadDroppedFiles",
        constants$58.const$5
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(JAVA_LONG,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "GetFileModTime",
        constants$59.const$4
    );
}


