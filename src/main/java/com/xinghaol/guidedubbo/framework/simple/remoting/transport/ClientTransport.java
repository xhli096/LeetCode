package com.xinghaol.guidedubbo.framework.simple.remoting.transport;

import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcRequest;

/**
 * @author: lixinghao
 * @date: 2020/7/28 2:49 下午
 * @Description: rpc协议传输
 */
public interface ClientTransport {
    /**
     * 传输消息到服务提供者端
     *
     * @param rpcRequest 消息体
     * @return 返回数据
     */
    Object sendRpcRequest(RpcRequest rpcRequest);
}
