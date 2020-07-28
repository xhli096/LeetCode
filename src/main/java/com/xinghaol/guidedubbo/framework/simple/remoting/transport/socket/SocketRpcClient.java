package com.xinghaol.guidedubbo.framework.simple.remoting.transport.socket;

import com.xinghaol.guidedubbo.framework.common.exception.RpcException;
import com.xinghaol.guidedubbo.framework.model.RpcServiceProperties;
import com.xinghaol.guidedubbo.framework.simple.loadbalance.LoadBalance;
import com.xinghaol.guidedubbo.framework.simple.registry.ServiceDiscovery;
import com.xinghaol.guidedubbo.framework.simple.registry.impl.zk.ZkServiceDiscovery;
import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcRequest;
import com.xinghaol.guidedubbo.framework.simple.remoting.transport.ClientTransport;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: lixinghao
 * @date: 2020/7/28 3:42 下午
 * @Description:
 */
@AllArgsConstructor
public class SocketRpcClient implements ClientTransport {
    private final ServiceDiscovery serviceDiscovery;

    public SocketRpcClient(LoadBalance loadBalance) {
        this.serviceDiscovery = new ZkServiceDiscovery(loadBalance);
    }

    /**
     * 传输消息到服务提供者端，bio的阻塞方式
     *
     * @param rpcRequest 消息体
     * @return 返回数据
     */
    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        String serviceName = RpcServiceProperties.builder().serviceName(rpcRequest.getInterfaceName())
                .group(rpcRequest.getGroup()).version(rpcRequest.getVersion()).build().toRpcServiceName();
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(serviceName);
        try (Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RpcException("调用服务失败：", e);
        }
    }
}
