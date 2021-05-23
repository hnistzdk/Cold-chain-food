package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author zdk
 * @Date 2021/5/10 17:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int id;
    private String roleName;
    private String roleDescription;
    private String rightId;
}
