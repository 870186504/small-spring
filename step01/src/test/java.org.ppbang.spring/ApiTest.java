import bean.UserService;
import org.junit.Test;
import org.ppbang.spring.BeanDefinition;
import org.ppbang.spring.BeanFactory;

public class ApiTest {

    @Test
    public void test_SimpleBeanFactory(){
        // 1、实例化bean工厂，bean定义
        BeanFactory beanFactory = new BeanFactory();
        BeanDefinition beanDefinition =  new BeanDefinition(new UserService());

        // 2、注册bean定义
        beanFactory.registerBeanDefinitionByBeanName("userService", beanDefinition);

        // 3、bean容器中获取bean
        UserService userService = (UserService) beanFactory.getBean("userService").getBean();
        userService.say();
    }

}
