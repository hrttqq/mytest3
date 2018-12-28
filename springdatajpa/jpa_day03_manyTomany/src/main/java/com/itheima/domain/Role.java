package com.itheima.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_memo")
    private String roleMemo;
    /**
     * 配置用户到角色的多对多关系
     *      配置多对多的映射关系
     *          1.声明表关系的配置
     *              @ManyToMany(targetEntity = Role.class)  //多对多
     *                  targetEntity：代表对方的实体类字节码
     *          2.配置中间表（包含两个外键）
     *                @JoinTable
     *                  name : 中间表的名称
     *                  joinColumns：配置当前对象在中间表的外键
     *                      @JoinColumn的数组
     *                          name：外键名
     *                          referencedColumnName：参照的主表的主键名
     *                  inverseJoinColumns：配置对方对象在中间表的外键
     *                     @JoinColumn的数组
     *                          name：外键名
     *                          referencedColumnName：参照的主表的主键名
     */
    // 因为角色一端是不常操作的一方，放弃关联关系，放弃中间表维护权（角色不能操作中间表）
    // 设置关联删除 角色表删除了 user表也删除,cascade = CascadeType.REMOVE
    @ManyToMany(targetEntity = User.class,mappedBy = "roles",cascade = CascadeType.REMOVE)
/*    @JoinTable(name = "sys_user_role",
            //joinColumns,当前对象在中间表中的外键
                joinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")},
            //inverseJoinColumns，对方对象在中间表的外键
                inverseJoinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")}
                )*/
    private Set<User> users=new HashSet<User>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleMemo() {
        return roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo;
    }
}
