package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Right;
import com.zdk.service.right.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RightInfo("rightList")
    @PostMapping("/rightTest")
    @CrossOrigin
    public Object rightTest(){
        List<Right> rights = rightService.getRights(null);
        return JSON.toJSONString(rights.toArray());
    }
}
