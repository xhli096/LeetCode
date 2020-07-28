package com.xinghaol.guidedubbo.framework.simple.remoting.transport.socket;

import com.xinghaol.guidedubbo.framework.common.factory.SingletonFactory;
import com.xinghaol.guidedubbo.framework.common.util.ThreadPoolFactoryUtil;
import com.xinghaol.guidedubbo.framework.model.RpcServiceProperties;
import com.xinghaol.guidedubbo.framework.simple.config.CustomShutdownHook;
import com.xinghaol.guidedubbo.framework.simple.provider.ServiceProvider;
import com.xinghaol.guidedubbo.framework.simple.provider.ServiceProviderImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * @author: lixinghao
 * @date: 2020/7/28 2:56 下午
 * @Description: socket rpc 传输服务器
 */
@Slf4j
public class SocketRpcServer {
    private final ExecutorService executorService;
    private final ServiceProvider serviceProvider;

    public SocketRpcServer() {
        this.executorService = ThreadPoolFactoryUtil.createCustomThreadPoolIfAbsent("socket-server-rpc-pool");
        this.serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);
    }

    /**
     * socket 协议下发布service
     *
     * @param service
     */
    public void registerService(Object service) {
        serviceProvider.publishService(service);
    }

    /**
     * socket 协议下发布service
     *
     * @param service
     * @param rpcServiceProperties
     */
    public void registerService(Object service, RpcServiceProperties rpcServiceProperties) {
        serviceProvider.publishService(service, rpcServiceProperties);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket()) {
            String host = InetAddress.getLocalHost().getHostAddress();
            // TODO 修改名称
            serverSocket.bind(new InetSocketAddress(host, 8080));
            // 先把之前的所有服务清除掉
            CustomShutdownHook.getCustomShutdownHook().clearAll();
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                log.info("client connected [{}]", socket.getInetAddress());
                executorService.execute(new SocketRpcRequestHandlerRunnable(socket));
            }
            executorService.shutdown();
        } catch (IOException e) {
            log.error("occur IOException:", e);
        }
    }
}
