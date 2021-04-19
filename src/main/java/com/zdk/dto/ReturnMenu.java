package com.zdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/4/19 18:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnMenu {
    private MyMenu[] data;
    private Meta meta;
}
