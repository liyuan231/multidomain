package com.example.demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DoaminValueErrorHandler {
    @ExceptionHandler(value = org.springframework.web.bind.MissingServletRequestParameterException.class)
    public String handleMissingParaException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        map.put("message","Missing parameter");
        map.put("code",401);
        request.setAttribute("legacy",map);
        request.setAttribute("javax.servlet.error.status_code",400);
        return "forward:/error";
    }
    @ExceptionHandler(value = DomainValueError.class)
    public String handleDomainValueError(Exception ex,HttpServletRequest request){
        DomainValueError e = (DomainValueError) ex;
        Map<String,Object> map=new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("code",e.getCode());
        request.setAttribute("legacy",map);
        request.setAttribute("javax.servlet.error.status_code",401);
        return "forward:/error";
    }
}
