package com.xinghaol.mydubbo.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/7/7 11:39 上午
 * @Description: 本地导出注册
 */
public class LocalRegister {
    private static Map<String, Class> cache = new HashMap<>();

    public static void register(String interfaceName, Class clazz) {
        cache.put(interfaceName, clazz);
    }

    public static Class get(String interfaceName) {
        return cache.get(interfaceName);
    }
}
