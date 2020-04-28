package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/4/26 1:14 下午
 * @Description:
 */
public class ListUtil {
    public static ListNode createLinkedList(int[] arr) {
        if (arr.length == 0) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.setNext(new ListNode(arr[i]));
            current = current.getNext();
        }

        return head;
    }

    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.getVal() + " -> ");
            current = current.getNext();
        }
        System.out.println("NULL");
    }

}
