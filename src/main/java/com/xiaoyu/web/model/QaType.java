package com.xiaoyu.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qa_type")
public class QaType {
    /**
     * 类别ID
     */
    @Id
    @Column(name = "type_id")
    private Long typeId;


    /**
     * 类别Name
     */
    @Column(name = "type_name")
    private String typeName;

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
     * 获取类别Name
     *
     * @return type_name - 类别Name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置类别Name
     *
     * @param typeName 类别Name
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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