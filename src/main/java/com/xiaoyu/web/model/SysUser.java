package com.xiaoyu.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 登陆名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码加密盐
     */
    private String salt;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户类别
     */
    @Column(name = "user_type")
    private Byte userType;

    /**
     * 用户状态
     */
    private Byte status;

    /**
     * 所属机构
     */
    @Column(name = "organization_id")
    private Integer organizationId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取登陆名
     *
     * @return login_name - 登陆名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登陆名
     *
     * @param loginName 登陆名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取密码加密盐
     *
     * @return salt - 密码加密盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置密码加密盐
     *
     * @param salt 密码加密盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Byte getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Byte age) {
        this.age = age;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户类别
     *
     * @return user_type - 用户类别
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * 设置用户类别
     *
     * @param userType 用户类别
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    /**
     * 获取用户状态
     *
     * @return status - 用户状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置用户状态
     *
     * @param status 用户状态
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取所属机构
     *
     * @return organization_id - 所属机构
     */
    public Integer getOrganizationId() {
        return organizationId;
    }

    /**
     * 设置所属机构
     *
     * @param organizationId 所属机构
     */
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}