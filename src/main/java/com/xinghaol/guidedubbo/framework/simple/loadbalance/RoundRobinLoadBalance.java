package com.xinghaol.guidedubbo.framework.simple.loadbalance;

import com.xinghaol.guidedubbo.framework.model.Balanceable;

import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/7/17 5:22 下午
 * @Description: 轮询方式的负载均衡算法
 */
public class RoundRobinLoadBalance extends AbstractLoadBalance {
    private static Integer position = 0;

    /**
     * 实现具体的负载均衡算法
     *
     * @param servers
     * @return
     */
    @Override
    protected Balanceable doSelect(List<Balanceable> servers) {
        synchronized (position) {
            if (position >= servers.size()) {
                position = 0;
            }
        }
        return servers.get(position);
    }
}
