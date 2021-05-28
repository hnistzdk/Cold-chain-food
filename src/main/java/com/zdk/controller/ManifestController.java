package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Manifest;
import com.zdk.service.mainfest.ManifestServiceImpl;
import com.zdk.utils.CommonMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author zdk
 * @date 2021/5/23 17:10
 */
@RestController
public class ManifestController {
    @Autowired
    @Qualifier("ManifestServiceImpl")
    ManifestServiceImpl manifestService;

    @RightInfo("getManifest")
    @PostMapping("/getManifest")
    @CrossOrigin
    public Object getManifest(@Param("query") String query, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        HashMap params=new HashMap<>();
        params.put("pageNum",(pageNum-1)*pageSize);
        params.put("pageSize",pageSize);
        System.out.println("query::"+query);
        params.put("query", query);
        List<Manifest> orderList= manifestService.queryManifest(params);
        int total=manifestService.manifestCount();
        if(orderList!=null){
            msg.put("status", "200");
            data.put("orderList", orderList.toArray());
            data.put("total", total);
        }else{
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo("addManifest")
    @PostMapping("/addManifest")
    @CrossOrigin
    public Object addManifest(Manifest manifest){
        int count = manifestService.addManifest(manifest);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }
}
