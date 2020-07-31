package com.xinghaol.guidedubbo.framework.simple.remoting.transport.netty;

import com.xinghaol.guidedubbo.framework.common.enumeration.RpcMessageType;
import com.xinghaol.guidedubbo.framework.common.enumeration.RpcResponseCode;
import com.xinghaol.guidedubbo.framework.common.factory.SingletonFactory;
import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcRequest;
import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcResponse;
import com.xinghaol.guidedubbo.framework.simple.remoting.handler.RpcRequestHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: lixinghao
 * @date: 2020/7/28 10:12 下午
 * @Description: 定制一个server handler用来处理客户端发送的数据，用于服务器端
 * 如果继承自 SimpleChannelInboundHandler 的话就不要考虑 ByteBuf 的释放 ，{@link io.netty.channel.SimpleChannelInboundHandler} 内部的
 * channelRead 方法会替你释放 ByteBuf ，避免可能导致的内存泄露问题。详见《Netty进阶之路 跟着案例学 Netty》
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private final RpcRequestHandler rpcRequestHandler;

    public NettyServerHandler() {
        this.rpcRequestHandler = SingletonFactory.getInstance(RpcRequestHandler.class);
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            log.info("server receive msg : [{}]", msg);
            RpcRequest rpcRequest = (RpcRequest) msg;
            // 如果是心跳请求，不处理这个请求
            if (rpcRequest.getRpcMessageType() == RpcMessageType.HEART_BEAT) {
                log.info("receive heat beat msg from client");
                return;
            }
            // Execute the target method (the method the client needs to execute) and return the method result
            // 运行目标方法，并得到方法的返回值
            Object result = rpcRequestHandler.handler(rpcRequest);
            log.info(String.format("server get result : %s", result));
            if (ctx.channel().isActive() && ctx.channel().isWritable()) {
                RpcResponse<Object> success = RpcResponse.success(result, rpcRequest.getRequestId());
                ctx.writeAndFlush(success).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            } else {
                RpcResponse<Object> fail = RpcResponse.fail(RpcResponseCode.FAIL);
                ctx.writeAndFlush(fail).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                log.error("not writable now, message dropped");
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * Calls {@link ChannelHandlerContext#fireUserEventTriggered(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param evt
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.READER_IDLE) {
                log.info("idle check happen, so close the connection");
                ctx.close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("server catch exception");
        cause.printStackTrace();
        ctx.close();
    }
}
