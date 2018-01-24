package com.zzw.base.entity;

/**
 * 操作员日志
 * @author Administrator
 */
public class OperatorLog extends BaseEntity
{

    private static final long serialVersionUID = -6733189334544245570L;

    /** "日志内容"属性名称 */
    public static final String LOG_CONTENT_ATTRIBUTE_NAME = OperatorLog.class
            .getName() + ".CONTENT";

    /** 操作 */
    private String operation;

    /** 操作员 */
    private String operator;

    /** 内容 */
    private String content;

    /** 请求参数 */
    private String parameter;

    /** IP */
    private String ip;

    /**
     * getOperation
     * @return operation
     */
    public String getOperation()
    {
        return operation;
    }

    /**
     * setOperation
     * @param operation 参数
     */
    public void setOperation(final String operation)
    {
        this.operation = operation;
    }

    /**
     * getOperator
     * @return operator
     */
    public String getOperator()
    {
        return operator;
    }

    /**
     * setOperator
     * @param operator 参数
     */
    public void setOperator(final String operator)
    {
        this.operator = operator;
    }

    /**
     * getContent
     * @return content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * setContent
     * @param content
     *            参数
     */
    public void setContent(final String content)
    {
        this.content = content;
    }

    /**
     * getParameter
     * @return parameter
     */
    public String getParameter()
    {
        return parameter;
    }

    /**
     * setParameter
     * @param parameter 参数
     */
    public void setParameter(final String parameter)
    {
        this.parameter = parameter;
    }

    /**
     * getIp
     * @return ip
     */
    public String getIp()
    {
        return ip;
    }

    /**
     * setIp
     * @param ip 参数
     */
    public void setIp(final String ip)
    {
        this.ip = ip;
    }

}
