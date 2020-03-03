package com.shengsiyuan.netty.demo4_heatbeat;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 责任链模式
        //  在一定的时间之内，如果没有读，也没有写，那么，我们称为
        // * Triggers an {@link IdleStateEvent} when a {@link Channel} has not performed
        // * read, write, or both operation for a while.
        pipeline.addLast(new IdleStateHandler(5, 7, 3, TimeUnit.SECONDS));
        //
        pipeline.addLast(new MyServerHandler());
    }
}
