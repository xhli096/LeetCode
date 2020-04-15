package com.xinghaol.programmer.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/4/13 2:09 下午
 * @Description: 设计推特
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 * <p>
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 * <p>
 * 示例:
 * Twitter twitter = new Twitter();
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);
 */
public class Twitter {
    /**
     * 某一个user的关注者记录
     */
    private Map<Integer, Set<Integer>> userFollowerMap;

    /**
     * 存储某一个user的Top 10的tweet
     */
    private PriorityQueue<Tweet> topTenTweet;

    /**
     * user 与发的tweet的关系存储
     */
    private Map<Integer, Tweet> userTweet;

    /**
     * 全局的时间戳，记录全局的tweet的记录，用于多路归并使用
     */
    private static int timeStamp = 0;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        userFollowerMap = new HashMap<>();
        userTweet = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        // 全局时间戳增加；用于统计是第几条tweet，用于多路归并使用
        timeStamp++;
        if (userTweet.containsKey(userId)) {
            Tweet oldHead = userTweet.get(userId);
            Tweet newHead = new Tweet(tweetId, timeStamp);
            newHead.next = oldHead;
            userTweet.put(userId, newHead);
        } else {
            Tweet tweet = new Tweet(tweetId, timeStamp);
            userTweet.put(userId, tweet);
        }
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        topTenTweet = new PriorityQueue<>((o1, o2) -> -o1.timestamp + o2.timestamp);

        // 加入自己发送的推文
        if (userTweet.containsKey(userId)) {
            topTenTweet.offer(userTweet.get(userId));
        }
        // 加入自己的
        Set<Integer> followerIds = userFollowerMap.get(userId);
        if (followerIds != null && followerIds.size() > 0) {
            for (Integer followerId : followerIds) {
                if (userTweet.containsKey(followerId)) {
                    topTenTweet.offer(userTweet.get(followerId));
                }
            }
        }
        List<Integer> result = new ArrayList<>(10);
        int count = 0;

        // 实现一个多路归并的操作
        // 由于 topTenTweet在实例化时，使用了-o1.timestamp + o2.timestamp；谁小，谁放在后面，所以前面的timestamp，要比后面的大。
        // 所以前面的较新，每次加入新的元素时，均会比较，维护一个最小堆。
        while (!topTenTweet.isEmpty() && count < 10) {
            Tweet tweet = topTenTweet.poll();
            result.add(tweet.id);

            if (tweet.next != null) {
                topTenTweet.offer(tweet.next);
            }
            count++;
        }

        return result;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        // 被关注者不能是自己
        if (followeeId == followerId) {
            return;
        }
        Set<Integer> followerIds = userFollowerMap.get(followerId);
        if (followerIds == null) {
            followerIds = new HashSet<>();
            followerIds.add(followeeId);
            userFollowerMap.put(followerId, followerIds);
        } else {
            if (followerIds.contains(followeeId)) {
                return;
            }
            followerIds.add(followeeId);
        }
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId) {
            return;
        }
        Set<Integer> followerIds = userFollowerMap.get(followerId);
        if (followerIds == null || followerIds.isEmpty()) {
            return;
        }
        if (followerIds.contains(followeeId)) {
            followerIds.remove(followeeId);
        }
    }

    private class Tweet {
        private int id;
        /**
         * 用于记录下一个Tweet
         */
        private Tweet next;
        /**
         * 用于记录发推文的时间戳
         */
        private int timestamp;

        public Tweet(int id, int timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }
}
