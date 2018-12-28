package com.springdatajpa.demo;

import com.itheima.dao.CustomerDao;
import com.itheima.dao.LinkManDao;
import com.itheima.domain.Customer;
import com.itheima.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;
    @Test
    public void createTable(){
    }
    @Test
    @Transactional
    @Commit
    public void testsave1(){
        for (int i=0;i<10;i++) {
            Customer c = customerDao.findOne(27L);
            LinkMan lm = new LinkMan();
            lm.setLkmName("小张"+i);
            lm.setLkmGender("男"+i);
            lm.setLkmEmail("98354834@qq.com");
            lm.setLkmMobile("13811111111");
            lm.setLkmPhone("010-34785348");
            lm.setLkmPosition("老师"+i);
            lm.setLkmMemo("还行吧"+i);
            //保存关联的数据
            //c.getLinkMans().add(lm);
            lm.setCustomer(c);
            linkManDao.save(lm);
        }
    }
    /**
     * 如果建立了双向关联关系，两个保存操作要在同一个事务完成，否则抛出瞬时对象异常
     * */
    @Test
    @Transactional
    @Commit
    public void testsave(){
        Customer c = new Customer();
        c.setCustName("新浪");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("中粮商务大楼");
        c.setCustPhone("0755-84389340");

        LinkMan lm = new LinkMan();
        lm.setLkmName("小张");
        lm.setLkmGender("男");
        lm.setLkmEmail("98354834@qq.com");
        lm.setLkmMobile("13811111111");
        lm.setLkmPhone("010-34785348");
        lm.setLkmPosition("老师");
        lm.setLkmMemo("还行吧");
        //保存关联的数据
        //c.getLinkMans().add(lm);
        lm.setCustomer(c);

        customerDao.save(c);
        linkManDao.save(lm);
    }
    @Test
    @Transactional
    @Commit
    public void testsave2(){
        Customer c = new Customer();
        c.setCustName("新浪1");
        c.setCustLevel("VIP客户1");
        c.setCustSource("网络1");
        c.setCustIndustry("商业办公1");
        c.setCustAddress("中粮商务大楼1");
        c.setCustPhone("0755-84389340");

        LinkMan lm = new LinkMan();
        lm.setLkmName("小张1");
        lm.setLkmGender("男1");
        lm.setLkmEmail("98354834@qq.com");
        lm.setLkmMobile("13811111111");
        lm.setLkmPhone("010-34785348");
        lm.setLkmPosition("老师1");
        lm.setLkmMemo("还行吧1");
        //保存关联的数据
        c.getLinkMans().add(lm);
        lm.setCustomer(c);

        customerDao.save(c);
        //linkManDao.save(lm);
    }
    //从表的删除
    @Test
    @Transactional
    @Commit
    public void testdelete(){
        linkManDao.delete(10L);
    }
    //主表删除 关联子表删除
    //级联操作：指操作一个对象同时操作它的关联对象
    @Test
    @Transactional
    @Commit
    public void testdelete1(){
        Customer customer = customerDao.findOne(25L);
        for (LinkMan linkMan : customer.getLinkMans()) {
            linkManDao.delete(linkMan.getLkmId());
        }
        customerDao.delete(25L);
    }
    @Test
    @Transactional
    @Commit
    public void testdelete2(){
        customerDao.delete(25L);
    }
}
