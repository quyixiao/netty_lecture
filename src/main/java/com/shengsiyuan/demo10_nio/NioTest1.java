package com.shengsiyuan.demo10_nio;


import java.nio.IntBuffer;
import java.security.SecureRandom;
// java.io
// java.nio
// java.io 中一个最核心的概念就是流，面向流的编程，java 中，一个注要么是输入流，要么是输出流，不可能同时输入流又是输出流
// java.nio 中拥有三个概念，Selector ，channerl与 Buffer ，在 java.nio 中，我们是面向块（block） 或是缓冲区编程的，Buffer 本身就是一块内存
// 底层实现上，它实际上是个数组，数据是读，写，都是 buffer 来实现。
// 除了数组之外，buffer 还提供了对于数据结构化访问方式，并且可以追踪到系统读写过程
// Java 中有8种原生数据类型都类型都有各自对应的 Buffer 类型，如 IntBuffer，LongBuffer,ByteBuffer 及 CharBuffer 等
// Channel 指的是可以向其写入数据或者是从读取数据结构对象，它类似于 java.io 中的 stream
// 所有的数据读写都是通过 buffer 来进行的，永远都不会出现直接 channerl 写入数据的情况，或者直接从 Channel 读取数据情况
// 与 Stream 不同的是，Channel 是双向的，一个流只可能是 InputStream 或者是 OutputStream ,Channel 打开则可以读取，写入或者是就读写
//  由于 Channel 是双向的，因此它能更加好的反映出底层操作的真实情况，在 Linux,底层操作系统是通道是双向的，
//

public class NioTest1 {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        System.out.println("capacity: " + buffer.capacity());


        for(int i = 0; i < 5; ++i) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        System.out.println("before flip limit: " + buffer.limit());

        buffer.flip();

        System.out.println("after flip limit: " + buffer.limit());

        System.out.println("enter while loop");

        while(buffer.hasRemaining()) {
            System.out.println("position: " + buffer.position());
            System.out.println("limit: " + buffer.limit());
            System.out.println("capacity: " + buffer.capacity());

            System.out.println(buffer.get());
        }
    }
}
