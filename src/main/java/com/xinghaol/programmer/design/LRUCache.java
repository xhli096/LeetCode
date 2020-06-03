package com.xinghaol.programmer.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/5/25 9:25 下午
 * @Description: LRUCache的设计
 */
public class LRUCache {
    /**
     * key-》Node
     */
    private Map<Integer, Node> map;

    private DoubleList cache;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new DoubleList();
        map = new HashMap<>();
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            // 将原来的节点删除掉，为节点的替换工作，此时不涉及空间问题。
            cache.remove(map.get(key));
        } else {
            if (cache.size() >= capacity) {
                Node last = cache.removeLast();
                // 移除掉链表中删除节点的值
                map.remove(last.key);
            }
        }
        cache.addFirst(node);
        map.put(key, node);
    }

    /**
     * 返回数据的同时，需要将当前的node移动至链表首部
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            Node node = map.get(key);
            // 先移除原来的节点，然后添加至链表首部。
            put(node.key, node.value);
            return node.value;
        }
    }


    class Node {
        public int key;
        public int value;
        public Node pre;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 双向链表
     * 至于为什么使用双向链表？在删除节点时，不仅要删除当前节点，还需要维护当前节点的前驱节点和后继节点之间的指针关系，
     * 又需要时间复杂度为O(1)。所以使用双向链表。
     */
    class DoubleList {
        /**
         * 头结点，虚节点
         */
        private Node head;
        /**
         * 尾结点，虚节点
         */
        private Node tail;
        /**
         * 大小
         */
        private int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
            size = 0;
        }

        public int size() {
            return this.size;
        }

        /**
         * 在链表头部插入节点
         *
         * @param node
         */
        public void addFirst(Node node) {
            node.next = head.next;
            node.pre = head;
            // 更改head下一个节点的前驱指针
            head.next.pre = node;
            head.next = node;
            size++;
        }

        /**
         * 移除双向链表中的最后一个节点
         *
         * @return
         */
        public Node removeLast() {
            if (tail.pre == head) {
                return null;
            }
            Node last = tail.pre;
            remove(last);
            return last;
        }

        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }

        @Override
        public String toString() {
            return "DoubleList{" +
                    "head=" + head +
                    ", tail=" + tail +
                    ", size=" + size +
                    '}';
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        int i = cache.get(1);
        System.out.println(i);
        cache.put(3, 3);
        i = cache.get(2);
        System.out.println(i);
    }
}
