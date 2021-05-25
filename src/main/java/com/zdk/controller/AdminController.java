package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.Right;
import com.zdk.pojo.Role;
import com.zdk.service.admin.AdminServiceImpl;
import com.zdk.service.right.RightService;
import com.zdk.service.role.RoleServiceImpl;
import com.zdk.utils.DateConversion;
import com.zdk.utils.CommonMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Qualifier("RightServiceImpl")
    private RightService rightService;

    @Autowired
    @Qualifier("RoleServiceImpl")
    private RoleServiceImpl roleService;

    @PostMapping("/adminLogin")
    @CrossOrigin
    public Object login(String id, String password,HttpServletRequest request){
        AdminAndUser admin= adminService.adminLogin(id, password);
        adminService.updateLoginInfo(id, DateConversion.getNowDate());
        //根据用户id查到该用户角色,然后查到对应的权限,把权限放入session
        //权限需要在访问的不同的方法的时候不断在拦截器中判断是否具有权限,所以放入session
        request.getSession().setAttribute("admin", admin);
        request.getSession().setAttribute("loginUser", admin);
        //获取用户的角色id
        Integer roleId =admin.getRoleId();
        //通过用户的角色id获取用户对应的角色对象
        Role role = roleService.getRoles(roleId).get(0);
        //获取该种角色的所有的权限id
        String rightId = role.getRightId();
        //数据库存储形式是1,2,3
        String[] rightsId=rightId.split(",");
        //获取所有的权限,与该角色所拥有的权限id进行匹配
        List<Right> rights = rightService.getRights(null);
        List<Right> functions = new ArrayList<>();
        //把用户所有的权限都添加进集合
        for (Right right : rights) {
            for(int i=0;i<rightsId.length;i++){
                if(Integer.parseInt(rightsId[i])==right.getId()){
                    functions.add(right);break;
                }
            }
        }
        //再把集合放入session
        request.getSession().setAttribute("functions", functions);

        Meta meta = CommonMessage.returnMsg(admin);
        return JSON.toJSONString(meta);
    }

    @RightInfo("adminList")
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
        msg.put("msg", "获取成功");
        msg.put("status", "200");
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo("removeAdmin")
    @DeleteMapping("/users/{id}")
    @CrossOrigin
    public Object removeAdmin(@PathVariable String id){
        int count = adminService.removeAdmin(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @PutMapping("/users/{id}/state/{mg_state}")
    @CrossOrigin
    public Object modifyState(@PathVariable String id,@PathVariable String mg_state){
        AdminAndUser admin = adminService.getAdminById(id);
        Meta returnMeta=new Meta();
        Map data=returnMeta.getData();
        Map meta=returnMeta.getMeta();
        data.put("id", id);
        data.put("rid", id);
        data.put("username", admin.getUsername());
        data.put("mobile", admin.getTel());
        data.put("email", admin.getEmail());
        data.put("mg_state", 0);
        meta.put("msg", "设置状态成功");
        meta.put("status", "200");
        return meta;
    }

}
