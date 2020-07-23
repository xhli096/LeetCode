package com.xinghaol.mydubbo.dubbospi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author: lixinghao
 * @date: 2020/7/8 10:56 下午
 * @Description:
 */
@SPI
public interface Car {
    /**
     * 有人调用getColor的时候，就会获取carType类型
     *
     * @param url
     */
    @Adaptive(value = "carType")
    void getColor(URL url);
}
