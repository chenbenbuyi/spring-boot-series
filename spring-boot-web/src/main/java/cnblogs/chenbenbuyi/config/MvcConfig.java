package cnblogs.chenbenbuyi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chen
 * @date 2021/3/31 21:27
 * @Description
 *  详见 ：https://docs.spring.io/spring-boot/docs/2.2.13.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-auto-configuration
 *      If you want to keep those Spring Boot MVC customizations and make more MVC customizations (interceptors, formatters, view controllers, and other features), you can add your own @Configuration class of type WebMvcConfigurer but without @EnableWebMvc.
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {

}
