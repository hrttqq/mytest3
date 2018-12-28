package com.itheima.test;

import com.itheima.SpringbootMybatisApplication;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
//SpringRunner继承自SpringJUnit4ClassRunner，我们使用哪一个Spring提供的测试引擎都可以
@SpringBootTest(classes = SpringbootMybatisApplication.class)
//@SpringBootTest的属性指定的是引导类的字节码对象
public class UserTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        List<User> list = userDao.findAll();
        System.out.println(list);
    }
}
