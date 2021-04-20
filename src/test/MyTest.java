import com.zdk.dto.AdminMeta;
import com.zdk.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author zdk
 * @date 2021/4/19 19:27
 */
public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userServiceImpl = context.getBean("UserServiceImpl", UserServiceImpl.class);
        List<AdminMeta> adminList = userServiceImpl.getAdminList(0, 2);
        for (AdminMeta userListMeta : adminList) {
            System.out.println(userListMeta);
        }
    }
}
