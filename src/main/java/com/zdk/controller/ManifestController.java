package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Manifest;
import com.zdk.service.mainfest.ManifestServiceImpl;
import com.zdk.utils.CommonMessage;
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
    @GetMapping("/getManifest")
    @CrossOrigin
    public Object getManifest(@Nullable String manifestId){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        msg.put("msg", "获取成功");
        msg.put("status", "200");
        List<Manifest> manifestList;
        if(manifestId==null){
            manifestList = manifestService.queryManifest(null);
        }else {
            manifestList = manifestService.queryManifest(manifestId);
        }
        data.put("manifestList", JSON.toJSON(manifestList.toArray()));
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
