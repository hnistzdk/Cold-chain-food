package com.zdk;

import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.EnterpriseUser;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
class FoodSpringbootApplicationTests {

    @Test
    void contextLoads() throws IOException {

        File jsonFile = ResourceUtils.getFile("classpath:static/json/adminMenu.json");
        String menu = FileUtils.readFileToString(jsonFile);
        System.out.println(menu);
    }

}
