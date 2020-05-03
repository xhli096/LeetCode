package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/5/1 1:00 下午
 * @Description:
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node1 = l1;
        ListNode node2 = l2;
        ListNode head = new ListNode(-1);
        ListNode current = head;
        while (node1 != null && node2 != null) {
            int value1 = node1.getVal();
            int value2 = node2.getVal();
            if (value1 <= value2) {
                current.setNext(new ListNode(node1.getVal()));
                node1 = node1.getNext();
            } else {
                current.setNext(new ListNode(node2.getVal()));
                node2 = node2.getNext();
            }
            current = current.getNext();
        }
        if (node1 != null) {
            current.setNext(node1);
        }
        if (node2 != null) {
            current.setNext(node2);
        }

        return head.getNext();
    }

    public static void main(String[] args) {
        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        ListNode listNode = mergeTwoLists.mergeTwoLists(ListUtil.createLinkedList(new int[]{1, 2, 4}), ListUtil.createLinkedList(new int[]{1, 3, 4}));
        ListUtil.printLinkedList(listNode);
    }
}
