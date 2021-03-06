package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Storage;
import com.zdk.service.storage.StorageServiceImpl;
import com.zdk.utils.CommonMessage;
import com.zdk.utils.ReturnMessage;
import com.zdk.utils.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "储存站点api")
@RestController
public class StorageController {
    @Autowired
    @Qualifier("StorageServiceImpl")
    StorageServiceImpl storageService;

    @ApiOperation("获取储存站点列表")
    @RightInfo(Permission.GETSTORAGE)
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

    @ApiOperation("获取要修改的储存站点的信息")
    @RightInfo(Permission.SHOWSTORAGE)
    @PostMapping("/showStorage/{id}")
    @CrossOrigin
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

    @ApiOperation("修改储存站点信息")
    @RightInfo(Permission.MODIFYSTORAGE)
    @PostMapping("/modifyStorage")
    @CrossOrigin
    public Object modifyStorage(Storage storage){
        int count = storageService.modifyStorage(storage);
        return CommonMessage.returnStatus(count>0);
    }

    @ApiOperation("删除储存站点")
    @RightInfo(Permission.DELETESTORAGE)
    @PostMapping("/deleteStorage/{id}")
    @CrossOrigin
    public Object deleteStorage(@PathVariable Integer id){
        int count = storageService.deleteStorage(id);
        return CommonMessage.returnStatus(count>0);
    }
}
