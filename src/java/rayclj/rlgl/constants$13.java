// Generated by jextract

package rayclj.rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$13 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$13() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rlEnableTextureCubemap",
        constants$6.const$0
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rlDisableTextureCubemap",
        constants$6.const$2
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.ofVoid(
        JAVA_INT,
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rlTextureParameters",
        constants$13.const$2
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "rlCubemapParameters",
        constants$13.const$2
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "rlEnableShader",
        constants$6.const$0
    );
}


