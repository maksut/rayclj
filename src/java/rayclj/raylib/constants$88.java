// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$88 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$88() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("data"),
        JAVA_INT.withName("width"),
        JAVA_INT.withName("height"),
        JAVA_INT.withName("mipmaps"),
        JAVA_INT.withName("format")
    ).withName("Image"),
        RuntimeHelper.POINTER,
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "LoadImageSvg",
        constants$88.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("data"),
        JAVA_INT.withName("width"),
        JAVA_INT.withName("height"),
        JAVA_INT.withName("mipmaps"),
        JAVA_INT.withName("format")
    ).withName("Image"),
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "LoadImageAnim",
        constants$88.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("data"),
        JAVA_INT.withName("width"),
        JAVA_INT.withName("height"),
        JAVA_INT.withName("mipmaps"),
        JAVA_INT.withName("format")
    ).withName("Image"),
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "LoadImageFromMemory",
        constants$88.const$4
    );
}


