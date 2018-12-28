package com.itheima.test;

import com.itheima.SpringbootJpaApplication;
import com.itheima.dao.UserRepository;
import com.itheima.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class UserTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    @Transactional
    public void test(){
        List<User> userListDate = (List<User>) redisTemplate.boundHashOps("User").get("findAll");
        if (userListDate==null){
            userListDate = userRepository.findAll();
            System.out.println("从数据库获取数据");
            redisTemplate.boundHashOps("User").put("findAll",userListDate);
        }else {
            System.out.println("从缓存库获取数据");
        }
        System.out.println(userListDate);
    }
    @Test
    public void test01(){
        List<User> list = userRepository.findAll();
        System.out.println(list);
    }
}
