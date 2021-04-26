package com.zdk.service.user;


import com.zdk.dto.AddUserMeta;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.EditMeta;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.EnterpriseUser;

import java.util.List;

/**
 * @author zdk
 */
public interface UserService {
    /**
     *普通用户的登录
    * @param  id ：账号
    * @param  password ：密码
    * @return 登录成功的结果>0则成功
    */
    AdminAndUser login(String id, String password);

    /**
     *获取普通用户的列表
     * @param  pageNum 从第几条数据开始获取
     * @param  pageSize 分页的每页的条数
     * @return 返回类型为AdminMeta的list
     */
    List<AdminMeta> getUserList(Integer pageNum, Integer pageSize);

    /**
     *模糊查询普通用户的列表
     * @param  query:模糊查询条件
     * @param  pageNum:开始条
     * @param  pageSize:页条数
     * @return 返回类型为AdminMeta的list
     */
    List<AdminMeta> fuzzyQueryUserList(String query,Integer pageNum, Integer pageSize);

    /**
     *获取普通用户的人数
     * @return 人数
     */
    int userTotalPage();

    /**
     *删除普通用户
     * @param  id
     * @return 返回类型条数
     */
    int removeUser(String id);

    /**
     *添加普通用户
     * @param  user:添加时dto对象
     * @return 返回类型条数
     */
    int addUser(AdminAndUser user);

    /**
     *显示普通用户编辑信息
     * @param  id:普通用户id
     * @return 返回编辑对象
     */
    EditMeta showPrimaryUser(String id);

    /**
     *修改普通用户信息
     * @param  user:编辑普通用户对象
     * @return 返回修改条数
     */
    int modifyPrimaryUser(EditMeta user);
}
