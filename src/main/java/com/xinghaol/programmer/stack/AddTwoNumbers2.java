package com.xinghaol.programmer.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/4/14 10:47 上午
 * @Description: 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int length1 = 0;
        int length2 = 0;
        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        List<Integer> value1List = new ArrayList<>();
        List<Integer> value2List = new ArrayList<>();

        while (tmp1 != null) {
            length1++;
            value1List.add(tmp1.val);
            tmp1 = tmp1.next;
        }
        while (tmp2 != null) {
            length2++;
            value2List.add(tmp2.val);
            tmp2 = tmp2.next;
        }
        int min = Math.min(length1, length2);
        int max = Math.max(length1, length2);

        List<Integer> list = new ArrayList<>();
        boolean isJinWei = false;

        int index = 0;
        if (min == length1) {
            for (int i = min - 1, j = max - 1; i >= 0; i--, j--) {
                int current = value1List.get(i) + value2List.get(j);
                if (isJinWei) {
                    current += 1;
                    isJinWei = false;
                }
                if (current >= 10) {
                    current -= 10;
                    isJinWei = true;
                }
                list.add(index, current);
                index++;
            }
            for (int i = max - min - 1; i >= 0; i--) {
                int current = value2List.get(i);
                if (isJinWei) {
                    current++;
                    isJinWei = false;
                }
                if (current >= 10) {
                    current -= 10;
                    isJinWei = true;
                }
                list.add(index, current);
                index++;
            }
            if (isJinWei) {
                list.add(1);
            }
        } else {
            for (int i = min - 1, j = max - 1; i >= 0; i--, j--) {
                int current = value1List.get(j) + value2List.get(i);
                if (isJinWei) {
                    current += 1;
                    isJinWei = false;
                }
                if (current >= 10) {
                    current -= 10;
                    isJinWei = true;
                }
                list.add(index, current);
                index++;
            }
            for (int i = max - min - 1; i >= 0; i--) {
                int current = value1List.get(i);
                if (isJinWei) {
                    current++;
                    isJinWei = false;
                }
                if (current >= 10) {
                    current -= 10;
                    isJinWei = true;
                }
                list.add(index, current);
                index++;
            }
            if (isJinWei) {
                list.add(1);
            }
        }

        ListNode head = new ListNode(-1);
        for (int i = 0; i < list.size(); i++) {
            ListNode node = new ListNode(list.get(i));
            node.next = head.next;
            head.next = node;
        }

        return head.next;
    }

    /**
     * 使用栈的特性存储数据
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode head = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int value1 = stack1.isEmpty() ? 0 : stack1.pop();
            int value2 = stack2.isEmpty() ? 0 : stack2.pop();
            int result = value1 + value2 + carry;
            ListNode node = new ListNode(result % 10);
            node.next = head;
            head = node;
            carry = result / 10;
        }

        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        ListNode ll1 = new ListNode(2);
        ListNode lll1 = new ListNode(4);
        ListNode llll1 = new ListNode(3);
        l1.next = ll1;
        ll1.next = lll1;
        lll1.next = llll1;

        ListNode l2 = new ListNode(5);
        ListNode ll2 = new ListNode(6);
        ListNode lll2 = new ListNode(4);
        l2.next = ll2;
        ll2.next = lll2;

    }
}
