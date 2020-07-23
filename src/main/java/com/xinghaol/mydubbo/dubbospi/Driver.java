package com.xinghaol.mydubbo.dubbospi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author: lixinghao
 * @date: 2020/7/8 11:11 下午
 * @Description: @SPI用于标识该接口可以被扩展
 */
@SPI
public interface Driver {
    /**
     * url在dubbo是一个总线的概念
     *
     * @param url
     */
    void driverCar(URL url);
}
