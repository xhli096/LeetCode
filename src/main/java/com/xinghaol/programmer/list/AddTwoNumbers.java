package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2019/12/28 4:24 下午
 * @Description: 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(6);
        ListNode listNode2 = new ListNode(1);
        listNode2.setNext(listNode3);

        ListNode listNode1 = new ListNode(9);
        listNode1.setNext(listNode2);


        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        l2.setNext(l1);
        ListNode l3 = new ListNode(5);
        l3.setNext(l2);


        ListNode result = addTwoNumbers(listNode1, new ListNode(0));
        System.out.println(result);
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        int length1 = 0;
        int length2 = 0;

        while (tmp1 != null) {
            length1++;
            tmp1 = tmp1.next;
        }
        while (tmp2 != null) {
            length2++;
            tmp2 = tmp2.next;
        }

        int min = Math.min(length1, length2);
        int max = Math.max(length1, length2);
        tmp1 = l1;
        tmp2 = l2;

        List<Integer> list = new ArrayList<Integer>();
        boolean isJinwei = false;

        for (int i = 0; i < min; i++) {
            int val = tmp1.val + tmp2.val;
            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
            if (isJinwei) {
                val = val + 1;
                isJinwei = false;
            }
            if (val >= 10) {
                val = val - 10;
                isJinwei = true;
            }
            list.add(val);
        }
        ListNode node = tmp1 == null ? tmp2 : tmp1;
        if (node != null && max > min) {
            for (int i = min + 1; i <= max; i++) {
                int val = node.getVal();
                node = node.next;
                if (isJinwei) {
                    val = val + 1;
                    isJinwei = false;
                }
                if (val >= 10) {
                    val = val - 10;
                    isJinwei = true;
                }
                list.add(val);
            }
        }
        if (isJinwei) {
            list.add(1);
        }
        Collections.reverse(list);
        List<ListNode> listNodeList = new ArrayList<ListNode>();
        for (int i = 0; i < list.size(); i++) {
            ListNode listNode = new ListNode(list.get(i));
            listNodeList.add(listNode);
        }
        for (int i = 1; i < listNodeList.size(); i++) {
            listNodeList.get(i).next = listNodeList.get(i - 1);
        }

        return listNodeList.get(listNodeList.size() - 1);
    }

    private static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = new ListNode(-1);
        ListNode current = head;
        int carry = 0;

        while (l1 != null && l2 != null) {
            int value = l1.val + l2.val + carry;
            carry = value / 10;
            ListNode node = new ListNode(value % 10);
            current.next = node;
            current = current.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null) {
            while (l1 != null) {
                int value = l1.val + carry;
                carry = value / 10;
                ListNode node = new ListNode(value % 10);
                current.next = node;
                current = current.next;
                l1 = l1.next;
            }
        }
        if (l2 != null) {
            while (l2 != null) {
                int value = l2.val + carry;
                carry = value / 10;
                ListNode node = new ListNode(value % 10);
                current.next = node;
                current = current.next;
                l2 = l2.next;
            }
        }

        if (carry != 0) {
            current.next = new ListNode(carry);
        }

        return head.next;
    }

    @Data
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
