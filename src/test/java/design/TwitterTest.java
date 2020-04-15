package design;

import com.xinghaol.programmer.design.Twitter;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/4/13 2:48 下午
 * @Description:
 */
public class TwitterTest {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        List<Integer> newsFeed = twitter.getNewsFeed(1);
        System.out.println(newsFeed);
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        List<Integer> feed = twitter.getNewsFeed(1);
        System.out.println(feed);
        twitter.unfollow(1, 2);
        List<Integer> f = twitter.getNewsFeed(1);
        System.out.println(f);
    }

    @Test
    public void fun() {
        Map<String, Object> map = new HashMap<>();
        map.put("jcode", "999");
        Object jcode = map.get("jcode");
        map.put("jcode", Integer.valueOf((String) jcode));
        System.out.println(map.get("jcode"));
    }
}
