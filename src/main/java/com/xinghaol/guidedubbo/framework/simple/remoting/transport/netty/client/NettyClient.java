package com.xinghaol.guidedubbo.framework.simple.remoting.transport.netty.client;

import com.xinghaol.guidedubbo.framework.simple.serializer.kyro.KryoSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: lixinghao
 * @date: 2020/8/1 8:02 下午
 * @Description: 初始化、关闭 bootstrap 对象
 */
public class NettyClient {
    /**
     * 负责初始话netty服务器，并且开始监听端口的socket请求
     */
    private final Bootstrap bootstrap;
    /**
     * Netty 的调度模块称为 EventLoopGroup
     */
    private final EventLoopGroup eventLoopGroup;

    public NettyClient() {
        // 每一个client都有一个，不是单例的
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        KryoSerializer kryoSerializer = new KryoSerializer();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                //
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
    }
}
