package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AddUserMeta;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.EditMeta;
import com.zdk.dto.Meta;
import com.zdk.pojo.AdminAndUser;
import com.zdk.service.user.UserService;
import com.zdk.utils.LoginMessage;
import com.zdk.utils.UUIDUtil;
import com.zdk.utils.UserConvert;
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
        List<AdminMeta> result;
        if(query==null){
            result = userService.getUserList((pagenum - 1) * pagesize, pagesize);
        }else {
            result = userService.fuzzyQueryUserList(query, (pagenum - 1) * pagesize, pagesize);
        }
        data.put("users",JSON.toJSON(result.toArray()));
        msg.put("msg", "获取成功");
        msg.put("status", "200");
        Meta meta = new Meta(msg,data);
        return JSON.toJSONString(meta);
    }

    @DeleteMapping("/PrimaryUsers/{id}")
    @CrossOrigin
    public Object removeEnterprise(@PathVariable String id){
        int count = userService.removeUser(id);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        if(count>0){
            msg.put("msg", "获取成功");
            msg.put("status", "200");
        }else {
            msg.put("msg", "获取失败");
            msg.put("status", "201");
        }
        Meta meta = new Meta(msg,data);
        return JSON.toJSONString(meta);
    }

    @PostMapping("/addUsers")
    @CrossOrigin
    public Object addUser(AddUserMeta user) {
        user.setId(UUIDUtil.getUUID(5));
        int count = userService.addUser(UserConvert.getAddUser(user, "普通用户"));
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        if(count>0){
            msg.put("msg", "获取成功");
            msg.put("status", "200");
        }else {
            msg.put("msg", "获取失败");
            msg.put("status", "201");
        }
        Meta meta = new Meta(msg,data);
        return JSON.toJSONString(meta);
    }

    @GetMapping("/showEditPrimaryUsers/{id}")
    @CrossOrigin
    public Object showEnterpriseUsers(@PathVariable String id){
        EditMeta editMeta = userService.showPrimaryUser(id);
        HashMap msg = new HashMap<>();
        HashMap data = new HashMap<>();
        if(editMeta!=null){
            msg.put("status", "200");
            data.put("id", id);
            data.put("username", editMeta.getUsername());
            data.put("tel", editMeta.getTel());
            data.put("email", editMeta.getEmail());
        }else{
            msg.put("status", "201");
        }
        Meta meta = new Meta(msg, data);
        return JSON.toJSONString(meta);
    }

    @PostMapping("/editPrimaryUsers/{id}")
    @CrossOrigin
    public Object editEnterpriseUsers(EditMeta user){
        System.out.println("接收到的user:"+user);
        int count = userService.modifyPrimaryUser(user);
        HashMap msg = new HashMap<>();
        if(count>0){
            msg.put("status", "200");
        }else {
            msg.put("status", "201");
        }
        Meta meta = new Meta(msg, null);
        return JSON.toJSONString(meta);
    }
}
