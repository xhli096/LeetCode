package com.xinghaol.guidedubbo.framework.simple.provider;

import com.xinghaol.guidedubbo.framework.common.enumeration.RpcErrorMessage;
import com.xinghaol.guidedubbo.framework.common.exception.RpcException;
import com.xinghaol.guidedubbo.framework.model.RpcServiceProperties;
import com.xinghaol.guidedubbo.framework.simple.registry.ServiceRegistry;
import com.xinghaol.guidedubbo.framework.simple.registry.impl.zk.ZkServiceRegistry;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: lixinghao
 * @date: 2020/7/28 1:16 下午
 * @Description: 服务提供者实现类
 */
@Slf4j
public class ServiceProviderImpl implements ServiceProvider {
    /**
     * 存放service服务
     * key：rpc service name => interface name + version + group
     * value : service object
     */
    private final Map<String, Object> serviceMap;
    /**
     * 服务提供者的key值
     */
    private final Set<String> registeredService;
    /**
     * 服务注册中心，使用zk实现
     */
    private final ServiceRegistry serviceRegistry;

    public ServiceProviderImpl() {
        this.serviceMap = new ConcurrentHashMap<>();
        this.registeredService = ConcurrentHashMap.newKeySet();
        this.serviceRegistry = new ZkServiceRegistry();
    }

    /**
     * 增加一个服务提供者
     *
     * @param service              service object
     * @param serviceClass         the interface class implemented by the service instance object
     * @param rpcServiceProperties service  related attributes
     */
    @Override
    public void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties) {
        String serviceName = rpcServiceProperties.toRpcServiceName();
        if (registeredService.contains(serviceName)) {
            return;
        }
        registeredService.add(serviceName);
        serviceMap.put(serviceName, service);
        log.info("add service : {} and interfaces : {}", service, service.getClass().getInterfaces());
    }

    /**
     * 获得一个提供者
     *
     * @param rpcServiceProperties service  related attributes
     * @return service object
     */
    @Override
    public Object getService(RpcServiceProperties rpcServiceProperties) {
        Object object = serviceMap.get(rpcServiceProperties.toRpcServiceName());
        if (null == object) {
            throw new RpcException(RpcErrorMessage.SERVICE_CAN_NOT_BE_FOUND);
        }
        return object;
    }

    /**
     * publishService
     *
     * @param service              service object
     * @param rpcServiceProperties service  related attributes
     */
    @Override
    public void publishService(Object service, RpcServiceProperties rpcServiceProperties) {
        try {
            // 获取当前机器的ip地址
            String host = InetAddress.getLocalHost().getHostAddress();
            // 获取实现的接口的第一个interface
            Class<?> serviceRelatedInterface = service.getClass().getInterfaces()[0];
            String serviceName = serviceRelatedInterface.getCanonicalName();
            rpcServiceProperties.setServiceName(serviceName);
            addService(service, serviceRelatedInterface, rpcServiceProperties);
            // TODO 修改端口号
            serviceRegistry.registryService(rpcServiceProperties.toRpcServiceName(), new InetSocketAddress(host, 8080));
        } catch (UnknownHostException e) {
            log.error("occur exception when getHostAddress", e);
        }
    }

    /**
     * publishService
     *
     * @param service service object
     */
    @Override
    public void publishService(Object service) {
        publishService(service, RpcServiceProperties.builder().group("").version("").build());
    }
}
