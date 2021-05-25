package com.zdk.service.food;

import com.zdk.mapper.FoodMapper;
import com.zdk.pojo.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/23 14:17
 */
@Service("FoodServiceImpl")
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodMapper foodMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addFood(Food food) {
        return foodMapper.addFood(food);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteFoodById(Integer id) {
        return foodMapper.deleteFoodById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyFood(Food food) {
        return foodMapper.modifyFood(food);
    }

    @Override
    public List<Food> queryFoodBy(Map map) {
        return foodMapper.queryFoodBy(map);
    }

    @Override
    public int foodCount(String query) {
        return foodMapper.foodCount(query);
    }

    @Override
    public List<Food> getFood(Map map) {
        return foodMapper.getFood(map);
    }

    @Override
    public List<Food> fuzzyQueryFood(Map map) {
        return foodMapper.fuzzyQueryFood(map);
    }
}
