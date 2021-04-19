package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.MyMenu;
import com.zdk.dto.ReturnMenu;
import com.zdk.service.UserService;
import com.zdk.dto.Meta;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin
    public Object login(String id, String password){
        System.out.println("id："+id);
        Meta meta = new Meta();
        HashMap<String, String> map = new HashMap<>();
        int result= userService.login(id, password);
        System.out.println("result:"+result);
        if(result>0){
            map.put("status","200");
        }else {
            map.put("status","404");
        }
        meta.setMeta(map);
        return JSON.toJSONString(meta);
    }

    @GetMapping("/menus")
    @CrossOrigin
    public Object getMenuList(){
        String returnMenu="{\"data\":[{\"id\":125,\"authName\":\"用户管理\",\"path\":\"users\"," +
                "\"children\":[{\"id\":110,\"authName\":\"用户列表\",\"path\":\"users\"," +
                "\"children\":[],\"order\":null}],\"order\":1},{\"id\":103,\"authName\":\"权限管理\",\"path\":\"rights\"," +
                "\"children\":[{\"id\":111,\"authName\":\"角色列表\",\"path\":\"roles\"," +
                "\"children\":[],\"order\":null},{\"id\":112,\"authName\":\"权限列表\",\"path\":\"rights\"," +
                "\"children\":[],\"order\":null}],\"order\":2},{\"id\":101,\"authName\":\"商品管理\",\"path\":\"goods\"," +
                "\"children\":[{\"id\":104,\"authName\":\"商品列表\",\"path\":\"goods\"," +
                "\"children\":[],\"order\":1},{\"id\":115,\"authName\":\"分类参数\",\"path\":\"params\"," +
                "\"children\":[],\"order\":2},{\"id\":121,\"authName\":\"商品分类\",\"path\":\"categories\"," +
                "\"children\":[],\"order\":3}],\"order\":3},{\"id\":102,\"authName\":\"订单管理\",\"path\":\"orders\"," +
                "\"children\":[{\"id\":107,\"authName\":\"订单列表\",\"path\":\"orders\"," +
                "\"children\":[],\"order\":null}],\"order\":4},{\"id\":145,\"authName\":\"数据统计\",\"path\":\"reports\"," +
                "\"children\":[{\"id\":146,\"authName\":\"数据报表\",\"path\":\"reports\"," +
                "\"children\":[],\"order\":null}],\"order\":5}],\"meta\":{\"msg\":\"获取菜单列表成功\",\"status\":200}}\n";
        String result = StringEscapeUtils.unescapeJava(returnMenu);
        System.out.println(JSON.toJSON(result));
        return JSON.toJSON(result);
    }
}
