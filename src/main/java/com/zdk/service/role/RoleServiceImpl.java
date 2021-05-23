package com.zdk.service.role;

import com.zdk.mapper.RoleMapper;
import com.zdk.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Role> getRoles(@Param("id") Integer id) {
        return roleMapper.getRoles(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addRoles(Role role) {
        return roleMapper.addRoles(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyRoles(Role role) {
        return roleMapper.modifyRoles(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteRoles(int id) {
        return roleMapper.deleteRoles(id);
    }
}
