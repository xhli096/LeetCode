package com.xinghaol.programmer.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/6/21 11:27 上午
 * @Description: 726. 原子的数量
 * https://leetcode-cn.com/problems/number-of-atoms/
 */
public class CountOfAtoms {
    public String countOfAtoms(String formula) {
        //记录（ 以元素计算的索引  CaHO(  此时 ( 的索引为 3
        LinkedList<Integer> recordFrontBracket = new LinkedList<>();
        int length = formula.length();

        //记录元素名CaHO    Ca → H → O
        ArrayList<String> atoms = new ArrayList<>();
        //记录atoms对应索引元素的个数CaHO   1 → 1 → 1
        ArrayList<Integer> counts = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            char c = formula.charAt(i);
            // 索引位置
            if (c == '(') {
                //记录（ 的索引
                recordFrontBracket.add(atoms.size());
            } else if (c == ')') {
                // 索引结束位置，获取后面跟随的数字
                int number = 0;
                while (i + 1 < length && isDigital(formula.charAt(i + 1))) {
                    number = number * 10 + formula.charAt(i + 1) - '0';
                    i++;
                }
                // 括号外没有标注，默认为1
                if (number == 0) {
                    number = 1;
                }
                //得到和 `)` 匹配的 `(` 的索引
                int index = recordFrontBracket.removeLast();
                // 处理括号里面的元素个数，从counts中取得index下标的，一直到最后
                for (int j = index; j < counts.size(); j++) {
                    counts.set(j, counts.get(j) * number);
                }
            } else if (isUpperCase(c)) {
                int t = i;
                while (t + 1 < length && isLowerCase(formula.charAt(t + 1))) {
                    t++;
                }
                //添加到存元素的列表
                atoms.add(formula.substring(i, t + 1));
                i = t;

                //计算此时 元素后面的数字
                int count = 0;
                while (i + 1 < length && isDigital(formula.charAt(i + 1))) {
                    count = count * 10 + formula.charAt(i + 1) - '0';
                    i++;
                }
                if (count == 0) {
                    //没有 默认为 1
                    count = 1;
                }
                //添加到存元素数量的列表
                counts.add(count);
            }
        }

        //统计 所有元素 以及 其总数量
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < atoms.size(); i++) {
            map.put(atoms.get(i), map.getOrDefault(atoms.get(i), 0) + counts.get(i));
        }
        //获取所有元素名
        String[] arr = new String[map.size()];
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            arr[index] = iterator.next().getKey();
            index++;
        }

        //并对其按字母排序
        Arrays.sort(arr);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            //添加元素
            res.append(arr[i]);
            //添加对应的数量  1的话不添加
            int count = map.get(arr[i]);
            res.append(count != 1 ? count : "");
        }
        return res.toString();
    }

    public boolean isDigital(char c) {
        return c >= '0' && c <= '9';
    }

    public boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static void main(String[] args) {
        CountOfAtoms countOfAtoms = new CountOfAtoms();
        String atoms = countOfAtoms.countOfAtoms("Mg(OH)2");
        System.out.println(atoms);
    }
}
