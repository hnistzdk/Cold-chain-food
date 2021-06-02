package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Food;
import com.zdk.pojo.FoodCategory;
import com.zdk.service.food.FoodServiceImpl;
import com.zdk.service.foodCategory.FoodCategoryServiceImpl;
import com.zdk.utils.CommonMessage;
import com.zdk.utils.MyMessage;
import com.zdk.utils.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    @Autowired
    @Qualifier("FoodCategoryServiceImpl")
    FoodCategoryServiceImpl foodCategoryService;

    @ApiOperation("添加食品信息")
    @RightInfo(Permission.ADDFOOD)
    @PostMapping("/addFood")
    @CrossOrigin
    public Object addFood(Food food){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id", food.getCategoryId());
        List<FoodCategory> foodCategory = foodCategoryService.getFoodCategory(map);
        if(foodCategory!=null){
            food.setFoodCategory(foodCategory.get(0).getCategoryName());
            food.setCategoryId(foodCategory.get(0).getId());
        }
        int count = foodService.addFood(food);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("删除食品信息")
    @RightInfo(Permission.DELETEFOOD)
    @PostMapping("/deleteFood/{id}")
    @CrossOrigin
    public Object deleteFood(@PathVariable Integer id){
        int count = foodService.deleteFoodById(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("修改食品信息")
    @RightInfo(Permission.MODIFYFOOD)
    @PostMapping("/modifyFood")
    @CrossOrigin
    public Object modifyFood(Food food){
        int count = foodService.modifyFood(food);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("查询食品信息")
    @RightInfo(Permission.GETFOOD)
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
            List<FoodCategory> categoryList = foodCategoryService.getFoodCategory(null);
            System.out.println("categoryList:"+categoryList);
            data.put("foodList",foodList.toArray());
            data.put("categoryList",categoryList.toArray());
            data.put("total",total);
            msg.put(MyMessage.STATUS, MyMessage.SUCCESS);
        }else {
            msg.put(MyMessage.STATUS, MyMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @ApiOperation("获取编辑食品的信息")
    @RightInfo(Permission.GETFOODBYID)
    @GetMapping("/foods/{id}")
    @CrossOrigin
    public Object getFoodById(@PathVariable Integer id){
        Food food = foodService.getFoodById(id);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data", food);
        map.put(MyMessage.STATUS, MyMessage.SUCCESS);
        return JSON.toJSONString(map);
    }

}
