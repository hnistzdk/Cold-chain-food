package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/19 16:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAndUser {
    private String id;
    private String username;
    private String pwd;
    private String trueName;
    private String gender;
    private String tel;
    private String email;
    private String registerTime;
    private String lastLoginTime;
    private int loginTimes;
    private String role;
}
