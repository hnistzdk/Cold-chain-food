package com.zdk.mapper;


import com.zdk.dto.AdminMeta;
import com.zdk.dto.EditMeta;
import com.zdk.pojo.AdminAndUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     *普通用户的登录
     * @param  id:账号
     * @param  password:密码
     * @param email:修改密码时接口的重用
     * @return 登录成功的结果不为空则成功
     */
    AdminAndUser login(@Param("id") String id, @Param("password") String password,@Param("email") String email);

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
     * @param query
     * @return 人数
     */
    int userTotalPage(String query);

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

    /**
     *改变登录次数和最后登录时间
     * @param  id:用户id
     * @param date:当前日期
     * @return 返回类型条数
     */
    int updateLoginInfo(@Param("id") String id,@Param("date") String date);

    /**
     *修改普通用户密码
     * @param  map:id和新密码
     * @return 返回修改条数
     */
    int modifyUserPwd(Map map);
}
