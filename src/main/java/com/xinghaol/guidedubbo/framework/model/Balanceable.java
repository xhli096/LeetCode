package com.xinghaol.guidedubbo.framework.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: lixinghao
 * @date: 2020/7/20 10:36 上午
 * @Description: 负载均衡服务器节点实体类
 */
@Data
@Accessors(chain = true)
public class Balanceable implements Serializable {
    private String serverAddress;
    private int weight = 1;
    private int active;

    public Balanceable(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Balanceable(String serverAddress, int weight, int active) {
        this.serverAddress = serverAddress;
        this.weight = weight;
        this.active = active;
    }
}
