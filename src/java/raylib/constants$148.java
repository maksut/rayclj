// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$148 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$148() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "IsAudioDeviceReady",
        constants$30.const$0
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "SetMasterVolume",
        constants$35.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "GetMasterVolume",
        constants$49.const$4
    );
    static final FunctionDescriptor const$3 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_INT.withName("frameCount"),
        JAVA_INT.withName("sampleRate"),
        JAVA_INT.withName("sampleSize"),
        JAVA_INT.withName("channels"),
        RuntimeHelper.POINTER.withName("data")
    ).withName("Wave"),
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "LoadWave",
        constants$148.const$3
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.of(MemoryLayout.structLayout(
        JAVA_INT.withName("frameCount"),
        JAVA_INT.withName("sampleRate"),
        JAVA_INT.withName("sampleSize"),
        JAVA_INT.withName("channels"),
        RuntimeHelper.POINTER.withName("data")
    ).withName("Wave"),
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "LoadWaveFromMemory",
        constants$148.const$5
    );
}

