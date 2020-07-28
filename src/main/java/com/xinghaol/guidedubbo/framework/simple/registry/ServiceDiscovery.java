package com.xinghaol.guidedubbo.framework.simple.registry;

import java.net.InetSocketAddress;

/**
 * @author: lixinghao
 * @date: 2020/7/24 9:51 下午
 * @Description: 服务发现接口
 */
public interface ServiceDiscovery {
    /**
     * 根据应用名称获得一个服务地址
     *
     * @param serviceName 服务service名称
     * @return
     */
    InetSocketAddress lookupService(String serviceName);
}
