package com.xinghaol.guidedubbo.framework.common.exception;

import com.xinghaol.guidedubbo.framework.common.enumeration.RpcErrorMessage;

/**
 * @author: lixinghao
 * @date: 2020/7/28 10:37 上午
 * @Description:
 */
public class RpcException extends RuntimeException {
    public RpcException(RpcErrorMessage rpcErrorMessage, String detail) {
        super(rpcErrorMessage.getMessage() + ":" + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcErrorMessage rpcErrorMessage) {
        super(rpcErrorMessage.getMessage());
    }
}
