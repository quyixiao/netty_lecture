package com.shengsiyuan.demo10_nio;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {

    public static void main(String[] args) throws Exception {
        try {
            FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            fileChannel.read(byteBuffer);
            byteBuffer.flip();

            while(byteBuffer.remaining() > 0) {
                byte b = byteBuffer.get();
                System.out.println("Character: " + (char)b);
            }

            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
