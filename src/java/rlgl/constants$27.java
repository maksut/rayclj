// Generated by jextract

package rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$27 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$27() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rlLoadTextureCubemap",
        constants$27.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.ofVoid(
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rlUpdateTexture",
        constants$27.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
        JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "rlGetGlTextureFormats",
        constants$27.const$4
    );
}


