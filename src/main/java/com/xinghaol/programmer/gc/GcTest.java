package com.xinghaol.programmer.gc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/3/26 3:15 下午
 * @Description:
 */
public class GcTest {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[59000*1024];
        allocation2 = new byte[900*1024];

        String s = "abcd";
        Map<Character, Integer> map = new HashMap<>();
        
    }
}
