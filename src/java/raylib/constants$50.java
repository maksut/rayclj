// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$50 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$50() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(JAVA_DOUBLE);
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "GetTime",
        constants$50.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "GetFPS",
        constants$35.const$5
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "SwapScreenBuffer",
        constants$29.const$4
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "PollInputEvents",
        constants$29.const$4
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.ofVoid(
        JAVA_DOUBLE
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "WaitTime",
        constants$50.const$5
    );
}


