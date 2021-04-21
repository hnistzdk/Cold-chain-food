package com.zdk.mapper.enterprise;

import org.apache.ibatis.annotations.Param;

/**
 * @author zdk
 * @date 2021/4/21 18:59
 */
public interface EnterpriseMapper {
    /**
     *企业用户的登录
     * @param  id:账号
     * @param  password:密码
     * @return 登录成功的结果>0则成功
     */
    int enterpriseLogin(@Param("id") String id, @Param("password") String password);
}
