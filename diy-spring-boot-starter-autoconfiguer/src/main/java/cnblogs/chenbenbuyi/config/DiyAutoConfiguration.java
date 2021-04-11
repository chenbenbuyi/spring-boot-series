package cnblogs.chenbenbuyi.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chen
 * @date 2021/4/11 18:50
 * @Description
 */
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
@ConditionalOnWebApplication
public class DiyAutoConfiguration {
}
