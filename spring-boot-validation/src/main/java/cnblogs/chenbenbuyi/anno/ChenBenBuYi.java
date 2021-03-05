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
 *  @Repeatable 是 1.8新增的元注解，为了解决注解不能在同一个地方重复使用而出现的，表示标记的注解可以多次应用于相同的声明或类型，
 *  简单理解就是在同一个地方可以使用多次 比如@NotNull 你可以在同一个字段上多次标注
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
@NotNull
public @interface ChenBenBuYi {

    /**
     * Bean Validation API 规范的要求message groups payload 三个属性是必须的
     */
    String message() default "{chenbenbuyi.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 自定义的限制条件
     * 给定 default 表示注解上可以不设置用默认值，否则必须显示设置才能通过编译
     */
    long min() default 0;

    long max() default 0;

    /**
     * 要使得 @ChenBenBuYi 注解可以重复使用，需声明相应的容器注解，@Repeatable 标注指定的注解即为容器注解
     * 该容器注解必须要声明一个 value 方法，并且返回值是一个ChenBenBuYi[]类型，否则编译报错。
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ChenBenBuYi[] value();
    }
}
