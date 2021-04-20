package com.zdk.mapper;


import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int login(@Param("id") String id,@Param("password") String password);
    int enterpriseLogin(@Param("eid") String id,@Param("password") String password);
    int adminLogin(@Param("id") String id,@Param("password") String password);
}
