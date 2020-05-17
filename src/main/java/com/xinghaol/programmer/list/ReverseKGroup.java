package com.xinghaol.programmer.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/5/16 11:06 上午
 * @Description:
 */
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;

        // 设置一个虚拟头结点
        ListNode newHead = new ListNode(-1);
        ListNode currentNode = newHead;

        while (true) {
            List<ListNode> listNodes = new ArrayList<>(k);
            for (int i = 1; i <= k; i++) {
                if (fast != null) {
                    ListNode node = new ListNode(fast.getVal());
                    node.setNext(fast.getNext());
                    listNodes.add(node);

                    fast = fast.getNext();
                } else {
                    break;
                }
            }

            if (fast != null) {
                currentNode = buildList(listNodes, currentNode);
            } else {
                if (listNodes.size() == k) {
                    currentNode = buildList(listNodes, currentNode);
                } else {
                    for (int i = 0; i < listNodes.size(); i++) {
                        currentNode.setNext(listNodes.get(i));
                        currentNode = currentNode.getNext();
                    }
                }
                currentNode.setNext(null);
                break;
            }
        }

        return newHead.getNext();
    }

    private ListNode buildList(List<ListNode> listNodes, ListNode currentNode) {
        int size = listNodes.size();

        for (int i = size - 1; i >= 0; i--) {
            currentNode.setNext(listNodes.get(i));
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(-1);
        newHead.setNext(head);

        ListNode pre = newHead;
        ListNode end = newHead;

        while (end.getNext() != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.getNext();
            }
            if (end == null) {
                break;
            }

            ListNode start = pre.getNext();
            ListNode next = end.getNext();
            end.setNext(null);

            pre.setNext(reverse(start));
            start.setNext(next);
            pre = start;
            end = pre;
        }

        return newHead.getNext();
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.getNext();
            current.setNext(pre);
            pre = current;
            current = next;
        }

        return pre;
    }

    /**
     * 0毫秒
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup3(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        ListNode first = head;
        ListNode last = null, remain = first;

        for (int i = 0; i < k; i++) {
            if (remain == null) {
                return head;
            }
            last = remain;
            remain = remain.getNext();
        }

        last.setNext(null);
        head = reverseList(head);
        first.setNext(reverseKGroup3(remain, k));

        return head;
    }

    /**
     * 反转链表
     */
    private ListNode reverseList(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        ListNode prev = null, current = head;
        while (current != null) {
            ListNode next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        ReverseKGroup reverseKGroup = new ReverseKGroup();
        ListNode node = reverseKGroup.reverseKGroup(ListUtil.createLinkedList(new int[]{1, 2}), 2);
        ListUtil.printLinkedList(node);
    }
}
