package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AddEnterpriseMeta;
import com.zdk.dto.EditMeta;
import com.zdk.dto.EnterpriseMeta;
import com.zdk.dto.Meta;
import com.zdk.pojo.EnterpriseUser;
import com.zdk.service.enterprise.EnterpriseServiceImpl;
import com.zdk.utils.LoginMessage;
import com.zdk.utils.UUIDUtil;
import com.zdk.utils.UserConvert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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
        EnterpriseUser result= enterpriseService.enterpriseLogin(id, password);
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

    @DeleteMapping("/enterpriseUsers/{id}")
    @CrossOrigin
    public Object removeEnterprise(@PathVariable String id){
        int count = enterpriseService.removeEnterprise(id);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        data.put("pagenum",2);
        int enterpriseTotalPage= enterpriseService.enterpriseTotalPage();
        data.put("total",enterpriseTotalPage);
        List<EnterpriseMeta> result = enterpriseService.getEnterpriseList(0,2);
        data.put("users",JSON.toJSON(result.toArray()));
        if(count>0){
            msg.put("msg", "获取成功");
            msg.put("status", "200");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }else {
            msg.put("msg", "获取失败");
            msg.put("status", "201");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }
    }

    @PostMapping("/addEnterpriseUsers")
    @CrossOrigin
    public Object addEnterprise(AddEnterpriseMeta enterpriseUser) {

        //BeanUtils.copyProperties(, );

        System.out.println("传来的参数对象为："+enterpriseUser);
        enterpriseUser.setId(UUIDUtil.getUUID(6));
        int count = enterpriseService.addEnterprise(UserConvert.getAddUser(enterpriseUser, "企业用户"));
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        data.put("pagenum",2);
        int enterpriseTotalPage= enterpriseService.enterpriseTotalPage();
        data.put("total",enterpriseTotalPage);
        List<EnterpriseMeta> result = enterpriseService.getEnterpriseList(0,2);
        data.put("users",JSON.toJSON(result.toArray()));
        if(count>0){
            msg.put("msg", "获取成功");
            msg.put("status", "200");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }else {
            msg.put("msg", "获取失败");
            msg.put("status", "201");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }
    }

    @GetMapping("/showEditEnterpriseUsers/{id}")
    @CrossOrigin
    public Object showEnterpriseUsers(@PathVariable String id){
        EditMeta editMeta = enterpriseService.showEnterprise(id);
        HashMap msg = new HashMap<>();
        HashMap data = new HashMap<>();
        if(editMeta!=null){
            msg.put("status", "200");
            data.put("username", editMeta.getUsername());
            data.put("tel", editMeta.getTel());
            data.put("email", editMeta.getEmail());
            data.put("enterpriseName", editMeta.getEnterpriseName());

        }else{
            msg.put("status", "201");
        }
        Meta meta = new Meta(msg, data);
        return JSON.toJSONString(meta);
    }

    @PutMapping("/editEnterpriseUsers/{id}")
    @CrossOrigin
    public Object editEnterpriseUsers(@PathVariable String id,EditMeta user){
        System.out.println("接收到的user"+user);
        //int count = enterpriseService.modifyEnterpriseUser(user);
//        HashMap msg = new HashMap<>();
//        if(count>0){
//            msg.put("status", "200");
//            Meta meta = new Meta(msg, null);
//        }else {
//            msg.put("status", "201");
//        }
//        Meta meta = new Meta(msg, null);
        //return JSON.toJSONString(meta);
        return null;
    }
}
