package com.zzw.base.entity;

/**
 * Created by Administrator on 2016/7/19.
 */
public class CategoryClassify extends BaseEntity
{
    /**
     * classifyName
     */
    private String classifyName;
    /**
     * classifyNo
     */
    private String classifyNo;

    /**
     * setClassifyName
     * @param classifyName 参数
     */
    public void setClassifyName(final String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * setClassifyNo
     * @param classifyNo 参数
     */
    public void setClassifyNo(final String classifyNo) {
        this.classifyNo = classifyNo;
    }

    /**
     * getClassifyName
     * @return classifyName
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * getClassifyNo
     * @return classifyNo
     */
    public String getClassifyNo() {
        return classifyNo;
    }
}
