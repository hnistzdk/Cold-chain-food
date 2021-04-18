package com.zdk.service;

import com.zdk.mapper.UserMapper;
import com.zdk.pojo.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

}
