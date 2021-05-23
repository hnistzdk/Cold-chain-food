package com.zdk.utils;

import com.zdk.dto.AddEnterpriseMeta;
import com.zdk.dto.AddUserMeta;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.EnterpriseUser;
import lombok.Data;

/**
 * @author zdk
 * @date 2021/4/25 19:37
 */

@Data
public class UserConvert {
    public static AdminAndUser getAddUser(AddUserMeta addUserMeta, String role) {
        AdminAndUser user = new AdminAndUser();
        user.setId(addUserMeta.getId());
        user.setUsername(addUserMeta.getUsername());
        user.setPwd(addUserMeta.getPwd());
        user.setTrueName(addUserMeta.getTrueName());
        user.setGender(addUserMeta.getGender());
        user.setTel(addUserMeta.getTel());
        user.setEmail(addUserMeta.getEmail());
        user.setRegisterTime(DateConversion.getNowDate());
        user.setRole(role);
        user.setLoginTimes(0);
        return user;
    }
    public static EnterpriseUser getAddUser(AddEnterpriseMeta addEnterpriseMeta, String role) {
        EnterpriseUser user = new EnterpriseUser();
        user.setId(addEnterpriseMeta.getId());
        user.setUsername(addEnterpriseMeta.getUsername());
        user.setPwd(addEnterpriseMeta.getPwd());
        user.setTrueName(addEnterpriseMeta.getTrueName());
        user.setGender(addEnterpriseMeta.getGender());
        user.setEnterpriseName(addEnterpriseMeta.getEnterpriseName());
        user.setTel(addEnterpriseMeta.getTel());
        user.setEmail(addEnterpriseMeta.getEmail());
        user.setRegisterTime(DateConversion.getNowDate());
        user.setRole(role);
        user.setLoginTimes(0);
        return user;
    }
}
