package cnblogs.chenbenbuyi.anno;

import java.lang.annotation.*;

/**
 * @author chen
 * @date 2021/3/6 0:19
 * @Description 自定义可重复注解
 *  啥，不知道运用场景？
 *  典型的 如 {@link org.springframework.context.annotation.PropertySource} 可以引入多个配置文件，如
 * @PropertySource(value = {"classpath:test.properties"})
 * @PropertySource(value = {"classpath:test2.properties"})
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyReapeatable.Container.class)
@Documented
public @interface MyReapeatable {
    String value() default "";


    /**
     * 定义容器注解
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface Container {
        MyReapeatable[] value();
    }
}
