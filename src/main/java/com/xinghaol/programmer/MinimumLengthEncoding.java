package com.xinghaol.programmer;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/3/28 7:35 下午
 * @Description: 单词的压缩编码
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * <p>
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 * <p>
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 * <p>
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 * <p>
 * 示例：
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 * <p>
 * 提示：
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 */
public class MinimumLengthEncoding {
    /**
     * 存储后缀
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = Sets.newHashSet(Arrays.asList(words));

        for (String word : set) {
            for (int k = 1; k < word.length(); k++) {
                set.remove(word.substring(k));
            }
        }

        int result = 0;
        for (String word : set) {
            result += word.length() + 1;
        }

        return result;
    }

    /**
     * 字典树
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding2(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> nodes = new HashMap();

        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            TrieNode cur = trie;
            for (int j = word.length() - 1; j >= 0; --j) {
                cur = cur.get(word.charAt(j));
            }
            nodes.put(cur, i);
        }

        int ans = 0;
        for (TrieNode node : nodes.keySet()) {
            if (node.count == 0) {
                ans += words[nodes.get(node)].length() + 1;
            }
        }
        return ans;

    }

    class TrieNode {
        TrieNode[] children;
        int count;

        TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }

        public TrieNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                count++;
            }
            return children[c - 'a'];
        }
    }
}
