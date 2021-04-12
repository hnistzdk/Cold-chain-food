package com.zdk.service;

import com.zdk.pojo.Food;
import com.zdk.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author zdk
 * @date 2021/4/12 19:27
 */
public interface UserService {
    User login(String username, String pwd);
    boolean register(@Param("username") String username, @Param("pwd") String pwd);
    Food queryFoodById(@Param("id")int id);
}
