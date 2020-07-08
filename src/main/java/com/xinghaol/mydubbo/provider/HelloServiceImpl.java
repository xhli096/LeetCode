package com.xinghaol.mydubbo.provider;

/**
 * @author: lixinghao
 * @date: 2020/7/7 10:56 上午
 * @Description:
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
