package com.zdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/5/30 10:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectFoodMeta {
    private Integer foodId;
    private String foodName;
}
