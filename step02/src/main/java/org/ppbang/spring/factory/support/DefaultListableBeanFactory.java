package org.ppbang.spring.factory.support;

import org.ppbang.spring.BeanException;
import org.ppbang.spring.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutoWireCapableBeanFactory implements BeanDefinitionRegister {
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

//    易错点
//    public BeanDefinition getBean(String beanName) {
//        return beanDefinitionMap.get(beanName);
//    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        return beanDefinitionMap.get(beanName);
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

}
