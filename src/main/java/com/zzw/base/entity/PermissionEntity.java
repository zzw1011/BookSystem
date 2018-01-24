package com.zzw.base.entity;

/**
 * @创建者：zzw
 * @创建时间 2018/1/2 0002
 * @描述 权限实体
 */
public class PermissionEntity extends BaseEntity {


    private Long perId;

    private String permissionName;
    /**
     * permissionPath
     */
    private String permissionPath;
    /**
     * permissionParentId
     */
    private int permissionParentId;
    /**
     * perFunction
     */
    private int perFunction;

    public Long getPerId() {
        return perId;
    }

    public void setPerId(final Long perId) {
        this.perId = perId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(final String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionPath() {
        return permissionPath;
    }

    public void setPermissionPath(final String permissionPath) {
        this.permissionPath = permissionPath;
    }

    public int getPermissionParentId() {
        return permissionParentId;
    }

    public void setPermissionParentId(final int permissionParentId) {
        this.permissionParentId = permissionParentId;
    }

    public int getPerFunction() {
        return perFunction;
    }

    public void setPerFunction(final int perFunction) {
        this.perFunction = perFunction;
    }
}
