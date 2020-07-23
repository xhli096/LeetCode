package com.xinghaol.io.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/7/22 9:47 下午
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Headers implements Serializable {
    private String method;
    private String path;
    private String version;

    private Map<String, String> headerMap = new HashMap<>();
}
