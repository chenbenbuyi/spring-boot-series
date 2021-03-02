package cnblogs.chenbenbuyi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

/**
 * @author chen
 * @date 2021/3/2 20:47
 */
@Data
@NoArgsConstructor
public class Person {

    private Integer id;

    @NotNull
    @Size(min = 6,max = 12)
    private String name;

    @NotNull
    @Min(20)
    private Integer age;
}