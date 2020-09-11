package com.xinghaol.programmer.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/9/9 8:32 下午
 * @Description: 2020 9 9 京东物流算法
 */
public class Number {
    /**
     * 1
     * 1 1
     * 2 1
     * 1 2 1 1
     * 1 1 1 2 2 1
     * 3 1 2 2 1 1
     * 1,3,1,1,2,2,2,1
     * 1,1,1,3,2,1,3,2,1,1
     */
    public static void main(String[] args) {
        Number number = new Number();
        List<Integer> fun = number.fun(8);
        System.out.println(JSON.toJSONString(fun));
    }

    /**
     * 求第n行的数值
     *
     * @param n
     * @return
     */
    public List<Integer> fun(int n) {
        // 特例判断
        if (n == 0) {
            return Lists.newArrayList();
        }
        if (n == 1) {
            return Lists.newArrayList(1);
        }
        // 使用队列
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        int index = 1;
        while (index < n) {
            int size = queue.size();
            int pre = 0;
            int count = 0;
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (i == 0) {
                    pre = current;
                }
                if (current == pre) {
                    count++;
                } else {
                    queue.offer(count);
                    queue.offer(pre);
                    pre = current;
                    count = 1;
                }
            }
            queue.offer(count);
            queue.offer(pre);
            index++;
        }

        List<Integer> list = Lists.newArrayList();
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }

        return list;
    }
}

