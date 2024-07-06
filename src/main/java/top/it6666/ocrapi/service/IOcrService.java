package top.it6666.ocrapi.service;

import top.it6666.ocrapi.domain.dto.TextOcrDto;

import java.util.List;

/**
 * @author BNTang
 * @version 1.0
 * @description OCR 识别业务实现
 * @since 2024/7/7 星期日
 **/
public interface IOcrService {
    /**
     * 文本识别
     *
     * @param imgBase64 图片 base64 编码
     * @param imgFormat 图片格式
     * @return 识别的文本
     */
    List<TextOcrDto> ocrText(String imgBase64, String imgFormat);
}
