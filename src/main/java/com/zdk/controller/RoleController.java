package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Role;
import com.zdk.service.role.RoleServiceImpl;
import com.zdk.utils.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/5/10 17:14
 */
@RestController
public class RoleController {
    @Autowired
    @Qualifier("RoleServiceImpl")
    private RoleServiceImpl roleService;

    @RightInfo("roleList")
    @GetMapping("/roles")
    @CrossOrigin
    public Object getRoles(){
        List<Role> roles = roleService.getRoles(null);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        data.put("roleList", JSON.toJSON(roles.toArray()));
        msg.put("status", "200");
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo("addRoles")
    @PostMapping("/roles")
    @CrossOrigin
    public Object addRoles(Role role){
        int count = roleService.addRoles(role);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo("showRoles")
    @GetMapping("/roles/{id}")
    @CrossOrigin
    public Object showRoles(@PathVariable Integer id){
        List<Role> roles = roleService.getRoles(id);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        if(roles!=null){
            msg.put("status", "200");
            data.put("role", roles.get(0));
        }else {
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo("modifyRoles")
    @PostMapping("/roles/{id}")
    @CrossOrigin
    public Object modifyRoles(Role role){
        int count = roleService.modifyRoles(role);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo("deleteRoles")
    @DeleteMapping("/roles/{id}")
    @CrossOrigin
    public Object deleteRoles(@PathVariable Integer id){
        int count=roleService.deleteRoles(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }
}
