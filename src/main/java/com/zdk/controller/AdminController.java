package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AddEnterpriseMeta;
import com.zdk.dto.AddUserMeta;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.AdminAndUser;
import com.zdk.service.admin.AdminServiceImpl;
import com.zdk.utils.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private JudgeLoginUtil judgeLoginUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/adminLogin")
    @CrossOrigin
    public Object login(String id, String password,HttpServletRequest request){
        AdminAndUser admin= adminService.adminLogin(id, null);
        System.out.println("admin"+admin);
        return judgeLoginUtil.judgeLogin(admin, id, password, request);
    }

    @RightInfo(Permission.ADMINLIST)
    @PostMapping("/adminUsers")
    @CrossOrigin
    public Object adminList(@Nullable String query, @Param("pagenum") Integer pagenum, @Param("pagesize") Integer pagesize){
        Map data = new HashMap<>();
        Map msg = new HashMap<>();
        data.put("pagenum",pagenum);
        int adminTotalPage= adminService.adminTotalPage(query);
        data.put("total",adminTotalPage);
        List<AdminMeta> result;
        if(query==null){
            result = adminService.getAdminList((pagenum - 1) * pagesize, pagesize);
        }else {
            result = adminService.fuzzyQueryAdminList(query, (pagenum - 1) * pagesize, pagesize);
        }
        data.put("users",JSON.toJSON(result.toArray()));
        msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo(Permission.REMOVEADMIN)
    @DeleteMapping("/users/{id}")
    @CrossOrigin
    public Object removeAdmin(@PathVariable String id){
        int count = adminService.removeAdmin(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.ADDADMIN)
    @PostMapping("/addAdmin")
    @CrossOrigin
    public Object addAdmin(AddUserMeta admin){
        admin.setId(UUIDUtil.getUUID(7));
        admin.setPwd(passwordEncoder.encode(admin.getPwd()));
        int count = adminService.addAdmin(UserConvert.getAddUser(admin, "管理员"));
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }
}
