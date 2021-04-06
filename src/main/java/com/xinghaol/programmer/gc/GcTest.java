package com.xinghaol.programmer.gc;

import java.util.HashMap;
import java.util.Map;

import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

/**
 * @author: lixinghao
 * @date: 2020/3/26 3:15 下午
 * @Description:
 */
public class GcTest {
    public static void main(String[] args) {
        String s = "122334-￥￥￥￥￥￥￥443322";
        boolean matches = s.matches("^[0-9]+$");
        if (matches) {
            System.out.println("全部是数字");
        } else {
            System.out.println("存在非数字");
        }
    }
}
