package com.zdk.service.admin;

import com.zdk.dto.AddUserMeta;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.EditMeta;
import com.zdk.pojo.AdminAndUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    AdminAndUser adminLogin(String id, String password);

    /**
     *获取管理员用户的列表
     * @param  pageNum 从第几条数据开始获取
     * @param  pageSize 分页的每页的条数
     * @return 返回类型为AdminMeta的list
     */
    List<AdminMeta> getAdminList(Integer pageNum, Integer pageSize);

    /**
     *模糊查询管理员用户的列表
     * @param  query:模糊查询条件
     * @param  pageNum:开始条
     * @param  pageSize:页条数
     * @return 返回类型为AdminMeta的list
     */
    List<AdminMeta> fuzzyQueryAdminList(String query,Integer pageNum, Integer pageSize);
    /**
     *获取管理员用户的人数
     * @param query
     * @return 人数
     */
    int adminTotalPage(String query);

    /**
     *删除管理员
     * @param  id
     * @return 返回类型条数
     */
    int removeAdmin(String id);

    /**
     * 添加管理员
     * @param  admin
     * @return 返回类型条数
     */
    int addAdmin(AdminAndUser admin);

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

    /**
     * 给管理员分配详细角色
     * @param map
     * @return
     */
    int assignRoleForAdmin(Map map);
}

