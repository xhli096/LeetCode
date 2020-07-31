package com.xinghaol.guidedubbo.framework.simple.remoting.handler;

import com.xinghaol.guidedubbo.framework.common.exception.RpcException;
import com.xinghaol.guidedubbo.framework.common.factory.SingletonFactory;
import com.xinghaol.guidedubbo.framework.simple.provider.ServiceProvider;
import com.xinghaol.guidedubbo.framework.simple.provider.ServiceProviderImpl;
import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcRequest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: lixinghao
 * @date: 2020/7/24 9:47 下午
 * @Description: 处理rpc 远程请求的handler方法
 */
@Slf4j
public class RpcRequestHandler {
    private final ServiceProvider serviceProvider;

    public RpcRequestHandler() {
        this.serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);
    }

    /**
     * 处理rpcRequest，调用目标方法，并返回结果
     *
     * @param rpcRequest
     * @return
     */
    public Object handler(RpcRequest rpcRequest) {
        Object service = serviceProvider.getService(rpcRequest.toRpcProperties());
        return invokeTargetMethod(rpcRequest, service);
    }

    /**
     * 执行目标方法并得到返回值
     *
     * @param rpcRequest
     * @param service
     * @return
     */
    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) {
        Object result;

        Method method = null;
        try {
            method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            result = method.invoke(service, rpcRequest.getParameters());
            log.info("invoke service : [{}] method : [{}] successful", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RpcException(e.getMessage(), e.getCause());
        }

        return result;
    }
}
