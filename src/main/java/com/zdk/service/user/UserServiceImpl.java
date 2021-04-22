package com.zdk.service.user;

import com.zdk.dto.AdminMeta;
import com.zdk.dto.EnterpriseMeta;
import com.zdk.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zdk
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int login(String id, String password) {
        return userMapper.login(id,password);
    }

    @Override
    public List<AdminMeta> getUserList(Integer pageNum, Integer pageSize) {
        Map<String, Integer> map=new HashMap<>();
        map.put("startIndex", pageNum);
        map.put("pageSize", pageSize);
        return userMapper.getUserList(map);
    }

    @Override
    public List<AdminMeta> fuzzyQueryUserList(String query, Integer pageNum, Integer pageSize) {
        Map map=new HashMap<>();
        map.put("query", query);
        map.put("startIndex", pageNum);
        map.put("pageSize", pageSize);
        return userMapper.fuzzyQueryUserList(map);
    }

    @Override
    public int userTotalPage() {
        return userMapper.userTotalPage();
    }
}
