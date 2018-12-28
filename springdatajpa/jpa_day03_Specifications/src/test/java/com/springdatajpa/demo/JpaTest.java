package com.springdatajpa.demo;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpaTest {
    @Autowired
    private CustomerDao customerDao;
    @Test
    public void testfindCustName(){
        /** 自定义查询条件
         *      1.实现Specification接口（提供泛型：查询的对象类型）
         *      2.实现toPredicate方法（构造查询条件）
         *      3.需要借助方法参数中的两个参数（
         *          root：获取需要查询的对象属性
         *          CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
         **/
        //创建查询条件
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //设置要作为条件的属性名
                Path<Object> custName = root.get("custName");
                //条件的判断
                Predicate predicate = cb.equal(custName, "华为集团5");
                return predicate;
            }
        };
        //利用条件对象进行查询
        List<Customer> list = customerDao.findAll(specification);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void findcustNameAndcustAddress(){
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                //因为底层like方法的第一个参数泛型已写死了 所以这里一定转换类型
                Predicate likecustName = cb.like(custName.as(String.class), "%华为%");
                Path<Object> custAddress = root.get("custAddress");
                Predicate equalcustAddress = cb.equal(custAddress, "深圳南山3");
                //将两个已经设置好的条件用and进行条件的拼接
                Predicate predicate = cb.and(likecustName, equalcustAddress);
                return predicate;
            }
        };
        List<Customer> list = customerDao.findAll(specification);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void findlikecustName(){
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                //因为底层like方法的第一个参数泛型已写死了 所以这里一定转换类型
                Predicate predicate = cb.like(custName.as(String.class), "%华为%");
                return predicate;
            }
        };
        List<Customer> list = customerDao.findAll(specification);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void findlikecustNameOrder(){
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "%华为%");
                return predicate;
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        //在findAll中传入第二个参数 sort
        List<Customer> list = customerDao.findAll(specification, sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void findcustNameLikePageOrders(){
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "%华为%");
                return predicate;
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        Pageable page = new PageRequest(0,3,sort);
        Page<Customer> pagelist = customerDao.findAll(specification, page);
        System.out.println("一共分了"+pagelist.getTotalPages()+"页");
        System.out.println("一共有"+pagelist.getTotalElements()+"条数据");
        for (Customer customer : pagelist.getContent()) {
            System.out.println(customer);
        }
    }
}
