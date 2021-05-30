package com.zdk.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/5/23 15:37
 * 风险记录表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("风险记录类")
public class Risk {
    @ApiModelProperty("风险记录id")
    private Integer recordId;
    @ApiModelProperty("食品id")
    private Integer foodId;
    @ApiModelProperty("食品名称")
    private String foodName;
    @ApiModelProperty("货单码")
    private String manifestId;
    @ApiModelProperty("途径地区")
    private String pathway;
    @ApiModelProperty("风险等级")
    private Integer riskDegree;
    @ApiModelProperty("是否进行了风险检测")
    private String riskEditor;
}
