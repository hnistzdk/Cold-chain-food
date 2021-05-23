package com.zdk.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/5/23 13:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("食品类")
public class Food {
    @ApiModelProperty("食品id")
    private Integer id;
    @ApiModelProperty("食品名称")
    private String foodName;
    @ApiModelProperty("生产日期")
    private String productionDate;
    @ApiModelProperty("过期日期")
    private String expiryDate;
    @ApiModelProperty("生产地址")
    private String address;
    @ApiModelProperty("生产厂家")
    private String producer;
    @ApiModelProperty("联系电话")
    private String phone;
    @ApiModelProperty("储存站点id")
    private Integer storageId;
    @ApiModelProperty("风险等级")
    private Integer riskDegree;
    @ApiModelProperty("食品类别的id")
    private Integer categoryId;
}
