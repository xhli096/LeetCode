package com.xinghaol.mydubbo.protocol.dubbo;

import com.xinghaol.mydubbo.framework.Invocation;
import com.xinghaol.mydubbo.framework.Protocol;
import com.xinghaol.mydubbo.framework.URL;

/**
 * @author: lixinghao
 * @date: 2020/7/8 11:50 上午
 * @Description:
 */
public class DubboProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new NettyServer().start(url.getHostName(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        return new NettyClient<>().send(url.getHostName(), url.getPort(), invocation);
    }
}
