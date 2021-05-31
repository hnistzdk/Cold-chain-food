package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Storage;
import com.zdk.service.storage.StorageServiceImpl;
import com.zdk.utils.CommonMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author zdk
 * @date 2021/5/30 10:57
 */
@RestController
public class StorageController {
    @Autowired
    @Qualifier("StorageServiceImpl")
    StorageServiceImpl storageService;

    @RightInfo("getStorage")
    @PostMapping("/getStorage")
    @CrossOrigin
    public Object getStorage(@Param("query") String query, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize){
        HashMap msg = new HashMap<>();
        HashMap data = new HashMap<>();
        HashMap params=new HashMap<>();
        if(query.equals("")){
            query=null;
        }
        params.put("query", query);
        params.put("pageNum", (pageNum-1)*pageSize);
        params.put("pageSize", pageSize);
        List<Storage> storageList = storageService.getStorage(params);
        if(storageList!=null){
            msg.put("status", "200");
            data.put("storageList",storageList);
        }else{
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg, data));
    }

    @RightInfo("showStorage")
    @PostMapping("/showStorage/{id}")
    @CrossOrigin
    public Object showStorage(@PathVariable Integer id){
        HashMap msg = new HashMap<>();
        HashMap data = new HashMap<>();
        HashMap params=new HashMap<>();
        params.put("id", id);
        List<Storage> storageList = storageService.getStorage(params);
        if(storageList!=null){
            msg.put("status", "200");
            data.put("storageList",storageList.get(0));
        }else{
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg, data));
    }

    @RightInfo("modifyStorage")
    @PostMapping("/modifyStorage")
    @CrossOrigin
    public Object modifyStorage(Storage storage){
        int count = storageService.modifyStorage(storage);
        return CommonMessage.returnStatus(count>0);
    }

    @RightInfo("deleteStorage")
    @PostMapping("/deleteStorage/{id}")
    @CrossOrigin
    public Object deleteStorage(@PathVariable Integer id){
        int count = storageService.deleteStorage(id);
        return CommonMessage.returnStatus(count>0);
    }
}
