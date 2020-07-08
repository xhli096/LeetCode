package com.xinghaol.mydubbo.framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: lixinghao
 * @date: 2020/7/7 11:46 上午
 * @Description: 自定义URL
 */
@Data
@Accessors(chain = true)
public class URL {
    private String hostName;
    private Integer port;

    public URL() {
    }

    public URL(String hostName, Integer port) {
        this.hostName = hostName;
        this.port = port;
    }
}
