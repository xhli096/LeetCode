package com.xinghaol.programmer.week.week0419;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/4/19 11:37 上午
 * @Description:
 */
public class MinNumberOfFrogs {
    public int minNumberOfFrogs(String croakOfFrogs) {
        if (croakOfFrogs.isEmpty()) {
            return -1;
        }
        // 用于存储字符串"croak"的各个前缀个数，如"c"、"cr"、"cro"、"croa"和"croak"
        Map<String, Integer> prefixMap = new HashMap<>();
        // 记录字符串"croak"的个数
        int frogsCount = 0;
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            String current = "";
            String prefix = "";
            switch (croakOfFrogs.charAt(i)) {
                case 'c':
                    prefixMap.put("c", prefixMap.getOrDefault("c", 0) + 1);
                    break;
                case 'r':
                    // 顺序必须为先c后r
                    current = "cr";
                    prefix = current.substring(0, current.length() - 1);
                    // 说明先出现了r，前面没有出现c
                    if (prefixMap.getOrDefault(prefix, 0) < 1) {
                        return -1;
                    }
                    // 消耗了一个前缀去组成了cr，则前缀的数量减1
                    prefixMap.put(prefix, prefixMap.get(prefix) - 1);
                    if (prefixMap.get(prefix) == 0) {
                        prefixMap.remove(prefix);
                    }
                    prefixMap.put(current, prefixMap.getOrDefault(current, 0) + 1);
                    break;
                case 'o':
                    current = "cro";
                    prefix = current.substring(0, current.length() - 1);
                    if (prefixMap.getOrDefault(prefix, 0) < 1) {
                        return -1;
                    }
                    // 消耗了一个前缀去组成了cro，则前缀的数量减1
                    prefixMap.put(prefix, prefixMap.get(prefix) - 1);
                    if (prefixMap.get(prefix) == 0) {
                        prefixMap.remove(prefix);
                    }
                    prefixMap.put(current, prefixMap.getOrDefault(current, 0) + 1);
                    break;
                case 'a':
                    current = "croa";
                    prefix = current.substring(0, current.length() - 1);
                    if (prefixMap.getOrDefault(prefix, 0) < 1) {
                        return -1;
                    }
                    // 消耗了一个前缀去组成了cro，则前缀的数量减1
                    prefixMap.put(prefix, prefixMap.get(prefix) - 1);
                    if (prefixMap.get(prefix) == 0) {
                        prefixMap.remove(prefix);
                    }
                    prefixMap.put(current, prefixMap.getOrDefault(current, 0) + 1);
                    break;
                case 'k':
                    current = "croak";
                    prefix = current.substring(0, current.length() - 1);
                    if (prefixMap.getOrDefault(prefix, 0) < 1) {
                        return -1;
                    }
                    // 消耗了一个前缀去组成了cro，则前缀的数量减1
                    prefixMap.put(prefix, prefixMap.get(prefix) - 1);
                    if (prefixMap.get(prefix) == 0) {
                        prefixMap.remove(prefix);
                    }
                    prefixMap.put(current, prefixMap.getOrDefault(current, 0) + 1);
                    break;
                default:
                    return -1;
            }
            // 每次遍历完一个字符后，统计当前需要的青蛙数量
            int sum = 0;
            for (String key : prefixMap.keySet()) {
                if (!key.equals("croak")) {
                    sum += prefixMap.get(key);
                }
            }
            frogsCount = Math.max(frogsCount, sum);
        }

        // 按照上面的循环来看，上面的前缀消耗完之后，就只剩了字符串"croak"，所以map的大小为1
        // 如果map的大小不为1，则说明有不成对的croak，则返回-1
        if (prefixMap.size() > 1) {
            return -1;
        } else {
            return frogsCount;
        }
    }


    @Test
    public void fun() {
        int result = minNumberOfFrogs("croakcroak");
        System.out.println(result);
    }
}
