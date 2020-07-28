package com.xinghaol.guidedubbo.framework.simple.remoting.transport.netty;

import com.xinghaol.guidedubbo.framework.simple.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/7/28 4:12 下午
 * @Description: 自定义解码器，继承netty的ByteToMessageDecoder。负责消息"入站"的处理，
 * 将消息格式转换为我们需要的业务对象
 */
@Slf4j
@AllArgsConstructor
public class NettyKryoDecoder extends ByteToMessageDecoder {
    private final Serializer serializer;
    private final Class<?> genericClass;

    /**
     * netty传输的消息长度，也就是对象序列化后对应的字节数组的大小，存储在ByteBuf头部
     */
    private static final int BODY_LENGTH = 4;

    /**
     * Decode the from one {@link ByteBuf} to an other. This method will be called till either the input
     * {@link ByteBuf} has nothing to read when return from this method or till nothing was read from the input
     * {@link ByteBuf}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
     * @param in  the {@link ByteBuf} from which to read data
     * @param out the {@link List} to which decoded messages should be added
     * @throws Exception is thrown if an error occurs
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 1、bytebuf中写入的消息长度所占字节数已经是4，所以bytebuf的可读字节数必须大于4
        if (in.readableBytes() >= BODY_LENGTH) {
            // 2、标记当前的readIndex的位置，以后便于重置readIndex的时候使用
            in.markReaderIndex();
            // 3.读取消息的长度
            // 注意： 消息长度是encode的时候我们自己写入的，参见 NettyKryoEncoder 的encode方法
            int dataLength = in.readInt();

            // 4.遇到不合理的情况直接 return
            if (dataLength < 0 || in.readableBytes() < 0) {
                log.info("data length or byteBuf readableBytes is not valid");
                return;
            }
            // 5.如果可读字节数小于消息长度的话，说明是不完整的消息，重置readIndex
            if (in.readableBytes() < dataLength) {
                in.resetReaderIndex();
                return;
            }
            byte[] body = new byte[dataLength];
            in.readBytes(body);
            Object object = serializer.deserializer(body, genericClass);
            out.add(object);

            log.info("successful decode ByteBuf to Object");
        }
    }
}
