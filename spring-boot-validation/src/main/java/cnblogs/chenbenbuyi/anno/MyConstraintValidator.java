package cnblogs.chenbenbuyi.anno;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验注解判断处理逻辑
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
        if (value >= min && value <= max) {
            return true;
        }
        return false;
    }
}