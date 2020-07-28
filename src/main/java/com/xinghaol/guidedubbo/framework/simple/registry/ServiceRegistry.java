package com.xinghaol.guidedubbo.framework.simple.registry;

import java.net.InetSocketAddress;

/**
 * @author: lixinghao
 * @date: 2020/7/24 9:48 下午
 * @Description: 服务注册接口
 */
public interface ServiceRegistry {
    /**
     * 注册服务
     *
     * @param serviceName       注册的服务名称
     * @param inetSocketAddress 服务地址
     */
    void registryService(String serviceName, InetSocketAddress inetSocketAddress);
}
