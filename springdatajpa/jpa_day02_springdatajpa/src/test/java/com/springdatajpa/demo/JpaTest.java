package com.springdatajpa.demo;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpaTest {
    @Autowired
    private CustomerDao customerDao;

    //根据id查询数据库
    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(2L);
        System.out.println(customer);
    }
    //保存
    @Test
    public void testsave(){
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setCustName("华为集团"+i);
            customer.setCustAddress("深圳南山"+i);
            customer.setCustIndustry("计算机硬件"+i);
            customer.setCustLevel("VIP客户"+i);
            customer.setCustPhone("0755-2332212"+i);
            customer.setCustSource("互联网"+i);
            customerDao.save(customer);
        }
    }
    //更新数据库方式一
    @Test
    public void testupdate1(){
        Customer customer = new Customer();
        customer.setCustId(2L);
        customer.setCustName("华为集团");
        customer.setCustAddress("深圳南山");
        customer.setCustIndustry("计算机硬件");
        customer.setCustLevel("VIP客户");
        customer.setCustPhone("0755-23322123");
        customer.setCustSource("互联网");
        customerDao.save(customer);
    }

    @Autowired
    private CustomerService customerService;
    //更新数据库方式二
    @Test
    public void test(){
        //customerService.testupdate2();
        testupdate2();
    }

    @Transactional
    @Commit
    public void testupdate2(){
        Customer customer = customerDao.findOne(3L);
        customer.setCustName("华为科技研究中心6");
        //customerDao.save(customer);
    }
    //删除数据库数据
    @Test
    public void testdelete(){
        customerDao.delete(2L);
    }
    //查询全部
    @Test
    public void findAll(){
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    //查询全部（排序）
    @Test
    public void testfindAllSort(){
        //DESC降序 ASC升序
        List<Customer> list = customerDao.findAll(new Sort(Sort.Direction.DESC,"custId"));
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    //查询全部（排序和分页）
    @Test
    public void testFindAllPage(){
        Pageable pageable = new PageRequest(0,2,new Sort(Sort.Direction.DESC,"custId"));
        Page<Customer> page = customerDao.findAll(pageable);
        System.out.println("一共有"+page.getTotalElements()+"条数据");
        System.out.println("共分了"+page.getTotalPages()+"页");
        for (Customer customer : page.getContent()) {
            System.out.println(customer);
        }
    }
    //统计总条数
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println("共有"+count+"条数据");
    }
    //判断该数据是否存在
    @Test
    public void testExists(){
        boolean exists = customerDao.exists(3L);
        System.out.println("id为2的客户数据是否存在"+exists);
    }
    //根据id查询数据库 延迟加载方式
    @Test
    @Transactional
    public void testGetOne(){
        Customer customer = customerDao.getOne(3L);
        System.out.println(customer.getCustName());
        System.out.println(customer);
    }
    //根据配置@Query注解 jpql语句
    @Test
    public void testfindAllCustomer(){
        List<Customer> list = customerDao.findAllCustomerJpql();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    //根据客户名查询客户信息
    @Test
    public void testfindCustomer(){
        Customer customer = customerDao.findCustomerJpql("华为集团0");
        System.out.println(customer);
    }
    //根据名字 模糊查询
    @Test
    public void testfindlikecustName(){
        List<Customer> list = customerDao.findlikecustNameJpql("%华为%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    //多层条件查询
    @Test
    public void testfindcustIndustryAndcustName(){
        List<Customer> list = customerDao.findcustIndustryAndcustName("%华为%", "计算机硬件0");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    //模糊查询+分页
    @Test
    public void testfindcustNameListPage(){
        //,new Sort(Sort.Direction.DESC,"custId")
        Pageable pageable = new PageRequest(0,2);
        List<Customer> list = customerDao.findcustNameListJpql("%华为%", "custId" , pageable);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    //根据id修改数据库
    @Test
    @Transactional
//    @Commit
    @Rollback(false) //设置事务不会滚
    public void testupdatecustName(){
        customerDao.updatecustName("华为总部1",3L);
    }
    /**************************sql语句********************************************/
    @Test
    public void findAllCustomerSql(){
        List<Object[]> customers = customerDao.findAllCustomerSql();
        /*for (Customer customer : customers) {
            System.out.println(customer);
        }*/
        for (Object[] customer : customers) {
            System.out.println(Arrays.toString(customer));
        }
    }
    //条件查询
    @Test
    public void testfindcustNameSql(){
        Customer customer = customerDao.findcustNameSql("华为集团9");
        System.out.println(customer);
    }
    //条件模糊查询
    @Test
    public void testfindlikecustNameSql(){
        List<Object[]> list = customerDao.findlikecustNameSql("%华为%");
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }
    //多条件查询
    @Test
    public void testfindcustNameandcustIndustrySql(){
        List<Customer> list = customerDao.findcustNameandcustIndustrySql("%华为%", "计算机硬件9");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    //分页查询
    @Test
    public void findcustNamePageSql(){
        List<Customer> list = customerDao.findcustNamePageSql("%华为%", 0, 2);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void findcustNamePageArraySql(){
        List<Object[]> list = customerDao.findcustNamePageArraySql("%华为%", 0, 3);
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }
    /**************************************命名规格查询***********************************************************************/
    @Test
    public void findByCustName(){
        List<Customer> list = customerDao.findByCustName("华为集团1");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void findByCustNameLike(){
        List<Customer> list = customerDao.findByCustNameLike("%华为%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void findByCustNameLikeAndCustIndustry(){
        List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("%华为%", "计算机硬件3");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void findByCustNameList(){
        Pageable pageable = new PageRequest(0,2);
        Page<Customer> page = customerDao.findByCustNameLike("%华为%", pageable);
        System.out.println("总页数："+page.getTotalPages());
        System.out.println("总记录数："+page.getTotalElements());
        for (Customer customer : page.getContent()) {
            System.out.println(customer);
        }
    }
}
