package com.zzw.base.entity;

import com.zzw.base.model.RoleModelEntity;

import java.util.*;

/**
 * @创建者：zzw
 * @创建时间 2018/1/2 0002
 * @描述 用户实体
 */
public class UserEntity extends BaseEntity {

    private String userName;

    private String realName;

    private String userPassword;

    private Date userAddTime;

    private String userRemark;

    private String userLastIp;

    private Date userLastTime;

    private Date lockedDate;

    private int failureCount;

    private int isLocked;

    private Date loginDate;

    private String loginIp;

    private String phoneNumber;

    private String gender;

    private String genderName;

    private String email;

    private Long roleId;

    private String roleName;

    private RoleModelEntity roleModelEntity;

    private List<RoleModelEntity> roleList = new ArrayList<RoleModelEntity>();


    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(final String realName) {
        this.realName = realName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(final String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getUserAddTime() {
        return userAddTime;
    }

    public void setUserAddTime(final Date userAddTime) {
        this.userAddTime = userAddTime;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(final String userRemark) {
        this.userRemark = userRemark;
    }

    public String getUserLastIp() {
        return userLastIp;
    }

    public void setUserLastIp(final String userLastIp) {
        this.userLastIp = userLastIp;
    }

    public Date getUserLastTime() {
        return userLastTime;
    }

    public void setUserLastTime(final Date userLastTime) {
        this.userLastTime = userLastTime;
    }

    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(final Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(final int failureCount) {
        this.failureCount = failureCount;
    }

    public int getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(final int isLocked) {
        this.isLocked = isLocked;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(final Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(final String loginIp) {
        this.loginIp = loginIp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(final String genderName) {
        this.genderName = genderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

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

    public RoleModelEntity getRoleModelEntity() {
        return roleModelEntity;
    }

    public void setRoleModelEntity(final RoleModelEntity roleModelEntity) {
        this.roleModelEntity = roleModelEntity;
    }

    public List<RoleModelEntity> getRoleList() {
        return roleList;
    }

    public void setRoleList(final List<RoleModelEntity> roleList) {
        this.roleList = roleList;
    }

    /**
     * getRolesName
     * @return set
     */
    public Set<String> getRolesName()
    {
        Set<String> set = new HashSet<String>();
        List<RoleModelEntity> roles = getRoleList();
        for (RoleModelEntity role : roles)
        {
            set.add(role.getRoleName());
        }
        return set;
    }

}
