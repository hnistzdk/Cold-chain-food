package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.Meta;
import com.zdk.service.admin.AdminServiceImpl;
import com.zdk.utils.LoginMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

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
    @Qualifier("AdminServiceImpl")
    private AdminServiceImpl adminService;

    @PostMapping("/adminLogin")
    @CrossOrigin
    public Object login(String id, String password){
        int result= adminService.adminLogin(id, password);
        Meta meta = LoginMessage.returnMsg(result);
        return JSON.toJSONString(meta);
    }

    @PostMapping("/users")
    @CrossOrigin
    public Object adminList(String query,@Param("pagenum") Integer pagenum, @Param("pagesize") Integer pagesize){


        HashMap data = new HashMap<>();
        HashMap<String, String> msg = new HashMap<>();
        data.put("pagenum",String.valueOf(pagenum));
        if(query==null){
            int adminTotalPage= adminService.adminTotalPage();
            List<AdminMeta> result = adminService.getAdminList((pagenum-1)*pagesize, pagesize);
            Object[] users = result.toArray();
            data.put("totalPage",String.valueOf(adminTotalPage));
            data.put("users",JSON.toJSON(users));
            msg.put("msg", "获取成功");
            msg.put("status", "200");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }else {
            List<AdminMeta> result = adminService.fuzzyQueryAdminList(query,(pagenum-1)*pagesize, pagesize);
            Object[] users = result.toArray();
            data.put("totalPage",String.valueOf(users.length));
            data.put("users",JSON.toJSON(users));
            msg.put("msg", "获取成功");
            msg.put("status", "200");
            Meta meta = new Meta(msg,data);
            return JSON.toJSONString(meta);
        }
    }

    @PutMapping("/users/{id}/state/{mg_state}")
    @CrossOrigin
    public Object modifyState(@PathVariable String id,@PathVariable String mg_state){

        return null;
    }

}
