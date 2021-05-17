package com.zdk.mapper.role;

import com.zdk.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/5/10 17:15
 */
public interface RoleMapper {
    /**
     *查询所有角色列表
     * @param id
     * @return List<Role>
     */
    List<Role> getRoles(@Param("id") Integer id);

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
}
