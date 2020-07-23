package com.xinghaol.mydubbo.dubbospi.impl;

import com.xinghaol.mydubbo.dubbospi.Car;
import org.apache.dubbo.common.URL;

/**
 * @author: lixinghao
 * @date: 2020/7/8 11:07 下午
 * @Description: 一个包装类，实际就是用来实现切面的
 */
public class CarWrapper implements Car {
    private Car car;

    public CarWrapper(Car car) {
        this.car = car;
    }

    @Override
    public void getColor(URL url ) {
        System.out.println("getColor before ...");
        car.getColor(url);
        System.out.println("getColor after ...");
    }
}
