package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/6/27 10:13 上午
 * @Description: 328. 奇偶链表
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 */
public class OddEvenList {
    /**
     * 快慢指针
     * 通过pre和tail对链表的节点进行把控
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        /**
         * 三种情况无需聚合
         */
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode currentNode = head;
        ListNode pre = head.next;
        ListNode tail = head.next.next;
        while (tail != null) {
            pre.next = tail.next;
            tail.next = currentNode.next;
            currentNode.next = tail;
            currentNode = currentNode.next;
            tail = pre.next;
            pre = pre.next;
            if (tail != null) {
                tail = tail.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        OddEvenList oddEvenList = new OddEvenList();
        ListNode node = oddEvenList.oddEvenList(ListUtil.createLinkedList(new int[]{1, 2, 3, 4, 5, 6}));
        ListUtil.printLinkedList(node);
    }
}
