package com.zdk.utils;

import com.alibaba.fastjson.JSON;
import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.EnterpriseUser;
import com.zdk.service.admin.AdminServiceImpl;
import com.zdk.service.enterprise.EnterpriseServiceImpl;
import com.zdk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zdk
 * @date 2021/6/9 16:59
 * 判断返回登录状态的工具类
 */
@Component
public class JudgeLoginUtil {
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
    private PutInfoSession putInfoSession;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String judgeLogin(AdminAndUser adminAndUser, String id, String password, HttpServletRequest request){
        if(adminAndUser!=null){
            if(!adminAndUser.getState()){
                return JSON.toJSONString(CommonMessage.returnMsg(ReturnMessage.ISLOCKED));
            }
            if(passwordEncoder.matches(password, adminAndUser.getPwd())){
                if(adminAndUser.getRole().equals(ReturnMessage.PRIMARY)){
                    userService.updateLoginInfo(id, DateConversion.getNowDate());
                }else if(adminAndUser.getRole().equals(ReturnMessage.ENTERPRISE)){
                    enterpriseService.updateLoginInfo(id, DateConversion.getNowDate());
                }else if(adminAndUser.getRole().contains(ReturnMessage.ADMIN)){
                    adminService.updateLoginInfo(id, DateConversion.getNowDate());
                }
                putInfoSession.putInfoSession(adminAndUser, request);
                return JSON.toJSONString(CommonMessage.returnMsg(adminAndUser.getId()));
            }
        }
        return JSON.toJSONString(CommonMessage.returnMsg(null));
    }

    public String judgeLogin(EnterpriseUser enterpriseUser, String id, String password, HttpServletRequest request){
        if(enterpriseUser!=null){
            if(!enterpriseUser.getState()){
                return JSON.toJSONString(CommonMessage.returnMsg(ReturnMessage.ISLOCKED));
            }
            if(passwordEncoder.matches(password, enterpriseUser.getPwd())){
                if(enterpriseUser.getRole().equals(ReturnMessage.PRIMARY)){
                    userService.updateLoginInfo(id, DateConversion.getNowDate());
                }else if(enterpriseUser.getRole().equals(ReturnMessage.ENTERPRISE)){
                    enterpriseService.updateLoginInfo(id, DateConversion.getNowDate());
                }else if(enterpriseUser.getRole().contains(ReturnMessage.ADMIN)){
                    adminService.updateLoginInfo(id, DateConversion.getNowDate());
                }
                putInfoSession.putInfoSession(enterpriseUser, request);
                return JSON.toJSONString(CommonMessage.returnMsg(enterpriseUser.getId()));
            }
        }
        return JSON.toJSONString(CommonMessage.returnMsg(null));
    }
}
