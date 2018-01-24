package com.zzw.base.service.impl;

import com.zzw.base.service.CaptchaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;

/**
 * Service - 验证码
 * @author JIUJIANG Team
 * @version 3.0
 */
@Service("captchaServiceImpl")
public class CaptchaServiceImpl implements CaptchaService
{
    /**
     * imageCaptchaService
     */
    @Resource(name = "imageCaptchaService")
    private com.octo.captcha.service.CaptchaService imageCaptchaService;

    /**
     * 创建图片
     * @param captchaId
     *            验证ID
     * @return 结果
     */
    public BufferedImage buildImage(final String captchaId)
    {
        return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
    }

    /**
     *
     * @param captchaId
     *            验证ID
     * @param captcha 验证码(忽略大小写)
     * @return 结果
     */
    public boolean isValid(final String captchaId, final String captcha)
    {
        if (StringUtils.isNotEmpty(captchaId)
                && StringUtils.isNotEmpty(captcha))
        {
            try
            {
                Boolean result = imageCaptchaService
                        .validateResponseForID(captchaId, captcha);
                return result;
            } catch (Exception e)
            {
                return false;
            }
        } else
        {
            return false;
        }

    }

}
