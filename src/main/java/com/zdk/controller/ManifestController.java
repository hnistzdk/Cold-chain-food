package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.dto.SelectFoodMeta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.*;
import com.zdk.service.food.FoodServiceImpl;
import com.zdk.service.mainfest.ManifestServiceImpl;
import com.zdk.service.storage.StorageServiceImpl;
import com.zdk.utils.CommonMessage;
import com.zdk.utils.Permission;
import com.zdk.utils.ReturnMessage;
import com.zdk.utils.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "货单管理api")
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

    @ApiOperation("管理员获取货单列表")
    @RightInfo(Permission.GETMANIFEST)
    @PostMapping("/getManifest")
    @CrossOrigin
    public Object getManifest(@Param("query") String query,
                              @Param("pageNum") Integer pageNum,
                              @Param("pageSize") Integer pageSize){
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

    @ApiOperation("添加货单")
    @RightInfo(Permission.ADDMANIFEST)
    @PostMapping("/addManifest")
    @CrossOrigin
    public Object addManifest(Manifest manifest,HttpServletRequest request){
        AdminAndUser loginUser = (AdminAndUser) request.getSession().getAttribute("loginUser");
        HashMap<Object,Object> params=new HashMap<>();
        //设置发货人id
        manifest.setConsignorId(loginUser.getId());
        //设置发货人姓名
        manifest.setConsignorName(loginUser.getTrueName());
        //设置未收货
        manifest.setIsReceived("未收货");
        //设置运输状态
        manifest.setTravelStatus("未发货");
        //设置发货站点
        params.put("uid", loginUser.getId());
        manifest.setStartingSite(storageService.getStorage(params).get(0).getStorageArea());
        //设置当前到达站点和最终到达站点
        params.remove("uid");
        params.put("id", manifest.getStorageId());
        manifest.setArrivedPoint(storageService.getStorage(params).get(0).getStorageArea());
        manifest.setReceivedSite(storageService.getStorage(params).get(0).getStorageArea());
        //设置食品名称
        manifest.setFoodName(foodService.getFoodById(manifest.getFoodId()).getFoodName());

        manifest.setManifestId(UUIDUtil.getUUID(10));
        int count = manifestService.addManifest(manifest);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("据货单号获取当前要修改的货单信息")
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

    @ApiOperation("修改货单信息")
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

    @ApiOperation("删除货单")
    @RightInfo(Permission.DELETEMANIFEST)
    @PostMapping("/deleteManifest/{id}")
    @CrossOrigin
    public Object deleteManifest(@PathVariable String id){
        int count = manifestService.deleteManifest(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("企业用户获取发货人为自己的货单列表")
    @RightInfo(Permission.GETSENDMANIFEST)
    @PostMapping("/getSendManifest")
    @CrossOrigin
    public Object getSendManifest(@Param("query") String query,
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
        EnterpriseUser loginUser = (EnterpriseUser) request.getSession().getAttribute("loginUser");
        params.put("consignorId", loginUser.getId());
        List<Manifest> orderList = manifestService.getManifestBySendOrGet(params);
        if(orderList!=null){
            data.put("orderList", orderList.toArray());
            data.put("orderList", orderList.toArray());
            List<Food> foodList = foodService.getFood(null);
            data.put("foodList", foodList.toArray());
            List<Storage> siteList = storageService.getStorage(null);
            data.put("siteList", siteList.toArray());
            int total = manifestService.getManifestBySendOrGetCount(params);
            data.put("total", total);
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg, data));
    }

    @ApiOperation("企业用户获取收货人为自己的货单列表")
    @RightInfo(Permission.GETSENDMANIFEST)
    @PostMapping("/getReceiveManifest")
    @CrossOrigin
    public Object getReceiveManifest(@Param("query") String query,
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
        EnterpriseUser loginUser = (EnterpriseUser) request.getSession().getAttribute("loginUser");
        params.put("consigneeId", loginUser.getId());
        List<Manifest> orderList = manifestService.getManifestBySendOrGet(params);
        if(orderList!=null){
            data.put("orderList", orderList.toArray());
            data.put("orderList", orderList.toArray());
            List<Food> foodList = foodService.getFood(null);
            data.put("foodList", foodList.toArray());
            List<Storage> siteList = storageService.getStorage(null);
            data.put("siteList", siteList.toArray());
            int total = manifestService.getManifestBySendOrGetCount(params);
            data.put("total", total);
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg, data));
    }

    @ApiOperation("企业用户进行收货确认")
    @RightInfo(Permission.RECEIVE)
    @PostMapping("/receive/{id}")
    @CrossOrigin
    public Object receive(@PathVariable String id){
        int count = manifestService.updateReceivedStatus(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }
}
