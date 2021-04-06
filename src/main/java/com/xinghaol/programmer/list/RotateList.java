package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2021/4/3 11:22 下午
 * @Description: 61题
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int count = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            count++;
        }
        int step = k % count;
        if (step == 0) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        int c = 0;

        while (c < step) {
            fast = fast.next;
            c++;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;
        ListNode newTail = newHead;

        while (newTail.next != null) {
            newTail = newTail.next;
        }

        newTail.next = head;

        return newHead;
    }

    private ListNode reverse(ListNode startNode, ListNode endNode) {
        ListNode pre = null;
        ListNode current = startNode;

        while (current != endNode) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }

        current.next = pre;
        pre = current;

        return pre;
    }

    public static void main(String[] args) {
        RotateList rotateList = new RotateList();
        ListNode linkedList = ListUtil.createLinkedList(new int[] { 0, 1, 2 });
        ListNode listNode = rotateList.rotateRight(linkedList, 4);
        ListUtil.printLinkedList(listNode);
    }
}
