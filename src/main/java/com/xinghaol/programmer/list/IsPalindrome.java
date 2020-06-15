package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/6/15 5:00 下午
 * @Description: 234. 回文链表
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class IsPalindrome {
    /**
     * 链表逆序，比较每个节点
     * O(N)时间复杂度
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        // 链表逆序
        ListNode newHead = new ListNode(-1);
        ListNode current = new ListNode(head.val);
        ListNode next = head.next;
        while (current != null) {
            current.next = newHead.next;
            newHead.next = current;
            if (next != null) {
                current = new ListNode(next.val);
                next = next.next;
            } else {
                current = null;
            }
        }
        // 比较两个链表的每个节点
        return compareListNode(newHead.next, head);
    }

    private boolean compareListNode(ListNode node1, ListNode node2) {
        ListNode p1 = node1;
        ListNode p2 = node2;

        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        ListNode linkedList = ListUtil.createLinkedList(new int[]{1, 2, 3, 1});
        ListUtil.printLinkedList(linkedList);
        boolean palindrome = isPalindrome.isPalindrome(linkedList);
        System.out.println(palindrome);
    }

    public boolean isPalindrome2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        ListNode cur = null;
        while (fast != null && fast.next != null) {
            cur = slow;

            slow = slow.next;
            fast = fast.next.next;

            cur.next = pre;
            pre = cur;
        }
        if (fast != null && fast.next == null) {
            slow = slow.next;
        }
        while (slow != null) {
            if (slow.val != cur.val) {
                return false;
            }
            slow = slow.next;
            cur = cur.next;
        }
        return true;
    }
}
