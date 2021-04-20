package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.Meta;
import com.zdk.service.UserService;
import com.zdk.utils.LoginMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class AdminController {
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @PostMapping("/adminLogin")
    @CrossOrigin
    public Object login(String id, String password){
        int result= userService.adminLogin(id, password);
        Meta meta = LoginMessage.returnMsg(result);
        return JSON.toJSONString(meta);
    }

    @PostMapping("/users")
    @CrossOrigin
    public Object adminList(@Nullable String query, @Param("pagenum") Integer pagenum, @Param("pagesize") Integer pagesize){
        int adminTotalPage= userService.adminTotalPage();
        System.out.println(adminTotalPage);
        List<AdminMeta> result = userService.getAdminList(pagenum, pagesize);
        Object[] users = result.toArray();
        HashMap data = new HashMap<>();
        HashMap<String, String> msg = new HashMap<>();
        data.put("totalPage",String.valueOf(adminTotalPage));
        data.put("pagenum",String.valueOf(pagenum));
        data.put("users",JSON.toJSON(users));
        msg.put("msg", "获取成功");
        msg.put("status", "200");
        Meta meta = new Meta(msg,data);
        return JSON.toJSONString(meta);
    }
}
