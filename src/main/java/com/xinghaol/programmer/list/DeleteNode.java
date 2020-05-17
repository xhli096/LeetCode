package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/5/16 10:15 下午
 * @Description: 237. 删除链表中的节点
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class DeleteNode {
    /**
     * 通过将下一个节点的值和指针赋值给当前节点，完成删除下一个节点。即符合题目要求。
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
