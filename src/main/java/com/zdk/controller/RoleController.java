package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Role;
import com.zdk.service.role.RoleServiceImpl;
import com.zdk.utils.CommonMessage;
import com.zdk.utils.ReturnMessage;
import com.zdk.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author zdk
 * @Date 2021/5/10 17:14
 */
@CrossOrigin
@RestController
public class RoleController {
    @Autowired
    @Qualifier("RoleServiceImpl")
    private RoleServiceImpl roleService;

    @RightInfo(Permission.ROLELIST)
    @GetMapping("/roles")
    public Object getRoles(){
        List<Role> roles = roleService.getRoles(null);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        data.put("roleList", JSON.toJSON(roles.toArray()));
        msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo(Permission.ADDROLES)
    @PostMapping("/roles")
    public Object addRoles(Role role){
        int count = roleService.addRoles(role);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.SHOWROLES)
    @GetMapping("/roles/{id}")
    public Object showRoles(@PathVariable Integer id){
        List<Role> roles = roleService.getRoles(id);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        if(roles!=null){
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            data.put("role", roles.get(0));
        }else {
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo(Permission.MODIFYROLES)
    @PostMapping("/roles/{id}")
    public Object modifyRoles(Role role){
        int count = roleService.modifyRoles(role);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.DELETEROLES)
    @DeleteMapping("/roles/{id}")
    public Object deleteRoles(@PathVariable Integer id){
        int count=roleService.deleteRoles(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.ASSIGNRIGHT)
    @PostMapping("/roles/{id}/rights")
    public Object assignRight(@PathVariable Integer id,String rightList){
        System.out.println("角色id："+id);
        System.out.println("权限列表id："+rightList);
        Map params=new HashMap();
        params.put("id", id);
        params.put("rightId", rightList.substring(0, rightList.length()-1));
        int count = roleService.assignRight(params);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }
}
