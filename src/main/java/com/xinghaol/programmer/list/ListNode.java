package com.xinghaol.programmer.list;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: lixinghao
 * @date: 2020/4/26 10:48 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
public class ListNode {
    private int val;

    private ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
