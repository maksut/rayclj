// Generated by jextract

package rayclj.rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$30 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$30() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rlFramebufferComplete",
        constants$11.const$0
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rlUnloadFramebuffer",
        constants$6.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rlLoadShaderCode",
        constants$30.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "rlCompileShader",
        constants$30.const$4
    );
}


