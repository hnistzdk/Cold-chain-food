package com.zdk.mapper;

import com.zdk.pojo.FoodCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/23 13:48
 */
@Mapper
@Repository
public interface FoodCategoryMapper {
    /**
     * 获取食品分类
     * @param id
     * @return list
     */
    List<FoodCategory> getFoodCategory(Integer id);

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
}
