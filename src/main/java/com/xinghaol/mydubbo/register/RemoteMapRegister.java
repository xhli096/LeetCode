package com.xinghaol.mydubbo.register;

import com.xinghaol.mydubbo.framework.URL;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author: lixinghao
 * @date: 2020/7/7 11:44 上午
 * @Description: 远程导出
 */
public class RemoteMapRegister {
    /**
     * 进程间隔离的，会导致provider和consumer的cache不相同，导致不能取到其中的值
     * 在demo中是在同一台机器上的，可以使用持久化到文件中；
     */
    private static Map<String, List<URL>> cache = new HashMap<>();

    public static void register(String interfaceName, URL url) {
        List<URL> urls = cache.get(interfaceName);
        if (CollectionUtils.isEmpty(urls)) {
            cache.put(interfaceName, Collections.singletonList(url));
        } else {
            urls.add(url);
        }
    }

    public static URL random(String interfaceName) {
        List<URL> urls = cache.get(interfaceName);
        if (CollectionUtils.isEmpty(urls)) {
            throw new IllegalArgumentException("参数错误，该服务并未注册");
        }
        Random random = new Random();
        int n = random.nextInt(urls.size());

        return urls.get(n);
    }
}
