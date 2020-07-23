package com.xinghaol.mydubbo.dubbospi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/7/9 10:18 上午
 * @Description:
 */
public class DriverDemo {
    public static void main(String[] args) {
        ExtensionLoader<Driver> extensionLoader = ExtensionLoader.getExtensionLoader(Driver.class);
        Driver driver = extensionLoader.getExtension("trucker");

        Map<String, String> map = new HashMap<>();
        map.put("carType", "black");
        URL url = new URL("", "", 0, map);

        driver.driverCar(url);
    }
}
