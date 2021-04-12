package com.zdk.dao;

import com.zdk.pojo.Food;
import com.zdk.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/12 18:37
 */
public interface UserMapper {
    User login(@Param("username") String username, @Param("pwd") String pwd);
    boolean register(@Param("username") String username, @Param("pwd") String pwd);
    Food queryFoodById(int id);
}
