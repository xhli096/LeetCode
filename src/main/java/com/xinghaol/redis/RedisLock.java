package com.xinghaol.redis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

/**
 * @author: lixinghao
 * @date: 2021/4/13 9:09 下午
 * @Description: redis分布式锁
 */
@Component
public class RedisLock {

    private String lockKey = "lock";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 尝试获取锁
     *
     * @param requestId
     * @param expireTime
     *
     * @return
     */
    public boolean tryGetLock(String requestId, Integer expireTime) {
        // 这里利用redis的set命令
        // 使用redis保证原子操作（判断是否存在，添加key，设置过期时间）
        while (true) {
            Boolean ifAbsent = stringRedisTemplate.boundValueOps(lockKey).setIfAbsent(requestId, expireTime, TimeUnit.SECONDS);
            if (ifAbsent) {
                return true;
            }
        }
    }

    /**
     * 释放锁
     *
     * @param requestId
     *
     * @return
     */
    public boolean releaseLock(String requestId) {
        // 这里使用Lua脚本保证原子性操作
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then " + "return redis.call('del', KEYS[1]) " + "else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        return new Long(1).equals(stringRedisTemplate.execute(redisScript, Collections.singletonList(lockKey), requestId));
    }

    /**
     * 创建续命子线程
     *
     * @param time
     * @param requestId
     *
     * @return
     */
    public Thread createLifeThread(int time, String requestId) {
        return new Thread(() -> {
            while (true) {
                try {
                    // 经过2/3的时间后，续命
                    TimeUnit.SECONDS.sleep(time * 2 / 3);

                    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then " + "return redis.call('expire', KEYS[1], ARGV[2]) " + "else return 0 end";
                    List args = new ArrayList();
                    args.add(requestId);
                    args.add(time);

                    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
                    stringRedisTemplate.execute(redisScript, Collections.singletonList(lockKey), args);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
    }

    /**
     * 测试分布式锁
     *
     * @return
     */
    public Map test() {
        Map result = new HashMap();

        long num = 0;
        int time = 10;
        String requestId = UUID.randomUUID().toString().replace("-", "");
        // 尝试获取锁, 获取不到时，自旋操作。
        tryGetLock(requestId, time);
        Thread thread1 = createLifeThread(time, requestId);

        try {
            // 说明thread1是一个守护线程
            thread1.setDaemon(true);
            thread1.start();
            result.put("tid", result);
        } finally {
            // 停止线程
            thread1.interrupt();
            releaseLock(requestId);
        }

        return result;
    }

    public static void main(String[] args) {
    }
}
