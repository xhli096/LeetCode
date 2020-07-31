package com.xinghaol.guidedubbo.framework.simple.remoting.dto;

import com.xinghaol.guidedubbo.framework.common.enumeration.RpcMessageType;
import com.xinghaol.guidedubbo.framework.model.RpcServiceProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: lixinghao
 * @date: 2020/7/22 11:17 上午
 * @Description: rpc请求
 */
@Data
@AllArgsConstructor
@Builder
@ToString
public class RpcRequest implements Serializable {
    /**
     * 全局唯一的requestId
     */
    private String requestId;
    /**
     * 接口名称
     */
    private String interfaceName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数值
     */
    private Object[] parameters;
    /**
     * 参数列表
     */
    private Class<?>[] paramTypes;
    /**
     * 版本号
     */
    private String version;
    /**
     * 组
     */
    private String group;
    /**
     * 消息类型
     */
    private RpcMessageType rpcMessageType;

    public RpcServiceProperties toRpcProperties() {
        return RpcServiceProperties.builder().serviceName(this.getInterfaceName())
                .version(this.getVersion())
                .group(this.getGroup()).build();
    }
}
