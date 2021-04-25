package com.zdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/4/25 19:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserMeta {
    private String id;
    private String username;
    private String pwd;
    private String trueName;
    private String gender;
    private String tel;
    private String email;
}
