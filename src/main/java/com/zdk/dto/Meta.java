package com.zdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author zdk
 * @date 2021/4/18 18:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private Map meta;
    private Map data;
}
