package com.zdk.service;

import com.zdk.dto.AdminMeta;
import com.zdk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zdk
 */
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

    @Override
    public List<AdminMeta> getAdminList(Integer pageNum, Integer pageSize) {
        Map<String, Integer> map=new HashMap<>();
        map.put("startIndex", pageNum);
        map.put("pageSize", pageSize);
        return userMapper.getAdminList(map);
    }

    @Override
    public int adminTotalPage() {
        return userMapper.adminTotalPage();
    }
}
