package com.springdatajpa.demo;

import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Test
    @Transactional
    @Commit
    public void manyTomanysavetest(){
        //创建用户对象
        User user = new User();
        user.setUserName("小李");
        user.setUserCode("xiaoli");
        user.setUserPassword("123");
        user.setUserState("1");

        //创建角色对象
        Role role = new Role();
        role.setRoleName("Java程序员");
        role.setRoleMemo("高薪人群");

        //建立双向关联关系
        user.getRoles().add(role);// 起到中间表的维护权利（操作中间表）
        role.getUsers().add(user);// role已经放弃了维护中间表权利，此时该行代码是不起作用的

        userDao.save(user);
        roleDao.save(role);
    }
    @Test
    @Transactional
    @Commit
    public void manyTomanysavetest1(){
        User user = new User();
        user.setUserName("小李1");
        user.setUserCode("xiaoli1");
        user.setUserPassword("123");
        user.setUserState("1");

        Role role = new Role();
        role.setRoleName("Java程序员1");
        role.setRoleMemo("高薪人群1");

        //建立双向关联关系
        user.getRoles().add(role);// 起到中间表的维护权利（操作中间表）
        role.getUsers().add(user);// role已经放弃了维护中间表权利，此时该行代码是不起作用的

        userDao.save(user);
        //roleDao.save(role);
    }
    //     测试删除id为2的角色：不能删除角色，因为角色一端不能维护中间表的数据，而中间表中存在外键的关联
    //     测试删除id为2的用户：能删除用户，因为用户一端能维护中间表的数据，选择先删除中间表的关联数据，再删除用户数据
    @Test
    @Transactional
    @Commit
    public void testdeleterole(){
        Role role = roleDao.findOne(3L);
        roleDao.delete(role);
    }
    @Test
    @Transactional
    @Commit
    public void testdeleteuser(){
        User user = userDao.findOne(5L);
        userDao.delete(user);
    }
}
