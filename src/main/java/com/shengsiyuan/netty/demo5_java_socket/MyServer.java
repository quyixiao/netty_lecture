package com.shengsiyuan.netty.demo5_java_socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

// rmi:remote method invocation
// client:
// server:
//  序列化与反序列化 也叫做 ：编码与解码
// RPC :remote Procedure Call ， 远程过程调用,很多的 RPC 框架是跨语言的
// 1.定义一个接口说明文件（idl）:描述了对象，（结构体），对象成员，接口方法等一系列的信息
// 2. 通过 RPC 框架所提供的编译器将接口说明文件编译成具体语言文件
// 3. 在客户端与服务端分别引入 RPC编译器所生成的文件，即可像调用本地方法一样的被调用
// 英文文档是可以过去的，但是如何的实现这个东西呢？ 但是实现这个东西是不一样的，

public class MyServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new WebSocketChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();

            workerGroup.shutdownGracefully();
        }
    }
}
