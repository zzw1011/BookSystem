package com.zzw.base.entity;

/**
 * Created by Administrator on 2016/7/18.
 */
public class Category extends BaseEntity
{
    /**
     * pid
     */
    private Long pid;
    /**
     * categoryName
     */
    private String categoryName;
    /**
     * categoryUrl
     */
    private String categoryUrl;
    /**
     * categoryImg
     */
    private String categoryImg;
    /**
     * permissionCode
     */
    private String permissionCode;
    /**
     * classifyId
     */
    private Long classifyId;
    /**
     * categoryDesc
     */
    private String categoryDesc;

    /**
     * getClassifyId
     * @return classifyId
     */
    public Long getClassifyId() {
        return classifyId;
    }

    /**
     * setClassifyId
     * @param classifyId 参数
     */
    public void setClassifyId(final Long classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * getPid
     * @return pid
     */
    public Long getPid() {
        return pid;
    }

    /**
     * getCategoryName
     * @return categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * getCategoryUr
     * @return categoryUrl
     */
    public String getCategoryUrl() {
        return categoryUrl;
    }

    /**
     * getCategoryImg
     * @return categoryImg
     */
    public String getCategoryImg() {
        return categoryImg;
    }

    /**
     * getPermissionCode
     * @return permissionCode
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * setPid
     * @param pid 参数
     */
    public void setPid(final Long pid) {
        this.pid = pid;
    }

    /**
     * setCategoryName
     * @param categoryName 参数
     */
    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * setCategoryUrl
     * @param categoryUrl 参数
     */
    public void setCategoryUrl(final String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    /**
     * setCategoryImg
     * @param categoryImg 参数
     */
    public void setCategoryImg(final String categoryImg) {
        this.categoryImg = categoryImg;
    }

    /**
     * setPermissionCode
     * @param permissionCode 参数
     */
    public void setPermissionCode(final String permissionCode) {
        this.permissionCode = permissionCode;
    }

    /**
     * setCategoryDesc
     * @param categoryDesc 参数
     */
    public void setCategoryDesc(final String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    /**
     * getCategoryDesc
     * @return categoryDesc
     */
    public String getCategoryDesc() {
        return categoryDesc;
    }
}
