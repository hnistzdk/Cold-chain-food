package com.zdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/4/20 19:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminMeta {
    private String id;
    private String username;
    private String gender;
    private String tel;
    private String role;
    private Boolean state;
}
