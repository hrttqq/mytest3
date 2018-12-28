package com.springdatajpa.demo;

import com.itheima.dao.CustomerDao;
import com.itheima.dao.LinkManDao;
import com.itheima.domain.Customer;
import com.itheima.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyQueryTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;
    //一查多
    @Test
    @Transactional
    @Commit
    public void findCustomer(){
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
            for (LinkMan linkMan : customer.getLinkMans()) {
                System.out.println(linkMan);
            }
        }
    }
    //一查一
    @Test
    @Transactional
    @Commit
    public void findLinkMan(){
        LinkMan linkMan = linkManDao.findOne(11L);
        System.out.println(linkMan);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
    @Test
    @Transactional
    @Commit
    public void findLinkManSpecification(){
        Specification<LinkMan> specification = new Specification<LinkMan>() {
            public Predicate toPredicate(Root<LinkMan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> lkmGender = root.get("lkmGender");
                Predicate predicate = cb.equal(lkmGender, "男1");
                return predicate;
            }
        };
        List<LinkMan> list = linkManDao.findAll(specification);
        for (LinkMan linkMan : list) {
            System.out.println(linkMan);
        }
    }
    @Test
    @Transactional
    @Commit
    public void findLinkManSpecification2(){
        Specification<LinkMan> specification = new Specification<LinkMan>() {
            public Predicate toPredicate(Root<LinkMan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //Join代表链接查询，通过root对象获取
                //创建的过程中，第一个参数为关联对象的属性名称，第二个参数为连接查询的方式（left，inner，right）
                //JoinType.LEFT : 左外连接,JoinType.INNER：内连接,JoinType.RIGHT：右外连接
                Join<Object, Object> join = root.join("customer", JoinType.INNER);
                Predicate predicate = cb.equal(join.get("custName"), "新浪1");
                return predicate;
            }
        };
        List<LinkMan> list = linkManDao.findAll(specification);
        for (LinkMan linkMan : list) {
            System.out.println(linkMan);
        }
    }
    //@Query 注解用法
    @Test
    public void testfindQuery(){
        List<LinkMan> list = linkManDao.findAllByCustName("%新浪1%");
        for (LinkMan linkMan : list) {
            System.out.println(linkMan);
            System.out.println(linkMan.getCustomer().getCustName()+"        "+linkMan.getCustomer().getCustAddress());
        }
    }
}
