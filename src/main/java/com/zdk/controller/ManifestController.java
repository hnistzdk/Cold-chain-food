package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.dto.SelectFoodMeta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.EnterpriseUser;
import com.zdk.pojo.Food;
import com.zdk.pojo.Manifest;
import com.zdk.pojo.Storage;
import com.zdk.service.food.FoodServiceImpl;
import com.zdk.service.mainfest.ManifestServiceImpl;
import com.zdk.service.storage.StorageServiceImpl;
import com.zdk.utils.CommonMessage;
import com.zdk.utils.ReturnMessage;
import com.zdk.utils.Permission;
import com.zdk.utils.UUIDUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RightInfo(Permission.GETMANIFEST)
    @PostMapping("/getManifest")
    @CrossOrigin
    public Object getManifest(@Param("query") String query,
                              @Param("pageNum") Integer pageNum,
                              @Param("pageSize") Integer pageSize,
                                HttpServletRequest request){
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
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            data.put("orderList", orderList.toArray());
            data.put("foodList", allFoodName.toArray());
            data.put("arrivedPointList", allStorageName.toArray());
            if(query==null){
                data.put("total", total);
            }else {
                data.put("total", 1);
            }
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo(Permission.ADDMANIFEST)
    @PostMapping("/addManifest")
    @CrossOrigin
    public Object addManifest(Manifest manifest){
        HashMap<Object,Object> params=new HashMap<>();
        params.put("id", manifest.getStorageId());
        manifest.setArrivedPoint(storageService.getStorage(params).get(0).getStorageArea());
        manifest.setFoodName(foodService.getFoodById(manifest.getFoodId()).getFoodName());
        manifest.setManifestId(UUIDUtil.getUUID(10));
        int count = manifestService.addManifest(manifest);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.GETMANIFESTBYID)
    @GetMapping("/manifests/{id}")
    @CrossOrigin
    public Object getManifestById(@PathVariable String id){
        Manifest manifest = manifestService.getManifestById(id);
        HashMap<Object, Object> map = new HashMap<>();
        if(manifest!=null){
            map.put("data", manifest);
            map.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        }else{
            map.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(map);
    }

    @RightInfo(Permission.MODIFYMANIFEST)
    @PostMapping("/modifyManifest")
    @CrossOrigin
    public Object modifyManifest(Manifest manifest){
        Food food = foodService.getFoodById(manifest.getFoodId());
        manifest.setFoodName(food.getFoodName());
        HashMap<Object,Object> params=new HashMap<>();
        params.put("id", manifest.getStorageId());
        List<Storage> storage = storageService.getStorage(params);
        manifest.setArrivedPoint(storage.get(0).getStorageArea());
        int count = manifestService.modifyManifest(manifest);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.DELETEMANIFEST)
    @PostMapping("/deleteManifest/{id}")
    @CrossOrigin
    public Object deleteManifest(@PathVariable String id){
        int count = manifestService.deleteManifest(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.GETSENDMANIFEST)
    @PostMapping("/getSendManifest")
    @CrossOrigin
    public Object getSendManifest(HttpServletRequest request){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        EnterpriseUser loginUser = (EnterpriseUser) request.getSession().getAttribute("loginUser");
        List<Manifest> orderList = manifestService.getManifestBySendOrGet(loginUser.getId(),null);
        if(orderList!=null){
            data.put("orderList", orderList.toArray());
            data.put("orderList", orderList.toArray());
            List<Food> foodList = foodService.getFood(null);
            data.put("foodList", foodList.toArray());
            List<Storage> siteList = storageService.getStorage(null);
            data.put("siteList", siteList.toArray());
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg, data));
    }

    @RightInfo(Permission.GETSENDMANIFEST)
    @PostMapping("/getReceiveManifest")
    @CrossOrigin
    public Object getReceiveManifest(HttpServletRequest request){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        EnterpriseUser loginUser = (EnterpriseUser) request.getSession().getAttribute("loginUser");
        List<Manifest> orderList = manifestService.getManifestBySendOrGet(null,loginUser.getId());
        if(orderList!=null){
            data.put("orderList", orderList.toArray());
            data.put("orderList", orderList.toArray());
            List<Food> foodList = foodService.getFood(null);
            data.put("foodList", foodList.toArray());
            List<Storage> siteList = storageService.getStorage(null);
            data.put("siteList", siteList.toArray());
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg, data));
    }
}
