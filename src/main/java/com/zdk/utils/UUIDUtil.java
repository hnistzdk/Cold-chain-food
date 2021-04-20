package com.zdk.utils;

import java.util.UUID;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/20 16:57
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
