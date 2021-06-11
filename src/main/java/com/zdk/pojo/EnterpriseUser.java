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
public class EnterpriseUser extends AdminAndUser{
    private String id;
    private String username;
    private String pwd;
    private String trueName;
    private String gender;
    private String enterpriseName;
    private String tel;
    private String email;
    private String registerTime;
    private String lastLoginTime;
    private int loginTimes;
    private String role;
    private Integer roleId;
    private Boolean state;
    private String site;
}
