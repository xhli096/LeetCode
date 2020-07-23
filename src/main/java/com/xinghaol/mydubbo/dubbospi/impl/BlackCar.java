package com.xinghaol.mydubbo.dubbospi.impl;

import com.xinghaol.mydubbo.dubbospi.Car;
import org.apache.dubbo.common.URL;

/**
 * @author: lixinghao
 * @date: 2020/7/8 10:57 下午
 * @Description:
 */
public class BlackCar implements Car {
    @Override
    public void getColor(URL url) {
        System.out.println("black car");
    }
}
