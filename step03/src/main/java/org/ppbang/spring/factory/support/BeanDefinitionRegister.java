package org.ppbang.spring.factory.support;

import org.ppbang.spring.factory.config.BeanDefinition;

public interface BeanDefinitionRegister {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
