package com.zdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/26 16:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditMeta {
    private String id;
    private String username;
    private String trueName;
    private String gender;
    private String tel;
    private String email;
    private String enterpriseName;
    private String site;
}
