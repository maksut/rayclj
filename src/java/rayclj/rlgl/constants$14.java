// Generated by jextract

package rayclj.rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$14 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$14() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rlDisableShader",
        constants$6.const$2
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rlEnableFramebuffer",
        constants$6.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "rlDisableFramebuffer",
        constants$6.const$2
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rlActiveDrawBuffers",
        constants$6.const$0
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "rlBlitFramebuffer",
        constants$14.const$4
    );
}


