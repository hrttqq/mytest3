package com.itheima.dao;

import com.itheima.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
    //查询全部
    @Query("from Customer")
    List<Customer> findAllCustomerJpql();
    //根据客户名字查询数据
    @Query("from Customer where custName = ?1")
    Customer findCustomerJpql(String custName);
    //根据模糊查询 查询数据
    @Query("from Customer where custName like ?1")
    List<Customer> findlikecustNameJpql(String custName);
    //多层条件查询数据
    @Query("from Customer where custIndustry = ?2 and custName like ?1")
    List<Customer> findcustIndustryAndcustName(String custName,String custIndustry);
    //条件查询+分页
    @Query("from Customer where custName like ?1 order by ?2")
    List<Customer> findcustNameListJpql(String custName , String colunm ,Pageable pageable);
    //根据id修改数据库数据
    @Query("update Customer set custName = ?1 where custId = ?2")
    @Modifying
    void updatecustName(String custName,Long custId);
    /**************************sql语句********************************************/
    /**
     *  *  Query : 配置sql查询
     *  *      value ： sql语句
     *  *      nativeQuery ： 查询方式
     *  *          true ： sql查询
     *  *          false：jpql查询
     *  */
    //查询全部
    @Query(value = "select * from cst_customer",nativeQuery = true)
    List<Object[]> findAllCustomerSql();
    //条件查询
    @Query(value = "select * from cst_customer where cust_name = ?1",nativeQuery = true)
    Customer findcustNameSql(String custName);
    //条件查询模糊查询
    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
    List<Object[]> findlikecustNameSql(String custName);
    //多条件查询
    @Query(value = "select * from cst_customer where cust_name like ?1 and cust_industry = ?2",nativeQuery = true)
    List<Customer> findcustNameandcustIndustrySql(String custName,String custIndustry);
    //分页查询
    @Query(value = "select * from cst_customer where cust_name like ?1 limit ?2,?3",nativeQuery = true)
    List<Customer> findcustNamePageSql(String custName,int rows,int size);
    @Query(value = "select * from cst_customer where cust_name like ?1 limit ?2,?3",nativeQuery = true)
    List<Object[]> findcustNamePageArraySql(String custName,int rows,int size);
    /************************************命名规则查询******************************************************************/
    public List<Customer> findByCustName(String custName);
    public List<Customer> findByCustNameLike(String custName);
    public List<Customer> findByCustNameLikeAndCustIndustry(String custName,String custIndustry);
    public Page<Customer> findByCustNameLike(String custName,Pageable pageable);
}
