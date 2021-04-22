package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.EnterpriseMeta;
import com.zdk.dto.Meta;
import com.zdk.service.enterprise.EnterpriseServiceImpl;
import com.zdk.service.user.UserService;
import com.zdk.utils.LoginMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/20 16:04
 */
@RestController
public class EnterpriseUserController {
    @Autowired
    @Qualifier("EnterpriseServiceImpl")
    private EnterpriseServiceImpl enterpriseService;

    @PostMapping("/enterpriseLogin")
    @CrossOrigin
    public Object login(String id, String password){
        int result= enterpriseService.enterpriseLogin(id, password);
        Meta meta = LoginMessage.returnMsg(result);
        return JSON.toJSONString(meta);
    }

    @PostMapping("/enterpriseUsers")
    @CrossOrigin
    public Object enterpriseList(String query, @Param("pagenum") Integer pagenum, @Param("pagesize") Integer pagesize){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        int enterpriseTotalPage=enterpriseService.enterpriseTotalPage();
        data.put("total",enterpriseTotalPage);
        data.put("pagenum",pagenum);
        if(query==null){
            List<EnterpriseMeta> result = enterpriseService.getEnterpriseList((pagenum-1)*pagesize, pagesize);
            data.put("users",JSON.toJSON(result.toArray()));
            msg.put("msg", "获取成功");
            msg.put("status", "200");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }else {
            List<EnterpriseMeta> result = enterpriseService.fuzzyQueryEnterpriseList(query,(pagenum-1)*pagesize, pagesize);
            data.put("users",JSON.toJSON(result.toArray()));
            msg.put("msg", "获取成功");
            msg.put("status", "200");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }
    }
}
