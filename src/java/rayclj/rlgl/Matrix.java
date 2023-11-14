// Generated by jextract

package rayclj.rlgl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct Matrix {
 *     float m0;
 *     float m4;
 *     float m8;
 *     float m12;
 *     float m1;
 *     float m5;
 *     float m9;
 *     float m13;
 *     float m2;
 *     float m6;
 *     float m10;
 *     float m14;
 *     float m3;
 *     float m7;
 *     float m11;
 *     float m15;
 * };
 * }
 */
public class Matrix {

    public static MemoryLayout $LAYOUT() {
        return constants$0.const$0;
    }
    public static VarHandle m0$VH() {
        return constants$0.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m0;
     * }
     */
    public static float m0$get(MemorySegment seg) {
        return (float)constants$0.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m0;
     * }
     */
    public static void m0$set(MemorySegment seg, float x) {
        constants$0.const$1.set(seg, x);
    }
    public static float m0$get(MemorySegment seg, long index) {
        return (float)constants$0.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void m0$set(MemorySegment seg, long index, float x) {
        constants$0.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m4$VH() {
        return constants$0.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m4;
     * }
     */
    public static float m4$get(MemorySegment seg) {
        return (float)constants$0.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m4;
     * }
     */
    public static void m4$set(MemorySegment seg, float x) {
        constants$0.const$2.set(seg, x);
    }
    public static float m4$get(MemorySegment seg, long index) {
        return (float)constants$0.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void m4$set(MemorySegment seg, long index, float x) {
        constants$0.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m8$VH() {
        return constants$0.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m8;
     * }
     */
    public static float m8$get(MemorySegment seg) {
        return (float)constants$0.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m8;
     * }
     */
    public static void m8$set(MemorySegment seg, float x) {
        constants$0.const$3.set(seg, x);
    }
    public static float m8$get(MemorySegment seg, long index) {
        return (float)constants$0.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void m8$set(MemorySegment seg, long index, float x) {
        constants$0.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m12$VH() {
        return constants$0.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m12;
     * }
     */
    public static float m12$get(MemorySegment seg) {
        return (float)constants$0.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m12;
     * }
     */
    public static void m12$set(MemorySegment seg, float x) {
        constants$0.const$4.set(seg, x);
    }
    public static float m12$get(MemorySegment seg, long index) {
        return (float)constants$0.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void m12$set(MemorySegment seg, long index, float x) {
        constants$0.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m1$VH() {
        return constants$0.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m1;
     * }
     */
    public static float m1$get(MemorySegment seg) {
        return (float)constants$0.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m1;
     * }
     */
    public static void m1$set(MemorySegment seg, float x) {
        constants$0.const$5.set(seg, x);
    }
    public static float m1$get(MemorySegment seg, long index) {
        return (float)constants$0.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void m1$set(MemorySegment seg, long index, float x) {
        constants$0.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m5$VH() {
        return constants$1.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m5;
     * }
     */
    public static float m5$get(MemorySegment seg) {
        return (float)constants$1.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m5;
     * }
     */
    public static void m5$set(MemorySegment seg, float x) {
        constants$1.const$0.set(seg, x);
    }
    public static float m5$get(MemorySegment seg, long index) {
        return (float)constants$1.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void m5$set(MemorySegment seg, long index, float x) {
        constants$1.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m9$VH() {
        return constants$1.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m9;
     * }
     */
    public static float m9$get(MemorySegment seg) {
        return (float)constants$1.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m9;
     * }
     */
    public static void m9$set(MemorySegment seg, float x) {
        constants$1.const$1.set(seg, x);
    }
    public static float m9$get(MemorySegment seg, long index) {
        return (float)constants$1.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void m9$set(MemorySegment seg, long index, float x) {
        constants$1.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m13$VH() {
        return constants$1.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m13;
     * }
     */
    public static float m13$get(MemorySegment seg) {
        return (float)constants$1.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m13;
     * }
     */
    public static void m13$set(MemorySegment seg, float x) {
        constants$1.const$2.set(seg, x);
    }
    public static float m13$get(MemorySegment seg, long index) {
        return (float)constants$1.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void m13$set(MemorySegment seg, long index, float x) {
        constants$1.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m2$VH() {
        return constants$1.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m2;
     * }
     */
    public static float m2$get(MemorySegment seg) {
        return (float)constants$1.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m2;
     * }
     */
    public static void m2$set(MemorySegment seg, float x) {
        constants$1.const$3.set(seg, x);
    }
    public static float m2$get(MemorySegment seg, long index) {
        return (float)constants$1.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void m2$set(MemorySegment seg, long index, float x) {
        constants$1.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m6$VH() {
        return constants$1.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m6;
     * }
     */
    public static float m6$get(MemorySegment seg) {
        return (float)constants$1.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m6;
     * }
     */
    public static void m6$set(MemorySegment seg, float x) {
        constants$1.const$4.set(seg, x);
    }
    public static float m6$get(MemorySegment seg, long index) {
        return (float)constants$1.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void m6$set(MemorySegment seg, long index, float x) {
        constants$1.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m10$VH() {
        return constants$1.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m10;
     * }
     */
    public static float m10$get(MemorySegment seg) {
        return (float)constants$1.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m10;
     * }
     */
    public static void m10$set(MemorySegment seg, float x) {
        constants$1.const$5.set(seg, x);
    }
    public static float m10$get(MemorySegment seg, long index) {
        return (float)constants$1.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void m10$set(MemorySegment seg, long index, float x) {
        constants$1.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m14$VH() {
        return constants$2.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m14;
     * }
     */
    public static float m14$get(MemorySegment seg) {
        return (float)constants$2.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m14;
     * }
     */
    public static void m14$set(MemorySegment seg, float x) {
        constants$2.const$0.set(seg, x);
    }
    public static float m14$get(MemorySegment seg, long index) {
        return (float)constants$2.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void m14$set(MemorySegment seg, long index, float x) {
        constants$2.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m3$VH() {
        return constants$2.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m3;
     * }
     */
    public static float m3$get(MemorySegment seg) {
        return (float)constants$2.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m3;
     * }
     */
    public static void m3$set(MemorySegment seg, float x) {
        constants$2.const$1.set(seg, x);
    }
    public static float m3$get(MemorySegment seg, long index) {
        return (float)constants$2.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void m3$set(MemorySegment seg, long index, float x) {
        constants$2.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m7$VH() {
        return constants$2.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m7;
     * }
     */
    public static float m7$get(MemorySegment seg) {
        return (float)constants$2.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m7;
     * }
     */
    public static void m7$set(MemorySegment seg, float x) {
        constants$2.const$2.set(seg, x);
    }
    public static float m7$get(MemorySegment seg, long index) {
        return (float)constants$2.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void m7$set(MemorySegment seg, long index, float x) {
        constants$2.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m11$VH() {
        return constants$2.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m11;
     * }
     */
    public static float m11$get(MemorySegment seg) {
        return (float)constants$2.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m11;
     * }
     */
    public static void m11$set(MemorySegment seg, float x) {
        constants$2.const$3.set(seg, x);
    }
    public static float m11$get(MemorySegment seg, long index) {
        return (float)constants$2.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void m11$set(MemorySegment seg, long index, float x) {
        constants$2.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m15$VH() {
        return constants$2.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * float m15;
     * }
     */
    public static float m15$get(MemorySegment seg) {
        return (float)constants$2.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * float m15;
     * }
     */
    public static void m15$set(MemorySegment seg, float x) {
        constants$2.const$4.set(seg, x);
    }
    public static float m15$get(MemorySegment seg, long index) {
        return (float)constants$2.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void m15$set(MemorySegment seg, long index, float x) {
        constants$2.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

