package com.zdk.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/5/17 19:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("权限类")
public class Right {
    private Integer id;
    @ApiModelProperty("权限名")
    private String rightName;
    @ApiModelProperty("权限路径")
    private String rightPath;
    @ApiModelProperty("权限描述")
    private String rightDescription;
    @ApiModelProperty("是否被勾选")
    private boolean isChecked;
}
