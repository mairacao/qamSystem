package com.xiaoyu.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_resource")
public class SysResource {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 打开方式 ajax,iframe
     */
    @Column(name = "open_mode")
    private String openMode;

    /**
     * 资源介绍
     */
    private String description;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 父级资源id
     */
    private Long pid;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 打开状态
     */
    private Integer opened;

    /**
     * 资源类别
     */
    @Column(name = "resource_type")
    private Integer resourceType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取资源名称
     *
     * @return name - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取资源路径
     *
     * @return url - 资源路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置资源路径
     *
     * @param url 资源路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取打开方式 ajax,iframe
     *
     * @return open_mode - 打开方式 ajax,iframe
     */
    public String getOpenMode() {
        return openMode;
    }

    /**
     * 设置打开方式 ajax,iframe
     *
     * @param openMode 打开方式 ajax,iframe
     */
    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

    /**
     * 获取资源介绍
     *
     * @return description - 资源介绍
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置资源介绍
     *
     * @param description 资源介绍
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取资源图标
     *
     * @return icon - 资源图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置资源图标
     *
     * @param icon 资源图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取父级资源id
     *
     * @return pid - 父级资源id
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父级资源id
     *
     * @param pid 父级资源id
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取排序
     *
     * @return seq - 排序
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置排序
     *
     * @param seq 排序
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取打开状态
     *
     * @return opened - 打开状态
     */
    public Integer getOpened() {
        return opened;
    }

    /**
     * 设置打开状态
     *
     * @param opened 打开状态
     */
    public void setOpened(Integer opened) {
        this.opened = opened;
    }

    /**
     * 获取资源类别
     *
     * @return resource_type - 资源类别
     */
    public Integer getResourceType() {
        return resourceType;
    }

    /**
     * 设置资源类别
     *
     * @param resourceType 资源类别
     */
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
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