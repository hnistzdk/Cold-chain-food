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
import com.zdk.utils.Permission;
import com.zdk.utils.ReturnMessage;
import com.zdk.utils.SendEmail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/20 16:01
 */

@SuppressWarnings("all")
@Api(tags = "公共api")
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

    @ApiOperation("获取食品类别信息")
    @PostMapping("/sendCode/{email}")
    @CrossOrigin
    public Object getEmailCode(@PathVariable String email){
        System.out.println(email);
        String coed= SendEmail.sendEmail(email);
        return JSON.toJSONString(coed);
    }

    @ApiOperation("修改密码")
    @RightInfo("")
    @PostMapping("/changePwd")
    @CrossOrigin
    public Object changePwd(String password, HttpServletRequest request){
        AdminAndUser loginUser = (AdminAndUser) request.getSession().getAttribute("loginUser");
        String role = (String) request.getSession().getAttribute("role");
        int result=0;
        if(role.equals(ReturnMessage.PRIMARY)){
            AddUserMeta addUserMeta = new AddUserMeta();
            addUserMeta.setId(loginUser.getId());
            addUserMeta.setPwd(passwordEncoder.encode(password));
            result = userService.modifyUserPwd(addUserMeta);
        }else if(role.equals(ReturnMessage.ENTERPRISE)){
            AddEnterpriseMeta addEnterpriseMeta = new AddEnterpriseMeta();
            addEnterpriseMeta.setId(loginUser.getId());
            addEnterpriseMeta.setPwd(passwordEncoder.encode(password));
            enterpriseService.modifyEnterprisePwd(addEnterpriseMeta);
        }else if(role.contains(ReturnMessage.ADMIN)){
            result = adminService.modifyPassword(loginUser.getId(), passwordEncoder.encode(password));
        }
        return CommonMessage.returnStatus(result>0);
    }

    @ApiOperation("获取当前登录用户个人信息")
    @RightInfo("")
    @GetMapping("/userInfo/{id}")
    @CrossOrigin
    public Object userInfo(@PathVariable String id,HttpServletRequest request){
        HashMap<Object, Object> data = new HashMap<>();
        HashMap<Object, Object> msg = new HashMap<>();
        String role = (String) request.getSession().getAttribute("role");
        if(role.equals(ReturnMessage.PRIMARY)){
            AdminAndUser primaryUser = userService.login(id, null, null);
            if(primaryUser!=null){
                data.put("userInfo", primaryUser);
                msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            }else{
                msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
            }
        }else if(role.equals(ReturnMessage.ENTERPRISE)){
            EnterpriseUser enterpriseUser = enterpriseService.enterpriseLogin(id, null, null);
            if(enterpriseUser!=null){
                data.put("userInfo", enterpriseUser);
                msg.put(ReturnMessage.STATUS, ReturnMessage.SUCCESS);
            }else{
                msg.put(ReturnMessage.STATUS, ReturnMessage.ERROR);
            }
        }else if(role.contains(ReturnMessage.ADMIN)){
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

    @ApiOperation("编辑修改当前登录用户信息")
    @RightInfo("")
    @PostMapping("/editUserInfo/{id}")
    @CrossOrigin
    public Object editUserInfo(EditMeta user,HttpServletRequest request){
        System.out.println("user:"+user);
        String role = (String) request.getSession().getAttribute("role");
        int count=0;
        if(role.equals(ReturnMessage.PRIMARY)){
            count=userService.modifyPrimaryUser(user);
        }else if(role.equals(ReturnMessage.ENTERPRISE)){
            count=enterpriseService.modifyEnterpriseUser(user);
        }else if(role.contains(ReturnMessage.ADMIN)){
            count = adminService.editUserInfo(user);
        }
//        AdminAndUser xxx = (AdminAndUser) request.getSession().getAttribute(user.getId());
//        AdminAndUser admin= adminService.adminLogin(user.getId(), xxx.getPwd());
//        request.getSession().setAttribute("admin", admin);
//        request.getSession().setAttribute(admin.getId(), admin);
//        request.getSession().setAttribute("loginUser", admin);
        return JSON.toJSONString(CommonMessage.returnStatus(count>0));
    }

    @ApiOperation("修改用户锁定状态")
    @RightInfo(Permission.CHANGESTATE)
    @PutMapping("/changeState/{id}/state/{mg_state}/{role}")
    @CrossOrigin
    public Object modifyState(@PathVariable String id,
                              @PathVariable Boolean mg_state,
                              @PathVariable String role,
                              HttpServletRequest request){
        int result=0;
        if(role.equals(ReturnMessage.PRIMARY)){
            result=userService.updateStatePrimary(id, mg_state);
        }else if(role.equals(ReturnMessage.ENTERPRISE)){
            result=enterpriseService.updateStateEnterprise(id, mg_state);
        }else if(role.contains(ReturnMessage.ADMIN)){
            result=adminService.updateStateAdmin(id, mg_state);
        }
        return JSON.toJSONString(CommonMessage.returnStatus(result>0));
    }

    @ApiOperation("据用户角色获取菜单")
    @GetMapping("/menus")
    @CrossOrigin
    public Object getMenuList(HttpServletRequest request) throws IOException {
        String role = (String) request.getSession().getAttribute("role");
        String menu = null;
        if(role.equals(ReturnMessage.PRIMARY)){
            menu="{\n" +
                    "  \"data\": [\n" +
                    "    {\n" +
                    "      \"id\": 101,\n" +
                    "      \"authName\": \"食品管理\",\n" +
                    "      \"path\": \"goods\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 104,\n" +
                    "          \"authName\": \"食品列表\",\n" +
                    "          \"path\": \"goods\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": 1\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"id\": 121,\n" +
                    "          \"authName\": \"食品分类\",\n" +
                    "          \"path\": \"categories\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": 3\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 3\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 102,\n" +
                    "      \"authName\": \"货单管理\",\n" +
                    "      \"path\": \"orders\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 107,\n" +
                    "          \"authName\": \"货单列表\",\n" +
                    "          \"path\": \"orders\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 4\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 147,\n" +
                    "      \"authName\": \"风险监测\",\n" +
                    "      \"path\": \"risk\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 148,\n" +
                    "          \"authName\": \"食品检测\",\n" +
                    "          \"path\": \"risk\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"id\": 149,\n" +
                    "          \"authName\": \"站点管理\",\n" +
                    "          \"path\": \"storage\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 6\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 150,\n" +
                    "      \"authName\": \"个人中心\",\n" +
                    "      \"path\": \"center\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 151,\n" +
                    "          \"authName\": \"个人面板\",\n" +
                    "          \"path\": \"center\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 7\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"meta\": {\n" +
                    "    \"msg\": \"获取菜单列表成功\",\n" +
                    "    \"status\": 200\n" +
                    "  }\n" +
                    "}";
//            ClassPathResource classPathResource=new ClassPathResource("/static/json/primaryMenu.json");
//            menu = FileUtils.readFileToString(classPathResource.getFile());
        }else if(role.equals(ReturnMessage.ENTERPRISE)){
            menu="{\n" +
                    "  \"data\": [\n" +
                    "    {\n" +
                    "      \"id\": 101,\n" +
                    "      \"authName\": \"食品管理\",\n" +
                    "      \"path\": \"goods\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 104,\n" +
                    "          \"authName\": \"食品列表\",\n" +
                    "          \"path\": \"goods\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": 1\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"id\": 121,\n" +
                    "          \"authName\": \"食品分类\",\n" +
                    "          \"path\": \"categories\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": 3\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 3\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 102,\n" +
                    "      \"authName\": \"货单管理\",\n" +
                    "      \"path\": \"deliver\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 107,\n" +
                    "          \"authName\": \"发货列表\",\n" +
                    "          \"path\": \"delivery\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"id\": 200,\n" +
                    "          \"authName\": \"收货列表\",\n" +
                    "          \"path\": \"receive\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 4\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 147,\n" +
                    "      \"authName\": \"风险监测\",\n" +
                    "      \"path\": \"risk\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 148,\n" +
                    "          \"authName\": \"食品检测\",\n" +
                    "          \"path\": \"risk\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"id\": 149,\n" +
                    "          \"authName\": \"站点管理\",\n" +
                    "          \"path\": \"storage\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 6\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 150,\n" +
                    "      \"authName\": \"个人中心\",\n" +
                    "      \"path\": \"center\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 151,\n" +
                    "          \"authName\": \"个人面板\",\n" +
                    "          \"path\": \"center\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 7\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"meta\": {\n" +
                    "    \"msg\": \"获取菜单列表成功\",\n" +
                    "    \"status\": 200\n" +
                    "  }\n" +
                    "}";
//            ClassPathResource classPathResource=new ClassPathResource("/static/json/enterpriseMenu.json");
//            menu = FileUtils.readFileToString(classPathResource.getFile());
        }else if(role.contains(ReturnMessage.ADMIN)){
            menu="{\n" +
                    "  \"data\": [\n" +
                    "    {\n" +
                    "      \"id\": 125,\n" +
                    "      \"authName\": \"用户管理\",\n" +
                    "      \"path\": \"users\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 111,\n" +
                    "          \"authName\": \"管理员\",\n" +
                    "          \"path\": \"admin\",\n" +
                    "          \"children\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"id\": 112,\n" +
                    "          \"authName\": \"普通用户\",\n" +
                    "          \"path\": \"primary\",\n" +
                    "          \"children\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"id\": 113,\n" +
                    "          \"authName\": \"企业用户\",\n" +
                    "          \"path\": \"enterprise\",\n" +
                    "          \"children\": []\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 1\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 103,\n" +
                    "      \"authName\": \"权限管理\",\n" +
                    "      \"path\": \"rights\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 111,\n" +
                    "          \"authName\": \"角色列表\",\n" +
                    "          \"path\": \"roles\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"id\": 112,\n" +
                    "          \"authName\": \"权限列表\",\n" +
                    "          \"path\": \"rights\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 2\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 101,\n" +
                    "      \"authName\": \"食品管理\",\n" +
                    "      \"path\": \"goods\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 104,\n" +
                    "          \"authName\": \"食品列表\",\n" +
                    "          \"path\": \"goods\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": 1\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"id\": 121,\n" +
                    "          \"authName\": \"食品分类\",\n" +
                    "          \"path\": \"categories\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": 3\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 3\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 102,\n" +
                    "      \"authName\": \"货单管理\",\n" +
                    "      \"path\": \"orders\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 107,\n" +
                    "          \"authName\": \"货单列表\",\n" +
                    "          \"path\": \"orders\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 4\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 147,\n" +
                    "      \"authName\": \"风险监测\",\n" +
                    "      \"path\": \"risk\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 148,\n" +
                    "          \"authName\": \"食品检测\",\n" +
                    "          \"path\": \"risk\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"id\": 149,\n" +
                    "          \"authName\": \"站点管理\",\n" +
                    "          \"path\": \"storage\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 6\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 150,\n" +
                    "      \"authName\": \"个人中心\",\n" +
                    "      \"path\": \"center\",\n" +
                    "      \"children\": [\n" +
                    "        {\n" +
                    "          \"id\": 151,\n" +
                    "          \"authName\": \"个人面板\",\n" +
                    "          \"path\": \"center\",\n" +
                    "          \"children\": [],\n" +
                    "          \"order\": null\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"order\": 7\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"meta\": {\n" +
                    "    \"msg\": \"获取菜单列表成功\",\n" +
                    "    \"status\": 200\n" +
                    "  }\n" +
                    "}";
//            ClassPathResource classPathResource=new ClassPathResource("/static/json/adminMenu.json");
//            menu = FileUtils.readFileToString(classPathResource.getFile());
        }
        String result = StringEscapeUtils.unescapeJava(menu);
        return JSON.toJSON(result);
    }
}
