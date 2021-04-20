package com.zdk.service;


import com.zdk.dto.AdminMeta;

import java.util.List;

public interface UserService {
    int login(String id, String password);
    int enterpriseLogin(String id, String password);
    int adminLogin(String id,String password);
    List<AdminMeta> getAdminList(Integer pageNum, Integer pageSize);
    int adminTotalPage();

}
