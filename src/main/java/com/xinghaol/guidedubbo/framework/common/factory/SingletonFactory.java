package com.xinghaol.guidedubbo.framework.common.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/7/28 11:01 上午
 * @Description: 获取单例对象的工厂类
 */
public class SingletonFactory {
    private static final Map<String, Object> OBJECT_MAP = new HashMap<>();

    public SingletonFactory() {
    }

    public static <T> T getInstance(Class<T> clazz) {
        String key = clazz.toString();
        Object object = OBJECT_MAP.get(key);
        synchronized (clazz) {
            if (object == null) {
                try {
                    object = clazz.newInstance();
                    OBJECT_MAP.put(key, object);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return clazz.cast(object);
    }
}
