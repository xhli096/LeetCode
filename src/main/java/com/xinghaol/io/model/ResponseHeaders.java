package com.xinghaol.io.model;

import com.xinghaol.io.constant.HttpConstant;
import com.xinghaol.io.emumeration.EnumStatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: lixinghao
 * @date: 2020/7/22 9:52 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseHeaders extends Headers implements Serializable {
    private int code;
    private String phrase;
    private String contentType;
    private int contentLength;
    private String server;

    public ResponseHeaders(int code) {
        this.code = code;
        this.phrase = EnumStatusCode.queryPhrase(code);
        setVersion(HttpConstant.DEFAULT_HTTP_VERSION);
        this.server = HttpConstant.SERVER_NAME;
    }
}
