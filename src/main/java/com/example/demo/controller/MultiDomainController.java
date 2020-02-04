package com.example.demo.controller;

import com.example.demo.TokenAnnotation.UserLoginToken;
import com.example.demo.config.DomainValueError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestController
public class MultiDomainController {
    private static Collection<String> collection = new HashSet<>();
    static {
        collection.add("hotel");
        collection.add("resturant");
        collection.add("airline");
        collection.add("e-commerce");
    }

    @PostMapping("/multidomain")
    @UserLoginToken
    public Object multidomain(@RequestParam("domain")String domain,
                              @RequestParam("text")String text) {
//        System.out.println(domain + ":" + text);
//        System.out.println(collection);
        if(!collection.contains(domain)){
            throw new DomainValueError("Domain's value error",416);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "Success");
        map.put("sentiment", "pos / neu /neg");
        return map;
    }
}
