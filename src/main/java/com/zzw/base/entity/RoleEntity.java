package com.zzw.base.entity;

/**
 * @创建者：zzw
 * @创建时间 2018/1/2 0002
 * @描述 角色实体
 */
public class RoleEntity extends BaseEntity{



    private Long roleId;

    private String roleName;
    /**
     * roleRemark
     */
    private String roleRemark;
    /**
     * selFlg
     */
    private int selFlg;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(final Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(final String roleRemark) {
        this.roleRemark = roleRemark;
    }

    public int getSelFlg() {
        return selFlg;
    }

    public void setSelFlg(final int selFlg) {
        this.selFlg = selFlg;
    }
}
