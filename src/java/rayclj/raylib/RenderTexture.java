// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct RenderTexture {
 *     unsigned int id;
 *     Texture texture;
 *     Texture depth;
 * };
 * }
 */
public class RenderTexture {

    public static MemoryLayout $LAYOUT() {
        return constants$8.const$3;
    }
    public static VarHandle id$VH() {
        return constants$8.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned int id;
     * }
     */
    public static int id$get(MemorySegment seg) {
        return (int)constants$8.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned int id;
     * }
     */
    public static void id$set(MemorySegment seg, int x) {
        constants$8.const$4.set(seg, x);
    }
    public static int id$get(MemorySegment seg, long index) {
        return (int)constants$8.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void id$set(MemorySegment seg, long index, int x) {
        constants$8.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment texture$slice(MemorySegment seg) {
        return seg.asSlice(4, 20);
    }
    public static MemorySegment depth$slice(MemorySegment seg) {
        return seg.asSlice(24, 20);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

