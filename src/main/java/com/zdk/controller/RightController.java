package com.zdk.controller;

import com.alibaba.fastjson.JSON;
import com.zdk.dto.Meta;
import com.zdk.interceptor.RightInfo;
import com.zdk.pojo.Right;
import com.zdk.pojo.Role;
import com.zdk.service.right.RightService;
import com.zdk.service.role.RoleServiceImpl;
import com.zdk.utils.MyMessage;
import com.zdk.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zdk
 * @date 2021/5/17 19:21
 */
@RestController
public class RightController {
    @Autowired
    @Qualifier("RightServiceImpl")
    private RightService rightService;

    @Autowired
    @Qualifier("RoleServiceImpl")
    private RoleServiceImpl roleService;

    @RightInfo(Permission.RIGHTLIST)
    @GetMapping("/rights/list")
    @CrossOrigin
    public Object rightList(@Nullable Integer pageNum, @Nullable Integer pageSize){
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        HashMap params=new HashMap<>();
        if(pageNum!=null){
            params.put("pageNum",(pageNum-1)*pageSize);
            params.put("pageSize",pageSize);
        }
        List<Right> rights = rightService.getRights(params);
        int total=rightService.getRightsCount();
        if(rights!=null){
            msg.put(MyMessage.STATUS, MyMessage.SUCCESS);
            data.put("rightsList",rights.toArray());
            data.put("total", total);
        }else {
            msg.put(MyMessage.STATUS, MyMessage.ERROR);
        }
        return JSON.toJSONString(new Meta(msg,data));
    }

    @RightInfo(Permission.RIGHTLISTROLE)
    @GetMapping("/rights/list/{id}")
    @CrossOrigin
    public Object rightList(@PathVariable Integer id){
        System.out.println("id:"+id);
        HashMap data = new HashMap<>();
        HashMap msg = new HashMap<>();
        Role role = roleService.getRoles(id).get(0);
        String rightId=role.getRightId();
        List<Right> rightsList = rightService.getRights(null);
        if(rightId!=null){
            String[] rightsId=rightId.split(",");
            List<String> checkedList = new ArrayList<>();
            for (String s : rightsId) {
                for (Right right : rightsList) {
                    if(Integer.parseInt(s)==right.getId()){
                        right.setChecked(true);
                        checkedList.add(right.getRightName());break;
                    }
                }
            }
            data.put("rightsList", rightsList.toArray());
            data.put("checkedList", checkedList.toArray());
            System.out.println("checkedList.toArray()"+checkedList.toArray().toString());
        }else {
            data.put("rightsList", rightsList.toArray());
            data.put("checkedList", new ArrayList<String>());
        }
        msg.put(MyMessage.STATUS, MyMessage.SUCCESS);
        return JSON.toJSONString(new Meta(msg,data));
    }

}
