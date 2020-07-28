package com.xinghaol.guidedubbo.framework.simple.registry.impl.zk;

import com.google.common.collect.Lists;
import com.xinghaol.guidedubbo.framework.common.enumeration.RpcErrorMessage;
import com.xinghaol.guidedubbo.framework.common.exception.RpcException;
import com.xinghaol.guidedubbo.framework.model.Balanceable;
import com.xinghaol.guidedubbo.framework.simple.loadbalance.LoadBalance;
import com.xinghaol.guidedubbo.framework.simple.registry.ServiceDiscovery;
import com.xinghaol.guidedubbo.framework.simple.util.CuratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/7/28 10:26 上午
 * @Description: zookeeper服务发现
 */
@Slf4j
public class ZkServiceDiscovery implements ServiceDiscovery {
    private final LoadBalance loadBalance;


    public ZkServiceDiscovery(LoadBalance loadBalance) {
        this.loadBalance = loadBalance;
    }

    /**
     * 根据应用名称获得一个服务地址
     *
     * @param serviceName 服务service名称
     * @return
     */
    @Override
    public InetSocketAddress lookupService(String serviceName) {
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        List<String> serviceAddressList = CuratorUtils.getChildrenNodes(zkClient, serviceName);
        if (CollectionUtils.isEmpty(serviceAddressList)) {
            throw new RpcException(RpcErrorMessage.SERVICE_CAN_NOT_BE_FOUND, serviceName);
        }
        List<Balanceable> balanceableList = Lists.newArrayListWithCapacity(serviceAddressList.size());
        serviceAddressList.stream().parallel().forEach(address -> {
            Balanceable balanceable = new Balanceable(address);
            balanceableList.add(balanceable);
        });
        Balanceable targetServer = loadBalance.selectServiceAddress(balanceableList);
        log.info("successfully found the service address : [{}]", targetServer.getServerAddress());
        String[] socketAddressArray = targetServer.getServerAddress().split(":");
        return new InetSocketAddress(socketAddressArray[0], Integer.parseInt(socketAddressArray[1]));
    }
}
