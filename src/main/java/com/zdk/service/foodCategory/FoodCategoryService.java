package com.zdk.service.foodCategory;

import com.zdk.pojo.FoodCategory;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 14:17
 */
public interface FoodCategoryService {
    /**
     * 获取食品分类
     * @param map
     * @return list
     */
    List<FoodCategory> getFoodCategory(Map map);

    /**
     * 增加食品分类
     * @param foodCategory
     * @return int
     */
    int addFoodCategory(FoodCategory foodCategory);
    /**
     * 删除食品分类
     * @param id
     * @return int
     */
    int deleteFoodCategory(Integer id);
    /**
     * 修改食品分类
     * @param foodCategory
     * @return int
     */
    int modifyFoodCategory(FoodCategory foodCategory);

    /**
     * 获取食品分类总数
     * @return int
     */
    int getAllFoodCategory();
}
