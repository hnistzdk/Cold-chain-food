package com.zdk.service.foodCategory;

import com.zdk.mapper.FoodCategoryMapper;
import com.zdk.pojo.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 14:17
 */
@Service("FoodCategoryServiceImpl")
public class FoodCategoryServiceImpl implements FoodCategoryService {

    @Autowired
    FoodCategoryMapper foodCategoryMapper;

    @Override
    public List<FoodCategory> getFoodCategory(Map map) {
        return foodCategoryMapper.getFoodCategory(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addFoodCategory(FoodCategory foodCategory) {
        return foodCategoryMapper.addFoodCategory(foodCategory);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteFoodCategory(Integer id) {
        return foodCategoryMapper.deleteFoodCategory(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyFoodCategory(FoodCategory foodCategory) {
        return foodCategoryMapper.modifyFoodCategory(foodCategory);
    }

    @Override
    public int getAllFoodCategory() {
        return foodCategoryMapper.getAllFoodCategory();
    }
}
