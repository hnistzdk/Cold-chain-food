package com.zdk.controller;


import com.alibaba.fastjson.JSON;
import com.zdk.pojo.Food;
import com.zdk.service.UserService;
import com.zdk.vo.Meta;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

//@Param("username") String username, @Param("password") String password
    @PostMapping("/login")
    @ResponseBody
    @CrossOrigin
    public Object login(String username, String password){
        Meta meta = new Meta();
        HashMap<String, String> map = new HashMap<>();
        if("admin".equals(username)&& "123".equals(password)){
            map.put("status","200");
        }else {
            map.put("status","404");
        }
        meta.setMeta(map);
        return JSON.toJSONString(meta);
    }
}
