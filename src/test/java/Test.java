import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/5/25 2:51 下午
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> orderCountList = Lists.newArrayList(1,3,6,10);
        List<Integer> awardCountList = Lists.newArrayList(0,30,40,80);
        Integer[] result = new Integer[10 + 1];
        result[0] = 0;
        for (int i = 0; i < orderCountList.size(); i++) {
            result[orderCountList.get(i)] = awardCountList.get(i);
        }

        for (int i = 1; i < result.length; i++) {
            if (result[i] == null) {
                result[i] = result[i - 1];
            }
        }
        System.out.println(JSON.toJSONString(result));
    }
}
