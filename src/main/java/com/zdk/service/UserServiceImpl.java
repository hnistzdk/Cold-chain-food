package com.zdk.service;

import com.zdk.dao.UserMapper;
import com.zdk.pojo.Food;
import com.zdk.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/12 18:37
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Food queryFoodById(int id) {
        return userMapper.queryFoodById(id);
    }
    @Override
    public List<Food> queryFoodList() {
        return userMapper.queryFoodList();
    }
}
