package com.shengsiyuan.demo10_nio;


import java.nio.ByteBuffer;

/**
 * Slice Buffer与原有buffer共享相同的底层数组
 */
public class NioTest6 {

    public static void main(String[] args) {
        // 这与Java的内存使用机制有关。第一种分配方式产生的内存开销是在JVM中的，而另外一种的分配方式产生的开销在JVM之外，以就是系统级的内存分配。
        // 当Java程序接收到外部传来的数据时，首先是被系统内存所获取，然后在由系统内存复制复制到JVM内存中供Java程序使用。所以在另外一种分配方式中，
        // 能够省去复制这一步操作，效率上会有所提高。可是系统级内存的分配比起JVM内存的分配要耗时得多，所以并非不论什么时候allocateDirect的操作
        // 效率都是最高的。以下是一个不同容量情况下两种分配方式的操作时间对照：
        // 由图能够看出，当操作数据量非常小时，两种分配方式操作使用时间基本是同样的，第一种方式有时可能会更快，可是当数据量非常大时，另外一种
        // 方式会远远大于第一种的分配方式。
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("-----------------" + buffer.capacity());

        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);

        ByteBuffer sliceBuffer = buffer.slice();
        System.out.println("++++++++++++++++++" +  sliceBuffer.capacity());

        for (int i = 0; i < sliceBuffer.capacity(); ++i) {
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
