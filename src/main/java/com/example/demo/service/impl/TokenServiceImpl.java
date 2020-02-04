package com.example.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.model.User;
import com.example.demo.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(User user) {
        Date start=new Date();
        long curentTime=System.currentTimeMillis()+60*30*1000;
        Date end=new Date(curentTime);
        String token= JWT.create().withAudience(new String[]{String.valueOf(user.getId())}).withIssuedAt(start).withExpiresAt(end).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
