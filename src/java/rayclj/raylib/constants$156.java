// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$156 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$156() {}
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
            JAVA_BOOLEAN.withName("looping"),
            MemoryLayout.paddingLayout(3),
            JAVA_INT.withName("ctxType"),
            MemoryLayout.paddingLayout(4),
            RuntimeHelper.POINTER.withName("ctxData")
        ).withName("Music")
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "UnloadMusicStream",
        constants$156.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "PlayMusicStream",
        constants$156.const$0
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "IsMusicStreamPlaying",
        constants$155.const$4
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "UpdateMusicStream",
        constants$156.const$0
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "StopMusicStream",
        constants$156.const$0
    );
}


