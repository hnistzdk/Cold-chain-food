package com.zdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/4/22 19:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseMeta {
    private String id;
    private String username;
    private String gender;
    private String tel;
    private String enterpriseName;
    private String role;
}
