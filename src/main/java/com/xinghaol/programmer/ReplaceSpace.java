package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/14 11:16 下午
 * @Description: 替换字符串
 */
public class ReplaceSpace {
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int len = s.length();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                len += 2;
            }
        }
        char[] ans = new char[len];
        int i = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                ans[i++] = '%';
                ans[i++] = '2';
                ans[i++] = '0';
            } else {
                ans[i++] = c;
            }
        }
        return new String(ans);
    }
}
