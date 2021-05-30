package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.sun.swing.internal.plaf.metal.resources.metal;
import com.zdk.dto.Meta;
import com.zdk.dto.SelectFoodMeta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Food;
import com.zdk.pojo.Manifest;
import com.zdk.pojo.Storage;
import com.zdk.service.food.FoodServiceImpl;
import com.zdk.service.mainfest.ManifestServiceImpl;
import com.zdk.service.storage.StorageServiceImpl;
import com.zdk.utils.CommonMessage;
import com.zdk.utils.UUIDUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    @Qualifier("StorageServiceImpl")
    StorageServiceImpl storageService;

    @Autowired
    @Qualifier("FoodServiceImpl")
    private FoodServiceImpl foodService;

    @RightInfo("getManifest")
    @PostMapping("/getManifest")
    @CrossOrigin
    public Object getManifest(@Param("query") String query, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        HashMap params=new HashMap<>();
        if(query.equals("")){
            query=null;
        }
        params.put("pageNum",(pageNum-1)*pageSize);
        params.put("pageSize",pageSize);
        params.put("query", query);
        List<Manifest> orderList= manifestService.queryManifest(params);
        List<SelectFoodMeta> allFoodName = manifestService.getAllFoodName();
        List<Storage> allStorageName = manifestService.getAllStorageName();
        int total=manifestService.manifestCount();
        if(orderList!=null){
            msg.put("status", "200");
            data.put("orderList", orderList.toArray());
            data.put("foodList", allFoodName.toArray());
            data.put("arrivedPointList", allStorageName.toArray());
            if(query==null){
                data.put("total", total);
            }else {
                data.put("total", 1);
            }
        }else{
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo("addManifest")
    @PostMapping("/addManifest")
    @CrossOrigin
    public Object addManifest(Manifest manifest){
        manifest.setArrivedPoint(storageService.getStorage(manifest.getStorageId()).get(0).getStorageArea());
        manifest.setFoodName(foodService.getFoodById(manifest.getFoodId()).getFoodName());
        manifest.setManifestId(UUIDUtil.getUUID(10));
        int count = manifestService.addManifest(manifest);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo("getManifestById")
    @GetMapping("/manifests/{id}")
    @CrossOrigin
    public Object getManifestById(@PathVariable String id){
        Manifest manifest = manifestService.getManifestById(id);
        HashMap<Object, Object> map = new HashMap<>();
        if(manifest!=null){
            map.put("data", manifest);
            map.put("status", "200");
        }else{
            map.put("status", "201");
        }
        return JSON.toJSONString(map);
    }

    @RightInfo("modifyManifest")
    @PostMapping("/modifyManifest")
    @CrossOrigin
    public Object modifyManifest(Manifest manifest){
        Food food = foodService.getFoodById(manifest.getFoodId());
        manifest.setFoodName(food.getFoodName());
        System.out.println("manifest"+manifest);
        List<Storage> storage = storageService.getStorage(manifest.getStorageId());
        manifest.setArrivedPoint(storage.get(0).getStorageArea());
        int count = manifestService.modifyManifest(manifest);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo("deleteManifest")
    @PostMapping("/deleteManifest/{id}")
    @CrossOrigin
    public Object deleteManifest(@PathVariable String id){
        int count = manifestService.deleteManifest(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }
}
