package com.xinghaol.io.emumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author: lixinghao
 * @date: 2020/7/22 9:49 下午
 * @Description:
 */
@AllArgsConstructor
@ToString
@Getter
public enum EnumStatusCode {
    /**
     * 状态值
     */
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private int code;

    private String phrase;

    public static String queryPhrase(int code) {
        for (EnumStatusCode statusCodeEnum : EnumStatusCode.values()) {
            if (statusCodeEnum.getCode() == code) {
                return statusCodeEnum.getPhrase();
            }
        }

        return null;
    }
}
