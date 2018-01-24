package com.zzw.base.entity;

import com.zzw.base.utils.JsonUtil;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @创建者：zzw
 * @创建时间 2018/1/2 0002
 * @描述 实体基类
 */
public abstract class BaseEntity implements Serializable {


    private static final long serialVersionUID = -2279405948697261511L;

    /** ID */
    private Long id;

    /** 创建日期 */
    private Date createDate;

    /** 修改日期 */
    private Date modifyDate;

    /** 新增用户 */
    private UserEntity createUser;

    /** 修改操作用户 */
    private UserEntity updateUser;

    /** 页码 */
    private Integer page;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public UserEntity getCreateUser() {
        return createUser;
    }

    public void setCreateUser(UserEntity createUser) {
        this.createUser = createUser;
    }

    public UserEntity getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(UserEntity updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this, JsonUtil.getJsonConfig()).toString();
    }
}
