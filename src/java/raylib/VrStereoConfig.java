// Generated by jextract

package raylib;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct VrStereoConfig {
 *     Matrix projection[2];
 *     Matrix viewOffset[2];
 *     float leftLensCenter[2];
 *     float rightLensCenter[2];
 *     float leftScreenCenter[2];
 *     float rightScreenCenter[2];
 *     float scale[2];
 *     float scaleIn[2];
 * };
 * }
 */
public class VrStereoConfig {

    public static MemoryLayout $LAYOUT() {
        return constants$24.const$5;
    }
    public static MemorySegment projection$slice(MemorySegment seg) {
        return seg.asSlice(0, 128);
    }
    public static MemorySegment viewOffset$slice(MemorySegment seg) {
        return seg.asSlice(128, 128);
    }
    public static MemorySegment leftLensCenter$slice(MemorySegment seg) {
        return seg.asSlice(256, 8);
    }
    public static MemorySegment rightLensCenter$slice(MemorySegment seg) {
        return seg.asSlice(264, 8);
    }
    public static MemorySegment leftScreenCenter$slice(MemorySegment seg) {
        return seg.asSlice(272, 8);
    }
    public static MemorySegment rightScreenCenter$slice(MemorySegment seg) {
        return seg.asSlice(280, 8);
    }
    public static MemorySegment scale$slice(MemorySegment seg) {
        return seg.asSlice(288, 8);
    }
    public static MemorySegment scaleIn$slice(MemorySegment seg) {
        return seg.asSlice(296, 8);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}


