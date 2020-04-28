package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/4/27 10:21 下午
 * @Description: 206. 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class ReverseList {
    /**
     * 穿针引线 画图理解
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        ListNode next = node.getNext();
        node.setNext(null);

        while (next != null) {
            ListNode tmp = next.getNext();
            next.setNext(node);
            node = next;
            next = tmp;
        }

        return node;
    }

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        ListNode node = reverseList.reverseList(ListUtil.createLinkedList(new int[]{1, 2, 3, 4, 5}));
        ListUtil.printLinkedList(node);
    }
}
