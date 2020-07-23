package com.xinghaol.mydubbo.dubbospi.impl;

import com.xinghaol.mydubbo.dubbospi.Car;
import com.xinghaol.mydubbo.dubbospi.Driver;
import org.apache.dubbo.common.URL;

/**
 * @author: lixinghao
 * @date: 2020/7/9 10:16 上午
 * @Description:
 */
public class Trucker implements Driver {
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public void driverCar(URL url) {
        car.getColor(url);
    }
}
