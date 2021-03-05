package cnblogs.chenbenbuyi.model;

import cnblogs.chenbenbuyi.anno.ChenBenBuYi;
import cnblogs.chenbenbuyi.common.UpdateValid;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @author chen
 * @date 2021/3/2 20:47
 */
@Data
@NoArgsConstructor
public class Person {

    @NotNull(groups = UpdateValid.class)
    private Integer id;

    @NotNull
    @Size(min = 6, max = 12)
    private String name;

    @ChenBenBuYi(min = 3, max = 5)
    private Integer age;

    /**
     * @Valid 用于嵌套校验，在这里也就会激活Address 对象内字段标注的校验
     * 嵌套可以是单个对象也可以是集合，也就是说写成 List<Address> address 也是可以的
     */
    @Valid
    @NotNull
    private Address address;
}