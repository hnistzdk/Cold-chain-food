package com.zdk.mapper.user;


import com.zdk.dto.AddEnterpriseMeta;
import com.zdk.dto.AddUserMeta;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.EditMeta;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.EnterpriseUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 */
public interface UserMapper {
    /**
     *普通用户的登录
     * @param  id:账号
     * @param  password:密码
     * @return 登录成功的结果不为空则成功
     */
    AdminAndUser login(@Param("id") String id, @Param("password") String password);

    /**
     *获取普通用户的列表
     * @param  map:map中有两个键值对,key分别为startIndex,pageSize,开始条,页条数
     * @return 返回类型为AdminMeta的list
     */
    List<AdminMeta> getUserList(Map<String,Integer> map);

    /**
     *模糊查询普通用户的列表
     * @param  map:map中有两个键值对,key分别为startIndex,pageSize,开始条,页条数
     * @return 返回类型为AdminMeta的list
     */
    List<AdminMeta> fuzzyQueryUserList(Map map);
    /**
     *获取企业用户的人数
     * @return 人数
     */
    int userTotalPage();

    /**
     *删除普通用户
     * @param  id
     * @return 返回类型条数
     */
    int removeUser(@Param("id") String id);

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
