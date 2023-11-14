// Generated by jextract

package rayclj.raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct ModelAnimation {
 *     int boneCount;
 *     int frameCount;
 *     BoneInfo* bones;
 *     Transform** framePoses;
 *     char name[32];
 * };
 * }
 */
public class ModelAnimation {

    public static MemoryLayout $LAYOUT() {
        return constants$18.const$3;
    }
    public static VarHandle boneCount$VH() {
        return constants$18.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int boneCount;
     * }
     */
    public static int boneCount$get(MemorySegment seg) {
        return (int)constants$18.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int boneCount;
     * }
     */
    public static void boneCount$set(MemorySegment seg, int x) {
        constants$18.const$4.set(seg, x);
    }
    public static int boneCount$get(MemorySegment seg, long index) {
        return (int)constants$18.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void boneCount$set(MemorySegment seg, long index, int x) {
        constants$18.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle frameCount$VH() {
        return constants$18.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int frameCount;
     * }
     */
    public static int frameCount$get(MemorySegment seg) {
        return (int)constants$18.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int frameCount;
     * }
     */
    public static void frameCount$set(MemorySegment seg, int x) {
        constants$18.const$5.set(seg, x);
    }
    public static int frameCount$get(MemorySegment seg, long index) {
        return (int)constants$18.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void frameCount$set(MemorySegment seg, long index, int x) {
        constants$18.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle bones$VH() {
        return constants$19.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * BoneInfo* bones;
     * }
     */
    public static MemorySegment bones$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$19.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * BoneInfo* bones;
     * }
     */
    public static void bones$set(MemorySegment seg, MemorySegment x) {
        constants$19.const$0.set(seg, x);
    }
    public static MemorySegment bones$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$19.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void bones$set(MemorySegment seg, long index, MemorySegment x) {
        constants$19.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle framePoses$VH() {
        return constants$19.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * Transform** framePoses;
     * }
     */
    public static MemorySegment framePoses$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$19.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * Transform** framePoses;
     * }
     */
    public static void framePoses$set(MemorySegment seg, MemorySegment x) {
        constants$19.const$1.set(seg, x);
    }
    public static MemorySegment framePoses$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$19.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void framePoses$set(MemorySegment seg, long index, MemorySegment x) {
        constants$19.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment name$slice(MemorySegment seg) {
        return seg.asSlice(24, 32);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

