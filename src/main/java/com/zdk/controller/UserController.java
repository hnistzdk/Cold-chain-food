package com.zdk.controller;


import com.alibaba.fastjson.JSON;
import com.zdk.service.UserService;
import com.zdk.vo.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin
    public Object login(String id, String password){
        System.out.println("id："+id);
        Meta meta = new Meta();
        HashMap<String, String> map = new HashMap<>();
        int result= userService.login(id, password);
        System.out.println("result:"+result);
        if(result>0){
            map.put("status","200");
        }else {
            map.put("status","404");
        }
        meta.setMeta(map);
        return JSON.toJSONString(meta);
    }

    @PostMapping("/getMenuList")
    @CrossOrigin
    public Object getMenuList(){

        return null;
    }
}
