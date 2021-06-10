package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AddEnterpriseMeta;
import com.zdk.dto.AddUserMeta;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.Role;
import com.zdk.service.admin.AdminServiceImpl;
import com.zdk.service.role.RoleServiceImpl;
import com.zdk.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "管理员api")
@RestController
public class AdminController {
    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminServiceImpl adminService;

    @Autowired
    @Qualifier("RoleServiceImpl")
    private RoleServiceImpl roleService;
    
    @Autowired
    private JudgeLoginUtil judgeLoginUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @ApiOperation("管理员登录")
    @PostMapping("/adminLogin")
    @CrossOrigin
    public Object login(String id, String password,HttpServletRequest request){
        AdminAndUser admin= adminService.adminLogin(id, null);
        System.out.println("admin"+admin);
        return judgeLoginUtil.judgeLogin(admin, id, password, request);
    }

    @ApiOperation("管理员列表")
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
        List<Role> contributeList = roleService.getRoles(null);
        data.put("users",JSON.toJSON(result.toArray()));
        data.put("contributeList",JSON.toJSON(contributeList.toArray()));
        msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        return JSON.toJSONString(new Meta(msg,data));
    }

    @ApiOperation("删除管理员")
    @RightInfo(Permission.REMOVEADMIN)
    @DeleteMapping("/users/{id}")
    @CrossOrigin
    public Object removeAdmin(@PathVariable String id){
        int count = adminService.removeAdmin(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("添加管理员")
    @RightInfo(Permission.ADDADMIN)
    @PostMapping("/addAdmin")
    @CrossOrigin
    public Object addAdmin(AddUserMeta admin){
        admin.setId(UUIDUtil.getUUID(7));
        admin.setPwd(passwordEncoder.encode(admin.getPwd()));
        int count = adminService.addAdmin(UserConvert.getAddUser(admin, "管理员"));
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("判断当前用户是否有权限分配")
    @PostMapping("/jurisdiction")
    @RightInfo()
    public Object jurisdiction(HttpServletRequest request){
        Map msg = new HashMap<>();
        String role = (String) request.getSession().getAttribute("role");
        if(role.equals(ReturnMessage.SUPERADMIN)){
            msg.put("status", "200");
        }else{
            msg.put("status", "403");
        }
        return JSON.toJSONString(new Meta(msg, null));
    }


    @ApiOperation("给管理员分配详细角色")
    @RightInfo(Permission.ASSIGNROLEFORADMIN)
    @PostMapping("/contribute/{userid}")
    @CrossOrigin
    public Object assignRoleForAdmin(@PathVariable String userid,String roleName){
        Map params = new HashMap<>();
        params.put("id", userid);
        params.put("role", roleService.getRoles(Integer.valueOf(roleName)).get(0).getRoleName());
        params.put("roleId", roleName);
        System.out.println("收到的参数"+params);
        int count = adminService.assignRoleForAdmin(params);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }
}
