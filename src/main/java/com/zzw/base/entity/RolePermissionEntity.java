package com.zzw.base.entity;

/**
 * @创建者：zzw
 * @创建时间 2018/1/2 0002
 * @描述 角色权限关联实体
 */
public class RolePermissionEntity extends BaseEntity {

    /**
     * roleId
     */
    private Long roleId;
    /**
     * perCode
     */
    private String perCode;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getPerCode() {
        return perCode;
    }

    public void setPerCode(String perCode) {
        this.perCode = perCode;
    }
}
