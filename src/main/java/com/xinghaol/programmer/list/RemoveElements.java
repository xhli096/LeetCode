package com.xinghaol.programmer.list;

import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/6/16 10:47 下午
 * @Description: 203. 移除链表元素
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode pre = newHead;
        ListNode current = head;
        while (current != null) {
            if (current.val != val) {
                pre = current;
                current = current.next;
            } else {
                pre.next = current.next;
                current = current.next;
            }
        }

        return newHead.next;
    }
}
