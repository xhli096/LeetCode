package com.xinghaol.guidedubbo.framework.simple.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lixinghao
 * @date: 2020/7/16 3:04 下午
 * @Description: 当服务的-provider关闭的时候做一些事情，比如取消注册所有服务
 */
@Slf4j
public class CustomShutdownHook {
    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK = new CustomShutdownHook();

    public static CustomShutdownHook getCustomShutdownHook() {
        return CUSTOM_SHUTDOWN_HOOK;
    }

    public void clearAll() {
        log.info("addShutdownHook for clearAll");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // 取消注册所有服务
            // 关闭线程池
        }));
    }
}
