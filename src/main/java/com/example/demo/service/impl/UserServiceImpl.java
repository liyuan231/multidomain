package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    @Resource(name = "userDao")
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }

    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        userDao.register(username,(new BCryptPasswordEncoder()).encode(password));
    }

    @Override
    public User selectUserById(Integer id) {
        return userDao.selectUserById(id);
    }
}
