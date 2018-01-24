package com.zzw.base.entity;

/**
 * Created by Administrator on 2016/7/19.
 */
public class SystemParameterEntity extends BaseEntity
{

    /**
     * serialVersionUID "身份信息"参数名称
     */
    private static final long serialVersionUID = -4403359869825087452L;

    /**
     * parameterKey
     */
    private String parameterKey;
    /**
     * parameterValue
     */
    private String parameterValue;
    /**
     * instruction
     */
    private String instruction;

    /**
     * getParameterKey
     * @return parameterKey
     */
    public String getParameterKey()
    {
        return parameterKey;
    }

    /**
     * setParameterKey
     * @param parameterKey 参数
     */
    public void setParameterKey(final String parameterKey)
    {
        this.parameterKey = parameterKey;
    }

    /**
     * getParameterValue
     * @return parameterValue
     */
    public String getParameterValue()
    {
        return parameterValue;
    }

    /**
     * setParameterValue
     * @param parameterValue 参数
     */
    public void setParameterValue(final String parameterValue)
    {
        this.parameterValue = parameterValue;
    }

    /**
     * getInstruction
     * @return instruction
     */
    public String getInstruction()
    {
        return instruction;
    }

    /**
     * setInstruction
     * @param instruction 参数
     */
    public void setInstruction(final String instruction)
    {
        this.instruction = instruction;
    }

}
