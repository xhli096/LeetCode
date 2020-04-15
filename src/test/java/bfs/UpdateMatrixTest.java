package bfs;

import com.alibaba.fastjson.JSON;
import com.xinghaol.programmer.bfs.UpdateMatrix;
import org.junit.Test;

/**
 * @author: lixinghao
 * @date: 2020/4/15 5:10 下午
 * @Description:
 */
public class UpdateMatrixTest {
    @Test
    public void fun() {
        UpdateMatrix updateMatrix = new UpdateMatrix();
        int[][] martix = {
                {0,0,0},
                {0,1,0},
                {1,1,1}
        };
        System.out.println(JSON.toJSONString(updateMatrix.updateMatrix(martix)));
    }
}
