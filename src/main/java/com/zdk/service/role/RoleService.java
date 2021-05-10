package com.zdk.service.role;

import com.zdk.pojo.Role;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/5/10 17:20
 */
public interface RoleService {
    /**
     *查询所有角色列表
     * @return List<Role>
     */
    List<Role> getRoles();
}
