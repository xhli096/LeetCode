package com.xinghaol.guidedubbo.framework.spring;

import com.xinghaol.guidedubbo.framework.common.factory.SingletonFactory;
import com.xinghaol.guidedubbo.framework.model.RpcServiceProperties;
import com.xinghaol.guidedubbo.framework.simple.annotation.RpcService;
import com.xinghaol.guidedubbo.framework.simple.provider.ServiceProvider;
import com.xinghaol.guidedubbo.framework.simple.provider.ServiceProviderImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: lixinghao
 * @date: 2020/7/23 12:58 下午
 * @Description: 在执行完各种*Aware后，执行相关的BeanPostProcessor的before方法。可以复习spring的bean的声明周期
 */
@Component
@Slf4j
public class SpringBeanPostProcessor implements BeanPostProcessor {
    private final ServiceProvider serviceProvider;

    public SpringBeanPostProcessor(ServiceProvider serviceProvider) {
        this.serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);
    }

    /**
     * Apply this {@code BeanPostProcessor} to the given new bean instance <i>before</i> any bean
     * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
     * or a custom init-method). The bean will already be populated with property values.
     * The returned bean instance may be a wrapper around the original.
     * <p>The default implementation returns the given {@code bean} as-is.
     *
     * @param bean     the new bean instance
     * @param beanName the name of the bean
     * @return the bean instance to use, either the original or a wrapped one;
     * if {@code null}, no subsequent BeanPostProcessors will be invoked
     * @throws BeansException in case of errors
     * @see InitializingBean#afterPropertiesSet
     */
    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 如果有RpcService注解，则进行发布。在发布过程中，会在zookeeper中以当前host，端口创建一个持久节点
        // 在以service名称获取的时候，从zookeeper中按负载均衡算法取得一个。
        // 每一个服务器在启动的时候都会以自己的ip去zookeeper中创建一个节点
        if (bean.getClass().isAnnotationPresent(RpcService.class)) {
            log.info("[{}] is annotated with [{}]", bean.getClass().getAnnotatedInterfaces(), RpcService.class.getCanonicalName());
            RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
            RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder().group(rpcService.group()).version(rpcService.group()).build();
            serviceProvider.publishService(bean, rpcServiceProperties);
        }
        return bean;
    }
}
