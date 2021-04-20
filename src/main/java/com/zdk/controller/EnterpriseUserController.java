package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.service.UserService;
import com.zdk.utils.LoginMessage;
import com.zdk.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/20 16:04
 */
@RestController
public class EnterpriseUserController {
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @PostMapping("/enterpriseLogin")
    @CrossOrigin
    public Object login(String id, String password){
        int result= userService.enterpriseLogin(id, password);
        Meta meta = LoginMessage.returnMsg(result);
        return JSON.toJSONString(meta);
    }
}
