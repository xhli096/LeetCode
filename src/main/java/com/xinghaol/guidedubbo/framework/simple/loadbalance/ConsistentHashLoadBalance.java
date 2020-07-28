package com.xinghaol.guidedubbo.framework.simple.loadbalance;

import com.xinghaol.guidedubbo.framework.model.Balanceable;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/7/24 10:24 上午
 * @Description: 一致性hash 负载均衡算法
 */
public class ConsistentHashLoadBalance extends AbstractLoadBalance {
    /**
     * 由子类实现具体的负载均衡算法去
     *
     * @param servers
     * @return
     */
    @Override
    protected Balanceable doSelect(List<Balanceable> servers) {
        List<String> list = new LinkedList<>();
        return null;
    }
}
