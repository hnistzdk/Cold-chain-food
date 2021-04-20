package com.zdk.service;

import com.zdk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public int login(String id, String password) {
        return userMapper.login(id,password);
    }

    @Override
    public int enterpriseLogin(String id, String password) {
        return userMapper.enterpriseLogin(id, password);
    }

    @Override
    public int adminLogin(String id, String password) {
        return userMapper.adminLogin(id, password);
    }
}
