package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Storage;
import com.zdk.service.storage.StorageServiceImpl;
import com.zdk.utils.CommonMessage;
import com.zdk.utils.ReturnMessage;
import com.zdk.utils.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author zdk
 * @date 2021/5/30 10:57
 */
@CrossOrigin
@RestController
public class StorageController {
    @Autowired
    @Qualifier("StorageServiceImpl")
    StorageServiceImpl storageService;

    @RightInfo(Permission.GETSTORAGE)
    @PostMapping("/getStorage")
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
        int total = storageService.getStorageCount();
        if(query!=null){
            total=1;
        }
        if(storageList!=null){
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            data.put("siteList",storageList.toArray());
            data.put("total",total);
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg, data));
    }

    @RightInfo(Permission.SHOWSTORAGE)
    @PostMapping("/showStorage/{id}")
    public Object showStorage(@PathVariable Integer id){
        HashMap data = new HashMap<>();
        HashMap params=new HashMap<>();
        params.put("id", id);
        List<Storage> storageList = storageService.getStorage(params);
        if(storageList!=null){
            data.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            data.put("data",storageList.get(0));
        }else{
            data.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(data);
    }

    @RightInfo(Permission.MODIFYSTORAGE)
    @PostMapping("/modifyStorage")
    public Object modifyStorage(Storage storage){
        int count = storageService.modifyStorage(storage);
        return CommonMessage.returnStatus(count>0);
    }

    @RightInfo(Permission.DELETESTORAGE)
    @PostMapping("/deleteStorage/{id}")
    public Object deleteStorage(@PathVariable Integer id){
        int count = storageService.deleteStorage(id);
        return CommonMessage.returnStatus(count>0);
    }
}
