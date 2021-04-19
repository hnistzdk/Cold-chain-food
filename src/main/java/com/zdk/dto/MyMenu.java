package com.zdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zdk
 * @date 2021/4/19 18:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyMenu {
    private String authName;
    private MyMenu[] children;
    private Integer id;
    private Integer order;
    private String path;
}
