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
    public static Meta returnMsg(String id){
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> map1 = new HashMap<>();
        if(id!=null){
            if(!id.equals(ReturnMessage.ISLOCKED)){
                map.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
                map1.put("token", id);
            }else {
                map.put(ReturnMessage.STATUS, ReturnMessage.ISLOCKED);
            }
        }else {
            map.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return new Meta(map,map1);
    }

    public static Meta returnStatus(boolean result){
        HashMap<String, String> msg = new HashMap<>();
        HashMap<String, String> data = new HashMap<>();
        if(result){
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        }else {
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return new Meta(msg,data);
    }
}
