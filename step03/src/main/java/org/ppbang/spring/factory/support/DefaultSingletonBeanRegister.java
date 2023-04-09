package org.ppbang.spring.factory.support;
import org.ppbang.spring.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegister implements SingletonBeanRegistry {
    private final Map<String, Object> singletonObjetctsMap = new HashMap<>();

    public Object getSingleton(String beanName) {
        return singletonObjetctsMap.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject){
        singletonObjetctsMap.put(beanName, singletonObject);
    }
}
