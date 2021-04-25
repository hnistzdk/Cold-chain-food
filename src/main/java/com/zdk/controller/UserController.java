package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.Meta;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.EnterpriseUser;
import com.zdk.service.user.UserService;
import com.zdk.utils.LoginMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author fengzhu
 */
@RestController
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @PostMapping("/primaryLogin")
    @CrossOrigin
    public Object login(String id, String password){
        AdminAndUser result= userService.login(id, password);
        Meta meta = LoginMessage.returnMsg(result);
        return JSON.toJSONString(meta);
    }

    @PostMapping("/PrimaryUsers")
    @CrossOrigin
    public Object userList(String query, @Param("pagenum") Integer pagenum, @Param("pagesize") Integer pagesize){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        int userTotalPage= userService.userTotalPage();
        data.put("total",userTotalPage);
        data.put("pagenum",pagenum);
        if(query==null){
            List<AdminMeta> result = userService.getUserList((pagenum-1)*pagesize, pagesize);
            data.put("users",JSON.toJSON(result.toArray()));
            msg.put("msg", "获取成功");
            msg.put("status", "200");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }else {
            List<AdminMeta> result = userService.fuzzyQueryUserList(query,(pagenum-1)*pagesize, pagesize);
            data.put("users",JSON.toJSON(result.toArray()));
            msg.put("msg", "获取成功");
            msg.put("status", "200");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }
    }

    @DeleteMapping("/PrimaryUsers/{id}")
    @CrossOrigin
    public Object removeEnterprise(@PathVariable String id){
        int count = userService.removeUser(id);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        data.put("pagenum",2);
        int userTotalPage= userService.userTotalPage();
        data.put("total",userTotalPage);
        List<AdminMeta> result = userService.getUserList(0,2);
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
}
