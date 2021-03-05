package cnblogs.chenbenbuyi.anno;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验注解判断处理逻辑
 *  总结起来，自定义注解分两步：
 *      ① 根据官方注解，依样画瓢定义自己注解
 *      ② 定义注解校验的逻辑处理实现类
 */
public class MyConstraintValidator implements ConstraintValidator<ChenBenBuYi, Integer> {
    private long max;
    private long min;

    @Override
    public void initialize(ChenBenBuYi chenBenBuYi) {
        this.max = chenBenBuYi.max();
        this.min = chenBenBuYi.min();
    }

    /**
     * @param value 入参传的值
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 这里要返回true,通过该验证，才能然自定义注解中嵌套的 @NotNull 生效
        if(value==null){
            return true;
        }
        // 正真的判断逻辑
        if (value >= min && value <= max) {
            return true;
        }
        return false;
    }
}