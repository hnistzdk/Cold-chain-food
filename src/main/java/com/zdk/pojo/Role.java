package com.zdk.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("角色类")
public class Role {
    private int id;
    @ApiModelProperty("角色名")
    private String roleName;
    @ApiModelProperty("角色描述")
    private String roleDescription;
    @ApiModelProperty("角色id")
    private String rightId;
}
