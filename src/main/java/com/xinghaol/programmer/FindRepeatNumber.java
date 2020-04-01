package com.xinghaol.programmer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/3/31 9:22 下午
 * @Description:
 */
public class FindRepeatNumber {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }
}
