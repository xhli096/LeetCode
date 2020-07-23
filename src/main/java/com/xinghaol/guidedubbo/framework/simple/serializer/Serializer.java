package com.xinghaol.guidedubbo.framework.simple.serializer;

/**
 * @author: lixinghao
 * @date: 2020/7/22 10:50 上午
 * @Description: 序列化接口，所有序列化类都要实现这个接口
 */
public interface Serializer {
    /**
     * 序列化
     *
     * @param object 要序列化的对象
     * @return 字节数组
     */
    byte[] serializer(Object object);

    /**
     * 反序列化
     *
     * @param bytes 序列化后的字节数组
     * @param clazz 目标类
     * @param <T>   类的类型
     * @return 反序列化的对象
     */
    <T> T deserializer(byte[] bytes, Class<T> clazz);
}
