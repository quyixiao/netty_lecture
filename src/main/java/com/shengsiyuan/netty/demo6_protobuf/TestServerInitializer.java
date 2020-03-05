package com.shengsiyuan.netty.demo6_protobuf;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

//Spring MVC
// Struts2
// @GetMapping (... = "/users/user/1233")
// PostMapping(... = "/users/user/")
// DispatcherServlet
// 控制器
// Netting 一定是在网络的底层里面，没有像其他的框架一样，在网络的顶层
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 这是针对protobuf协议的ProtobufVarint32LengthFieldPrepender()所加的长度属性的解码器
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //
        pipeline.addLast(new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
        //对protobuf协议的的消息头上加上一个长度为32的整形字段，用于标志这个消息的长度。
        //这里是官方举的例子，实际这个字段的长度是5byte
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());

        pipeline.addLast(new TestServerHandler());
    }
}
