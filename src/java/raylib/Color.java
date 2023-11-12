// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct Color {
 *     unsigned char r;
 *     unsigned char g;
 *     unsigned char b;
 *     unsigned char a;
 * };
 * }
 */
public class Color {

    public static MemoryLayout $LAYOUT() {
        return constants$4.const$5;
    }
    public static VarHandle r$VH() {
        return constants$5.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned char r;
     * }
     */
    public static byte r$get(MemorySegment seg) {
        return (byte)constants$5.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned char r;
     * }
     */
    public static void r$set(MemorySegment seg, byte x) {
        constants$5.const$0.set(seg, x);
    }
    public static byte r$get(MemorySegment seg, long index) {
        return (byte)constants$5.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void r$set(MemorySegment seg, long index, byte x) {
        constants$5.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle g$VH() {
        return constants$5.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned char g;
     * }
     */
    public static byte g$get(MemorySegment seg) {
        return (byte)constants$5.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned char g;
     * }
     */
    public static void g$set(MemorySegment seg, byte x) {
        constants$5.const$1.set(seg, x);
    }
    public static byte g$get(MemorySegment seg, long index) {
        return (byte)constants$5.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void g$set(MemorySegment seg, long index, byte x) {
        constants$5.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle b$VH() {
        return constants$5.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned char b;
     * }
     */
    public static byte b$get(MemorySegment seg) {
        return (byte)constants$5.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned char b;
     * }
     */
    public static void b$set(MemorySegment seg, byte x) {
        constants$5.const$2.set(seg, x);
    }
    public static byte b$get(MemorySegment seg, long index) {
        return (byte)constants$5.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void b$set(MemorySegment seg, long index, byte x) {
        constants$5.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle a$VH() {
        return constants$5.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned char a;
     * }
     */
    public static byte a$get(MemorySegment seg) {
        return (byte)constants$5.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned char a;
     * }
     */
    public static void a$set(MemorySegment seg, byte x) {
        constants$5.const$3.set(seg, x);
    }
    public static byte a$get(MemorySegment seg, long index) {
        return (byte)constants$5.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void a$set(MemorySegment seg, long index, byte x) {
        constants$5.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}


