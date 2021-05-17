package com.zdk.interceptor;

import java.lang.reflect.Method;

/**
 * @author zdk
 * @date 2021/5/17 19:46
 * 解析权限注解内容工具类
 */
public class RightInfoAnnotationParse {
    public static String parse(Class targetClass,String methodName) throws NoSuchMethodException,SecurityException{
        String rightName="";
        Method method=targetClass.getMethod(methodName);
        if(method.isAnnotationPresent(RightInfo.class)){
            RightInfo rightInfo=method.getAnnotation(RightInfo.class);
            rightName=rightInfo.value();
        }
        return rightName;
    }
}
