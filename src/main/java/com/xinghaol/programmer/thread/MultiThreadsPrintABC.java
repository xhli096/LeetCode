package com.xinghaol.programmer.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lixinghao
 * @date: 2020/6/16 2:58 下午
 * @Description:
 */
public class MultiThreadsPrintABC {
    private static ReentrantLock lock = new ReentrantLock();

    private static int state = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10;) {
                try {
                    lock.lock();
                    while (state % 3 == 0) {
                        System.out.println("A");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10;) {
                try {
                    lock.lock();
                    while (state % 3 == 1) {
                        System.out.println("B");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10;) {
                try {
                    lock.lock();
                    while (state % 3 == 2) {
                        System.out.println("C");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

}
