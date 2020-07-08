package com.xinghaol.mydubbo.framework;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: lixinghao
 * @date: 2020/7/8 12:08 下午
 * @Description:
 */
public class ProtocolFactory {
    /**
     * java spi，读取META-INF.services下的以类全路径名称开头文件中的内容；
     * mysql 是使用的spi；但是如果暴露多个服务，在读取后选择那个服务进行实现不方便，只能返回一个协议
     * 即Iterator<Protocol> iterator = serviceLoader.iterator(); =》 dubbo spi
     *
     * @return
     */
    public static Protocol getProtocol() {
        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        return iterator.next();
    }
}
