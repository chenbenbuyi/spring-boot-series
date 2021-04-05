package cnblogs.chenbenbuyi.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author chen
 * @date 2021/4/5 17:47
 * @Description
 */

@Configuration
public class MyCacheConfig {

    /**
     *  向容器中注入自定义的KeyGenerator
     */
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> method.getName() + "[" + Arrays.asList(params).toString() + "]";
    }
}
