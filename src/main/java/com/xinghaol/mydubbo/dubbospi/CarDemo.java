package com.xinghaol.mydubbo.dubbospi;

import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author: lixinghao
 * @date: 2020/7/8 11:05 下午
 * @Description:
 */
public class CarDemo {
    public static void main(String[] args) {
        // 每个接口对应于自己的一个extensionLoader

        // ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension()  如果传入的不是ExtensionFactory
        // 也会得到ExtensionFactory的loader，并获取其代理类-AdaptiveExtension
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);

        // 增加包装类的实现后，实际上拿出来的就是包装类。切面即产生了效果
        // 接口可以使用@SPI指定一个默认的实现类，即@SPI(value="xxx")
        // if ("true".equals(name)) {
        //      return getDefaultExtension();
        // }

        // dubbo会将META-INF.service下对应配置文件变成一个map，然后根据传入的name去取得对应的class
        // org.apache.dubbo.common.extension.ExtensionLoader.cacheAdaptiveClass用于判断每个方法的@Adpative只能有一个值，否则就会报错
        // 是否是一个包装类，需要提供一个参数类型为接口类型的构造方法-》只有这样才能够通过判定是一个接口的包装类
        Car redCar = extensionLoader.getExtension("red");
        redCar.getColor(null);

/*
        Car blackCar = extensionLoader.getExtension("black");
        blackCar.getColor();
*/

        // org.apache.dubbo.common.extension.ExtensionLoader.injectExtension 完成对象的依赖注入
        // 在实现了接口的类中，可以打上@Adaptive的注解，如果有则加载到缓存中；如果所有的实现类均没有打这个注解，则由dubbo来实现一个默认的实现
        // 打了@Adaptive的类就相当于那个接口的代理类
        // 在完成依赖注入时，如spring一样，必须要有set方法，如果没有set方法则无法依赖注入
    }
}
