package com.zdk.pojo;

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
public class FoodCategory {
    private Integer id;
    private String categoryName;
}
