package com.xinghaol.mydubbo.protocol.http;

import com.xinghaol.mydubbo.framework.Invocation;
import com.xinghaol.mydubbo.framework.Protocol;
import com.xinghaol.mydubbo.framework.URL;

/**
 * @author: lixinghao
 * @date: 2020/7/8 11:50 上午
 * @Description:
 */
public class HttpProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new HttpServer().start(url.getHostName(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        return new HttpClient().send(url.getHostName(), url.getPort(), invocation);
    }
}
