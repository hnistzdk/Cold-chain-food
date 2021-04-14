package com.zdk.mapper;

import com.zdk.pojo.Food;

import java.util.List;


public interface UserMapper {
    //    根据ID查询
    Food queryFoodById(int id);
    //    查询全部
    List<Food> queryFoodList();
}
