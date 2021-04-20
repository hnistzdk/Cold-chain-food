package com.zdk.mapper;


import com.zdk.dto.AdminMeta;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int login(@Param("id") String id,@Param("password") String password);
    int enterpriseLogin(@Param("id") String id,@Param("password") String password);
    int adminLogin(@Param("id") String id,@Param("password") String password);
    List<AdminMeta> getAdminList(Map<String,Integer> map);
    int adminTotalPage();
}
