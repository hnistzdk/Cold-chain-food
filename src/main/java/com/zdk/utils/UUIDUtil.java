package com.zdk.utils;

import java.util.UUID;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/20 16:57
 * UUID工具类  因为是单机应用  没有使用适用于分布式的SnowFlake(雪花算法 Twitter)id生成算法
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    /**
     * (获取指定长度uuid)
     * @return uuid
     */
    public static  String getUUID(int len)
    {
        if(0 >= len) {
            return null;
        }
        String uuid = getUUID();
        System.out.println(uuid);
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < len; i++) {
            str.append(uuid.charAt(i));
        }
        return str.toString();
    }
}
