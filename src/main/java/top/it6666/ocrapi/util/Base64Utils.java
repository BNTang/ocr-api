package top.it6666.ocrapi.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.Base64;

/**
 * @author BNTang
 * @version 1.0
 * @description Base64 工具类
 * @since 2024/7/7 星期日
 **/
// @UtilityClass：生成静态方法的工具类，直接编写普通方法一样编写工具方法即可，不需要写静态，因为它会自动帮我们加上
@UtilityClass
// 添加 @Slf4j 注解，生成日志对象，可以直接使用 log 对象打印日志
@Slf4j
public class Base64Utils {

    /**
     * base64 转图片, 保存到指定路径
     *
     * @param base64Str base64 字符串
     * @param imgPath   图片路径
     */
    public void base64ToImage(String base64Str, String imgPath) {
        // 1. base64 转图片，可以采用 Java Util Base64 类
        // Base64.getDecoder()，获取解码器
        // decode()，解码
        byte[] bytes = Base64.getDecoder().decode(base64Str);

        // 返回的是一个字节数组，可以直接写入到文件中

        // 2. 将图片保存到指定路径
        // 可以使用 commons-io 工具包中的 FileUtils 类, 是 Apache 的一个工具类
        try {
            // writeByteArrayToFile()，将字节数组写入到文件中，第一个参数是文件对象，第二个参数是字节数组
            FileUtils.writeByteArrayToFile(FileUtils.getFile(imgPath), bytes);
            log.info("base64 转图片成功，图片保存路径：{}。", imgPath);
        } catch (IOException e) {
            log.error("base64 转图片失败，图片保存路径：{}。", imgPath);
            throw new RuntimeException(e);
        }
    }
}
