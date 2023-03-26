package org.ppbang.spring.factory.config;

public class BeanDefinition {
    private Class BeanClass;

    public BeanDefinition(Class beanClass){
        this.BeanClass = beanClass;
    }


    public Class getBeanClass() {
        return BeanClass;
    }

    public void setBeanClass(Class beanClass) {
        BeanClass = beanClass;
    }
}
