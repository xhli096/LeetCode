package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/6/1 9:00 下午
 * @Description:
 */
public class SortList {
    /**
     * 递归解法
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 递归结束条件
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head.next;
        ListNode slow = head;

        // 快慢指针向后遍历，遍历到队列尾部；退出循环时，head指针已经到达链表尾部
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode tmp = slow.next;
        slow.next = null;
        // 将链表从中间一分为二，先递归去处理每一个分开子list的左、右
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        // 设置一个虚拟头结点
        ListNode h = new ListNode(0);
        ListNode res = h;

        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }

        h.next = left != null ? left : right;

        // 返回虚拟头结点的下一个
        return res.next;
    }
}
