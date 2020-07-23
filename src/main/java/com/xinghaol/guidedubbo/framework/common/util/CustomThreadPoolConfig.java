package com.xinghaol.guidedubbo.framework.common.util;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: lixinghao
 * @date: 2020/7/23 1:06 下午
 * @Description: 线程池自定义的配置类，可以根据业务场景自行修改参数值
 */
@Getter
@Setter
public class CustomThreadPoolConfig {
    /**
     * 线程池的默认参数
     */
    private static final int CORE = Runtime.getRuntime().availableProcessors();
    private static final int DEFAULT_CORE_POOL_SIZE = CORE;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = CORE << 3;
    private static final int DEFAULT_KEEP_ALIVE_TIME = 100;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MINUTES;
    private static final int DEFAULT_BLOCKING_QUEUE_CAPACITY = CORE << 4;
    private static final int BLOCKING_QUEUE_CAPACITY = CORE << 4;

    /**
     * 支持可配置的参数
     */
    private int corePoolSize = DEFAULT_CORE_POOL_SIZE;
    private int maximumPoolSize = DEFAULT_MAXIMUM_POOL_SIZE;
    private long keepAliveTime = DEFAULT_KEEP_ALIVE_TIME;
    private TimeUnit timeUnit = DEFAULT_TIME_UNIT;
    private int blockingQueueCapacity = DEFAULT_BLOCKING_QUEUE_CAPACITY;

    /**
     * 默认使用有界队列
     */
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(DEFAULT_BLOCKING_QUEUE_CAPACITY);
}
