package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/4/30 8:42 下午
 * @Description: 83. 删除排序链表中的重复元素
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(head.getVal());
        ListNode current = newHead;
        int pre = head.getVal();
        ListNode node = head.getNext();
        while (node != null) {
            if (node.getVal() != pre) {
                current.setNext(new ListNode(node.getVal()));
                current = current.getNext();
                pre = node.getVal();
            }
            node = node.getNext();
        }

        return newHead;
    }

    public static void main(String[] args) {
        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        ListNode listNode = deleteDuplicates.deleteDuplicates(ListUtil.createLinkedList(new int[]{1, 1, 2, 3, 3}));
        ListUtil.printLinkedList(listNode);
    }
}
