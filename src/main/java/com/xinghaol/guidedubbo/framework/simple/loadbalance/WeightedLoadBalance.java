package com.xinghaol.guidedubbo.framework.simple.loadbalance;

import com.xinghaol.guidedubbo.framework.model.Balanceable;

import java.util.List;
import java.util.Random;

/**
 * @author: lixinghao
 * @date: 2020/7/16 4:14 下午
 * @Description: 权重的负载均衡算法
 */
public class WeightedLoadBalance extends AbstractLoadBalance {
    @Override
    protected Balanceable doSelect(List<Balanceable> servers) {
        int seed = 0;
        for (Balanceable balanceable : servers) {
            seed += balanceable.getWeight();
        }

        int random = new Random().nextInt(seed);
        int tmp = 0;
        for (Balanceable balanceable : servers) {
            tmp += balanceable.getWeight();
            if (tmp >= random) {
                return balanceable;
            }
        }

        return null;
    }
}
