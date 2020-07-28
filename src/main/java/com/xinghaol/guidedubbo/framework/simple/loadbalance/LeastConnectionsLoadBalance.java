package com.xinghaol.guidedubbo.framework.simple.loadbalance;

import com.xinghaol.guidedubbo.framework.model.Balanceable;

import java.util.List;
import java.util.Random;

/**
 * @author: lixinghao
 * @date: 2020/7/22 11:37 下午
 * @Description: 最小连接数的负载均衡算法
 */
public class LeastConnectionsLoadBalance extends AbstractLoadBalance {

    /**
     * 由子类实现具体的负载均衡算法去，保证同步
     * 首先找到服务提供者当前最小的活跃连接数，如果一个服务提供者的服务连接数比其他的都要小，
     * 则选择这个活跃连接数最小的服务提供者发起调用，如果存在多个服务提供者的活跃连接数，并且是最小的，
     * 则在这些服务提供者之间选择加权随机算法选择一个服务提供者
     * 优点：根据服务器当前的请求处理情况，动态分配
     * 缺点：算法实现相对复杂，需要监控服务器请求连接数
     *
     * @param servers
     * @return
     */
    @Override
    protected synchronized Balanceable doSelect(List<Balanceable> servers) {
        int length = servers.size();
        int leastActive = -1;
        int leastCount = 0;
        // 存放连接数最小服务器的下标值
        int[] leastIndexs = new int[length];
        // 总的权重，如果存在多个相同的最小连接数服务器，则基于权重的去选择一个
        int totalWeight = 0;
        int firstWeight = 0;
        boolean sameWeight = true;

        // 循环处理注册到注册中心的服务器列表
        for (int i = 0; i < length; i++) {
            Balanceable server = servers.get(i);
            int weight = server.getWeight();
            int active = server.getActive();

            if (leastActive == -1 || active < leastActive) {
                leastActive = active;
                leastCount = 1;
                totalWeight = weight;
                firstWeight = weight;
                sameWeight = true;
                leastIndexs[0] = i;
            } else if (active == leastActive) {
                // 多个服务器的连接数相同，都是最小的连接数
                leastIndexs[leastCount++] = i;
                totalWeight += weight;
                // 相同连接的服务器的权重不同，则后面需要根据权重来选择一个
                if (sameWeight && weight != firstWeight) {
                    sameWeight = false;
                }
            }
        }

        // 说明只有一个最小连接数的服务器
        if (leastCount == 1) {
            return servers.get(leastIndexs[0]);
        }
        Random random = new Random();
        // 存在相同的最小连接数的服务器，且它们之间的权重不同，则根据权重来选择一个
        if (!sameWeight && totalWeight > 0) {
            // 这里的+1操作为了防止某一个server无法被选中的局面
            int offsetWeight = random.nextInt(totalWeight) + 1;
            for (int i = 0; i < leastCount; i++) {
                int leastIndex = leastIndexs[i];
                // 获取权重值，并让随机数减去权重值
                offsetWeight -= servers.get(leastIndex).getWeight();
                if (offsetWeight <= 0) {
                    return servers.get(leastIndex);
                }
            }
        }
        // 退出循环后，可能在经过leastCount次运算后，offsetWeight还是一个大于0的，无法选中server

        // 存在相同的最小连接数的服务器，且它们之间的权重相同，随机返回去一个即可。
        return servers.get(leastIndexs[random.nextInt(leastCount)]);
    }
}
