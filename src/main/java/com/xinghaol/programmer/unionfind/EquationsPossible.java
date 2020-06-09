package com.xinghaol.programmer.unionfind;

/**
 * @author: lixinghao
 * @date: 2020/6/8 11:15 下午
 * @Description: 990. 等式方程的可满足性
 * https://leetcode-cn.com/problems/satisfiability-of-equality-equations/
 * weiwei题解：https://leetcode-cn.com/problems/satisfiability-of-equality-equations/solution/shi-yong-bing-cha-ji-chu-li-bu-xiang-jiao-ji-he-we/
 */
public class EquationsPossible {
    public boolean equationsPossible(String[] equations) {
        // 字母均是小写
        UnionFind unionFind = new UnionFind(26);

        // 遍历等式，将等式两边的字符进行连通
        for (String equation : equations) {
            char[] array = equation.toCharArray();
            if (array[1] == '=') {
                int index1 = array[0] - 'a';
                int index2 = array[3] - 'a';
                unionFind.union(index1, index2);
            }
        }

        // 遍历不等式，如果不等式两边属于同一个并查集，则返回false
        for (String equation : equations) {
            char[] array = equation.toCharArray();
            if (array[1] == '!') {
                int index1 = array[0] - 'a';
                int index2 = array[3] - 'a';

                // 不等式两边的元素如果是连通的，说明是不可以的。直接返回false
                if (unionFind.isConnected(index1, index2)) {
                    return false;
                }
            }
        }

        // 如果检查了所有不等式，都没有发现矛盾，返回 true
        return true;
    }

    class UnionFind {
        /**
         * 存储指向
         */
        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * 隔代压缩
         *
         * @param p
         * @return
         */
        public int find(int p) {
            // 条件不成立，则说明还没有找到根节点
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }

            return p;
        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        /**
         * 如果合并成功，返回 true
         *
         * @param p
         * @param q
         */
        public void union(int p, int q) {
            // 找到p和q的根节点
            int rootP = find(p);
            int rootQ = find(q);

            parent[rootP] = rootQ;
        }
    }

    public static void main(String[] args) {
        // String[] equations = new String[]{"b==a", "a==b"};
        // String[] equations = new String[]{"a==b","b==c","a==c"};
        // String[] equations = new String[]{"a==b","b!=c","c==a"};
        String[] equations = new String[]{"c==c", "b==d", "x!=z"};

        EquationsPossible solution = new EquationsPossible();
        boolean res = solution.equationsPossible(equations);
        System.out.println(res);
    }
}
