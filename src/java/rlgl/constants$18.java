// Generated by jextract

package rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$18 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$18() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rlEnableSmoothLines",
        constants$6.const$2
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rlDisableSmoothLines",
        constants$6.const$2
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "rlEnableStereoRender",
        constants$6.const$2
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rlDisableStereoRender",
        constants$6.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(JAVA_BOOLEAN);
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "rlIsStereoRenderEnabled",
        constants$18.const$4
    );
}

