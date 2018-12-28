package com.itheima.dao;

import com.itheima.domain.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinkManDao extends JpaSpecificationExecutor<LinkMan>, JpaRepository<LinkMan,Long> {
    //fetch 关键字 表示 不需要懒加载 直接查询出结果来使用
    @Query("from LinkMan lm inner join fetch lm.customer c where c.custName like ?")
    List<LinkMan> findAllByCustName(String custName);
}
