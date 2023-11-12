// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct AudioStream {
 *     rAudioBuffer* buffer;
 *     rAudioProcessor* processor;
 *     unsigned int sampleRate;
 *     unsigned int sampleSize;
 *     unsigned int channels;
 * };
 * }
 */
public class AudioStream {

    public static MemoryLayout $LAYOUT() {
        return constants$21.const$1;
    }
    public static VarHandle buffer$VH() {
        return constants$21.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * rAudioBuffer* buffer;
     * }
     */
    public static MemorySegment buffer$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$21.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * rAudioBuffer* buffer;
     * }
     */
    public static void buffer$set(MemorySegment seg, MemorySegment x) {
        constants$21.const$2.set(seg, x);
    }
    public static MemorySegment buffer$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$21.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void buffer$set(MemorySegment seg, long index, MemorySegment x) {
        constants$21.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle processor$VH() {
        return constants$21.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * rAudioProcessor* processor;
     * }
     */
    public static MemorySegment processor$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$21.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * rAudioProcessor* processor;
     * }
     */
    public static void processor$set(MemorySegment seg, MemorySegment x) {
        constants$21.const$3.set(seg, x);
    }
    public static MemorySegment processor$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$21.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void processor$set(MemorySegment seg, long index, MemorySegment x) {
        constants$21.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle sampleRate$VH() {
        return constants$21.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned int sampleRate;
     * }
     */
    public static int sampleRate$get(MemorySegment seg) {
        return (int)constants$21.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned int sampleRate;
     * }
     */
    public static void sampleRate$set(MemorySegment seg, int x) {
        constants$21.const$4.set(seg, x);
    }
    public static int sampleRate$get(MemorySegment seg, long index) {
        return (int)constants$21.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void sampleRate$set(MemorySegment seg, long index, int x) {
        constants$21.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle sampleSize$VH() {
        return constants$21.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned int sampleSize;
     * }
     */
    public static int sampleSize$get(MemorySegment seg) {
        return (int)constants$21.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned int sampleSize;
     * }
     */
    public static void sampleSize$set(MemorySegment seg, int x) {
        constants$21.const$5.set(seg, x);
    }
    public static int sampleSize$get(MemorySegment seg, long index) {
        return (int)constants$21.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void sampleSize$set(MemorySegment seg, long index, int x) {
        constants$21.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle channels$VH() {
        return constants$22.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned int channels;
     * }
     */
    public static int channels$get(MemorySegment seg) {
        return (int)constants$22.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned int channels;
     * }
     */
    public static void channels$set(MemorySegment seg, int x) {
        constants$22.const$0.set(seg, x);
    }
    public static int channels$get(MemorySegment seg, long index) {
        return (int)constants$22.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void channels$set(MemorySegment seg, long index, int x) {
        constants$22.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

