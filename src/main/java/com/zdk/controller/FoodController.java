package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Food;
import com.zdk.service.food.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 14:37
 */
@RestController
public class FoodController {

    @Autowired
    @Qualifier("FoodServiceImpl")
    private FoodServiceImpl foodService;

    @RightInfo("addFood")
    @PostMapping("/addFood")
    @CrossOrigin
    public Object addFood(Food food){
        HashMap msg = new HashMap<>();
        int count = foodService.addFood(food);
        if(count>0){
            msg.put("msg", "添加成功");
            msg.put("status", "200");
        }else {
            msg.put("msg", "添加失败");
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,null));
    }

    @RightInfo("deleteFood")
    @PostMapping("/deleteFood")
    @CrossOrigin
    public Object deleteFood(Integer id){
        HashMap msg = new HashMap<>();
        int count = foodService.deleteFoodById(id);
        if(count>0){
            msg.put("msg", "删除成功");
            msg.put("status", "200");
        }else {
            msg.put("msg", "删除失败");
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,null));
    }

    @RightInfo("modifyFood")
    @PostMapping("/modifyFood")
    @CrossOrigin
    public Object modifyFood(Food food){
        HashMap msg = new HashMap<>();
        int count = foodService.modifyFood(food);
        if(count>0){
            msg.put("msg", "修改成功");
            msg.put("status", "200");
        }else {
            msg.put("msg", "修改失败");
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,null));
    }

    @RightInfo("modifyFood")
    @PostMapping("/modifyFood")
    @CrossOrigin
    public Object modifyFood(Map map){
        HashMap msg = new HashMap<>();
        HashMap data = new HashMap<>();
        List<Food> foodList = foodService.queryFoodBy(map);
        int total = foodService.foodCount();
        if(foodList!=null){
            data.put("foodList",foodList);
            data.put("total",total);
            msg.put("msg", "获取成功");
            msg.put("status", "200");
        }else {
            msg.put("msg", "获取失败");
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,data));
    }
}
