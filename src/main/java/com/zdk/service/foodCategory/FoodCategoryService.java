package com.zdk.service.foodCategory;

import com.zdk.pojo.FoodCategory;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/23 14:17
 */
public interface FoodCategoryService {
    /**
     * 获取食品分类
     * @param id
     * @return list
     */
    List<FoodCategory> getFoodCategory(Integer id);
}
