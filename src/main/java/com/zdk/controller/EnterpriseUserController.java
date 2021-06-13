package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.AddEnterpriseMeta;
import com.zdk.dto.EditMeta;
import com.zdk.dto.EnterpriseMeta;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.EnterpriseUser;
import com.zdk.pojo.Right;
import com.zdk.pojo.Role;
import com.zdk.service.enterprise.EnterpriseServiceImpl;
import com.zdk.service.right.RightService;
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
import sun.util.resources.es.CurrencyNames_es_UY;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/20 16:04
 * BeanUtils.copyProperties(, );
 */
@Api(tags = "企业用户api")
@RestController
public class EnterpriseUserController {
    @Autowired
    @Qualifier("EnterpriseServiceImpl")
    private EnterpriseServiceImpl enterpriseService;

    @Autowired
    private JudgeLoginUtil judgeLoginUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("企业用户登录")
    @PostMapping("/enterpriseLogin")
    @CrossOrigin
    public Object login(String id, String password, String email, HttpServletRequest request){
        EnterpriseUser enterpriseUser= enterpriseService.enterpriseLogin(id, null,email);
        System.out.println("enterpriseUser"+enterpriseUser);
        return judgeLoginUtil.judgeLogin(enterpriseUser, id, password, request);
    }

    @ApiOperation("企业用户列表")
    @RightInfo(Permission.ENTERPRISELIST)
    @PostMapping("/enterpriseUsers")
    @CrossOrigin
    public Object enterpriseList(@Nullable String query, @Param("pagenum") Integer pagenum, @Param("pagesize") Integer pagesize){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        int enterpriseTotalPage=enterpriseService.enterpriseTotalPage(query);
        data.put("total",enterpriseTotalPage);
        data.put("pagenum",pagenum);
        List<EnterpriseMeta> result;
        if(query==null){
            result = enterpriseService.getEnterpriseList((pagenum - 1) * pagesize, pagesize);
        }else {
            result = enterpriseService.fuzzyQueryEnterpriseList(query, (pagenum - 1) * pagesize, pagesize);
        }
        data.put("users",JSON.toJSON(result.toArray()));
        msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
        return JSON.toJSONString(new Meta(msg,data));
    }

    @ApiOperation("删除企业用户")
    @RightInfo(Permission.REMOVEENTERPRISE)
    @DeleteMapping("/enterpriseUsers/{id}")
    @CrossOrigin
    public Object removeEnterprise(@PathVariable String id) {
        int count = enterpriseService.removeEnterprise(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("添加企业用户")
    @RightInfo(Permission.ADDENTERPRISE)
    @PostMapping("/addEnterpriseUsers")
    @CrossOrigin
    public Object addEnterprise(AddEnterpriseMeta enterpriseUser) {
        enterpriseUser.setId(UUIDUtil.getUUID(6));
        enterpriseUser.setPwd(passwordEncoder.encode(enterpriseUser.getPwd()));
        int count = enterpriseService.addEnterprise(UserConvert.getAddUser(enterpriseUser, "企业用户"));
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("展示当前要编辑的企业用户信息")
    @RightInfo(Permission.SHOWENTERPRISEUSERS)
    @GetMapping("/showEditEnterpriseUsers/{id}")
    @CrossOrigin
    public Object showEnterpriseUsers(@PathVariable String id){
        EditMeta editMeta = enterpriseService.showEnterprise(id);
        HashMap msg = new HashMap<>();
        HashMap data = new HashMap<>();
        if(editMeta!=null){
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            data.put("id", id);
            data.put("username", editMeta.getUsername());
            data.put("tel", editMeta.getTel());
            data.put("email", editMeta.getEmail());
            data.put("enterpriseName", editMeta.getEnterpriseName());

        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg, data));
    }

    @ApiOperation("修改企业用户信息")
    @RightInfo(Permission.EDITENTERPRISEUSERS)
    @PostMapping("/editEnterpriseUsers/{id}")
    @CrossOrigin
    public Object editEnterpriseUsers(EditMeta user){
        int count = enterpriseService.modifyEnterpriseUser(user);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("企业用户注册")
    @RightInfo("")
    @PostMapping("/enterpriseRegister")
    @CrossOrigin
    public Object enterpriseRegister(AddEnterpriseMeta enterpriseUser){
        HashMap msg = new HashMap<>(2);
        HashMap data = new HashMap<>(2);
        String id=UUIDUtil.getUUID(6);
        enterpriseUser.setId(id);
        enterpriseUser.setPwd(passwordEncoder.encode(enterpriseUser.getPwd()));
        int count = enterpriseService.addEnterprise(UserConvert.getAddUser(enterpriseUser, "企业用户"));
        if(count>0){
            msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            data.put("userId", id);
        }else{
            msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg, data));
    }

    @ApiOperation("企业用户修改密码")
    @RightInfo("")
    @PostMapping("/enterprisePwdChange")
    @CrossOrigin
    public Object enterprisePwdChange(AddEnterpriseMeta enterpriseUser){
        if(enterpriseService.enterpriseLogin(enterpriseUser.getId(), null,enterpriseUser.getEmail())!=null){
            enterpriseUser.setPwd(passwordEncoder.encode(enterpriseUser.getPwd()));
            int count = enterpriseService.modifyEnterprisePwd(enterpriseUser);
            return CommonMessage.returnStatus(count>0);
        }
        return CommonMessage.returnStatus(false);
    }
}
