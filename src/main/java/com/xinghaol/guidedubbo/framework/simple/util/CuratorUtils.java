package com.xinghaol.guidedubbo.framework.simple.util;

import com.xinghaol.guidedubbo.framework.common.enumeration.RpcProperties;
import com.xinghaol.guidedubbo.framework.common.util.PropertiesFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.dubbo.rpc.RpcException;
import org.apache.zookeeper.CreateMode;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: lixinghao
 * @date: 2020/7/24 10:30 下午
 * @Description: zookeeper的客户端工具
 */
@Slf4j
public class CuratorUtils {
    /**
     * 默认的睡眠时间，1s
     */
    private static final int BASE_SLEEP_TIME = 1000;
    /**
     * 最大的重试次数
     */
    private static final int MAX_RETRIES = 3;
    /**
     * zookeeper的注册根路径
     */
    public static final String ZK_REGISTER_ROOT_PATH = "/my-rpc";
    /**
     * 服务的地址列表
     */
    private static final Map<String, List<String>> SERVICE_ADDRESS_MAP = new ConcurrentHashMap<>();
    /**
     * 注册路径集合，获取一个线程安全的set
     */
    private static final Set<String> REGISTER_PATH_SET = ConcurrentHashMap.newKeySet();
    /**
     * 默认的zookeeper地址
     */
    private static String defaultZookeeperAddress = "127.0.0.1:2181";
    /**
     * zookeeper客户端
     */
    private static CuratorFramework zkClient;

    public CuratorUtils() {
    }

    /**
     * 获取一个zookeeper客户端对象
     *
     * @return zookeeper客户端对象
     */
    public static CuratorFramework getZkClient() {
        Properties properties = PropertiesFileUtil.readProperties(RpcProperties.RPC_CONFIG_PATH.getPropertyValue());
        // properties不为空，则使用默认的地址
        if (properties != null) {
            defaultZookeeperAddress = properties.getProperty(RpcProperties.ZK_ADDRESS.getPropertyValue());
        }
        // 如果zookeeper客户端已经准备好，则直接返回即可
        if (zkClient != null && zkClient.getState() == CuratorFrameworkState.STARTED) {
            return zkClient;
        }
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES);
        zkClient = CuratorFrameworkFactory.builder()
                .connectString(defaultZookeeperAddress)
                .authorization("digest", "user1:123456".getBytes())
                .retryPolicy(retryPolicy)
                .build();
        zkClient.start();
        return zkClient;
    }

    /**
     * 获取某个node下的子节点
     *
     * @param zkClient
     * @param serviceName
     * @return
     */
    public static List<String> getChildrenNodes(CuratorFramework zkClient, String serviceName) {
        // 如果已经注册过，并且已经缓存过，则直接取出来返回
        if (SERVICE_ADDRESS_MAP.containsKey(serviceName)) {
            return SERVICE_ADDRESS_MAP.get(serviceName);
        }
        List<String> result;

        String servicePath = ZK_REGISTER_ROOT_PATH + "/" + serviceName;
        try {
            result = zkClient.getChildren().forPath(servicePath);
            // 找到结果，添加进缓存中
            SERVICE_ADDRESS_MAP.put(serviceName, result);
            // 添加监控
            registerWatcher(zkClient, serviceName);
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e.getCause());
        }

        return result;
    }

    /**
     * cache是一种缓存机制，可以借助cache实现监听。
     * 简单来说，cache在客户端缓存了znode的各种状态，当感知到zk集群的znode状态变化，会触发event事件，
     * 注册的监听器会处理这些事件。是不是很简单。
     * curator支持的cache种类有3种Path Cache，Node Cache，Tree Cache
     * <p>
     * 注册监听，为了监听某一个节点的改动操作
     *
     * @param zkClient
     * @param serviceName
     */
    private static void registerWatcher(CuratorFramework zkClient, String serviceName) {
        String servicePath = ZK_REGISTER_ROOT_PATH + "/" + serviceName;
        PathChildrenCache pathChildrenCache = new PathChildrenCache(zkClient, servicePath, true);
        PathChildrenCacheListener pathChildrenCacheListener = ((client, event) -> {
            List<String> serviceAddress = client.getChildren().forPath(servicePath);
            SERVICE_ADDRESS_MAP.put(serviceName, serviceAddress);
        });
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        try {
            pathChildrenCache.start();
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 清空所有的注册的service
     *
     * @param curatorFramework
     */
    public static void clearRegistry(CuratorFramework curatorFramework) {
        // parallel 并行的处理
        REGISTER_PATH_SET.stream().parallel().forEach(p -> {
            try {
                zkClient.delete().forPath(p);
            } catch (Exception e) {
                throw new RpcException(e.getMessage(), e.getCause());
            }
        });
        log.info("All registered services on the server are cleared:[{}]", REGISTER_PATH_SET.toString());
    }

    /**
     * 创建一个持久节点，与虚拟节点不同，当客户端的关闭时，不需要移除持久节点
     *
     * @param zkClient
     * @param path
     */
    public static void createPersistentNode(CuratorFramework zkClient, String path) {
        try {
            if (REGISTER_PATH_SET.contains(path) || zkClient.checkExists().forPath(path) != null) {
                log.info("The node already exists. The node is:[{}]", path);
            } else {
                zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
                log.info("created node successfully. The node is : [{}]", path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
