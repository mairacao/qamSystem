package com.xiaoyu.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qa_property")
public class QaProperty {
    /**
     * 属性ID
     */
    @Id
    @Column(name = "property_id")
    private Long propertyId;

    /**
     * 属性名称
     */
    @Column(name = "property_name")
    private String propertyName;

    /**
     * 类别ID
     */
    @Column(name = "type_id")
    private Long typeId;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建者ID
     */
    @Column(name = "create_userid")
    private Long createUserid;

    /**
     * 修改日期
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改者ID
     */
    @Column(name = "update_userid")
    private Long updateUserid;

    /**
     * 获取属性ID
     *
     * @return property_id - 属性ID
     */
    public Long getPropertyId() {
        return propertyId;
    }

    /**
     * 设置属性ID
     *
     * @param propertyId 属性ID
     */
    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * 获取属性名称
     *
     * @return property_name - 属性名称
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 设置属性名称
     *
     * @param propertyName 属性名称
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * 获取类别ID
     *
     * @return type_id - 类别ID
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * 设置类别ID
     *
     * @param typeId 类别ID
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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
    public Long getCreateUserid() {
        return createUserid;
    }

    /**
     * 设置创建者ID
     *
     * @param createUserid 创建者ID
     */
    public void setCreateUserid(Long createUserid) {
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
    public Long getUpdateUserid() {
        return updateUserid;
    }

    /**
     * 设置修改者ID
     *
     * @param updateUserid 修改者ID
     */
    public void setUpdateUserid(Long updateUserid) {
        this.updateUserid = updateUserid;
    }
}