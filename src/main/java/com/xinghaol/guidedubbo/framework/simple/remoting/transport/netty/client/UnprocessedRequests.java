package com.xinghaol.guidedubbo.framework.simple.remoting.transport.netty.client;

import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcResponse;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: lixinghao
 * @date: 2020/8/3 5:47 下午
 * @Description: netty server端不做处理的请求
 */
public class UnprocessedRequests {
    private static final Map<String, CompletableFuture<RpcResponse<Object>>> UNPROCESSED_RESPONSE_FUTURES = new ConcurrentHashMap<>();

    public void put(String requestId, CompletableFuture<RpcResponse<Object>> future) {
        UNPROCESSED_RESPONSE_FUTURES.put(requestId, future);
    }

    public void complete(RpcResponse<Object> response) {
        CompletableFuture<RpcResponse<Object>> future = UNPROCESSED_RESPONSE_FUTURES.remove(response.getRequestId());
        if (null != future) {
            future.complete(response);
        } else {
            throw new IllegalStateException();
        }
    }
}
