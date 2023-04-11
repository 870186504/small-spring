package org.ppbang.spring.factory.support;

import org.ppbang.spring.BeanException;
import org.ppbang.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeanException;;
}
