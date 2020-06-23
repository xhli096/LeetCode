package com.xinghaol.programmer.design;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lixinghao
 * @date: 2020/6/21 11:54 上午
 * @Description: 1188. 设计有限阻塞队列
 * https://leetcode-cn.com/problems/design-bounded-blocking-queue/
 */
public class BoundedBlockingQueue {
    private int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private Queue<Integer> queue = new LinkedList<>();
    private final Condition freeCond = lock.newCondition();
    private final Condition elemCond = lock.newCondition();


    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                freeCond.await();
            }
            queue.offer(element);
            elemCond.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                elemCond.await();
            }
            int value = queue.poll();
            freeCond.signal();
            return value;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
