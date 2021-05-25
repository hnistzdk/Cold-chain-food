package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Food;
import com.zdk.service.food.FoodServiceImpl;
import com.zdk.utils.CommonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api("食品管理api")
@RestController
public class FoodController {

    @Autowired
    @Qualifier("FoodServiceImpl")
    private FoodServiceImpl foodService;

    @ApiOperation("添加食品信息")
    @RightInfo("addFood")
    @PostMapping("/addFood")
    @CrossOrigin
    public Object addFood(Food food){
        int count = foodService.addFood(food);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("删除食品信息")
    @ApiImplicitParam(name = "id",value = "食品id",type = "Integer")
    @RightInfo("deleteFood")
    @PostMapping("/deleteFood")
    @CrossOrigin
    public Object deleteFood(Integer id){
        int count = foodService.deleteFoodById(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("修改食品信息")
    @RightInfo("modifyFood")
    @PostMapping("/modifyFood")
    @CrossOrigin
    public Object modifyFood(Food food){
        int count = foodService.modifyFood(food);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("查询食品信息")
    @ApiImplicitParam(name = "map",value = "查询食品的参数封装在map中",type = "Map")
    @RightInfo("queryFoodBy")
    @PostMapping("/queryFoodBy")
    @CrossOrigin
    public Object queryFoodBy(Map map){
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
