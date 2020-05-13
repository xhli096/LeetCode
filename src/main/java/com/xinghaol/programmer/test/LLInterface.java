package com.xinghaol.programmer.test;

/**
 * @author: lixinghao
 * @date: 2020/5/12 3:57 下午
 * @Description:
 */
public interface LLInterface {
    default void hello() {
        System.out.println("hello");
    }
}
