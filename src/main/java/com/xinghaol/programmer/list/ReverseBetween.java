package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/4/27 10:55 下午
 * @Description: 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 设置一个虚拟节点
        ListNode newHead = new ListNode(0);
        ListNode current = newHead;
        ListNode node = head;
        int index = 1;

        while (index < m) {
            current.setNext(node);
            current = node;
            node = node.getNext();
            index++;
        }
        ListNode preNode = current;
        // current 为反转的前一个节点

        ListNode tmp = node;
        ListNode reverseCurrentNode = node;
        ListNode next = reverseCurrentNode.getNext();
        reverseCurrentNode.setNext(null);
        for (int i = m; i < n; i++) {
            ListNode t = next.getNext();
            next.setNext(reverseCurrentNode);
            reverseCurrentNode = next;
            next = t;
        }
        tmp.setNext(next);
        preNode.setNext(reverseCurrentNode);
        return newHead.getNext();
    }

    public static void main(String[] args) {
        ReverseBetween reverseBetween = new ReverseBetween();
        ListNode listNode = reverseBetween.reverseBetween(ListUtil.createLinkedList(new int[]{1, 2, 3, 4, 5}), 1, 2);
        ListUtil.printLinkedList(listNode);
    }
}
