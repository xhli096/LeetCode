package com.xinghaol.mydubbo.framework;

import com.xinghaol.mydubbo.protocol.http.HttpClient;
import com.xinghaol.mydubbo.register.RemoteMapRegister;

import java.lang.reflect.Proxy;

/**
 * @author: lixinghao
 * @date: 2020/7/7 10:40 下午
 * @Description:
 */
public class ProxyFactory {
    public static <T> T getProxy(Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, (proxy, method, args) -> {
            HttpClient httpClient = new HttpClient();
            Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
            URL url = RemoteMapRegister.random(interfaceClass.getName());
            String result = httpClient.send(url.getHostName(), url.getPort(), invocation);

            return result;
        });
    }
}
