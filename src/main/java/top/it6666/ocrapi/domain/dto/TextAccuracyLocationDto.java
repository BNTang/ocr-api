package top.it6666.ocrapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author BNTang
 * @version 1.0
 * @description 文本识别结果，包含文本，可信度，字符位置
 * 主要顾虑类名太长，建议不要用简写，因为一个好的类的命名，首先要明确表达这个类是用来干什么的
 * @since 2024/7/7 星期日
 **/
// @AllArgsConstructor：生成全参构造器
@AllArgsConstructor
// @NoArgsConstructor：生成无参构造器
@NoArgsConstructor
// @Data：生成 getter、setter、equals、canEqual、hashCode、toString 方法
@Data
public class TextAccuracyLocationDto {
    /**
     * 识别的文本
     */
    private String text;

    /**
     * 文本识别准确度，可信度
     */
    private String accuracy;

    /**
     * 文本在图片中的位置
     */
    private Map<String, OcrPoint> location;
}
