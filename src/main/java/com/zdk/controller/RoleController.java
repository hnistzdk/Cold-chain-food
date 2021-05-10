package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.pojo.Role;
import com.zdk.service.role.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/roles")
    @CrossOrigin
    public Object getRoles(){
        List<Role> roles = roleService.getRoles();
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        data.put("roleList", JSON.toJSON(roles.toArray()));
        msg.put("status", "200");
        Meta meta = new Meta(msg,data);
        return JSON.toJSONString(meta);
    }
}
