package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/4/19 9:20 下午
 * @Description: 太难了，我现在还不会
 */
public class GetMaxRepetitions {
    /**
     * 找到S2字符串中是否存在稳定的循环节，这样就可以将S2= head + n*(循环节) + tail
     *
     * @param s1
     * @param n1
     * @param s2
     * @param n2
     * @return
     */
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int length1 = s1.length();
        int length2 = s2.length();
        if (n1 == 0 || n2 == 0 || length1 <= 0 || length2 <= 0 || length1 * n1 < length2 * n2) {
            return 0;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        // 记录匹配到s2的字符的下标位置
        int index = 0;
        // 匹配字符串S2的个数
        int count = 0;

        // 数组用来记录，遍历完第i个s1后，出现s2的个数
        int[] countRecorder = new int[n1];
        // 数组用来记录，遍历完第i个s1后，下一个遍历s2的index
        int[] indexRecorder = new int[n1];

        for (int i = 0; i < n1; i++) {
            // 遍历S1
            for (int j = 0; j < length1; j++) {
                // 匹配s2字符，匹配成功，s2索引+1
                if (chars1[j] == chars2[index]) {
                    index++;
                }
                // 说明已经匹配完成了一个s2
                if (index == chars2.length) {
                    index = 0;
                    count++;
                }
            }
            // 记录遍历完i个s1后s2出现的次数
            countRecorder[i] = count;
            // 记录遍历完i个s1后，下一个s2的index
            indexRecorder[i] = index;

            // 下面进行剪枝，查看是否之前出现过出现过该index下标，如果已经出现过，说明已经出现了循环节，则可以直接利用
            // 上一次出现该索引是在第j个s1中（同时可以说明第一个循环节的出现是从第j+1个s1开始的）
            for (int j = 0; j < i && indexRecorder[j] == index; j++) {
                // preCount用来记录在出现循环节之前，s2的个数
                int preCount = countRecorder[j];
                // 记录循环节中出现s2的个数
                // (n1 - 1 - j) / (i - j) 循环节的个数
                // (countRecorder[i] - countRecorder[j]) 一个循环节中包含s2的个数
                int patternCount = ((n1 - 1 - j) / (i - j)) * (countRecorder[i] - countRecorder[j]);

                // remainCount: 记录剩余未构成完整循环节的部分出现的s2的个数
                //      通过取模从已有循环节记录中查找，并减去循环节之前出现的次数
                int remainCount = countRecorder[j + (n1 - 1 - j) % (i - j)] - countRecorder[j];

                // 三者相加，即为出现的s2的总次数。最后除以n2，则计算M的最大值
                return (preCount + patternCount + remainCount) / n2;
            }
        }

        // 没有循环节的出现，相当于直接使用暴力法
        return countRecorder[n1 - 1] / n2;
    }
}
