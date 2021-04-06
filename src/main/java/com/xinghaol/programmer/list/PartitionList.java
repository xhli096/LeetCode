package com.xinghaol.programmer.list;

import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/6/22 10:05 下午
 * @Description: 86. 分隔链表
 * https://leetcode-cn.com/problems/partition-list/
 */
public class PartitionList {
    /**
     * 两个子串拼接的办法
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition2(ListNode head, int x) {
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

    public ListNode partition(ListNode head, int x) {
        if(head == null) {
            return null;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode pre = newHead;
        ListNode slow = head;
        ListNode fast = head;
        while(slow != null && fast != null) {
            if(slow.val < x) {
                pre = slow;
                slow = slow.next;
            } else {
                ListNode p = slow;
                fast = slow.next;
                while(fast != null && fast.val >= x) {
                    fast = fast.next;
                    p = p.next;
                }
                if(fast == null) {
                    break;
                } else {
                    p.next = fast.next;
                    fast.next = pre.next;
                    pre.next = fast;
                    pre = pre.next;
                }
            }
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        PartitionList partitionList = new PartitionList();
        ListNode partition = partitionList.partition(ListUtil.createLinkedList(new int[] { 2,1 }), 2);
        ListUtil.printLinkedList(partition);
    }
}
