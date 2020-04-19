package com.xinghaol.programmer.week.week0419;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/4/19 10:31 上午
 * @Description:
 */
public class Reformat {
    @Test
    public void fun() {
        System.out.println(reformat("ab123"));
    }

    public String reformat(String s) {
        if (s.length() <= 0) {
            return "";
        }
        boolean hasCharacter = false;
        boolean hasDigit = false;
        Queue<Character> list = new LinkedList<>();
        Queue<Character> integerList = new LinkedList<>();

        for (Character c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                integerList.offer(c);
                hasDigit = true;
            } else {
                list.offer(c);
                hasCharacter = true;
            }
        }

        if ((hasCharacter == true && hasDigit == false) || (hasCharacter == false && hasDigit == true)) {
            if (s.length() == 1) {
                return s;
            } else {
                return "";
            }
        }


        int max = Math.max(list.size(), integerList.size());
        System.out.println(max);
        StringBuilder stringBuilder = new StringBuilder();
        if (list.size() == max) {
            while (!list.isEmpty()) {
                stringBuilder.append(list.poll());
                if (!integerList.isEmpty()) {
                    stringBuilder.append(integerList.poll());
                }
            }
        } else {
            while (!integerList.isEmpty()) {
                stringBuilder.append(integerList.poll());
                if (!list.isEmpty()) {
                    stringBuilder.append(list.poll());
                }
            }
        }

        return stringBuilder.toString();
    }
}
