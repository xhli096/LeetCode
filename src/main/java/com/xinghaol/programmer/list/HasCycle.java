package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/6/15 11:26 上午
 * @Description: 141. 环形链表
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            if (slow == fast) {
                break;
            }
        }

        if (fast == null) {
            return false;
        }
        if (slow == fast) {
           return true;
        }
        return false;
    }

    public static void main(String[] args) {
        HasCycle hasCycle = new HasCycle();

    }
}
