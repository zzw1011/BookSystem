/**
 * @Title: ChinaFont
 * @author wuxiangwen-南昌泽诺信息科技有限公司
 * @date 2017/4/12 16:40
 */
package com.zzw.base.service.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.BaseFont;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

/**
 * @author wuxiangwen-南昌泽诺信息科技有限公司
 * @ClassName: ChinaFont
 * @date 2017/4/12 16:40
 */
public class ChinaFont implements FontProvider
{
    /**
     * 日志
     */
    private static final Log LOG = LogFactory.getLog(ChinaFont.class);

    @Override
    public boolean isRegistered(String fontname)
    {
        return false;
    }

    /**
     * getFont
     * @param fontname
     *            fontname
     * @param encoding
     *            encoding
     * @param embedded
     *            embedded
     * @param size
     *            size
     * @param style
     *            style
     * @param color
     * @return Font
     *            color
     */
    public Font getFont(String fontname, String encoding, boolean embedded,
            float size, int style, BaseColor color)
    {

        try
        {
            BaseFont bfChinese;
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED);
            return new Font(bfChinese, size, style, color);
        } catch (DocumentException e)
        {
            LOG.error("",e);
        } catch (IOException e)
        {
            LOG.error("",e);
        }
        return null;
    }
}
