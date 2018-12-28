package com.itheima.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {
    @RequestMapping("/quick")
    public String quick(){
        return "欢迎来到springboot入门案例2";
    }
}
