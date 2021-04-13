package com.zdk.service;

import com.zdk.pojo.Food;
import com.zdk.pojo.User;

import java.util.List;

/**
 * @author zdk
 * @date 2021/4/12 19:27
 */
public interface UserService {
    User login(String username, String pwd);
    boolean register(String username,String pwd);
    Food queryFoodById(int id);
    List<Food> queryFoodList();
}
