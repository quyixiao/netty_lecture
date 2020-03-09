package com.shengsiyuan.demo6_protobuf;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
// 使用 Git 作为中版本控制系统
// git submodule :是 Git 仓库里的一个仓库
// ServerProject :
// Protobuf-java :  中间项目
// ClientProject :
// git submodule  本质上是外层仓库里面有一个子仓库，
// branch:
//      develop : 开发的分支
//      test : 测试环境发布的分支
//      master : 生产的分支
// git submodule  中也会有 develop ,test,master
// git subtree ,
// Apache Thirift 使用方式与文件编写方式分析
// Netty 大文件传递支持
// 可扩展的事件模型
// Netty 统一通信
// thrift 容器类型
// list:一系列由 T 类型的数据组成的有序列表，元素
// set
// map
// Thrift 工作原理
// 如何实现多语言之间的通信
// Thrift 工作原理
//  定义 thrift 的文件，由 thrift 文件（IDL） 生成双方语言的接口
// 结构体（struct ）
// 就象 C 言语，Thrift 枚举类型支持自定义 exeption,规则 与 struct 一样
// exception RequestException{
// 1.i32 code ;
// 2.String
// }
// Thrift 定义服务相当于
// Thrift 支持类似C++ 一样的定义typedef 定义：
// typedef i32 int
// typedef
// 常量
// thrift 也支持常量的定义，
// thirft 的命名空间相当于 Java中的 package 的意思，主要目的是组织代码，thrift 相当于
// thrift 也支持文件包含，相当于 C/C++ 中的 include ，Java中的 import
//注释
// Thrift 注释方式支持 shell 风格的注释，支持 C/C++风格的注释，即 #
// Thrift 提供了两个关键字 requeired ，optional ，分别用于表示对应的字段是必还是可选的
// 了解了如何定义 thrift 文件之后，我们需要用定义好的 thrift 文件生成我们需要的目标语言和源码
// 首先需要定义 thrift 代码
//

public class TestServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
