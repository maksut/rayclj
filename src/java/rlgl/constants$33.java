// Generated by jextract

package rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$33 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$33() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rlComputeShaderDispatch",
        constants$13.const$2
    );
    static final FunctionDescriptor const$1 = FunctionDescriptor.of(JAVA_INT,
        JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "rlLoadShaderBuffer",
        constants$33.const$1
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rlUnloadShaderBuffer",
        constants$6.const$0
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "rlUpdateShaderBuffer",
        constants$24.const$1
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "rlBindShaderBuffer",
        constants$9.const$1
    );
}

