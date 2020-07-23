package com.xinghaol.guidedubbo.framework.simple.loadbalance;

import com.xinghaol.guidedubbo.framework.model.Balanceable;

import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/7/16 3:19 下午
 * @Description: 负载均衡抽象类
 */
public abstract class AbstractLoadBalance implements LoadBalance {
    @Override
    public Balanceable selectServiceAddress(List<Balanceable> servers) {
        if (servers == null || servers.size() == 0) {
            return null;
        }
        if (servers.size() == 1) {
            return servers.get(0);
        }
        return doSelect(servers);
    }

    /**
     * 由子类实现具体的负载均衡算法去
     *
     * @param servers
     * @return
     */
    protected abstract Balanceable doSelect(List<Balanceable> servers);
}
