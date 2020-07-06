package com.xinghaol.programmer.array;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/7/3 5:02 下午
 * @Description: 1030. 距离顺序排列矩阵单元格
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 * <p>
 * 示例 1：
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * 示例 2：
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * 示例 3：
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 * <p>
 * 提示：
 * <p>
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 */
public class AllCellsDistOrder {
    /**
     * 把每个点记录下来，然后根据曼哈顿距离排序，最后输出
     *
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] result = new int[R * C][2];
        int index = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                result[index++] = new int[]{i, j};
            }
        }
        Arrays.sort(result, (a1, a2) -> {
            int d1 = dist(a1[0], a1[1], r0, c0);
            int d2 = dist(a2[0], a2[1], r0, c0);
            return Integer.compare(d1, d2);
        });

        return result;
    }

    private int dist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }


    public static void main(String[] args) {
        AllCellsDistOrder allCellsDistOrder = new AllCellsDistOrder();
        int[][] result = allCellsDistOrder.allCellsDistOrder(2, 2, 0, 0);
        System.out.println(JSON.toJSONString(result));
    }

    /**
     * BFS
     * 1、可以把所有的坐标看作树的结点，距离相等的结点位于树的同一层
     * 2、而对于每一层的结点，它们的距离 dist 可以分为行距离和列距离，且 rowDist + colDist = dist 必然成立
     * 3、使 rowDist 从 0 到 dist 递增，colDist 相应有不同的值，可以得到不同的坐标：
     * 3.1、横坐标为：r0 - rowDist 或 r0 + rowDist
     * 3.2、纵坐标为：c0 - colDist 或 c0 + colDist
     * 注意特殊情况：rowDist 或 colDist 为 0 时，每组只有一个正确值
     * 4、对步骤 3 中，所有在矩阵范围内的坐标进行记录
     * <p>
     * 注意：
     * 此解法不关心最大距离，只要步骤 4 中记录的结果达到 R * C 的数量就可以终止搜索
     * 此解法的时间复杂度是 O((R+C)^2)，因为对每一种距离 dist，rowDist 都要进行从 0 开始递增到 dist 的遍历操作，而距离可能的最大值为 R + C
     * 此解法时间复杂度大于 O(R * C) 的原因是：每种距离可能产生多个不在矩阵内的坐标，但搜索算法必须依次检查予以排除
     * 理论上此解法并不比桶排序优秀，但是代码中极少创建额外的容器和对象，所以实际的运行效率不会太差
     *
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    private int[][] allCellsDistOrderBFS(int R, int C, int r0, int c0) {
        int[][] result = new int[R * C][2];
        int dist = 0;
        int count = 0;
        int[] factor = {-1, 1};
        // 点的个数还没有到那么多
        while (count < R * C) {
            for (int rowDist = 0; rowDist <= dist; rowDist++) {
                int colDist = dist - rowDist;
                for (int i = 0; i < 2; i++) {
                    int row = r0 + factor[i] * rowDist;
                    for (int j = 0; j < 2; j++) {
                        int col = c0 + factor[j] * colDist;
                        if (row >= 0 && row < R && col >= 0 && col < C) {
                            result[count][0] = row;
                            result[count][1] = col;
                            count++;
                        }
                        if (colDist == 0) {
                            break;
                        }
                    }
                    if (rowDist == 0) {
                        break;
                    }
                }
                dist++;
            }
        }

        return result;
    }
}
