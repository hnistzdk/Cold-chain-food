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
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

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
@RestController
public class EnterpriseUserController {
    @Autowired
    @Qualifier("EnterpriseServiceImpl")
    private EnterpriseServiceImpl enterpriseService;

    @Autowired
    @Qualifier("RightServiceImpl")
    private RightService rightService;

    @Autowired
    @Qualifier("RoleServiceImpl")
    private RoleServiceImpl roleService;

    @PostMapping("/enterpriseLogin")
    @CrossOrigin
    public Object login(String id, String password, String email, HttpServletRequest request){
        EnterpriseUser enterpriseUser= enterpriseService.enterpriseLogin(id, password,email);
        enterpriseService.updateLoginInfo(id, DateConversion.getNowDate());
        request.getSession().setAttribute("admin", enterpriseUser);
        request.getSession().setAttribute(enterpriseUser.getId(), enterpriseUser);
        request.getSession().setAttribute("loginUser", enterpriseUser);
        //获取用户的角色id
        Integer roleId =enterpriseUser.getRoleId();
        //通过用户的角色id获取用户对应的角色对象
        Role role = roleService.getRoles(roleId).get(0);
        //获取该种角色的所有的权限id
        String rightId = role.getRightId();
        if(rightId!=null){
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
        }
        return JSON.toJSONString(CommonMessage.returnMsg(enterpriseUser));
    }

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
        msg.put(MyMessage.STATUS, MyMessage.SUCCESS);
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo(Permission.REMOVEENTERPRISE)
    @DeleteMapping("/enterpriseUsers/{id}")
    @CrossOrigin
    public Object removeEnterprise(@PathVariable String id) {
        int count = enterpriseService.removeEnterprise(id);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.ADDENTERPRISE)
    @PostMapping("/addEnterpriseUsers")
    @CrossOrigin
    public Object addEnterprise(AddEnterpriseMeta enterpriseUser) {
        enterpriseUser.setId(UUIDUtil.getUUID(6));
        int count = enterpriseService.addEnterprise(UserConvert.getAddUser(enterpriseUser, "企业用户"));
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo(Permission.SHOWENTERPRISEUSERS)
    @GetMapping("/showEditEnterpriseUsers/{id}")
    @CrossOrigin
    public Object showEnterpriseUsers(@PathVariable String id){
        EditMeta editMeta = enterpriseService.showEnterprise(id);
        HashMap msg = new HashMap<>();
        HashMap data = new HashMap<>();
        if(editMeta!=null){
            msg.put(MyMessage.STATUS, MyMessage.SUCCESS);
            data.put("id", id);
            data.put("username", editMeta.getUsername());
            data.put("tel", editMeta.getTel());
            data.put("email", editMeta.getEmail());
            data.put("enterpriseName", editMeta.getEnterpriseName());

        }else{
            msg.put(MyMessage.STATUS, MyMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg, data));
    }

    @RightInfo(Permission.EDITENTERPRISEUSERS)
    @PostMapping("/editEnterpriseUsers/{id}")
    @CrossOrigin
    public Object editEnterpriseUsers(EditMeta user){
        int count = enterpriseService.modifyEnterpriseUser(user);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo("")
    @PostMapping("/enterpriseRegister")
    @CrossOrigin
    public Object enterpriseRegister(AddEnterpriseMeta enterpriseUser){
        enterpriseUser.setId(UUIDUtil.getUUID(6));
        int count = enterpriseService.addEnterprise(UserConvert.getAddUser(enterpriseUser, "企业用户"));
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @RightInfo("")
    @PostMapping("/enterprisePwdChange")
    @CrossOrigin
    public Object enterprisePwdChange(AddEnterpriseMeta enterpriseUser){
        if(enterpriseService.enterpriseLogin(enterpriseUser.getId(), null,enterpriseUser.getEmail())!=null){
            int count = enterpriseService.modifyEnterprisePwd(enterpriseUser);
            return CommonMessage.returnStatus(count>0);
        }
        return null;
    }
}
