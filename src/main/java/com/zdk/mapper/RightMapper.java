package com.zdk.mapper;

import com.zdk.pojo.Right;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zdk
 * @date 2021/5/17 19:19
 */
@Mapper
@Repository
public interface RightMapper {
    /**
     *获得所有权限的信息
     * @param id
     * @return List<Role>
     */
    List<Right> getRights(@Param("id") Integer id);
}
