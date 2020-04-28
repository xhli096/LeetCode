package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2020/4/26 10:30 下午
 * @Description:
 */
public class MergeKLists {
    /**
     * 需要的时间有点长啊。穿针引线的思路
     * <p>
     * 时间复杂度O(NK)，N为链表的长度，K为链表的个数
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 添加一个虚拟节点
        ListNode head = new ListNode(-1);
        ListNode current = head;
        int length = lists.length;
        ListNode[] pointer = new ListNode[length];

        // 初始化每个指针
        for (int i = 0; i < length; i++) {
            pointer[i] = lists[i];
        }

        while (true) {
            int max = Integer.MAX_VALUE;
            // 用于替换向下一个节点
            int index = -1;
            for (int i = 0; i < length; i++) {
                if (pointer[i] != null && pointer[i].getVal() < max) {
                    max = pointer[i].getVal();
                    index = i;
                }
            }
            // 如果没有节点，即退出循环
            if (index == -1) {
                break;
            }

            ListNode node = new ListNode(pointer[index].getVal());
            current.setNext(node);
            current = node;
            pointer[index] = pointer[index].getNext();
        }

        return head.getNext();
    }

    public static void main(String[] args) {
        ListNode listNode1 = ListUtil.createLinkedList(new int[]{1, 4, 5});
        ListNode listNode2 = ListUtil.createLinkedList(new int[]{1, 3, 4});
        ListNode listNode3 = ListUtil.createLinkedList(new int[]{2, 6});


        ListNode[] listNodes = new ListNode[]{listNode1, listNode2, listNode3};
        MergeKLists mergeKLists = new MergeKLists();
        ListNode listNode = mergeKLists.mergeKLists(listNodes);
        ListUtil.printLinkedList(listNode);
    }
}
