package com.itheima.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController1 {
    @Value("${name}")
    private String name;
    @Value("${person.name}")
    private String name1;
    @Value("${person.age}")
    private String age;
    @Value("${person.address}")
    private String address;

    @RequestMapping("/quick1")
    public String quick1(){
        return "从配置文件中获取的name值为:"+name;
    }
    @RequestMapping("/quick2")
    public String quick2(){
        return "从配置文件中获取的name值为:"+name1+",age值为"+age+",address值:"+address;
    }
}
