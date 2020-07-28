package com.xinghaol.guidedubbo.framework.simple.registry.impl.zk;

import com.xinghaol.guidedubbo.framework.simple.registry.ServiceRegistry;
import com.xinghaol.guidedubbo.framework.simple.util.CuratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;

import static com.xinghaol.guidedubbo.framework.simple.util.CuratorUtils.ZK_REGISTER_ROOT_PATH;

/**
 * @author: lixinghao
 * @date: 2020/7/28 10:20 上午
 * @Description: zookeeper服务注册中心
 */
@Slf4j
public class ZkServiceRegistry implements ServiceRegistry {
    /**
     * 注册服务
     *
     * @param serviceName       注册的服务名称
     * @param inetSocketAddress 服务地址
     */
    @Override
    public void registryService(String serviceName, InetSocketAddress inetSocketAddress) {
        // 拼装服务地址
        String servicePath = ZK_REGISTER_ROOT_PATH + "/" + serviceName + inetSocketAddress.toString();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        CuratorUtils.createPersistentNode(zkClient, servicePath);
    }
}
