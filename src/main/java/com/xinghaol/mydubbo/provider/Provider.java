package com.xinghaol.mydubbo.provider;

import com.xinghaol.mydubbo.framework.URL;
import com.xinghaol.mydubbo.protocol.http.HttpServer;
import com.xinghaol.mydubbo.register.LocalRegister;
import com.xinghaol.mydubbo.register.RemoteMapRegister;

/**
 * @author: lixinghao
 * @date: 2020/7/7 11:02 上午
 * @Description:
 */
public class Provider {
    public static void main(String[] args) {
        // 1、本地注册
        // {服务名：实现类} =》 map
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        // 2、远程注册
        // {服务名：List<URL>} => 可能是一个集群
        RemoteMapRegister.register(HelloService.class.getName(), new URL("localhost", 8080));

        // 3、启动服务-Tomcat
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
