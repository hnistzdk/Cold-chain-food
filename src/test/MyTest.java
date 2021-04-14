import com.zdk.pojo.Food;
import com.zdk.service.UserService;
import com.zdk.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/13 21:55
 */
public class MyTest {
    @Test
    public void test(){
        ApplicationContext Context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserServiceImpl) Context.getBean("UserServiceImpl");
        List<Food> list=userService.queryFoodList();
        for (Food food : list) {
            System.out.println(food);
        }
    }
}
