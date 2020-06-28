package com.xinghaol.programmer.list;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/4/30 8:42 下午
 * @Description: 83. 删除排序链表中的重复元素
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(head.getVal());
        ListNode current = newHead;
        int pre = head.getVal();
        ListNode node = head.getNext();
        while (node != null) {
            if (node.getVal() != pre) {
                current.setNext(new ListNode(node.getVal()));
                current = current.getNext();
                pre = node.getVal();
            }
            node = node.getNext();
        }

        return newHead;
    }

    public static void main(String[] args) {
        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        ListNode listNode = deleteDuplicates.deleteDuplicates(ListUtil.createLinkedList(new int[]{1, 1, 2, 3, 3}));
        ListUtil.printLinkedList(listNode);
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     * 指针指向第一个和第三个位置，用.next的方法来比较后面的值
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode preNode = newHead;
        ListNode node = head.next;

        while (node != null) {
            if (preNode.next.val != node.val) {
                preNode = preNode.next;
                node = node.next;
            } else {
                while (node != null && preNode.next.val == node.val) {
                    node = node.next;
                }
                preNode.next = node;
                node = node == null ? null : node.next;
            }
        }

        return newHead.next;
    }

    /**
     * 面试题 02.01. 移除重复节点
     * https://leetcode-cn.com/problems/remove-duplicate-node-lcci/description/
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode current = head;
        set.add(head.val);
        ListNode node = head.next;
        while (node != null) {
            if (!set.contains(node.val)) {
                current.next = new ListNode(node.val);
                current = current.next;
                set.add(node.val);
            }
            node = node.next;
        }

        return newHead.next;
    }
}
