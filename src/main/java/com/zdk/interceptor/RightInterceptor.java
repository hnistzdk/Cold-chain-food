package com.zdk.interceptor;

import com.zdk.pojo.AdminAndUser;
import com.zdk.pojo.Right;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zdk
 * @date 2021/5/17 19:40
 */

@Component
public class RightInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器============");
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        List<Right> rights= (List<Right>) request.getSession().getAttribute("functions");
        System.out.println("拦截器中获取的权限集合:"+request.getSession().getAttribute("functions"));
        AdminAndUser admin=(AdminAndUser)request.getSession().getAttribute("admin");
        System.out.println("拦截器中获取的用户"+admin);
        if(admin!=null&&rights!=null){
            if(handler instanceof HandlerMethod){
                HandlerMethod handlerMethod=(HandlerMethod) handler;
                String value = handlerMethod.getMethodAnnotation(RightInfo.class).value();
                System.out.println("用户想要执行的操作是:"+value);
                boolean flag=false;
                if(value.equals("")){
                    //没有权限,说明任何人都能访问
                    flag=true;
                }else{
                    for (Right right : rights) {
                        if(right.getRightName().equals(value)){
                            flag=true;
                            break;
                        }
                    }
                }
                if(flag){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
