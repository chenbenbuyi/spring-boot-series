package cnblogs.chenbenbuyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chen
 * @date 2021/4/5 15:59
 * {@link CacheAutoConfiguration}  ps: 单纯测试缓存是不用引入额外依赖的，因为自动配置类已经在Spring Boot的自动配置依赖包(spring-boot-autoconfigure)里了，而且缓存本来就是Spring 核心包本来就支持的
 * Spring Boot 缓存的执行原理：
 *  1 容器启动，在自动配置的注册过程中，会先导入一些默认定义好的缓存类型组件{@link CacheAutoConfiguration.CacheConfigurationImportSelector},从中可以看到默认的一些缓存组件的配置类
 *  2 默认情况下，生效的为 {@link org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration} ,可开启 Spring Boot 的debug查看哪些组件类生效
 *  3 SimpleCacheConfiguration 向容器中注册了其自己的缓存管理器 {@link ConcurrentMapCacheManager}
 *      缓存管理器的作用在于根据名字获取缓存组件(最终操作缓存的就是缓存组件)，该缓存管理器创建{@link ConcurrentMapCacheManager#getCache(java.lang.String)}了一个缓存组件 ConcurrentMapCache
 *  4 ConcurrentMapCache 就是最终操作缓存的缓存组件，而且用作缓存的就是其内部维护的 {@link ConcurrentHashMap}
 *
 *  5 在切换Redis作为缓存组件并引入了Redis的自动配置组件之后，Spring Boot就会注册Redis的缓存管理器，则默认的的配置SimpleCacheConfiguration就不会再生效了
 *
 */
@MapperScan("cnblogs.chenbenbuyi.mapper")
@SpringBootApplication
// 开启基于注解的缓存
@EnableCaching
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
