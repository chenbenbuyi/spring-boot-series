package cnblogs.chenbenbuyi.model;

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

    @NotNull(message = "用户Id不能为空", groups = UpdateValid.class)
    private Integer id;

    @NotNull(message = "用户名不能为空哟")
    @Size(min = 6, max = 12, message = "字符数必须在 6 -12 个")
    private String name;

    @NotNull
    @Min(value = 20, message = "年轻最小20，不能再小了！")
    private Integer age;

    /**
     *   @Valid 用于嵌套校验，在这里也就会激活Address 对象内字段标注的校验
     */
    @Valid
    @NotNull(message = "地址信息不能为空")
    private Address address;
}