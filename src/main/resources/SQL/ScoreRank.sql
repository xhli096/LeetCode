/*
编写一个 SQL 查询来实现分数排名。如果两个分数相同，则两个分数排名（Rank）相同。请注意，平分后的下一个名次应该是下一个连续的整数值。
换句话说，名次之间不应该有“间隔”。

+----+-------+
| Id | Score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
例如，根据上述给定的 Scores 表，你的查询应该返回（按分数从高到低排列）：

+-------+------+
| Score | Rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+
*/
SELECT a.Score, count(distinct (b.Score)) AS Rank
from Scores a, Scores b
where a.Score <= b.Score
group by a.Id
order by a.Score desc;

/*
 select

a.score as Score,

count(DISTINCT b.score) AS Rank # 统计b表符合条件的不重复的分数的数量作为排名

FROM scores a join scores b

where b.score >= a.score # 条件是这个分数不小于我，因为a、b表数据相同，所以排名值最小是1

group by a.id # a表中每个数据都进行排名

order by a.score DESC # 最后按分数（跟排名一样）降序排列
 */