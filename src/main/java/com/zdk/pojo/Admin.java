package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/19 16:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String id;
    private String username;
    private String trueName;
    private String gender;
    private String pwd;
    private String tel;
    private String registerTime;
    private String lastLoginTime;
    private String loginTimes;
    private String role;
}
