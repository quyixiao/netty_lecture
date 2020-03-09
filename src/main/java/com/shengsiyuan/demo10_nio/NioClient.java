package com.shengsiyuan.demo10_nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// NIO实现了IO多路复用的Ractor模型，一个线程Thread使用一个选择器Selector通过轮询的方式去监听多个通道Channel上的事件，找到IO事件已经到达的Channle执行。
// 原因（好处）：因为创建和切换线程的开销很大，因此使用一个线程来处理多个事件而不是一个线程处理一个事件，具有更好的性能
//
public class NioClient {

    public static void main(String[] args) throws IOException {
        try {
            // 通过调用Selector.open()方法创建一个Selector，
            // isOpen() —— 判断Selector是否处于打开状态。Selector对象创建后就处于打开状态了
            // close() —— 当调用了Selector对象的close()方法，就进入关闭状态.。用完Selector后调用其close()方法会关闭该Selector，
            // 且使注册到该Selector上的所有SelectionKey实例无效。通道本身并不会关闭
            //
            SocketChannel socketChannel = SocketChannel.open();
            /***
             注意
             1. 通道必需配置为非阻塞模式，否则使用选择器没有任何意义
                因为如果通道在某个事件上被阻塞，那么服务器就不能响应其他的事情，必等待这个事件处理完毕才能去处理其他的事件
             2.通道主要分为两大类，文件（File）和通道和套接字（Socket） 通道
              涉及主要的类有 FileChannel 类和三个 Socket 通道类，SocketkChannel,ServerSocketChannel,和 DataGramChannel
                应该注意的是：只有套接字 Channel 才能配置为非阻塞，而 FileChannel 不能配置为非阻塞没有意义

             */
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            /****
             *注册的具体的事件，主要有以下的几个类
             * SelectionKey.OP_CONNECT --连接就绪事件，表示客户端与服务器的连接已经建立成功
             * SelectionKey.OP_ACCEPT  --接收连接事件，表示服务器监听到了客户连接，服务器可以接收这个连接了
             * SelectionKey.OP_READ    --读就绪事件，表示通道中已经有有了可读的数据，可以执行读操作了
             * SelectionKey.OP_WRITE   --写就绪事件，表示已经可以向通道写数据了
             *register()返回值 —— SelectionKey,  Selector中的SelectionKey集合
             * 只要ServerSocketChannel及SocketChannel向Selector注册了特定的事件，Selector就会监控这些事件是否发生。
             * SelectableChannel的register()方法返回一个SelectionKey对象，该对象是用于跟踪这些被注册事件的句柄。
             *一个Selector对象会包含3种类型的SelectionKey集合：
             * all-keys集合 —— 当前所有向Selector注册的SelectionKey的集合，Selector的keys()方法返回该集合
             * selected-keys集合 —— 相关事件已经被Selector捕获的SelectionKey的集合，Selector的selectedKeys()方法返回该集合
             * cancelled-keys集合 —— 已经被取消的SelectionKey的集合，Selector没有提供访问这种集合的方法
             *
             */
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            // 创建服务器对象
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

            while (true) {
                //阻塞到至少有一个通道在你注册的选择器上就绪了
                selector.select();
                /**
                 *    selector.keys():当前所有向Selector注册的SelectionKey集合
                 *    selector.selectedKeys()：相关事件已经被Selector捕获的SelectionKey集合
                */
                Set<SelectionKey> keySet = selector.selectedKeys();

                for (SelectionKey selectionKey : keySet) {
                    if (selectionKey.isConnectable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();

                        if (client.isConnectionPending()) {
                            client.finishConnect();

                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                            writeBuffer.put((LocalDateTime.now() + " 连接成功").getBytes());
                            writeBuffer.flip();
                            client.write(writeBuffer);

                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            executorService.submit(() -> {
                                while (true) {
                                    try {
                                        writeBuffer.clear();
                                        InputStreamReader input = new InputStreamReader(System.in);
                                        BufferedReader br = new BufferedReader(input);

                                        String sendMessage = br.readLine();

                                        writeBuffer.put(sendMessage.getBytes());
                                        writeBuffer.flip();
                                        client.write(writeBuffer);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                        }

                        client.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();

                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                        int count = client.read(readBuffer);

                        if (count > 0) {
                            String receivedMessage = new String(readBuffer.array(), 0, count);
                            System.out.println(receivedMessage);
                        }
                    }
                }

                keySet.clear();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
