package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetPageController {
    @GetMapping("/login")
    public String login(){
        return "login1.html";
    }
    @GetMapping("/register")
    public String register(){
        System.out.println("test");
        return "signin1.html";
    }
}
