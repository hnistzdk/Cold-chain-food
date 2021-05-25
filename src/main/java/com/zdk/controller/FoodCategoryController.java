package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.EnterpriseMeta;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.FoodCategory;
import com.zdk.service.foodCategory.FoodCategoryServiceImpl;
import com.zdk.utils.CommonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author zdk
 * @date 2021/5/23 14:37
 */
@Api("食品分类api")
@RestController
public class FoodCategoryController {

    @Autowired
    @Qualifier("FoodCategoryServiceImpl")
    FoodCategoryServiceImpl foodCategoryService;

    @ApiOperation("获取食品类别信息")
    @ApiImplicitParam(name = "id",value = "可空参数,是否按id获取",paramType = "Integer")
    @RightInfo("getFoodCategory")
    @GetMapping("/getFoodCategory")
    @CrossOrigin
    public Object getFoodCategory(Integer id){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        msg.put("msg", "获取成功");
        msg.put("status", "200");
        List<FoodCategory> foodCategory;
        if(id==null){
            foodCategory = foodCategoryService.getFoodCategory(null);
        }else {
            foodCategory = foodCategoryService.getFoodCategory(id);
        }
        data.put("foodCategory", JSON.toJSON(foodCategory.toArray()));
        return JSON.toJSONString(new Meta(msg,data));
    }

    @ApiOperation("添加食品类别")
    @ApiImplicitParam(name = "foodCategory",paramType = "FoodCategory")
    @RightInfo("addFoodCategory")
    @PostMapping("/addFoodCategory")
    @CrossOrigin
    public Object addFoodCategory(FoodCategory foodCategory){
        int count = foodCategoryService.addFoodCategory(foodCategory);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("删除食品类别")
    @ApiImplicitParam(name = "id",paramType = "Integer")
    @RightInfo("deleteFoodCategory")
    @PostMapping("/deleteFoodCategory")
    @CrossOrigin
    public Object deleteFoodCategory(Integer id){
        int count = foodCategoryService.deleteFoodCategory(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("修改食品类别")
    @ApiImplicitParam(name = "foodCategory",paramType = "FoodCategory")
    @RightInfo("modifyFoodCategory")
    @PostMapping("/modifyFoodCategory")
    @CrossOrigin
    public Object modifyFoodCategory(FoodCategory foodCategory){
        int count = foodCategoryService.modifyFoodCategory(foodCategory);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }
}
