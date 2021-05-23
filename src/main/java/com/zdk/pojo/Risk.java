package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/5/23 15:37
 * 风险记录表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Risk {
    private Integer recordId;
    private Integer foodId;
    private String manifestId;
    private String pathway;
    private Integer riskDegree;
}
