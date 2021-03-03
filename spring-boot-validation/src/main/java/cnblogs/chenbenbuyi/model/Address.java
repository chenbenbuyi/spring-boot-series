package cnblogs.chenbenbuyi.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chen
 * @date 2021/3/3 23:34
 * @Description 地址信息，用于嵌套校验测试
 */

@Data
public class Address {

    @NotNull(message = "详细地址不能为空")
    private String addres;
}
