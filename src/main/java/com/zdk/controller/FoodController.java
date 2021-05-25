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
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RightInfo("deleteFood")
    @PostMapping("/deleteFood/{id}")
    @CrossOrigin
    public Object deleteFood(@PathVariable Integer id){
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
    @RightInfo("getFood")
    @PostMapping("/getFood")
    @CrossOrigin
    public Object getFood(@Param("query") String query, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize){
        System.out.println("query"+query);
        HashMap msg = new HashMap<>();
        HashMap data = new HashMap<>();
        HashMap params=new HashMap<>();
        params.put("pageNum",(pageNum-1)*pageSize);
        params.put("pageSize",pageSize);
        List<Food> foodList;
        if(query!=null){
            params.put("query",query);
            foodList = foodService.fuzzyQueryFood(params);
        }else{
            params.put("query",null);
            foodList = foodService.getFood(params);
        }
        int total = foodService.foodCount(query);
        if(foodList!=null){
            data.put("foodList",foodList.toArray());
            data.put("total",total);
            msg.put("status", "200");
        }else {
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,data));
    }
}
