package cnblogs.chenbenbuyi.anno;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author chen
 * @date 2021/3/5 23:09
 * @Description 依葫芦画瓢自定义 ChenBenBuYi 校验注解
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
/**
 *  @Repeatable 是 1.8新增的元注解，简单理解就是在同一个地方可以使用多次 比如@NotNull 你可以在同一个字段上多次标注
 */
@Repeatable(ChenBenBuYi.List.class)
@Documented
/**
 * 指定校验的实现类，可以是一个或多个。但是参见官方的注解这个都是空值，尚不清楚官方如何绑定具体的校验逻辑类
 */
@Constraint(validatedBy = {MyConstraintValidator.class})
/**
 * 自定义校验注解可以复用内嵌的注解，比如这里标注了 @NotNull注解，这样@ChenBenBuYi就兼具了@NotNull的功能。
 */
@NotNull(message = "不能为空")
public @interface ChenBenBuYi {

    /**
     * Bean Validation API 规范的要求message groups payload 三个属性是必须的
     */
    String message() default "{chenbenbuyi.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     *  自定义的限制条件
     */
    long min() default 0;

    long max() default 0;

    int[] values() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        ChenBenBuYi[] value();
    }
}
