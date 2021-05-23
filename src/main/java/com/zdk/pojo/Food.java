package com.zdk.pojo;

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
public class Food {
    private Integer id;
    private String foodName;
    private String productionDate;
    private String expiryDate;
    private String address;
    private String producer;
    private String phone;
    private Integer storageId;
    private Integer riskDegree;
    private Integer categoryId;
}
