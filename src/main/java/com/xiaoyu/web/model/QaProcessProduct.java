package com.xiaoyu.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qa_process_product")
public class QaProcessProduct {
    /**
     * 过程产品ID
     */
    @Id
    @Column(name = "processpdu_id")
    private Integer processpduId;

    /**
     * 过程产品名称
     */
    @Column(name = "processpdu_name")
    private String processpduName;

    /**
     * 属性Code
     */
    @Column(name = "property_id")
    private Integer propertyId;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建者ID
     */
    @Column(name = "create_userid")
    private Integer createUserid;

    /**
     * 修改日期
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改者ID
     */
    @Column(name = "update_userid")
    private Integer updateUserid;

    /**
     * 获取过程产品ID
     *
     * @return processpdu_id - 过程产品ID
     */
    public Integer getProcesspduId() {
        return processpduId;
    }

    /**
     * 设置过程产品ID
     *
     * @param processpduId 过程产品ID
     */
    public void setProcesspduId(Integer processpduId) {
        this.processpduId = processpduId;
    }

    /**
     * 获取过程产品名称
     *
     * @return processpdu_name - 过程产品名称
     */
    public String getProcesspduName() {
        return processpduName;
    }

    /**
     * 设置过程产品名称
     *
     * @param processpduName 过程产品名称
     */
    public void setProcesspduName(String processpduName) {
        this.processpduName = processpduName;
    }

    /**
     * 获取属性Code
     *
     * @return property_id - 属性Code
     */
    public Integer getPropertyId() {
        return propertyId;
    }

    /**
     * 设置属性Code
     *
     * @param propertyId 属性Code
     */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * 获取创建日期
     *
     * @return create_date - 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建日期
     *
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取创建者ID
     *
     * @return create_userid - 创建者ID
     */
    public Integer getCreateUserid() {
        return createUserid;
    }

    /**
     * 设置创建者ID
     *
     * @param createUserid 创建者ID
     */
    public void setCreateUserid(Integer createUserid) {
        this.createUserid = createUserid;
    }

    /**
     * 获取修改日期
     *
     * @return update_date - 修改日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改日期
     *
     * @param updateDate 修改日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取修改者ID
     *
     * @return update_userid - 修改者ID
     */
    public Integer getUpdateUserid() {
        return updateUserid;
    }

    /**
     * 设置修改者ID
     *
     * @param updateUserid 修改者ID
     */
    public void setUpdateUserid(Integer updateUserid) {
        this.updateUserid = updateUserid;
    }
}