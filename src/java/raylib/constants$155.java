// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$155 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$155() {}
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
        JAVA_BOOLEAN.withName("looping"),
        MemoryLayout.paddingLayout(3),
        JAVA_INT.withName("ctxType"),
        MemoryLayout.paddingLayout(4),
        RuntimeHelper.POINTER.withName("ctxData")
    ).withName("Music"),
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "LoadMusicStream",
        constants$155.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(MemoryLayout.structLayout(
        MemoryLayout.structLayout(
            RuntimeHelper.POINTER.withName("buffer"),
            RuntimeHelper.POINTER.withName("processor"),
            JAVA_INT.withName("sampleRate"),
            JAVA_INT.withName("sampleSize"),
            JAVA_INT.withName("channels"),
            MemoryLayout.paddingLayout(4)
        ).withName("stream"),
        JAVA_INT.withName("frameCount"),
        JAVA_BOOLEAN.withName("looping"),
        MemoryLayout.paddingLayout(3),
        JAVA_INT.withName("ctxType"),
        MemoryLayout.paddingLayout(4),
        RuntimeHelper.POINTER.withName("ctxData")
    ).withName("Music"),
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "LoadMusicStreamFromMemory",
        constants$155.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(JAVA_BOOLEAN,
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
            JAVA_BOOLEAN.withName("looping"),
            MemoryLayout.paddingLayout(3),
            JAVA_INT.withName("ctxType"),
            MemoryLayout.paddingLayout(4),
            RuntimeHelper.POINTER.withName("ctxData")
        ).withName("Music")
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "IsMusicReady",
        constants$155.const$4
    );
}

