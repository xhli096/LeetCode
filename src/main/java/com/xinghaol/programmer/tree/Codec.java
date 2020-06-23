package com.xinghaol.programmer.tree;

import com.alibaba.fastjson.JSON;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: lixinghao
 * @date: 2020/6/16 5:49 下午
 * @Description: 297. 二叉树的序列化与反序列化
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 */
public class Codec {
    /**
     * 层次遍历
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return new String();
        }
        StringBuilder result = new StringBuilder();
        result.append('[');
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode current;

        int validNum = 1;
        while (validNum != 0) {
            current = queue.poll();
            if (current != null) {
                validNum--;
                result.append(current.val).append(",");
                if (current.left != null) {
                    queue.offer(current.left);
                    validNum++;
                } else {
                    queue.offer(null);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                    validNum++;
                } else {
                    queue.offer(null);
                }
            } else {
                result.append("null,");
            }
        }
        // 最后多出一个,
        result.setLength(result.length() - 1);
        result.append("]");

        return result.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty() || data.length() < 3) {
            return null;
        }
        String[] datas = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(datas[0]));
        Deque<TreeNode> quque = new LinkedList<>();
        quque.add(root);
        TreeNode cur;
        int idx = 0;
        while (true) {
            cur = quque.remove();
            if (++idx >= datas.length) {
                break;
            }
            // 如果不是null
            if (datas[idx].compareTo("null") != 0) {
                cur.left = new TreeNode(Integer.parseInt(datas[idx]));
                quque.offer(cur.left);
            }
            if (++idx >= datas.length) {
                break;
            }
            if (datas[idx].compareTo("null") != 0) {
                cur.right = new TreeNode(Integer.parseInt(datas[idx]));
                quque.offer(cur.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode deserialize = codec.deserialize("[1,2,3,null,null,4,5]");
        System.out.println(JSON.toJSONString(deserialize));
        String serialize = codec.serialize(deserialize);
        System.out.println(serialize);
    }
}
