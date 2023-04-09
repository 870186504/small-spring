package org.ppbang.spring.factory.support;

import org.ppbang.spring.BeanException;
import org.ppbang.spring.factory.BeanFactory;
import org.ppbang.spring.factory.config.BeanDefinition;

/**
 * 定义父模版类，骨架
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegister implements BeanFactory {
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }


    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    /**
     * 通用方法，模版获取bean
     * @param beanName
     * @param args
     * @return
     * @param <T>
     */
    public <T> T doGetBean(String beanName, Object[] args){
        // 1、查询单例池，存在则优先返回
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return (T) getSingleton(beanName);
        }

        // 2、获取bean定义信息，用来创建bean
        BeanDefinition beanDefinition = getBeanDefinition(beanName);

        // 3、交给子类实现createBean方法详细内容
        return (T) createBean(beanName, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    /**
     *  具体方法实现交给子类区实现
     * @param beanName
     * @param beanDefinition
     * @return
     * @throws BeanException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException;
}
