package com.itheima.service;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public void testupdate2(){
        Customer customer = customerDao.findOne(3L);//
        customer.setCustName("华为科技研究中心7");
        //customerDao.save(customer);
    }

}
