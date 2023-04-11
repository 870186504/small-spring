package org.ppbang.spring.factory.config;

import org.ppbang.spring.PropertyValue;
import org.ppbang.spring.PropertyValues;

public class BeanDefinition {
    private Class BeanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass){
        this.BeanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues){
        this.BeanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }


    public Class getBeanClass() {
        return BeanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setBeanClass(Class beanClass) {
        BeanClass = beanClass;
    }
}
