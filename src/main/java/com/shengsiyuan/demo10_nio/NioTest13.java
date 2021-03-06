package com.shengsiyuan.demo10_nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class NioTest13 {
    // 将一个字符串，在磁盘上，目标，磁盘存储到我们，字符总是字节一种编码方式，一个字节是一个 byte
    // unicode ,采用了两个字节来表示一个字符
    // UTF,Unicode ，Translation ,Format
    // Unicode 是一种编码方式，而 UTF 则是一种存储方式，UTF-8是 Unicode 的实现方式之一
    // UTF-16LE(littele endian) UTF16-BE (big endian)
    // 如果不可以的放，那就再看看代码就可以了，原理性的东西很快的回复起来的
    // IdleStateHandler是用来做心跳检的，如何知道，两台机器他们之间的联接是怎样的
    // 网络 ，如何去合理的知道这个问题
    //
    public static void main(String[] args) {
        try {
            String inputFile = "/Users/quyixiao/project/netty_lecture/src/NioTest13_In.txt";
            String outputFile = "/Users/quyixiao/project/netty_lecture/src/NioTest13_Out.txt";

            RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
            RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");

            long inputLength = new File(inputFile).length();

            FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
            FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

            MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

            System.out.println("=============");
            // 系统中所有的可用的字符集合

            Charset.availableCharsets().forEach((k, v) -> {
                System.out.println(k + ", " + v);
            });
            System.out.println("=============");
            // 为什么使用 iso-8859-1 就会出现这个问题呢？
            Charset charset = Charset.forName("iso-8859-1");
            CharsetDecoder decoder = charset.newDecoder();
            CharsetEncoder encoder = charset.newEncoder();
            CharBuffer charBuffer = decoder.decode(inputData);

            for(int i = 0;i< charBuffer.length();i ++){
                System.out.println(charBuffer.get(i));
            }
            ByteBuffer outputData = encoder.encode(charBuffer);

            //   ByteBuffer outputData = Charset.forName("utf-8").encode(charBuffer);

            outputFileChannel.write(outputData);

            inputRandomAccessFile.close();
            outputRandomAccessFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
