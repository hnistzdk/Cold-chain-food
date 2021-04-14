package com.zdk.service;

import com.zdk.pojo.Food;

import java.util.List;


public interface UserService {
//    根据ID查询
    Food queryFoodById(int id);
//    查询全部
    List<Food> queryFoodList();
}
