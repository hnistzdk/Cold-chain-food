package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AddEnterpriseMeta;
import com.zdk.dto.AddUserMeta;
import com.zdk.dto.EditMeta;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.EnterpriseUser;
import com.zdk.service.admin.AdminServiceImpl;
import com.zdk.service.enterprise.EnterpriseServiceImpl;
import com.zdk.service.user.UserService;
import com.zdk.utils.CommonMessage;
import com.zdk.utils.ReturnMessage;
import com.zdk.utils.SendEmail;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/20 16:01
 */

@SuppressWarnings("all")
@RestController
public class CommonController {
    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminServiceImpl adminService;

    @Autowired
    @Qualifier("EnterpriseServiceImpl")
    private EnterpriseServiceImpl enterpriseService;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/sendCode/{email}")
    @CrossOrigin
    public Object getEmailCode(@PathVariable String email){
        System.out.println(email);
        String coed= SendEmail.sendEmail(email);
        return JSON.toJSONString(coed);
    }

    @RightInfo("")
    @PostMapping("/changePwd")
    @CrossOrigin
    public Object changePwd(String password, HttpServletRequest request){
        AdminAndUser loginUser = (AdminAndUser) request.getSession().getAttribute("loginUser");
        String role = (String) request.getSession().getAttribute("role");
        int result=0;
        if(role.equals("普通用户")){
            AddUserMeta addUserMeta = new AddUserMeta();
            addUserMeta.setId(loginUser.getId());
            addUserMeta.setPwd(passwordEncoder.encode(password));
            result = userService.modifyUserPwd(addUserMeta);
        }else if(role.equals("企业用户")){
            AddEnterpriseMeta addEnterpriseMeta = new AddEnterpriseMeta();
            addEnterpriseMeta.setId(loginUser.getId());
            addEnterpriseMeta.setPwd(passwordEncoder.encode(password));
            enterpriseService.modifyEnterprisePwd(addEnterpriseMeta);
        }else if(role.indexOf("管理员")!=-1){
            result = adminService.modifyPassword(loginUser.getId(), passwordEncoder.encode(password));
        }
        return CommonMessage.returnStatus(result>0);
    }

    @RightInfo("")
    @GetMapping("/userInfo/{id}")
    @CrossOrigin
    public Object userInfo(@PathVariable String id,HttpServletRequest request){
        HashMap<Object, Object> data = new HashMap<>();
        HashMap<Object, Object> msg = new HashMap<>();
        String role = (String) request.getSession().getAttribute("role");
        if(role.equals("普通用户")){
            AdminAndUser primaryUser = userService.login(id, null, null);
            if(primaryUser!=null){
                data.put("userInfo", primaryUser);
                msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            }else{
                msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
            }
        }else if(role.equals("企业用户")){
            EnterpriseUser enterpriseUser = enterpriseService.enterpriseLogin(id, null, null);
            if(enterpriseUser!=null){
                data.put("userInfo", enterpriseUser);
                msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            }else{
                msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
            }
        }else if(role.indexOf("管理员")!=-1){
            AdminAndUser admin = adminService.adminLogin(id, null);
            if(admin!=null){
                data.put("userInfo", admin);
                msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            }else{
                msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
            }
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo("")
    @PostMapping("/editUserInfo/{id}")
    @CrossOrigin
    public Object editUserInfo(EditMeta user,HttpServletRequest request){
        System.out.println("user:"+user);
        String role = (String) request.getSession().getAttribute("role");
        int count=0;
        if(role.equals("普通用户")){
            count=userService.modifyPrimaryUser(user);
        }else if(role.equals("企业用户")){
            count=enterpriseService.modifyEnterpriseUser(user);
        }else if(role.indexOf("管理员")!=-1){
            count = adminService.editUserInfo(user);
        }
//        AdminAndUser xxx = (AdminAndUser) request.getSession().getAttribute(user.getId());
//        AdminAndUser admin= adminService.adminLogin(user.getId(), xxx.getPwd());
//        request.getSession().setAttribute("admin", admin);
//        request.getSession().setAttribute(admin.getId(), admin);
//        request.getSession().setAttribute("loginUser", admin);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @GetMapping("/menus")
    @CrossOrigin
    public Object getMenuList(HttpServletRequest request) throws IOException {
        String role = (String) request.getSession().getAttribute("role");
        String menu = null;
        if(role.equals("普通用户")){
            File jsonFile = ResourceUtils.getFile("classpath:static/json/primaryMenu.json");
            menu = FileUtils.readFileToString(jsonFile);
        }else if(role.equals("企业用户")){
            File jsonFile = ResourceUtils.getFile("classpath:static/json/enterpriseMenu.json");
            menu = FileUtils.readFileToString(jsonFile);
        }else if(role.indexOf("管理员")!=-1){
            File jsonFile = ResourceUtils.getFile("classpath:static/json/adminMenu.json");
            menu = FileUtils.readFileToString(jsonFile);
        }
        String result = StringEscapeUtils.unescapeJava(menu);
        return JSON.toJSON(result);
    }
}
