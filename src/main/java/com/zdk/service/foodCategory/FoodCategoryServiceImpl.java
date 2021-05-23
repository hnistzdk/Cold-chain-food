package com.zdk.service.foodCategory;

import com.zdk.mapper.FoodCategoryMapper;
import com.zdk.pojo.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/23 14:17
 */
@Service("FoodCategoryServiceImpl")
public class FoodCategoryServiceImpl implements FoodCategoryService {

    @Autowired
    FoodCategoryMapper foodCategoryMapper;

    @Override
    public List<FoodCategory> getFoodCategory(Integer id) {
        return foodCategoryMapper.getFoodCategory(id);
    }
}
