// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$153 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$153() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.ofVoid(
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
        JAVA_FLOAT
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "SetSoundVolume",
        constants$153.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "SetSoundPitch",
        constants$153.const$0
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "SetSoundPan",
        constants$153.const$0
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_INT.withName("frameCount"),
        JAVA_INT.withName("sampleRate"),
        JAVA_INT.withName("sampleSize"),
        JAVA_INT.withName("channels"),
        RuntimeHelper.POINTER.withName("data")
    ).withName("Wave"),
        MemoryLayout.structLayout(
            JAVA_INT.withName("frameCount"),
            JAVA_INT.withName("sampleRate"),
            JAVA_INT.withName("sampleSize"),
            JAVA_INT.withName("channels"),
            RuntimeHelper.POINTER.withName("data")
        ).withName("Wave")
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "WaveCopy",
        constants$153.const$4
    );
}

