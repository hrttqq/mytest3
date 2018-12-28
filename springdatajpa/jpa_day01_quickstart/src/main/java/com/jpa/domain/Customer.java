package com.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
/**
 * 操作对象---操作数据库的（JPA注解）
 * 1、建立实体类和数据库表的映射
 * @Entity: 放置类上，表示该类是一个操作数据库实体类
 * @Table(name="cst_customer"): 放到类上，表示实体类和数据库表的映射，同时指定表的名称，如果数据库表名和实体类的名称一致，此时name属性可以省略
 * 2、建立实体类属性和数据库表中的字段映射
 * @Id: 放置主键的字段上，表示该属性对应数据库表的主键
 * @GeneratedValue(strategy=GenerationType.IDENTITY): 放置主键的字段上，表示主键生成策略
 *  GenerationType.IDENTITY:表示自增长(对应:AUTO_INCREMENT),在Mysql数据库可以使用
 *  提供了四种策略：
 *      IDENTITY:主键由数据库自动生成(主要是自动增长型)，其中mysql数据库可以支持自增长
 *      SEQUENCE:根据底层数据库的序列生成主键，条件是数据库支持序列。其中oracle数据库支持序列，mysql支持序列
 *      AUTO:主键有程序控制(默认采用sequence的形式维护)
 *      TABLE:使用一个特定的数据库表哥来保存主键
 * @Column(name="cust_id") 表示该属性对应数据库表中的字段名
 *      属性：
 *              name：指定数据库表的列名称。
 *         		unique：是否唯一
 *         		nullable：是否可以为空
 *         		inserttable：是否可以插入
 *         		updateable：是否可以更新
 *         		columnDefinition: 定义建表时创建此列的DDL
 * */
@Entity //声明实体类
@Table(name = "cst_customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_source")
    private String custSource;
    @Column(name = "cust_industry")
    private String custIndustry;
    @Column(name = "cust_level")
   private String custLevel;
    @Column(name = "cust_address")
    private String custAddress;
    @Column(name = "cust_phone")
    private String custPhone;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }
}
