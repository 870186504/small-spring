package org.ppbang.spring.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.ppbang.spring.BeanException;
import org.ppbang.spring.PropertyValue;
import org.ppbang.spring.PropertyValues;
import org.ppbang.spring.factory.config.BeanDefinition;
import org.ppbang.spring.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 *  子模版骨架继承实现父模版中的方法
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory  {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            // 1、实例化bean
            bean = createBeanInstance(beanDefinition, beanName, args);

            // 2、为bean填充属性
            applyPropertyValues(beanName, beanDefinition, bean);

        } catch (Exception e){
            throw new BeanException("Instantiation of bean failed");
        }

        addSingleton(beanName, bean);
        return bean;
    }



    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor<?> constructorToUse = null;

        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors ){
            // 这里简单判断参数类型和入参个数是否相同  （存在构造函数中不同参数类型诞参数数量相同的问题，这类情况会有问题）
            // tips：spring源码还会根据判断参数类型和入参是否相同
            if (args != null && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
            }
            break;
        }

        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * Bean属性填充
     */
    protected void applyPropertyValues(String beanName, BeanDefinition beanDefinition, Object bean) {
        try {
            PropertyValues pvs =  beanDefinition.getPropertyValues();

            for (PropertyValue pv : pvs.getPropertyValues()) {
                String name = pv.getName();
                Object value = pv.getValue();

                if (value instanceof BeanReference) {
                    // A依赖B，获取B的实例化
                    BeanReference beanReference =  (BeanReference)value;
                    value = getBean(beanReference.getBeanName());
                }

                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeanException("Error setting property values" + beanName);
        }
    }
}
