package com.zdk.mapper.right;

import com.zdk.pojo.Right;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/17 19:19
 */
public interface RightMapper {
    /**
     *获得所有权限的信息
     * @param id
     * @return List<Role>
     */
    List<Right> getRights(@Param("id") Integer id);
}
