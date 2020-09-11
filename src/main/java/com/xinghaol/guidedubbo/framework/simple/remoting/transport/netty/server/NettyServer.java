package com.xinghaol.guidedubbo.framework.simple.remoting.transport.netty.server;

import com.xinghaol.guidedubbo.framework.common.factory.SingletonFactory;
import com.xinghaol.guidedubbo.framework.model.RpcServiceProperties;
import com.xinghaol.guidedubbo.framework.simple.config.CustomShutdownHook;
import com.xinghaol.guidedubbo.framework.simple.provider.ServiceProvider;
import com.xinghaol.guidedubbo.framework.simple.provider.ServiceProviderImpl;
import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcRequest;
import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcResponse;
import com.xinghaol.guidedubbo.framework.simple.remoting.transport.netty.NettyKryoDecoder;
import com.xinghaol.guidedubbo.framework.simple.remoting.transport.netty.NettyKryoEncoder;
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

    public void registerService(Object service) {
        serviceProvider.publishService(service);
    }

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
                    // TCP默认开启了Nagle算法,该算法的作用是尽可能的发送大数据块,减少网络传输.TCP_NODELAY参数的作用就是控制是否启用Nagle算法
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 是否开启TCP底层心跳机制
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 表示系统用于临时存放已经完成三次握手的请求的队列的最大长度；如果连接建立频繁，服务器处理创建新连接比较慢，可以适当调大这个参数。
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 当客户端第一次请求的时候才会进行初始化
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
