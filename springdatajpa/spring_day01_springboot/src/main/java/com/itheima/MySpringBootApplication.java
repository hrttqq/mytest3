package com.itheima;

import org.springframework.boot.SpringApplication;

//声明该类是springboot引导类
//该注解表示该类为springboot的启动类
//@SpringBootApplication
public class MySpringBootApplication {
    //main是java程序的入口
    public static void main(String[] args) {
        //run方法 表示运行springboot的引导类 run参数就是springboot引导类的字节码对象
        SpringApplication.run(BootBean.class);
    }
}
