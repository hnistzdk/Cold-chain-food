package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Right;
import com.zdk.pojo.Role;
import com.zdk.service.right.RightService;
import com.zdk.service.role.RoleServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author zdk
 * @date 2021/5/17 19:21
 */
@RestController
public class RightController {
    @Autowired
    @Qualifier("RightServiceImpl")
    private RightService rightService;

    @Autowired
    @Qualifier("RoleServiceImpl")
    private RoleServiceImpl roleService;

    @RightInfo("rightList")
    @GetMapping("/rights/list")
    @CrossOrigin
    public Object rightList(@Nullable Integer pageNum, @Nullable Integer pageSize){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        HashMap params=new HashMap<>();
        if(pageNum!=null){
            params.put("pageNum",(pageNum-1)*pageSize);
            params.put("pageSize",pageSize);
        }
        List<Right> rights = rightService.getRights(params);
        int total=rightService.getRightsCount();
        if(rights!=null){
            msg.put("status", "200");
            data.put("rightsList",rights.toArray());
            data.put("total", total);
        }else {
            msg.put("status", "201");
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo("rightListRole")
    @GetMapping("/rights/list/{id}")
    @CrossOrigin
    public Object rightList(@PathVariable Integer id){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        HashMap params=new HashMap<>();
        params.put("id", id);
        Role role = roleService.getRoles(id).get(0);
        String rightId=role.getRightId();
        String[] rightsId=rightId.split(",");
        List<Right> rights = rightService.getRights(params);
        for (Right right : rights) {
            for (String s : rightsId) {
                if(right.getId()==Integer.parseInt(s)){
                    right.setChecked(true);break;
                }
            }
        }
        data.put("rightsList", rights);
        msg.put("status", "200");
        return JSON.toJSONString(new Meta(msg,data));
    }

}
