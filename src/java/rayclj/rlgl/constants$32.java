// Generated by jextract

package rayclj.rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$32 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$32() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.ofVoid(
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
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rlSetUniformMatrix",
        constants$32.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "rlSetUniformSampler",
        constants$9.const$1
    );
    static final FunctionDescriptor const$3 = FunctionDescriptor.ofVoid(
        JAVA_INT,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "rlSetShader",
        constants$32.const$3
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.of(JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "rlLoadComputeShaderProgram",
        constants$32.const$5
    );
}

