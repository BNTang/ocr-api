package top.it6666.ocrapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BNTang
 * @version 1.0
 * @description 文本字符的像素坐标
 * @since 2024/7/7 星期日
 **/
// @AllArgsConstructor：生成全参构造器
@AllArgsConstructor
// @NoArgsConstructor：生成无参构造器
@NoArgsConstructor
// @Data：生成 getter、setter、equals、canEqual、hashCode、toString 方法
@Data
public class OcrPoint {
    /**
     * x 坐标，横坐标
     */
    private String x;

    /**
     * y 坐标，纵坐标
     */
    private String y;
}
