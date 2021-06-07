package com.zdk.utils;

import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.EnterpriseUser;
import com.zdk.pojo.Right;
import com.zdk.pojo.Role;
import com.zdk.service.right.RightService;
import com.zdk.service.role.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zdk
 * @date 2021/6/7 18:21
 * 将用户登录后的权限等信息放入session的工具类
 */
@Component
public class PutInfoSession {

    @Autowired
    @Qualifier("RightServiceImpl")
    private RightService rightService;

    @Autowired
    @Qualifier("RoleServiceImpl")
    private RoleServiceImpl roleService;

    public void putInfoSession(AdminAndUser adminAndUser, HttpServletRequest request){
        //根据用户id查到该用户角色,然后查到对应的权限,把权限放入session
        //权限需要在访问的不同的方法的时候不断在拦截器中判断是否具有权限,所以放入session
        request.getSession().setAttribute("admin", adminAndUser);
        request.getSession().setAttribute(adminAndUser.getId(), adminAndUser);
        request.getSession().setAttribute("loginUser", adminAndUser);
        request.getSession().setAttribute("role", adminAndUser.getRole());
        //获取用户的角色id
        Integer roleId =adminAndUser.getRoleId();
        //通过用户的角色id获取用户对应的角色对象
        Role role = roleService.getRoles(roleId).get(0);
        //获取该种角色的所有的权限id
        String rightId = role.getRightId();
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

    public void putInfoSession(EnterpriseUser enterpriseUser, HttpServletRequest request){
        request.getSession().setAttribute("admin", enterpriseUser);
        request.getSession().setAttribute(enterpriseUser.getId(), enterpriseUser);
        request.getSession().setAttribute("loginUser", enterpriseUser);
        request.getSession().setAttribute("role", enterpriseUser.getRole());
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
    }
}
