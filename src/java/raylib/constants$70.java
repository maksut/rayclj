// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$70 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$70() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "GetGestureDragAngle",
        constants$49.const$4
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "GetGesturePinchVector",
        constants$38.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "GetGesturePinchAngle",
        constants$49.const$4
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "UpdateCamera",
        constants$33.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
        RuntimeHelper.POINTER,
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3"),
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("x"),
            JAVA_FLOAT.withName("y"),
            JAVA_FLOAT.withName("z")
        ).withName("Vector3"),
        JAVA_FLOAT
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "UpdateCameraPro",
        constants$70.const$4
    );
}

