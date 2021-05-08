package cnblogs.chenbenbuyi.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.DigestUtils;

import java.time.Duration;
import java.util.HashMap;

/**
 * @author chen
 * @date 2021/3/25 19:53
 * @Description RedisTemplate 配置模板
 */
@Configuration
public class RedisConfig<K,V> extends CachingConfigurerSupport{

    /**
     * 自定义RedisTemplate ,原始的{@link RedisTemplate} 采用的是默认的序列化器 JdkSerializationRedisSerializer
     * 在 {@link RedisAutoConfiguration} 中 redisTemplate 方法上的注解为 @ConditionalOnMissingBean(name = "redisTemplate")，所以，自定义的可以替代默认的配置
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<K, V> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<K, V> template = new RedisTemplate<>();
        // 设置连接工厂
        template.setConnectionFactory(redisConnectionFactory);

        //设置key的序列化方式()
        template.setKeySerializer(keySerializer());
        template.setHashKeySerializer(keySerializer());
        // 设置 value 的序列化器设置
        template.setValueSerializer(valueSerializer());
        template.setHashValueSerializer(valueSerializer());
        template.afterPropertiesSet();
        return template;
    }




    /**
     * @Cacheable 注解存储redis缓存数据和通过RedisTemplate去获取存储的数据，由于@Cacheable默认的序列化编码存储到数据为byte类型，而RedisTemplate的默认编码为JdkSerializationRedisSerializer，所以要通过设置同一个序列化方式去解决问题
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(module);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)).entryTtl(Duration.ofDays(1));
        return configuration;
    }



    /**
     * 自定义缓存注解key的生成策略。 这里是生成的key是：类全名.方法名 方法参数（的md5加密）
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder prefix = new StringBuilder();
            prefix.append(target.getClass().getName());
            prefix.append(".").append(method.getName());
            StringBuilder sb = new StringBuilder();
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return prefix.append(DigestUtils.md5DigestAsHex(sb.toString().getBytes()));
        };
    }


    /**
     *  自定义缓存管理器
     *  实际上，引入了Redis之后，通过自动配置原理机制{@link org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration}会向容器中配置并注入了默认的Redis的缓存管理器{@link org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration#cacheManager(org.springframework.boot.autoconfigure.cache.CacheProperties, org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers, org.springframework.beans.factory.ObjectProvider, org.springframework.beans.factory.ObjectProvider, org.springframework.data.redis.connection.RedisConnectionFactory, org.springframework.core.io.ResourceLoader)}
     *  这里自定义缓存管理器，便于自定义配置
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory factory) {
        RedisCacheConfiguration redisCacheConfiguration = redisCacheConfiguration();
        //以锁写入的方式创建RedisCacheWriter对象
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(factory);
        return new RedisCacheManager(writer, redisCacheConfiguration);
    }



    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会抛出异常
        /** Since 2.10 use {@link #activateDefaultTyping(PolymorphicTypeValidator,DefaultTyping)} instead */
        // objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,JsonTypeInfo.As.PROPERTY);
        //解决时间序列化问题
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }
}
