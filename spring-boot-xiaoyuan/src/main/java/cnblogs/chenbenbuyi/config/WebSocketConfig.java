package cnblogs.chenbenbuyi.config;

import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Date 2022/2/20 14:21
 * @Created by csxian
 * 配置后 spring boot 才能去扫描后面的关于 websocket 的注解
 * 该种方式并不需要 @EnableWebSocket 启用注解
 */
@Configuration
public class WebSocketConfig {
    /**
     *  在使用内嵌容器的Spring Boot应用中使用@ServerEndpoint，你需要声明一个单独的ServerEndpointExporter
     *  该bean将使用底层的WebSocket容器注册任何被@ServerEndpoint注解的beans。没有ServerEndpointExporter的话会报404。
     *  当部署到一个单独的servlet容器时，该角色将被一个servlet容器初始化方法执行，就不需要ServerEndpointExporter对象了
     */
//    @Bean
//    public ServerEndpointExporter serverEndpoint() {
//        return new ServerEndpointExporter();
//    }
}
