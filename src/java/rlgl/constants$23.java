// Generated by jextract

package rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$23 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$23() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rlDrawRenderBatchActive",
        constants$6.const$2
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rlCheckRenderBatchLimit",
        constants$11.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "rlSetTexture",
        constants$6.const$0
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rlLoadVertexArray",
        constants$20.const$3
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_INT,
        JAVA_BOOLEAN
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "rlLoadVertexBuffer",
        constants$23.const$4
    );
}


