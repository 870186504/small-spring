package org.ppbang.spring.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.ppbang.spring.BeanException;
import org.ppbang.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * cdlib策略实现类
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeanException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode(){
                return super.hashCode();
            }
        });

        if (null == ctor) {
            return enhancer.create();
        }

        return enhancer.create(ctor.getParameterTypes(), args);
    }
}
