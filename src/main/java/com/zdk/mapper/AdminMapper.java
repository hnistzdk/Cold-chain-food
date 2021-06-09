package com.zdk.mapper;

import com.zdk.dto.AddUserMeta;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.EditMeta;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.EnterpriseUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/4/21 18:40
 */
@Mapper
@Repository
public interface AdminMapper {
    /**
     *管理员用户的登录
     * @param  id:账号
     * @param  password:密码
     * @return 登录成功的结果不为空则成功
     */
    AdminAndUser adminLogin(@Param("id") String id, @Param("password") String password);

    /**
     *获取管理员用户的列表
     * @param  map:map中有两个键值对,key分别为startIndex,pageSize,开始条,页条数
     * @return 返回类型为AdminMeta的list
     */
    List<AdminMeta> getAdminList(Map map);

    /**
     *获取管理员用户的人数
     * @param query
     * @return 人数
     */
    int adminTotalPage(String query);

    /**
     *模糊查询管理员用户的列表
     * @param  map:map中有两个键值对,key分别为startIndex,pageSize,开始条,页条数
     * @return 返回类型为AdminMeta的list
     */
    List<AdminMeta> fuzzyQueryAdminList(Map map);

    /**
     *删除管理员
     * @param  id
     * @return 返回类型条数
     */
    int removeAdmin(@Param("id") String id);

    /**
     *根据id查询管理员
     * @param  id
     * @return 返回类型条数
     */
    AdminAndUser getAdminById(@Param("id") String id);

    /**
     *改变登录次数和最后登录时间
     * @param  id:用户id
     * @param date:当前日期
     * @return 返回类型条数
     */
    int updateLoginInfo(@Param("id") String id,@Param("date") String date);

    /**
     *添加管理员
     * @param  admin
     * @return 返回类型条数
     */
    int addAdmin(AdminAndUser admin);

    /**
     *显示管理员用户编辑信息
     * @param  id:企业用户id
     * @return 返回编辑对象
     */
    EditMeta showAdminUser(String id);

    /**
     *修改管理员个人信息
     * @param  user
     * @return 返回类型条数
     */
    int editUserInfo(EditMeta user);

    /**
     *修改管理员密码
     * @param id
     * @param password
     * @return 返回类型条数
     */
    int modifyPassword(String id,String password);

    /**
     * 改变用户锁定状态
     * @param id
     * @param mg_state
     * @return
     */
    int updateStateAdmin(String id,Boolean mg_state);
}
