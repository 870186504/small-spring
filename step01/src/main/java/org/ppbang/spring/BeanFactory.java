package org.ppbang.spring;


import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    public Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public BeanDefinition getBean(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    public void registerBeanDefinitionByBeanName(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
