// Generated by jextract

package rayclj.rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$19 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$19() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rlClearColor",
        constants$10.const$2
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rlClearScreenBuffers",
        constants$6.const$2
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "rlCheckErrors",
        constants$6.const$2
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rlSetBlendMode",
        constants$6.const$0
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "rlSetBlendFactors",
        constants$13.const$2
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.ofVoid(
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "rlSetBlendFactorsSeparate",
        constants$19.const$5
    );
}

