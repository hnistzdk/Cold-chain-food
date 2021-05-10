package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.utils.SendEmail;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/20 16:01
 */

@SuppressWarnings("all")
@RestController
public class CommonController {
    @PostMapping("/sendCode/{email}")
    @CrossOrigin
    public Object getEmailCode(@PathVariable String email){
        System.out.println(email);
        String coed= SendEmail.sendEmail(email);
        return JSON.toJSONString(coed);
    }

    @GetMapping("/menus")
    @CrossOrigin
    public Object getMenuList() {
        String returnMenu = "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 125,\n" +
                "            \"authName\": \"用户管理\",\n" +
                "            \"path\": \"users\",\n" +
                "            \"children\": [\n" +
                "                {\n" +
                "                    \"id\": 111,\n" +
                "                    \"authName\": \"管理员\",\n" +
                "                    \"path\": \"admin\",\n" +
                "                    \"children\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 112,\n" +
                "                    \"authName\": \"普通用户\",\n" +
                "                    \"path\": \"primary\",\n" +
                "                    \"children\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 113,\n" +
                "                    \"authName\": \"企业用户\",\n" +
                "                    \"path\": \"enterprise\",\n" +
                "                    \"children\": []\n" +
                "                }\n" +
                "            ],\n" +
                "            \"order\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 103,\n" +
                "            \"authName\": \"权限管理\",\n" +
                "            \"path\": \"rights\",\n" +
                "            \"children\": [\n" +
                "                {\n" +
                "                    \"id\": 111,\n" +
                "                    \"authName\": \"角色列表\",\n" +
                "                    \"path\": \"roles\",\n" +
                "                    \"children\": [],\n" +
                "                    \"order\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 112,\n" +
                "                    \"authName\": \"权限列表\",\n" +
                "                    \"path\": \"rights\",\n" +
                "                    \"children\": [],\n" +
                "                    \"order\": null\n" +
                "                }\n" +
                "            ],\n" +
                "            \"order\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"authName\": \"商品管理\",\n" +
                "            \"path\": \"goods\",\n" +
                "            \"children\": [\n" +
                "                {\n" +
                "                    \"id\": 104,\n" +
                "                    \"authName\": \"商品列表\",\n" +
                "                    \"path\": \"goods\",\n" +
                "                    \"children\": [],\n" +
                "                    \"order\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 115,\n" +
                "                    \"authName\": \"分类参数\",\n" +
                "                    \"path\": \"params\",\n" +
                "                    \"children\": [],\n" +
                "                    \"order\": 2\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 121,\n" +
                "                    \"authName\": \"商品分类\",\n" +
                "                    \"path\": \"categories\",\n" +
                "                    \"children\": [],\n" +
                "                    \"order\": 3\n" +
                "                }\n" +
                "            ],\n" +
                "            \"order\": 3\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 102,\n" +
                "            \"authName\": \"订单管理\",\n" +
                "            \"path\": \"orders\",\n" +
                "            \"children\": [\n" +
                "                {\n" +
                "                    \"id\": 107,\n" +
                "                    \"authName\": \"订单列表\",\n" +
                "                    \"path\": \"orders\",\n" +
                "                    \"children\": [],\n" +
                "                    \"order\": null\n" +
                "                }\n" +
                "            ],\n" +
                "            \"order\": 4\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 145,\n" +
                "            \"authName\": \"数据统计\",\n" +
                "            \"path\": \"reports\",\n" +
                "            \"children\": [\n" +
                "                {\n" +
                "                    \"id\": 146,\n" +
                "                    \"authName\": \"数据报表\",\n" +
                "                    \"path\": \"reports\",\n" +
                "                    \"children\": [],\n" +
                "                    \"order\": null\n" +
                "                }\n" +
                "            ],\n" +
                "            \"order\": 5\n" +
                "        }\n" +
                "    ],\n" +
                "    \"meta\": {\n" +
                "        \"msg\": \"获取菜单列表成功\",\n" +
                "        \"status\": 200\n" +
                "    }\n" +
                "}";
        String result = StringEscapeUtils.unescapeJava(returnMenu);
        return JSON.toJSON(result);
    }
}
