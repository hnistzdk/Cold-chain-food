package com.zdk.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/5/23 13:47
 * 食品分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("食品分类")
public class FoodCategory {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("食品类名")
    private String categoryName;
}
