package com.shengsiyuan.demo4_heatbeat;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
// 课程目标
// 对程序语言设计者来说，设计一个令人满意的 IO 输入输出系统，是件极艰巨的任务
// 摘自《thining in Java》
// 流类
//  流的概念
// Java 程序通过流来完成输入输出的操作
//  输入输出时，数据在通信通道中流动
//  在 java . io 包中提供了60多个类（流）
//  从功能上
//  字节流和字符流
// Java 的原始版本不包括字符流，字节流
//  写，流的分类
//  节点流：从特定的地方读写的流类，例如，磁盘或者一块内存区域
//  过滤流，使用节点流作为输出或者输出，过滤流是使用一个已经存在的输入流或者输出流连接创建的
// InputStream
//          FileInputStream : 中包含了一套字节
//          ByteArrayInputStream
//          FilterInputStream
//          ObjectInputStream ：
//          PipedInputStream
//          SequenceInputStream
//
// 从文件中获取输入字节，增加了缓冲的功能，增加了读取 Java基本的数据类型功能
// Output Stream Chain
//  数据 -> 可往输出流中写入 java 基本的数据类型，提供数据写入缓冲区的功能-> 将数据输出到文件中
// Java 的 IO 库提供了一个称做链接的机制，可以将一个流与另一个流首尾相应，形成一个流管理的链接，这种机制实际是一种被称为
// Decorator(装饰)设计模式的应用
//  装饰模式名包装模式
// 装饰模式以对客户端透明的方式扩展对象的功能，是继承
// 装饰模式的角色
//  抽象构件角色（Component）：给出一个抽象接口，以规范准备接收附加责任的对象
// 具体构件角色（Concrete Component） :定义一个将要接收的附加责任的类
// 装饰对象包含了一个真实对象的引用（reference）
// 装饰对象接收所有的来自客户端的请求，
//
public class MyServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new MyServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();

            workerGroup.shutdownGracefully();
        }
    }
}
