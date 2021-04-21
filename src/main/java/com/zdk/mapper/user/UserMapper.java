package com.zdk.mapper.user;


import com.zdk.dto.AdminMeta;
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
     * @return 登录成功的结果>0则成功
     */
    int login(@Param("id") String id,@Param("password") String password);
}
