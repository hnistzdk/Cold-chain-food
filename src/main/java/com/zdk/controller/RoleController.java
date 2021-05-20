package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Role;
import com.zdk.service.role.RoleServiceImpl;
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

    @RightInfo("角色列表")
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

    @PostMapping("/roles")
    @CrossOrigin
    public Object addRoles(Role role){
        HashMap msg = new HashMap<>();
        int result = roleService.addRoles(role);
        if(result>0){
            msg.put("status", "200");
        }else{
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,null));
    }

    @GetMapping("/roles/{id}")
    @CrossOrigin
    public Object showRoles(@PathVariable Integer id){
        System.out.println("show里的id："+id);
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

    @PostMapping("/roles/{id}")
    @CrossOrigin
    public Object modifyRoles(Role role){
        System.out.println("role:"+role);
        System.out.println("id:"+role.getId());
        HashMap msg = new HashMap<>();
        int result = roleService.modifyRoles(role);
        if(result>0){
            msg.put("status", "200");
        }else{
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,null));
    }

    @DeleteMapping("/roles/{id}")
    @CrossOrigin
    public Object deleteRoles(@PathVariable Integer id){
        HashMap msg = new HashMap<>();
        int result=roleService.deleteRoles(id);
        if(result>0){
            msg.put("status", "200");
        }else{
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,null));
    }
}
