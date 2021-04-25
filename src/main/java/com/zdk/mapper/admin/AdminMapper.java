package com.zdk.mapper.admin;

import com.zdk.dto.AdminMeta;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.EnterpriseUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zdk
 * @date 2021/4/21 18:40
 */
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
    List<AdminMeta> getAdminList(Map<String,Integer> map);

    /**
     *获取管理员用户的人数
     * @return 人数
     */
    int adminTotalPage();

    /**
     *模糊查询管理员用户的列表
     * @param  map:map中有两个键值对,key分别为startIndex,pageSize,开始条,页条数
     * @return 返回类型为AdminMeta的list
     */
    List<AdminMeta> fuzzyQueryAdminList(Map<String,Integer> map);

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
}
