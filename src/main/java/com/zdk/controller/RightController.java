package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Right;
import com.zdk.service.right.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author zdk
 * @date 2021/5/17 19:21
 */
@RestController
public class RightController {
    @Autowired
    @Qualifier("RightServiceImpl")
    private RightService rightService;

    @RightInfo("rightList")
    @GetMapping("/rights/list")
    @CrossOrigin
    public Object rightList(){
        List<Right> rights = rightService.getRights(null);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        if(rights!=null){
            msg.put("status", "200");
            data.put("rightsList",rights.toArray());
        }else {
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,data));
    }


    @RightInfo("showSetRightDialog")
    @GetMapping("/rights/tree")
    @CrossOrigin
    public Object showSetRightDialog(){
        List<Right> rights = rightService.getRights(null);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        if(rights!=null){
            msg.put("status", "200");
            data.put("rightsList",rights.toArray());
        }else {
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

}
