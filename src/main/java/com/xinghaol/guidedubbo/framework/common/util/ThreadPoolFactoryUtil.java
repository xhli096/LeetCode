package com.xinghaol.guidedubbo.framework.common.util;

import com.google.common.base.Strings;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: lixinghao
 * @date: 2020/7/23 1:06 下午
 * @Description: 创建线程池的工具类 threadPool
 */
@Slf4j
public class ThreadPoolFactoryUtil {
    /**
     * key：threadNamePrefix; value：threadPool
     * 通过threadNamePrefix来区分不同的线程池，可以把相同的threadPoolPrefix的线程池看做是同一业务场景服务。
     */
    private static final Map<String, ExecutorService> THREAD_POOLS = new ConcurrentHashMap<>();

    public ThreadPoolFactoryUtil() {
    }

    /**
     * @param threadNamePrefix
     * @return
     */
    public static ExecutorService createCustomThreadPoolIfAbsent(String threadNamePrefix) {
        CustomThreadPoolConfig customThreadPoolConfig = new CustomThreadPoolConfig();
        return createCustomThreadPoolIfAbsent(customThreadPoolConfig, threadNamePrefix, false);
    }

    /**
     * @param threadNamePrefix
     * @param customThreadPoolConfig
     * @return
     */
    public static ExecutorService createCustomThreadPoolIfAbsent(String threadNamePrefix, CustomThreadPoolConfig customThreadPoolConfig) {
        return createCustomThreadPoolIfAbsent(customThreadPoolConfig, threadNamePrefix, false);
    }

    /**
     * @param customThreadPoolConfig 自定义config类
     * @param threadNamePrefix       作为创建的线程名字的前缀
     * @param daemon                 指定是否为 Daemon Thread(守护线程)
     * @return
     */
    public static ExecutorService createCustomThreadPoolIfAbsent(CustomThreadPoolConfig customThreadPoolConfig, String threadNamePrefix, Boolean daemon) {
        // computeIfAbsent 查看thread—pool里面的是否存在以threadNamePrefix的线程池，没有则创建一个
        ExecutorService threadPool = THREAD_POOLS.computeIfAbsent(threadNamePrefix, k -> createThreadPool(customThreadPoolConfig, threadNamePrefix, daemon));

        // 如果threadPool被shutdown了，则新建立一个
        if (threadPool.isShutdown() || threadPool.isTerminated()) {
            THREAD_POOLS.remove(threadNamePrefix);
            threadPool = createThreadPool(customThreadPoolConfig, threadNamePrefix, daemon);
            THREAD_POOLS.put(threadNamePrefix, threadPool);
        }

        return threadPool;
    }

    /**
     * 创建线程池
     *
     * @param customThreadPoolConfig
     * @param threadNamePrefix
     * @param daemon
     * @return
     */
    private static ExecutorService createThreadPool(CustomThreadPoolConfig customThreadPoolConfig, String threadNamePrefix, Boolean daemon) {
        ThreadFactory threadFactory = createThreadFactory(threadNamePrefix, daemon);
        return new ThreadPoolExecutor(customThreadPoolConfig.getCorePoolSize()
                , customThreadPoolConfig.getMaximumPoolSize()
                , customThreadPoolConfig.getKeepAliveTime()
                , customThreadPoolConfig.getTimeUnit()
                , customThreadPoolConfig.getWorkQueue()
                , threadFactory);
    }

    /**
     * 创建ThreadFactory，如果threadNamePrefix为空，则创建默认的defaultThreadFactory
     *
     * @param threadNamePrefix
     * @param daemon           指定是否为 Daemon Thread(守护线程)
     * @return
     */
    private static ThreadFactory createThreadFactory(String threadNamePrefix, Boolean daemon) {
        if (!Strings.isNullOrEmpty(threadNamePrefix)) {
            if (Objects.nonNull(daemon)) {
                return new ThreadFactoryBuilder().setNameFormat(threadNamePrefix + "-%d").setDaemon(daemon).build();
            } else {
                return new ThreadFactoryBuilder().setNameFormat(threadNamePrefix + "-%d").build();
            }
        }

        return Executors.defaultThreadFactory();
    }

    /**
     * 关闭所有线程池
     */
    public static void shutDownAllThreadPool() {
        log.info("shut down all ThreadPool method");
        THREAD_POOLS.entrySet().parallelStream().forEach(entry -> {
            ExecutorService executorService = entry.getValue();
            // 关闭线程池，会等待已提交的任务执行
            executorService.shutdown();
            log.info("shut down thread pool [{}] [{}]", entry.getKey(), executorService.isTerminated());
            try {
                executorService.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                executorService.shutdownNow();
            }
        });
    }

    /**
     * 打印线程池的状态
     *
     * @param threadPool 线程池对象
     */
    public static void printThreadPoolStatus(ThreadPoolExecutor threadPool) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, createThreadFactory("print-thread-pool-status", false));
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("============= ThreadPool Status ============");
            log.info("ThreadPool Size : [{}]", threadPool.getPoolSize());
            log.info("Active Thread : [{}]", threadPool.getActiveCount());
            log.info("Number of Tasks : [{}]", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue : {}", threadPool.getQueue().size());
            log.info("============================================");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
