package cnblogs.chenbenbuyi.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
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
//public class MyCacheConfig extends CachingConfigurerSupport{

    /**
     *  方式一：
     *  向容器中注入自定义的KeyGenerator,这种方式可以在具体的注解中显示的指定具体的键的生成策略，没有指定的还是使用默认的 {@link SimpleKeyGenerator}
     *
     */
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> method.getName() + "[" + Arrays.asList(params).toString() + "]";
    }


    /**
     *  方式二：
     *  也可以通过重写 {@link CachingConfigurerSupport} 中的keyGenerator方法自定义来统一定义，这种就是全局性的
     */
//    @Override
//    public KeyGenerator keyGenerator() {
//        return (target, method, params) -> method.getName() + "[" + Arrays.asList(params).toString() + "]";
//    }
}
