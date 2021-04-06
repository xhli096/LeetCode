package com.xinghaol.programmer.list;

import lombok.Data;

/**
 * @author: lixinghao
 * @date: 2021/4/4 11:44 上午
 * @Description:
 */
@Data
public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
