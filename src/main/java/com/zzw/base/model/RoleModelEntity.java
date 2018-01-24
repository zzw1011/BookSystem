package com.zzw.base.model;

import com.zzw.base.entity.RolePermissionEntity;
import com.zzw.base.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * RoleEntity
 */
public class RoleModelEntity
{
    /**
     * roleId
     */
    private int roleId;
    /**
     * roleName
     */
    private String roleName;
    /**
     * roleRemark
     */
    private String roleRemark;
    /**
     * 一个角色对应多个权限
     */
    private List<RolePermissionEntity> permissionList = new ArrayList<RolePermissionEntity>();
    /**
     * 一个角色对应多个用户
     */
    private List<UserEntity> users = new ArrayList<UserEntity>();

    /**
     * getRoleId
     * @return roleId
     */
    public int getRoleId()
    {
        return roleId;
    }

    /**
     * setRoleId
     * @param roleId 参数
     */
    public void setRoleId(final int roleId)
    {
        this.roleId = roleId;
    }

    /**
     * getRoleName
     * @return roleName
     */
    public String getRoleName()
    {
        return roleName;
    }

    /**
     * setRoleName
     * @param roleName 参数
     */
    public void setRoleName(final String roleName)
    {
        this.roleName = roleName;
    }

    /**
     * getRoleRemark
     * @return roleRemark
     */
    public String getRoleRemark()
    {
        return roleRemark;
    }

    /**
     * setRoleRemark
     * @param roleRemark 参数
     */
    public void setRoleRemark(final String roleRemark)
    {
        this.roleRemark = roleRemark;
    }

    /**
     * getUsers
     * @return users
     */
    public List<UserEntity> getUsers()
    {
        return users;
    }

    /**
     * getPermissionList
     * @return permissionList
     */
    public List<RolePermissionEntity> getPermissionList()
    {
        return permissionList;
    }

    /**
     * setPermissionList
     * @param permissionList 参数
     */
    public void setPermissionList(final List<RolePermissionEntity> permissionList)
    {
        this.permissionList = permissionList;
    }

    /**
     * setUsers
     * @param users 参数
     */
    public void setUsers(final List<UserEntity> users)
    {
        this.users = users;
    }

    /**
     * getPermissionName
     * @return list
     */
    public List<String> getPermissionName()
    {
        List<String> list = new ArrayList<String>();
        List<RolePermissionEntity> perlist = getPermissionList();
        for (RolePermissionEntity per : perlist)
        {
            list.add(per.getPerCode());
        }

        return list;
    }

}
