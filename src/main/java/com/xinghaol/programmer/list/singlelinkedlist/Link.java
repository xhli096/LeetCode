package com.xinghaol.programmer.list.singlelinkedlist;

/**
 * @author: lixinghao
 * @date: 2020/4/26 5:52 下午
 * @Description: 单链表的 API
 * 1、链表的初始化
 * 2、在链表的最后插入元素
 * 3、在链表的头部插入元素
 * 4、在链表的指定位置的"后面"插入元素
 * 5、删除链表头部的元素
 * 6、删除链表尾部的元素
 * 7、删除链表中间的元素
 * 8、获取指定下标的元素
 * 9、打印所有的元素的数据
 */
public class Link {
    private int size = 0;
    private Node first;
    private Node last;

    public Link() {
    }

    /**
     * 将节点添加到链表的最后
     * 注意：
     * 1、要考虑link元素为空的情况；
     * 2、此时可以看到设置last指针的好处
     *
     * @param data
     */
    private void addLast(int data) {
        if (size == 0) {
            fillStart(data);
        } else {
            Node node = new Node(data);
            last.next = node;
            last = node;
        }
        size++;
    }

    /**
     * 在链表的头部插入节点
     *
     * @param data
     */
    public void addFirst(int data) {
        if (size == 0) {
            fillStart(data);
        } else {
            Node node = new Node(data);
            // 将刚刚创建的节点指向当前的第一个节点
            node.next = first;
            first = node;
        }
        size++;
    }

    /**
     * 在链表的指定位置后面插入节点
     *
     * @param data
     * @param index
     */
    public void add(int data, int index) {
        if (size > index) {
            if (size == 0) {
                fillStart(data);
            } else if (index == 0) {
                addFirst(data);
            } else if (size == index + 1) {
                // 下标一般从0开始，所以size == 最后一个元素的index + 1;
                addLast(data);
            } else {
                Node pre = get(index);
                Node next = pre.next;
                Node node = new Node(data);
                pre.next = node;
                node.next = next;
                size++;
            }
        } else {
            throw new IndexOutOfBoundsException("链表没有那么长");
        }
    }

    /**
     * 删除链表的头结点
     * 注意：删除链表中的元素的时候，应该把元素的引用去掉
     */
    public void removeFirst() {
        if (size == 0) {
            throw new RuntimeException("链表已经为空，没有元素可以删除了");
        } else if (size == 1) {
            clear();
        } else {
            Node tmp = first;
            first = first.next;
            // 去掉引用，是的可以在gc过程中回收
            tmp = null;
            size--;
        }
    }

    /**
     * 删除链表尾部的节点
     * 思路：拿到链表的倒数第二个节点（索引是size - 2），把这个元素的空间释放
     */
    public void removeLast() {
        if (size == 0) {
            throw new RuntimeException("链表已经为空，没有元素可以删除了");
        } else if (size == 1) {
            clear();
        } else {
            Node tmp = get(size - 2);
            tmp.next = null;
            last = tmp;
            size--;
        }
    }

    /**
     * 删除链表中间的元素
     *
     * @param index
     */
    public void removeMiddle(int index) {
        if (size == 0) {
            throw new RuntimeException("链表已经为空，没有元素可以删除了");
        } else if (size == 1) {
            clear();
        } else {
            if (index == 0) {
                removeFirst();
            } else if (index == size - 1) {
                removeLast();
            } else {
                Node tmp = get(index - 1);
                Node next = tmp.next;
                tmp.next = next.next;
                next = null;
                size--;
            }
        }
    }

    public int size() {
        return size;
    }

    public void printAll() {
        Node node = first;
        while (node != null) {
            System.out.println(node.data + " -> ");
            node = node.next;
        }
        System.out.println("NULL");
    }

    private Node get(int index) {
        Node node = first;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private void fillStart(int data) {
        Node node = new Node(data);
        first = node;
        last = first;
    }
}

class Node {
    int data;
    Node next;

    Node(int value) {
        this.data = value;
    }
}
