package com.chester.webhomework4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
//    @Override
    @Autowired
    LoginInterceptor loginInterceptor;
    public void addInterceptors(InterceptorRegistry registry){
//        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor((HandlerInterceptor) loginInterceptor);
        loginRegistry.addPathPatterns("/**");
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/login");

        loginRegistry.excludePathPatterns("/css/style.css");
    }
}
