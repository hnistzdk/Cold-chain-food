package com.zdk.mapper;

import com.zdk.pojo.FoodCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 13:48
 */
@Mapper
@Repository
public interface FoodCategoryMapper {
    /**
     * 获取食品分类
     * @param map
     * @return list
     */
    List<FoodCategory> getFoodCategory(Map map);

    /**
     * 增加食品分类
     * @param foodCategory
     * @return list
     */
    int addFoodCategory(FoodCategory foodCategory);
    /**
     * 删除食品分类
     * @param id
     * @return list
     */
    int deleteFoodCategory(Integer id);
    /**
     * 修改食品分类
     * @param foodCategory
     * @return list
     */
    int modifyFoodCategory(FoodCategory foodCategory);

    /**
     * 获取食品分类总数
     * @return int
     */
    int getAllFoodCategory();
}
