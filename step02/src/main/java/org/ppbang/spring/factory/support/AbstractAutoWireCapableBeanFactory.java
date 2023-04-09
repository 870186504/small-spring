package org.ppbang.spring.factory.support;

import org.ppbang.spring.BeanException;
import org.ppbang.spring.factory.config.BeanDefinition;

/**
 *  子模版骨架继承实现父模版中的方法
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory  {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanException("createBean newInstance error", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

}
