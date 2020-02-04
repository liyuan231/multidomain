package com.example.demo.config;

import com.example.demo.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
@Component
public class MvcConfig implements WebMvcConfigurer {
    private AuthenticationInterceptor authenticationInterceptor;
    @Resource(name = "authenticationInterceptor")
    public void setAuthenticationInterceptor(AuthenticationInterceptor authenticationInterceptor){
        this.authenticationInterceptor=authenticationInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**").excludePathPatterns("/");
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login1.html");
//    }
}
