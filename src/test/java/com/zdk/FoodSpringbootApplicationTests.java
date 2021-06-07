package com.zdk;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@SpringBootTest
class FoodSpringbootApplicationTests {

    @Test
    void contextLoads() throws IOException {

//        File jsonFile = ResourceUtils.getFile("classpath:static/json/adminMenu.json");
//        String menu = FileUtils.readFileToString(jsonFile);
//        System.out.println(menu);
        ClassPathResource classPathResource=new ClassPathResource("/json/primaryMenu.json");
        String menu = FileUtils.readFileToString(classPathResource.getFile());
        System.out.println(menu);
    }

}
