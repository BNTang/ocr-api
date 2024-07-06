package top.it6666.ocrapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author BNTang
 * @version 1.0
 * @description RestTemplate 配置
 * @since 2024/7/7 星期日
 **/
// @Configuration：标记为配置类，在这个类中可以配置一些 Bean 对象
@Configuration
// @Bean：标记为 Bean 对象，交给 Spring 管理
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        // RestTemplate：Spring 提供的用于访问 REST 服务的客户端工具
        // 1. 创建一个 RestTemplate 对象
        RestTemplate restTemplate = new RestTemplate(factory);
        // 2. 返回这个 RestTemplate 对象
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        // SimpleClientHttpRequestFactory：简单的 HTTP 请求工厂，用于创建 HTTP 请求
        // 1. 创建一个简单的 HTTP 请求工厂
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 2. 设置连接超时时间，即与服务器建立连接的超时时间
        factory.setConnectTimeout(15000);
        // 3. 设置读取超时时间，即从服务器读取数据的超时时间
        factory.setReadTimeout(20000);
        // 4. 返回这个 HTTP 请求工厂
        return factory;
    }
}
