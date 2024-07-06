package top.it6666.ocrapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.it6666.ocrapi.domain.dto.TextOcrDto;
import top.it6666.ocrapi.domain.param.OcrParam;

import java.util.List;

/**
 * @author BNTang
 * @version 1.0
 * @description OCR 文本识别接口
 * @since 2024/7/7 星期日
 **/
// @RestController：标记为控制器类，返回 JSON 数据
@RestController
// @RequestMapping：映射请求路径
@RequestMapping("/text")
public class OcrTextController {

    @PostMapping("/text-only")
    public List<List<TextOcrDto>> textOnly(@RequestBody OcrParam ocrParam) {
        return null;
    }
}
