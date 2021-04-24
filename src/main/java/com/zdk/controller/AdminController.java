package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.Meta;
import com.zdk.pojo.AdminAndUser;
import com.zdk.service.admin.AdminServiceImpl;
import com.zdk.utils.LoginMessage;
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
public class AdminController {
    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminServiceImpl adminService;

    @PostMapping("/adminLogin")
    @CrossOrigin
    public Object login(String id, String password){
        int result= adminService.adminLogin(id, password);
        Meta meta = LoginMessage.returnMsg(result);
        return JSON.toJSONString(meta);
    }

    @PostMapping("/adminUsers")
    @CrossOrigin
    public Object adminList(String query,@Param("pagenum") Integer pagenum, @Param("pagesize") Integer pagesize){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        data.put("pagenum",pagenum);
        int adminTotalPage= adminService.adminTotalPage();
        data.put("total",adminTotalPage);
        if(query==null){
            List<AdminMeta> result = adminService.getAdminList((pagenum-1)*pagesize, pagesize);
            data.put("users",JSON.toJSON(result.toArray()));
            msg.put("msg", "获取成功");
            msg.put("status", "200");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }else {
            List<AdminMeta> result = adminService.fuzzyQueryAdminList(query,(pagenum-1)*pagesize, pagesize);
            data.put("users",JSON.toJSON(result.toArray()));
            msg.put("msg", "获取成功");
            msg.put("status", "200");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }
    }

    @DeleteMapping("/users/{id}")
    @CrossOrigin
    public Object removeAdmin(@PathVariable String id){
        int count = adminService.removeAdmin(id);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        data.put("pagenum",2);
        int adminTotalPage= adminService.adminTotalPage();
        data.put("total",adminTotalPage);
        List<AdminMeta> result = adminService.getAdminList(0,2);
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

    @PutMapping("/users/{id}/state/{mg_state}")
    @CrossOrigin
    public Object modifyState(@PathVariable String id,@PathVariable String mg_state){
        AdminAndUser admin = adminService.getAdminById(id);
        Meta returnMeta=new Meta();
        HashMap data=returnMeta.getData();
        HashMap meta=returnMeta.getMeta();
        data.put("id", id);
        data.put("rid", id);
        data.put("username", admin.getUsername());
        data.put("mobile", admin.getTel());
        data.put("email", admin.getEmail());
        data.put("mg_state", 0);
        meta.put("msg", "设置状态成功");
        meta.put("status", "200");
        return meta;
    }

}
