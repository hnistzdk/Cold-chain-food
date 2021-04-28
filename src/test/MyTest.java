import com.zdk.service.user.UserServiceImpl;
import com.zdk.utils.SendEmail;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zdk
 * @date 2021/4/19 19:27
 */
public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userServiceImpl = context.getBean("UserServiceImpl", UserServiceImpl.class);
    }
    @Test
    public void testEmail(){
        SendEmail.sendEmail("369365576@qq.com");
    }
}
