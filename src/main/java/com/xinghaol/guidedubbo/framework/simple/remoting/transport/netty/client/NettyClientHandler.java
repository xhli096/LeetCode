package com.xinghaol.guidedubbo.framework.simple.remoting.transport.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: lixinghao
 * @date: 2020/8/3 5:45 下午
 * @Description: 自定义netty client，用来处理由server返回来的数据
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private final ChannelProvider channelProvider;

    public NettyClientHandler(ChannelProvider channelProvider) {
        this.channelProvider = channelProvider;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
