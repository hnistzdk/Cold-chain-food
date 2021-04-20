package com.zdk.service;


import org.apache.ibatis.annotations.Param;

public interface UserService {
    int login(String id, String password);
    int enterpriseLogin(String id, String password);
    int adminLogin(String id,String password);
}
