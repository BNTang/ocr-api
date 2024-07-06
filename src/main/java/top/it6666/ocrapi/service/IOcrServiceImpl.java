package top.it6666.ocrapi.service;

import com.github.yitter.idgen.YitIdHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.it6666.ocrapi.domain.dto.TextAccuracyLocationDto;
import top.it6666.ocrapi.domain.dto.TextOcrDto;
import top.it6666.ocrapi.domain.param.PaddleOcrPostParam;
import top.it6666.ocrapi.util.Base64Utils;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author BNTang
 * @version 1.0
 * @description OCR 识别业务实现
 * @since 2024/7/7 星期日
 **/
// @Slf4j：生成日志对象，可以直接使用 log 对象打印日志
@Slf4j
// @Service：标记为服务类，交给 Spring 管理
@Service
public class IOcrServiceImpl implements IOcrService {
    // 保存图片的名称格式，第一个占位符是图片名称，第二个占位符是图片路径
    private static final String FORMAT_IMG_NAME = "%s.%s";
    // 保存图片的路径
    @Value("${ocr.img-path}")
    private String imgPath;

    // @Value("#{'${ocr.paddle-ocr-url}'.split(',')}")：获取配置文件中的 paddle-ocr-url 属性值，并使用逗号分隔
    // #{}：SpEL 表达式，用于获取配置文件中的属性值，${}：获取配置文件中的属性值，一起使用就是获取配置文件中的属性值，区别在于 #{} 可以使用 SpEL 表达式，${} 不能，所以我在外面加了一个 #{}，这样就可以使用 SpEL 表达式了然后就可以使用 split(',') 方法将获取到的值使用逗号分隔
    @Value("#{'${ocr.paddle-ocr-url}'.split(',')}")
    private List<String> paddleOcrUrls;

    // 创建一个原子类，用于记录 paddle-ocr 服务的调用次数，实现负载均衡
    private static AtomicLong paddleCount = new AtomicLong();

    @Resource
    private RestTemplate restTemplate;

    /**
     * 文本识别
     *
     * @param imgBase64 图片 base64 编码
     * @param imgFormat 图片格式
     * @return 识别的文本
     */
    @Override
    public List<TextOcrDto> ocrText(String imgBase64, String imgFormat) {
        // 1. 将 base64 编码转换为图片
        saveImage(imgBase64, imgFormat);
        // 2. 调用 flask 服务进行 OCR 文本识别
        // 3. 将识别的文本封装到 TextOcrDto 对象中
        return List.of();
    }

    /**
     * 保存图片
     *
     * @param imgBase64 图片 base64 编码
     * @param imgFormat 图片格式
     * @return 保存的图片路径
     */
    private String saveImage(String imgBase64, String imgFormat) {
        // 1. 保存图片的名称和路径
        // 通过雪花算法生成图片 ID，获取唯一自增 ID
        // 如果这个方法被重复调用ID会重复吗？通过查看代码发现是不会重复的，因为加了锁
        long imgId = YitIdHelper.nextId();
        // 生成图片名称
        String imgName = String.format(FORMAT_IMG_NAME, imgId, imgFormat);
        // 生成图片完整的保存路径
        String imgFullPath = String.format(imgPath, imgName);
        // 保存图片到服务器
        Base64Utils.base64ToImage(imgBase64, imgFullPath);
        return imgFullPath;
    }

    private List<TextAccuracyLocationDto> ocrImage(String imgPath) {
        // 1. 调用 flask 服务进行 OCR 文本识别
        // 由于 paddle-ocr 在普通的部署方式下，无法利用多核 CPU 进行并行处理
        // 所以我们可以通过部署多个 paddle-ocr 服务，然后通过负载均衡的方式，将请求分发到不同的 paddle-ocr 服务上，从而提高 OCR 文本识别的速度
        // paddleCount.getAndIncrement() % paddleOcrUrls.size()，先获取 paddleCount 的值，然后自增 1，再对 paddleOcrUrls.size() 取余，获取 paddle-ocr 服务的下标
        int index = (int) (paddleCount.getAndIncrement() % paddleOcrUrls.size());
        // 获取 paddle-ocr 服务的 URL
        String paddleOcrUrl = paddleOcrUrls.get(index);
        // 调用 paddle-ocr 服务进行 OCR 文本识别
        log.info("调用 paddle-ocr 服务进行 OCR 文本识别，URL：{}", paddleOcrUrl);
        String ocrResult = restTemplate.postForObject(paddleOcrUrl, new PaddleOcrPostParam(imgPath), String.class);
        log.info("调用 paddle-ocr 服务进行 OCR 文本识别，结果：{}", ocrResult);
        // 2. 将识别的文本封装到 TextAccuracyLocationDto 对象中
        return List.of();
    }
}
