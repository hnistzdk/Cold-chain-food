package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.FoodCategory;
import com.zdk.service.foodCategory.FoodCategoryServiceImpl;
import com.zdk.utils.CommonMessage;
import com.zdk.utils.ReturnMessage;
import com.zdk.utils.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 14:37
 */
@Api("食品分类api")
@CrossOrigin
@RestController
public class FoodCategoryController {

    @Autowired
    @Qualifier("FoodCategoryServiceImpl")
    FoodCategoryServiceImpl foodCategoryService;

    @ApiOperation("获取食品类别信息")
    @RightInfo(Permission.GETFOODCATEGORY)
    @GetMapping("/getFoodCategory")
    public Object getFoodCategory(Integer id, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        HashMap params=new HashMap<>();
        params.put("id",id);
        params.put("pageNum",(pageNum-1)*pageSize);
        params.put("pageSize",pageSize);
        List<FoodCategory> foodCategory = foodCategoryService.getFoodCategory(params);
        if(foodCategory!=null){
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            int total = foodCategoryService.getAllFoodCategory();
            data.put("foodCategory", foodCategory.toArray());
            data.put("total", total);
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @ApiOperation("添加食品类别")
    @RightInfo(Permission.ADDFOODCATEGORY)
    @PostMapping("/addFoodCategory")
    public Object addFoodCategory(FoodCategory foodCategory){
        int count = foodCategoryService.addFoodCategory(foodCategory);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("删除食品类别")
    @RightInfo(Permission.DELETEFOODCATEGORY)
    @PostMapping("/deleteFoodCategory/{id}")
    public Object deleteFoodCategory(@PathVariable Integer id){
        int count = foodCategoryService.deleteFoodCategory(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("修改食品类别")
    @RightInfo(Permission.MODIFYFOODCATEGORY)
    @PostMapping("/modifyFoodCategory")
    public Object modifyFoodCategory(FoodCategory foodCategory){
        int count = foodCategoryService.modifyFoodCategory(foodCategory);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("编辑查看食品类别")
    @RightInfo(Permission.CATEGORIES)
    @GetMapping("/categories/{id}")
    public Object showFoodCategory(@PathVariable Integer id){
        Map map=new HashMap();
        map.put("id", id);
        List<FoodCategory> result = foodCategoryService.getFoodCategory(map);
        if(result!=null){
            map.put("data", result.get(0));
            map.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        }
        return JSON.toJSONString(map);
    }
}
