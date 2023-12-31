// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct AutomationEvent {
 *     unsigned int frame;
 *     unsigned int type;
 *     int params[4];
 * };
 * }
 */
public class AutomationEvent {

    public static MemoryLayout $LAYOUT() {
        return constants$25.const$4;
    }
    public static VarHandle frame$VH() {
        return constants$25.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned int frame;
     * }
     */
    public static int frame$get(MemorySegment seg) {
        return (int)constants$25.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned int frame;
     * }
     */
    public static void frame$set(MemorySegment seg, int x) {
        constants$25.const$5.set(seg, x);
    }
    public static int frame$get(MemorySegment seg, long index) {
        return (int)constants$25.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void frame$set(MemorySegment seg, long index, int x) {
        constants$25.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle type$VH() {
        return constants$26.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned int type;
     * }
     */
    public static int type$get(MemorySegment seg) {
        return (int)constants$26.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned int type;
     * }
     */
    public static void type$set(MemorySegment seg, int x) {
        constants$26.const$0.set(seg, x);
    }
    public static int type$get(MemorySegment seg, long index) {
        return (int)constants$26.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void type$set(MemorySegment seg, long index, int x) {
        constants$26.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment params$slice(MemorySegment seg) {
        return seg.asSlice(8, 16);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}


