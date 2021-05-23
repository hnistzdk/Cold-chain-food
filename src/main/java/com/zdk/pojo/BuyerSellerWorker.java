package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/5/23 17:25
 * 买家卖家工作人员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerSellerWorker {
    private Integer id;
    private String name;
    private String gender;
    private String tel;
}
