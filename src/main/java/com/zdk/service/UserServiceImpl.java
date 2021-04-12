package com.zdk.service;

import com.zdk.dao.UserMapper;
import com.zdk.pojo.Food;
import com.zdk.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/12 18:37
 */
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(String username, String pwd) {
        return userMapper.login(username, pwd);
    }

    @Override
    public boolean register(String username, String pwd) {
        if(login(username, pwd)!=null){
           return false;
        }
        return userMapper.register(username, pwd);
    }

    @Override
    public Food queryFoodById(int id) {
        return userMapper.queryFoodById(id);
    }
}
