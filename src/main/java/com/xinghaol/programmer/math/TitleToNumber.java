package com.xinghaol.programmer.math;

import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/5/19 10:57 下午
 * @Description: 171. Excel表列序号
 */
public class TitleToNumber {
    private static Map<Character, Integer> map;

    static {
        map.put('A', 1);
        map.put('B', 2);
        map.put('C', 3);
        map.put('D', 4);
        map.put('E', 5);
        map.put('F', 6);
        map.put('G', 7);
        map.put('H', 8);
        map.put('I', 9);
        map.put('J', 10);
        map.put('K', 11);
        map.put('L', 12);
        map.put('M', 13);
        map.put('N', 14);
        map.put('O', 15);
        map.put('P', 16);
        map.put('Q', 17);
        map.put('R', 18);
        map.put('S', 19);
        map.put('T', 20);
        map.put('U', 21);
        map.put('V', 22);
        map.put('W', 23);
        map.put('X', 24);
        map.put('Y', 25);
        map.put('Z', 26);
    }

    public int titleToNumber(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            result = result * 26 + map.get(s.charAt(i));
        }

        return result;
    }

    public int titleToNumber2(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int nums = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + nums;
        }
        return ans;
    }
}