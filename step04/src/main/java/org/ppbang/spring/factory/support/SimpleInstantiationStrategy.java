package org.ppbang.spring.factory.support;

import org.ppbang.spring.BeanException;
import org.ppbang.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * JDK实例化策略类
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeanException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null != constructor) {
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.newInstance();
            }
        }catch(InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new BeanException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
