package com.zdk.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/5/23 15:29
 * 储存环境表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("储存环境类")
public class Storage {
    @ApiModelProperty("储存站点id")
    private Integer storageId;
    @ApiModelProperty("企业用户id")
    private String uId;
    @ApiModelProperty("储存站点的地区")
    private String storageArea;
    @ApiModelProperty("空气温度")
    private Double temper;
    @ApiModelProperty("空气湿度")
    private Double humidity;
    @ApiModelProperty("报警记录")
    private String alarmRecord;
    @ApiModelProperty("市电监控")
    private String powerSupply;
}
