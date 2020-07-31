package com.xinghaol.guidedubbo.framework.simple.remoting.dto;

import com.xinghaol.guidedubbo.framework.common.enumeration.RpcResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author: lixinghao
 * @date: 2020/7/22 11:24 上午
 * @Description: rpc返回值
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse<T> implements Serializable {
    /**
     * 全局的请求id
     */
    private String requestId;
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public static <T> RpcResponse<T> success(T data, String requestId) {
        RpcResponse<T> rpcResponse = new RpcResponse<>();

        rpcResponse.setRequestId(requestId);
        if (Objects.nonNull(data)) {
            rpcResponse.setData(data);
        }

        return rpcResponse;
    }

    public static <T> RpcResponse<T> fail(RpcResponseCode rpcResponseCode) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(rpcResponseCode.getCode());
        response.setMessage(rpcResponseCode.getMessage());
        return response;
    }
}
