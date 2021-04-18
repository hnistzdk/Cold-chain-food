package com.zdk.vo;

import com.sun.javafx.binding.StringFormatter;
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
    private Map<String, String> meta;
}
