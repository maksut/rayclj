// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$150 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$150() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(MemoryLayout.structLayout(
        MemoryLayout.structLayout(
            RuntimeHelper.POINTER.withName("buffer"),
            RuntimeHelper.POINTER.withName("processor"),
            JAVA_INT.withName("sampleRate"),
            JAVA_INT.withName("sampleSize"),
            JAVA_INT.withName("channels"),
            MemoryLayout.paddingLayout(4)
        ).withName("stream"),
        JAVA_INT.withName("frameCount"),
        MemoryLayout.paddingLayout(4)
    ).withName("Sound"),
        MemoryLayout.structLayout(
            MemoryLayout.structLayout(
                RuntimeHelper.POINTER.withName("buffer"),
                RuntimeHelper.POINTER.withName("processor"),
                JAVA_INT.withName("sampleRate"),
                JAVA_INT.withName("sampleSize"),
                JAVA_INT.withName("channels"),
                MemoryLayout.paddingLayout(4)
            ).withName("stream"),
            JAVA_INT.withName("frameCount"),
            MemoryLayout.paddingLayout(4)
        ).withName("Sound")
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "LoadSoundAlias",
        constants$150.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(JAVA_BOOLEAN,
        MemoryLayout.structLayout(
            MemoryLayout.structLayout(
                RuntimeHelper.POINTER.withName("buffer"),
                RuntimeHelper.POINTER.withName("processor"),
                JAVA_INT.withName("sampleRate"),
                JAVA_INT.withName("sampleSize"),
                JAVA_INT.withName("channels"),
                MemoryLayout.paddingLayout(4)
            ).withName("stream"),
            JAVA_INT.withName("frameCount"),
            MemoryLayout.paddingLayout(4)
        ).withName("Sound")
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "IsSoundReady",
        constants$150.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
        MemoryLayout.structLayout(
            MemoryLayout.structLayout(
                RuntimeHelper.POINTER.withName("buffer"),
                RuntimeHelper.POINTER.withName("processor"),
                JAVA_INT.withName("sampleRate"),
                JAVA_INT.withName("sampleSize"),
                JAVA_INT.withName("channels"),
                MemoryLayout.paddingLayout(4)
            ).withName("stream"),
            JAVA_INT.withName("frameCount"),
            MemoryLayout.paddingLayout(4)
        ).withName("Sound"),
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "UpdateSound",
        constants$150.const$4
    );
}


