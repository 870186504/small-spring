import org.junit.Test;
import org.ppbang.spring.factory.BeanFactory;
import org.ppbang.spring.factory.config.BeanDefinition;
import org.ppbang.spring.factory.support.DefaultListableBeanFactory;
import org.ppbang.spring.test.bean.UserService;

public class ApiTest {
    @Test
    public void test_BeanFactory(){
        // 1、初始化bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2、注册bean定义
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3、工厂中获取bean，对bean中方法发起调用
        UserService userService = (UserService) beanFactory.getBean("userService");

        // 4、第一次获取拿到的bean
        userService.say();
        System.out.println(userService);

        // 5、第二次获取拿到的bean,验证是否来自单例池的对象
        UserService userServiceIsSingleton = (UserService) beanFactory.getBean("userService");
        System.out.println(userServiceIsSingleton);
        userServiceIsSingleton.say();
    }
}
