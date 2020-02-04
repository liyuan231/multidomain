package com.example.demo.service;

import com.example.demo.model.User;

public interface TokenService {
    String getToken(User user);
}
