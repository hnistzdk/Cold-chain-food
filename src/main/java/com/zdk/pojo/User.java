package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/12 18:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String position;
    private String pwd;
    private String tel;
    //注册时间
    private Date registerDate;
    //最后一次登录时间
    private Date lastLoginTime;
    //登录次数
    private int loginTimes;
    private String realName;
    private String gender;
}
