package com.itheima.controller;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/queryUser")
    public List<User> queryUser(){
        List<User> list = userDao.findAll();
        return list;
    }

}
