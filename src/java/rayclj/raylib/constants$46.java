// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$46 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$46() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "GetShaderLocationAttrib",
        constants$45.const$5
    );
    static final FunctionDescriptor const$1 = FunctionDescriptor.ofVoid(
        MemoryLayout.structLayout(
            JAVA_INT.withName("id"),
            MemoryLayout.paddingLayout(4),
            RuntimeHelper.POINTER.withName("locs")
        ).withName("Shader"),
        JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "SetShaderValue",
        constants$46.const$1
    );
    static final FunctionDescriptor const$3 = FunctionDescriptor.ofVoid(
        MemoryLayout.structLayout(
            JAVA_INT.withName("id"),
            MemoryLayout.paddingLayout(4),
            RuntimeHelper.POINTER.withName("locs")
        ).withName("Shader"),
        JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "SetShaderValueV",
        constants$46.const$3
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.ofVoid(
        MemoryLayout.structLayout(
            JAVA_INT.withName("id"),
            MemoryLayout.paddingLayout(4),
            RuntimeHelper.POINTER.withName("locs")
        ).withName("Shader"),
        JAVA_INT,
        MemoryLayout.structLayout(
            JAVA_FLOAT.withName("m0"),
            JAVA_FLOAT.withName("m4"),
            JAVA_FLOAT.withName("m8"),
            JAVA_FLOAT.withName("m12"),
            JAVA_FLOAT.withName("m1"),
            JAVA_FLOAT.withName("m5"),
            JAVA_FLOAT.withName("m9"),
            JAVA_FLOAT.withName("m13"),
            JAVA_FLOAT.withName("m2"),
            JAVA_FLOAT.withName("m6"),
            JAVA_FLOAT.withName("m10"),
            JAVA_FLOAT.withName("m14"),
            JAVA_FLOAT.withName("m3"),
            JAVA_FLOAT.withName("m7"),
            JAVA_FLOAT.withName("m11"),
            JAVA_FLOAT.withName("m15")
        ).withName("Matrix")
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "SetShaderValueMatrix",
        constants$46.const$5
    );
}


