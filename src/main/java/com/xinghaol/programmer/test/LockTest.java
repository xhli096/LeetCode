package com.xinghaol.programmer.test;

/**
 * @author: lixinghao
 * @date: 2020/8/23 3:07 下午
 * @Description:
 */
public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        MyLock.fun1();
        MyLock myLock = new MyLock();
        myLock.fun2();
    }
}
