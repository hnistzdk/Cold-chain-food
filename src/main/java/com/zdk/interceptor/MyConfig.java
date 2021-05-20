package com.zdk.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zdk
 * @date 2021/5/17 21:28
 */

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RightInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
//                .allowedOrigins("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedHeaders("Access-Control-Allow-Headers", "X-Requested-With")
                .allowedHeaders("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS")
                .allowedHeaders("Content-Type", "application/json;charset=utf-8")
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                .maxAge(3600);
                //一小时内不用再预先检测(发送OPTIONS请求)
    }
}
