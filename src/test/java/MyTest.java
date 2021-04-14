import com.zdk.pojo.Food;
import com.zdk.service.UserService;
import com.zdk.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/12 18:36
 */
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserServiceImpl) context.getBean("UserServiceImpl");
        List<Food> list=userService.queryFoodList();
        for (Food food : list) {
            System.out.println(food);
        }
    }
}
