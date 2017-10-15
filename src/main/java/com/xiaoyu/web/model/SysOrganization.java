package com.xiaoyu.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_organization")
public class SysOrganization {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 组织名
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 编号
     */
    private String code;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父级主键
     */
    private Long pid;

    /**
     * 排序
     */
    private Byte seq;

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
     * 获取组织名
     *
     * @return name - 组织名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组织名
     *
     * @param name 组织名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取编号
     *
     * @return code - 编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编号
     *
     * @param code 编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取父级主键
     *
     * @return pid - 父级主键
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父级主键
     *
     * @param pid 父级主键
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取排序
     *
     * @return seq - 排序
     */
    public Byte getSeq() {
        return seq;
    }

    /**
     * 设置排序
     *
     * @param seq 排序
     */
    public void setSeq(Byte seq) {
        this.seq = seq;
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