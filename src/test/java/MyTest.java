import com.zdk.pojo.Food;
import com.zdk.service.UserServiceImpl;
import org.springframework.core.io.buffer.LimitedDataBufferList;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/12 18:36
 */
public class MyTest {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        //Food food = userService.queryFoodById(1);
        //System.out.println(food);
        List<Food> list=userService.queryFoodList();
        for (Food food : list) {
            System.out.println(food);
        }
    }
}
