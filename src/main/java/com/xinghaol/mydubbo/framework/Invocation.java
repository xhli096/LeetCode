package com.xinghaol.mydubbo.framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: lixinghao
 * @date: 2020/7/7 3:20 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class[] paramTypes;
    private Object[] params;

    public Invocation() {
    }
}
