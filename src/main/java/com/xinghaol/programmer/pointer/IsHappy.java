package com.xinghaol.programmer.pointer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/4/30 3:50 下午
 * @Description:
 */
public class IsHappy {
    /**
     * 使用快慢指针
     * 每个中间计算的值都可以认为是链表中的一个节点，如果最后不能为1，则一定存在循环。
     * 则使用快慢指针查找链表中是否存在换
     *
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        if (n == 1) {
            return true;
        }

        int slow = n;
        int fast = squareSum(n);
        while (slow != fast) {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }

        return slow == 1;
    }

    private int squareSum(int n) {
        int sum = 0;
        while(n > 0){
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    /**
     * 依靠hashset来存储之前计算过的值，如果已经发现了之前出现过的值，肯定不能到1
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }

        Set<Integer> set = new HashSet<>();
        set.add(n);
        return isHappy(n, set);
    }

    private boolean isHappy(int n, Set<Integer> set) {
        if (n == 1) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        int tmp = n;
        while (tmp != 0) {
            list.add(tmp % 10);
            tmp = tmp / 10;
        }
        int next = 0;
        for (Integer integer : list) {
            next += (integer * integer);
        }

        if (set.contains(next)) {
            return false;
        } else {
            set.add(next);
            return isHappy(next, set);
        }

    }

    public static void main(String[] args) {
        IsHappy isHappy = new IsHappy();
        boolean happy = isHappy.isHappy(19);
        System.out.println(happy);
    }
}
