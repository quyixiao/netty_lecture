package com.shengsiyuan.demo10_nio;


import java.nio.IntBuffer;
import java.security.SecureRandom;
// java.io
// java.nio
// java.io 中一个最核心的概念就是流，面向流的编程，java 中，一个注要么是输入流，要么是输出流，不可能同时输入流又是输出流
// java.nio 中拥有三个概念，Selector ，channerl与 Buffer ，在 java.nio 中，我们面向块（block ）inputstream
// outputstream
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
