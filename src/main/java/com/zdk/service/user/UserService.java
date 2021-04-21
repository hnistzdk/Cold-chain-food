package com.zdk.service.user;


/**
 * @author zdk
 */
public interface UserService {
    /**
     *普通用户的登录
    * @param  id：账号
    * @param  password：密码
    * @return 登录成功的结果>0则成功
    */
    int login(String id, String password);

}
