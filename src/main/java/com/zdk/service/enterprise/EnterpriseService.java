package com.zdk.service.enterprise;

/**
 * @author zdk
 * @date 2021/4/21 18:58
 */
public interface EnterpriseService {
    /**
     *企业用户的登录
     * @param  id：账号
     * @param  password：密码
     * @return 登录成功的结果>0则成功
     */
    int enterpriseLogin(String id, String password);
}
