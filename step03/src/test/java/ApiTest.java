import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;
import org.ppbang.spring.factory.BeanFactory;
import org.ppbang.spring.factory.config.BeanDefinition;
import org.ppbang.spring.factory.support.DefaultListableBeanFactory;
import org.ppbang.spring.test.bean.UserService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ApiTest {
    @Test
    public void test_BeanFactory(){
        // 1、初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2、注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("UserService", beanDefinition);

        // 3、获取bean
        UserService userService = (UserService) beanFactory.getBean("UserService", "彭彭");
        userService.queryUserName();
    }


    /**
     * 无参构造函数（存在有参构造函数，会报错）
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void test_newInstance() throws InstantiationException, IllegalAccessException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }


    /**
     * 验证有参构造函数实例化
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void test_constructor() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<UserService> userServiceClass = UserService.class;

        // 1、获取String参数类型的构造函数
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);

        // 2、参数值传入"彭彭帮"，实例化类
        UserService userService = declaredConstructor.newInstance("彭彭帮");
        System.out.println(userService);
    }

    /**
     * 获取所有构造函数信息（核心方法在于beanClass.getDeclaredConstructors()，获取一个类的所有构造函数）
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @Test
    public void test_paramTypes() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<UserService> beanClass = UserService.class;

        // 1、获取该类构造函数集合
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        // 2、获取首个构造函数
        Constructor<?> ctor = declaredConstructors[0];

        // 3、获取构造函数的构造器
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(ctor.getParameterTypes());

        // 4、进行实例化
        UserService userService = declaredConstructor.newInstance("elon");

        System.out.println(userService);
    }

    /**
     * 验证cglib实例化
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @Test
    public void test_cglib() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<UserService> beanClass = UserService.class;

        // 1、加强类，enhancer设置该类为父类，这样可以继承父类的方法
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);

        // 2、setCallback必须重写，否则报空指针
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        // 3、create方法实现实例化
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"马斯克"});
        System.out.println(obj);
    }

}
