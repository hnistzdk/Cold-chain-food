package com.zdk.utils;

import com.zdk.dto.Meta;

import java.util.HashMap;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/20 17:05
 */
public class LoginMessage {
    public static Meta returnMsg(int result){
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> map1 = new HashMap<>();
        if(result>0){
            map.put("status","200");
            map1.put("token", UUIDUtil.getUUID());
        }else {
            map.put("status","404");
        }
        return new Meta(map,map1);
    }
}
