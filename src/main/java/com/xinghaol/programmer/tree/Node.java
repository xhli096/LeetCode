package com.xinghaol.programmer.tree;

import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/6/8 11:50 上午
 * @Description:
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
