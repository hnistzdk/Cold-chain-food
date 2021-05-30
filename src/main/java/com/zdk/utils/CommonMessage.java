package com.zdk.utils;

import com.zdk.dto.Meta;
import com.zdk.pojo.AdminAndUser;

import java.util.HashMap;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/20 17:05
 */
public class CommonMessage {
    public static Meta returnMsg(Object result){
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> map1 = new HashMap<>();
        AdminAndUser user= (AdminAndUser) result;
        if(result!=null){
            map.put("status","200");
            map1.put("token", user.getId());
        }else {
            map.put("status","404");
        }
        return new Meta(map,map1);
    }

    public static Meta returnStatus(boolean result){
        HashMap<String, String> msg = new HashMap<>();
        HashMap<String, String> data = new HashMap<>();
        if(result){
            msg.put("status","200");
        }else {
            msg.put("status","201");
        }
        return new Meta(msg,data);
    }
}
