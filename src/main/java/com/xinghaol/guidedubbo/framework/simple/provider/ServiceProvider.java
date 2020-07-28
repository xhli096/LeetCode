package com.xinghaol.guidedubbo.framework.simple.provider;

import com.xinghaol.guidedubbo.framework.model.RpcServiceProperties;

/**
 * @author: lixinghao
 * @date: 2020/7/28 11:50 上午
 * @Description: 存储并提供service实例
 */
public interface ServiceProvider {
    /**
     * 增加一个服务提供者
     *
     * @param service              service object
     * @param serviceClass         the interface class implemented by the service instance object
     * @param rpcServiceProperties service  related attributes
     */
    void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties);

    /**
     * 获得一个提供者
     *
     * @param rpcServiceProperties service  related attributes
     * @return service object
     */
    Object getService(RpcServiceProperties rpcServiceProperties);

    /**
     * publishService
     *
     * @param service              service object
     * @param rpcServiceProperties service  related attributes
     */
    void publishService(Object service, RpcServiceProperties rpcServiceProperties);

    /**
     * publishService
     *
     * @param service service object
     */
    void publishService(Object service);
}
