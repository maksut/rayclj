// Generated by jextract

package rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct rlVertexBuffer {
 *     int elementCount;
 *     float* vertices;
 *     float* texcoords;
 *     unsigned char* colors;
 *     unsigned int* indices;
 *     unsigned int vaoId;
 *     unsigned int vboId[4];
 * };
 * }
 */
public class rlVertexBuffer {

    public static MemoryLayout $LAYOUT() {
        return constants$2.const$5;
    }
    public static VarHandle elementCount$VH() {
        return constants$3.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int elementCount;
     * }
     */
    public static int elementCount$get(MemorySegment seg) {
        return (int)constants$3.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int elementCount;
     * }
     */
    public static void elementCount$set(MemorySegment seg, int x) {
        constants$3.const$0.set(seg, x);
    }
    public static int elementCount$get(MemorySegment seg, long index) {
        return (int)constants$3.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void elementCount$set(MemorySegment seg, long index, int x) {
        constants$3.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle vertices$VH() {
        return constants$3.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float* vertices;
     * }
     */
    public static MemorySegment vertices$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$3.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float* vertices;
     * }
     */
    public static void vertices$set(MemorySegment seg, MemorySegment x) {
        constants$3.const$1.set(seg, x);
    }
    public static MemorySegment vertices$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$3.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void vertices$set(MemorySegment seg, long index, MemorySegment x) {
        constants$3.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle texcoords$VH() {
        return constants$3.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float* texcoords;
     * }
     */
    public static MemorySegment texcoords$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$3.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float* texcoords;
     * }
     */
    public static void texcoords$set(MemorySegment seg, MemorySegment x) {
        constants$3.const$2.set(seg, x);
    }
    public static MemorySegment texcoords$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$3.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void texcoords$set(MemorySegment seg, long index, MemorySegment x) {
        constants$3.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle colors$VH() {
        return constants$3.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned char* colors;
     * }
     */
    public static MemorySegment colors$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$3.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned char* colors;
     * }
     */
    public static void colors$set(MemorySegment seg, MemorySegment x) {
        constants$3.const$3.set(seg, x);
    }
    public static MemorySegment colors$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$3.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void colors$set(MemorySegment seg, long index, MemorySegment x) {
        constants$3.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle indices$VH() {
        return constants$3.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned int* indices;
     * }
     */
    public static MemorySegment indices$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$3.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned int* indices;
     * }
     */
    public static void indices$set(MemorySegment seg, MemorySegment x) {
        constants$3.const$4.set(seg, x);
    }
    public static MemorySegment indices$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$3.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void indices$set(MemorySegment seg, long index, MemorySegment x) {
        constants$3.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle vaoId$VH() {
        return constants$3.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned int vaoId;
     * }
     */
    public static int vaoId$get(MemorySegment seg) {
        return (int)constants$3.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned int vaoId;
     * }
     */
    public static void vaoId$set(MemorySegment seg, int x) {
        constants$3.const$5.set(seg, x);
    }
    public static int vaoId$get(MemorySegment seg, long index) {
        return (int)constants$3.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void vaoId$set(MemorySegment seg, long index, int x) {
        constants$3.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment vboId$slice(MemorySegment seg) {
        return seg.asSlice(44, 16);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

