// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct Image {
 *     void* data;
 *     int width;
 *     int height;
 *     int mipmaps;
 *     int format;
 * };
 * }
 */
public class Image {

    public static MemoryLayout $LAYOUT() {
        return constants$6.const$3;
    }
    public static VarHandle data$VH() {
        return constants$6.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * void* data;
     * }
     */
    public static MemorySegment data$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$6.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * void* data;
     * }
     */
    public static void data$set(MemorySegment seg, MemorySegment x) {
        constants$6.const$4.set(seg, x);
    }
    public static MemorySegment data$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$6.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void data$set(MemorySegment seg, long index, MemorySegment x) {
        constants$6.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle width$VH() {
        return constants$6.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int width;
     * }
     */
    public static int width$get(MemorySegment seg) {
        return (int)constants$6.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int width;
     * }
     */
    public static void width$set(MemorySegment seg, int x) {
        constants$6.const$5.set(seg, x);
    }
    public static int width$get(MemorySegment seg, long index) {
        return (int)constants$6.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void width$set(MemorySegment seg, long index, int x) {
        constants$6.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle height$VH() {
        return constants$7.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int height;
     * }
     */
    public static int height$get(MemorySegment seg) {
        return (int)constants$7.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int height;
     * }
     */
    public static void height$set(MemorySegment seg, int x) {
        constants$7.const$0.set(seg, x);
    }
    public static int height$get(MemorySegment seg, long index) {
        return (int)constants$7.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void height$set(MemorySegment seg, long index, int x) {
        constants$7.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle mipmaps$VH() {
        return constants$7.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int mipmaps;
     * }
     */
    public static int mipmaps$get(MemorySegment seg) {
        return (int)constants$7.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int mipmaps;
     * }
     */
    public static void mipmaps$set(MemorySegment seg, int x) {
        constants$7.const$1.set(seg, x);
    }
    public static int mipmaps$get(MemorySegment seg, long index) {
        return (int)constants$7.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void mipmaps$set(MemorySegment seg, long index, int x) {
        constants$7.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle format$VH() {
        return constants$7.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int format;
     * }
     */
    public static int format$get(MemorySegment seg) {
        return (int)constants$7.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int format;
     * }
     */
    public static void format$set(MemorySegment seg, int x) {
        constants$7.const$2.set(seg, x);
    }
    public static int format$get(MemorySegment seg, long index) {
        return (int)constants$7.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void format$set(MemorySegment seg, long index, int x) {
        constants$7.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}


