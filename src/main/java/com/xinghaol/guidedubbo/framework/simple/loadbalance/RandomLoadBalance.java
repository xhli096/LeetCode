package com.xinghaol.guidedubbo.framework.simple.loadbalance;

import com.xinghaol.guidedubbo.framework.model.Balanceable;

import java.util.List;
import java.util.Random;

/**
 * @author: lixinghao
 * @date: 2020/7/16 3:51 下午
 * @Description: 随机负载均衡
 */
public class RandomLoadBalance extends AbstractLoadBalance {
    /**
     * 随机负载均衡
     *
     * @param servers
     * @return
     */
    @Override
    protected Balanceable doSelect(List<Balanceable> servers) {
        Random random = new Random();
        return servers.get(random.nextInt(servers.size()));
    }
}
