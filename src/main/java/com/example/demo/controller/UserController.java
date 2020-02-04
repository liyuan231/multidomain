package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    UserService userService;
    TokenService tokenService;
    @Resource(name = "userServiceImpl")
    public void setUserService(UserService userService){
        this.userService=userService;
    }
    @Resource(name = "tokenServiceImpl")
    public void setTokenService(TokenService tokenService){
        this.tokenService=tokenService;
    }


    @PostMapping("/login")
    public Object login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        HttpServletResponse response){
        Map<String,Object>map=new HashMap<>();
        User user =this.userService.selectByUsername(username);
        if(user!=null&&(new BCryptPasswordEncoder()).matches(password,user.getPassword())){
            String token = this.tokenService.getToken(user);
            response.addCookie(new Cookie("token",token));
            map.put("token",token);
            map.put("username",username);
            map.put("code",1);
            map.put("message","登陆成功！");
            return map;
        }else{
            map.put("message","用户名或密码错误！");
            map.put("code",-6);
            return map;
        }
    }

    @PostMapping("/register")
    public Object register(@RequestParam("username")String username,
                           @RequestParam("password")String password){
        Map<String,Object>map=new HashMap<>();
        User user=this.userService.selectByUsername(username);
        if(user!=null){
            map.put("code",-5);
            map.put("message","用户名重复！");
        }else{
            this.userService.register(username,password);
            map.put("code",1);
            map.put("message","注册成功！");
        }
        return map;
    }
}
