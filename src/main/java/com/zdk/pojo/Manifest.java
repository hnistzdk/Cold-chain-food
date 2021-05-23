package com.zdk.pojo;

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
public class Manifest {
    private String manifestId;
    private Integer foodId;
    private String consignorName;
    private String consigneeName;
    private String startingSite;
    private String receivedSite;
    private String travelStatus;
    private String arrivedPoint;
    private Integer consignorId;
    private Integer consigneeId;
}
