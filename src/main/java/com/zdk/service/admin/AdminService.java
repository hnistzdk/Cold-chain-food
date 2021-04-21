package com.zdk.service.admin;

import com.zdk.dto.AdminMeta;

import java.util.List;

/**
 * @author zdk
 * @date 2021/4/21 18:42
 */
public interface AdminService {
    /**
     *管理员用户的登录
     * @param  id：账号
     * @param  password：密码
     * @return 登录成功的结果>0则成功
     */
    int adminLogin(String id,String password);

    /**
     *获取管理员用户的列表
     * @param  pageNum 从第几条数据开始获取
     * @param  pageSize 分页的每页的条数
     * @return 返回类型为AdminMeta的list
     */
    List<AdminMeta> getAdminList(Integer pageNum, Integer pageSize);

    /**
     *获取管理员用户的人数
     * @return 人数
     */
    int adminTotalPage();
}
