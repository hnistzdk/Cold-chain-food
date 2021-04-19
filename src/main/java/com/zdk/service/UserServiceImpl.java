package com.zdk.service;

import com.zdk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public int login(String id, String password) {
        return userMapper.login(id,password);
    }
}
