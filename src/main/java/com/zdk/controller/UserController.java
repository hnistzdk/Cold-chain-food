package com.zdk.controller;

import com.zdk.pojo.Food;
import com.zdk.pojo.User;
import com.zdk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/12 18:36
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String pwd){
        System.out.println(username+pwd);
        User login = userService.login(username, pwd);
        System.out.println(login);
        if(login!=null){
            return "test";
        }else{
            return "error";
        }
    }
    @PostMapping("/queryFood")
    public String queryFood(@RequestParam("id") int id){
        System.out.println(id);
        Food food = userService.queryFoodById(id);
        if(food!=null){
            return "test";
        }else{
            return "error";
        }
    }
}
