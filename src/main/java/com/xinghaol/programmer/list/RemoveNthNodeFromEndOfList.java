package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2021/4/3 11:51 下午
 * @Description: 19 移除链表中倒数第n个节点 middle
 */
public class RemoveNthNodeFromEndOfList {

    /**
     * 当fast为null，说明在遍历到n的时候，fast 为null了，退出了while循环，此时要移除的就是链表的首节点。
     *
     * @param head
     * @param n
     *
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        int count = 0;
        while (count < n) {
            count++;
            fast = fast.next;
        }

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        if (fast == null) {
            head = head.next;
        } else {
            ListNode next = slow.next.next;
            slow.next = next;
        }

        return head;
    }
}
