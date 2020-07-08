package com.xinghaol.mydubbo.consumer;

import com.xinghaol.mydubbo.framework.ProxyFactory;
import com.xinghaol.mydubbo.provider.HelloService;

/**
 * @author: lixinghao
 * @date: 2020/7/7 6:23 下午
 * @Description:
 */
public class Consumer {
    public static void main(String[] args) {
        // 代理对象
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("lalla");
        System.out.println("result: " + result);
    }
}
