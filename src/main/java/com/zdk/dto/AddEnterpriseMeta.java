package com.zdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/4/24 19:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEnterpriseMeta {
    private String id;
    private String username;
    private String pwd;
    private String gender;
    private String tel;
    private String email;
}
