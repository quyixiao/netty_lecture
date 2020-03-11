package com.shengsiyuan.demo11_zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {

    public static void main(String[] args) throws Exception{
        InetSocketAddress address = new InetSocketAddress(8899);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        //启动端口重用，这行代码一定要放到绑定端口前。
        serverSocket.setReuseAddress(true);
        serverSocket.bind(address);
        // 分配10字节大小内存空间
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            //
            socketChannel.configureBlocking(true);

            int readCount = 0;

            while (-1 != readCount) {
                try {
                    readCount = socketChannel.read(byteBuffer);
                    System.out.println(readCount);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // 将参数设置为position=0，mark=-1，limit的值不变(注意：指针指向0)。
                //调用rewind()之前指针指向下标9即位置10,已经是最大容量
                //调用rewind()之后将指针移动到下标0即位置1
                byteBuffer.rewind();
            }
        }

    }
}
