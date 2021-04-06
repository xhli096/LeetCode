package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2021/4/3 11:55 下午
 * @Description: 234  回文链表，逆序后比较
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        ListNode l1 = copyListNode(head);
        ListNode newHead = reverse(head);
        ListNode l2 = newHead;

        while (l1 != null) {
            if (l1.val == l2.val) {
                l1 = l1.next;
                l2 = l2.next;
            } else {
                return false;
            }
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    private ListNode copyListNode(ListNode head) {
        ListNode node = head;
        ListNode newHead = new ListNode(head.val);
        ListNode current = newHead;

        while (node != null) {
            node = node.next;
            if(node == null) {
                break;
            }
            ListNode listNode = new ListNode(node.val);
            current.next = listNode;
            current = current.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        PalindromeLinkedList palindromeLinkedList = new PalindromeLinkedList();
        boolean palindrome = palindromeLinkedList.isPalindrome(ListUtil.createLinkedList(new int[] { 1, 1, 2, 1 }));
        System.out.println(palindrome);
    }
}
