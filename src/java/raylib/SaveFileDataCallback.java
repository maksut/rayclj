// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * _Bool (*SaveFileDataCallback)(char* fileName,void* data,int dataSize);
 * }
 */
public interface SaveFileDataCallback {

    boolean apply(java.lang.foreign.MemorySegment fileName, java.lang.foreign.MemorySegment data, int dataSize);
    static MemorySegment allocate(SaveFileDataCallback fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$28.const$0, fi, constants$27.const$5, scope);
    }
    static SaveFileDataCallback ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return (java.lang.foreign.MemorySegment _fileName, java.lang.foreign.MemorySegment _data, int _dataSize) -> {
            try {
                return (boolean)constants$28.const$1.invokeExact(symbol, _fileName, _data, _dataSize);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


