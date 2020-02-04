package com.example.demo.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

//默认错误属性处理
@Component
public class DomainValueErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String,Object> map= super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String,Object> legacy= (Map<String, Object>) webRequest.getAttribute("legacy",0);
        if(legacy!=null){
            return legacy;
        }
        return map;
    }
}
