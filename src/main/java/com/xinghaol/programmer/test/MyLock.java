package com.xinghaol.programmer.test;

/**
 * @author: lixinghao
 * @date: 2020/8/23 3:08 下午
 * @Description:
 */
public class MyLock {
    public synchronized static void fun1() throws InterruptedException {
        System.out.println("这是mylock的静态方法");
        Thread.currentThread().wait();
    }

    public void fun2() throws InterruptedException {
        synchronized (MyLock.class) {
            System.out.println("这是fun2方法");
            Thread.currentThread().wait();
        }
    }
}
