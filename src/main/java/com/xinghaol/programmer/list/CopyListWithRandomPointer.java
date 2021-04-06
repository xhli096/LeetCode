package com.xinghaol.programmer.list;

/**
 * @author: lixinghao
 * @date: 2021/4/4 11:43 上午
 * @Description: 138 复制带随机指针的链表
 */
public class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }
        Node node = head;

        // 生成相同的节点
        while(node != null) {
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
            node = newNode.next;
        }

        // 复制随机指针
        node = head;
        while (node != null) {
            if(node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        // 拆分成两条链表
        Node dummy = new Node(-1);
        node = head;
        Node current = dummy;
        while (node != null) {
            current.next = node.next;
            current = current.next;
            node.next = current.next;
            node = node.next;
        }

        return  dummy.next;
    }

    public static void main(String[] args) {
        CopyListWithRandomPointer copyListWithRandomPointer = new CopyListWithRandomPointer();

        Node head = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        head.random = null;
        node1.random = head;
        node2.random = node4;
        node3.random = node2;
        node4.random = head;

        copyListWithRandomPointer.copyRandomList(head);
    }
}


