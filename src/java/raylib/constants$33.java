// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$33 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$33() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.ofVoid(
        MemoryLayout.structLayout(
            RuntimeHelper.POINTER.withName("data"),
            JAVA_INT.withName("width"),
            JAVA_INT.withName("height"),
            JAVA_INT.withName("mipmaps"),
            JAVA_INT.withName("format")
        ).withName("Image")
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "SetWindowIcon",
        constants$33.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.ofVoid(
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "SetWindowIcons",
        constants$33.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "SetWindowTitle",
        constants$33.const$4
    );
}

