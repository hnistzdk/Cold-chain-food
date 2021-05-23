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
}
