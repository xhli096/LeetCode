package com.xinghaol.programmer.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/4/6 7:10 下午
 * @Description:
 */
public class LFUCache {
    /**
     * 缓存大小
     */
    private int capacity;
    /**
     * 当前缓存中的元素个数
     */
    private int size;

    /**
     * cache
     */
    private Map<Integer, Node> cache;

    /**
     * 访问次序存储，每个双向链表中的节点，访问次序是一致的。
     */
    private Map<Integer, DuplexLinkedList> frequentMap;

    private int minFrequent;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        frequentMap = new HashMap<>();
        minFrequent = Integer.MAX_VALUE;
    }

    /**
     * 访问某个key，其频次需要加一，放入新的双向链表中
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (cache.containsKey(key)) {
            Node currentNode = cache.get(key);
            currentNode.freq++;
            removeListNode(currentNode);
            addListNode(currentNode);

            return currentNode.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            node.freq++;
            removeListNode(node);
            addListNode(node);
            return;
        } else {
            if (cache.size() == capacity) {
                // 删除访问次数最小的链表的末节点
                DuplexLinkedList duplexLinkedList = frequentMap.get(minFrequent);
                Node tailNode = duplexLinkedList.tail.pre;
                removeListNode(tailNode);
                cache.remove(tailNode.key);
            }
            Node node = new Node(key, value);
            addListNode(node);
            cache.put(key, node);

        }
    }

    private void addListNode(Node node) {
        DuplexLinkedList duplexLinkedList = frequentMap.get(node.freq);
        if (duplexLinkedList == null) {
            duplexLinkedList = new DuplexLinkedList();
        }

        duplexLinkedList.addNode(node);
        frequentMap.put(node.freq, duplexLinkedList);
        if (node.freq < minFrequent) {
            minFrequent = node.freq;
        }
    }

    private void removeListNode(Node node) {
        // 修改节点指向。
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;

        frequentMap.get(node.freq).count--;
        // 链表中无节点后删除链表
        if (frequentMap.get(node.freq).count == 0) {
            frequentMap.remove(node.freq);
        }
    }

    class Node {
        private int key;
        private int value;
        private int freq = 1;
        // 当前节点的前驱和后继节点
        private Node pre;
        private Node next;

        Node() {

        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DuplexLinkedList {
        /**
         * 虚拟头结点，无前驱节点
         */
        private Node head;
        /**
         * 虚拟尾结点，无后继节点
         */
        private Node tail;

        private int count;

        DuplexLinkedList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
            count = 0;
        }

        void addNode(Node node) {
            Node next = head.next;
            head.next = node;
            node.next = next;
            next.pre = node;
            node.pre = head;

            count++;
        }
    }
}
