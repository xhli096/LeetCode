package com.xinghaol.guidedubbo.framework.common.enumeration;

/**
 * @author: lixinghao
 * @date: 2020/7/22 11:44 上午
 * @Description:
 */
public enum RpcProperties {
    RPC_CONFIG_PATH("rpc.properties"),
    ZK_ADDRESS("rpc.zookeeper.address");

    private final String propertyValue;


    RpcProperties(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyValue() {
        return propertyValue;
    }
}
