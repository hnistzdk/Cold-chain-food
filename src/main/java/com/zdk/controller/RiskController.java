package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Food;
import com.zdk.pojo.Risk;
import com.zdk.service.food.FoodServiceImpl;
import com.zdk.service.risk.RiskServiceImpl;
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
public class RiskController {
    @Autowired
    @Qualifier("RiskServiceImpl")
    RiskServiceImpl riskService;

    @Autowired
    @Qualifier("FoodServiceImpl")
    private FoodServiceImpl foodService;

    @RightInfo(Permission.GETRISKLIST)
    @PostMapping("/getRiskList")
    @CrossOrigin
    public Object getRiskList(@Param("query") String query, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize){
        HashMap data = new HashMap<>(2);
        HashMap msg = new HashMap<>(2);
        HashMap params=new HashMap<>(3);
        if(query.equals("")){
            query=null;
        }
        params.put("pageNum",(pageNum-1)*pageSize);
        params.put("pageSize",pageSize);
        params.put("query", query);
        List<Risk> riskList;
        if(query!=null){
            riskList = riskService.fuzzyQueryRiskFood(params);
        }else{
            riskList = riskService.getRiskList(params);
        }
        int total = riskService.getRiskCount(query);
        if(riskList!=null){
            List<Food> addFoodList = foodService.getFood(null);
            data.put("riskList",riskList.toArray());
            data.put("addFoodList",addFoodList.toArray());
            data.put("total", total);
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo(Permission.ADDRISK)
    @PostMapping("/addRisk")
    @CrossOrigin
    public Object addRisk(Risk risk){
        risk.setFoodName(foodService.getFoodById(risk.getFoodId()).getFoodName());
        int count = riskService.addRisk(risk);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.DELETERISK)
    @PostMapping("/deleteRisk/{id}")
    @CrossOrigin
    public Object deleteRisk(@PathVariable Integer id){
        int count=riskService.deleteRisk(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.SHOWRISKINFO)
    @GetMapping("/risks/{id}")
    @CrossOrigin
    public Object showRiskInfo(@PathVariable Integer id){
        System.out.println("id"+id);
        HashMap params = new HashMap<>();
        HashMap data = new HashMap<>();
        params.put("id", id);
        List<Risk> risk = riskService.getRiskList(params);
        if(risk!=null){
            data.put("data", risk.get(0));
            data.put("status", "200");
        }else{
            data.put("status", "201");
        }
        return JSON.toJSONString(data);
    }

    @RightInfo(Permission.MODIFYRISKS)
    @PostMapping("/modifyRisks")
    @CrossOrigin
    public Object modifyRisks(Risk risk){
        int count = riskService.modifyRisks(risk);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }
}
