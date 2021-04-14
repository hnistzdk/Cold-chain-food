package com.zdk.controller;


import com.zdk.pojo.Food;
import com.zdk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @RequestMapping("/test")
    public String test(){
        return "index.jsp";
    }
    @RequestMapping("/toFood")
    public String toFood(){
        return "queryFood.jsp";
    }
    @RequestMapping("/queryFood")
    public String queryFoodList(Model model){
        List<Food> foods = userService.queryFoodList();
        System.out.println(foods);
        model.addAttribute("foods",foods);
        return "FoodList.jsp";
    }

    @RequestMapping("/queryFoodById")
    public String queryFoodById(int id,Model model){
        Food food = userService.queryFoodById(id);
        System.out.println(food);
        if (food!=null){
            model.addAttribute("food",food);
            return "FoodList.jsp";
        }else {
            model.addAttribute("isnull","莫有查到数据(>~<)");
            return "queryFood.jsp";
        }
    }
}
