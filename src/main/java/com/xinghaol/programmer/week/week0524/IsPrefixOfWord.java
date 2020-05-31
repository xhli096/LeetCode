package com.xinghaol.programmer.week.week0524;

/**
 * @author: lixinghao
 * @date: 2020/5/24 10:32 上午
 * @Description:
 */
public class IsPrefixOfWord {
    public int isPrefixOfWord(String sentence, String searchWord) {
        int lengthW = searchWord.length();
        if (lengthW == 0) {
            return -1;
        }
        String[] sentenceArray = sentence.split(" ");

        for (int i = 0; i < sentenceArray.length; i++) {
            if (check(sentenceArray[i], searchWord)) {
                return i + 1;
            }
        }

        return -1;
    }

    private boolean check(String sentence, String searchWord) {
        char[] sentenceChar = sentence.toCharArray();
        char[] sentenceWordChar = searchWord.toCharArray();
        if (sentenceChar.length < sentenceWordChar.length) {
            return false;
        }
        for (int i = 0; i < sentenceWordChar.length; i++) {
            if (sentenceChar[i] != sentenceWordChar[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        IsPrefixOfWord isPrefixOfWord = new IsPrefixOfWord();
        int index = isPrefixOfWord.isPrefixOfWord("hello from the other side", "they");
        System.out.println(index);
    }
}
