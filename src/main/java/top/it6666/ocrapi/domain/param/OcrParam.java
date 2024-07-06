package top.it6666.ocrapi.domain.param;

import lombok.Data;

/**
 * @author BNTang
 * @version 1.0
 * @description OCR 接口参数
 * @since 2024/7/7 星期日
 **/
@Data
public class OcrParam {
    /**
     * 图片 base64 编码
     */
    private String imgBase64;

    /**
     * 图片格式
     */
    private String imgFormat;
}
