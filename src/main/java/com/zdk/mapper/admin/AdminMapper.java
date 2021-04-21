package com.zdk.mapper.admin;

import com.zdk.dto.AdminMeta;
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
     * @return 登录成功的结果>0则成功
     */
    int adminLogin(@Param("id") String id,@Param("password") String password);

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
}
