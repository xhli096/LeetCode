package com.xinghaol.guidedubbo.framework.simple.loadbalance;

import com.xinghaol.guidedubbo.framework.model.Balanceable;

import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/7/16 3:19 下午
 * @Description: 负载均衡接口
 */
public interface LoadBalance {
    /**
     * 在已有的服务列表地址中选择一个
     *
     * @param servers
     * @return
     */
    Balanceable selectServiceAddress(List<Balanceable> servers);
}
