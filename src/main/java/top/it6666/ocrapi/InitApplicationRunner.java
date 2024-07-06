package top.it6666.ocrapi;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author BNTang
 * @version 1.0
 * @description 项目启动后执行，进行一些初始化操作
 * @since 2024/7/7 星期日
 **/
@Component
public class InitApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 雪花算法ID生成器初始化
        IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
        YitIdHelper.setIdGenerator(options);
        // 如上的配置主要就是为 YitIdHelper 设置一个 workerId，workerId 主要是区别不同的机器，或不同应用的唯一ID
    }
}
