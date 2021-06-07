package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AddUserMeta;
import com.zdk.dto.AdminMeta;
import com.zdk.dto.EditMeta;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.Right;
import com.zdk.pojo.Role;
import com.zdk.service.right.RightService;
import com.zdk.service.role.RoleServiceImpl;
import com.zdk.service.user.UserService;
import com.zdk.utils.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @Autowired
    private PutInfoSession putInfoSession;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/primaryLogin")
    @CrossOrigin
    public Object login(String id, String password, String email, HttpServletRequest request){
        AdminAndUser result= userService.login(id, null,email);
        if(result!=null){
            if(passwordEncoder.matches(password, result.getPwd())){
                userService.updateLoginInfo(id, DateConversion.getNowDate());
                putInfoSession.putInfoSession(result, request);
                return JSON.toJSONString(CommonMessage.returnMsg(result.getId()));
            }
        }
        return JSON.toJSONString(CommonMessage.returnMsg(null));
    }

    @RightInfo(Permission.PRIMARYLIST)
    @PostMapping("/PrimaryUsers")
    @CrossOrigin
    public Object userList(@Nullable String query, @Param("pagenum") Integer pagenum, @Param("pagesize") Integer pagesize){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        int userTotalPage= userService.userTotalPage(query);
        data.put("total",userTotalPage);
        data.put("pagenum",pagenum);
        List<AdminMeta> result;
        if(query==null){
            result = userService.getUserList((pagenum - 1) * pagesize, pagesize);
        }else {
            result = userService.fuzzyQueryUserList(query, (pagenum - 1) * pagesize, pagesize);
        }
        data.put("users",JSON.toJSON(result.toArray()));
        msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo(Permission.REMOVEPRIMARYUSERS)
    @DeleteMapping("/PrimaryUsers/{id}")
    @CrossOrigin
    public Object removePrimaryUsers(@PathVariable String id){
        int count = userService.removeUser(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.ADDUSER)
    @PostMapping("/addUsers")
    @CrossOrigin
    public Object addUser(AddUserMeta user) {
        user.setId(UUIDUtil.getUUID(5));
        int count = userService.addUser(UserConvert.getAddUser(user, "普通用户"));
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.SHOWEDITPRIMARYUSERS)
    @GetMapping("/showEditPrimaryUsers/{id}")
    @CrossOrigin
    public Object showEditPrimaryUsers(@PathVariable String id){
        EditMeta editMeta = userService.showPrimaryUser(id);
        HashMap msg = new HashMap<>();
        HashMap data = new HashMap<>();
        if(editMeta!=null){
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            data.put("id", id);
            data.put("username", editMeta.getUsername());
            data.put("tel", editMeta.getTel());
            data.put("email", editMeta.getEmail());
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo(Permission.EDITPRIMARYUSERS)
    @PostMapping("/editPrimaryUsers/{id}")
    @CrossOrigin
    public Object editPrimaryUsers(EditMeta user){
        int count = userService.modifyPrimaryUser(user);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo("")
    @PostMapping("/primaryRegister")
    @CrossOrigin
    public Object primaryRegister(AddUserMeta user){
        user.setId(UUIDUtil.getUUID(5));
        int count = userService.addUser(UserConvert.getAddUser(user, "普通用户"));
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo("")
    @PostMapping("/primaryPwdChange")

    @CrossOrigin
    public Object primaryPwdChange(AddUserMeta user){
        if(userService.login(user.getId(), null,user.getEmail())!=null){
            int count = userService.modifyUserPwd(user);
            return JSON.toJSONString(CommonMessage.returnStatus(count>0));
        }
        return null;
    }
}
