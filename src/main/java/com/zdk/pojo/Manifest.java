package com.zdk.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/5/23 15:22
 * 货单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("货单类")
public class Manifest {
    @ApiModelProperty("货单号")
    private String manifestId;
    @ApiModelProperty("食品id")
    private Integer foodId;
    @ApiModelProperty("食品名称")
    private String foodName;
    @ApiModelProperty("发货人姓名")
    private String consignorName;
    @ApiModelProperty("收货人姓名")
    private String consigneeName;
    @ApiModelProperty("发货地址")
    private String startingSite;
    @ApiModelProperty("收货地址")
    private String receivedSite;
    @ApiModelProperty("运输状态")
    private String travelStatus;
    @ApiModelProperty("站点id")
    private Integer storageId;
    @ApiModelProperty("到达站点")
    private String arrivedPoint;
    @ApiModelProperty("发货人id")
    private Integer consignorId;
    @ApiModelProperty("收货人id")
    private Integer consigneeId;
}
