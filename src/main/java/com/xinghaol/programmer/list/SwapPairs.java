package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/6/11 6:09 下午
 * @Description: 24. 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class SwapPairs {
    /**
     * 每次处理两个，如果不够两个，则一定处理到链表的最后了，直接放到后面即可
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(-1);
        ListNode node = head;
        ListNode current = newHead;

        while (node != null) {
            ListNode preNode = new ListNode(node.val);
            node = node.next;
            if (node != null) {
                current.next = new ListNode(node.val);
                current = current.next;
                current.next = new ListNode(preNode.val);
                node = node.next;
            } else {
                current.next = new ListNode(preNode.val);
                node = null;
            }
            current = current.next;
        }

        return newHead.next;
    }

    public static void main(String[] args) {
        SwapPairs swapPairs = new SwapPairs();
        ListNode node = ListUtil.createLinkedList(new int[]{1, 2, 3, 4});
        ListUtil.printLinkedList(swapPairs.swapPairs(node));
    }
}
