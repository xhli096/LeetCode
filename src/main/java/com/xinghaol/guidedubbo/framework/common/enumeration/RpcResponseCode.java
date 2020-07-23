package com.xinghaol.guidedubbo.framework.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author: lixinghao
 * @date: 2020/7/22 11:31 上午
 * @Description: rpc远程调用结果
 */
@AllArgsConstructor
@Getter
@ToString
public enum RpcResponseCode {
    /**
     * 调用方法成功
     */
    SUCCESS(200, "调用方法成功"),
    /**
     * 调用方法失败
     */
    FAIL(500, "调用方法失败"),
    /**
     * 未找到指定方法
     */
    NOT_FOUND_METHOD(500, "未找到指定方法"),
    /**
     * 未找到指定类
     */
    NOT_FOUND_CLASS(500, "未找到指定类");
    private final int code;
    private final String message;
}
