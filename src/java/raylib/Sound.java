// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct Sound {
 *     AudioStream stream;
 *     unsigned int frameCount;
 * };
 * }
 */
public class Sound {

    public static MemoryLayout $LAYOUT() {
        return constants$22.const$1;
    }
    public static MemorySegment stream$slice(MemorySegment seg) {
        return seg.asSlice(0, 32);
    }
    public static VarHandle frameCount$VH() {
        return constants$22.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned int frameCount;
     * }
     */
    public static int frameCount$get(MemorySegment seg) {
        return (int)constants$22.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned int frameCount;
     * }
     */
    public static void frameCount$set(MemorySegment seg, int x) {
        constants$22.const$2.set(seg, x);
    }
    public static int frameCount$get(MemorySegment seg, long index) {
        return (int)constants$22.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void frameCount$set(MemorySegment seg, long index, int x) {
        constants$22.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}


