// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * void (*AudioCallback)(void* bufferData,unsigned int frames);
 * }
 */
public interface AudioCallback {

    void apply(java.lang.foreign.MemorySegment bufferData, int frames);
    static MemorySegment allocate(AudioCallback fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$147.const$2, fi, constants$33.const$2, scope);
    }
    static AudioCallback ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return (java.lang.foreign.MemorySegment _bufferData, int _frames) -> {
            try {
                constants$147.const$3.invokeExact(symbol, _bufferData, _frames);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


