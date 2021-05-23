package com.zdk.service.food;

import com.zdk.mapper.FoodMapper;
import com.zdk.pojo.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 14:17
 */
@Service("FoodServiceImpl")
public class FoodServiceImpl implements FoodService{

    @Autowired
    FoodMapper foodMapper;

    @Override
    public int addFood(Food food) {
        return foodMapper.addFood(food);
    }

    @Override
    public int deleteFoodById(Integer id) {
        return foodMapper.deleteFoodById(id);
    }

    @Override
    public int modifyFood(Food food) {
        return foodMapper.modifyFood(food);
    }

    @Override
    public List<Food> queryFoodBy(Map map) {
        return foodMapper.queryFoodBy(map);
    }

    @Override
    public int foodCount() {
        return 0;
    }
}
