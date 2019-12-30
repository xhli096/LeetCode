package com.tujia.xinghaol.programmer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lixinghao
 * @date: 2019/12/30 6:24 下午
 * @Description: 我们提供一个类：
 * <p>
 * class FooBar {
 * public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 * }
 * <p>
 * public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 * }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 * 示例 2:
 * <p>
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 */
/*
与顺序打印ABC思路大致相同。多个线程有序输出内容，通过方法使用condition与可重入锁结合。
1、condition和可重入锁配合，使用一个锁来控制一次只执行一个线程。
2、需要保证线程先输出foo方法，so,需要增加一个变量来控制执行顺序
3、根据两个线程就使用一个锁创建两个condition条件，根据fooRun来判断执行哪一个方法，并且通过condition来对当前线程等待还是继续执行。
4、用一个变量fooRun来控制当前应该执行的是哪一个方法，如果当前线程和fooRun想要执行的线程不一致，就把当前线程等待，并且释放当前的锁。
顺序打印ABC维护三个Condition即可
 */
public class FooBar {
    private static volatile int n;

    private static Lock lock = new ReentrantLock();
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();
    private static volatile String s = "foo";

    public static void main(String[] args) throws InterruptedException {
        n = 2;
        foo(new Runnable() {
            public void run() {
                System.out.print("foo");
            }
        });
        bar(new Runnable() {
            public void run() {
                System.out.print("bar");
            }
        });
    }

    public FooBar(int n) {
        this.n = n;
    }

    public static void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            if (!s.equals("foo")) {
                conditionA.await();
                conditionB.signal();
            }
            s = "bar";
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            conditionB.signal();
            lock.unlock();
        }
    }

    public static void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            if (!s.equals("bar")) {
                conditionB.await();
                conditionA.signal();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            s = "foo";
            printBar.run();
            conditionA.signal();
            lock.unlock();
        }
    }
}
