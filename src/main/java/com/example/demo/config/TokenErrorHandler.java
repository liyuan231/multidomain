package com.example.demo.config;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TokenErrorHandler {
    @ExceptionHandler(value = TokenError.class)
    public String handleTokenError(Exception ex, HttpServletRequest request){
        TokenError e= (TokenError) ex;
        Map<String,Object> map=new HashMap<>();
        map.put("message",e.getMessage());
        map.put("code",e.getCode());
        request.setAttribute("legacy",map);
        request.setAttribute("javax.servlet.error.status_code",500);
        return "forward:/error";
    }
}
