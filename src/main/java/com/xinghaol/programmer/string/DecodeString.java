package com.xinghaol.programmer.string;

import java.util.LinkedList;

/**
 * @author: lixinghao
 * @date: 2020/5/28 4:54 下午
 * @Description:
 */
public class DecodeString {
    /**
     * 辅助栈
     * <p>
     * 1、构建辅助栈stack，遍历字符串s中的每个字符c
     * 1.1：当c为数字时，将数字字符转化为数字multi，用于后续倍数计算；
     * 1.2：当c为字母时，在res尾部添加c；
     * 1.3：当c为[时，将当前multi和res入栈，并且分别置空置0；
     * 1.3.1：记录此[前的临时结果res入栈，用于发现对应]后的拼接操作
     * 1.3.2：记录此[前的倍数muti至栈，用于发现对应]后，获取multi ✖️ [...]字符串
     * 1.3.1：进入新的[后，res和multi重新记录；
     * 1.4：当c为]时，stack出栈，拼接字符串res=last_res+cur_multi*res;其中：
     * 1.4.1：last_res是上一个[到当前]的字符串，例如3[a2[c]]中的a；
     * 1.4.2：cur_multi是当前[到]内字符串的重复倍数，如3[a2[c]]中的2；
     * <p>
     * 还是比较好理解的
     * <p>
     * 时间复杂度:O(N)
     * 空间复杂度:O(N)
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        int length = s.length();

        // abc3[a2[c]]2[bc]
        StringBuilder stringBuilder = new StringBuilder();
        LinkedList<Integer> multiList = new LinkedList<>();
        LinkedList<String> res = new LinkedList<>();
        int multi = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (isCharacter(c)) {
                stringBuilder.append(c);
            } else if (c == '[') {
                multiList.addLast(multi);
                res.add(stringBuilder.toString());
                multi = 0;
                stringBuilder = new StringBuilder();
            } else if (c == ']') {
                int lastMulti = multiList.removeLast();
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < lastMulti; j++) {
                    tmp.append(stringBuilder);
                }
                stringBuilder = new StringBuilder(res.removeLast() + tmp);
            } else {
                // 前面的数字可能是多位数
                multi = multi * 10 + Integer.parseInt(c + "");
            }
        }

        return stringBuilder.toString();
    }

    private boolean isCharacter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        String string = decodeString.decodeString("100[leetcode]");
        System.out.println(string);
    }

    public String decodeString2(String s) {
        return dfs(s, 0)[1];
    }

    private String[] dfs(String s, int i) {//字符串s和索引i,返回字符数组，索引i和结果string
        StringBuilder res = new StringBuilder();
        int multi = 0;//重复次数
        while (i < s.length()) {//没有走到结尾时
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {//是数字
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                //计算数字的值
            } else if (s.charAt(i) == '[') {//递归开启
                String[] tmp = dfs(s, i + 1);
                i = Integer.parseInt(tmp[0]);//遇到']',i=结尾索引，即右括号位置
                while (multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            } else if (s.charAt(i) == ']') {
                return new String[]{String.valueOf(i), res.toString()};
            } else {
                res.append(s.charAt(i));
            }
            i++;
        }
        return new String[]{String.valueOf(i), res.toString()};
    }
}
