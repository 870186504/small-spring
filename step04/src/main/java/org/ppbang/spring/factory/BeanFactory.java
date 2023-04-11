package org.ppbang.spring.factory;

public interface BeanFactory {
    public Object getBean(String beanName);

    public Object getBean(String beanName, Object... args);
}
