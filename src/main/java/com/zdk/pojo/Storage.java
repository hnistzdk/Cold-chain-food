package com.zdk.pojo;

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
public class Storage {
    private Integer storageId;
    private String uId;
    private String storageArea;
    private double temper;
    private double humidity;
    private String alarmRecord;
    private String powerSupply;
}
