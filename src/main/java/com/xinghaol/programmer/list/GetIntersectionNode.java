package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/6/14 11:53 下午
 * @Description: 160. 相交链表
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class GetIntersectionNode {
    /**
     * 这两个链表从同距离末尾同等距离的位置开始遍历，这个位置只能是比较短的链表的头结点位置
     * 消除两个链表的长度差
     * 设置pa、pb两个指针，依次向后遍历。当pa到达末尾，pa=headB；同理pb也是如此操作；此时长度差就会消除。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;

        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }

        // 如果现在是个单链表，则同时为null；如果为相交链表，则为相交节点
        return pa;
    }
}
