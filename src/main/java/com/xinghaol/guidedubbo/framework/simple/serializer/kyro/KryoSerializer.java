package com.xinghaol.guidedubbo.framework.simple.serializer.kyro;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcRequest;
import com.xinghaol.guidedubbo.framework.simple.remoting.dto.RpcResponse;
import com.xinghaol.guidedubbo.framework.simple.serializer.Serializer;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author: lixinghao
 * @date: 2020/7/22 10:56 上午
 * @Description: Kryo序列化类，Kryo序列化效率很高，但是只兼容 Java 语言
 */
@Slf4j
public class KryoSerializer implements Serializer {
    /**
     * 由于Kryo不是线程安全的，每个线程都应该有自己的Kryo、Input和Output实例，使用ThreadLocal存放Kryo，保证每个线程均由一份自己的
     */
    private final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(RpcResponse.class);
        kryo.register(RpcRequest.class);
        //默认值为true,是否关闭注册行为,关闭之后可能存在序列化问题，一般推荐设置为 true
        kryo.setReferences(true);
        //默认值为false,是否关闭循环引用，可以提高性能，但是一般不推荐设置为 true
        kryo.setRegistrationRequired(false);
        return kryo;
    });

    /**
     * 序列化
     *
     * @param object 要序列化的对象
     * @return 字节数组
     */
    @Override
    public byte[] serializer(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        // Object -> byte[] 将对象序列化为一个byte数组
        Kryo kryo = kryoThreadLocal.get();
        kryo.writeObject(output, object);
        kryoThreadLocal.remove();
        return output.toBytes();
    }

    /**
     * 反序列化
     *
     * @param bytes 序列化后的字节数组
     * @param clazz 目标类
     * @return 反序列化的对象
     */
    @Override
    public <T> T deserializer(byte[] bytes, Class<T> clazz) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        Kryo kryo = kryoThreadLocal.get();

        // byte[] -> object  将byte反序列化为对象
        Object object = kryo.readObject(input, clazz);
        kryoThreadLocal.remove();
        return clazz.cast(object);
    }
}
