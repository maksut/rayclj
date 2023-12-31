// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * unsigned char* (*LoadFileDataCallback)(char* fileName,int* dataSize);
 * }
 */
public interface LoadFileDataCallback {

    java.lang.foreign.MemorySegment apply(java.lang.foreign.MemorySegment fileName, java.lang.foreign.MemorySegment dataSize);
    static MemorySegment allocate(LoadFileDataCallback fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$27.const$3, fi, constants$27.const$2, scope);
    }
    static LoadFileDataCallback ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return (java.lang.foreign.MemorySegment _fileName, java.lang.foreign.MemorySegment _dataSize) -> {
            try {
                return (java.lang.foreign.MemorySegment)constants$27.const$4.invokeExact(symbol, _fileName, _dataSize);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


