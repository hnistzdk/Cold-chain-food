package com.zdk.service.role;

import com.zdk.mapper.role.RoleMapper;
import com.zdk.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/5/10 17:20
 */
@Service("RoleServiceImpl")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoles() {
        return roleMapper.getRoles();
    }
}
