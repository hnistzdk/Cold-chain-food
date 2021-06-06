package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Risk;
import com.zdk.service.risk.RiskServiceImpl;
import com.zdk.utils.ReturnMessage;
import com.zdk.utils.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RightInfo(Permission.GETRISKLIST)
    @PostMapping("/getRiskList")
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
        List<Risk> riskList = riskService.getRiskList(params);
        int total = riskService.getRiskCount();
        if(riskList!=null){
            data.put("riskList",riskList.toArray());
            data.put("total", total);
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg,data));
    }
}
