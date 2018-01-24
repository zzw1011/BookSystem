package com.zzw.base.entity;

/**
 * @创建者：zzw
 * @创建时间 2018/1/2 0002
 * @描述
 */
public class UserRoleEntity extends BaseEntity {

    private Long userId;

    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(final Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }
}
