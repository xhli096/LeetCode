package com.xinghaol.guidedubbo.framework.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author: lixinghao
 * @date: 2020/7/22 11:36 上午
 * @Description: 远程调用失败信息
 */
@AllArgsConstructor
@ToString
@Getter
public enum RpcErrorMessage {
    /**
     * 客户端连接服务器端失败
     */
    CLIENT_CONNECT_SERVER_FAILURE(10001, "客户端连接服务器端失败"),
    /**
     * 服务调用失败
     */
    SERVICE_INVOCATION_FAILURE(10002, "服务调用失败"),
    /**
     * 没有找到指定的服务
     */
    SERVICE_CAN_NOT_BE_FOUND(10003, "没有找到指定的服务"),
    /**
     * 注册的服务没有实现任何接口
     */
    SERVICE_NOT_IMPLEMENT_ANY_INTERFACE(10004, "注册的服务没有实现任何接口"),
    /**
     * 返回结果错误！请求和返回的响应不匹配
     */
    REQUEST_NOT_MATCH_RESPONSE(10005, "返回结果错误！请求和返回的响应不匹配");


    private final int code;
    private final String message;
}
