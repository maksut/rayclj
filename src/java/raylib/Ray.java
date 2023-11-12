// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct Ray {
 *     Vector3 position;
 *     Vector3 direction;
 * };
 * }
 */
public class Ray {

    public static MemoryLayout $LAYOUT() {
        return constants$19.const$2;
    }
    public static MemorySegment position$slice(MemorySegment seg) {
        return seg.asSlice(0, 12);
    }
    public static MemorySegment direction$slice(MemorySegment seg) {
        return seg.asSlice(12, 12);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}


