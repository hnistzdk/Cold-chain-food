package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/5/23 15:43
 * 从业人员表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer employeeId;
    private Integer storageId;
    private String uId;
    private String detection;
    private String notification;
}
