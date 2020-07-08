package com.xinghaol.mydubbo.protocol.dubbo;

import com.xinghaol.mydubbo.framework.Invocation;
import com.xinghaol.mydubbo.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * @author: lixinghao
 * @date: 2020/7/7 11:28 下午
 * @Description: netty适用于一些并发量较高的短请求
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;

        Class clazz = LocalRegister.get(invocation.getInterfaceName());
        Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        Object result = method.invoke(clazz.newInstance(), invocation.getParams());

        System.out.println("Netty:" + result.toString());
        ctx.writeAndFlush(result);
    }
}
