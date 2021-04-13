package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zdk
 * @date 2021/4/12 19:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private int id;
    private String foodName;
    private Date productionDate;
    private Date expiryDate;
    private String address;
    private String producer;
    private String phone;
    private int storageId;
    private String riskDegree;
}
