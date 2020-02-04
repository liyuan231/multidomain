package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User selectByUsername(String username);

    void register(String username, String password);

    User selectUserById(Integer valueOf);
}
