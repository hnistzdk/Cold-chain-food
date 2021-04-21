package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/4/21 17:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseUser {
    private String id;
    private String username;
    private String trueName;
    private String gender;
    private String pwd;
    private String enterpriseName;
    private String tel;
    private String email;
    private String registerTime;
    private String lastLoginTime;
    private String loginTimes;
    private String role;
}
