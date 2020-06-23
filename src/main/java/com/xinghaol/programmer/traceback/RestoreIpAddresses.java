package com.xinghaol.programmer.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/6/22 10:54 下午
 * @Description: 93. 复原IP地址
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 */
public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int length = s.length();
        // 长度小于4或者大于12，一定不能拼出有效的ip地址
        if (length < 4 || length > 12) {
            return result;
        }
        // 2、每个节点有三种截取方式：1位、2位和3位，所以每一个结点可以生长出的分支最多只有 3 条分支；
        // 根据截取出来的字符串判断是否是合理的 ip 段，这里写法比较多，可以先截取，再转换成 int ，再判断。我采用的做法是先转成 int，是合法的 ip 段数值以后，再截取。

        // 3、由于 ip 段最多就 4 个段，因此这棵三叉树最多 4 层，这个条件作为递归终止条件之一；

        return result;
    }

}
