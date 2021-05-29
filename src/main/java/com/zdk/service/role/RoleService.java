package com.zdk.service.role;

import com.zdk.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author zdk
 * @Date 2021/5/10 17:20
 */
public interface RoleService {
    /**
     *查询所有角色列表
     * @param id
     * @return List<Role>
     */
    List<Role> getRoles(Integer id);

    /**
     *添加角色
     * @param role
     * @return 条数
     */
    int addRoles(Role role);

    /**
     *修改角色信息
     * @param role
     * @return 条数
     */
    int modifyRoles(Role role);

    /**
     *删除角色
     * @param id
     * @return 条数
     */
    int deleteRoles(@Param("id") int id);

    /**
     *给角色分配权限
     * @param map
     * @return 条数
     */
    int assignRight(Map map);
}
