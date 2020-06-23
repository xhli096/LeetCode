package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/6/22 10:05 下午
 * @Description: 86. 分隔链表
 * https://leetcode-cn.com/problems/partition-list/
 */
public class Partition {
    /**
     * 两个子串拼接的办法
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode current1 = head1;
        ListNode current2 = head2;
        while (head != null) {
            if (head.val < x) {
                current1.next = new ListNode(head.val);
                current1 = current1.next;
            } else {
                current2.next = new ListNode(head.val);
                current2 = current2.next;
            }

            head = head.next;
        }
        current1.next = head2.next;

        return head1.next;
    }
}
