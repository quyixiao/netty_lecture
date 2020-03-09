package com.shengsiyuan.demo10_nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {
    // 理解原理性的东西，是没有问题的，在工作还是
    // 从FileInputStream 获取到 FileChannel对象
    // 创建 buffer
    //  将数据从 chanell读取到 Buffer 中
    // 绝对方法与相对方法的含义
    //  1.相对方法：limit 的值与 position 的值是运行时获得
    // 2.
    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream("/Users/quyixiao/project/netty_lecture/src/input.txt");
            FileOutputStream outputStream = new FileOutputStream("/Users/quyixiao/project/netty_lecture/src/output.txt");
            FileChannel inputChannel = inputStream.getChannel();
            FileChannel outputChannel = outputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(512);
            while (true) {
                            buffer.clear(); // 如果注释掉该行代码会发生什么情况？

                int read = inputChannel.read(buffer);

                System.out.println("read: " + read);

                if (-1 == read) {

                    break;
                }
                buffer.flip();
                outputChannel.write(buffer);
            }
            inputChannel.close();
            outputChannel.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
