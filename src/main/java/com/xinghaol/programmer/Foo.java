package com.xinghaol.programmer;

import java.util.concurrent.CountDownLatch;

/**
 * @author: lixinghao
 * @date: 2019/12/30 6:03 下午
 * @Description: 我们提供了一个类：
 * <p>
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 * 示例 2:
 * <p>
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 *  
 * <p>
 * 注意:
 * <p>
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 * <p>
 * 你看到的输入格式主要是为了确保测试的全面性。
 */
/*
线程屏障的问题一般有三种解决版本
第一种方式是通过CountDownLatch设置屏障，这个是Java自带的
第二种方式是通过设置一个Object类型的成员变量，辅以synchronized，通过wait & notifyAll进行控制
第三种方式是通过volatile这个特性，这个特性可以保证可见性


或者采用自旋的无限循环方式
 */
public class Foo {
    /**
     * 推荐使用CountDownLatch
     */
    private CountDownLatch second = new CountDownLatch(1);
    private CountDownLatch third = new CountDownLatch(1);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        second.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        third.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        third.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
/*
 private volatile int flag = 1;
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        flag = 2;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(flag != 2);
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        flag = 3;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(flag != 3);
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
 */
/*
private int sign =0;
    public  Foo() {

    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            sign=1;
            notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {

            while (sign != 1){
                wait();
            }

            printSecond.run();
            sign=2;
            notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {

            while (sign != 2){
                wait();
            }

            printThird.run();
            sign=3;
    }
 */
