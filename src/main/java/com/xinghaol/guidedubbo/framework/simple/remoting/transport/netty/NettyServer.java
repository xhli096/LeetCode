package com.xinghaol.guidedubbo.framework.simple.remoting.transport.netty;

import com.xinghaol.guidedubbo.framework.common.factory.SingletonFactory;
import com.xinghaol.guidedubbo.framework.model.RpcServiceProperties;
import com.xinghaol.guidedubbo.framework.simple.config.CustomShutdownHook;
import com.xinghaol.guidedubbo.framework.simple.provider.ServiceProvider;
import com.xinghaol.guidedubbo.framework.simple.provider.ServiceProviderImpl;
import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcRequest;
import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcResponse;
import com.xinghaol.guidedubbo.framework.simple.serializer.kyro.KryoSerializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author: lixinghao
 * @date: 2020/7/30 11:32 下午
 * @Description: netty server，接受客户端的消息，根据客户端的消息调用某个方法，并得到返回值
 */
@Slf4j
public class NettyServer implements InitializingBean {
    private final KryoSerializer kryoSerializer = new KryoSerializer();
    private static final Integer PORT = 9998;

    private final ServiceProvider serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);

    public void registerService(Object service, RpcServiceProperties rpcServiceProperties) {
        serviceProvider.publishService(service, rpcServiceProperties);
    }

    /**
     * 开启服务器
     */
    @SneakyThrows
    public void start() {
        String host = InetAddress.getLocalHost().getHostAddress();
        // Netty 的调度模块称为 EventLoopGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 30 秒之内没有收到客户端请求的话就关闭连接
                            ch.pipeline().addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new NettyKryoDecoder(kryoSerializer, RpcRequest.class));
                            ch.pipeline().addLast(new NettyKryoEncoder(kryoSerializer, RpcResponse.class));
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            // 同步绑定端口，等待成功
            ChannelFuture future = bootstrap.bind(host, PORT).sync();
            // 等待服务端看关闭端口
            future.channel().closeFuture().sync();
        } finally {
            log.error("shutdown bossGroup and workerGroup");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        CustomShutdownHook.getCustomShutdownHook().clearAll();
    }
}
