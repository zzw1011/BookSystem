package com.zzw.base.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 上传文件工具类
 * @author Administrator
 */
public final class FileUtil
{

    /**
     * 水印图片base64码
     */
    private static String shuiyingImg = "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puEVgDOJ6ijo4UiRpmmqpmmqoiiyrut6LBaTdF3XZFlWZFlWTAxhSVVVPRqNypIkqYqiqKFQSGLg3NFoVAuFQorf71ckSdJramr8bdu2rT548OABNHXPCKqqaqIo2vaFk0kZo3IvdGasL0tSZ8HoQFM11Cngm93vQHopC5vfnaTgNSEnaCEA0LNnz9ju3bvlOXPmfDt27NhrbU6xNfaY+8jree6xZ8+e4jfeeKMEgPb222+XPvDAA9t79ep1auJ5hJDehJC0dYhKSkp2TZ48uRjGwqqIopiRNmq1T7SY6ssC71VV1UxGkszAe0nTNE2WZYWBctsF3gcCAVlRFL22ttY28D4UCum7d+8+Uul2URhMlshMgqIoqtfb3P1ZXV1dOXz4cMakjFGbSVI2PJqmcS90ZtaICkA788wzldGjR2f36dMnOzc3V8zPz8/y+Xxul8vl8Xq9PpfLJbrd7hxCiEsURe/y5cu/vfDCC+2yhlIxYEufk5Q0TXNklOzfv7+8e/duZdy4cRsvv/zywe3bt28GWJdo7AGAAQMGDCeEpAW3o5TSl156aTHiaBeep5566ttZs2adnCTxO51RiP79739n0pRF52Uk5Cypl/ypTp9//vlnkUhk49atW92RSATV1dVyfX29+jMH3ttJErvf0p3DchmbgIXBwOpptjgwJt22bRtj0nokZ1IAQDgcPuR2u8vMTCDdLmNIkqRITk5OMCcnp9RsR128eHGX/Pz8V3kHZfDgwRtgqKhOx/KokCRJjhbbYcOGifPmzQsAEP7xj398M3HixJttTiMul+tGGNZ57Nq1yy0IwrU87RcXF29766239sNYaBUA7n/+85/04Ycf3tCvXz/HgN979uzZOXXq1D2Ip+BBEIRMjYCNiwK31ffGG288aN64YObMmf07duyYI4qikJWV5fF6vS6PxyP6fD6vKIrE/HSVl5dXnnXWWYuRmmkOJ/De+pnsN564YZaa1MxIkqiyZsCkFAB69OixEAYOTpsU427FzqUA9Pz8fEersduA6vuPyCqxISpJkqPn6datmwDj3ZBHHnlk+3XXXbene/fuzaKBCCFnKYrS1+12bz3hhBNGEEI6pO0Mpfqzzz67GHEESYZcKD766KOLP/vss36pgmBs2qNTp05djHgiuwpASKhCz01mbDi31deaFBt75513ut50001cOZ7z5s37EoZBw9pOSwXeU85zgfRS1ZpL2EQzsPr9MpGkNmOoJvnN+ntjH+rr69WCggLedwtN03LQgpW6W5oURXFklOzYsSND/CMAQq+88sqiqVOn3mGX4CCK4s3l5eV/EQThjzxtb9++fdOsWbNKEZd+LPk8+uWXXx5as2bNqt/85jfn8va1uLh465tvvnkAcVhVDQAxo/McUybGJApA8fl8oeuvv34YzwXBYHDHhAkT1sAe2Phw0tkSv0eSczP5PTERuRHepKampmL48OGzM2BSRlajkAv2i43VgEQBkE2bNknDhnENOQAgFovZZrZQSgVYQjnRtMI4C+VMTP3Kgk0MNeL5sDWEkGe4Owfn+Fv5+flZiIPKRadNm7b3jjvuSGrs6dSp019gaC0pSdd1dfLkyUz6MQgXNk8lAOG77rpryU8//XSWx+NJ60fXdV1/+umn2d7Uqp1RG7efY+JlVB2AvGvXrrOzs7O5Sqxv2LDhE3MQeCAyWtKIcTgO+eZfUqoGg8HS888//+Nt27Yx/F6nTMomGlOJGCNRALj55pt9nTt3Fvr165fVsWPH1l27dm3TsWNHNyHEpet6vpOHyMvLu1nX9T+aq7GV8Y5EyXineanU7/c7xfZlqjyDOQk988wz38yYMcPW2EMIOZ2n3c2bN2+YN29eBeLSz4pYqAKIbtiwoXb58uUrzjvvvPPTtbd9+/aNH3zwwcGE9hq7dRhj7Ej11a+55hrStWvXq3laDgaDm4cOHboHcUjHw0G6owAYwkE7AAUwVn82AVmyL5NWjJGqAdQTQg7LyLVnz57iJ554YuPmzZsFANFXX33Vdemll57Yvn17MSsrSxBFMVHSeMy/RbN/bjOLx0cpdRNC2PlZplXvsFdbKwmCkHZv1oLEkOy5F8dIJOLI6mviR1kDLqKzZs06+MADD2Rk7AEMy/PEiRO/RXzhTARDY+U7wp07d04b365pmvr444+z9qyLNwEAURS5YuRTEH8I4UcfffQ7Qkh3nnPff//9r8xOWrFt0r5MSmlrAN0BdAPQA0AXGBCR7ZFZlo1MKT0AYCeATQA2OXU+Dx48uAgGOr4IIHzzzTePaNWqlaP8M7adatmSmv8R1Fi/FJzMaodtlYos8ddWPKLQo48+uvjzzz/vZ8GX4qb169evWbRoURUMyFGrysueQwegvPnmm1l9+vQZmK69zZs3r/v000/tpDOAxiSLwyIuRnXiPD5w4MDOCRMmVFoeupnKaybcdoVRyr0ngBNhMGbGBXaSkAfASeZxKQCJUvojgM8IIXs4rrfuG3UAshOQ6186mVhBIpobyJLSwoULY08//TT3PQghVo2DgZxFv/zyy0Nr165dc84556StW2slRVHke++99zvE95LJLPX66NGjL+NBa5wzZ85aNJfOwGFKVGsUFxdcqOk8ToumZpqnv0W8qpluft2eUnoupXQMpfRvAGbDSMZ9AMAfYKQgtTST2pEXwHAA0yilD1BK092TuW5iMFdLQRB+8THPvGQp/sytKqxbt06jlDrZjlgZ1bpXDd95553fybLsaOFctWrVTz/++GMtEuZowmm0urq6Y3Z29jCeNu+8886zYKk7lNieud3JhBrHKS2jOnEe79q1a1tpaWnN6tWrTwoGg5fpuj6RUvo+DNS6iQCugMGUh6uzHy4RAMMAvEIpTWUcs6pbKgDdaXn7XzpNmTKF911a83u51V+Xy5WV0EYTY8+yZctW8LYly3JswoQJSxDfSybdkrVp0+ZaGNpCWiosLDx7yZIl7WAjndu3b08cYEYnHbO0Yv2EE064gMd5DAAnnnhir3/9619TMuzUz0EdADxFKX2IEJKYY2k1LDASQ6GQngni389FyZLAJUmSjZRWVY1Go7Ku61o4HJYVRVElSVLD4bCiqqpWX18fk2VZb2hokAOBgBqNRrXKysqYx+PxU0oPLl682KmGQXVdl3lBCMw9qFViM6kqAwh//PHHm84///y0VlkAWL58+YqNGzfWIclekpEkST0EQRjK+0CEEOF3v/vdnwA8m9jec889l/H+1Fr5ICWjlpaWenidx+agtqgF8yhROwCPUUofJoTYBSMwIgBIMBjUOnfmqhWcEVFK9ZKSkpJMksCDwaDq9/tVziTwwyVWZpKNDdfjAY4R/d02bTTuVR9//PFzeNqJRqPh22+/fSkMaZqUSQHA7XZf7+CZAAAul2tgJBLplZ2dvdP6fY8ePTKu96vreqPqm5JRu3TpchEMq+svnU4yMXj+le7EnTt3Vufn56+vrq5GNBolsVhMMaWT3tDQICmKooVCITkcDquRSETt27dvuyuuuOI83o6sXbt2w8CBA787As/IG2DCG4RiDcxw4rumTlRfQRDsJjoFoH/11Vf53bp142LUJUuWLNu9e7cf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";

    /**
     * LOGGER
     */
    private static final Log LOGGER = LogFactory.getLog(FileUtil.class);

    /**
     * FILE_SIZE
     */
    private static final Integer FILE_SIZE = 1024;

    /**
     * BASE_SIZE
     */
    private static final Integer BASE_SIZE = 256;

    private FileUtil()
    {
    }

    /**
     * 创建文件
     * @param file
     *            文件夹
     * @return 结果
     */
    public static boolean mkdirs(final String file)
    {
        File path = new File(file);
        if (path.exists())
        {
            path.delete();
        }
        boolean mkdirs = path.mkdirs();
        return mkdirs;
    }

    /**
     * 删除所有文件
     * @param path
     *            路径
     * @return 结果
     */
    public static boolean delAllFile(final String path)
    {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists())
        {
            return flag;
        }
        if (!file.isDirectory())
        {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++)
        {
            if (path.endsWith(File.separator))
            {
                temp = new File(path + tempList[i]);
            } else
            {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile())
            {
                temp.delete();
            }
            if (temp.isDirectory())
            {
                // 先删除文件夹里面的文件
                delAllFile(path + "/" + tempList[i]);
                // 再删除空文件夹
                delFolder(path + "/" + tempList[i]);
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件夹
     * @param folderPath
     *            文件夹完整绝对路径
     */
    public static void delFolder(final String folderPath)
    {
        try
        {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e)
        {
            LOGGER.error("", e);
        }
    }

    /**
     * @param destinationDir
     *            目标目录
     * @param file
     *            文件
     * @param fileName
     *            文件名
     * @return 结果
     */
    public static boolean uploadFile(final String destinationDir,
            final MultipartFile file, final String fileName)
    {
        String filePath = SysParamUtils.getString("filesDir") + destinationDir;

        /** 根据真实路径创建目录* */
        File logoSaveFile = new File(filePath);

        if (!logoSaveFile.exists())
        {
            logoSaveFile.mkdirs();
        }

        return saveFileFromInputStream(file, filePath, fileName);
    }

    /**
     * @param destinationDir
     *            目标目录
     * @param file
     *            文件
     * @return 结果
     */
    public static String uploadFileNew(final String destinationDir,
            final MultipartFile file)
    {
        /** 获取文件的后缀* */
        String suffix = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        /** 使用UUID生成文件名称* */
        String imageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
        String filePath = SysParamUtils.getString("filesDir") + "/"
                + destinationDir;
        /** 根据真实路径创建目录* */
        File logoSaveFile = new File(filePath);
        if (!logoSaveFile.exists())
        {
            logoSaveFile.mkdirs();
        }
        if (saveFileFromInputStream(file, filePath, imageName))
        {
            return destinationDir + "/" + imageName;
        } else
        {
            return null;
        }
    }

    /**
     * 保存文件
     * @param path
     *            路径
     * @param fileName
     *            文件名
     * @param file
     *            文件
     * @return 结果
     * @throws IOException
     *             异常
     */
    private static boolean saveFileFromInputStream(final MultipartFile file,
            final String path, final String fileName)
    {
        Boolean result = true;

        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        try
        {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(
                    path + File.separator + fileName);

            int byteCount = 0;

            byte[] bytes = new byte[FILE_SIZE];

            while ((byteCount = inputStream.read(bytes)) != -1)
            {
                outputStream.write(bytes, 0, byteCount);
            }
        } catch (FileNotFoundException e)
        {
            LOGGER.error("", e);
            result = false;
        } catch (IOException e)
        {
            LOGGER.error("", e);
            result = false;
        } finally
        {
            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                } catch (IOException e)
                {
                    LOGGER.error("", e);
                }
            }

            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                } catch (IOException e)
                {
                    LOGGER.error("", e);
                }
            }
        }

        return result;
    }

    /**
     * base64字符串转化成图片
     * @param imgStr
     *            源文件的64位编码流
     * @param destinationDir
     *            目标目录
     * @param fileName
     *            文件名
     * @return 结果
     */
    public static boolean generateImage(final String imgStr,
            final String destinationDir, final String fileName)
    {
        String filePath = destinationDir;
        OutputStream outputStream = null;
        /** 根据真实路径创建目录* */
        File logoSaveFile = new File(filePath);
        if (!logoSaveFile.exists())
        {
            logoSaveFile.mkdirs();
        }
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null)
        {
            // 图像数据为空
            return false;
        }
        try
        {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(imgStr);
            String imgStrReplace = m.replaceAll("");
            imgStrReplace = imgStrReplace.replaceAll(" ", "");
            // String imgStrReplace = imgStr.replaceAll("\r|\n","");
            // Base64解码
            byte[] b = Base64.decode(addWatermark(imgStrReplace));// 在这一步加入水印
            for (int i = 0; i < b.length; ++i)
            {
                if (b[i] < 0)
                {
                    // 调整异常数据
                    b[i] += BASE_SIZE;
                }
            }
            // 生成jpeg图片
            outputStream = new FileOutputStream(filePath + fileName);
            outputStream.write(b);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e)
        {
            LOGGER.error("", e);
            return false;
        } finally
        {
            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                } catch (IOException e)
                {
                    LOGGER.error("", e);
                }
            }
        }
    }

    /**
     * 根据base64码加上水印后返回新的base64码
     * @param srcStr
     *            照片base64字符串
     * @return String
     */
    private static String addWatermark(String srcStr)
    {
        // 根据传递的base64图片的大小来决定水印图片的大小。
        // 处理透明信息
        float alpha = 0.5F;
        int srcWidth = 0;
        int srcHeight = 0;
        byte[] b;

        b = Base64.decode(srcStr);
        try (InputStream is = new java.io.ByteArrayInputStream(b))
        {
            BufferedImage src = ImageIO.read(is);
            if (src != null)
            {
                srcWidth = src.getWidth(null);
                srcHeight = src.getHeight(null);
                if (srcWidth <= 0 || srcHeight <= 0)
                {
                    return null;
                }
                // 根据原始图片变换水印图片的尺寸
                BufferedImage waterMark = resize(shuiyingImg, srcWidth,
                        srcHeight);
                /* 添加水印 */
                BufferedImage img = new BufferedImage(srcWidth,
                        srcHeight, BufferedImage.TYPE_USHORT_565_RGB);
                // 创建画板
                Graphics2D graph = img.createGraphics();
                // 把原图印到图板上
                graph.drawImage(src, null, 0, 0);
                // 设置透明度,alpha
                graph.setComposite(AlphaComposite
                        .getInstance(AlphaComposite.SRC_ATOP, alpha));
                Integer x = Double
                        .valueOf((double) srcWidth/2
                                - ((double) waterMark.getWidth(null)/2))
                        .intValue();
                Integer y =  Double
                        .valueOf((double) srcHeight
                                - ((double) waterMark.getHeight(null)))
                        .intValue();
                // 画水印图片
                graph.drawImage(waterMark, null, x, y);
                /* 把图片转换为字节 */
                try (ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream())
                {
                    ImageIO.write(img, "jpg", jpegOutputStream);
                    // ImageIO.write(img, "jpg",new File("d://wave.jpg"));
                    byte[] resultByte = jpegOutputStream.toByteArray();
                    graph.dispose();
                    return Base64.encode(resultByte);
                }
            }
        } catch (IOException e)
        {
            LOGGER.error("", e);
        }
        return null;
    }

    /**
     * 根据图片大小，自动变化水印图片大小。
     * @param src：
     * @param w：原图片宽度
     * @param h：元图片高度
     * @return 返回image
     */
    private static BufferedImage resize(String src, int w, int h)
    {
        byte[] b;
        // 加载内存中的水印图片
        w = w / 3;
        h = h / 3;
        b = Base64.decode(src);
        try (InputStream is = new java.io.ByteArrayInputStream(b))
        {
            BufferedImage img = ImageIO.read(is);
            // 获得适合的缩放比率，即以在规定缩略尺寸中完整显示图片内容的同时又保证最大的缩放比率
            // 根据比例画出缓存图像
            BufferedImage mini = new BufferedImage(w, h,
                    BufferedImage.TYPE_USHORT_565_RGB);
            Graphics2D gmini = mini.createGraphics();
            gmini.setBackground(Color.WHITE);
            // 让生成的图片按相同的比例变换
            gmini.clearRect(0, 0, w, h);

            AffineTransform trans = new AffineTransform();
            // 长和宽同时变换
            trans.scale(((double) w / img.getWidth()),
                    ((double) h / img.getHeight()));
            gmini.setComposite(
                    AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.7f));
            AffineTransformOp op = new AffineTransformOp(trans,
                    AffineTransformOp.TYPE_BILINEAR);
            gmini.drawImage(img, op, 0, 0);
            gmini.dispose();
            return img;
        } catch (IOException e)
        {
            LOGGER.error("", e);
        }
        return null;
    }

    /**
     * getRootPath
     * @return 结果
     */
    public static String getRootPath()
    {
        String classPath = FileUtil.class.getClassLoader().getResource("/")
                .getPath();
        String rootPath = "";

        // windows下
        if ("\\".equals(File.separator))
        {
            rootPath = classPath.substring(1,
                    classPath.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("/", "\\");
        }
        // linux下
        if ("/".equals(File.separator))
        {
            rootPath = classPath.substring(0,
                    classPath.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("\\", "/");
        }
        return rootPath;
    }

}
