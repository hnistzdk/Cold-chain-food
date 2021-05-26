package com.zdk.mapper;

import com.zdk.pojo.Right;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/5/17 19:19
 */
@Mapper
@Repository
public interface RightMapper {
    /**
     *获得所有权限的信息
     * @param map
     * @return List<Right>
     */
    List<Right> getRights(Map map);

    /**
     *获得所有权限数量
     * @return int
     */
    int getRightsCount();
}
