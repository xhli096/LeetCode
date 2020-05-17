package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/5/14 7:19 下午
 * @Description: 142. 环形链表 II
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.getNext() == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (true) {
            if (fast == null || fast.getNext() == null) {
                return null;
            }
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (slow == fast) {
                break;
            }
        }

        fast = head;
        while (fast != slow) {
            fast = fast.getNext();
            slow = slow.getNext();
        }

        return slow;
    }

    public static void main(String[] args) {
        DetectCycle detectCycle = new DetectCycle();
    }
}
