package com.shengsiyuan.demo11_zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    /***
     * 在文件特定位置进行读写操作
     * 将文件一部分直接加载到内存，这样效率更高
     * 以更快的速度将文件数据从一个通道传输到另一个通道
     * 锁定文件的某一部分来限制其他线程访问
     * 为了避免数据丢失，强制立即将更新写入文件并存储
     * 当我们读取一个大文件时，FileChannel比标准I/O执行得更快。需要注意，虽然FileChannel是Java NIO的一部分，
     * 但是FileChannel操作是阻塞的，并且没有非阻塞模式。
     */
    public static void main(String[] args) throws Exception {
        // Reactor 模式的一个体现，
        // 反应器模式
        // Proactor 模式
        // Netty 整体架构 是Reactor 模式的完整模式




        //Channel是对I/O操作的封装。
        // FileChannel配合着ByteBuffer，将读写的数据缓存到内存中，然后以批量/缓存的方式read/write，
        // 省去了非批量操作时的重复中间操作，操纵大文件时可以显著提高效率（和Stream以byte数组方式有什么区别？
        // 经过测试，效率上几乎无区别）。
        // 不过对于运行在容器中的应用需要考虑GC，而ByteBuffer可以使用直接内存（系统内存）（allocateDirect），使用后无需jvm回收。
        // 顾名思义，FileChannel就是连接到文件的Channel。使用FileChannel，你可以读取文件数据，以及往文件里面写入数据。
        // Java NIO的FileChannel是使用标准Java IO读取文件的一种替代方案。


        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        // FileChannel不能被设置非阻塞模式，它总是以阻塞模式运行。
        socketChannel.configureBlocking(true);

        String fileName = "/Users/quyixiao/Desktop/92_精通并发与Netty课程总结与展望.mp4";
        //在你使用FileChannel前，你必须先打开它。你不能直接打开一个FileChannel。
        // 你必须通过一个InputStream、OutputStream或者RandomAccessFile来获得一个FileChannel
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();
        // FileChannel的size()方法返回这个Channel连接的文件大小
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        // FileChannel.force()方法会将Channel里面还未写入的数据全部刷新到磁盘。操作系统可能会将数据缓存在内存里以提升性能，
        // 因此我们无法保证你写入Channel的数据都被写到了磁盘，直到你调用force()方法。
        // force()方法有一个boolean类型的参数，代表是否将文件元数据（比如权限等）也刷新到磁盘。

        System.out.println("发送总字节数：" + transferCount + "，耗时： " + (System.currentTimeMillis() - startTime));
        // 当时使用完FileChannel后，你必须关闭它
        fileChannel.close();
    }
}
