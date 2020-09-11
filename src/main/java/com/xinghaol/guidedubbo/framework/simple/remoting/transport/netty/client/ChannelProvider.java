package com.xinghaol.guidedubbo.framework.simple.remoting.transport.netty.client;

import java.nio.channels.Channel;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/8/1 8:00 下午
 * @Description: 存储并获得管道channel
 */
public class ChannelProvider {
    private final Map<String, Channel> channelMap;
    private final NettyClient nettyClient;

    public ChannelProvider(Map<String, Channel> channelMap, NettyClient nettyClient) {
        this.channelMap = channelMap;
        this.nettyClient = nettyClient;
    }
}
