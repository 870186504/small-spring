package org.ppbang.spring;

public class BeanDefinition {
    private Object object;

    public BeanDefinition(Object o){
        this.object = o;
    }

    public Object getBean(){
       return object;
    }
}
