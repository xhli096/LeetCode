package com.xinghaol.programmer.week.week0516;

/**
 * @author: lixinghao
 * @date: 2020/5/16 10:32 下午
 * @Description:
 */
public class MaxPower {
    public int maxPower(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        char pre = s.charAt(0);
        int max = 1;
        int currentCount = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == pre) {
                currentCount++;
            } else {
                pre = c;
                currentCount = 1;
            }
            max = Math.max(max, currentCount);
        }

        return max;
    }

    public static void main(String[] args) {
        MaxPower maxPower = new MaxPower();
        int hooraaaaaaaaaaay = maxPower.maxPower("triplepillooooow");
        System.out.println(hooraaaaaaaaaaay);
    }
}
