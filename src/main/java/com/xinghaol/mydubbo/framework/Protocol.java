package com.xinghaol.mydubbo.framework;

/**
 * @author: lixinghao
 * @date: 2020/7/8 11:19 上午
 * @Description: 由于切换协议的时候需要进行修改源码，需要一个Protocol进行统筹
 */
public interface Protocol {
    void start(URL url);

    String send(URL url, Invocation invocation);
}
