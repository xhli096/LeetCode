package com.xinghaol.programmer.test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: lixinghao
 * @date: 2020/3/28 11:38 下午
 * @Description:
 */
public class IlegalString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input;

        StringBuilder validStringBuilder = new StringBuilder();
        StringBuilder invalidStringBuilder = new StringBuilder();

        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            input = scanner.nextLine().trim();
            if ("".equals(input)) {
                break;
            }
            if (isValid(input)) {
                if (!list.contains(input)) {
                   list.add(input);
                }
            } else {
                invalidStringBuilder.append(input).append(" ");
            }
        }

        for (int i = 0; i < list.size(); i++) {
            validStringBuilder.append(list.get(i)).append(" ");
        }
        System.out.println(validStringBuilder.substring(0, validStringBuilder.length() - 1));
        System.out.println(invalidStringBuilder.substring(0, invalidStringBuilder.length() - 1));
    }

    private static boolean isValid(String input) {
        System.out.println(input);
        for (int i = 0; i < input.length(); i++) {
            if (!isValid(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValid(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') ||  (c >= 'A' && c <= 'Z');
    }
}
